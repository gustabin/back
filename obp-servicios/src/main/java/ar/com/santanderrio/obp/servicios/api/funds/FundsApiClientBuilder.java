package ar.com.santanderrio.obp.servicios.api.funds;

import ar.com.santanderrio.obp.base.webservice.interceptor.RestLoggingInterceptor;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfigFunds;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxHeadersInterceptor;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxObpContext;
import ar.com.santanderrio.obp.servicios.api.common.utils.CommonApiErrorResponseHandler;
import ar.com.santanderrio.obp.servicios.api.common.utils.GsonDateTypeConverter;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplateBuilder;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthTokenProvider;
import com.google.gson.GsonBuilder;
import org.joda.time.format.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.Assert;

import java.net.Proxy;
import java.util.*;

public class FundsApiClientBuilder {
    private Proxy proxy;
    private IatxObpContext iatxContext;
    private ApiConfigFunds fundsApiConfig;

    private OAuthTokenProvider oauthTokenProvider;
    private OAuth2ResourceDetails resourceDetails;
    private OAuth2RestTemplate oauth2RestTemplate;

    public FundsApiClientBuilder withIatxObpContext(IatxObpContext iatxObpContextConn) {
        this.iatxContext = iatxObpContextConn;
        return this;
    }

    public FundsApiClientBuilder withFundsApiConfig(ApiConfigFunds fundsApiConfig) {
        this.fundsApiConfig = fundsApiConfig;
        return this;
    }

    public FundsApiClientBuilder withOAuthRestTemplate(OAuth2RestTemplate oAuth2RestTemplate) {
        this.oauth2RestTemplate = oAuth2RestTemplate;
        return this;
    }

    public FundsApiClientBuilder withResourceDetails(OAuth2ResourceDetails resourceDetails) {
        this.resourceDetails = resourceDetails;
        return this;
    }

    public FundsApiClientBuilder withProxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    public FundsApiClientBuilder withOAuthTokenProvider(OAuthTokenProvider oauthTokenProvider) {
        this.oauthTokenProvider = oauthTokenProvider;
        return this;
    }

    public static FundsApiClientBuilder builder() {
        return new FundsApiClientBuilder();
    }

    public FundsApiClient build() {
        Assert.notNull(fundsApiConfig, "apif-api_mutual_funds Scope missing");
        if(oauth2RestTemplate == null) {
            Assert.notNull(iatxContext, "iatx context details missing");
            Assert.notNull(oauthTokenProvider, "oauthTokenProvider missing");
            Assert.notNull(resourceDetails, "apif-api_mutual_funds resource details missing");
            oauth2RestTemplate = buildRestTemplate();
        }

        return new FundsApiClient(customPaths());
    }

    private Map<String, String> customPaths() {
        Map<String, String> paths = new HashMap<String, String>();

        // URLS
        paths.put(FundsApiConstants.Paths.HOLDINGS_BFF, fundsApiConfig.getHoldingsBffPath());
        paths.put(FundsApiConstants.Paths.REDEMPTIONS_BFF, fundsApiConfig.getFundsRedemptionsPath());

        // Cache
        paths.put(FundsApiConstants.Paths.HOLDINGS_CACHE, fundsApiConfig.getCleanCacheHoldingsPath());
        paths.put(FundsApiConstants.Paths.PYL_SERVICE_CACHE, fundsApiConfig.getCleanCachePylPath());

        return paths;
    }

    private BufferingClientHttpRequestFactory buildFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(5000);
        factory.setOutputStreaming(false);
        if(proxy != null) {
            factory.setProxy(proxy);
        }

        return new BufferingClientHttpRequestFactory(factory);
    }

    private OAuth2RestTemplate buildRestTemplate() {
        OAuth2RestTemplate restTemplate = OAuth2RestTemplateBuilder.builder()
                .resourceDetails(resourceDetails)
                .oauthTokenProvider(oauthTokenProvider)
                .build();

        List<HttpMessageConverter<?>> msgConverters = getMsgConverters();
        CommonApiErrorResponseHandler commonApiErrorResponseHandler = new CommonApiErrorResponseHandler(msgConverters);

        restTemplate.setMessageConverters(msgConverters);
        restTemplate.setErrorHandler(commonApiErrorResponseHandler);
        restTemplate.setRequestFactory(buildFactory());

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new IatxHeadersInterceptor(iatxContext));
        interceptors.add(new RestLoggingInterceptor());
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

    private List<HttpMessageConverter<?>> getMsgConverters() {
        List<HttpMessageConverter<?>> msgConverters = new ArrayList<HttpMessageConverter<?>>();

        DateTimeFormatter formatWithTimezone = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").withZoneUTC();
        DateTimeFormatter localDateFormat = DateTimeFormat.forPattern("yyyy-MM-dd");

        DateTimePrinter printer = formatWithTimezone.getPrinter();
        DateTimeParser[] parsers = { formatWithTimezone.getParser(), localDateFormat.getParser() };

        DateTimeFormatter compositeFormat = new DateTimeFormatterBuilder()
                .append(printer, parsers)
                .toFormatter();

        GsonDateTypeConverter dateTypeConverter = new GsonDateTypeConverter()
                .withDeserializationFormat(compositeFormat)
                .withSerializationFormat(compositeFormat);

        GsonHttpMessageConverter gsonHttpMsgConverter = new GsonHttpMessageConverter();
        gsonHttpMsgConverter.setGson(new GsonBuilder()
                .registerTypeAdapter(Date.class, dateTypeConverter)
                .create());

        msgConverters.add(gsonHttpMsgConverter);

        return msgConverters;
    }
}

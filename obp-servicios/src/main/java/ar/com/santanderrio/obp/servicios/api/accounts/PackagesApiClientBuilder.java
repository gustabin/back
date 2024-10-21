package ar.com.santanderrio.obp.servicios.api.accounts;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxHeadersInterceptor;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxObpContext;
import ar.com.santanderrio.obp.servicios.api.common.utils.CommonApiErrorResponseHandler;
import ar.com.santanderrio.obp.servicios.api.common.utils.GsonDateTypeConverter;
import ar.com.santanderrio.obp.base.webservice.interceptor.RestLoggingInterceptor;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplateBuilder;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthTokenProvider;
import org.joda.time.format.*;
import com.google.gson.GsonBuilder;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.net.URI;
import java.net.Proxy;

public class PackagesApiClientBuilder {
    private Proxy proxy;
    private IatxObpContext iatxContext;

    private ApiConfig packagesApiConfig;

    private OAuthTokenProvider oauthTokenProvider;
    private OAuth2ResourceDetails resourceDetails;
    private OAuth2RestTemplate oauth2RestTemplate;

    public PackagesApiClientBuilder withIatxObpContext(IatxObpContext iatxObpContextConn) {
        this.iatxContext = iatxObpContextConn;
        return this;
    }

    public PackagesApiClientBuilder withAccountApiConfig(ApiConfig packagesApiConfig) {
        this.packagesApiConfig = packagesApiConfig;
        return this;
    }

    public PackagesApiClientBuilder withOAuthRestTemplate(OAuth2RestTemplate oAuth2RestTemplate) {
        this.oauth2RestTemplate = oAuth2RestTemplate;
        return this;
    }

    public PackagesApiClientBuilder withResourceDetails(OAuth2ResourceDetails resourceDetails) {
        this.resourceDetails = resourceDetails;
        return this;
    }

    public PackagesApiClientBuilder withProxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    public PackagesApiClientBuilder withOAuthTokenProvider(OAuthTokenProvider oauthTokenProvider) {
        this.oauthTokenProvider = oauthTokenProvider;
        return this;
    }

    public static PackagesApiClientBuilder builder() {
        return new PackagesApiClientBuilder();
    }

    public PackagesApiClient build() {
        Assert.notNull(packagesApiConfig, "packages-api Scope missing");
        if(oauth2RestTemplate == null) {
            Assert.notNull(iatxContext, "iatx context details missing");
            Assert.notNull(oauthTokenProvider, "oauthTokenProvider missing");
            Assert.notNull(resourceDetails, "packages-api resource details missing");
            oauth2RestTemplate = buildRestTemplateApi();
        }

        return new PackagesApiClient(URI.create(packagesApiConfig.getBasePath()), oauth2RestTemplate);
    }

    private List<HttpMessageConverter<?>> getMessageConvertersHttp() {
        List<HttpMessageConverter<?>> messageConvertersList = new ArrayList<HttpMessageConverter<?>>();

        DateTimeFormatter localDateFormatApi = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTimeFormatter formatWithTimezoneApi = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").withZoneUTC();

        DateTimeParser[] parsers = { formatWithTimezoneApi.getParser(), localDateFormatApi.getParser() };
        DateTimePrinter printer = formatWithTimezoneApi.getPrinter();

        DateTimeFormatter compositeFormatterApi = new DateTimeFormatterBuilder()
                .append(printer, parsers)
                .toFormatter();

        GsonDateTypeConverter dateTypeConverter = new GsonDateTypeConverter()
                .withDeserializationFormat(compositeFormatterApi)
                .withSerializationFormat(compositeFormatterApi);

        GsonHttpMessageConverter gsonHttpMessageConverterApi = new GsonHttpMessageConverter();
        gsonHttpMessageConverterApi.setGson(new GsonBuilder()
                .registerTypeAdapter(Date.class, dateTypeConverter)
                .create());

        messageConvertersList.add(gsonHttpMessageConverterApi);

        return messageConvertersList;
    }

    private BufferingClientHttpRequestFactory buildFactoryApi() {
        SimpleClientHttpRequestFactory factoryApi = new SimpleClientHttpRequestFactory();
        factoryApi.setConnectTimeout(5000);
        factoryApi.setReadTimeout(5000);
        factoryApi.setOutputStreaming(false);
        if(proxy != null) {
            factoryApi.setProxy(proxy);
        }

        return new BufferingClientHttpRequestFactory(factoryApi);
    }

    private OAuth2RestTemplate buildRestTemplateApi() {
        OAuth2RestTemplate restTemplateApi = OAuth2RestTemplateBuilder.builder()
                .resourceDetails(resourceDetails)
                .oauthTokenProvider(oauthTokenProvider)
                .build();

        List<HttpMessageConverter<?>> messageConvertersApi = getMessageConvertersHttp();
        CommonApiErrorResponseHandler commonApiErrorResponseHandlerApi = new CommonApiErrorResponseHandler(messageConvertersApi);

        restTemplateApi.setMessageConverters(messageConvertersApi);
        restTemplateApi.setErrorHandler(commonApiErrorResponseHandlerApi);
        restTemplateApi.setRequestFactory(buildFactoryApi());

        List<ClientHttpRequestInterceptor> interceptorsApi = new ArrayList<ClientHttpRequestInterceptor>();
        interceptorsApi.add(new IatxHeadersInterceptor(iatxContext));
        interceptorsApi.add(new RestLoggingInterceptor());
        restTemplateApi.setInterceptors(interceptorsApi);

        return restTemplateApi;
    }

}

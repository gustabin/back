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

public class AccountsApiClientBuilder {
    private Proxy proxy;
    private IatxObpContext iatxContext;
    private ApiConfig accountsApiConfig;

    private OAuthTokenProvider oauthTokenProvider;
    private OAuth2ResourceDetails resourceDetails;
    private OAuth2RestTemplate oauth2RestTemplate;

    public AccountsApiClientBuilder withIatxObpContext(IatxObpContext iatxObpContextConn) {
        this.iatxContext = iatxObpContextConn;
        return this;
    }

    public AccountsApiClientBuilder withAccountApiConfig(ApiConfig accountsApiConfig) {
        this.accountsApiConfig = accountsApiConfig;
        return this;
    }

    public AccountsApiClientBuilder withOAuthRestTemplate(OAuth2RestTemplate oAuth2RestTemplate) {
        this.oauth2RestTemplate = oAuth2RestTemplate;
        return this;
    }

    public AccountsApiClientBuilder withResourceDetails(OAuth2ResourceDetails resourceDetails) {
        this.resourceDetails = resourceDetails;
        return this;
    }

    public AccountsApiClientBuilder withProxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    public AccountsApiClientBuilder withOAuthTokenProvider(OAuthTokenProvider oauthTokenProvider) {
        this.oauthTokenProvider = oauthTokenProvider;
        return this;
    }

    public static AccountsApiClientBuilder builder() {
        return new AccountsApiClientBuilder();
    }

    public AccountsApiClient build() {
        Assert.notNull(accountsApiConfig, "accounts-api Scope missing");
        if(oauth2RestTemplate == null) {
            Assert.notNull(iatxContext, "iatx context details missing");
            Assert.notNull(oauthTokenProvider, "oauthTokenProvider missing");
            Assert.notNull(resourceDetails, "accounts-api resource details missing");
            oauth2RestTemplate = buildRestTemplate();
        }

        return new AccountsApiClient(URI.create(accountsApiConfig.getBasePath()), oauth2RestTemplate);
    }

    private List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

        DateTimeFormatter localDateFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTimeFormatter formatWithTimezone = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").withZoneUTC();

        DateTimeParser[] parsers = { formatWithTimezone.getParser(), localDateFormat.getParser() };
        DateTimePrinter printer = formatWithTimezone.getPrinter();

        DateTimeFormatter compositeFormatter = new DateTimeFormatterBuilder()
                .append(printer, parsers)
                .toFormatter();

        GsonDateTypeConverter dateTypeConverter = new GsonDateTypeConverter()
                .withDeserializationFormat(compositeFormatter)
                .withSerializationFormat(compositeFormatter);

        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(new GsonBuilder()
                .registerTypeAdapter(Date.class, dateTypeConverter)
                .create());

        messageConverters.add(gsonHttpMessageConverter);

        return messageConverters;
    }

    private BufferingClientHttpRequestFactory buildFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
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

        List<HttpMessageConverter<?>> messageConverters = getMessageConverters();
        CommonApiErrorResponseHandler commonApiErrorResponseHandler = new CommonApiErrorResponseHandler(messageConverters);

        restTemplate.setMessageConverters(messageConverters);
        restTemplate.setErrorHandler(commonApiErrorResponseHandler);
        restTemplate.setRequestFactory(buildFactory());

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new IatxHeadersInterceptor(iatxContext));
        interceptors.add(new RestLoggingInterceptor());
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }
    
}

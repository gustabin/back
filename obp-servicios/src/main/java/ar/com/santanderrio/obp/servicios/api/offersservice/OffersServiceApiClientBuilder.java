package ar.com.santanderrio.obp.servicios.api.offersservice;

import java.net.Proxy;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.DateTimePrinter;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.Assert;

import com.google.gson.GsonBuilder;

import ar.com.santanderrio.obp.base.webservice.interceptor.RestLoggingInterceptor;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxHeadersInterceptor;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxObpContext;
import ar.com.santanderrio.obp.servicios.api.common.utils.CommonApiErrorResponseHandler;
import ar.com.santanderrio.obp.servicios.api.common.utils.GsonDateTypeConverter;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplateBuilder;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthTokenProvider;

public class OffersServiceApiClientBuilder {
    private ApiConfig offersApiConfig;
    private IatxObpContext iatxObpContext;

    private OAuth2ResourceDetails resourceDetails;
    private OAuthTokenProvider oauthTokenProvider;
    private OAuth2RestTemplate oauth2RestTemplate;

    private Proxy proxy;

    public static OffersServiceApiClientBuilder builder() {
        return new OffersServiceApiClientBuilder();
    }

    public OffersServiceApiClientBuilder withCustomersApiConfig(ApiConfig offersApiConfig) {
        this.offersApiConfig = offersApiConfig;
        return this;
    }

    public OffersServiceApiClientBuilder withIatxObpContext(IatxObpContext iatxObpContext) {
        this.iatxObpContext = iatxObpContext;
        return this;
    }

    public OffersServiceApiClientBuilder withResourceDetails(OAuth2ResourceDetails resourceDetails) {
        this.resourceDetails = resourceDetails;
        return this;
    }

    public OffersServiceApiClientBuilder withOAuthRestTemplate(OAuth2RestTemplate oAuth2RestTemplate) {
        this.oauth2RestTemplate = oAuth2RestTemplate;
        return this;
    }

    public OffersServiceApiClientBuilder withOAuthTokenProvider(OAuthTokenProvider oauthTokenProvider) {
        this.oauthTokenProvider = oauthTokenProvider;
        return this;
    }

    public OffersServiceApiClientBuilder withProxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    public OffersServiceApiClient build() {
        Assert.notNull(offersApiConfig, "offers api Scope missing");

        if(oauth2RestTemplate == null) {
            Assert.notNull(iatxObpContext, "iatx context details missing");
            Assert.notNull(oauthTokenProvider, "oauthTokenProvider missing");
            Assert.notNull(resourceDetails, "customers api resource details missing");
            oauth2RestTemplate = buildRestTemplate();
        }
        return new OffersServiceApiClient(URI.create(offersApiConfig.getBasePath()), oauth2RestTemplate);
    }

    private OAuth2RestTemplate buildRestTemplate() {
        OAuth2RestTemplate restTemplate = OAuth2RestTemplateBuilder.builder()
                .oauthTokenProvider(oauthTokenProvider)
                .resourceDetails(resourceDetails)
                .build();

        List<HttpMessageConverter<?>> messageConverters = getMessageConverters();
        CommonApiErrorResponseHandler commonApiErrorResponseHandler = new CommonApiErrorResponseHandler(messageConverters);

        restTemplate.setErrorHandler(commonApiErrorResponseHandler);
        restTemplate.setMessageConverters(messageConverters);
        restTemplate.setRequestFactory(buildFactory());
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new IatxHeadersInterceptor(iatxObpContext));
        interceptors.add(new RestLoggingInterceptor());
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
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

    private List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

        DateTimeFormatter localDateFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTimeFormatter formatWithTimezone = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").withZoneUTC();
        DateTimePrinter printer = formatWithTimezone.getPrinter();
        DateTimeParser[] parsers = { formatWithTimezone.getParser(), localDateFormat.getParser() };

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
}

package ar.com.santanderrio.obp.servicios.api.customers;

import ar.com.santanderrio.obp.base.webservice.interceptor.RestLoggingInterceptor;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxHeadersInterceptor;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxObpContext;
import ar.com.santanderrio.obp.servicios.api.common.utils.CommonApiErrorResponseHandler;
import ar.com.santanderrio.obp.servicios.api.common.utils.GsonDateTypeConverter;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplateBuilder;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthTokenProvider;
import com.google.gson.GsonBuilder;
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

import java.net.Proxy;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomersApiClientBuilder {
    private ApiConfig customersApiConfig;
    private IatxObpContext iatxObpContext;

    private OAuth2ResourceDetails resourceDetails;
    private OAuthTokenProvider oauthTokenProvider;
    private OAuth2RestTemplate oauth2RestTemplate;

    private Proxy proxy;

    public static CustomersApiClientBuilder builder() {
        return new CustomersApiClientBuilder();
    }

    public CustomersApiClientBuilder withCustomersApiConfig(ApiConfig customersApiConfig) {
        this.customersApiConfig = customersApiConfig;
        return this;
    }

    public CustomersApiClientBuilder withIatxObpContext(IatxObpContext iatxObpContext) {
        this.iatxObpContext = iatxObpContext;
        return this;
    }

    public CustomersApiClientBuilder withResourceDetails(OAuth2ResourceDetails resourceDetails) {
        this.resourceDetails = resourceDetails;
        return this;
    }

    public CustomersApiClientBuilder withOAuthRestTemplate(OAuth2RestTemplate oAuth2RestTemplate) {
        this.oauth2RestTemplate = oAuth2RestTemplate;
        return this;
    }

    public CustomersApiClientBuilder withOAuthTokenProvider(OAuthTokenProvider oauthTokenProvider) {
        this.oauthTokenProvider = oauthTokenProvider;
        return this;
    }

    public CustomersApiClientBuilder withProxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    public CustomersApiClient build() {
        Assert.notNull(customersApiConfig, "customers api Scope missing");

        if(oauth2RestTemplate == null) {
            Assert.notNull(iatxObpContext, "iatx context details missing");
            Assert.notNull(oauthTokenProvider, "oauthTokenProvider missing");
            Assert.notNull(resourceDetails, "customers api resource details missing");
            oauth2RestTemplate = buildRestTemplate();
        }
        return new CustomersApiClient(URI.create(customersApiConfig.getBasePath()), oauth2RestTemplate);
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
        interceptors.add(new IatxHeadersInterceptor(iatxObpContext));
        interceptors.add(new RestLoggingInterceptor());
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
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

    private List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

        DateTimeFormatter formatWithTimezone = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").withZoneUTC();
        DateTimeFormatter localDateFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
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
}

package ar.com.santanderrio.obp.servicios.api.sso;

import ar.com.santanderrio.obp.base.webservice.interceptor.RestLoggingInterceptor;
import ar.com.santanderrio.obp.servicios.api.common.utils.CommonApiErrorResponseHandler;
import ar.com.santanderrio.obp.servicios.api.common.utils.CompositeGsonDateTypeConverter;
import ar.com.santanderrio.obp.servicios.api.common.utils.GsonDateTypeConverter;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplateBuilder;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthTokenProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SSOApiBuilderCommons {

    private SSOApiBuilderCommons() {}

    public static OAuth2RestTemplate buildRestTemplate(
            final OAuthTokenProvider oauthTokenProvider,
            final OAuth2ResourceDetails resourceDetails
    ) {
        OAuth2RestTemplate restTemplate = OAuth2RestTemplateBuilder.builder()
                .resourceDetails(resourceDetails)
                .oauthTokenProvider(oauthTokenProvider)
                .build();
        restTemplate.setInterceptors(buildInterceptors());
        restTemplate.setMessageConverters(getMessageConverters());
        restTemplate.setRequestFactory(buildFactory());

        CommonApiErrorResponseHandler commonApiErrorResponseHandler =
                new CommonApiErrorResponseHandler(restTemplate.getMessageConverters());
        restTemplate.setErrorHandler(commonApiErrorResponseHandler);
        return restTemplate;
    }

    public static List<ClientHttpRequestInterceptor> buildInterceptors() {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new RestLoggingInterceptor());
        return interceptors;
    }

    public static List<HttpMessageConverter<?>> getMessageConverters() {
        DateTimeFormatter formatWithTimezone = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").withZoneUTC();
        DateTimeFormatter localDateFormat = DateTimeFormat.forPattern("yyyy-MM-dd");

        DateTimeFormatter compositeFormatter = new DateTimeFormatterBuilder()
                .append(
                        formatWithTimezone.getPrinter(),
                        new DateTimeParser[]{ formatWithTimezone.getParser(), localDateFormat.getParser()}
                ).toFormatter();

        GsonDateTypeConverter dateTypeConverter = new CompositeGsonDateTypeConverter()
                .withDeserializationFormat(compositeFormatter)
                .withSerializationFormat(compositeFormatter);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, dateTypeConverter).create();

        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(gson);

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(gsonHttpMessageConverter);
        return messageConverters;
    }

    public static BufferingClientHttpRequestFactory buildFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(5000);
        factory.setOutputStreaming(false);
        return new BufferingClientHttpRequestFactory(factory);
    }
}

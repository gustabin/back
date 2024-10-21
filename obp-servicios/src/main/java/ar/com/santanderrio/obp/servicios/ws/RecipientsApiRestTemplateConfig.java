package ar.com.santanderrio.obp.servicios.ws;

import ar.com.santanderrio.obp.servicios.api.common.utils.GsonDateTypeConverter;
import com.google.gson.GsonBuilder;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.DateTimePrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class RecipientsApiRestTemplateConfig {

    @Value("${REST.RECIPIENTS-API.READ.TIMEOUT}")
    private Integer readTimeOut;

    @Value("${REST.RECIPIENTS-API.CONNECT.TIMEOUT}")
    private Integer connectTimeOut;

    @Value("${PROXY.REQUIRED:false}")
    private Boolean proxyRequired;

    @Value("${PROXY.ADDRESS}")
    private String proxyAddress;

    @Value("${PROXY.PORT:8080}")
    private Integer proxyPort;

    @Bean(name = "recipientsApiRestTemplate")
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(getRequestFactory()));

        return restTemplate;

    }

    private SimpleClientHttpRequestFactory getRequestFactory() {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeOut);
        factory.setReadTimeout(readTimeOut);
        factory.setOutputStreaming(false);

        if(Boolean.TRUE.equals(proxyRequired)) {

            factory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort)));

        }

        return factory;

    }

    private List<HttpMessageConverter<?>> getMessageConverters() {

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

        messageConverters.add(getGsonMessageConverter());
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new ResourceHttpMessageConverter());
        messageConverters.add(new ByteArrayHttpMessageConverter());

        return messageConverters;

    }

    private GsonHttpMessageConverter getGsonMessageConverter() {

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

        return gsonHttpMessageConverter;

    }

}

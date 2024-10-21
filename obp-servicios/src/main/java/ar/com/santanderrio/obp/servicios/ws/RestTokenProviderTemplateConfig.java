package ar.com.santanderrio.obp.servicios.ws;

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
import java.util.List;

@Configuration
public class RestTokenProviderTemplateConfig {

    @Value("${REST.TOKEN-PROVIDER.READ.TIMEOUT:2000}")
    private Integer readTimeOut;

    @Value("${REST.TOKEN-PROVIDER.CONNECT.TIMEOUT:2000}")
    private Integer connectTimeOut;

    @Value("${PROXY.REQUIRED:false}")
    private Boolean proxyRequired;

    @Value("${PROXY.ADDRESS}")
    private String proxyAddress;

    @Value("${PROXY.PORT:8080}")
    private Integer proxyPort;

    @Bean(name = "tokenProviderRestTemplate")
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

        messageConverters.add(new GsonHttpMessageConverter());
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new ResourceHttpMessageConverter());
        messageConverters.add(new ByteArrayHttpMessageConverter());

        return messageConverters;

    }

}

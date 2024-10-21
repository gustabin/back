package ar.com.santanderrio.obp.servicios.ws;

import ar.com.santanderrio.obp.base.webservice.interceptor.RestLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTransferenciaRsaTemplateConfig {

    @Value("${RESTTRANSFERENCIARSA.READ.TIMOUT:2000}")
    private Integer readTimeOut;

    @Value("${RESTTRANSFERENCIARSA.CONNECT.TIMEOUT:2000}")
    private Integer connectTimeOut;

    @Value("${PROXY.REQUIRED:false}")
    private Boolean proxyRequired;

    @Value("${PROXY.ADDRESS}")
    private String proxyAddress;

    @Value("${PROXY.PORT:8080}")
    private Integer proxyPort;


    @Bean(name = "restTransferenciaRsaTemplate")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory factory = createFactory();
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(factory));
        restTemplate.setInterceptors(createInterceptor(factory));
        return restTemplate;
    }

    private SimpleClientHttpRequestFactory createFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeOut);
        factory.setReadTimeout(readTimeOut);
        factory.setOutputStreaming(false);

        if(proxyRequired) {
            factory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort)));
        }

        return factory;
    }

    private List<ClientHttpRequestInterceptor> createInterceptor(SimpleClientHttpRequestFactory factory) {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new RestLoggingInterceptor());
        return interceptors;
    }
}

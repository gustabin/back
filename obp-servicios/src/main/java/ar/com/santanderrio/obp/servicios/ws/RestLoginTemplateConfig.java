package ar.com.santanderrio.obp.servicios.ws;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import ar.com.santanderrio.obp.base.webservice.interceptor.RestLoggingInterceptor;

@Configuration
public class RestLoginTemplateConfig {

	@Value("${PROXYLOGIN.READ.TIMEOUT:2000}")
	private Integer readTimeOut;

	@Value("${PROXYLOGIN.CONNECT.TIMEOUT:2000}")
	private Integer connectTimeOut;

	@Autowired
	@Bean(name = "restLoginTemplate")
	public RestTemplate restTemplate(final ProxyConfig proxyConfig) {
		RestTemplate restTemplate = new RestTemplate();
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		if (proxyConfig.getProxyRequired()) {
			factory.setProxy(new Proxy(Proxy.Type.HTTP,
					new InetSocketAddress(proxyConfig.getProxyAddress(), proxyConfig.getProxyPort())));
		}
		factory.setConnectTimeout(connectTimeOut);
		factory.setReadTimeout(readTimeOut);
		factory.setOutputStreaming(false);
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(factory));

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new RestLoggingInterceptor());
		restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}
}

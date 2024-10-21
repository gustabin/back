package ar.com.santanderrio.obp.servicios.ws;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import ar.com.santanderrio.obp.base.webservice.interceptor.RestLoggingInterceptor;

/**
 * The Class RestTemplateConfig.
 *
 * @author sergio.e.goldentair
 */
@Configuration
public class RestTemplateConfig {

	/** The read time out. */
	@Value("${REST.READ.TIMEOUT:2000}")
	private Integer readTimeOut;

	/** The connect time out. */
	@Value("${REST.CONNECT.TIMEOUT:2000}")
	private Integer connectTimeOut;

	/** The proxy required. */
	@Value("${PROXY.REQUIRED:false}")
	private Boolean proxyRequired;

	/** The proxy address. */
	@Value("${PROXY.ADDRESS}")
	private String proxyAddress;

	/** The proxy port. */
	@Value("${PROXY.PORT:8080}")
	private Integer proxyPort;

	/**
	 * Configura el restTemplate para que utilice el proxy si esta indicado y
	 * los timeouts.
	 * 
	 * 1) HttpComponentsClientHttpRequestFactory tiene un pool de conexiones.
	 * verlo bien en el was porque rompe con
	 * org.apache.http.conn.ssl.SSLConnectionSocketFactory de httpClient 2)
	 * SimpleClientHttpRequestFactory es el default y no tiene el pool.
	 *
	 * @return RestTemplate
	 */
	@Bean(name = "restTemplate")
	@Primary
	public RestTemplate restTemplate() {
		RestTemplate rest = new RestTemplate();
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(readTimeOut);
		factory.setConnectTimeout(connectTimeOut);
		if (proxyRequired) {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));
			factory.setProxy(proxy);
		}
		// set up a buffering request factory, so response body is always
		// buffered
		BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(
				factory);
		factory.setOutputStreaming(false);

		rest.setRequestFactory(bufferingClientHttpRequestFactory);

		// add the interceptor that will handle logging
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new RestLoggingInterceptor());
		rest.setInterceptors(interceptors);

		return rest;
	}
}

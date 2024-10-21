package ar.com.santanderrio.obp.servicios.oauth2.config;

import ar.com.santanderrio.obp.base.webservice.interceptor.RestLoggingInterceptor;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuth2AccessToken;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuth2AccessTokenTypeConverter;
import ar.com.santanderrio.obp.servicios.ws.ProxyConfig;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Source;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class OAuthLoginTemplateConfig {

	@Value("${OAUTHV2.TIMEOUT:2000}")
	private Integer readTimeOut;

	@Value("${OAUTHV2.TIMEOUT:2000}")
	private Integer connectTimeOut;

	@Bean(name= "oauthRestTemplate")
	@Autowired
	public RestTemplate restTemplate(final ProxyConfig proxyConfig) {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

		GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
		gsonHttpMessageConverter.setGson(new GsonBuilder()
				.registerTypeAdapter(OAuth2AccessToken.class, new OAuth2AccessTokenTypeConverter())
				.create());
		messageConverters.add(new ByteArrayHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new ResourceHttpMessageConverter());
		messageConverters.add(new SourceHttpMessageConverter<Source>());
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());
		messageConverters.add(gsonHttpMessageConverter);

		RestTemplate restTemplate = new RestTemplate(messageConverters);
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(connectTimeOut);
		factory.setReadTimeout(readTimeOut);
		factory.setOutputStreaming(false);

		if (proxyConfig.getProxyRequired()) {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyConfig.getProxyAddress(), proxyConfig.getProxyPort()));
			factory.setProxy(proxy);
		}

		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(factory));

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new RestLoggingInterceptor());
		restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}
}

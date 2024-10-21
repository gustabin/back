package ar.com.santanderrio.obp.servicios.ws;

import java.sql.SQLException;
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

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.webservice.interceptor.RestLoggingInterceptor;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxHeadersInterceptor;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxObpContext;

@Configuration
public class RestApiMenuTemplateConfig {

	@Value("${SERVICIO.API.MENU.TIMEOUT:5000}")
	private Integer timeOut;
	
	@Autowired
    private CredentialSecurityFactory credentialSecurityFactory;
	
	@Autowired
    private IatxObpContext iatxObpContext;

	@Autowired
	@Bean(name = "restApiMenuTemplate")
	public RestTemplate restTemplate(final ProxyConfig proxyConfig) {
		RestTemplate restTemplate = new RestTemplate();
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(timeOut);
		factory.setReadTimeout(timeOut);
		factory.setOutputStreaming(false);
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(factory));
		
		initIatxObpContext();

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new IatxHeadersInterceptor(iatxObpContext));
		interceptors.add(new RestLoggingInterceptor());
		restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}
	
	private void initIatxObpContext() {
        try {
            Credential cred = credentialSecurityFactory.getCredentialById(iatxObpContext.getSecurityId());
            iatxObpContext.setIatxUser(cred.getUsuario());
            iatxObpContext.setIatxPassword(cred.getPassword());
        } catch (SQLException ex) {
            throw new IllegalStateException("Error at fetching apimenu securityId credentials");
        }
    }
}

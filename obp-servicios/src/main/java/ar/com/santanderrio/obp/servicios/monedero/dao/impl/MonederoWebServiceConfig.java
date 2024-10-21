/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dao.impl;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;

import ar.com.santanderrio.obp.generated.webservices.monedero.SingleServiceSoap;

/**
 * Configuracioin de monedero.
 *
 * @author Fernando_Bolten
 */
// @Configuration
public class MonederoWebServiceConfig {

	/**
	 * The monedero url.
	 */
	@Value("${MONEDERO.ENDPOINT}")
	private String monederoUrl;

	/**
	 * Monedero.
	 *
	 * @return the single service soap
	 */
	// @Bean
	/** public SingleServiceSoap monedero(KeyStoreFactory keyStoreFactory) { */
	public SingleServiceSoap monedero() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(SingleServiceSoap.class);
		factory.setAddress(monederoUrl);

		factory.getOutInterceptors().clear();
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.getInInterceptors().clear();
		factory.getInInterceptors().add(new LoggingInInterceptor());

		return (SingleServiceSoap) factory.create();
	}
}

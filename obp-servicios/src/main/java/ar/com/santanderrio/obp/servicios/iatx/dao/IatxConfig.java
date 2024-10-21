/*
 * 
 */
package ar.com.santanderrio.obp.servicios.iatx.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import ar.com.santanderrio.obp.base.iatx.helpers.IatxSender;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxSenderImpl;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxSenderMockImpl;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxSenderRouting;
import ar.com.santanderrio.obp.base.profile.Profiles;

/**
 * The Class IatxConfig.
 */
@Configuration
public class IatxConfig {

	/**
	 * Iatx sender impl.
	 *
	 * @return the iatx sender impl
	 */
	@Bean
	@Profile({ Profiles.PRODUCTION, Profiles.HOMO, Profiles.DESA, Profiles.DIAGNOSTIC, Profiles.TOMCAT })
	public IatxSender iatxSenderImpl() {
		return new IatxSenderImpl();
	}

	/**
	 * Iatx sender mock.
	 *
	 * @return the iatx sender
	 */
	@Bean
	@Profile({ Profiles.MOCK, Profiles.TOMCAT })
	public IatxSender iatxSenderMock() {
		return new IatxSenderMockImpl();
	}
	
	/**
	 * Iatx sender impl.
	 *
	 * @return the iatx sender impl
	 */
	@Bean
	@Primary
	@Profile({ Profiles.TOMCAT })
	public IatxSender iatxSenderRouting() {
		return new IatxSenderRouting(iatxSenderImpl(), iatxSenderMock());
	}

}
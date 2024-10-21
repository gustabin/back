package ar.com.santanderrio.obp.servicios.obp.servicios.base.profiles.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.servicios.obp.servicios.base.profiles.beans.impl.DummyProduction;
import ar.com.santanderrio.obp.servicios.obp.servicios.base.profiles.beans.impl.DummyTest;

/**
 * The Class ProfileConfig.
 */
@Configuration
public class ProfileConfig {

	/**
	 * Dummy prod.
	 *
	 * @return the dummy interface
	 */
	@Bean
	@Profile({ Profiles.PRODUCTION, Profiles.HOMO, Profiles.DESA })
	public DummyInterface dummyProd() {
		return new DummyProduction();
	}

	/**
	 * Dummy test.
	 *
	 * @return the dummy interface
	 */
	@Bean
	@Profile(Profiles.TEST)
	public DummyInterface dummyTest() {
		return new DummyTest();
	}
}

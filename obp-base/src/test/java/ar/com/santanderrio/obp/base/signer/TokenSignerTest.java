/**
 * 
 */
package ar.com.santanderrio.obp.base.signer;

import org.hamcrest.core.StringContains;
import org.hamcrest.core.StringStartsWith;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.impl.SignImpl;
import ar.com.santanderrio.obp.base.signer.TokenSignerTest.TokenSignerTestConfiguration;
import ar.com.santanderrio.obp.base.signer.factory.impl.TokenFactoryImpl;
import ar.com.santanderrio.obp.base.signer.impl.TokenSignerImpl;

/**
 * The Class TokenSignerTest.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TokenSignerTestConfiguration.class })
@TestPropertySource(properties = { "APP.ENCODING = UTF-8" })
@ActiveProfiles(Profiles.TEST)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TokenSignerTest {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenSignerTest.class);

	/** The token signer. */
	@Autowired
	@InjectMocks
	private TokenSigner tokenSigner;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The Class TokenSignerTestConfiguration.
	 */
	@Configuration
	@ComponentScan(basePackageClasses = { TokenSignerImpl.class, SignImpl.class, TokenFactoryImpl.class,
			KeyStoreHelperImpl.class, PMCTokenFactory.class,
			SecurityProviderConfig.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
					KeyStoreFactory.class }))
	public static class TokenSignerTestConfiguration {

		/**
		 * Key store factory.
		 *
		 * @return the key store factory
		 */
		@Bean
		KeyStoreFactory keyStoreFactory() {
			return Mockito.mock(KeyStoreFactory.class, new Answer<KeyStore>() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * org.mockito.stubbing.Answer#answer(org.mockito.invocation.
				 * InvocationOnMock)
				 */
				@Override
				public KeyStore answer(InvocationOnMock invocation) throws Throwable {
					KeyStore keyStore = new KeyStore();
					keyStore.setKeystoreType("JKS");
					keyStore.setKeystorePath(Object.class.getResource("/config/test/keyStore/hbkey.jks").getPath());
					keyStore.setKeystoreAlias("bcorio");
					keyStore.setKeystorePassword("hbpassword");
					return keyStore;
				}

			});
		}

		/**
		 * Property configurer.
		 *
		 * @return the property sources placeholder configurer
		 */
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
			return new PropertySourcesPlaceholderConfigurer();
		}
	}

	/**
	 * Probar que la firma se arma correctamente.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void generarFirma() throws DAOException {
		String firma = tokenSigner.obtenerTokenFirmado("PMC");
		LOGGER.info("Firma obtenida:\r\n{}\r\n", firma);
		Assert.assertNotNull(firma);
		Assert.assertThat(firma, StringStartsWith.startsWith("-----BEGIN PKCS7-----"));
		Assert.assertThat(firma,
				StringContains.containsString("MIAGCSqGSIb3DQEHAqCAMIACAQExCzAJBgUrDgMCGgUAMIAGCSqGSIb3DQEHAaCA"));
		Assert.assertThat(firma, StringContains.containsString("-----END PKCS7-----"));
	}
}

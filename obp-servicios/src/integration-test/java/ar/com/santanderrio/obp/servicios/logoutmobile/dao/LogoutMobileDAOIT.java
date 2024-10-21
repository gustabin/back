/**
 * 
 */
package ar.com.santanderrio.obp.servicios.logoutmobile.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClientImpl;
import ar.com.santanderrio.obp.servicios.logoutmobile.dao.LogoutMobileDAOIT.LogoutMobileDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.logoutmobile.entities.LogoutMobileOutEntity;

/**
 * @author sergio.e.goldentair
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { LogoutMobileDAOITConfiguration.class,
        SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "APP.ENCODING = UTF-8",
        "LOGOUT.MOBILE.URL=http://wasinetdesamb.ar.bsch:9087/MBI/services", "LOGOUT.MOBILE.TIMEOUT=10000",
        "DB.LOGOUT.MOBILE.ID:20087", "KEYSTORE.LOGOUT.MOBILE.ID=20104", "KEYSTORE.LOGOUT.MOBILE.IDSEGURIDAD=20104",
        "KEYSTORE.LOGOUT.MOBILE.PATH=/aplicaciones/hb/conf/keyStore/hbkey.jks", "KEYSTORE.LOGOUT.MOBILE.TYPE=JKS" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class LogoutMobileDAOIT {
	@Autowired
	private LogoutMobileDAO logoutMobileDAO;

	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	@Value("${DB.LOGOUT.MOBILE.ID}")
	private String idSeg;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The Class MapServiceDAOITConfiguration.
	 */
	@Configuration
	@ComponentScan(basePackageClasses = { RestWebClientImpl.class, Sign.class, KeyStoreHelperImpl.class,
	        ContextHolder.class, LogoutMobileDAO.class,
	        KeyStoreHelperImpl.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
	                KeyStoreFactory.class }))
	public static class LogoutMobileDAOITConfiguration {
		/**
		 * Key store factory.
		 *
		 * @return the key store factory
		 */
		@Bean
		public KeyStoreFactory keyStoreFactory() {
			return Mockito.mock(KeyStoreFactory.class, new Answer<KeyStore>() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.
				 * InvocationOnMock)
				 */
				@Override
				public KeyStore answer(InvocationOnMock invocation) throws Throwable {
					KeyStore keyStore = new KeyStore();
					keyStore.setKeystoreType("JKS");
					keyStore.setKeystorePath(Object.class.getResource("/config/test/keyStore/hbkey.jks").getPath());
					keyStore.setKeystoreAlias("obp");
					keyStore.setKeystorePassword("hbpassword");
					return keyStore;
				}

			});
		}

		@Bean
		public CredentialSecurityFactory credentialSecurityFactory() {
			return Mockito.mock(CredentialSecurityFactory.class);
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

	@Test
	public void obtenerDatosEncriptados() throws DAOException, SQLException {
		String key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAslDysOnqy3fUZ5L4be2hAS/PV+h+6pMC6wP4syW1lxDqlUFsGrk6U6HuQrDmkujaCvev+G7pBfLBdXHNk52laGQyyWiWsCAPGMtJ9UQFqYIc48LEH56bbne5rItXvgN9c6dUiHpeRMKMO/HOSmD/fLxlPKxgRxZm/m+opGqso1Iet4UvBAM7WTVSqz2t8l1zb4ZHKT9CjSmhFr4oBPk+spYqbxRLbDJXJWgkxaHcrVqVVttfS6Lc/dDwO860zHC+DyzU/rFAlaVL/OnvoDcf/lGaUQxs15q9xx8BfpqnN0KwcY76kSt39HzFDthDmiPDjw/JcASUadHkVdPCd2kVzQIDAQAB";
		Credential cred = new Credential();
		cred.setPassword(key);
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);

		Cliente cliente = new Cliente();
		cliente.setNup("04264078");
		String datoEncriptado = logoutMobileDAO.getDatoEncriptado(cliente);
		Assert.assertNotNull(datoEncriptado);
	}

	@Test
	public void consultaAdhesionMaps() throws DAOException {
		String encryptedData = "6d6bea55108b9395be62a765a28c179ffd2c9a65ab989cc2be10ec4ed9086c62beff6e15928b9a6aed0425196a63c86f28c234b1886c418efd938ef1d5c92eaa231e328b4fdb4c5b0913fedf54b059af66116b692632678008166c80b036b90f5d3962906b1c8f175c4985fc1b29d98a1a554da215a898b711efa2c5be300aba23db9f25df69c06d9b717b49c79c8a867050561e67f1317e92e5a4d56a2318601d84ae4bc32e2f20de191665407ff291b51673c765e7c03fad1756b42dc24bd51b81496d2da9546b9063f4ca1c57bbac779892f0f00bdcc5fb27397f46b06dd8b27f7778583955abd3de10cd608437b6390c5da7b6116a7cba38e127f38c8393";
		LogoutMobileOutEntity logoutMobileOutEntity = logoutMobileDAO.logoutMobile(encryptedData);
		Assert.assertNotNull(logoutMobileOutEntity);
		Assert.assertEquals("0", logoutMobileOutEntity.getRespuesta());
	}

}
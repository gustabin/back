package ar.com.santanderrio.obp.servicios.blanqueopin.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.config.executor.ExecutorConfig;
import ar.com.santanderrio.obp.generated.webservices.banelco.ObjectFactory;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSTarjetaDTO;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSTerminalData;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSUserData;
import ar.com.santanderrio.obp.servicios.blanqueopin.dao.BlanqueoPinDAOIT.BlanqueoPinDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.blanqueopin.dao.impl.BlanqueoPinDAOImpl;
import ar.com.santanderrio.obp.servicios.blanqueopin.dao.impl.GestionarBanelcoWSImpl;
import ar.com.santanderrio.obp.servicios.blanqueopin.entities.BlanqueoPinEnum;
import ar.com.santanderrio.obp.servicios.blanqueopin.entities.TipoDocumentoBlanqueoPin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { BlanqueoPinDAOITConfiguration.class,
		ExecutorConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "WSBANELCO.POOL.ACTIVO=false",
		"WSBANELCO.URL=https://wssrv3.banelcoservices.com.ar/services//CentroDeServiciosClient",
		"WSBANELCO.TIMEOUT=100000", "WSBANELCO.POOL.SIZE=30", "WSBANELCO.POOL.MAXWAIT=5000", "APP.ENCODING = UTF-8",
		"THREAD.CORE.POOL.SIZE=7", "THREAD.MAX.POOL.SIZE=42", "THREAD.QUEUE.CAPACITY=11" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BlanqueoPinDAOIT {

	@Autowired
	private BlanqueoPinDAO blanqueoPinDAO;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The Class GestionWSTestConfiguration.
	 */
	@Configuration
	@ComponentScan(basePackageClasses = { BlanqueoPinDAOImpl.class, GestionarBanelcoWSImpl.class, Sign.class,
			KeyStoreHelperImpl.class,
			ContextHolder.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
					KeyStoreFactory.class }))
	public static class BlanqueoPinDAOITConfiguration {

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
	public void blanqueoPinTest() throws DAOException {
		ObjectFactory objectFactory = new ObjectFactory();
		WSTarjetaDTO tarjetaDTO = objectFactory.createWSTarjetaDTO();
		tarjetaDTO.setMiembro(objectFactory.createWSTarjetaDTOMiembro("001"));
		tarjetaDTO.setNumero(objectFactory.createWSTarjetaDTONumero("00004517660063696412"));
		WSUserData userData = objectFactory.createWSUserData();
		userData.setNumeroDocumento(objectFactory.createWSUserDataNumeroDocumento("00018375690"));
		userData.setTipoDocumento(
				objectFactory.createWSUserDataTipoDocumento(TipoDocumentoBlanqueoPin.obtenerDatoParaBanelco("N")));
		WSTerminalData terminalData = objectFactory.createWSTerminalData();
		terminalData.setDatosTerminal(objectFactory.createWSTerminalDataDatosTerminal("70"));
		terminalData.setIpOrigen(objectFactory.createWSTerminalDataIpOrigen("180.166.119.135"));
		blanqueoPinDAO.blanquearPin(terminalData, userData, tarjetaDTO, BlanqueoPinEnum.NUMERICO);
	}

}

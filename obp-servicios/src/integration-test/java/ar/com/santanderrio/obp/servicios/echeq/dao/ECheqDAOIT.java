package ar.com.santanderrio.obp.servicios.echeq.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.generated.webservices.echeq.ResponseFull;
import ar.com.santanderrio.obp.servicios.echeq.dao.impl.ECheqAmcoDAOImpl;
import ar.com.santanderrio.obp.servicios.echeq.dao.impl.ECheqGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.echeq.entities.ECheqEmitidoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ECheqEndosadoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ECheqRecibidoInEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;

/**
 * Clase test ECheqDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAOIT.ECheqDAOITConfiguration.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { 
		"APP.ENCODING = UTF-8", 
		"ECHEQ.JKS = ECHEQ",
		"ECHEQ.POOL.ACTIVO = true",
		"ECHEQ.POOL.MAXWAIT = 5000",
		"ECHEQ.POOL.SIZE = 3",
		"ECHEQ.TIMEOUT = 10000",
		"ECHEQ.URL = https://test.echeq.amco.com.ar:8443/echeq-soap/ws", 
		"KEYSTORE.ECHEQ.IDSEGURIDAD = 20104",
		"KEYSTORE.ECHEQ.PATH = /aplicaciones/hb/conf/keyStore/hbkey.jks",
		"KEYSTORE.FACTURAELECTRONICA.IDSEGURIDAD = 20104", 
		"KEYSTORE.ECHEQ.TYPE = JKS" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ECheqDAOIT {

	/** The echeq Amco DAO. */
	@Autowired
	@Qualifier("eCheqAmcoDAOImpl")
	private ECheqAmcoDAO echeqAmcoDAO;

	/**
	 * Init.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The Class ECheqDAOITConfiguration.
	 */
	@Configuration
	@ComponentScan(basePackageClasses = { ECheqGestionarWSImpl.class, ECheqAmcoDAOImpl.class, ContextHolder.class,
			Environment.class, FilePath.class })
	public static class ECheqDAOITConfiguration {

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
				 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
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
		 * Credential security factory.
		 *
		 * @return the credential security factory
		 */
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

		/**
		 * Iatx comm.
		 *
		 * @return the iatx comm
		 */
		@Bean
		public static IatxComm iatxComm() {
			return Mockito.mock(IatxComm.class);
		}
	}

	/**
	 * Obtener cheques recibidos OK test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void obtenerChequesRecibidosOKTest() throws DAOException {
		ECheqRecibidoInEntity request = new ECheqRecibidoInEntity();
		request.setCuit("30696394217");
		List<ResponseFull> respuesta = echeqAmcoDAO.obtenerChequesRecibidos(request);
		Assert.assertNotNull(respuesta);
	}

	/**
	 * Obtener cheques emitidos OK test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void obtenerChequesEmitidosOKTest() throws DAOException {
		ECheqEmitidoInEntity request = new ECheqEmitidoInEntity();
		request.setCuit("30696394217");
		List<ResponseFull> respuesta = echeqAmcoDAO.obtenerChequesEmitidos(request);
		Assert.assertNotNull(respuesta);
	}

	/**
	 * Obtener cheques endosados OK test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void obtenerChequesEndosadosOKTest() throws DAOException {
		ECheqEndosadoInEntity request = new ECheqEndosadoInEntity();
		request.setCuit("30696394217");
		List<ResponseFull> respuesta = echeqAmcoDAO.obtenerChequesEndosados(request);
		Assert.assertNotNull(respuesta);
	}

}

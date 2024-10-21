package ar.com.santanderrio.obp.servicios.compraventa.dao;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.Assert;
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

import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarOperacionCambioParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.ObjectFactory;
import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.OperacionBancaPrivadaDAOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.OperacionBancaPrivadaGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelper;

/**
 * OperacionBancaPrivadaDAOIT
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		OperacionBancaPrivadaGestionarWSImpl.class, OperacionBancaPrivadaDAOImpl.class, 
		ar.com.santanderrio.obp.servicios.compraventa.dao.OperacionBancaPrivadaDAOIT.OperacionBancaPrivadaDAOITConfiguration.class,
		SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = {
		"APP.ENCODING = UTF-8",  
		"BPRIVWS.TIMEOUT = 10000",
		"BPRIVWS.POOL.ACTIVO = true", 
		"BPRIVWS.POOL.MAXWAIT = 5", 
		"BPRIVWS.POOL.SIZE= 5",
		"BPRIVWS.URL = http://webdesabmg02.rio.ar.bsch:8030/BancaPrivada_SucursalSvc.svc" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class OperacionBancaPrivadaDAOIT {

	/** The operacion banca privada DAO. */
	@Autowired
	private OperacionBancaPrivadaDAO operacionBancaPrivadaDAO;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * The Class OperacionBancaPrivadaDAOITConfiguration.
	 */
	@Configuration
	@ComponentScan( basePackageClasses = { ContextHolder.class,
			CryptoHelper.class, Environment.class })
	public static class OperacionBancaPrivadaDAOITConfiguration {

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
	 * Insertar Operacion Cambio OK test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void insertarOperacionCambioOKTest() throws DAOException, SQLException, DatatypeConfigurationException {
		InsertarOperacionCambioParameter parameter = obtenerParameter();
		String respuesta = operacionBancaPrivadaDAO.insertarOperacionCambio(null,null,null);
		Assert.assertNotNull(respuesta);
	}

	private InsertarOperacionCambioParameter obtenerParameter() {
		InsertarOperacionCambioParameter parameter = new InsertarOperacionCambioParameter();
		ObjectFactory of = new ObjectFactory();
		parameter.setBoleto(of.createInsertarOperacionCambioParameterBoleto("FXS53065"));
		parameter.setCantTitu8(of.createInsertarOperacionCambioParameterCantTitu8(new BigDecimal("777")));
		parameter.setCapital(of.createInsertarOperacionCambioParameterCapital(new BigDecimal("59829")));
		parameter.setCuentaDest(of.createInsertarOperacionCambioParameterCuentaDest("2023423657"));
		parameter.setCuentaOri(of.createInsertarOperacionCambioParameterCuentaOri("7003524624"));
		parameter.setCuitCuil(of.createInsertarOperacionCambioParameterCuitCuil("CUIT"));
		parameter.setEspecie(of.createInsertarOperacionCambioParameterEspecie("70021"));
		parameter.setFciTipoCambio(of.createInsertarOperacionCambioParameterFciTipoCambio(new BigDecimal("44.55")));
		parameter.setMoneLiq(of.createInsertarOperacionCambioParameterMoneLiq("P"));
		parameter.setNroCuitCuil(of.createInsertarOperacionCambioParameterNroCuitCuil("8888877777"));
		parameter.setNumMercap(of.createInsertarOperacionCambioParameterNumMercap("A09"));
		parameter.setNup(of.createInsertarOperacionCambioParameterNup("01390639"));
		parameter.setOper(of.createInsertarOperacionCambioParameterOper("SERV"));
		parameter.setOrigenOrden(of.createInsertarOperacionCambioParameterOrigenOrden("ST"));
		parameter.setPrecUni8(of.createInsertarOperacionCambioParameterPrecUni8(new BigDecimal("77.35")));
		parameter.setSucuDest(of.createInsertarOperacionCambioParameterSucuDest("250"));
		parameter.setSucuOri(of.createInsertarOperacionCambioParameterSucuOri("250"));
		parameter.setTipoOrd(of.createInsertarOperacionCambioParameterTipoOrd("V"));
		parameter.setValorInterno8(of.createInsertarOperacionCambioParameterValorInterno8(new BigDecimal("77.35")));

		return parameter;
	}

}

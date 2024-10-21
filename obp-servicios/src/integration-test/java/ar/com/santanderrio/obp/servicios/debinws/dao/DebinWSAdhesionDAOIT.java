package ar.com.santanderrio.obp.servicios.debinws.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.datasource.SystemRoutingDataSource;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestAdhesion;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConsulta;
import ar.com.santanderrio.obp.generated.webservices.debin.Response;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseVendedor;
import ar.com.santanderrio.obp.servicios.debin.entities.ElementoComprobanteAdhesionReporte;
import ar.com.santanderrio.obp.servicios.debinws.dao.impl.DebinGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.debinws.dao.impl.DebinWSSolicitudesDAOImpl;
import ar.com.santanderrio.obp.servicios.debinws.entities.ComprobanteAdhesionEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelper;
import ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO;

/**
 * The Class DebinWSAdhesionDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, 
classes = {
		ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSAdhesionDAOIT.DebinWSAdhesionDAOITConfiguration.class,
		SecurityProviderConfig.class 
}
		)
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { 
		"APP.ENCODING = UTF-8", 
		"DEBINWS.JKS = DEBINWS", 
		"DEBINWS.TIMEOUT = 10000",
		"DEBINWS.POOL.ACTIVO = true", 
		"DEBINWS.POOL.MAXWAIT = 5", 
		"DEBINWS.POOL.SIZE= 5",
		"DEBINWS.URL = https://200.59.131.137/debin-api/external/v3/DebinClient", 
		"KEYSTORE.DEBINWS.IDSEGURIDAD = 20104",
		"KEYSTORE.DEBINWS.PATH = /aplicaciones/hb/conf/keyStore/hbkey.jks",
		"KEYSTORE.FACTURAELECTRONICA.IDSEGURIDAD = 20104", 
		"DEBINWS.ENC_USER = banelco_pub",
		"DEBINWS.TTL_TIMESTAMP = 600", 
		"KEYSTORE.DEBINWS.TYPE = JKS",
"TAGS.A.ELIMINAR.DB="})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class DebinWSAdhesionDAOIT {

	/** The Debin WS Adhesion DAO. */
	@Autowired
	private DebinWSAdhesionDAO debinWSAdhesionDAO;

	/** The id seg. */
	@Value("${KEYSTORE.DEBINWS.IDSEGURIDAD}")
	private String idSeg;
	
	/** The app context. */
	ApplicationContext appContext = new ClassPathXmlApplicationContext();

	
	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;
	
	/**
	 * 
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
	@ComponentScan(basePackageClasses = { 
			DebinGestionarWSImpl.class, 
			DebinWSSolicitudesDAOImpl.class, 
			ContextHolder.class,
			CryptoHelper.class, 
			Environment.class})
	public static class DebinWSAdhesionDAOITConfiguration {

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

		@Bean
		public SystemRoutingDataSource SystemRoutingDataSource() {
			return Mockito.mock(SystemRoutingDataSource.class);
		}

		@Bean
		public TransferenciaDAO transferenciaDAO() {
			return Mockito.mock(TransferenciaDAO.class);
		}


	}

	/**
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 */
	@Test
	public void descargarComprobanteMultiple() throws IOException, IllegalAccessException {
		ComprobanteAdhesionEntity comprobanteAdhesionEntity = new ComprobanteAdhesionEntity();
		comprobanteAdhesionEntity.setTitulo("Comprobante modificación de adhesión de cuentas a DEBIN");
		comprobanteAdhesionEntity.setSubtitulo("Modificación adhesión de cuentas a DEBIN");
		comprobanteAdhesionEntity.setNumeroComprobante("012361235123");
		comprobanteAdhesionEntity.setFecha("12/12/2012");
		List<ElementoComprobanteAdhesionReporte> dataCollectionAlta = new ArrayList<ElementoComprobanteAdhesionReporte>();
		List<ElementoComprobanteAdhesionReporte> dataCollectionBaja = new ArrayList<ElementoComprobanteAdhesionReporte>();
		ElementoComprobanteAdhesionReporte elementoAlta = null;
		ElementoComprobanteAdhesionReporte elementoBaja = null;
	
		for(int i= 0; i<31; i++) {
			elementoAlta = new ElementoComprobanteAdhesionReporte();
			elementoAlta.setDescripcionCuenta("Cuenta Unica");
			elementoAlta.setNumeroCuenta("100-3001236");
			elementoAlta.setTipoCuenta("Cuenta en pesos");
			elementoBaja = new ElementoComprobanteAdhesionReporte();
			elementoBaja.setDescripcionCuenta("Cuenta Unica");
			elementoBaja.setNumeroCuenta("100-3001236");
			elementoBaja.setTipoCuenta("Cuenta en dolares");
			dataCollectionAlta.add(elementoAlta);
			dataCollectionBaja.add(elementoBaja);
		}
		
		for(int i= 0; i<31; i++) {
			elementoAlta = new ElementoComprobanteAdhesionReporte();
			elementoAlta.setDescripcionCuenta("Cuenta Unica");
			elementoAlta.setNumeroCuenta("Cuenta \"EME\" <br> 100-3001236");
			elementoAlta.setTipoCuenta("Cuenta en pesos");
			elementoBaja = new ElementoComprobanteAdhesionReporte();
			elementoBaja.setDescripcionCuenta("Cuenta Unica");
			elementoBaja.setNumeroCuenta("Cuenta \"EME\" <br> 100-3001236");
			elementoBaja.setTipoCuenta("Cuenta en dolares");
			dataCollectionAlta.add(elementoAlta);
			dataCollectionBaja.add(elementoBaja);
		}
		comprobanteAdhesionEntity.setCuentasAdhesion(dataCollectionAlta);
		comprobanteAdhesionEntity.setCuentasBaja(dataCollectionBaja);
		FieldUtils.writeField(debinWSAdhesionDAO, "logoCabecera", 
				appContext.getResource("classpath:/report/debinWS/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(debinWSAdhesionDAO, "logoCierre", 
				appContext.getResource("classpath:/report/debinWS/logo_cierre_comprobante.png"), true);
		FieldUtils.writeField(debinWSAdhesionDAO, "fileJasperComprobanteGestionAdhesionDebin",
				appContext.getResource("classpath:/report/debinWS/ComprobanteGestionAdhesionDebin.jasper"), true);
		Reporte reporte = debinWSAdhesionDAO.generarComprobante(comprobanteAdhesionEntity);
		Assert.assertNotNull(reporte.getBytes());
		//FileUtils.writeByteArrayToFile(new File("C:/tools/fileAdhesionMultiple.pdf"),reporte.getBytes());
	}

	/**
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 */
	@Test
	public void descargarComprobanteAlta() throws IOException, IllegalAccessException {
		ComprobanteAdhesionEntity comprobanteAdhesionEntity = new ComprobanteAdhesionEntity();
		comprobanteAdhesionEntity.setTitulo("Comprobante de adhesión de cuentas a DEBIN");
		comprobanteAdhesionEntity.setSubtitulo("Adhesión de cuentas a DEBIN");
		comprobanteAdhesionEntity.setNumeroComprobante("012361235123");
		comprobanteAdhesionEntity.setFecha("12/12/2012");
		List<ElementoComprobanteAdhesionReporte> dataCollectionAlta = new ArrayList<ElementoComprobanteAdhesionReporte>();
		ElementoComprobanteAdhesionReporte elementoAlta = null;
		for(int i= 0; i<31; i++) {
			elementoAlta = new ElementoComprobanteAdhesionReporte();
			elementoAlta.setDescripcionCuenta("Cuenta Unica");
			elementoAlta.setNumeroCuenta("100-3001236");
			elementoAlta.setTipoCuenta("Cuenta en pesos");
			dataCollectionAlta.add(elementoAlta);
		}
		comprobanteAdhesionEntity.setCuentasAdhesion(dataCollectionAlta);
		FieldUtils.writeField(debinWSAdhesionDAO, "logoCabecera", 
				appContext.getResource("classpath:/report/debinWS/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(debinWSAdhesionDAO, "logoCierre", 
				appContext.getResource("classpath:/report/debinWS/logo_cierre_comprobante.png"), true);
		FieldUtils.writeField(debinWSAdhesionDAO, "fileJasperComprobanteGestionAdhesionDebin",
				appContext.getResource("classpath:/report/debinWS/ComprobanteGestionAdhesionDebin.jasper"), true);
		Reporte reporte = debinWSAdhesionDAO.generarComprobante(comprobanteAdhesionEntity);
		Assert.assertNotNull(reporte.getBytes());
		//FileUtils.writeByteArrayToFile(new File("C:/tools/fileAdhesionAlta.pdf"),reporte.getBytes());
	}


	/**
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 */
	@Test
	public void descargarComprobanteBaja() throws IOException, IllegalAccessException {
		ComprobanteAdhesionEntity comprobanteAdhesionEntity = new ComprobanteAdhesionEntity();
		comprobanteAdhesionEntity.setTitulo("Comprobante de baja de adhesión de cuentas a DEBIN");
		comprobanteAdhesionEntity.setSubtitulo("Baja de adhesión de cuentas a DEBIN");
		comprobanteAdhesionEntity.setNumeroComprobante("012361235123");
		comprobanteAdhesionEntity.setFecha("12/12/2012");
		List<ElementoComprobanteAdhesionReporte> dataCollectionBaja = new ArrayList<ElementoComprobanteAdhesionReporte>();
		ElementoComprobanteAdhesionReporte elementoBaja = null;
		for(int i= 0; i<31; i++) {
			elementoBaja = new ElementoComprobanteAdhesionReporte();
			elementoBaja.setDescripcionCuenta("Cuenta Unica");
			elementoBaja.setNumeroCuenta("100-3001236");
			elementoBaja.setTipoCuenta("Cuenta en dolares");
			dataCollectionBaja.add(elementoBaja);
		}
		comprobanteAdhesionEntity.setCuentasBaja(dataCollectionBaja);
		FieldUtils.writeField(debinWSAdhesionDAO, "logoCabecera", 
				appContext.getResource("classpath:/report/debinWS/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(debinWSAdhesionDAO, "logoCierre", 
				appContext.getResource("classpath:/report/debinWS/logo_cierre_comprobante.png"), true);
		FieldUtils.writeField(debinWSAdhesionDAO, "fileJasperComprobanteGestionAdhesionDebin",
				appContext.getResource("classpath:/report/debinWS/ComprobanteGestionAdhesionDebin.jasper"), true);
		Reporte reporte = debinWSAdhesionDAO.generarComprobante(comprobanteAdhesionEntity);
		Assert.assertNotNull(reporte.getBytes());
		//FileUtils.writeByteArrayToFile(new File("C:/tools/fileAdhesionBaja.pdf"),reporte.getBytes());
	}
	
	/**
	 * 
	 * @throws DAOException
	 * @throws SQLException
	 */
	@Test
	public void consultaVendedorOK() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		RequestConsulta request = new RequestConsulta();
		request.setCanal("E");
		request.setCuit("20215871836");
		request.setIp("127.0.0.1");
		request.setNroDocumento("21587183");
		request.setTipoDocumento("1");

		ResponseVendedor response = debinWSAdhesionDAO.consultaVendedor(request);
		Assert.assertNotNull(response);
	}
	
	/**
	 * 
	 * @throws DAOException
	 * @throws SQLException
	 */
	@Test(expected = DAOException.class)
	public void consultaVendedorErrorRuntime() throws DAOException, SQLException {
		debinWSAdhesionDAO.consultaVendedor(new RequestConsulta());
	}
	
	/**
	 * 
	 * @throws DAOException
	 * @throws SQLException
	 */
	@Test(expected = DAOException.class)
	public void consultaVendedorErrorDebin() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
		debinWSAdhesionDAO.consultaVendedor(new RequestConsulta());
	}
	
	/**
	 * 
	 * @throws DAOException
	 * @throws SQLException
	 */
	@Test(expected = DAOException.class)
	public void consultaVendedorErrorWebservice() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
		Mockito.when(debinWSAdhesionDAO.consultaVendedor(Matchers.any(RequestConsulta.class))).thenThrow(new WebServiceException());
	}
	
	/**
	 * 
	 * @throws DAOException
	 * @throws SQLException
	 */
	@Test
	public void adhesionVendedorOK() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		RequestAdhesion request = new RequestAdhesion();
		request.setCanal("E");
		request.setCuit("20215871836");
		request.setIp("127.0.0.1");
		request.setNroDocumento("21587183");
		request.setTipoDocumento("1");
		request.setCuenta(new CuentaDebinDTO());
		request.getCuenta().setCbu("0720033520000000819954");
		
		Response response = debinWSAdhesionDAO.adhesionVendedor(request);
		Assert.assertNotNull(response);
	}
	
	/**
	 * 
	 * @throws DAOException
	 * @throws SQLException
	 */
	@Test(expected = DAOException.class)
	public void adhesionVendedorErrorRuntime() throws DAOException, SQLException {
		debinWSAdhesionDAO.adhesionVendedor(new RequestAdhesion());
	}
	
	/**
	 * 
	 * @throws DAOException
	 * @throws SQLException
	 */
	@Test(expected = DAOException.class)
	public void adhesionVendedorErrorDebin() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
		debinWSAdhesionDAO.adhesionVendedor(new RequestAdhesion());
	}
	
	/**
	 * 
	 * @throws DAOException
	 * @throws SQLException
	 */
	@Test(expected = DAOException.class)
	public void adhesionVendedorErrorWebservice() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		Mockito.when(debinWSAdhesionDAO.adhesionVendedor(Matchers.any(RequestAdhesion.class))).thenThrow(new WebServiceException());
	}
	
	/**
	 * 
	 * @throws DAOException
	 * @throws SQLException
	 */
	@Test
	public void bajaCuentaVendedorOK() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		RequestAdhesion request = new RequestAdhesion();
		request.setCanal("E");
		request.setCuit("20215871836");
		request.setIp("127.0.0.1");
		request.setNroDocumento("21587183");
		request.setTipoDocumento("1");
		request.setCuenta(new CuentaDebinDTO());
		request.getCuenta().setCbu("0720033520000000819954");
		
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
		Response response = debinWSAdhesionDAO.bajaCuentaVendedor(request);
		Assert.assertNotNull(response);
	}
	
	/**
	 * 
	 * @throws DAOException
	 * @throws SQLException
	 */
	@Test(expected = DAOException.class)
	public void bajaCuentaVendedorErrorRuntime() throws DAOException, SQLException {
		debinWSAdhesionDAO.bajaCuentaVendedor(new RequestAdhesion());
	}
	
	/**
	 * 
	 * @throws DAOException
	 * @throws SQLException
	 */
	@Test(expected = DAOException.class)
	public void bajaCuentaVendedorErrorDebin() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
		debinWSAdhesionDAO.bajaCuentaVendedor(new RequestAdhesion());
	}
	
	/**
	 * 
	 * @throws DAOException
	 * @throws SQLException
	 */
	@Test(expected = DAOException.class)
	public void bajaCuentaVendedorErrorWebservice() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		Mockito.when(debinWSAdhesionDAO.bajaCuentaVendedor(Matchers.any(RequestAdhesion.class))).thenThrow(new WebServiceException());
	}
}

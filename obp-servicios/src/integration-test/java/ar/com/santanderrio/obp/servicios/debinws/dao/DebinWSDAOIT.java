package ar.com.santanderrio.obp.servicios.debinws.dao;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.datatype.DatatypeConfigurationException;
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
import org.springframework.beans.factory.annotation.Qualifier;
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
import ar.com.santanderrio.obp.generated.webservices.debin.CompradorDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestAdhesion;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConfirmacionDebito;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConfirmacionDebitoV3;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestListaDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.Response;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseListaDebin;
import ar.com.santanderrio.obp.servicios.debinws.dao.impl.DebinGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.debinws.dao.impl.DebinWSDAOImpl;
import ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteInDTO;
import ar.com.santanderrio.obp.servicios.debinws.mock.DebinWSMock;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelper;
import ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO;

/**
 * The Class DebinWSDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSDAOIT.DebinWSDAOITConfiguration.class,
		SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { 
		"APP.ENCODING = UTF-8", 
		"DEBINWS.JKS = DEBINWS", 
		"DEBINWS.TIMEOUT = 20000",
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
public class DebinWSDAOIT {

	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	/** The debin WSDAO. */
	@Autowired
	@Qualifier("debinWSDAOImpl")
	private DebinWSDAO debinWSDAO;

	/** The id seg. */
	@Value("${KEYSTORE.DEBINWS.IDSEGURIDAD}")
	private String idSeg;
	
	/** The app context. */
	ApplicationContext appContext = new ClassPathXmlApplicationContext();

	/** The comprobante in DTO. */
	private ComprobanteInDTO comprobanteInDTO;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		comprobanteInDTO = new ComprobanteInDTO();
		comprobanteInDTO.setAlias("asda");
		comprobanteInDTO.setAliasCuenta("Alias Cuenta");
		comprobanteInDTO.setCbu("0720000788000006188786");
		comprobanteInDTO.setComprobante("181227183122");
		comprobanteInDTO.setConcepto("Expensas");
		comprobanteInDTO.setCuit("27-35426856-1");
		comprobanteInDTO.setDescripcion("Clave");
		comprobanteInDTO.setFecha("26/12/2018");
		comprobanteInDTO.setFechaSolicitud("26/12/2018");
		comprobanteInDTO.setFechaVencimiento("28/12/2018");
		comprobanteInDTO.setIdDebin("4XJ8G7V95JG11YM9EMPYR0");
		comprobanteInDTO.setImporte("$100.10");
		comprobanteInDTO.setNumeroCuenta("100-123456/7");
		comprobanteInDTO.setSolicitante("ALESSO SALVAY CAIFAS JONAS");
		comprobanteInDTO.setTipoCuenta("Cuenta Unica");
	}

	/**
	 * The Class DebinWSDAOITConfiguration.
	 */
	@Configuration
	@ComponentScan(basePackageClasses = { DebinGestionarWSImpl.class, DebinWSDAOImpl.class, ContextHolder.class,
			CryptoHelper.class, Environment.class })
	public static class DebinWSDAOITConfiguration {

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
		
		/**
		 * System routing data source.
		 *
		 * @return the system routing data source
		 */
		@Bean
		public SystemRoutingDataSource SystemRoutingDataSource() {
			return Mockito.mock(SystemRoutingDataSource.class);
		}
		
		/**
		 * Transferencia DAO.
		 *
		 * @return the transferencia DAO
		 */
		@Bean
		public TransferenciaDAO transferenciaDAO() {
			return Mockito.mock(TransferenciaDAO.class);
		}
	}

	/**
	 * Lista debin new OK test.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 * @throws DatatypeConfigurationException the datatype configuration exception
	 */
	@Test
	public void listaDebinNewOKTest() throws DAOException, SQLException, DatatypeConfigurationException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		RequestListaDebin request = DebinWSMock.obtenerRequestListaDebinNew();
		ResponseListaDebin respuesta = debinWSDAO.listaDebinNew(request);
		Assert.assertNotNull(respuesta);
	}

	/**
	 * Lista debin new webservice.
	 *
	 * @throws SQLException the SQL exception
	 * @throws DAOException the DAO exception
	 */
	@Test(expected = DAOException.class)
	public void listaDebinNewWebservice() throws SQLException, DAOException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(debinWSDAO.listaDebinNew(Matchers.any(RequestListaDebin.class))).thenThrow(new WebServiceException());
		
		debinWSDAO.listaDebinNew(new RequestListaDebin());
	}

	/**
	 * Lista debin new debin api exception.
	 *
	 * @throws SQLException the SQL exception
	 * @throws DAOException the DAO exception
	 */
	@Test(expected = DAOException.class)
	public void listaDebinNewDebinApiException() throws SQLException, DAOException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		debinWSDAO.listaDebinNew(new RequestListaDebin());
	}

	/**
	 * Lista debin new runtime exception.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test(expected = DAOException.class)
	public void listaDebinNewRuntimeException() throws DAOException{
		debinWSDAO.listaDebinNew(new RequestListaDebin());
	}

	/**
	 * Consultar debin new test.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void consultarDebinNewTest() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		RequestDebin request = DebinWSMock.obtenerRequestDebinNew();
		ResponseDebin respuesta = debinWSDAO.consultaDebinNew(request);
		Assert.assertNotNull(respuesta);
	}

	/**
	 * Consultar debin new error webservice.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test(expected = DAOException.class)
	public void consultarDebinNewErrorWebservice() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(debinWSDAO.consultaDebinNew(Matchers.any(RequestDebin.class))).thenThrow(new WebServiceException());
		debinWSDAO.consultaDebinNew(new RequestDebin());
	}

	/**
	 * Consultar debin error debin api exception.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test(expected = DAOException.class)
	public void consultarDebinErrorDebinApiException() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
		debinWSDAO.consultaDebinNew(new RequestDebin());
	}

	/**
	 * Consultar debin error runtime exception.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test(expected = DAOException.class)
	public void consultarDebinErrorRuntimeException() throws DAOException {
		debinWSDAO.consultaDebinNew(new RequestDebin());
	}

	/**
	 * Descargar comprobante rechazo.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void descargarComprobanteRechazo() throws IOException, IllegalAccessException {	
		comprobanteInDTO.setTipoComprobante("RechazoDebin");
		FieldUtils.writeField(debinWSDAO, "logoCabecera", 
				appContext.getResource("classpath:/report/debinWS/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(debinWSDAO, "logoCierre", 
				appContext.getResource("classpath:/report/debinWS/logo_cierre_comprobante.png"), true);
		FieldUtils.writeField(debinWSDAO, "fileJasperComprobanteRechazoDebin",
				appContext.getResource("classpath:/report/debinWS/ComprobanteRechazoDebin.jasper"), true);
		Reporte reporte = debinWSDAO.descargarComprobante(comprobanteInDTO);
		Assert.assertNotNull(reporte.getBytes());
		//FileUtils.writeByteArrayToFile(new File("C:/tools/fileComprobanteRechazoDebin.pdf"),reporte.getBytes());
	}

	/**
	 * Descargar comprobante eliminacion.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void descargarComprobanteEliminacion() throws IOException, IllegalAccessException {	
		comprobanteInDTO.setTipoComprobante("EliminacionDebin");
		FieldUtils.writeField(debinWSDAO, "logoCabecera", 
				appContext.getResource("classpath:/report/debinWS/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(debinWSDAO, "logoCierre", 
				appContext.getResource("classpath:/report/debinWS/logo_cierre_comprobante.png"), true);
		FieldUtils.writeField(debinWSDAO, "fileJasperComprobanteEliminacionDebin",
				appContext.getResource("classpath:/report/debinWS/ComprobanteEliminacionDebin.jasper"), true);
		Reporte reporte = debinWSDAO.descargarComprobante(comprobanteInDTO);
		Assert.assertNotNull(reporte.getBytes());
		//FileUtils.writeByteArrayToFile(new File("C:/tools/fileComprobanteEliminacionDebin.pdf"),reporte.getBytes());
	}

	/**
	 * Descargar comprobante pagar.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void descargarComprobantePagar() throws IOException, IllegalAccessException {	
		comprobanteInDTO.setTipoComprobante("ConfirmacionDebito");		
		FieldUtils.writeField(debinWSDAO, "logoCabecera", 
				appContext.getResource("classpath:/report/debinWS/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(debinWSDAO, "logoCierre", 
				appContext.getResource("classpath:/report/debinWS/logo_cierre_comprobante.png"), true);
		FieldUtils.writeField(debinWSDAO, "fileJasperComprobanteConfirmacionDebito",
				appContext.getResource("classpath:/report/debinWS/ComprobanteConfirmacionDebito.jasper"), true);
		Reporte reporte = debinWSDAO.descargarComprobante(comprobanteInDTO);
		Assert.assertNotNull(reporte.getBytes());
		//FileUtils.writeByteArrayToFile(new File("C:/tools/fileComprobanteConfirmacionDebito1.pdf"),reporte.getBytes());
	}

	/**
	 * Confirmacion debito OK.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void confirmacionDebitoOK() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
				
		RequestConfirmacionDebito request = new RequestConfirmacionDebito();
		request.setCanal("E");
        request.setCuit("20215871836");
        request.setIp("180.166.121.183");
        request.setNroDocumento("21587183");
        request.setTipoDocumento("1");
        request.setIdDebin("5L18MKX9R16JPP49O6WYV4");
        
        CompradorDebinDTO comprador = new CompradorDebinDTO();
        comprador.setCuit("20215871836");
        comprador.setCuenta(new CuentaDebinDTO());
        comprador.getCuenta().setCbu("0720033520000000819954");
        request.setComprador(comprador);

		Response response = debinWSDAO.confirmacionDebito(request);
		Assert.assertNotNull(response);
	}

	/**
	 * Confirmacion debito error webservice.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test(expected = DAOException.class)
	public void confirmacionDebitoErrorWebservice() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(debinWSDAO.confirmacionDebito(Matchers.any(RequestConfirmacionDebito.class))).thenThrow(new WebServiceException());
	}

	/**
	 * Confirmacion debito error debin api exception.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test(expected = DAOException.class)
	public void confirmacionDebitoErrorDebinApiException() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
				
		debinWSDAO.confirmacionDebito(new RequestConfirmacionDebito());
	}

	/**
	 * Confirmacion debito error runtime exception.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test(expected = DAOException.class)
	public void confirmacionDebitoErrorRuntimeException() throws DAOException {
		debinWSDAO.confirmacionDebito(new RequestConfirmacionDebito());
	}
	
	/**
	 * Confirmacion debito V3 OK.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void confirmacionDebitoV3OK() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
				
		RequestConfirmacionDebitoV3 request = new RequestConfirmacionDebitoV3();
		request.setCanal("E");
        request.setCuit("20215871836");
        request.setIp("180.166.121.183");
        request.setNroDocumento("21587183");
        request.setTipoDocumento("1");
        request.setIdDebin("5L18MKX9R16JPP49O6WYV4");
        
        CompradorDebinDTO comprador = new CompradorDebinDTO();
        comprador.setCuit("20215871836");
        comprador.setCuenta(new CuentaDebinDTO());
        comprador.getCuenta().setCbu("0720033520000000819954");
        request.setComprador(comprador);

		Response response = debinWSDAO.confirmacionDebitoV3(request);
		Assert.assertNotNull(response);
	}

	/**
	 * Confirmacion debito V3 error webservice.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test(expected = DAOException.class)
	public void confirmacionDebitoV3ErrorWebservice() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(debinWSDAO.confirmacionDebitoV3(Matchers.any(RequestConfirmacionDebitoV3.class))).thenThrow(new WebServiceException());
	}

	/**
	 * Confirmacion debito V3 error debin api exception.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test(expected = DAOException.class)
	public void confirmacionDebitoV3ErrorDebinApiException() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
				
		debinWSDAO.confirmacionDebitoV3(new RequestConfirmacionDebitoV3());
	}

	/**
	 * Confirmacion debito V3 error runtime exception.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test(expected = DAOException.class)
	public void confirmacionDebitoV3ErrorRuntimeException() throws DAOException {
		debinWSDAO.confirmacionDebitoV3(new RequestConfirmacionDebitoV3());
	}

	/**
	 * Eliminar debin OK.
	 *
	 * @throws SQLException the SQL exception
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void eliminarDebinOK() throws SQLException, DAOException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		RequestDebin request = DebinWSMock.obtenerRequestDebinNew();
		request.setIdDebin("O7L8GYKNXRYZPEWNMP");
		Response response = debinWSDAO.eliminarDebin(request);
		Assert.assertNotNull(response);
	}

	/**
	 * Eliminar debin error webservice.
	 *
	 * @throws SQLException the SQL exception
	 * @throws DAOException the DAO exception
	 */
	@Test(expected = DAOException.class)
	public void eliminarDebinErrorWebservice() throws SQLException, DAOException {	
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(debinWSDAO.eliminarDebin(Matchers.any(RequestDebin.class))).thenThrow(new WebServiceException());
	}

//	/**
//	 * 
//	 * @throws DAOException
//	 * @throws SQLException
//	 */
//	@Test(expected = DAOException.class)
//	public void eliminarDebinErrorStackOverflow() throws SQLException, DAOException {	
//		Credential cred = new Credential();
//		cred.setUsuario("bcorio");
//		cred.setPassword("hbpassword");
//		
//		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
//		Mockito.when(debinWSDAO.eliminarDebin(Matchers.any(RequestDebin.class))).thenThrow(new StackOverflowError());
//		
//		debinWSDAO.eliminarDebin(new RequestDebin());
//	}

	/**
	 * Eliminar debin error debin api exception.
	 *
	 * @throws SQLException the SQL exception
	 * @throws DAOException the DAO exception
	 */
	@Test(expected = DAOException.class)
	public void eliminarDebinErrorDebinApiException() throws SQLException, DAOException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
		debinWSDAO.eliminarDebin(new RequestDebin());
	}

	/**
	 * Eliminar debin error runtime.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test(expected = DAOException.class)
	public void eliminarDebinErrorRuntime() throws DAOException {
		debinWSDAO.eliminarDebin(new RequestDebin());
	}

	/**
	 * Adhesion OK.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void adhesionOK() throws DAOException, SQLException {
		RequestAdhesion request = new RequestAdhesion();
        request.setCanal("E");
        request.setCuit("20215871836");
        request.setIp("127.0.0.1");
        request.setNroDocumento("21587183");
        request.setTipoDocumento("1");
        request.setCuenta(new CuentaDebinDTO());
        request.getCuenta().setCbu("0720033520000000819954");

		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
		Response response = debinWSDAO.adhesionComprador(request);
		
		Assert.assertNotNull(response);
	}

	/**
	 * Adhesion error webservice.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test(expected = DAOException.class)
	public void adhesionErrorWebservice() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		Mockito.when(debinWSDAO.adhesionComprador(Matchers.any(RequestAdhesion.class))).thenThrow(new WebServiceException());
	}

	/**
	 * Adhesion error debin api exception.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test(expected = DAOException.class)
	public void adhesionErrorDebinApiException() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		debinWSDAO.adhesionComprador(new RequestAdhesion());
	}

	/**
	 * Adhesion error runtime exception.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test(expected = DAOException.class)
	public void adhesionErrorRuntimeException() throws DAOException {
		debinWSDAO.adhesionComprador(new RequestAdhesion());
	}

}

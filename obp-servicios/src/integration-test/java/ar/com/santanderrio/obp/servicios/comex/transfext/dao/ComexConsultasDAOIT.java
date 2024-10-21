package ar.com.santanderrio.obp.servicios.comex.transfext.dao;

import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.ws.WebServiceException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaBancosResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaMonedasResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaPaisesResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexConsultasDAOIT.ComexConsultasDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.impl.ComexConsultasDAOImpl;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.impl.ComexConsultasGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ComprobanteComexInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaBancosInEntity;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;



/**
 * The Class ComexConsultasDAOIT.
 * 
 * @author ITResources
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ComexConsultasDAOITConfiguration.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { 
		"TRANSFEXT.CONSULTAS.POOL.ACTIVO = true",
//				"TRANSFEXT.CONSULTAS.URL = http://webinetdesa01.rio.ar.bsch:19081/ComexConsultas.svc",
				"TRANSFEXT.CONSULTAS.URL = http://webinetdesa01.rio.ar.bsch:9081/ComexConsultas.svc",
				"TRANSFEXT.CONSULTAS.POOL.SIZE = 3",
				"KEYSTORE.TRANSFEXT.CONSULTAS.PATH = /aplicaciones/hb/conf/keyStorehbkey.jks",
				"KEYSTORE.TRANSFEXT.CONSULTAS.TYPE = JKS",
				"KEYSTORE.TRANSFEXT.CONSULTAS.IDSEGURIDAD = 20010",
				"TRANSFEXT.CONSULTAS.JKS=TRANSFEXT.CONSULTAS",
				"TRANSFEXT.CONSULTAS.TIMEOUT=10000",
				"TRANSFEXT.CONSULTAS.POOL.MAXWAIT=5000",
				"TRANSFEXT.CANALES.CANT.MAX.OPERACIONES=4000",
				"COMEX.VALIDADOR.VIRUS.DOCUMENTOS.PATH=/aplicaciones/hb/pruebaCopiaArchivos/",
				"COMEX.MAX.FILESIZE=10485760",
				"COMEX.MAX.FILES.ATACHED=4",
				"COMEX.TIPO.ARCHIVOS.ADJ=.txt,.jpg,.tiff,.pdf,.xls,.xlsx,.doc,.docx,.bmp,.ppt,.zip,.rar,.jpeg,.gif",
				"COMEX.CONCEPTOS.DOCUMENTACION.ADJUNTA=S06|B05|B06|B07|I08",
				"COMEX.TIEMPO.ESPERA.VERIFICACION.ARCHIVO.ANTIVIRUS=2000",
		"APP.ENCODING = UTF-8" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ComexConsultasDAOIT {
	
	/** The Constant BCORIO. */
	private static final String BCORIO = "bcorio";

	/** The Constant HBPASS. */
	private static final String HBPASS = "hbpassword";
	
	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The sesion cliente. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	/** The Comex Consultas DAO. */
	@Autowired
	private ComexConsultasDAO comexConsultasDAO;
	
	/** The id seg. */
	@Value("${KEYSTORE.TRANSFEXT.CONSULTAS.IDSEGURIDAD}")
	private String idSeg;
	
    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();
	/**
	 * Inits the.
	 */
	@Before
	public void init() throws DAOException {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * The Class GestionWSTestConfiguration.
	 */
	@Configuration
	@ComponentScan(basePackageClasses = { 
			ComexConsultasDAOImpl.class, 
			ComexConsultasGestionarWSImpl.class, 
			Sign.class, KeyStoreHelperImpl.class,
			ContextHolder.class,
			}, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
					KeyStoreFactory.class }))
	public static class ComexConsultasDAOITConfiguration {

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
				 * @see
				 * org.mockito.stubbing.Answer#answer(org.mockito.invocation.
				 * InvocationOnMock)
				 */
				@Override
				public KeyStore answer(InvocationOnMock invocation) throws Throwable {
					KeyStore keyStore = new KeyStore();
					keyStore.setKeystoreType("JKS");
					keyStore.setKeystorePath(Object.class.getResource("/config/test/keyStore/hbkeyBilleteraDESA8.jks").getPath());
					keyStore.setKeystoreAlias("obp");
					keyStore.setKeystorePassword(HBPASS);
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
         * Sesion cliente.
         *
         * @return the sesion cliente
         */
        @Bean
        public SesionCliente SesionCliente() {
            return Mockito.mock(SesionCliente.class);
        }
		
        @Bean
        public SesionParametros SesionParametros() {
            return Mockito.mock(SesionParametros.class);
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
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws DAOException 
	 */
    @Test
    public void generarComprobante() throws IOException, IllegalAccessException, DAOException {
    	ComprobanteComexInEntity comprobanteTransferenciaComexInEntity = 
    			new ComprobanteComexInEntity();
    	comprobanteTransferenciaComexInEntity.setCodigoBancario("CITIUS33XXM");
    	comprobanteTransferenciaComexInEntity.setCuenta("Cuenta 023-123456/7");
    	comprobanteTransferenciaComexInEntity.setCuentaDestino("72834196582");
    	comprobanteTransferenciaComexInEntity.setDescripcionCuenta("Caja de ahorro en u$s");
    	comprobanteTransferenciaComexInEntity.setDocumentacion("");
    	comprobanteTransferenciaComexInEntity.setDomicilio("Park Avenue 42");
    	comprobanteTransferenciaComexInEntity.setFecha("12/08/2016 - 12:32");
    	comprobanteTransferenciaComexInEntity.setGastosACargo("Ordenante");
    	comprobanteTransferenciaComexInEntity.setImporte("u$s 100,00");
    	comprobanteTransferenciaComexInEntity.setMotivo("Transferenca personales");
    	comprobanteTransferenciaComexInEntity.setNombre("Magalí Romero");
    	comprobanteTransferenciaComexInEntity.setNombreEmisor("Agustina Fernandez");
    	comprobanteTransferenciaComexInEntity.setNumeroComprobante("123456789");
    	comprobanteTransferenciaComexInEntity.setPais("CA, Estados Unidos");
    	comprobanteTransferenciaComexInEntity.setCodigoBancarioIntermediario("asdaw123123");
    	comprobanteTransferenciaComexInEntity.setCuentaBancoIntermediario("123245");
    	
    	comprobanteTransferenciaComexInEntity.setLegales("Prueba");
        FieldUtils.writeField(comexConsultasDAO, "logoCabecera",
        		appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
        FieldUtils.writeField(comexConsultasDAO, "logoCierre", 
        		appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
        FieldUtils.writeField(comexConsultasDAO, "fileJasperComex",
                appContext.getResource("classpath:/report/comex/transferenciasComex.jasper"), true);
        Reporte reporte = comexConsultasDAO.generarComprobante(comprobanteTransferenciaComexInEntity);
//        FileUtils.writeByteArrayToFile(new File("C:/tools/file15.pdf"),reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
    }
    
	/**
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws DAOException 
	 */
    @Test (expected = ISBANRuntimeException.class)
    public void generarComprobanteErrorIO() throws IOException, IllegalAccessException, DAOException {
    	ComprobanteComexInEntity comprobanteTransferenciaComexInEntity = 
    			new ComprobanteComexInEntity();
    	comprobanteTransferenciaComexInEntity.setCodigoBancario("CITIUS33XXM");
    	comprobanteTransferenciaComexInEntity.setCuenta("Cuenta 023-123456/7");
    	comprobanteTransferenciaComexInEntity.setCuentaDestino("72834196582");
    	comprobanteTransferenciaComexInEntity.setDescripcionCuenta("Caja de ahorro en u$s");
    	comprobanteTransferenciaComexInEntity.setDocumentacion("Documento 1-Documento 2-Documento 3-Documento 4");
    	comprobanteTransferenciaComexInEntity.setDomicilio("Park Avenue 42");
    	comprobanteTransferenciaComexInEntity.setFecha("12/08/2016 - 12:32");
    	comprobanteTransferenciaComexInEntity.setGastosACargo("Ordenante");
    	comprobanteTransferenciaComexInEntity.setImporte("u$s 100,00");
    	comprobanteTransferenciaComexInEntity.setMotivo("Transferenca personales");
    	comprobanteTransferenciaComexInEntity.setNombre("Magalí Romero");
    	comprobanteTransferenciaComexInEntity.setNombreEmisor("Agustina Fernandez");
    	comprobanteTransferenciaComexInEntity.setNumeroComprobante("123456789");
    	comprobanteTransferenciaComexInEntity.setPais("CA, Estados Unidos");
    	comprobanteTransferenciaComexInEntity.setCodigoBancarioIntermediario("asdaw123123");
    	comprobanteTransferenciaComexInEntity.setCuentaBancoIntermediario("123245");
    	comprobanteTransferenciaComexInEntity.setVinculo("Hermano");
    	comprobanteTransferenciaComexInEntity.setLegales("Prueba");
        FieldUtils.writeField(comexConsultasDAO, "logoCabecera",
        		appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
        FieldUtils.writeField(comexConsultasDAO, "logoCierre", 
        		appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobate.png"), true);
        FieldUtils.writeField(comexConsultasDAO, "fileJasperComex",
                appContext.getResource("classpath:/report/comex/transferenciasComex.jasper"), true);
        
        Reporte reporte = comexConsultasDAO.generarComprobante(comprobanteTransferenciaComexInEntity);
    }

	@Test
	public void consultaPaisesOk() throws DAOException, SQLException{
		
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");
		
		
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		ConsultaPaisesResponse  respuesta = comexConsultasDAO.consultaPaises();
		
		Assert.assertNotNull(respuesta);
	}
	
	@Test (expected = DAOException.class)
	public void consultaPaisesErrorEncoding() throws DAOException, SQLException, IllegalAccessException{
		
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");
		
		FieldUtils.writeDeclaredField(comexConsultasDAO, "appEncoding", "PEPE", true);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		comexConsultasDAO.consultaPaises();
	}
	@Test
	public void consultaMonedasOk() throws DAOException, SQLException{
		
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");
		
		
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		ConsultaMonedasResponse respuesta = comexConsultasDAO.consultaMonedas();
		
		Assert.assertNotNull(respuesta);
	}
	
	@Test (expected = DAOException.class)
	public void consultaMonedasErrorEnconding() throws DAOException, SQLException, IllegalAccessException{
		
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");
		
		FieldUtils.writeDeclaredField(comexConsultasDAO, "appEncoding", "PEPE", true);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		comexConsultasDAO.consultaMonedas();
	}
	
	@Test (expected = DAOException.class)
	public void consultaMonedasErrorWeBService() throws DAOException, SQLException, IllegalAccessException{
		
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");
	
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
	
		when(comexConsultasDAO.consultaMonedas()).thenThrow(WebServiceException.class);
		comexConsultasDAO.consultaMonedas();
	}
	

	@Test
	public void consultaBancosOk() throws DAOException, SQLException{
		
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");
		ConsultaBancosInEntity consultaBancosInEntity = new ConsultaBancosInEntity();
		consultaBancosInEntity.setCodigoBancario("CITIUS");
		consultaBancosInEntity.setTipoCodigo("SWIFT");
		
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		ConsultaBancosResponse respuesta = comexConsultasDAO.consultaBancos(consultaBancosInEntity);
		
		Assert.assertNotNull(respuesta);
	}
	
	@Test (expected = DAOException.class)
	public void consultaBancosErrorEnconding() throws DAOException, SQLException, IllegalAccessException{
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");
		ConsultaBancosInEntity consultaBancosInEntity = new ConsultaBancosInEntity();
		consultaBancosInEntity.setCodigoBancario("CITIUS");
		consultaBancosInEntity.setTipoCodigo("SWIFT");
		FieldUtils.writeDeclaredField(comexConsultasDAO, "appEncoding", "PEPE", true);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		comexConsultasDAO.consultaBancos(consultaBancosInEntity);
	}
}

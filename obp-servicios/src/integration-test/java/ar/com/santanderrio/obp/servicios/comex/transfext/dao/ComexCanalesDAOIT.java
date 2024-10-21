package ar.com.santanderrio.obp.servicios.comex.transfext.dao;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.SQLException;

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
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConceptosPorTipoResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfOBPResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaOperacionesResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexCanalesDAOIT.ComexCanalesDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.impl.ComexCanalesDAOImpl;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.impl.ComexCanalesGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaConceptosPorTipoInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaDetalleTrfOBPInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaOperacionesInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ProcesarTransferenciaComexInEntity;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;

/**
 * The Class ComexCanalesDAOIT.
 * 
 * @author ITResources
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ComexCanalesDAOITConfiguration.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { 
		"TRANSFEXT.CANALES.POOL.ACTIVO = true",
//				"TRANSFEXT.CANALES.URL = http://webinetdesa01.rio.ar.bsch:19081/ComexCanalesTrf.svc",
				"TRANSFEXT.CANALES.URL = http://webinetdesa01.rio.ar.bsch:9081/ComexCanalesTrf.svc",
				"TRANSFEXT.CANALES.POOL.SIZE = 3",
				"KEYSTORE.TRANSFEXT.CANALES.PATH = aplicaciones/hb/conf/keyStorehbkey.jks",
				"KEYSTORE.TRANSFEXT.CANALES.TYPE = JKS",
				"KEYSTORE.TRANSFEXT.CANALES.IDSEGURIDAD = 20010",
				"TRANSFEXT.CANALES.JKS=TRANSFEXT.CANALES",
				"TRANSFEXT.CANALES.TIMEOUT=10000",
				"TRANSFEXT.CANALES.POOL.MAXWAIT=5000",
				"TRANSFEXT.CANALES.CANT.MAX.OPERACIONES=4000",
				"COMEX.VALIDADOR.VIRUS.DOCUMENTOS.PATH=aplicaciones/hb/pruebaCopiaArchivos/",
				"COMEX.MAX.FILESIZE=10485760",
				"COMEX.MAX.FILES.ATACHED=4",
				"COMEX.TIPO.ARCHIVOS.ADJ=.txt,.jpg,.tiff,.pdf,.xls,.xlsx,.doc,.docx,.bmp,.ppt,.zip,.rar,.jpeg,.gif",
				"COMEX.CONCEPTOS.DOCUMENTACION.ADJUNTA=S06|B05|B06|B07|I08",
				"COMEX.TIEMPO.ESPERA.VERIFICACION.ARCHIVO.ANTIVIRUS=2000",
		"APP.ENCODING = UTF-8" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ComexCanalesDAOIT {
	/** The Constant BCORIO. */
	private static final String BCORIO = "bcorio";

	/** The Constant HBPASS. */
	private static final String HBPASS = "hbpassword";
	
	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	/** The Comex Consultas DAO. */
	@Autowired
	private ComexCanalesDAO comexCanalesDAO;
	
	/** The id seg. */
	@Value("${KEYSTORE.TRANSFEXT.CANALES.IDSEGURIDAD}")
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
			ComexCanalesDAOImpl.class, 
			ComexCanalesGestionarWSImpl.class, 
			Sign.class, KeyStoreHelperImpl.class,
			ContextHolder.class,
			}, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
					KeyStoreFactory.class }))
	public static class ComexCanalesDAOITConfiguration {

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
					keyStore.setKeystorePath(Object.class.getResource("/config/test/keyStore/hbkey.jks").getPath());
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
	public void consultaDetalleTrfOk() throws DAOException, SQLException{
		ConsultaDetalleTrfOBPInEntity detalleInEntity = new ConsultaDetalleTrfOBPInEntity();
		detalleInEntity.setNroTransferencia(402990);
		
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		ConsultaDetalleTrfOBPResponse respuesta = comexCanalesDAO.consultaDetalleTrf(detalleInEntity);

		Assert.assertNotNull(respuesta);
	}
	
	@Test (expected = DAOException.class)
	public void consultaDetalleTrfErrorEncoding() throws DAOException, SQLException, IllegalAccessException{
		ConsultaDetalleTrfOBPInEntity detalleInEntity = new ConsultaDetalleTrfOBPInEntity();
		detalleInEntity.setNroTransferencia(402990);
		
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");
		
		FieldUtils.writeDeclaredField(comexCanalesDAO, "appEncoding", "PEPE", true);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		comexCanalesDAO.consultaDetalleTrf(detalleInEntity);
	}
	
	@Test
	public void consultaConceptosPorTipoOk() throws DAOException, SQLException{
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");

		ConsultaConceptosPorTipoInEntity consultaConceptosPorTipoInEntity = new ConsultaConceptosPorTipoInEntity();
		consultaConceptosPorTipoInEntity.setProducto("1");
		consultaConceptosPorTipoInEntity.setTipoConcepto(null);
		
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		ConceptosPorTipoResponse respuesta = comexCanalesDAO.consultaConceptosPorTipo(consultaConceptosPorTipoInEntity);
		
		Assert.assertNotNull(respuesta);
	}
	
	@Test (expected = DAOException.class)
	public void consultaConceptoPorTipoErrorEncoding() throws DAOException, SQLException, IllegalAccessException{
		
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");

		ConsultaConceptosPorTipoInEntity consultaConceptosPorTipoInEntity = new ConsultaConceptosPorTipoInEntity();
		consultaConceptosPorTipoInEntity.setProducto("1");
		consultaConceptosPorTipoInEntity.setTipoConcepto("");

		FieldUtils.writeDeclaredField(comexCanalesDAO, "appEncoding", "PEPE", true);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		comexCanalesDAO.consultaConceptosPorTipo(consultaConceptosPorTipoInEntity);
	}
	
	@Test
	public void  consultaOperacionesOk() throws DAOException, SQLException {

		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");
		
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		ConsultaOperacionesResponse respuesta = comexCanalesDAO.consultaOperaciones(new ConsultaOperacionesInEntity());
		Assert.assertNotNull(respuesta);
	}
	
	@Test (expected = DAOException.class)
	public void  consultaOperacionesErrorEncoding() throws DAOException, SQLException, IllegalAccessException {

		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");
		
		FieldUtils.writeDeclaredField(comexCanalesDAO, "appEncoding", "PEPE", true);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		comexCanalesDAO.consultaOperaciones(new ConsultaOperacionesInEntity());
	}
	
	@Test
	public void procesarTransferenciaOBPOk() throws SQLException, DAOException{
		ProcesarTransferenciaComexInEntity procesarInEntity = new ProcesarTransferenciaComexInEntity();
		
		
		procesarInEntity.setAceptaDDJJ("1");
		procesarInEntity.setBancoBeneficiario("CITIUS33GRP");
//		procesarInEntity.setBancoIntermediario(bancoIntermediario);
		procesarInEntity.setBeneficiarioDomicilio("calle 000, localidad, Argentina");
//		procesarInEntity.setBeneficiarioPais(beneficiarioPais);
		procesarInEntity.setConcepto("1387");
		procesarInEntity.setCtaAltair("000-02-3580475");
//		procesarInEntity.setCuentaBcoIntermediario(cuentaBcoIntermediario);
		procesarInEntity.setCuentaBeneficiario("453535334");
		procesarInEntity.setCuentaDebito("CLIE00013580475");
//		procesarInEntity.setEstadoTransferencia(estadoTransferencia);
		procesarInEntity.setGastoACargo("1");
		procesarInEntity.setImporteTransferencia(new BigDecimal(10000));
		procesarInEntity.setMoneda("002");
//		procesarInEntity.setNroFormRel(nroFormRel);
//		procesarInEntity.setNroTransferencia(nroTransferencia);
		procesarInEntity.setRazonSocial("ADRIANA CASANDRA BELLADONNA");
//		procesarInEntity.setTipoOperacion(tipoOperacion);
		procesarInEntity.setVinculo("Hermano");
		
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");
		
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		ProcesarTransferenciaOBPResponse respuesta = comexCanalesDAO.procesarTransferenciaComex(procesarInEntity);

		Assert.assertNotNull(respuesta);
	}
	
	@Test (expected = DAOException.class)
	public void procesarTransferenciaOBPErrorEncoding() throws SQLException, DAOException, IllegalAccessException{
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("9999999");
		cliente.setTipoDocumento("N");
		cliente.setDni("38285216");
		
		FieldUtils.writeDeclaredField(comexCanalesDAO, "appEncoding", "PEPE", true);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		comexCanalesDAO.procesarTransferenciaComex(new ProcesarTransferenciaComexInEntity());
		
	}
}

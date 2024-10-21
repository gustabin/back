package ar.com.santanderrio.obp.servicios.comex.transfext.dao;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
import ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexOrdenPagoDAOIT.ComexOrdenPagoDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.impl.ComexOrdenPagoDAOImpl;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.impl.ComexOrdenPagoGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ProcesarOrPagoOBPInEntity;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ComexOrdenPagoDAOITConfiguration.class,
		SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "TRANSFEXT.ORDENPAGO.POOL.ACTIVO = true",
		"TRANSFEXT.ORDENPAGO.URL = http://webinetdesa01.rio.ar.bsch:9081/ComexCanalesOrPago.svc",
		"TRANSFEXT.ORDENPAGO.POOL.SIZE = 3",
		"KEYSTORE.TRANSFEXT.ORDENPAGO.PATH = aplicaciones/hb/conf/keyStorehbkey.jks",
		"KEYSTORE.TRANSFEXT.ORDENPAGO.TYPE = JKS", "KEYSTORE.TRANSFEXT.ORDENPAGO.IDSEGURIDAD = 20010",
		"TRANSFEXT.ORDENPAGO.JKS=TRANSFEXT.ORDENPAGO", "TRANSFEXT.ORDENPAGO.TIMEOUT=10000",
		"TRANSFEXT.ORDENPAGO.POOL.MAXWAIT=5000", "TRANSFEXT.CANALES.CANT.MAX.OPERACIONES=4000",
		"COMEX.VALIDADOR.VIRUS.DOCUMENTOS.PATH=/aplicaciones/hb/pruebaCopiaArchivos/", "COMEX.MAX.FILESIZE=10485760",
		"COMEX.MAX.FILES.ATACHED=4",
		"COMEX.TIPO.ARCHIVOS.ADJ=.txt,.jpg,.tiff,.pdf,.xls,.xlsx,.doc,.docx,.bmp,.ppt,.zip,.rar,.jpeg,.gif",
		"COMEX.CONCEPTOS.DOCUMENTACION.ADJUNTA=S06|B05|B06|B07|I08",
		"COMEX.TIEMPO.ESPERA.VERIFICACION.ARCHIVO.ANTIVIRUS=2000", "APP.ENCODING = UTF-8" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ComexOrdenPagoDAOIT {

	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The id seg. */
	@Value("${KEYSTORE.TRANSFEXT.ORDENPAGO.IDSEGURIDAD}")
	private String idSeg;

	/** The Comex Consultas DAO. */
	@Autowired
	private ComexOrdenPagoDAO comexOrdenPagoDAO;

	/** The Constant BCORIO. */
	private static final String BCORIO = "bcorio";

	/** The Constant HBPASS. */
	private static final String HBPASS = "hbpassword";

	@Before
	public void init() throws DAOException {
		MockitoAnnotations.initMocks(this);
	}

	@Configuration
	@ComponentScan(basePackageClasses = { ComexOrdenPagoDAOImpl.class, ComexOrdenPagoGestionarWSImpl.class, Sign.class,
			KeyStoreHelperImpl.class,
			ContextHolder.class, }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
					KeyStoreFactory.class }))
	public static class ComexOrdenPagoDAOITConfiguration {

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
					keyStore.setKeystorePath(
							Object.class.getResource("/config/test/keyStore/hbkey.jks").getPath());
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
	public void procesarOrdenPagoTest() throws SQLException, DAOException {
		ProcesarOrPagoOBPInEntity inEntity = new ProcesarOrPagoOBPInEntity();
		inEntity.setAceptaDdjj((short) 1);
		inEntity.setConcepto("aaa");
		inEntity.setCuentaCredito("013-05-3587599");
		inEntity.setImportePago(new BigDecimal(2000));
		inEntity.setNroDocCliente("18375690");
		inEntity.setNroForm(null);
		inEntity.setNroOperacion("P002044243");
		inEntity.setNupCliente("93098");
		inEntity.setRazonSocial("CONDE NEREA DINA");
		inEntity.setTipoDocCliente("DNI");

		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		Cliente cliente = new Cliente();
		cliente.setNup("00093098");
		cliente.setTipoDocumento("N");
		cliente.setDni("18375690");
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		when(sesionCliente.getCliente()).thenReturn(cliente);

		ProcesarOrPagoOBPResponse response = comexOrdenPagoDAO.procesarOrdenPago(inEntity);

		Assert.assertNotNull(response);
	}

}

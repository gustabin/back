package ar.com.santanderrio.obp.servicios.turnosweb.dao;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.cxf.binding.soap.SoapFault;
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
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.ConsultaCitaRequest;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.ParametrosCita;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaConMotivoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetBajaTurnoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConMotivoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaHorariosDisponiblesSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaSucursalesSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetModificacionCitaSvcResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.solicitudes.dao.impl.ConsSolUpgradeGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.turnosweb.dao.TurnosWebDAOIT.TurnosWebDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.turnosweb.dao.impl.DireccionadorGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.turnosweb.dao.impl.TurnosWebDAOImpl;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.AltaModificacionCitaInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.ComprobanteTurnoInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.HorariosDisponiblesInEntity;


/**
 * The Class TurnosWebDAOIT.
 * 
 * @author ITResources
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TurnosWebDAOITConfiguration.class,
		SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { 
		"CONSULTACITASVC.POOL.ACTIVO=true",
		//"CONSULTACITASVC.URL = http://localhost:8400/Direccionador.svc",
		//"CONSULTACITASVC.URL = http://webfrontdesa01.rio.ar.bsch:18401/Direccionador.svc",
		"CONSULTACITASVC.URL = http://webfronthomo01.rio.ar.bsch:18400/Direccionador.svc",
		"CONSULTACITASVC.TIMEOUT=10000",
		"CONSULTACITASVC.POOL.SIZE=3", 
		"CONSULTACITASVC.POOL.MAXWAIT=5000", 
		"APP.ENCODING = UTF-8",
		"KEYSTORE.CONSULTACITASVC.PATH=/aplicaciones/hb/conf/keyStore/hbkey.jks", 
		"KEYSTORE.CONSULTACITASVC.TYPE=JKS",
		"KEYSTORE.CONSULTACITASVC.IDSEGURIDAD=20010", 
"CONSULTACITASVC.JKS=CONSULTACITASVC" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TurnosWebDAOIT {

	/** The Constant BCORIO. */
	private static final String BCORIO = "bcorio";

	/** The Constant HBPASS. */
	private static final String HBPASS = "hbpassword";

	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	/** The turnos web DAO. */
	@Autowired
	private TurnosWebDAO turnosWebDAO;

	/** The id seg. */
	@Value("${KEYSTORE.CONSULTACITASVC.IDSEGURIDAD}")
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
	@ComponentScan(basePackageClasses = { TurnosWebDAOImpl.class, ConsSolUpgradeGestionarWSImpl.class,
			DireccionadorGestionarWSImpl.class, Sign.class, KeyStoreHelperImpl.class,
			ContextHolder.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
					KeyStoreFactory.class }))
	public static class TurnosWebDAOITConfiguration {

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
	 * Consultar citas OK.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void consultarCitasOK() throws SQLException, DAOException {

		//When
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		String nup = "03348599";
		ConsultaCitaRequest request = new ConsultaCitaRequest();
		request.setParametros(new ParametrosCita(nup));

		//Then
		GetConsultaCitaConMotivoSvcResponse result = turnosWebDAO.consultarCitas(nup);

		//Expected
		Assert.assertNotNull(result);
	}
	
	/**
	 * 
	 * @throws SQLException
	 * @throws DAOException
	 * @throws IllegalAccessException
	 */
	@Test (expected = DAOException.class)
	public void consultarCitasErrorEncoding() throws SQLException, DAOException, IllegalAccessException {

		//When
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		FieldUtils.writeDeclaredField(turnosWebDAO, "appEncoding", "PEPE", true);
		String nup = "03348599";
		ConsultaCitaRequest request = new ConsultaCitaRequest();
		request.setParametros(new ParametrosCita(nup));
		//Then
		turnosWebDAO.consultarCitas(nup);
	}

	/**
	 * 
	 * @throws SQLException
	 * @throws DAOException
	 */
	@Test
	public void consultaHorarioDisponiblesOK() throws SQLException, DAOException {

		//When
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		HorariosDisponiblesInEntity horariosDisponiblesInEntity = new HorariosDisponiblesInEntity();
		horariosDisponiblesInEntity.setNroSuc("000");
		horariosDisponiblesInEntity.setSector("P");

		//Then
		GetConsultaHorariosDisponiblesSvcResponse response = turnosWebDAO.consultaHorarioDisponibles(horariosDisponiblesInEntity);
		//Expected
		Assert.assertNotNull(response);
	}

	/**
	 * 
	 * @throws SQLException
	 * @throws DAOException
	 * @throws SoapFault
	 */
	@Test
	public void consultaSucursalesOK() throws SQLException, DAOException, SoapFault {

		//When
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		//Then
		GetConsultaSucursalesSvcResponse response = turnosWebDAO.consultaSucursales();

		//Expected
		Assert.assertNotNull(response);

	}

	/**
	 * 
	 * @throws SQLException
	 * @throws DAOException
	 */
	@Test
	public void altaCitaOK() throws SQLException, DAOException {

		//When
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		AltaModificacionCitaInEntity altaModificacionCitaInEntity = new AltaModificacionCitaInEntity();
		altaModificacionCitaInEntity.setFecha("20180614");
		altaModificacionCitaInEntity.setFraccion("1000-1030");
		altaModificacionCitaInEntity.setIdTurno((long) 0);
		altaModificacionCitaInEntity.setNroSuc("000");
		altaModificacionCitaInEntity.setNup("03348599");
		altaModificacionCitaInEntity.setSector("P");     
		//Then
		GetAltaCitaConMotivoSvcResponse response = turnosWebDAO.altaCita(altaModificacionCitaInEntity);
		//Expected
		Assert.assertNotNull(response);
	}

	
	/**
	 * 
	 * @throws SQLException
	 * @throws DAOException
	 */
	@Test
	public void modificacionCitaOK() throws SQLException, DAOException {

		//When
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		AltaModificacionCitaInEntity altaModificacionCitaInEntity = new AltaModificacionCitaInEntity();
		altaModificacionCitaInEntity.setFecha("20180614");
		altaModificacionCitaInEntity.setFraccion("1000-1030");
		altaModificacionCitaInEntity.setIdTurno((long) 123456);
		altaModificacionCitaInEntity.setNroSuc("000");
		altaModificacionCitaInEntity.setNup("03348599");
		altaModificacionCitaInEntity.setSector("P");     
		//Then
		GetModificacionCitaSvcResponse response = turnosWebDAO.modificacionCita(altaModificacionCitaInEntity);
		//Expected
		Assert.assertNotNull(response);
	}
	
	/**
	 * 
	 * @throws SQLException
	 * @throws DAOException
	 */
	@Test
	public void bajaTurnoOK() throws SQLException, DAOException {

		//When
		Credential cred = new Credential();
		cred.setUsuario(BCORIO);
		cred.setPassword(HBPASS);
		when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		//Then
		GetBajaTurnoSvcResponse response = turnosWebDAO.bajaTurno((long) 1234);
		//Expected
		Assert.assertNotNull(response);
	}
	
	/**
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 */
    @Test
    public void generarComprobante() throws IOException, IllegalAccessException {
    	ComprobanteTurnoInEntity comprobanteTurnoInEntity = new ComprobanteTurnoInEntity();
		comprobanteTurnoInEntity.setLogoDefault("Solicitud de turno con ejecutivo");
		comprobanteTurnoInEntity.setDia("Lunes 21, de Septiembre del 2018");
		comprobanteTurnoInEntity.setHora("09:00 hs");
		comprobanteTurnoInEntity.setSucursal("245 - Salta, Tres cerritos");
		comprobanteTurnoInEntity.setDireccion("Virrey Toledo 749, Salta");
		comprobanteTurnoInEntity.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		comprobanteTurnoInEntity.setAreaCelular("11");
		comprobanteTurnoInEntity.setNumeroCelular("22223333");
		comprobanteTurnoInEntity.setEmpresaCelular("Claro");
		comprobanteTurnoInEntity.setEmail("dtraslavina@servexternos.isban-santander.com.ar");
        FieldUtils.writeField(turnosWebDAO, "logoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
        FieldUtils.writeField(turnosWebDAO, "logoCierre", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
        FieldUtils.writeField(turnosWebDAO, "fileJasperTurnoWeb",
                appContext.getResource("classpath:/report/turnosWeb/TurnoWeb.jasper"), true);
        Cliente cliente = new Cliente();
        cliente.setDni("11111111");
        cliente.setNombre("Nombre");
        cliente.setApellido1("Apellido");
        Reporte reporte = turnosWebDAO.generarComprobante(comprobanteTurnoInEntity, cliente);
        Assert.assertNotNull(reporte.getBytes());
       // FileUtils.writeByteArrayToFile(new File("C:/tools/file15.pdf"),reporte.getBytes());
    }
          
}
package ar.com.santanderrio.obp.servicios.debinws.dao;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceException;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.datasource.SystemRoutingDataSource;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.generated.webservices.debin.CompradorDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.DetalleDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConsulta;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestNuevoDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestNuevoDebinV3;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseNuevoDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseVendedor;
import ar.com.santanderrio.obp.generated.webservices.debin.VendedorDebinDTO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.debinws.dao.impl.DebinGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.debinws.dao.impl.DebinWSSolicitudesDAOImpl;
import ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteSolicitudDTO;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCBUEntityIn;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCbuEntityOut;
import ar.com.santanderrio.obp.servicios.debinws.exceptions.DebinCBUInvalidoDAOException;
import ar.com.santanderrio.obp.servicios.debinws.exceptions.DebinDestinatarioNoVerificadoException;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelper;
import ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.RequestCNSTITCBU;

/**
 * The Class DebinWSSolicitudesDAOImpl.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, 
		classes = {
					ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSSolicitudesDAOIT.DebinWSSolicitudesDAOITConfiguration.class,
					SecurityProviderConfig.class 
					}
)
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
public class DebinWSSolicitudesDAOIT {
	
	/** The debin WS Solicitudes DAO. */
	@Autowired
	private DebinWSSolicitudesDAO debinWSSolicitudesDAO;

	/** The id seg. */
	@Value("${KEYSTORE.DEBINWS.IDSEGURIDAD}")
	private String idSeg;

    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();
    
	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;
	
    /** The CNSTITCBU name. */
    private String CNSTITCBUname = "CNSTITCBU_";
    
    /** The CNSTITCBU version. */
    private String CNSTITCBUversion = "110";
    
    /** The CNSTITCB U 110 response OK. */
    private String CNSTITCBU_110_ResponseOK = "200000000000P04HTML0009900010300MRQT37  ********00170890000000092017051809401400000000IBF21602000000000000CNSTITCBU_1100000            00276937    00        00 000000000201705180940030000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0018900000000õQAPEREZ QALILIANA J  /DEL BUENO GABRIELA   /                    õ27181388167õ27231462517õ           õBANCO ITAU            õ11õ04391813013                 õ01õ11õIBAYõ0000õ0000000õ";
    
    @Autowired
    @InjectMocks
    private TransferenciaDAO transferenciaDAOImpl;
   
    /** The cliente. */
    private Cliente cliente;
    
	/**
	 * 
	 * Inits the.
	 */
    @Before
    public void init() {
    	MockitoAnnotations.initMocks(this);
    	cliente = new Cliente();
    	Segmento segmento = new Segmento();
    	segmento.setSelect(false);
    	segmento.setDuo(false);
        segmento.setPyme(false);
    	segmento.setUniversidad(false);
    	cliente.setNombre("CONSTANCIO PERCY");
    	cliente.setApellido1("MILANDO");
    	cliente.setApellido2("M");
    	cliente.setSegmento(segmento);
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
	public static class DebinWSSolicitudesDAOITConfiguration {

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
    public void descargarComprobante() throws IOException, IllegalAccessException {
    	ComprobanteSolicitudDTO comprobanteSolicitudDTO = new ComprobanteSolicitudDTO();
    	comprobanteSolicitudDTO.setNombreDestinatario("DESA 4");
    	comprobanteSolicitudDTO.setImporte("$ 200,00");
    	comprobanteSolicitudDTO.setCuit("27-35426856-1");
    	comprobanteSolicitudDTO.setCbu("07200007880000061887");
    	comprobanteSolicitudDTO.setAlias("PRUEBA.ALIAS.ITR");
    	comprobanteSolicitudDTO.setBanco("BANCO SANTANDER");
    	comprobanteSolicitudDTO.setTipoCuentaAcreditacion("Cuenta Única");
    	comprobanteSolicitudDTO.setNroCuentaAcreditacion("100-200529/4");
    	comprobanteSolicitudDTO.setFechaVencimiento("22/12/2018");
    	comprobanteSolicitudDTO.setConcepto("Facturas");
    	comprobanteSolicitudDTO.setDescripcion("Prueba ITR");
    	comprobanteSolicitudDTO.setNroComprobante("181220115535");
    	comprobanteSolicitudDTO.setFechaHora("20/12/2018 - 11:55");		
        FieldUtils.writeField(debinWSSolicitudesDAO, "logoCabecera", 
        		appContext.getResource("classpath:/report/debinWS/logo_cabecera_comprobante.png"), true);
        FieldUtils.writeField(debinWSSolicitudesDAO, "logoCierre", 
        		appContext.getResource("classpath:/report/debinWS/logo_cierre_comprobante.png"), true);
        FieldUtils.writeField(debinWSSolicitudesDAO, "fileJasperComprobanteSolicitudDebin",
                appContext.getResource("classpath:/report/debinWS/ComprobanteSolicitudDebin.jasper"), true);
        Reporte reporte = debinWSSolicitudesDAO.descargarComprobante(comprobanteSolicitudDTO);
        Assert.assertNotNull(reporte.getBytes());
       // FileUtils.writeByteArrayToFile(new File("C:/tools/file27.pdf"),reporte.getBytes());
    }
    
    
    /**
     * @throws SQLException
     * @throws DAOException
     * @throws DatatypeConfigurationException
     */
    @Test
    public void solicitarDebinOK() throws SQLException, DAOException, DatatypeConfigurationException {
    	Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
    	RequestNuevoDebin request = new RequestNuevoDebin();
    	
    	CuentaDebinDTO cuenta = new CuentaDebinDTO();
        CompradorDebinDTO comprador = new CompradorDebinDTO();
        CuentaDebinDTO cuentaVendedor = new CuentaDebinDTO();
        cuentaVendedor.setCbu("0720100088000020052940");
        comprador.setCuit("20215871836");
        comprador.setTitular("MILANDO CONSTANCIO PERCY");
        
        cuenta.setCbu("0720033588000036125322");
        comprador.setCuenta(cuenta);
       
        DetalleDebinDTO detalleDebin = new DetalleDebinDTO();
        
        Calendar calendarHasta = new GregorianCalendar();
		calendarHasta.setTime(new Date());

		Calendar calendarDesde = new GregorianCalendar();
		calendarDesde.setTime(new Date());
		calendarDesde.add(Calendar.DAY_OF_YEAR, 90);

		GregorianCalendar c = new GregorianCalendar();
		c.setTime(calendarDesde.getTime());
		XMLGregorianCalendar fechaCreacion = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		
		c.setTime(calendarHasta.getTime());
		XMLGregorianCalendar fechaExpiracion = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		
		VendedorDebinDTO vendedor = new VendedorDebinDTO();
		vendedor.setCuit("20169406910");
		vendedor.setCuenta(cuentaVendedor);
        
        detalleDebin.setConcepto("02");
        detalleDebin.setMoneda("840");
        detalleDebin.setImporte("500.00");
        detalleDebin.setDescripcion("Descripcion");
        detalleDebin.setFechaCreacion(fechaCreacion);
        detalleDebin.setFechaExpiracion(fechaExpiracion);
        
        request.setDetalle(detalleDebin);
    	request.setCanal("E");
    	request.setCategoriaLimite(0);
    	request.setComprador(comprador);
    	request.setVendedor(vendedor);
    	request.setCuit("20215871836");
    	request.setIp("127.0.0.1");
    	request.setNroDocumento("21587183");
    	request.setTipoDocumento("1");
    	request.setMismoTitular(0);
    	request.setRecurrencia(false);
    	
    	ResponseNuevoDebin response = debinWSSolicitudesDAO.solicitarDebin(request);
    	
    	Assert.assertNotNull(response);
    }
    
    /**
     * @throws SQLException
     * @throws DAOException
     */
    @Test(expected = DAOException.class)
    public void solicitarDebinErrorDebinException() throws SQLException, DAOException {
    	Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
		debinWSSolicitudesDAO.solicitarDebin(new RequestNuevoDebin());
    }
    
    /**
     * @throws DAOException
     */
    @Test(expected = DAOException.class)
    public void solicitarDebinErrorRuntime() throws DAOException {
		debinWSSolicitudesDAO.solicitarDebin(new RequestNuevoDebin());
    }

    @Test(expected = DAOException.class)
    public void solicitarDebinErrorWebservice() throws DAOException, SQLException {
    	Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		Mockito.when(debinWSSolicitudesDAO.solicitarDebin(Matchers.any(RequestNuevoDebin.class))).thenThrow(new WebServiceException());
    }
    
    /**
     * @throws SQLException
     * @throws DAOException
     * @throws DatatypeConfigurationException
     */
    @Test
    public void solicitarDebinV3OK() throws SQLException, DAOException, DatatypeConfigurationException {
    	Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
    	RequestNuevoDebinV3 request = new RequestNuevoDebinV3();
    	
    	CuentaDebinDTO cuenta = new CuentaDebinDTO();
        CompradorDebinDTO comprador = new CompradorDebinDTO();
        CuentaDebinDTO cuentaVendedor = new CuentaDebinDTO();
        cuentaVendedor.setCbu("0720100088000020052940");
        comprador.setCuit("20215871836");
        comprador.setTitular("MILANDO CONSTANCIO PERCY");
        
        cuenta.setCbu("0720033588000036125322");
        comprador.setCuenta(cuenta);
       
        DetalleDebinDTO detalleDebin = new DetalleDebinDTO();
        
        Calendar calendarHasta = new GregorianCalendar();
		calendarHasta.setTime(new Date());

		Calendar calendarDesde = new GregorianCalendar();
		calendarDesde.setTime(new Date());
		calendarDesde.add(Calendar.DAY_OF_YEAR, 90);

		GregorianCalendar c = new GregorianCalendar();
		c.setTime(calendarDesde.getTime());
		XMLGregorianCalendar fechaCreacion = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		
		c.setTime(calendarHasta.getTime());
		XMLGregorianCalendar fechaExpiracion = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		
		VendedorDebinDTO vendedor = new VendedorDebinDTO();
		vendedor.setCuit("20169406910");
		vendedor.setCuenta(cuentaVendedor);
        
        detalleDebin.setConcepto("02");
        detalleDebin.setMoneda("840");
        detalleDebin.setImporte("500.00");
        detalleDebin.setDescripcion("Descripcion");
        detalleDebin.setFechaCreacion(fechaCreacion);
        detalleDebin.setFechaExpiracion(fechaExpiracion);
        
        request.setDetalle(detalleDebin);
    	request.setCanal("E");
    	request.setCategoriaLimite(0);
    	request.setComprador(comprador);
    	request.setVendedor(vendedor);
    	request.setCuit("20215871836");
    	request.setIp("127.0.0.1");
    	request.setNroDocumento("21587183");
    	request.setTipoDocumento("1");
    	request.setMismoTitular(0);
    	request.setRecurrencia(false);
    	
    	ResponseNuevoDebin response = debinWSSolicitudesDAO.solicitarDebinV3(request);
    	
    	Assert.assertNotNull(response);
    }
    
    /**
     * @throws SQLException
     * @throws DAOException
     */
    @Test(expected = DAOException.class)
    public void solicitarDebinV3ErrorDebinException() throws SQLException, DAOException {
    	Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
		debinWSSolicitudesDAO.solicitarDebinV3(new RequestNuevoDebinV3());
    }
    
    /**
     * @throws DAOException
     */
    @Test(expected = DAOException.class)
    public void solicitarDebinV3ErrorRuntime() throws DAOException {
		debinWSSolicitudesDAO.solicitarDebinV3(new RequestNuevoDebinV3());
    }

    @Test(expected = DAOException.class)
    public void solicitarDebinV3ErrorWebservice() throws DAOException, SQLException {
    	Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		Mockito.when(debinWSSolicitudesDAO.solicitarDebinV3(Matchers.any(RequestNuevoDebinV3.class))).thenThrow(new WebServiceException());
    }
    
    @Test
    public void consultarCuentasAdheridasOK() throws SQLException, DAOException{
    	Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		
		RequestConsulta request = new RequestConsulta();
		
		request.setCanal("E");
		request.setCuit("20169406910");
		request.setIp("180.166.120.154");
		request.setNroDocumento("16940691");
		request.setTipoDocumento("1");
		
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
		ResponseVendedor response = debinWSSolicitudesDAO.consultarCuentasAdheridas(request);
		
		Assert.assertNotNull(response);
    }
    
    @Test(expected = DAOException.class)
    public void consultarCuentasAdheridasErrorDebinException() throws SQLException, DAOException{
    	Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		
		debinWSSolicitudesDAO.consultarCuentasAdheridas(new RequestConsulta());
    }
    
    @Test(expected = DAOException.class)
    public void consultarCuentasAdheridasErrorRuntime() throws DAOException{		
		debinWSSolicitudesDAO.consultarCuentasAdheridas(new RequestConsulta());
    }
    
    @Test(expected = DAOException.class)
    public void consultarCuentasAdheridasErrorWebservice() throws SQLException, DAOException{
    	Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		Mockito.when(debinWSSolicitudesDAO.consultarCuentasAdheridas(Matchers.any(RequestConsulta.class))).thenThrow(new WebServiceException());
    }
        
    /**
     * * Test para validar que el CBU sea valido. Mocks iatxSender con CNSCTATIT_
     * version 110
     * 
     * @throws IatxException
     * @throws DAOException 
     * @throws DebinDestinatarioNoVerificadoException 
     * @throws DebinCBUInvalidoDAOException 
     */
    @Test
    public void consultarCNSTITCBUOK() throws IatxException, DAOException, DebinCBUInvalidoDAOException, DebinDestinatarioNoVerificadoException{
    	ConsultaCBUEntityIn consultaCBUIn = new ConsultaCBUEntityIn();
    	consultaCBUIn.setCbuDestino("07200007880000061887");
    	consultaCBUIn.setDireccionIP("127.0.0.1");
    	consultaCBUIn.setNroCuenta("3000400036111");
    	consultaCBUIn.setNroSucursal("011");
    	consultaCBUIn.setNroTarjeta("4485715860868875");
    	consultaCBUIn.setTipoCuenta("09");

        
        IatxResponse response = new IatxResponse();
        response.setEstadoRespuesta(EstadoRespuesta.OK);
        response.setNombreServicio(CNSTITCBUname);
        response.setVersionServicio(CNSTITCBUversion);
        Vector<String> vector = buildVectorIsCBUValido();
        response.setTrama(CNSTITCBU_110_ResponseOK);
        response.setIatxBody(vector);
        response.setErrorCode(00000000);
        
        
        when(transferenciaDAOImpl.conexionCNSTITICBU(Matchers.any(Cliente.class), Matchers.any(RequestCNSTITCBU.class))).thenReturn(response);
        
        ConsultaCbuEntityOut respuesta = debinWSSolicitudesDAO.consultarCNSTITCBU(consultaCBUIn);
        
        Assert.assertNotNull(respuesta);
    }
    
    private Vector<String> buildVectorIsCBUValido() {
        Vector<String> vector = new Vector<String>();
        vector.add("00000000");
        vector.add("QAPEREZ QALILIANA J  /DEL BUENO GABRIELA   /                    ");
        vector.add("27181388167");
        vector.add("27231462517");
        vector.add("           ");
        vector.add("BANCO ITAU            ");
        vector.add("11");
        vector.add("04391813013                 ");
        vector.add("01");
        vector.add("11");
        vector.add("IBAY");
        vector.add("0000");
        vector.add("0000000");
        return vector;
    }
}

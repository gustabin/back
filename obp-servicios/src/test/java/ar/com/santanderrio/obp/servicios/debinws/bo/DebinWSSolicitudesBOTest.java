package ar.com.santanderrio.obp.servicios.debinws.bo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendido;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendidoResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.CuentaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.MonedaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.TitularidadExtendido;
import ar.com.santanderrio.obp.generated.webservices.debin.DetalleDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.Error;
import ar.com.santanderrio.obp.generated.webservices.debin.EstadoDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConsulta;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestNuevoDebinV3;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseNuevoDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseVendedor;
import ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.debinws.bo.impl.DebinWSSolicitudesBOImpl;
import ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSSolicitudesDAO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteSolicitudDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CreacionDebinWSInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CreacionDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentasAdheridasInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentasAdheridasOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarAliasInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarAliasOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarCbuInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarCbuOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCBUEntityIn;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCbuEntityOut;
import ar.com.santanderrio.obp.servicios.debinws.exceptions.DebinCBUInvalidoDAOException;
import ar.com.santanderrio.obp.servicios.debinws.exceptions.DebinDestinatarioNoVerificadoException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class DebinWSSolicitudesBOTest {
	
	
	@InjectMocks
	private DebinWSSolicitudesBO solicitudesDebinWSBO = new DebinWSSolicitudesBOImpl();
	
    /** The solicitudes debin WSDAO. */
	@Mock
    private DebinWSSolicitudesDAO solicitudesDebinWSDAO;
	
    @Mock
    private AliasCbuDAO aliasCbuDAO;
    
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Mock
    private MensajeBO mensajeBO;
        
    @Mock
    private SesionParametros sesionParametros;

    @Mock
    private SesionCliente sesionCliente;
    
    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;
    
    private Mensaje mensaje = new Mensaje();

    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
        MockitoAnnotations.initMocks(this);
        cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("0720033520000000819954");
        cuenta.setAlias("Alias");
        cuenta.setNroSucursal("02");
        cuenta.setTipoCuenta("2");
        cuenta.setNroCuentaProducto("3423423423432");
        cuentas.add(cuenta );
        cliente.setCuentas(cuentas );
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("Silvina");
        cliente.setApellido1("Luque");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
        cliente.setNup("123456789");
        cliente.setNumeroCUILCUIT("27216775134");
        cliente.setDni("21677513");
        cliente.setTipoDocumento("N");
    }
    
    @Test
    public void consultaCuentasAdheridasOK() throws DAOException {
    	CuentasAdheridasInDTO cuentasAdheridas = new CuentasAdheridasInDTO();
    	cuentasAdheridas.setNroDocumento("21677513");
    	cuentasAdheridas.setTipoDocumento("N");
    	cuentasAdheridas.setCanal("E");
    	
    	ResponseVendedor responseVendedor = new ResponseVendedor();
    	Error error = new Error();
    	error.setCodigo("00");
    	
    	responseVendedor.setCuit("27216775134");
    	responseVendedor.setError(error);
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	Mockito.when(solicitudesDebinWSDAO.consultarCuentasAdheridas(Matchers.any(RequestConsulta.class))).thenReturn(responseVendedor);
    	
    	Respuesta<CuentasAdheridasOutDTO> respuesta = solicitudesDebinWSBO.consultaCuentasAdheridas(cuentasAdheridas);

    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void consultaCuentasAdheridasErrorTimeOut() throws DAOException {
    	CuentasAdheridasInDTO cuentasAdheridas = new CuentasAdheridasInDTO();
    	cuentasAdheridas.setNroDocumento("21677513");
    	cuentasAdheridas.setTipoDocumento("N");
    	cuentasAdheridas.setCanal("E");
    	
    	DAOException error = new DAOException();
    	error.setErrorCode("099");
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	Mockito.when(solicitudesDebinWSDAO.consultarCuentasAdheridas(Matchers.any(RequestConsulta.class))).thenThrow(error);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<CuentasAdheridasOutDTO> respuesta = solicitudesDebinWSBO.consultaCuentasAdheridas(cuentasAdheridas);

    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void consultaCuentasAdheridasErrorGenerico() throws DAOException {
    	CuentasAdheridasInDTO cuentasAdheridas = new CuentasAdheridasInDTO();
    	cuentasAdheridas.setNroDocumento("21677513");
    	cuentasAdheridas.setTipoDocumento("N");
    	cuentasAdheridas.setCanal("E");
    	
    	DAOException error = new DAOException();
    	error.setErrorCode("09");
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	Mockito.when(solicitudesDebinWSDAO.consultarCuentasAdheridas(Matchers.any(RequestConsulta.class))).thenThrow(error);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<CuentasAdheridasOutDTO> respuesta = solicitudesDebinWSBO.consultaCuentasAdheridas(cuentasAdheridas);

    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    @Test
    public void consultaCuentasAdheridasError() throws DAOException {
    	CuentasAdheridasInDTO cuentasAdheridas = new CuentasAdheridasInDTO();
    	cuentasAdheridas.setNroDocumento("21677513");
    	cuentasAdheridas.setTipoDocumento("N");
    	cuentasAdheridas.setCanal("E");
    	
    	ResponseVendedor responseVendedor = new ResponseVendedor();
    	Error error = new Error();
    	error.setCodigo("02");
    	
    	responseVendedor.setCuit("27216775134");
    	responseVendedor.setError(error);
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	Mockito.when(solicitudesDebinWSDAO.consultarCuentasAdheridas(Matchers.any(RequestConsulta.class))).thenReturn(responseVendedor);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<CuentasAdheridasOutDTO> respuesta = solicitudesDebinWSBO.consultaCuentasAdheridas(cuentasAdheridas);

    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void consultaCuentasAdheridasWarning() throws DAOException {
    	CuentasAdheridasInDTO cuentasAdheridas = new CuentasAdheridasInDTO();
    	cuentasAdheridas.setNroDocumento("21677513");
    	cuentasAdheridas.setTipoDocumento("N");
    	cuentasAdheridas.setCanal("E");
    	
    	ResponseVendedor responseVendedor = new ResponseVendedor();
    	Error error = new Error();
    	error.setCodigo("1302");
    	
    	responseVendedor.setCuit("27216775134");
    	responseVendedor.setError(error);
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	Mockito.when(solicitudesDebinWSDAO.consultarCuentasAdheridas(Matchers.any(RequestConsulta.class))).thenReturn(responseVendedor);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<CuentasAdheridasOutDTO> respuesta = solicitudesDebinWSBO.consultaCuentasAdheridas(cuentasAdheridas);

    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    @Test
    public void validarCbuDebinOK() throws DAOException, DebinCBUInvalidoDAOException, DebinDestinatarioNoVerificadoException {    	
    	ConsultaCbuEntityOut consultaCbu = new ConsultaCbuEntityOut();
    	consultaCbu.setBandes("BANCO ITAU            ");
    	consultaCbu.setTitular("QAPEREZ QALILIANA J  /DEL BUENO GABRIELA   /                    ");
    	consultaCbu.setCuit1("27181388167");
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	Mockito.when(solicitudesDebinWSDAO.consultarCNSTITCBU(Matchers.any(ConsultaCBUEntityIn.class))).thenReturn(consultaCbu);
    	
    	Respuesta<ValidarCbuOutDTO> respuesta = solicitudesDebinWSBO.validarCbuDebin(obtenerValidarCbuInDTO());
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarCbuDebinErrorSinTitular() throws DAOException, DebinCBUInvalidoDAOException, DebinDestinatarioNoVerificadoException {
    	ConsultaCbuEntityOut consultaCbu = new ConsultaCbuEntityOut();
    	consultaCbu.setTitular("   ");
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	Mockito.when(solicitudesDebinWSDAO.consultarCNSTITCBU(Matchers.any(ConsultaCBUEntityIn.class))).thenReturn(consultaCbu);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ValidarCbuOutDTO> respuesta = solicitudesDebinWSBO.validarCbuDebin(obtenerValidarCbuInDTO());
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarCbuDebinErrorDebinDestinatarioNoVerificado() throws DAOException, DebinCBUInvalidoDAOException, DebinDestinatarioNoVerificadoException {    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	Mockito.when(solicitudesDebinWSDAO.consultarCNSTITCBU(Matchers.any(ConsultaCBUEntityIn.class))).thenThrow(new DebinDestinatarioNoVerificadoException());
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ValidarCbuOutDTO> respuesta = solicitudesDebinWSBO.validarCbuDebin(obtenerValidarCbuInDTO());
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarCbuDebinErrorDebinCBUInvalido57() throws DAOException, DebinCBUInvalidoDAOException, DebinDestinatarioNoVerificadoException {
    	DebinCBUInvalidoDAOException e = new DebinCBUInvalidoDAOException();
    	e.setErrorCode(10000036);
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	Mockito.when(solicitudesDebinWSDAO.consultarCNSTITCBU(Matchers.any(ConsultaCBUEntityIn.class))).thenThrow(e);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ValidarCbuOutDTO> respuesta = solicitudesDebinWSBO.validarCbuDebin(obtenerValidarCbuInDTO());
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarCbuDebinErrorDebinCBUInvalidoUsuarioInexistente() throws DAOException, DebinCBUInvalidoDAOException, DebinDestinatarioNoVerificadoException {    	
    	DebinCBUInvalidoDAOException e = new DebinCBUInvalidoDAOException();
    	e.setErrorCode(10000056);
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	Mockito.when(solicitudesDebinWSDAO.consultarCNSTITCBU(Matchers.any(ConsultaCBUEntityIn.class))).thenThrow(e);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ValidarCbuOutDTO> respuesta = solicitudesDebinWSBO.validarCbuDebin(obtenerValidarCbuInDTO());
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarCbuDebinErrorDebinCBUInvalidoGenerico() throws DAOException, DebinCBUInvalidoDAOException, DebinDestinatarioNoVerificadoException {    	
    	DebinCBUInvalidoDAOException e = new DebinCBUInvalidoDAOException();
    	e.setErrorCode(10000099);
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	Mockito.when(solicitudesDebinWSDAO.consultarCNSTITCBU(Matchers.any(ConsultaCBUEntityIn.class))).thenThrow(e);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ValidarCbuOutDTO> respuesta = solicitudesDebinWSBO.validarCbuDebin(obtenerValidarCbuInDTO());
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarCbuDebinTimeOut() throws DAOException, DebinCBUInvalidoDAOException, DebinDestinatarioNoVerificadoException {    	    	
    	DAOException error = new DAOException();
    	error.setErrorCode("099");
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	Mockito.when(solicitudesDebinWSDAO.consultarCNSTITCBU(Matchers.any(ConsultaCBUEntityIn.class))).thenThrow(error);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ValidarCbuOutDTO> respuesta = solicitudesDebinWSBO.validarCbuDebin(obtenerValidarCbuInDTO());
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarCbuDebinErrorGenerico() throws DAOException, DebinCBUInvalidoDAOException, DebinDestinatarioNoVerificadoException {    	    	
    	DAOException error = new DAOException();
    	error.setErrorCode("09");
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	Mockito.when(solicitudesDebinWSDAO.consultarCNSTITCBU(Matchers.any(ConsultaCBUEntityIn.class))).thenThrow(error);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ValidarCbuOutDTO> respuesta = solicitudesDebinWSBO.validarCbuDebin(obtenerValidarCbuInDTO());
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void descargarComprobanteOK() {
    	
    	when(solicitudesDebinWSDAO.descargarComprobante(Matchers.any(ComprobanteSolicitudDTO.class))).thenReturn(new Reporte());
    	
    	Respuesta<Reporte> respuesta = solicitudesDebinWSBO.descargarComprobante(new ComprobanteSolicitudDTO());
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void descargarComprobanteError() {
    	when(solicitudesDebinWSDAO.descargarComprobante(Matchers.any(ComprobanteSolicitudDTO.class))).thenThrow(new ISBANRuntimeException());
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<Reporte> respuesta = solicitudesDebinWSBO.descargarComprobante(new ComprobanteSolicitudDTO());
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    private ValidarCbuInDTO obtenerValidarCbuInDTO(){
    	ValidarCbuInDTO validarCBU = new ValidarCbuInDTO();
    	validarCBU.setCbuDestino("0720033520000000819954");
    	validarCBU.setNroCuenta("1234567");
    	validarCBU.setNroSucursal("011");
    	validarCBU.setNroTarjeta("4485715860868875");
    	validarCBU.setTipoCuenta("09");
    	validarCBU.setMoneda("032");
    	return validarCBU;
    }
    
    @Test
    public void solicitarDebinOK() throws DAOException {
    	CreacionDebinWSInDTO creacionDebinWS = new CreacionDebinWSInDTO();
    	creacionDebinWS.setCanal("E");
    	creacionDebinWS.setNroDocumento("21677513");
    	creacionDebinWS.setCuitDestinatario("27216775134");
    	creacionDebinWS.setCategoriaLimite(0);
    	creacionDebinWS.setPermitePreautorizado(false);
    	creacionDebinWS.setTitularDestinatario("Silvina Luque");
    	creacionDebinWS.setCbuDestinatario("0720033520000000819954");
    	creacionDebinWS.setAliasDestinatario("Alias");
    	creacionDebinWS.setConcepto("02");
    	creacionDebinWS.setDescripcion("Descripcion");
    	creacionDebinWS.setFechaVencimiento("11/02/2019");
    	creacionDebinWS.setMoneda("032");
    	creacionDebinWS.setCbuOrigen("0720033520000000819975");
    	creacionDebinWS.setTipoDocumento("N");
    	creacionDebinWS.setImporte("1200");
        
    	ResponseNuevoDebin responseNuevoDebin = new ResponseNuevoDebin();
    	
    	DetalleDebinDTO detalleDebin = new DetalleDebinDTO();
        EstadoDebinDTO estadoDTO = new EstadoDebinDTO();
        estadoDTO.setCodigo("1");
        estadoDTO.setDescripcion("ACREDITADO");
        detalleDebin.setEstadoDebin(estadoDTO );
        detalleDebin.setConcepto("02");
        GregorianCalendar cal = new GregorianCalendar(2018, 1, 27);
        detalleDebin.setFechaCreacion(new XMLGregorianCalendarImpl(cal));
        GregorianCalendar cal2 = new GregorianCalendar(2018, 5, 27);
        detalleDebin.setFechaExpiracion(new XMLGregorianCalendarImpl(cal2));
        detalleDebin.setDescripcion("Compra de patineta voladora");
        detalleDebin.setIdDebin("dxcsd234vsdd");
        detalleDebin.setImporte("345.56");
        detalleDebin.setMoneda("032");
        
        responseNuevoDebin.setDetalleDebin(detalleDebin);
        
    	when(solicitudesDebinWSDAO.solicitarDebinV3(Matchers.any(RequestNuevoDebinV3.class))).thenReturn(responseNuevoDebin);
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	
    	Respuesta<CreacionDebinWSOutDTO> respuesta = solicitudesDebinWSBO.solicitarDebin(creacionDebinWS);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void solicitarDebinError() throws DAOException {
       	CreacionDebinWSInDTO creacionDebinWS = new CreacionDebinWSInDTO();
    	creacionDebinWS.setCanal("E");
    	creacionDebinWS.setNroDocumento("21677513");
    	creacionDebinWS.setCuitDestinatario("27216775134");
    	creacionDebinWS.setCategoriaLimite(0);
    	creacionDebinWS.setPermitePreautorizado(false);
    	creacionDebinWS.setTitularDestinatario("Silvina Luque");
    	creacionDebinWS.setCbuDestinatario("0720033520000000819954");
    	creacionDebinWS.setAliasDestinatario("Alias");
    	creacionDebinWS.setConcepto("02");
    	creacionDebinWS.setDescripcion("Descripcion");
    	creacionDebinWS.setFechaVencimiento("11/02/2019");
    	creacionDebinWS.setMoneda("032");
    	creacionDebinWS.setCbuOrigen("0720033520000000819975");
    	creacionDebinWS.setTipoDocumento("N");
    	creacionDebinWS.setImporte("1200");
        
    	ResponseNuevoDebin responseNuevoDebin = new ResponseNuevoDebin();
    	Error error = new Error();
    	error.setCodigo("02");
    	
    	DetalleDebinDTO detalleDebin = new DetalleDebinDTO();
        EstadoDebinDTO estadoDTO = new EstadoDebinDTO();
        estadoDTO.setCodigo("1");
        estadoDTO.setDescripcion("ACREDITADO");
        detalleDebin.setEstadoDebin(estadoDTO );
        detalleDebin.setConcepto("02");
        GregorianCalendar cal = new GregorianCalendar(2018, 1, 27);
        detalleDebin.setFechaCreacion(new XMLGregorianCalendarImpl(cal));
        GregorianCalendar cal2 = new GregorianCalendar(2018, 5, 27);
        detalleDebin.setFechaExpiracion(new XMLGregorianCalendarImpl(cal2));
        detalleDebin.setDescripcion("Compra de patineta voladora");
        detalleDebin.setIdDebin("dxcsd234vsdd");
        detalleDebin.setImporte("345.56");
        detalleDebin.setMoneda("032");
        responseNuevoDebin.setError(error);
        responseNuevoDebin.setDetalleDebin(detalleDebin);
        
    	when(solicitudesDebinWSDAO.solicitarDebinV3(Matchers.any(RequestNuevoDebinV3.class))).thenReturn(responseNuevoDebin);
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<CreacionDebinWSOutDTO> respuesta = solicitudesDebinWSBO.solicitarDebin(creacionDebinWS);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void solicitarDebinErrorTimeOut() throws DAOException {
       	CreacionDebinWSInDTO creacionDebinWS = new CreacionDebinWSInDTO();
    	creacionDebinWS.setCanal("E");
    	creacionDebinWS.setNroDocumento("21677513");
    	creacionDebinWS.setCuitDestinatario("27216775134");
    	creacionDebinWS.setCategoriaLimite(0);
    	creacionDebinWS.setPermitePreautorizado(false);
    	creacionDebinWS.setTitularDestinatario("Silvina Luque");
    	creacionDebinWS.setCbuDestinatario("0720033520000000819954");
    	creacionDebinWS.setAliasDestinatario("Alias");
    	creacionDebinWS.setConcepto("02");
    	creacionDebinWS.setDescripcion("Descripcion");
    	creacionDebinWS.setFechaVencimiento("11/02/2019");
    	creacionDebinWS.setMoneda("032");
    	creacionDebinWS.setCbuOrigen("0720033520000000819975");
    	creacionDebinWS.setTipoDocumento("N");
    	creacionDebinWS.setImporte("1200");
        
 
    	DAOException error = new DAOException();
    	error.setErrorCode("099");
    	
        
    	when(solicitudesDebinWSDAO.solicitarDebinV3(Matchers.any(RequestNuevoDebinV3.class))).thenThrow(error);
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<CreacionDebinWSOutDTO> respuesta = solicitudesDebinWSBO.solicitarDebin(creacionDebinWS);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void solicitarDebinErrorDAOException() throws DAOException {
       	CreacionDebinWSInDTO creacionDebinWS = new CreacionDebinWSInDTO();
    	creacionDebinWS.setCanal("E");
    	creacionDebinWS.setNroDocumento("21677513");
    	creacionDebinWS.setCuitDestinatario("27216775134");
    	creacionDebinWS.setCategoriaLimite(0);
    	creacionDebinWS.setPermitePreautorizado(false);
    	creacionDebinWS.setTitularDestinatario("Silvina Luque");
    	creacionDebinWS.setCbuDestinatario("0720033520000000819954");
    	creacionDebinWS.setAliasDestinatario("Alias");
    	creacionDebinWS.setConcepto("02");
    	creacionDebinWS.setDescripcion("Descripcion");
    	creacionDebinWS.setFechaVencimiento("11/02/2019");
    	creacionDebinWS.setMoneda("032");
    	creacionDebinWS.setCbuOrigen("0720033520000000819975");
    	creacionDebinWS.setTipoDocumento("N");
    	creacionDebinWS.setImporte("1200");
        
    	when(solicitudesDebinWSDAO.solicitarDebinV3(Matchers.any(RequestNuevoDebinV3.class))).thenThrow(new DAOException());
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<CreacionDebinWSOutDTO> respuesta = solicitudesDebinWSBO.solicitarDebin(creacionDebinWS);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void solicitarDebinErrorImporteConvertException() throws DAOException {
       	CreacionDebinWSInDTO creacionDebinWS = new CreacionDebinWSInDTO();
    	creacionDebinWS.setCanal("E");
    	creacionDebinWS.setNroDocumento("21677513");
    	creacionDebinWS.setCuitDestinatario("27216775134");
    	creacionDebinWS.setCategoriaLimite(0);
    	creacionDebinWS.setPermitePreautorizado(false);
    	creacionDebinWS.setTitularDestinatario("Silvina Luque");
    	creacionDebinWS.setCbuDestinatario("0720033520000000819954");
    	creacionDebinWS.setAliasDestinatario("Alias");
    	creacionDebinWS.setConcepto("02");
    	creacionDebinWS.setDescripcion("Descripcion");
    	creacionDebinWS.setFechaVencimiento("11/02/2019");
    	creacionDebinWS.setMoneda("032");
    	creacionDebinWS.setCbuOrigen("0720033520000000819975");
    	creacionDebinWS.setTipoDocumento("N");
        
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<CreacionDebinWSOutDTO> respuesta = solicitudesDebinWSBO.solicitarDebin(creacionDebinWS);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void solicitarDebinErrorParseException() {
       	CreacionDebinWSInDTO creacionDebinWS = new CreacionDebinWSInDTO();
    	creacionDebinWS.setCanal("E");
    	creacionDebinWS.setNroDocumento("21677513");
    	creacionDebinWS.setCuitDestinatario("27216775134");
    	creacionDebinWS.setCategoriaLimite(0);
    	creacionDebinWS.setPermitePreautorizado(false);
    	creacionDebinWS.setTitularDestinatario("Silvina Luque");
    	creacionDebinWS.setCbuDestinatario("0720033520000000819954");
    	creacionDebinWS.setAliasDestinatario("Alias");
    	creacionDebinWS.setConcepto("02");
    	creacionDebinWS.setDescripcion("Descripcion");
    	creacionDebinWS.setFechaVencimiento("11-02-2019");
    	creacionDebinWS.setMoneda("032");
    	creacionDebinWS.setCbuOrigen("0720033520000000819975");
    	creacionDebinWS.setTipoDocumento("N");
        
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<CreacionDebinWSOutDTO> respuesta = solicitudesDebinWSBO.solicitarDebin(creacionDebinWS);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
//    @Test
//    public void solicitarDebinErrorDatatypeConfigurationException() throws DAOException {
//       	CreacionDebinWSInDTO creacionDebinWS = new CreacionDebinWSInDTO();
//    	creacionDebinWS.setCanal("E");
//    	creacionDebinWS.setNroDocumento("21677513");
//    	creacionDebinWS.setCuitDestinatario("27216775134");
//    	creacionDebinWS.setCategoriaLimite(0);
//    	creacionDebinWS.setPermitePreautorizado(false);
//    	creacionDebinWS.setTitularDestinatario("Silvina Luque");
//    	creacionDebinWS.setCbuDestinatario("0720033520000000819954");
//    	creacionDebinWS.setAliasDestinatario("Alias");
//    	creacionDebinWS.setConcepto("02");
//    	creacionDebinWS.setDescripcion("Descripcion");
//    	creacionDebinWS.setFechaVencimiento("13/32/4");
//    	creacionDebinWS.setMoneda("032");
//    	creacionDebinWS.setCbuOrigen("0720033520000000819975");
//    	creacionDebinWS.setTipoDocumento("N");
//    	creacionDebinWS.setImporte("1200");
//    	
//    	when(sesionCliente.getCliente()).thenReturn(cliente);
//    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
//    	
//    	Respuesta<CreacionDebinWSOutDTO> respuesta = solicitudesDebinWSBO.solicitarDebin(creacionDebinWS);
//    	
//    	Assert.assertNotNull(respuesta);
//    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
//    }
    @Test
    public void validarAliasDebinOK() throws DAOException {
    	ValidarAliasInDTO validarAliasIn = new ValidarAliasInDTO();
    	validarAliasIn.setAlias("Alias");
    	validarAliasIn.setUserAgent("Google Chrome");
    	Cuenta cuenta = cliente.getCuenta("0720033520000000819954");
    	cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
    	validarAliasIn.setCuenta(cuenta);
    	
    	ConsultarDatosTitularidadExtendidoResponse datosTitularidad = new ConsultarDatosTitularidadExtendidoResponse();
    	TitularidadExtendido titularidadExtendido = new TitularidadExtendido();
    	titularidadExtendido.setNombreTitular("Jorge");
    	titularidadExtendido.setNombreBanco("Banco ITAU");
    	
    	CuentaDTO cuentaDTO = new CuentaDTO();
    	cuentaDTO.setDescripcion("descripcion");
    	cuentaDTO.setNumeroCBU("0720033520000000819951");
    	MonedaDTO moneda = new MonedaDTO();
    	moneda.setCodigo("1");
    	moneda.setDescripcion("PESOS");
    	cuentaDTO.setMoneda(moneda);
    	
    	titularidadExtendido.setCtaDestino(cuentaDTO);
    	titularidadExtendido.setCuits(Arrays.asList("27216775134"));
    	
    	datosTitularidad.setTitularidadExtendido(titularidadExtendido);
    	    	
    	when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class))).thenReturn(datosTitularidad);
    	
    	Respuesta<ValidarAliasOutDTO> respuesta = solicitudesDebinWSBO.validarAliasDebin(validarAliasIn);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarAliasDebinErrorAliasCorrespondienteCuentaPropiaUnicaException() throws DAOException {
    	ValidarAliasInDTO validarAliasIn = new ValidarAliasInDTO();
    	validarAliasIn.setAlias("Alias");
    	validarAliasIn.setUserAgent("Google Chrome");
    	Cuenta cuenta = cliente.getCuenta("0720033520000000819954");
    	cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
    	validarAliasIn.setCuenta(cuenta);
    	
    	ConsultarDatosTitularidadExtendidoResponse datosTitularidad = new ConsultarDatosTitularidadExtendidoResponse();
    	TitularidadExtendido titularidadExtendido = new TitularidadExtendido();
    	titularidadExtendido.setNombreTitular("Jorge");
    	titularidadExtendido.setNombreBanco("Banco ITAU");
    	
    	CuentaDTO cuentaDTO = new CuentaDTO();
    	cuentaDTO.setDescripcion("descripcion");
    	cuentaDTO.setNumeroCBU("0720033520000000819954");
    	MonedaDTO moneda = new MonedaDTO();
    	moneda.setCodigo("1");
    	moneda.setDescripcion("PESOS");
    	cuentaDTO.setMoneda(moneda);
    	
    	titularidadExtendido.setCtaDestino(cuentaDTO);
    	titularidadExtendido.setCuits(Arrays.asList("27216775134"));
    	
    	datosTitularidad.setTitularidadExtendido(titularidadExtendido);
    	    	
    	when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class))).thenReturn(datosTitularidad);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ValidarAliasOutDTO> respuesta = solicitudesDebinWSBO.validarAliasDebin(validarAliasIn);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarAliasDebinErrorCuentaInactiva() throws DAOException {
    	ValidarAliasInDTO validarAliasIn = new ValidarAliasInDTO();
    	validarAliasIn.setAlias("Alias");
    	validarAliasIn.setUserAgent("Google Chrome");
    	Cuenta cuenta = cliente.getCuenta("0720033520000000819954");
    	cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
    	validarAliasIn.setCuenta(cuenta);
    	
    	ConsultarDatosTitularidadExtendidoResponse datosTitularidad = new ConsultarDatosTitularidadExtendidoResponse();
    	datosTitularidad.setCodigo("0160");
    	
    	
    	when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class))).thenReturn(datosTitularidad);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ValidarAliasOutDTO> respuesta = solicitudesDebinWSBO.validarAliasDebin(validarAliasIn);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarAliasDebinErrorAliasEliminado() throws DAOException {
    	ValidarAliasInDTO validarAliasIn = new ValidarAliasInDTO();
    	validarAliasIn.setAlias("Alias");
    	validarAliasIn.setUserAgent("Google Chrome");
    	Cuenta cuenta = cliente.getCuenta("0720033520000000819954");
    	cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
    	validarAliasIn.setCuenta(cuenta);
    	
    	ConsultarDatosTitularidadExtendidoResponse datosTitularidad = new ConsultarDatosTitularidadExtendidoResponse();
    	datosTitularidad.setCodigo("0190");
    	
    	
    	when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class))).thenReturn(datosTitularidad);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ValidarAliasOutDTO> respuesta = solicitudesDebinWSBO.validarAliasDebin(validarAliasIn);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarAliasDebinErrorMonedaInvalida() throws DAOException {
    	ValidarAliasInDTO validarAliasIn = new ValidarAliasInDTO();
    	validarAliasIn.setAlias("Alias");
    	validarAliasIn.setUserAgent("Google Chrome");
    	Cuenta cuenta = cliente.getCuenta("0720033520000000819954");
    	cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
    	validarAliasIn.setCuenta(cuenta);
    	
    	ConsultarDatosTitularidadExtendidoResponse datosTitularidad = new ConsultarDatosTitularidadExtendidoResponse();
    	datosTitularidad.setCodigo("36");
    	
    	
    	when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class))).thenReturn(datosTitularidad);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ValidarAliasOutDTO> respuesta = solicitudesDebinWSBO.validarAliasDebin(validarAliasIn);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarAliasDebinErrorAliasInexistente() throws DAOException {
    	ValidarAliasInDTO validarAliasIn = new ValidarAliasInDTO();
    	validarAliasIn.setAlias("Alias");
    	validarAliasIn.setUserAgent("Google Chrome");
    	Cuenta cuenta = cliente.getCuenta("0720033520000000819954");
    	cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
    	validarAliasIn.setCuenta(cuenta);
    	
    	ConsultarDatosTitularidadExtendidoResponse datosTitularidad = new ConsultarDatosTitularidadExtendidoResponse();
    	datosTitularidad.setCodigo("0110");
    	
    	
    	when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class))).thenReturn(datosTitularidad);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ValidarAliasOutDTO> respuesta = solicitudesDebinWSBO.validarAliasDebin(validarAliasIn);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarAliasDebinErrorGenerico() throws DAOException {
    	ValidarAliasInDTO validarAliasIn = new ValidarAliasInDTO();
    	validarAliasIn.setAlias("Alias");
    	validarAliasIn.setUserAgent("Google Chrome");
    	Cuenta cuenta = cliente.getCuenta("0720033520000000819954");
    	cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
    	validarAliasIn.setCuenta(cuenta);
    	
    	ConsultarDatosTitularidadExtendidoResponse datosTitularidad = new ConsultarDatosTitularidadExtendidoResponse();
    	datosTitularidad.setCodigo("9999");
    	
    	
    	when(sesionCliente.getIpCliente()).thenReturn("127.0.0.1");
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class))).thenReturn(datosTitularidad);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ValidarAliasOutDTO> respuesta = solicitudesDebinWSBO.validarAliasDebin(validarAliasIn);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
}

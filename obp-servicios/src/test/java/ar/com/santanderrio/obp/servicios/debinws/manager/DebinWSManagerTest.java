package ar.com.santanderrio.obp.servicios.debinws.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.debinrecurrente.bo.EstadoRecurrenciaDebinEnum;
import ar.com.santanderrio.obp.servicios.debinws.common.EstadoDebinEnum;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSBO;
import ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSSolicitudesBO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDebinWSInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDetalleDebinWSInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDetalleDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.DebinWSEliminarOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.RechazarDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.manager.impl.DebinWSManagerImpl;
import ar.com.santanderrio.obp.servicios.debinws.mock.DebinWSMock;
import ar.com.santanderrio.obp.servicios.debinws.view.ConfiguracionGrillaDebinWSView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDebinWSInView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDebinWSOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDetalleDebinWSInView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDetalleDebinWSOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.DebinWSEliminarOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.PagarDebinWSView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;


@RunWith(MockitoJUnitRunner.class)
public class DebinWSManagerTest {

    /** The debinWsManager. */
    @InjectMocks
    private DebinWSManager debinWsManager = new DebinWSManagerImpl();

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The debinWsBO. */
    @Mock
    private DebinWSBO debinWsBO;

    /** The mensajeBO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The scompBO. */
    @Mock
    private ScompBO scompBO;

    /** The solicitudes DebinWSBO. */
    @Mock
    private DebinWSSolicitudesBO solicitudesDebinWSBO;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje Manager. */
    @Mock
    private MensajeManager mensajeManager;

    /** The cuenta Manager. */
    @Mock
    private CuentaManager cuentaManager;

    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The sesion Parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The sesion Cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The cliente. */
    private Cliente cliente = new Cliente();

    /** The autentificacion Manager. */
    @Mock
    private AutentificacionManager autentificacionManager;

    @Spy
    @InjectMocks
    private DesafioOperacionRSA<PagarDebinWSView> pagarDebinWSViewDesafioOperacionRSA;

    /** The selectores BO. */
    @Mock
    private DatosSelectoresBO selectoresBO;

    /** The cliente manager. */
    @Mock
    private ClienteManager clienteManager;

    Mensaje mensaje = new Mensaje();

    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
        MockitoAnnotations.initMocks(this);
        cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("0720033520000000819954");
        cuenta.setAlias("asdasdasd");
        cuenta.setNroSucursal("02");
        cuenta.setTipoCuenta("2");
        cuenta.setNroCuentaProducto("3423423423432");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuentas.add(cuenta);
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
        cliente.setDni("21677513");
        cliente.setNumeroCUILCUIT("27216775134");
        ReflectionTestUtils.setField(debinWsManager, "valorDesafioPagarDebinWS", "3");
    }
    
    /**
     * consultaDebinOkTest
     * @throws IllegalAccessException 
     */
    @Test
    public void consultaDebinOkTest() throws IllegalAccessException {
        Respuesta<ConsultaDebinWSOutDTO> respuestaBO =  new Respuesta<ConsultaDebinWSOutDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(DebinWSMock.obtenerRespuestaConsultaDebinBO());
        
        FieldUtils.writeDeclaredField(debinWsManager, "conceptosStr", "01-Alquileres-1-ALQ|02-Cuotas-2-CUO|03-Expensas-3-EXP|04-Facturas-4-FAC|05-Préstamos-5-PRE|06-Seguros-6-SEG|07-Honorarios-7-HON|08-Haberes-8-HAB|09-Varios-9-VAR|M-Plazo Fijo Web-M-PLF", true);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ConsultaDebinWSOutView>respuesta = new Respuesta<ConsultaDebinWSOutView>();
        when(debinWsBO.consultaDebin(Matchers.any(ConsultaDebinWSInDTO.class))).thenReturn(respuestaBO);
        ConsultaDebinWSInView consultaDebinInView = new ConsultaDebinWSInView();
        consultaDebinInView.setConsultaDesdeRecibidos(true);
        
        respuesta =   debinWsManager.consultaDebin(consultaDebinInView);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    /**
     * consultaDebinWarningTest
     * @throws IllegalAccessException 
     */
    @Test
    public void consultaDebinWarningTest() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(debinWsManager, "conceptosStr", "01-Alquileres-1-ALQ|02-Cuotas-2-CUO|03-Expensas-3-EXP|04-Facturas-4-FAC|05-Préstamos-5-PRE|06-Seguros-6-SEG|07-Honorarios-7-HON|08-Haberes-8-HAB|09-Varios-9-VAR|M-Plazo Fijo Web-M-PLF", true);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ConsultaDebinWSOutDTO> respuestaBO =  new Respuesta<ConsultaDebinWSOutDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaBO.setRespuesta(DebinWSMock.obtenerRespuestaConsultaDebinBO());
        List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.DEBINWS_WARNING_LISTA_ERROR_PARCIAL.getDescripcion());
        itemMensajeRespuesta.add(item );
        respuestaBO.setItemMensajeRespuesta(itemMensajeRespuesta );
        Respuesta<ConsultaDebinWSOutView>respuesta = new Respuesta<ConsultaDebinWSOutView>();
        when(debinWsBO.consultaDebin(Matchers.any(ConsultaDebinWSInDTO.class))).thenReturn(respuestaBO);
        ConsultaDebinWSInView consultaDebinInView = new ConsultaDebinWSInView();
        consultaDebinInView.setConsultaDesdeRecibidos(true);
        respuesta =   debinWsManager.consultaDebin(consultaDebinInView );
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
    }   
    
    /**
     * consultaDebinErrorTest
     */
    @Test
    public void consultaDebinErrorTest() {
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ConsultaDebinWSOutDTO> respuestaBO =  new Respuesta<ConsultaDebinWSOutDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaBO.setRespuesta(DebinWSMock.obtenerRespuestaConsultaDebinBO());
        Respuesta<ConsultaDebinWSOutView>respuesta = new Respuesta<ConsultaDebinWSOutView>();
        when(debinWsBO.consultaDebin(Matchers.any(ConsultaDebinWSInDTO.class))).thenReturn(respuestaBO);
        ConsultaDebinWSInView consultaDebinInView = new ConsultaDebinWSInView();
        consultaDebinInView.setConsultaDesdeRecibidos(true);
        respuesta =   debinWsManager.consultaDebin(consultaDebinInView );
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }   
 
    /**
     * consultaDetalleOkTest
     * @throws IllegalAccessException 
     */
    @Test
    public void consultaDetalleOkTest() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(debinWsManager, "conceptosStr", "01-Alquileres-1-ALQ|02-Cuotas-2-CUO|03-Expensas-3-EXP|04-Facturas-4-FAC|05-Préstamos-5-PRE|06-Seguros-6-SEG|07-Honorarios-7-HON|08-Haberes-8-HAB|09-Varios-9-VAR|M-Plazo Fijo Web-M-PLF", true);
        ConsultaDetalleDebinWSInView consultaDetalleDebinWSInView = new ConsultaDetalleDebinWSInView();
        consultaDetalleDebinWSInView.setDebinId("123");
        Respuesta<ConsultaDetalleDebinWSOutDTO> respuestaBO =  new Respuesta<ConsultaDetalleDebinWSOutDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(DebinWSMock.obtenerDetalleOutBO());
        List<String> idDebinRecurrencias = new ArrayList<String>();
        idDebinRecurrencias.add("123");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getIdDebinesRecurrencias()).thenReturn(idDebinRecurrencias);
        when(debinWsBO.consultaDetalleDebin(Matchers.any(ConsultaDetalleDebinWSInDTO.class))).thenReturn(respuestaBO );
        Respuesta<ConsultaDetalleDebinWSOutView> respuesta = debinWsManager.consultaDetalleDebin(consultaDetalleDebinWSInView);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    /**
     * consultaDetalleErrorIdRecurrencias
     * @throws IllegalAccessException 
     */
    @Test
    public void consultaDetalleErrorIdRecurrencias() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(debinWsManager, "conceptosStr", "01-Alquileres-1-ALQ|02-Cuotas-2-CUO|03-Expensas-3-EXP|04-Facturas-4-FAC|05-Préstamos-5-PRE|06-Seguros-6-SEG|07-Honorarios-7-HON|08-Haberes-8-HAB|09-Varios-9-VAR|M-Plazo Fijo Web-M-PLF", true);
        ConsultaDetalleDebinWSInView consultaDetalleDebinWSInView = new ConsultaDetalleDebinWSInView();
        consultaDetalleDebinWSInView.setDebinId("123");

        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        
        Respuesta<ConsultaDetalleDebinWSOutView> respuesta = debinWsManager.consultaDetalleDebin(consultaDetalleDebinWSInView);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    /**
     * consultaDetalleOkDesdeRecibidosTest
     * @throws IllegalAccessException 
     */
    @Test
    public void consultaDetalleOkDesdeRecibidosTest() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(debinWsManager, "conceptosStr", "01-Alquileres-1-ALQ|02-Cuotas-2-CUO|03-Expensas-3-EXP|04-Facturas-4-FAC|05-Préstamos-5-PRE|06-Seguros-6-SEG|07-Honorarios-7-HON|08-Haberes-8-HAB|09-Varios-9-VAR|M-Plazo Fijo Web-M-PLF", true);
        ConsultaDetalleDebinWSInView consultaDetalleDebinWSInView = new ConsultaDetalleDebinWSInView();
        consultaDetalleDebinWSInView.setDebinId("123");
        consultaDetalleDebinWSInView.setConsultaDesdeRecibidos(true);
        Respuesta<ConsultaDetalleDebinWSOutDTO> respuestaBO =  new Respuesta<ConsultaDetalleDebinWSOutDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(DebinWSMock.obtenerDetalleOutBO());
        List<String> idDebinRecurrencias = new ArrayList<String>();
        idDebinRecurrencias.add("123");
        when(sesionParametros.getIdDebinesRecurrencias()).thenReturn(idDebinRecurrencias);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(debinWsBO.consultaDetalleDebin(Matchers.any(ConsultaDetalleDebinWSInDTO.class))).thenReturn(respuestaBO );
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        Respuesta<ConsultaDetalleDebinWSOutView> respuesta = debinWsManager.consultaDetalleDebin(consultaDetalleDebinWSInView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    /**
     * consultaDetalleErrorTest
     * @throws IllegalAccessException 
     */
    @Test
    public void consultaDetalleErrorTest() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(debinWsManager, "conceptosStr", "01-Alquileres-1-ALQ|02-Cuotas-2-CUO|03-Expensas-3-EXP|04-Facturas-4-FAC|05-Préstamos-5-PRE|06-Seguros-6-SEG|07-Honorarios-7-HON|08-Haberes-8-HAB|09-Varios-9-VAR|M-Plazo Fijo Web-M-PLF", true);
        ConsultaDetalleDebinWSInView consultaDetalleDebinWSInView = new ConsultaDetalleDebinWSInView();
        consultaDetalleDebinWSInView.setDebinId("123");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        Respuesta<ConsultaDetalleDebinWSOutDTO> respuestaBO =  new Respuesta<ConsultaDetalleDebinWSOutDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaBO.setRespuesta(DebinWSMock.obtenerDetalleOutBO());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(debinWsBO.consultaDetalleDebin(Matchers.any(ConsultaDetalleDebinWSInDTO.class))).thenReturn(respuestaBO );
        Respuesta<ConsultaDetalleDebinWSOutView> respuesta = debinWsManager.consultaDetalleDebin(consultaDetalleDebinWSInView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    @Test
    public void eliminarDebinOK() {
    	DebinWSEliminarOutDTO debinWsEliminarOutDTO = new DebinWSEliminarOutDTO();
    	
    	debinWsEliminarOutDTO.setFechaComprobante("29/09/1983");
    	debinWsEliminarOutDTO.setNumeroComprobante("4324");
    	Respuesta<DebinWSEliminarOutDTO> responseBO = new Respuesta<DebinWSEliminarOutDTO>();
    	responseBO.setEstadoRespuesta(EstadoRespuesta.OK);
    	responseBO.setRespuesta(debinWsEliminarOutDTO);
    	
    	when(debinWsBO.eliminarDebin()).thenReturn(responseBO);
    	when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
    	
    	Respuesta<DebinWSEliminarOutView> respuesta = debinWsManager.eliminarDebin();
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    @Test
    public void eliminarDebinErrorBOReintentos() {
    	DebinWSEliminarOutDTO debinWsEliminarOutDTO = new DebinWSEliminarOutDTO();
    	
    	debinWsEliminarOutDTO.setFechaComprobante("29/09/1983");
    	debinWsEliminarOutDTO.setNumeroComprobante("4324");
    	Respuesta<DebinWSEliminarOutDTO> responseBO = new Respuesta<DebinWSEliminarOutDTO>();
    	responseBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
    	ItemMensajeRespuesta mensajeRespuesta = new ItemMensajeRespuesta();
    	mensajeRespuesta.setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
    	itemMensajeRespuesta.add(mensajeRespuesta);
		responseBO.setItemMensajeRespuesta(itemMensajeRespuesta);
    	responseBO.setRespuesta(debinWsEliminarOutDTO);
    	
    	when(debinWsBO.eliminarDebin()).thenReturn(responseBO);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
    	
    	Respuesta<DebinWSEliminarOutView> respuesta = debinWsManager.eliminarDebin();
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    @Test
    public void eliminarDebinErrorBODebinWSEliminar() {
    	DebinWSEliminarOutDTO debinWsEliminarOutDTO = new DebinWSEliminarOutDTO();
    	
    	debinWsEliminarOutDTO.setFechaComprobante("29/09/1983");
    	debinWsEliminarOutDTO.setNumeroComprobante("4324");
    	Respuesta<DebinWSEliminarOutDTO> responseBO = new Respuesta<DebinWSEliminarOutDTO>();
    	responseBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
    	ItemMensajeRespuesta mensajeRespuesta = new ItemMensajeRespuesta();
    	mensajeRespuesta.setTipoError(TipoError.DEBINWS_ERROR_ELIMINAR.getDescripcion());
    	itemMensajeRespuesta.add(mensajeRespuesta);
		responseBO.setItemMensajeRespuesta(itemMensajeRespuesta);
    	responseBO.setRespuesta(debinWsEliminarOutDTO);
    	
    	when(debinWsBO.eliminarDebin()).thenReturn(responseBO);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
    	
    	Respuesta<DebinWSEliminarOutView> respuesta = debinWsManager.eliminarDebin();
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    @Test
    public void rechazarDebinOk() {
    	Respuesta<RechazarDebinWSOutDTO> rtaBO = new Respuesta<RechazarDebinWSOutDTO>();
    	rtaBO.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	RechazarDebinWSOutDTO rechazarDebinOutDTO = new RechazarDebinWSOutDTO();
    	rechazarDebinOutDTO.setFechaComprobante("10/05/2019");
    	rechazarDebinOutDTO.setNumeroComprobante("1234");
    	rtaBO.setRespuesta(rechazarDebinOutDTO);
    	
    	when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
    	when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	when(debinWsBO.rechazarDebin()).thenReturn(rtaBO);
    	Respuesta<RechazarDebinWSOutDTO> respuesta = debinWsManager.rechazarDebin();
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    @Test
    public void rechazarDebinErrorBOReintentos() {
    	Respuesta<RechazarDebinWSOutDTO> rtaBO = new Respuesta<RechazarDebinWSOutDTO>();
    	rtaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	RechazarDebinWSOutDTO rechazarDebinOutDTO = new RechazarDebinWSOutDTO();
    	rechazarDebinOutDTO.setFechaComprobante("10/05/2019");
    	rechazarDebinOutDTO.setNumeroComprobante("1234");
    	rtaBO.setRespuesta(rechazarDebinOutDTO);
    	
    	List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
    	ItemMensajeRespuesta mensajeRespuesta = new ItemMensajeRespuesta();
    	mensajeRespuesta.setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
    	itemMensajeRespuesta.add(mensajeRespuesta);
    	rtaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
    	
    	when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	when(debinWsBO.rechazarDebin()).thenReturn(rtaBO);
    	Respuesta<RechazarDebinWSOutDTO> respuesta = debinWsManager.rechazarDebin();
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    @Test
    public void rechazarDebinErrorBORechazaDebin() {
    	Respuesta<RechazarDebinWSOutDTO> rtaBO = new Respuesta<RechazarDebinWSOutDTO>();
    	rtaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	RechazarDebinWSOutDTO rechazarDebinOutDTO = new RechazarDebinWSOutDTO();
    	rechazarDebinOutDTO.setFechaComprobante("10/05/2019");
    	rechazarDebinOutDTO.setNumeroComprobante("1234");
    	rtaBO.setRespuesta(rechazarDebinOutDTO);
    	
    	List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
    	ItemMensajeRespuesta mensajeRespuesta = new ItemMensajeRespuesta();
    	mensajeRespuesta.setTipoError(TipoError.DEBINWS_RECHAZAR_DEBIN_ERROR.getDescripcion());
    	itemMensajeRespuesta.add(mensajeRespuesta);
    	rtaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
    	
    	when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	when(debinWsBO.rechazarDebin()).thenReturn(rtaBO);
    	when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<RechazarDebinWSOutDTO> respuesta = debinWsManager.rechazarDebin();
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    	
    }
    
    @Test
    public void pagarDebinOK() {
    	Respuesta<PagarDebinWSView> rtaBO = new Respuesta<PagarDebinWSView>();
    	rtaBO.setEstadoRespuesta(EstadoRespuesta.OK);
    	PagarDebinWSView pagarDebinWSView = new PagarDebinWSView();
    	rtaBO.setRespuesta(pagarDebinWSView);
    	
    	AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        pagarDebinWSView.setDesafio(autentificacionDTO);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.OK);

        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacionDTO);
    	when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	when(debinWsBO.pagarDebin(Matchers.any(PagarDebinWSView.class))).thenReturn(rtaBO);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	when(sesionCliente.getTieneTokenRSA()).thenReturn(Boolean.TRUE);
    	
    	Respuesta<PagarDebinWSView> respuesta = debinWsManager.pagarDebin(pagarDebinWSView);

    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    @Test
    public void pagarDebinErrorBOReintentos() {
    	Respuesta<PagarDebinWSView> rtaBO = new Respuesta<PagarDebinWSView>();
    	rtaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	PagarDebinWSView pagarDebinWSView = new PagarDebinWSView();
    	
    	List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
    	ItemMensajeRespuesta mensajeRespuesta = new ItemMensajeRespuesta();
    	mensajeRespuesta.setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
    	itemMensajeRespuesta.add(mensajeRespuesta);
    	rtaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
    	
    	AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        pagarDebinWSView.setDesafio(autentificacionDTO);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.OK);

        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacionDTO);
    	when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	when(debinWsBO.pagarDebin(Matchers.any(PagarDebinWSView.class))).thenReturn(rtaBO);
    	
    	Respuesta<PagarDebinWSView> respuesta = debinWsManager.pagarDebin(pagarDebinWSView);

    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    @Test
    public void pagarDebinErrorBOAdhesion() {
    	Respuesta<PagarDebinWSView> rtaBO = new Respuesta<PagarDebinWSView>();
    	rtaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	PagarDebinWSView pagarDebinWSView = new PagarDebinWSView();
    	
    	List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
    	ItemMensajeRespuesta mensajeRespuesta = new ItemMensajeRespuesta();
    	mensajeRespuesta.setTipoError(TipoError.DEBINWS_ERROR_ADHESION_COMPRADOR.getDescripcion());
    	itemMensajeRespuesta.add(mensajeRespuesta);
    	rtaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
    	
    	AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        pagarDebinWSView.setDesafio(autentificacionDTO);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.OK);

        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacionDTO);
    	when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	when(debinWsBO.pagarDebin(Matchers.any(PagarDebinWSView.class))).thenReturn(rtaBO);
    	
    	Respuesta<PagarDebinWSView> respuesta = debinWsManager.pagarDebin(pagarDebinWSView);

    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    @Test
    public void pagarDebinErrorBOGenerico() {
    	Respuesta<PagarDebinWSView> rtaBO = new Respuesta<PagarDebinWSView>();
    	rtaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	PagarDebinWSView pagarDebinWSView = new PagarDebinWSView();

    	List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
    	ItemMensajeRespuesta mensajeRespuesta = new ItemMensajeRespuesta();
    	mensajeRespuesta.setTipoError(TipoError.DEBINWS_ERROR_PAGAR.getDescripcion());
    	itemMensajeRespuesta.add(mensajeRespuesta);
    	rtaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
    	
    	AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        pagarDebinWSView.setDesafio(autentificacionDTO);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.OK);

        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacionDTO);
    	when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	when(debinWsBO.pagarDebin(Matchers.any(PagarDebinWSView.class))).thenReturn(rtaBO);
    	
    	Respuesta<PagarDebinWSView> respuesta = debinWsManager.pagarDebin(pagarDebinWSView);

    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    @Test
    public void configuracionGrillaDebinWSViewOK() {
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	Respuesta<ConfiguracionGrillaDebinWSView> respuesta = debinWsManager.configuracionGrillaDebinWSView();

    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    	ConfiguracionGrillaDebinWSView configuracionGrillaDebinWSView = respuesta.getRespuesta();


        checkResponseEstadoDEBIN(configuracionGrillaDebinWSView);

        checkResponseEstadoRecurrencia(configuracionGrillaDebinWSView);



    }

    private void checkResponseEstadoDEBIN(ConfiguracionGrillaDebinWSView configuracionGrillaDebinWSView) {

        Assert.assertEquals(12, configuracionGrillaDebinWSView.getListaEstadoDebin().size());

        Assert.assertEquals(EstadoDebinEnum.INICIADO_Y_ACREDITADO.getId(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(0).getId());
        Assert.assertEquals(EstadoDebinEnum.INICIADO_Y_ACREDITADO.getDescView(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(0).getDescripcion());

        Assert.assertEquals(EstadoDebinEnum.INICIADO.getId(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(1).getId());
        Assert.assertEquals(EstadoDebinEnum.INICIADO.getDescView(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(1).getDescripcion());

        Assert.assertEquals(EstadoDebinEnum.VENCIDO.getId(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(2).getId());
        Assert.assertEquals(EstadoDebinEnum.VENCIDO.getDescView(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(2).getDescripcion());

        Assert.assertEquals(EstadoDebinEnum.EN_CURSO.getId(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(3).getId());
        Assert.assertEquals(EstadoDebinEnum.EN_CURSO.getDescView(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(3).getDescripcion());

        Assert.assertEquals(EstadoDebinEnum.ACREDITADO.getId(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(4).getId());
        Assert.assertEquals(EstadoDebinEnum.ACREDITADO.getDescView(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(4).getDescripcion());

        Assert.assertEquals(EstadoDebinEnum.ERROR_DATOS.getId(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(5).getId());
        Assert.assertEquals(EstadoDebinEnum.ERROR_DATOS.getDescView(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(5).getDescripcion());

        Assert.assertEquals(EstadoDebinEnum.ERROR_DEBITO.getId(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(6).getId());
        Assert.assertEquals(EstadoDebinEnum.ERROR_DEBITO.getDescView(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(6).getDescripcion());

        Assert.assertEquals(EstadoDebinEnum.SIN_SALDO.getId(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(7).getId());
        Assert.assertEquals(EstadoDebinEnum.SIN_SALDO.getDescView(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(7).getDescripcion());

        Assert.assertEquals(EstadoDebinEnum.RECHAZO_CLIENTE.getId(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(8).getId());
        Assert.assertEquals(EstadoDebinEnum.RECHAZO_CLIENTE.getDescView(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(8).getDescripcion());
        Assert.assertEquals(EstadoDebinEnum.ELIMINADO.getDescView(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(9).getDescripcion());

        Assert.assertEquals(EstadoDebinEnum.ERROR_ACREDITACION.getDescView(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(10).getDescripcion());

        Assert.assertEquals(EstadoDebinEnum.SIN_GARANTIA.getDescView(), configuracionGrillaDebinWSView.getListaEstadoDebin().get(11).getDescripcion());
    }

    private void checkResponseEstadoRecurrencia(ConfiguracionGrillaDebinWSView configuracionGrillaDebinWSView){

        Assert.assertEquals(5, configuracionGrillaDebinWSView.getListaEstadoRecurrencia().size());

        Assert.assertEquals(EstadoRecurrenciaDebinEnum.ACTIVA.getId(),configuracionGrillaDebinWSView.getListaEstadoRecurrencia().get(0).getId());
        Assert.assertEquals(EstadoRecurrenciaDebinEnum.ACTIVA.getDescView(),configuracionGrillaDebinWSView.getListaEstadoRecurrencia().get(0).getDescripcion());

        Assert.assertEquals(EstadoRecurrenciaDebinEnum.INACTIVA_DEFINITIVA.getId(),configuracionGrillaDebinWSView.getListaEstadoRecurrencia().get(1).getId());
        Assert.assertEquals(EstadoRecurrenciaDebinEnum.INACTIVA_DEFINITIVA.getDescView(),configuracionGrillaDebinWSView.getListaEstadoRecurrencia().get(1).getDescripcion());

        Assert.assertEquals(EstadoRecurrenciaDebinEnum.INACTIVA.getId(),configuracionGrillaDebinWSView.getListaEstadoRecurrencia().get(2).getId());
        Assert.assertEquals(EstadoRecurrenciaDebinEnum.INACTIVA.getDescView(),configuracionGrillaDebinWSView.getListaEstadoRecurrencia().get(2).getDescripcion());

        Assert.assertEquals(EstadoRecurrenciaDebinEnum.PENDIENTE.getId(),configuracionGrillaDebinWSView.getListaEstadoRecurrencia().get(3).getId());
        Assert.assertEquals(EstadoRecurrenciaDebinEnum.PENDIENTE.getDescView(),configuracionGrillaDebinWSView.getListaEstadoRecurrencia().get(3).getDescripcion());

        Assert.assertEquals(EstadoRecurrenciaDebinEnum.RECHAZADA.getId(),configuracionGrillaDebinWSView.getListaEstadoRecurrencia().get(4).getId());
        Assert.assertEquals(EstadoRecurrenciaDebinEnum.RECHAZADA.getDescView(),configuracionGrillaDebinWSView.getListaEstadoRecurrencia().get(4).getDescripcion());

    }
    
    @Test
    public void configuracionGrillaDebinWSViewOKIsAdvance() throws IllegalAccessException {
    	cliente.getSegmento().setDuo(true);
        cliente.getSegmento().setPyme(false);
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	Respuesta<ConfiguracionGrillaDebinWSView> respuesta = debinWsManager.configuracionGrillaDebinWSView();

    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void configuracionGrillaDebinWSViewError() throws IllegalAccessException {    	
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	Respuesta<ConfiguracionGrillaDebinWSView> respuesta = debinWsManager.configuracionGrillaDebinWSView();

    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
}

package ar.com.santanderrio.obp.servicios.debinws.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSAdhesionBO;
import ar.com.santanderrio.obp.servicios.debinws.manager.impl.DebinWSAdhesionManagerImpl;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.debinws.view.GestionarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class DebinWSAdhesionManagerTest {
	
    /** The debinWsManager. */
    @InjectMocks
    private DebinWSAdhesionManager debinWsManager = new DebinWSAdhesionManagerImpl();

    
    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;;
    
    @Mock
    private DebinWSAdhesionBO debinWsAdhesionBO;

    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private MensajeDAO mensajeDAO;
    
    @Mock
    private MensajeManager mensajeManager;
    
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Spy
    @InjectMocks
    private DesafioOperacionRSA<GestionarAdhesionDebinesView> gestionarAdhesionDebinesViewDesafioOperacionRSA;
    
    @Mock
    private SesionParametros sesionParametros;
    
    @Mock
    private SesionCliente sesionCliente;
    
    
    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    @Mock
    private AutentificacionManager autentificacionManager;
    
    private Mensaje mensaje = new Mensaje();
    
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
        cliente.setNumeroCUILCUIT("");
        ReflectionTestUtils.setField(debinWsManager, "valorDesafioGestionAdhesionDebinWS", "3");
    }
    
    @Test
    public void gestionarAdhesionDebinesOKSinRSA() {
    	GestionarAdhesionDebinesView gestionAdhesionView = new GestionarAdhesionDebinesView();
    	gestionAdhesionView.setValidaRSA(false);

    	Respuesta<GestionarAdhesionDebinesView> respuestaBO = new Respuesta<GestionarAdhesionDebinesView>();
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	when(debinWsAdhesionBO.gestionarAdhesionDebines(Matchers.any(GestionarAdhesionDebinesView.class))).thenReturn(respuestaBO);
    	
    	Respuesta<GestionarAdhesionDebinesView> respuesta = debinWsManager.gestionarAdhesionDebines(gestionAdhesionView);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }	
    	
    @Test
    public void gestionarAdhesionDebinesOKConRSA() {
    	GestionarAdhesionDebinesView gestionAdhesionView = new GestionarAdhesionDebinesView();
    	gestionAdhesionView.setValidaRSA(true);

    	Respuesta<GestionarAdhesionDebinesView> respuestaBO = new Respuesta<GestionarAdhesionDebinesView>();
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        gestionAdhesionView.setDesafio(autentificacionDTO);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.OK);

        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacionDTO);
		when(debinWsAdhesionBO.gestionarAdhesionDebines(Matchers.any(GestionarAdhesionDebinesView.class))).thenReturn(respuestaBO);
    	
    	Respuesta<GestionarAdhesionDebinesView> respuesta = debinWsManager.gestionarAdhesionDebines(gestionAdhesionView);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    @Test
    public void gestionarAdhesionDebinesWARNINGRSA() {
    	GestionarAdhesionDebinesView gestionAdhesionView = new GestionarAdhesionDebinesView();
    	gestionAdhesionView.setValidaRSA(true);
		
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        gestionAdhesionView.setDesafio(autentificacionDTO);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);

        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacionDTO);
 	
    	Respuesta<GestionarAdhesionDebinesView> respuesta = debinWsManager.gestionarAdhesionDebines(gestionAdhesionView);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
    }
    
    @Test
    public void gestionarAdhesionDebinesERRORRSA() {
    	GestionarAdhesionDebinesView gestionAdhesionView = new GestionarAdhesionDebinesView();
    	gestionAdhesionView.setValidaRSA(true);

    	AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        gestionAdhesionView.setDesafio(autentificacionDTO);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        
    	Respuesta<GestionarAdhesionDebinesView> respuesta = debinWsManager.gestionarAdhesionDebines(gestionAdhesionView);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    @Test
    public void buscarCuentasParaAdhesionDebinesOK(){    	
    	Respuesta<ConsultarAdhesionDebinesView> respuestaBO = new Respuesta<ConsultarAdhesionDebinesView>();
    	ConsultarAdhesionDebinesView consultaAdhesion = new ConsultarAdhesionDebinesView();
    	respuestaBO.setRespuesta(consultaAdhesion);
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	Cuenta cuenta = cliente.getCuenta("0720033520000000819954");
    	cuenta.setGrupoAfinidad("    3");
    	cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
    	cliente.getCuentas().add(cuenta);
    	when(debinWsAdhesionBO.buscarCuentasParaAdhesionDebines()).thenReturn(respuestaBO);
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ConsultarAdhesionDebinesView> respuesta = debinWsManager.buscarCuentasParaAdhesionDebines();
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    @Test
	@Ignore
	// LA LOGICA QUE SE INTENTA TESTEAR SE ENCUENTRA COMENTADA EN debinWsSolicitudesManager.ingresoSolicitarDebin()
    public void buscarCuentasParaAdhesionDebinesWARNING(){    	
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<ConsultarAdhesionDebinesView> respuesta = debinWsManager.buscarCuentasParaAdhesionDebines();
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
    }
    
//    @Test
//    public void buscarCuentasParaAdhesionDebinesError(){    	
//    	Respuesta<ConsultarAdhesionDebinesView> respuestaBO = new Respuesta<ConsultarAdhesionDebinesView>();
//    	ConsultarAdhesionDebinesView consultaAdhesion = new ConsultarAdhesionDebinesView();
//    	respuestaBO.setRespuesta(consultaAdhesion);
//    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
//    	
//    	Cuenta cuenta = cliente.getCuenta("0720033520000000819954");
//    	cuenta.setGrupoAfinidad("    3");
//    	cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
//    	cliente.getCuentas().add(cuenta);
//    	when(debinWsAdhesionBO.buscarCuentasParaAdhesionDebines()).thenReturn(respuestaBO);
//    	when(sesionCliente.getCliente()).thenReturn(cliente);
//    	when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
//    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
//    	
//    	Respuesta<ConsultarAdhesionDebinesView> respuesta = debinWsManager.buscarCuentasParaAdhesionDebines();
//    	
//    	Assert.assertNotNull(respuesta);
//    	Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
//    }

}

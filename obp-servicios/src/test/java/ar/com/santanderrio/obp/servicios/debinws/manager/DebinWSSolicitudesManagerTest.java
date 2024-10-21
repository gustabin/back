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

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSSolicitudesBO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentasAdheridasInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentasAdheridasOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.manager.impl.DebinWSManagerImpl;
import ar.com.santanderrio.obp.servicios.debinws.manager.impl.DebinWSSolicitudesManagerImpl;
import ar.com.santanderrio.obp.servicios.debinws.view.CuentasAdheridasDebinOutView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class DebinWSSolicitudesManagerTest{
	
	/** The debinWs Solicitudes Manager. */
	@InjectMocks
	private DebinWSSolicitudesManager debinWsSolicitudesManager = new DebinWSSolicitudesManagerImpl();
	
	
	/** The cuenta Manager. */
    @Mock
    private CuentaManager cuentaManager;
    
    /** The legal BO. */
	@Mock
    private LegalBO legalBO;
    
    /** The selectores BO. */
	@Mock
    private DatosSelectoresBO selectoresBO;
	
	/** The mensaje Manager. */
    @Mock
    private MensajeManager mensajeManager;
    
    /** The mensajeBO. */
    @Mock
    private MensajeBO mensajeBO;
	
	@Mock
    private SesionCliente sesionCliente;
	
	/** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;
	
	@Mock
	private DebinWSSolicitudesBO solicitudesDebinWSBO;
	
    /** The sesion Parametros. */
    @Mock
    private SesionParametros sesionParametros;
    
	@Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
    /** The debinWsManager. */
	@Spy
	@InjectMocks
    private DebinWSManager debinWsManager = new DebinWSManagerImpl();
	
	/** The cliente. */
    private Cliente cliente = new Cliente();
    
    /** The mensaje. */
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
    }
    
	@Test
	public void ingresoSolicitarDebinOK() {
		Cuenta cuenta = cliente.getCuenta("0720033520000000819954");
    	cuenta.setGrupoAfinidad("    3");
    	cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
    	cliente.getCuentas().add(cuenta);
		
    	Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
    	respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
    	CuentasView cuentasView= new CuentasView();
    	List<CuentasAdhesionDebitoView> listCuentaAdherida = new ArrayList<CuentasAdhesionDebitoView>();
    	CuentasAdhesionDebitoView cuentaView = new CuentasAdhesionDebitoView();
    	cuentaView.setCbu("0720033520000000819954");
    	cuentaView.setAlias("asdasdasd");
    	cuentaView.setNumero("3423423423432");
    	cuentaView.setIsCerrada(false);
    	
    	listCuentaAdherida.add(cuentaView);
    	cuentasView.setCuentas(listCuentaAdherida);
    	respuestaCuentas.setRespuesta(cuentasView);
    	
    	Respuesta<CuentasAdheridasOutDTO> respuestaBO = new Respuesta<CuentasAdheridasOutDTO>();
    	CuentasAdheridasOutDTO cuentasAdheridas = new CuentasAdheridasOutDTO();
    	List<CuentaDebinDTO> cuentas = new ArrayList<CuentaDebinDTO>();
    	
    	CuentaDebinDTO cuenta1 = new CuentaDebinDTO();
        cuenta1.setCbu("0720033520000000819954");
        cuenta1.setAlias("asdasdasd");
        cuenta1.setSucursal("02");
        cuenta1.setTipo("2");
        cuenta1.setNumero("3423423423432");
        cuenta1.setMoneda("000");
        
        cuentas.add(cuenta1);
        cuentasAdheridas.setCuentasAdheridas(cuentas);
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
    	respuestaBO.setRespuesta(cuentasAdheridas);
        
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentas);
    	when(solicitudesDebinWSBO.consultaCuentasAdheridas(Matchers.any(CuentasAdheridasInDTO.class))).thenReturn(respuestaBO);
		Respuesta<CuentasAdheridasDebinOutView> respuesta = debinWsSolicitudesManager.ingresoSolicitarDebin();
//		when
		
		
		
		Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	 @Test
	    public void ingresoSolicitarDebinErrorCuentas() {
	    	Cuenta cuenta = new Cuenta();
	    	cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
	    	cuenta.setGrupoAfinidad("    3");
	    	cliente.getCuentas().add(cuenta);
	    	
	    	Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
	    	respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.ERROR);
	    	
	    	Respuesta<CuentasAdheridasOutDTO> respuestaBO = new Respuesta<CuentasAdheridasOutDTO>();
	    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
	    	
	    	when(sesionCliente.getCliente()).thenReturn(cliente);
	    	when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentas);
	    	when(solicitudesDebinWSBO.consultaCuentasAdheridas(Matchers.any(CuentasAdheridasInDTO.class))).thenReturn(respuestaBO);
	    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
	    	
	    	Respuesta<CuentasAdheridasDebinOutView> respuesta = debinWsSolicitudesManager.ingresoSolicitarDebin();
	    	
	    	Assert.assertNotNull(respuesta);
	    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
	    }
	 
	@Test
	@Ignore
	// LA LOGICA QUE SE INTENTA TESTEAR SE ENCUENTRA COMENTADA EN debinWsSolicitudesManager.ingresoSolicitarDebin()
	public void ingresoSolicitarDebinWarningSinSoftToken() {
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
		Respuesta<CuentasAdheridasDebinOutView> respuesta = debinWsSolicitudesManager.ingresoSolicitarDebin();

		Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
	}
	
	@Test
    public void ingresoSolicitarDebinWarning() {
    	Cuenta cuenta = new Cuenta();
    	cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
    	cuenta.setGrupoAfinidad("    3");
    	cliente.getCuentas().add(cuenta);
    	
    	Respuesta<CuentasAdheridasOutDTO> respuestaBO = new Respuesta<CuentasAdheridasOutDTO>();
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
    	
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(solicitudesDebinWSBO.consultaCuentasAdheridas(Matchers.any(CuentasAdheridasInDTO.class))).thenReturn(respuestaBO);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<CuentasAdheridasDebinOutView> respuesta = debinWsSolicitudesManager.ingresoSolicitarDebin();
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }
	
	@Test
    public void ingresoSolicitarDebinError() {
    	Cuenta cuenta = new Cuenta();
    	cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
    	cuenta.setGrupoAfinidad("    3");
    	cliente.getCuentas().add(cuenta);
    	
    	Respuesta<CuentasAdheridasOutDTO> respuestaBO = new Respuesta<CuentasAdheridasOutDTO>();
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(solicitudesDebinWSBO.consultaCuentasAdheridas(Matchers.any(CuentasAdheridasInDTO.class))).thenReturn(respuestaBO);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<CuentasAdheridasDebinOutView> respuesta = debinWsSolicitudesManager.ingresoSolicitarDebin();
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }


}

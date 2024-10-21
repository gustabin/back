package ar.com.santanderrio.obp.servicios.trackingtarjetas.manager;

import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
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

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.bo.TrackingTarjetasBO;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.dto.TrackingPiezaDTO;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.manager.impl.TrackingTarjetasManagerImpl;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.mock.TrackingTarjetasObjectsMock;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.view.TrackingTarjetasView;
/**
 * TrackingTarjetasManagerTest
 * @author Silvina_Luque
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TrackingTarjetasManagerTest {

	//TODO
	// Se podria jugar con la hora en vez que con los rangos. Habria que poder mockear DateTime de alguna forma para forzarla.
	// Por el momento se deja un intervalo para que no falle
	
    /** The trackingTarjetasManager. */
    @InjectMocks
    private TrackingTarjetasManager trackingTarjetasManager = new TrackingTarjetasManagerImpl();
    
    /** The estadisticaManager*/
    @Mock
    private EstadisticaManager estadisticaManager;;
    
    /** The trackingTarjetasBO*/
    @Mock
    private TrackingTarjetasBO trackingTarjetasBO;

    /** The mensajeBO*/
    @Mock
    private MensajeBO mensajeBO;
    
    /** The mensajeDAO */
    @Mock
    private MensajeDAO mensajeDAO;
    
    /** The mensajeManager */
    @Mock
    private MensajeManager mensajeManager;
    
    /**respuestaFactory */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    /** The sesionParametros*/
    @Mock
    private SesionParametros sesionParametros;
    
    /** The sesionCliente */
    @Mock
    private SesionCliente sesionCliente;
    
    /** The consultarSucursalesBO */
    @Mock
    private ConsultarSucursalesBO consultarSucursalesBO;
    
     /** The cliente. */
    private Cliente cliente;
    
    /** The autentificacionManager*/
    @Mock
    private AutentificacionManager autentificacionManager;
    
    /** The mensaje */
    Mensaje mensaje = new Mensaje();

    /**
     * Inicio
     * @throws IllegalAccessException
     */
    @Before
    public void init()  {
        mensaje.setMensaje("EL mensaje de tracking de tarjetas");
        MockitoAnnotations.initMocks(this);
        cliente = TrackingTarjetasObjectsMock.obtenerDatosClienteOKTest();
        when(mensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(mensaje);
    }     
    
    /**
     * consultarTrackingTarjetasTest
     * @throws IllegalAccessException 
     */
    @Test
    public void consultarTrackingTarjetasTest() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(trackingTarjetasManager, "horaDesde", "00:00", true);
        FieldUtils.writeDeclaredField(trackingTarjetasManager, "horaHasta", "23:59", true);
        Respuesta<List<TrackingPiezaDTO>> respuestaBO =  new Respuesta<List<TrackingPiezaDTO>>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(TrackingTarjetasObjectsMock.obtenerRespuestaPiezasDTO());
        when(consultarSucursalesBO.consultarSucursalPorId(Matchers.anyString())).thenReturn(TrackingTarjetasObjectsMock.obtenerRespuestaSucursal());
        when(trackingTarjetasBO.obtenerTrackingTarjetas(Matchers.any(Cliente.class))).thenReturn(respuestaBO);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<TrackingTarjetasView> respuesta = trackingTarjetasManager.obtenerTrackingTarjetas();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    

    
    
    /**
     * consultarTrackingTarjetasTest
     * @throws IllegalAccessException 
     */
    @Test
    public void consultarTrackingTarjetasFueraHorarioTest() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(trackingTarjetasManager, "horaDesde", "00:00", true);
        FieldUtils.writeDeclaredField(trackingTarjetasManager, "horaHasta", "00:00", true);
        Respuesta<List<TrackingPiezaDTO>> respuestaBO =  new Respuesta<List<TrackingPiezaDTO>>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(trackingTarjetasBO.obtenerTrackingTarjetas(Matchers.any(Cliente.class))).thenReturn(respuestaBO);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<TrackingTarjetasView> respuesta = trackingTarjetasManager.obtenerTrackingTarjetas();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    /**
     * consultarTrackingTarjetasTest
     * @throws IllegalAccessException 
     */
    @Test
    public void consultarTrackingTarjetasErrorGenericoTest() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(trackingTarjetasManager, "horaDesde", "00:00", true);
        FieldUtils.writeDeclaredField(trackingTarjetasManager, "horaHasta", "23:59", true);
        Respuesta<List<TrackingPiezaDTO>> respuestaBO =  new Respuesta<List<TrackingPiezaDTO>>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(trackingTarjetasBO.obtenerTrackingTarjetas(Matchers.any(Cliente.class))).thenReturn(respuestaBO);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<TrackingTarjetasView> respuesta = trackingTarjetasManager.obtenerTrackingTarjetas();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    /**
     * consultarTrackingTarjetasTest
     * @throws IllegalAccessException 
     */
    @Test
    public void consultarTrackingTarjetasConReimpresionTest() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(trackingTarjetasManager, "horaDesde", "00:00", true);
        FieldUtils.writeDeclaredField(trackingTarjetasManager, "horaHasta", "23:59", true);
        Respuesta<List<TrackingPiezaDTO>> respuestaBO =  new Respuesta<List<TrackingPiezaDTO>>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(TrackingTarjetasObjectsMock.obtenerRespuestaPiezasDTO());
        when(consultarSucursalesBO.consultarSucursalPorId(Matchers.anyString())).thenReturn(TrackingTarjetasObjectsMock.obtenerRespuestaSucursal());
        when(trackingTarjetasBO.obtenerTrackingTarjetas(Matchers.any(Cliente.class))).thenReturn(respuestaBO);
        when(sesionCliente.getCliente()).thenReturn(TrackingTarjetasObjectsMock.obtenerDatosClienteOKReimpresionTest());
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<TrackingTarjetasView> respuesta = trackingTarjetasManager.obtenerTrackingTarjetas();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    /**
     * consultarTrackingTarjetasTest
     * @throws IllegalAccessException 
     */
    @Test
    public void consultarTrackingTarjetasWarningTest() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(trackingTarjetasManager, "horaDesde", "00:00", true);
        FieldUtils.writeDeclaredField(trackingTarjetasManager, "horaHasta", "23:59", true);
        Respuesta<List<TrackingPiezaDTO>> respuestaBO =  new Respuesta<List<TrackingPiezaDTO>>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaBO.setRespuesta(TrackingTarjetasObjectsMock.obtenerRespuestaPiezasDTO());
        when(trackingTarjetasBO.obtenerTrackingTarjetas(Matchers.any(Cliente.class))).thenReturn(respuestaBO);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<TrackingTarjetasView> respuesta = trackingTarjetasManager.obtenerTrackingTarjetas();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }
    
    /**
     * consultarTrackingTarjetasTest
     * @throws IllegalAccessException 
     */
    @Test
    public void consultarTrackingTarjetasParseExceptionTest() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(trackingTarjetasManager, "horaDesde", "00:00", true);
        FieldUtils.writeDeclaredField(trackingTarjetasManager, "horaHasta", "23:59", true);
        Respuesta<List<TrackingPiezaDTO>> respuestaBO =  new Respuesta<List<TrackingPiezaDTO>>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(TrackingTarjetasObjectsMock.obtenerRespuestaPiezasErrorFechaDTO());
        when(consultarSucursalesBO.consultarSucursalPorId(Matchers.anyString())).thenReturn(TrackingTarjetasObjectsMock.obtenerRespuestaSucursal());
        when(trackingTarjetasBO.obtenerTrackingTarjetas(Matchers.any(Cliente.class))).thenReturn(respuestaBO);
        when(sesionCliente.getCliente()).thenReturn(TrackingTarjetasObjectsMock.obtenerDatosClienteOKReimpresionTest());
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<TrackingTarjetasView> respuesta = trackingTarjetasManager.obtenerTrackingTarjetas();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
}

package ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.bo.TraspasoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.TraspasoAutomaticoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.impl.TraspasoAutomaticoManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view.TraspasoAutomaticoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class TraspasoAutomaticoManagerConfirmarTest {
    
    @InjectMocks
    private TraspasoAutomaticoManagerImpl traspasoAutomaticoManager = new TraspasoAutomaticoManagerImpl();
    
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Mock
    private SesionParametros sesionParametros;
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
    private CuentaManager cuentaManager;
    
    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private TraspasoAutomaticoBO traspasoAutomaticoBO;

    @Test
    public void solicitarTraspasoAutomaticoTest() throws ServiceException{
        
        AbstractCuenta cuenta = new Cuenta();
        TraspasoAutomaticoView traspasoAutomaticoView = new TraspasoAutomaticoView();
        Cliente cliente = new Cliente();
        Respuesta<Void> respuestaVoid = new Respuesta<Void>();
        Mensaje mensaje = new Mensaje();
        
        respuestaVoid.setEstadoRespuesta(EstadoRespuesta.OK);
        mensaje.setMensaje("Mensaje {0} {1}");
        cuenta.setAlias("Alias");
        cuenta.setNroCuentaProducto("1234567");
        cuenta.setNroSucursal("123");
        cuenta.setTraspasoAutomaticoActivo(Boolean.FALSE);
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta.setSolicitudPendienteTraspasoAutomatico(Boolean.TRUE);
        
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(traspasoAutomaticoBO.confirmarTraspasoAutomatico(Matchers.any(TraspasoAutomaticoDTO.class))).thenReturn(respuestaVoid);
        when(cuentaManager.obtenerCuentaById(Matchers.anyString())).thenReturn(cuenta);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        
        Respuesta<TraspasoAutomaticoView> respuesta = traspasoAutomaticoManager.confirmarTraspasoAutomatico(traspasoAutomaticoView);
        
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        TraspasoAutomaticoView traspaso = respuesta.getRespuesta();
        assertNotNull(traspaso);
        assertEquals(traspaso.getMensajeFeedback(), "Mensaje activo canceló");
        
    }

}

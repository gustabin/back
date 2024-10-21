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

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.impl.TraspasoAutomaticoManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view.TraspasoAutomaticoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class TraspasoAutomaticoManagerSolicitarTest {

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

    @Test
    public void solicitarTraspasoAutomaticoTest() throws ServiceException{
        
        AbstractCuenta cuenta = new Cuenta();
        TraspasoAutomaticoView traspasoAutomaticoView = new TraspasoAutomaticoView();
        
        cuenta.setAlias("Alias");
        cuenta.setNroCuentaProducto("1234567");
        cuenta.setNroSucursal("123");
        cuenta.setTraspasoAutomaticoActivo(Boolean.FALSE);
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta.setSolicitudPendienteTraspasoAutomatico(Boolean.FALSE);
        
        when(cuentaManager.obtenerCuentaById(Matchers.anyString())).thenReturn(cuenta);
        
        Respuesta<TraspasoAutomaticoView> respuesta = traspasoAutomaticoManager.solicitarTraspasoAutomatico(traspasoAutomaticoView);
        
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        TraspasoAutomaticoView traspaso = respuesta.getRespuesta();
        assertNotNull(traspaso);
        assertEquals(traspaso.getAliasCuenta(), "Alias");
        assertEquals(traspaso.getNumeroCuenta(), "123-123456/7");
        
    }
}

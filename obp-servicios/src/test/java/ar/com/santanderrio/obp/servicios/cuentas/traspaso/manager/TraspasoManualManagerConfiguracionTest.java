package ar.com.santanderrio.obp.servicios.cuentas.traspaso.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.impl.TraspasoManualManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view.CuentasTraspasoView;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view.TraspasoManualView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class TraspasoManualManagerConfiguracionTest {
    
    @InjectMocks
    private TraspasoManualManagerImpl traspasoManualManager = new TraspasoManualManagerImpl();
    
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
    private SesionParametros sesionParametros;

    @Test
    public void confirmarTraspasoAutomaticoTest() throws DAOException{
        
        Cliente cliente = new Cliente();
        TraspasoManualView traspasoManualView = new TraspasoManualView();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        
        traspasoManualView.setNumeroCuenta("123-123456/7");
        cuenta.setSaldoAperCuentaCorrientePesos(new BigDecimal("300"));
        cuenta.setSaldoAperCajaAhorroPesos(new BigDecimal("200"));
        cuenta.setAlias("Alias");
        cuenta.setNroCuentaProducto("1234567");
        cuenta.setNroSucursal("123");
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);
        
        when(sesionCliente.getCliente()).thenReturn(cliente);
        
        Respuesta<TraspasoManualView> respuesta = traspasoManualManager.configurarTraspasoManual(traspasoManualView);
        
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        List<CuentasTraspasoView> cuentasRespuesta = respuesta.getRespuesta().getCuentas();
        CuentasTraspasoView cajaAhorro = cuentasRespuesta.get(0);
        CuentasTraspasoView cuentaCorriente = cuentasRespuesta.get(1);
        assertEquals(cajaAhorro.getAlias(), "Alias");
        assertEquals(cajaAhorro.getDescripcionTipoCuenta(), "Caja de ahorro en $");
        assertEquals(cajaAhorro.getNumero(), "123-123456/7");
        assertEquals(cajaAhorro.getSaldoPesos(), "200,00");
        assertEquals(cuentaCorriente.getAlias(), "Alias");
        assertEquals(cuentaCorriente.getDescripcionTipoCuenta(), "Cuenta corriente en $");
        assertEquals(cuentaCorriente.getNumero(), "123-123456/7");
        assertEquals(cuentaCorriente.getSaldoPesos(), "300,00");
    }

}

package ar.com.santanderrio.obp.servicios.tenencias.sei;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tenencias.manager.TenenciasManager;
import ar.com.santanderrio.obp.servicios.tenencias.view.CuentaView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ImpuestoMonedaExtranjeraResumenView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ImpuestoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.InversionesView;
import ar.com.santanderrio.obp.servicios.tenencias.view.PrestamoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.RendimientoFondoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TarjetaView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasInView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasView;

@RunWith(MockitoJUnitRunner.class)
public class TenenciasSEITest {
    /** The Tenencias Manager. */
    @InjectMocks
    private TenenciasSEIImpl tenenciasSEI = new TenenciasSEIImpl();

    /** The Tenencias BO. */
    @Mock
    private TenenciasManager tenenciasManager;
    
    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    /** The SesionCliente */
    @Mock
    private SesionCliente sesionCliente;
    
    
    @Test
    public void testConsultarTenencias(){
        TenenciasInView viewRequest = new TenenciasInView();
        viewRequest.setAnio("2015");
        viewRequest.setNup("123456");
        
        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        
        when(sesionCliente.getCliente()).thenReturn(cliente);
        
        TenenciasView view = new TenenciasView();
        view.setCotiDolar("10,00");
        view.setCuentas(new ArrayList<CuentaView>());
        view.setCuentasME(new ArrayList<CuentaView>());
        view.setImpuestoMonedaExtranjera(new ArrayList<ImpuestoMonedaExtranjeraResumenView>());
        view.setImpuestos(new ArrayList<ImpuestoView>());
        view.setInversiones(new InversionesView());
        view.setPrestamos(new ArrayList<PrestamoView>());
        view.setRendimientoFondos(new ArrayList<RendimientoFondoView>());
        view.setSaldoTotalDolares("0,00");
        view.setSaldoTotalPesos("0,00");
        view.setTarjetas(new ArrayList<TarjetaView>());
        Respuesta<TenenciasView> respuesta = new Respuesta<TenenciasView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(view);
        
        when(tenenciasManager.consultarTenencias(viewRequest)).thenReturn(respuesta);
        
        assertEquals(respuesta, tenenciasSEI.consultarTenencias(viewRequest));
    
    }
    
}

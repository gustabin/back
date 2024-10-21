package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.SEI;

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
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.AccesoDirectoProductoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.AccesoDirectoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.AccesoDirectoResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.DatosAccesoDirectoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.DatosAccesoDirectoResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.manager.AccesosDirectosManager;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.sei.AccesoDirectoSEI;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.sei.AccesoDirectoSEIImpl;
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
public class AccesoDirectoSEITest {
    /** The Tenencias Manager. */
    @InjectMocks
    private AccesoDirectoSEIImpl accesoDirectoSEIImpl = new AccesoDirectoSEIImpl();

    /** The Tenencias BO. */
    @Mock
    private AccesosDirectosManager accesosDirectosManager;
    
    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    
    @Test
    public void testObtenerAccesoDirecto(){

        Respuesta<AccesoDirectoProductoEntity> respuesta = new Respuesta<AccesoDirectoProductoEntity>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        when(accesosDirectosManager.obtenerAccesoDirecto()).thenReturn(respuesta);
        
        Respuesta<AccesoDirectoProductoEntity> respuestaSei = accesoDirectoSEIImpl.obtenerAccesoDirecto();
        System.out.println(respuestaSei.getEstadoRespuesta());
                
        assertEquals(respuesta, respuestaSei);
        

    
    }
    
}

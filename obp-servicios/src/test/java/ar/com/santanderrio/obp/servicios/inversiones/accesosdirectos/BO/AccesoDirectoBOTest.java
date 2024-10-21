package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.BO;

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
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.bo.AccesosDirectosBOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.dao.AccesosDirectosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.dao.AccesosDirectosDAOImpl;
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
public class AccesoDirectoBOTest {
    /** The Tenencias Manager. */
    @InjectMocks
    private AccesosDirectosBOImpl accesoDirectoBOImpl = new AccesosDirectosBOImpl();

    /** The Tenencias BO. */
    @Mock
    private AccesosDirectosDAOImpl accesosDirectosDAOImpl;
    
    
	/** The SesionCliente */
	@Mock
	private SesionCliente sesionCliente;
    
    
    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    
    @Test
    public void testObtenerAccesoDirecto(){

        AccesoDirectoProductoEntity producto = new AccesoDirectoProductoEntity();
        
        Cliente cliente = new Cliente();
        cliente.setNup("03007878");
        

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(accesosDirectosDAOImpl.obtenerAccesoDirecto(cliente.getNup())).thenReturn(producto);
        
        
        AccesoDirectoProductoEntity respuestaBO = accesoDirectoBOImpl.obtenerAccesoDirecto();

        
        assertEquals(producto, respuestaBO);
    
    }
    
}

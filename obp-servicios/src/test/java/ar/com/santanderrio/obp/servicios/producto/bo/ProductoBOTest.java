package ar.com.santanderrio.obp.servicios.producto.bo;

import static org.mockito.Mockito.when;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.producto.bo.impl.BajaProductoBOImpl;
import ar.com.santanderrio.obp.servicios.producto.dao.SolicitudProductoDAO;
import ar.com.santanderrio.obp.servicios.producto.dao.SolicitudProductoDAOImpl;
import ar.com.santanderrio.obp.servicios.producto.dto.ObtenerBajaProductoDTO;
import ar.com.santanderrio.obp.servicios.producto.view.ComprobanteBajaProductoView;
import ar.com.santanderrio.obp.servicios.producto.view.TipoOperacionBajaProductoEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

@RunWith(MockitoJUnitRunner.class)
public class ProductoBOTest {

    @InjectMocks
    BajaProductoBO bajaProductoBO = new BajaProductoBOImpl();
    
    @Mock
    SesionCliente sesionCliente = new SesionCliente();

    @Mock
    SolicitudProductoDAO solicitudProductoDAO = new SolicitudProductoDAOImpl();
    
    @Test
    public void testSolicitarProductosBAja() throws DAOException, ServiceException, BusinessException, IllegalAccessException{
        Cliente cliente = obtenerCliente();
        when(sesionCliente.getCliente()).thenReturn(cliente);
        FieldUtils.writeField(bajaProductoBO, "sesionCliente",
                sesionCliente, true);
        FieldUtils.writeField(bajaProductoBO, "respuestaFactory",
                new RespuestaFactory(), true);
        
        Respuesta<ObtenerBajaProductoDTO>  respuesta = bajaProductoBO.obtenerProductosBajaTarjeta();
        Assert.assertNotNull(respuesta);
        respuesta = bajaProductoBO.obtenerProductosPaquetesCuenta();
        Assert.assertNotNull(respuesta);
        
        
    }
    
    @Test
    public void testGenerarComproanteProductosBAja() throws DAOException, ServiceException, BusinessException, IllegalAccessException{
        Cliente cliente = obtenerCliente();
        sesionCliente.setCliente(cliente);
        FieldUtils.writeField(bajaProductoBO, "sesionCliente",
                sesionCliente, true);
        FieldUtils.writeField(bajaProductoBO, "respuestaFactory",
                new RespuestaFactory(), true);
        
        Reporte reporte = new Reporte();
        reporte.setBytes("bytes ".getBytes());
        when(solicitudProductoDAO.generarComprobanteBaja(Matchers.any(ComprobanteBajaProductoView.class))).thenReturn(reporte);
        
        ComprobanteBajaProductoView comprobanteView = new ComprobanteBajaProductoView();
        comprobanteView.setDescripcionProducto("descripcion");
        comprobanteView.setFechaDeBaja("01-'1-2'17");
        comprobanteView.setMensaje("Mensaje");
        comprobanteView.setNroComprobante("12312311414");
        comprobanteView.setTipoOperacion(TipoOperacionBajaProductoEnum.BAJA_PAQUETES_CUENTA);
        Respuesta<Reporte>  respuesta = bajaProductoBO.generarComprobante(comprobanteView);
        Assert.assertNotNull(respuesta.getRespuesta().getBytes());
        
        
    }
    
    public static Cliente obtenerCliente() {
        Cliente cliente = ClienteMock.completarInfoCliente();
        return cliente;
    }
}

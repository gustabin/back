package ar.com.santanderrio.obp.servicios.producto.dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosComprobante;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.DatosComprobanteExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.producto.view.ComprobanteBajaProductoView;
import ar.com.santanderrio.obp.servicios.producto.view.TipoOperacionBajaProductoEnum;

@RunWith(MockitoJUnitRunner.class)
public class ProductoDAOTest {
    
    
    /** The reporte CBU cuenta DAO. */
    @InjectMocks
    private SolicitudProductoDAO solicitudDAO = new SolicitudProductoDAOImpl();

    @Mock
    private ComprobanteBajaProductoView comprobanteBajaProducto;
    
    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();
    
     @Test
        public void testReporteCambioClaveUsuario() throws IOException, IllegalAccessException {
            DatosComprobante datos = new DatosComprobante();
            datos.setNroComprobante("123345556");
            datos.setTipoComprobante("Usuario y Clave");
            FieldUtils.writeField(solicitudDAO, "logoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"),
                    true);
            FieldUtils.writeField(solicitudDAO, "fileJasperBajaProducto",
                    appContext.getResource("classpath:/report/bajaProducto/ComprobanteBajaProducto.jasper"), true);
            
            FieldUtils.writeField(solicitudDAO, "logoCierre",
                    appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
            
            ComprobanteBajaProductoView comprobanteView = new ComprobanteBajaProductoView();
            comprobanteView.setDescripcionProducto("descripcion");
            comprobanteView.setFechaDeBaja("01-'1-2'17");
            comprobanteView.setMensaje("Mensaje");
            comprobanteView.setNroComprobante("12312311414");
            comprobanteView.setTipoOperacion(TipoOperacionBajaProductoEnum.BAJA_PAQUETES_CUENTA);
            
            Reporte reporte = solicitudDAO.generarComprobanteBaja(comprobanteView);
            
            Assert.assertNotNull(reporte);
        }
     
     
     @Test
     public void testGenerarComprobantePDFExtraccionEfectivo() throws IllegalAccessException, DAOException, IOException {
         FieldUtils.writeField(solicitudDAO, "logoCabecera",
                 appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
         FieldUtils.writeField(solicitudDAO, "logoCierre",
                 appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
         FieldUtils.writeField(solicitudDAO, "fileJasperBajaProducto",
                 appContext.getResource("classpath:/report/bajaProducto/ComprobanteBajaProducto.jasper"), true);
         
         ComprobanteBajaProductoView comprobanteBajaProducto = new ComprobanteBajaProductoView();
         
         String mensajeLegal = "<p><b>Importante:</b> Para completar la\r\n" + 
        		 "cancelación, deberás acercarte a tu sucursal para\r\n" + 
         		"vaciar la caja de seguridad y entregar la llave.</p>";
         
         comprobanteBajaProducto.setTipoOperacion(TipoOperacionBajaProductoEnum.BAJA_PAQUETES_CUENTA);
         comprobanteBajaProducto.setDescripcionProducto("Caja de seguridad");
         comprobanteBajaProducto.setFechaDeBaja("03/01/2022");
         comprobanteBajaProducto.setFechaOperacion("03/01/2022");
         comprobanteBajaProducto.setNroComprobante("12345");
         comprobanteBajaProducto.setTipoProducto("CAJA_SEGURIDAD");
         comprobanteBajaProducto.setNumero("144567478");
         comprobanteBajaProducto.setMantieneCajaAhorro(false);
         comprobanteBajaProducto.setArrepentimiento(false);
         comprobanteBajaProducto.setMensaje(mensajeLegal.replaceAll("<p>", "").replaceAll("<b>", "").replaceAll("</b>", "").replaceAll("</p>", ""));
         
         Reporte reporte = solicitudDAO.generarComprobanteBaja(comprobanteBajaProducto);
         FileUtils.writeByteArrayToFile(new File("C:/tools/ComprobanteBajaProducto.pdf"), reporte.getBytes());
         Assert.assertNotNull(reporte.getBytes());
     }
     

}

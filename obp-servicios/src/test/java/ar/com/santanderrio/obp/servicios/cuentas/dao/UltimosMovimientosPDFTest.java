package ar.com.santanderrio.obp.servicios.cuentas.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Ignore;
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
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ReporteComprobantePDFDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.impl.ReporteComprobantePDFDAOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientosPDF;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientoExcelItem;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.ItemReporteDetalle;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RunWith(MockitoJUnitRunner.class)
public class UltimosMovimientosPDFTest {

    @InjectMocks
    private ReporteComprobantePDFDAO reporteComprobantePDFDAO = new ReporteComprobantePDFDAOImpl();

    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private DetalleUltimosMovimientosPDF ultimosMovimientosPDF;

    private ApplicationContext appContext = new ClassPathXmlApplicationContext();

    @Test
    @Ignore
    public void testReportePDFUltimosMovimientos() throws IllegalAccessException, DAOException, IOException {
        FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoTop",
                appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"), true);
        FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoCabecera",
                appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
        FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoPie",
                appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
        Mockito.when(ultimosMovimientosPDF.obtenerJasper()).thenReturn(
                appContext.getResource("classpath:/report/cuentas/UltimosMovimientos.jasper").getFile().getPath());
        Mockito.when(ultimosMovimientosPDF.obtenerParametrosPDF()).thenReturn(obtenerParametrosPDF());
        Mockito.when(ultimosMovimientosPDF.getTituloComprobante())
                .thenReturn("Ultimos Movimientos");
        Reporte reporte = reporteComprobantePDFDAO.obtenerReporteComprobantePDF(ultimosMovimientosPDF);
        FileUtils.writeByteArrayToFile(new File("C:/tools/UltimosMovimientosTest.pdf"), reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
    }

    private HashMap<String, Object> obtenerParametrosPDF() throws IOException {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("TITULO", "Ultimos Movimientos");
        parametros.put("LOGO", appContext.getResource("classpath:/report/comprobantes/logo_transparente.png")
                .getFile().getPath());
        parametros.put("FECHA_ACTUAL", "viernes, 14 de diciembre de 2018 - 09:00");
        parametros.put("LEGAL_PIE", "La presente búsqueda no refleja los movimientos y/o transacciones que se encuentran pendientes de confirmación.");
        parametros.put("REPORT_LOCALE", new Locale("es", "AR"));
        
        List<ItemReporteDetalle> cabecera = new ArrayList<ItemReporteDetalle>();
        cabecera.add(new ItemReporteDetalle("Cuenta", "Cuenta Corriente en dólares 084-451663/5"));
        cabecera.add(new ItemReporteDetalle("Moneda", "Pesos"));
        cabecera.add(new ItemReporteDetalle("Fecha", "07/12/2018 - 14/12/2018"));
        cabecera.add(new ItemReporteDetalle("Palabra clave", "123123"));
        cabecera.add(new ItemReporteDetalle("Importes", "07/12/2018 - 14/12/2018"));
        
        List<MovimientoExcelItem> movimientos = new ArrayList<MovimientoExcelItem>();

        movimientos.add(movimiento("13/12/2018 23:25", "0000 CASA CENTRAL",
                "TRANSFERENCIAS INMEDIATAS          A  CBU 2590050910339053910019                               ",
                "052863999", 0d, -800d, true, true, false, true));
        
        movimientos.add(movimiento("13/12/2018 23:25", "0000 CASA CENTRAL",
                "EXTRACCION BANELCO O/SUC 0000      TARJ NRO. 3295                                              ",
                "000000427", 0d, -0.24d, true, true, false, true));
        
        movimientos.add(movimiento("13/12/2018 23:25", "0000 CASA CENTRAL",
                "RECAUDACIONES ELECTRONICAS TARJ.   ",
                "052863999", 40961.98d, -800d, true, true, false, false));
        
        movimientos.add(movimiento("13/12/2018 23:25", "0000 CASA CENTRAL",
                "TRANSFERENCIAS INMEDIATAS          A  CBU 2590050910339053910019                              a",
                "052863999", 0d, -0.24d, true, true, false, false));
        for (int i = 0; i < 45; i++) {
            movimientos.add(movimiento("08/12/2018 09:07", "0769 PEATONAL FLORIDA",
                    "TRANSFERENCIAS INMEDIATAS          A  CBU 2590050910339053910019                               ",
                    "0528639"+i, 48806.12d, -123456789.12d, true, true, false, false));     
        }

        parametros.put("CABECERA", new JRBeanCollectionDataSource(cabecera));
        parametros.put("MOVIMIENTOS", new JRBeanCollectionDataSource(movimientos));
        parametros.put("TIPO_CTA", "Importe caja de ahorro dólares");
        parametros.put("TIPO_CTA2", "Importe cuenta corriente pesos");
        parametros.put("TIENE_MULTIPLE_CTA", false);
        parametros.put("MONEDA", "dólares");

        return parametros;
    }

    private MovimientoExcelItem movimiento(String fechaHora, String sucursalOrigen, String descripcion,
            String referencia, Double saldo, Double importe, Boolean isCajaDeAhoroEnPesos,
            Boolean isCuentaCorrienteEnPesos, Boolean isMovimientoEnDolares, Boolean isDelDia) {
        MovimientoExcelItem mov = new MovimientoExcelItem();
        mov.setFechaHora(fechaHora);
        mov.setSucursalOrigen(sucursalOrigen);
        mov.setDescripcion(descripcion);
        mov.setReferencia(referencia);
        mov.setSaldo(saldo);
        mov.setImporte(importe);
        mov.setIsCajaDeAhoroEnPesos(isCajaDeAhoroEnPesos);
        mov.setIsCuentaCorrienteEnPesos(isCuentaCorrienteEnPesos);
        mov.setIsMovimientoEnDolares(isMovimientoEnDolares);
        mov.setIsDelDia(isDelDia);
        return mov;

    }

}

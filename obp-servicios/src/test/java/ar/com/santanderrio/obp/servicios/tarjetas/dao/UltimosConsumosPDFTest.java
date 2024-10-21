package ar.com.santanderrio.obp.servicios.tarjetas.dao;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleUltimosConsumosPDF;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.MovimientosTarjetaExcel2;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RunWith(MockitoJUnitRunner.class)
public class UltimosConsumosPDFTest {

    @InjectMocks
    private ReporteComprobantePDFDAO reporteComprobantePDFDAO = new ReporteComprobantePDFDAOImpl();

    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private DetalleUltimosConsumosPDF ultimosConsumosPDF;

    private ApplicationContext appContext = new ClassPathXmlApplicationContext();

    @Test
    @Ignore
    public void testReportePDFUltimosConsumos() throws IllegalAccessException, DAOException, IOException {
        FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoTop",
                appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"), true);
        FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoCabecera",
                appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
        FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoPie",
                appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
        Mockito.when(ultimosConsumosPDF.obtenerJasper()).thenReturn(
                appContext.getResource("classpath:/report/tarjetas/UltimosConsumosYPendientes.jasper").getFile().getPath());
        Mockito.when(ultimosConsumosPDF.obtenerParametrosPDF()).thenReturn(obtenerParametrosPDF());
        Mockito.when(ultimosConsumosPDF.getTituloComprobante())
                .thenReturn("Ultimos Consumos");
        Reporte reporte = reporteComprobantePDFDAO.obtenerReporteComprobantePDF(ultimosConsumosPDF);
        FileUtils.writeByteArrayToFile(new File("C:/tools/UltimosConsumosTest.pdf"), reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
    }

    private HashMap<String, Object> obtenerParametrosPDF() throws IOException {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("TITULO", "Ultimos Consumos");
        parametros.put("LOGO", appContext.getResource("classpath:/report/comprobantes/logo_transparente.png")
                .getFile().getPath());
        parametros.put("FECHA_ACTUAL", "viernes, 14 de diciembre de 2018 - 09:00");
        parametros.put("REPORT_LOCALE", new Locale("es", "AR"));
        
        MovimientosTarjetaExcel2 movimientos = new MovimientosTarjetaExcel2(conseguirConfirmados(), conseguirPendientes());
                
        parametros.put("CONSUMOS", new JRBeanCollectionDataSource(movimientos.getUltimosConsumos()));
        parametros.put("PENDIENTES", new JRBeanCollectionDataSource(movimientos.getConsumosPendientes()));
        parametros.put("TIENE_ULTIMOS_CONSUMOS", movimientos.getTieneUltimosConsumos());
        parametros.put("TIENE_CONSUMOS_PENDIENTES", movimientos.getTieneConsumosPendientes());
        
        parametros.put("TOTAL_ULTIMOS_CONSUMOS_PESOS", movimientos.getTotalUltimosConsumosPesos());
        parametros.put("TOTAL_ULTIMOS_CONSUMOS_DOLARES", movimientos.getTotalUltimosConsumosDolares());
        parametros.put("TOTAL_CONSUMOS_PENDIENTES_PESOS", movimientos.getTotalConsumosPendientesPesos());
        parametros.put("TOTAL_CONSUMOS_PENDIENTES_DOLARES", movimientos.getTotalConsumosPendientesDolares());
        
        
        return parametros;
    }
    
    private List<ConsumoTarjetaDTO> conseguirConfirmados() {
        List<ConsumoTarjetaDTO> consumos = new ArrayList<ConsumoTarjetaDTO>();
        ConsumoTarjetaDTO consumo = new ConsumoTarjetaDTO();
        consumo.setMarca("VISA Recargable");
        consumo.setNumero("XXXX-3235");
        consumo.setConsumoPesos(new BigDecimal("615,48".replace(",", ".")));
        consumo.setConsumoDolares(new BigDecimal("0,00".replace(",", ".")));
        consumo.setIsTitular(true);
        consumo.setIsAdicional(false);
        consumo.setIsTarjetaRecargable(true);
        consumo.setNombreAdicional("");
        consumo.setLineas(obtenerLineasConfirmados());
        consumos.add(consumo);
        ConsumoTarjetaDTO consumo2 = new ConsumoTarjetaDTO();
        consumo2.setMarca("VISA");
        consumo2.setNumero("XXXX-3737");
        consumo2.setConsumoPesos(new BigDecimal("615,48".replace(",", ".")));
        consumo2.setConsumoDolares(new BigDecimal("0,00".replace(",", ".")));
        consumo2.setIsTarjetaRecargable(false);
        consumo2.setIsTitular(false);
        consumo2.setIsAdicional(true);
        consumo2.setNombreAdicional("Ernesto");
        consumo2.setLineas(obtenerLineasConfirmados2());
        consumos.add(consumo2);
        return consumos;
    }

    private List<LineaDetalleConsumoTarjetaDTO> obtenerLineasConfirmados2() {
        List<LineaDetalleConsumoTarjetaDTO> lineas = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
        LineaDetalleConsumoTarjetaDTO lineaDTO2 = new LineaDetalleConsumoTarjetaDTO();
        lineaDTO2.setFecha(ISBANStringUtils.formatearFecha("26/01/2018"));
        lineaDTO2.setFechaExcel("26/02/2018");
        lineaDTO2.setDescripcion("GARBARINO SA");
        lineaDTO2.setCodigoEstablecimiento("");
        lineaDTO2.setNroReferencia("2326897");
        lineaDTO2.setImportePesos(new BigDecimal("0.00"));
        lineaDTO2.setImporteDolares(new BigDecimal("12,01".replace(",", ".")));
        //lineas.add(lineaDTO2);
        return lineas;
    }

    private List<LineaDetalleConsumoTarjetaDTO> obtenerLineasConfirmados() {
        List<LineaDetalleConsumoTarjetaDTO> lineas = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
        LineaDetalleConsumoTarjetaDTO lineaDTO = new LineaDetalleConsumoTarjetaDTO();
        lineaDTO.setFecha(ISBANStringUtils.formatearFecha("26/01/2018"));
        lineaDTO.setFechaExcel("26/01/2018");
        lineaDTO.setDescripcion("GARBARINO SA");
        lineaDTO.setCodigoEstablecimiento("23452345234");
        lineaDTO.setNroReferencia("232689744");
        lineaDTO.setImportePesos(new BigDecimal("15,01".replace(",", ".")));
    
        lineas.add(lineaDTO);
        lineas.add(lineaDTO);
        lineas.add(lineaDTO);
      LineaDetalleConsumoTarjetaDTO lineaDTO2 = new LineaDetalleConsumoTarjetaDTO();
      lineaDTO2.setFecha(ISBANStringUtils.formatearFecha("26/01/2018"));
      lineaDTO2.setFechaExcel("26/02/2018");
      lineaDTO2.setDescripcion("Su Pago En Pesos");
      lineaDTO2.setCodigoEstablecimiento("");
      lineaDTO2.setNroReferencia("2326897   ");
      lineaDTO2.setImportePesos(new BigDecimal("0.00"));
      lineaDTO2.setImporteDolares(new BigDecimal("12,01".replace(",", ".")));
      lineas.add(lineaDTO2);
        return lineas;
    }

    private List<ConsumoTarjetaDTO> conseguirPendientes() {
        List<ConsumoTarjetaDTO> consumos = new ArrayList<ConsumoTarjetaDTO>();
        ConsumoTarjetaDTO consumo = new ConsumoTarjetaDTO();
        consumo.setMarca("VISA Recargable");
        consumo.setNumero("XXXX-3838");
        consumo.setConsumoPesos(new BigDecimal("615,48".replace(",", ".")));
        consumo.setConsumoDolares(new BigDecimal("0,00".replace(",", ".")));
        consumo.setIsTitular(true);
        consumo.setIsAdicional(false);
        consumo.setIsTarjetaRecargable(true);
        consumo.setLineas(obtenerLineasPendientes());
        consumos.add(consumo);
        ConsumoTarjetaDTO consumo2 = new ConsumoTarjetaDTO();
        consumo2.setMarca("AMEX");
        consumo2.setNumero("XXXX-4636");
        consumo2.setConsumoPesos(new BigDecimal("615,48".replace(",", ".")));
        consumo2.setConsumoDolares(new BigDecimal("0,00".replace(",", ".")));
        consumo2.setIsTitular(false);
        consumo2.setIsAdicional(true);
        consumo2.setIsTarjetaRecargable(false);
        consumo2.setNombreAdicional("Rodrigo ortiz");
        consumo2.setLineas(obtenerLineasPendientes2());
        consumos.add(consumo2);
        return consumos;
    }

    private List<LineaDetalleConsumoTarjetaDTO> obtenerLineasPendientes() {
        List<LineaDetalleConsumoTarjetaDTO> lineas = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
        LineaDetalleConsumoTarjetaDTO lineaDTO = new LineaDetalleConsumoTarjetaDTO();
        lineaDTO.setFecha(ISBANStringUtils.formatearFecha("26/01/2018"));
        lineaDTO.setFechaExcel("26/01/2017");
        lineaDTO.setDescripcion("COMPUMUNDO SA");
        lineaDTO.setCodigoEstablecimiento("324523452");
        lineaDTO.setNroReferencia("2326897");
        lineaDTO.setImportePesos(new BigDecimal("-0,01".replace(",", ".")));
        lineaDTO.setImporteDolares(new BigDecimal("13,23".replace(",", ".")));
        lineaDTO.setEsPendienteConfirmacion(true);
        lineas.add(lineaDTO);
        lineas.add(lineaDTO);
        lineas.add(lineaDTO);
        return lineas;
    }
    
    private List<LineaDetalleConsumoTarjetaDTO> obtenerLineasPendientes2() {
        List<LineaDetalleConsumoTarjetaDTO> lineas = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
        LineaDetalleConsumoTarjetaDTO lineaDTO = new LineaDetalleConsumoTarjetaDTO();
        lineaDTO.setFecha(ISBANStringUtils.formatearFecha("26/01/2018"));
        lineaDTO.setFechaExcel("26/01/2017");
        lineaDTO.setDescripcion("COMPUMUNDO SA");
        lineaDTO.setCodigoEstablecimiento("324523452");
        lineaDTO.setNroReferencia("2326897");
        lineaDTO.setImportePesos(new BigDecimal("-0,01".replace(",", ".")));
        lineaDTO.setImporteDolares(new BigDecimal("13,23".replace(",", ".")));
        lineaDTO.setEsPendienteConfirmacion(true);
        lineas.add(lineaDTO);
        lineas.add(lineaDTO);
        lineas.add(lineaDTO);
        lineas.add(lineaDTO);
        return lineas;
    }
}

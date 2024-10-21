package ar.com.santanderrio.obp.servicios.prestamos.dao;

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
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.ReportePrestamosPreaprobadoDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoPreaprobadoMonoproductoInOutView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportSimulacionPrestamosView;

@RunWith(MockitoJUnitRunner.class)
public class ReportePrestamoPreaprobadoDAOTest {
	
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
	@InjectMocks
	ReportePrestamosPreaprobadoDAO reportePrestamoDAO = new ReportePrestamosPreaprobadoDAOImpl();
	
	@Mock
	PrestamoPreaprobadoMonoproductoInOutView comprobanteView;
	
	private ApplicationContext appContext = new ClassPathXmlApplicationContext();
	
	@InjectMocks
    ReportSimulacionPrestamosView reportSimulacionPrestamosViewImpl = new ReportSimulacionPrestamosView();
	
	
	@Test
	public void testReportePrestamoPreaprobadoComprobantePDF() throws IllegalAccessException, DAOException, IOException {
        FieldUtils.writeField(reportePrestamoDAO, "logoPie",
                appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
        FieldUtils.writeField(reportePrestamoDAO, "imagenLogoCabecera",
                appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
        FieldUtils.writeField(reportePrestamoDAO, "imagenLogoPersonal",
                appContext.getResource("classpath:/report/comprobantes/super-prestamo-personal.png"), true);
        Mockito.when(comprobanteView.obtenerJasper()).thenReturn(
                appContext.getResource("classpath:/report/prestamos/comprobante/comprobantePrestamoPreaprobado.jasper").getFile().getPath());
        Mockito.when(comprobanteView.obtenerParametros()).thenReturn(obtenerParametrosPDF(true, true));
        Reporte reporte = reportePrestamoDAO.generarComprobantePrestamoPreaprobado(comprobanteView);
        FileUtils.writeByteArrayToFile(new File("C:/tools/PrestamoPreaprobado.pdf"), reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
	}
	
	@Test
	public void testReportePrestamoPreaprobadoSinComisionComprobantePDF() throws IllegalAccessException, DAOException, IOException {
        FieldUtils.writeField(reportePrestamoDAO, "logoPie",
                appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
        FieldUtils.writeField(reportePrestamoDAO, "imagenLogoCabecera",
                appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
        FieldUtils.writeField(reportePrestamoDAO, "imagenLogoPersonal",
                appContext.getResource("classpath:/report/comprobantes/super-prestamo-personal.png"), true);
        Mockito.when(comprobanteView.obtenerJasper()).thenReturn(
                appContext.getResource("classpath:/report/prestamos/comprobante/comprobantePrestamoPreaprobado.jasper").getFile().getPath());
        Mockito.when(comprobanteView.obtenerParametros()).thenReturn(obtenerParametrosPDF(false, true));
        Reporte reporte = reportePrestamoDAO.generarComprobantePrestamoPreaprobado(comprobanteView);
        FileUtils.writeByteArrayToFile(new File("C:/tools/PrestamoPreaprobadoSinComision.pdf"), reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
	}
	
	@Test
	public void testReportePrestamoPreaprobadoSinImpuestosComprobantePDF() throws IllegalAccessException, DAOException, IOException {
		FieldUtils.writeField(reportePrestamoDAO, "logoPie",
                appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
        FieldUtils.writeField(reportePrestamoDAO, "imagenLogoCabecera",
                appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
        FieldUtils.writeField(reportePrestamoDAO, "imagenLogoPersonal",
                appContext.getResource("classpath:/report/comprobantes/super-prestamo-personal.png"), true);
        Mockito.when(comprobanteView.obtenerJasper()).thenReturn(
                appContext.getResource("classpath:/report/prestamos/comprobante/comprobantePrestamoPreaprobado.jasper").getFile().getPath());
        Mockito.when(comprobanteView.obtenerParametros()).thenReturn(obtenerParametrosPDF(true, false));
        Reporte reporte = reportePrestamoDAO.generarComprobantePrestamoPreaprobado(comprobanteView);
        FileUtils.writeByteArrayToFile(new File("C:/tools/PrestamoPreaprobadoSinImpuestos.pdf"), reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
	}
	@Test
	public void testReportePrestamoPreaprobadoSinImpuestosNiComisionComprobantePDF() throws IllegalAccessException, DAOException, IOException {
		FieldUtils.writeField(reportePrestamoDAO, "logoPie",
                appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
        FieldUtils.writeField(reportePrestamoDAO, "imagenLogoCabecera",
                appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
        FieldUtils.writeField(reportePrestamoDAO, "imagenLogoPersonal",
                appContext.getResource("classpath:/report/comprobantes/super-prestamo-personal.png"), true);
        Mockito.when(comprobanteView.obtenerJasper()).thenReturn(
                appContext.getResource("classpath:/report/prestamos/comprobante/comprobantePrestamoPreaprobado.jasper").getFile().getPath());
        Mockito.when(comprobanteView.obtenerParametros()).thenReturn(obtenerParametrosPDF(false, false));
        Reporte reporte = reportePrestamoDAO.generarComprobantePrestamoPreaprobado(comprobanteView);
        FileUtils.writeByteArrayToFile(new File("C:/tools/PrestamoPreaprobadoSinImpuestosNiComision.pdf"), reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
	}

	private HashMap<String, Object> obtenerParametrosPDF(boolean comision, boolean intereses) {
		
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("importeAcreditado", DivisaEnum.PESO.getSimbolo() + "50.000,00");
		parameters.put("monto", DivisaEnum.PESO.getSimbolo() + "50.000,00");
		if(comision)
			parameters.put("comision", DivisaEnum.PESO.getSimbolo() + "1.500,00");
		if(intereses)
			parameters.put("impuestos", DivisaEnum.PESO.getSimbolo() + "1.300,00");
		parameters.put("cuentaDestino", "066-133054/6");
		parameters.put("tipoCuenta", "Cuenta única");
		parameters.put("nroCuotas", "60");
		parameters.put("motivo", "Adquisicion automotores utilitario");
		parameters.put("fechaPrimeraCuota", "28/09/2020");
		parameters.put("cuotaConstante", DivisaEnum.PESO.getSimbolo() + "880,00");
		parameters.put("tipoTasa", "Fija");
		parameters.put("tasa", "30,00 %");
		parameters.put("tea", "34,48 %");
		parameters.put("nroComprobante", "0123456789");
		parameters.put("legales", "");
		return parameters;
	}
	
	
	
}

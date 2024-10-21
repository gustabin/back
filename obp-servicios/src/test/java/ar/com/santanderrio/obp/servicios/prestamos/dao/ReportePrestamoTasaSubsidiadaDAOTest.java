package ar.com.santanderrio.obp.servicios.prestamos.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.entities.InfoEmpleadoPrestamoTasaSubsidiada;
import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.ReportePrestamosTasaSubsidiadaDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoTasaSubsidiadaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportSimulacionPrestamosView;

@RunWith(MockitoJUnitRunner.class)
public class ReportePrestamoTasaSubsidiadaDAOTest {
	
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
	@InjectMocks
	ReportePrestamosTasaSubsidiadaDAO reportePrestamoDAO = new ReportePrestamosTasaSubsidiadaDAOImpl();
	
	@Mock
	ComprobantePrestamoTasaSubsidiadaView comprobanteView;
	
	private ApplicationContext appContext = new ClassPathXmlApplicationContext();
	
	@InjectMocks
    ReportSimulacionPrestamosView reportSimulacionPrestamosViewImpl = new ReportSimulacionPrestamosView();
	
	
	@Test
	public void testReportePrestamoTasaSubComprobantePDF() throws IllegalAccessException, DAOException, IOException {
        FieldUtils.writeField(reportePrestamoDAO, "logoPie",
                appContext.getResource("classpath:/report/comprobantes/cierre de comprobante.png"), true);
        FieldUtils.writeField(reportePrestamoDAO, "fileJasperCompPrestamoTasaSub",
                appContext.getResource("classpath:/report/prestamos/comprobante/comprobantePrestamoTasaSub.jasper"), true);
        Mockito.when(comprobanteView.obtenerJasper()).thenReturn(
                appContext.getResource("classpath:/report/prestamos/comprobante/comprobantePrestamoTasaSub.jasper").getFile().getPath());
        Mockito.when(comprobanteView.obtenerParametros()).thenReturn(obtenerParametrosPDF());
        Reporte reporte = reportePrestamoDAO.generarComprobantePrestamoTasaSubsidiada(comprobanteView);
        FileUtils.writeByteArrayToFile(new File("C:/tools/PrestamoTasaSub.pdf"), reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
	}

	private HashMap<String, Object> obtenerParametrosPDF() {
		List<InfoEmpleadoPrestamoTasaSubsidiada> listaEmpleados = new ArrayList<InfoEmpleadoPrestamoTasaSubsidiada>();
		
		for (int i = 0; i < 4; i++) {
			InfoEmpleadoPrestamoTasaSubsidiada empleado = new InfoEmpleadoPrestamoTasaSubsidiada();
			empleado.setCuit("27-35629852-2");
			empleado.setCbu("1234567891011121314155");
			empleado.setMonto("$78000");
			listaEmpleados.add(empleado);
		}
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nroComprobante", "190820201553");
		parametros.put("monto", "$ 1.250.458,98");
		parametros.put("cuotasPrestamo", "12 Mensuales");
		parametros.put("tasa", "45 %");
		parametros.put("cuentaDebito", "000-063880/1");
		parametros.put("tipoCuenta", "Cuenta unica en pesos $");
		parametros.put("email", "mail@santandertecnologia.com.ar");
		parametros.put("empleados", listaEmpleados);
		parametros.put("legales", "");
		parametros.put("textoFooter", "");
		return parametros;
	}
	
	
	
}

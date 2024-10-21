package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import java.io.IOException;
import java.util.HashMap;

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
import ar.com.santanderrio.obp.servicios.comprobantes.dao.impl.ReporteComprobantePDFDAOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class ReporteComprobantePMCDAOTest {

	@InjectMocks
	private ReporteComprobantePDFDAO reporteComprobantePDFDAO = new ReporteComprobantePDFDAOImpl();

	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Mock
	private DetalleComprobanteView detalleComprobanteView;

	private ApplicationContext appContext = new ClassPathXmlApplicationContext();

	@Test
	@Ignore
	public void testDetalleComprobantePDF() throws IllegalAccessException, DAOException, IOException {
		FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoTop",
				appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"), true);
		FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoCabecera",
				appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoPie",
				appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
		Mockito.when(detalleComprobanteView.obtenerJasper()).thenReturn(
				appContext.getResource("classpath:/report/comprobantes/pmc-servicio.jasper").getFile().getPath());
		Mockito.when(detalleComprobanteView.obtenerParametrosPDF()).thenReturn(obtenerParametrosPDF());
		Mockito.when(detalleComprobanteView.getTituloComprobante())
				.thenReturn("Comprobante de pago");
		Reporte reporte = reporteComprobantePDFDAO.obtenerReporteComprobantePDF(detalleComprobanteView);
//        FileUtils.writeByteArrayToFile(new File("C:/tools/comprobantePMCTest.pdf"),reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
	}

	private HashMap<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("TITULO", "AUTONOMOS SUSS");
		parametros.put("IMPORTE", "$ 2.102,68");
		parametros.put("EMPRESA", "AUTONOMOS SUSS");
		parametros.put("FECHA_VENCIMIENTO", "13/09/2017");	
		parametros.put("IDENTIFICACION", "20003882889");
		parametros.put("LEYENDA_FACTURA", "leyenda factura");
		parametros.put("MEDIO_PAGO", "168-356669/3");
		parametros.put("TIPO_MEDIO_PAGO", "Cuenta corriente");
		parametros.put("FECHA_Y_HORA", "27/07/2019");
		parametros.put("FECHA_PAGO", "27/08/2019");
		parametros.put("NUMERO_CONTROL", "79310");
		parametros.put("INFORMACION_ADICIONAL", "INFORMACION_ADICIONAL");
		parametros.put("NUMERO_COMPROBANTE", "10313144");		
		return parametros;
	}
		
	
}

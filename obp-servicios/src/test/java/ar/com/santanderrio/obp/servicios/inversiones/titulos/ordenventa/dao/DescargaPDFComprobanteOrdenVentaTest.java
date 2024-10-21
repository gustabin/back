package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dao;

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
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ReporteComprobantePDFDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.impl.ReporteComprobantePDFDAOImpl;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ComprobanteOrdenVenta;

@RunWith(MockitoJUnitRunner.class)
public class DescargaPDFComprobanteOrdenVentaTest {

	@InjectMocks
	private ReporteComprobantePDFDAO reporteComprobantePDFDAO = new ReporteComprobantePDFDAOImpl();

	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Mock
	private ComprobanteOrdenVenta comprobanteOrdenVenta;

	private ApplicationContext appContext = new ClassPathXmlApplicationContext();

	@Test
	public void testReporteComprobantePDFTransferencia() throws IllegalAccessException, DAOException, IOException {
		FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoTop",
				appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"), true);
		FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoCabecera",
				appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoPie",
				appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
		Mockito.when(comprobanteOrdenVenta.obtenerParametrosPDF()).thenReturn(obtenerParametrosPDF());
		Mockito.when(comprobanteOrdenVenta.obtenerJasper()).thenReturn(appContext
				.getResource("classpath:/report/comprobantes/inversiones/orden-venta.jasper").getFile().getPath());

		Mockito.when(comprobanteOrdenVenta.getTituloComprobante()).thenReturn("Comprobante de orden venta titulos");

		Reporte reporte = reporteComprobantePDFDAO.obtenerReporteComprobantePDF(comprobanteOrdenVenta);
//		FileUtils.writeByteArrayToFile(new File("C:/tools/comprobanteOrdenVentaTest.pdf"), reporte.getBytes());
		Assert.assertNotNull(reporte.getBytes());
	}

	private HashMap<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		String legales = "Banco Santander Rio S.A. le informa que esta operacion sera cursada a traves del "
				+ "mercado de valores de Buenos Aires (MERVAL), bajo la modalidad de compra venta en la "
				+ "rueda de concurrencia de negociacion, quedando esta orden sujeta a confirmacion por "
				+ "parte del mencionado mercado. Banco Santander Rio S.A. le informa que se le debitara "
				+ "una comision por la compra-venta de bonos del 1% mas IVA (con un minimo de 30 pe mas IVA) "
				+ "y un cargo del 0.01% para bonos externos, titulos publicos y obligaciones.";

		parametros.put("NOMBRE_ESPECIE", "TENARIS - TS");
		parametros.put("CUENTA_TITULOS", "123456/7");
		parametros.put("TIPO", "Acciones");
		parametros.put("PLAZO_LIQUIDACION", "Inmediato");
		parametros.put("PRECIO_REFERENCIA", "$ 100,00");
		parametros.put("FECHA_REFERENCIA", "20/04/2018 - 15:00");
		parametros.put("CANTIDAD_NOMINALES", "20");
		parametros.put("MONEDA_LIQUIDACION", "Pesos");
		parametros.put("FECHA_LIQUIDACION", "28/04/2018");
		parametros.put("PRECIO_LIMITE", "$ 10,00");
		parametros.put("NUMERO_CUENTA_DESTINO", "1234567/8");
		parametros.put("TIPO_CUENTA_DESTINO", "Cuenta Unica");
		parametros.put("ADHESION_PODER_COMPRA", null);
		parametros.put("COMISIONES", "$ 28,32");
		parametros.put("IVA_COMISION", "$ 28,32");
		parametros.put("DERECHO_MERCADO", "$ 28,32");
		parametros.put("IVA_DERECHO_MERCADO", "$ 28,32");
		parametros.put("NUMERO_ORDEN", "1234567");
		parametros.put("COMPROBANTE", "7654321");
		parametros.put("LEGALES", legales);
		parametros.put("FECHA_ACTUAL", "20/04/2018 - 15:00");

		return parametros;
	}

}

package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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
import ar.com.santanderrio.obp.servicios.comprobantes.dao.impl.ReporteComprobantePDFDAOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteTransferenciaProgramadaView;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class ReporteComprobantePDFDAOTest {

	@InjectMocks
	private ReporteComprobantePDFDAO reporteComprobantePDFDAO = new ReporteComprobantePDFDAOImpl();

	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Mock
	private DetalleComprobanteTransferenciaProgramadaView detalleProgramadaView;

	private ApplicationContext appContext = new ClassPathXmlApplicationContext();

	@Test
	@Ignore
	public void testReporteComprobantePDFTransferencia() throws IllegalAccessException, DAOException, IOException {
		FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoTop",
				appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"), true);
		FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoCabecera",
				appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(reporteComprobantePDFDAO, "imagenLogoPie",
				appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
		Mockito.when(detalleProgramadaView.obtenerJasper()).thenReturn(
				appContext.getResource("classpath:/report/comprobantes/transferencias.jasper").getFile().getPath());
		Mockito.when(detalleProgramadaView.obtenerParametrosPDF()).thenReturn(obtenerParametrosPDF());
		Mockito.when(detalleProgramadaView.getTituloComprobante())
				.thenReturn("Comprobante de transferencia programada");
		Reporte reporte = reporteComprobantePDFDAO.obtenerReporteComprobantePDF(detalleProgramadaView);
        FileUtils.writeByteArrayToFile(new File("C:/tools/comprobanteTest.pdf"),reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
	}

	private HashMap<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("TITULO", "Oquendo Romero  Panfilo Climac");
		parametros.put("IMPORTE", "$33,00");
		parametros.put("NUMERO_ORIGEN", "201-363238/1");
		parametros.put("TIPO_ORIGEN", "Cuenta única");
		parametros.put("NUMERO_DESTINO", "168-356669/3");
		parametros.put("TIPO_DESTINO", "Cuenta única");
		parametros.put("TIPO_CUIT", "CUIT/CUIL del\r\n" + 
				"destinatario");
		parametros.put("CUIT", "27-36594757-6");
		parametros.put("BANCO", BancoEnum.SANTANDER_RIO.getDescripcion());
		parametros.put("DESTINATARIO", "Oquendo Romero  Panfilo Climac");
		parametros.put("FECHA_EJECUCION", "13/09/2017");
		parametros.put("CONCEPTO", "VAR");
		parametros.put("DESCRIPCION", "Varios");
		parametros.put("PLAZO_ACREDITACION", "48 hs");
		parametros.put("NUMERO_COMPROBANTE", "10313144");
		parametros.put("AVISO", "No");
		return parametros;
	}
}

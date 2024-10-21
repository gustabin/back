package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.impl.ReporteDescuentoChequesPDFDAOImpl;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequeSimuladoDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequeAceptadoViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequeRechazadoViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ReporteDescuentoChequesPDFDAOTest {

	@InjectMocks
	ReporteDescuentoChequesPDFDAO pdfDao = new ReporteDescuentoChequesPDFDAOImpl();

	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Mock
	private AltaChequesViewOut chequesView;

	private ApplicationContext appContext = new ClassPathXmlApplicationContext();

	@Test
	public void testearDescargaPdfOk() throws DAOException, IllegalAccessException, FileNotFoundException, IOException {
		FieldUtils.writeField(pdfDao, "imagenLogoTop",
				appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"), true);
		FieldUtils.writeField(pdfDao, "imagenLogoCabecera",
				appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(pdfDao, "imagenLogoPie",
				appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
		Mockito.when(chequesView.obtenerJasper()).thenReturn(appContext
				.getResource("classpath:/report/descuentoCheques/operacion-alta-cheques.jasper").getFile().getPath());
		Mockito.when(chequesView.obtenerParametrosPDF()).thenReturn(obtenerParametrosPDF());

		Reporte reporte = pdfDao.obtenerOperacionPDF(chequesView);
		ReporteView reporteView = ReporteView.fromReporte(reporte);
		Respuesta<ReporteView> resRep = respuestaFactory.crearRespuestaOk(ReporteView.class, reporteView);
		Gson gson = new Gson();
		String res = gson.toJson(resRep);
		FileUtils.writeByteArrayToFile(new File("C:/tools/comprobanteTest.pdf"), reporte.getBytes());
		Assert.assertNotNull(reporte.getBytes());
	}

	private Map<String, Object> obtenerParametrosPDF() {
		Gson gson = new Gson();
		AltaChequesViewOut viewOut = gson.fromJson(
				"{\"importeAcreditar\":\"450,00\",\"importeTotal\":\"500,00\",\"cuentaCredito\":\"000-060162/3\",\"numeroDeOperacion\":\"218564\",\"chequesDescontados\":\"10\",\"importeTotalCheque\":\"500,00\",\"importeImpuestos\":\"0,00\",\"importeIntereses\":\"0,00\",\"importeAcreditarCheque\":\"450,00\",\"listaAceptados\":[{\"numeroCheque\":\"07200010361000016139000002306700\",\"banco\":\"SANTANDER\",\"fechaPago\":\"01/12/2017\",\"importeTotal\":\"50,00\",\"importeImpuestos\":\"0,00\",\"importeIntereses\":\"0,00\",\"importeAAcreditar\":\"50,00\"},{\"numeroCheque\":\"07200010361963858252000001716700\",\"banco\":\"SANTANDER\",\"fechaPago\":\"01/12/2017\",\"importeTotal\":\"50,00\",\"importeImpuestos\":\"0,00\",\"importeIntereses\":\"0,00\",\"importeAAcreditar\":\"50,00\"},{\"numeroCheque\":\"07200010361963858283000001716700\",\"banco\":\"SANTANDER\",\"fechaPago\":\"01/12/2017\",\"importeTotal\":\"50,00\",\"importeImpuestos\":\"0,00\",\"importeIntereses\":\"0,00\",\"importeAAcreditar\":\"50,00\"},{\"numeroCheque\":\"07200010361963858498000001716700\",\"banco\":\"SANTANDER\",\"fechaPago\":\"01/12/2017\",\"importeTotal\":\"50,00\",\"importeImpuestos\":\"0,00\",\"importeIntereses\":\"0,00\",\"importeAAcreditar\":\"50,00\"},{\"numeroCheque\":\"07200010361963858658000001716700\",\"banco\":\"SANTANDER\",\"fechaPago\":\"01/12/2017\",\"importeTotal\":\"50,00\",\"importeImpuestos\":\"0,00\",\"importeIntereses\":\"0,00\",\"importeAAcreditar\":\"50,00\"},{\"numeroCheque\":\"07228016428810612987000032056314\",\"banco\":\"SANTANDER\",\"fechaPago\":\"01/12/2017\",\"importeTotal\":\"50,00\",\"importeImpuestos\":\"0,00\",\"importeIntereses\":\"0,00\",\"importeAAcreditar\":\"50,00\"},{\"numeroCheque\":\"07228016428810613034000032056314\",\"banco\":\"SANTANDER\",\"fechaPago\":\"01/12/2017\",\"importeTotal\":\"50,00\",\"importeImpuestos\":\"0,00\",\"importeIntereses\":\"0,00\",\"importeAAcreditar\":\"50,00\"},{\"numeroCheque\":\"07228016428810613164000032056314\",\"banco\":\"SANTANDER\",\"fechaPago\":\"01/12/2017\",\"importeTotal\":\"50,00\",\"importeImpuestos\":\"0,00\",\"importeIntereses\":\"0,00\",\"importeAAcreditar\":\"50,00\"},{\"numeroCheque\":\"07228016428810613171000032056314\",\"banco\":\"SANTANDER\",\"fechaPago\":\"01/12/2017\",\"importeTotal\":\"50,00\",\"importeImpuestos\":\"0,00\",\"importeIntereses\":\"0,00\",\"importeAAcreditar\":\"50,00\"},{\"numeroCheque\":\"07228016428810613218000032056314\",\"banco\":\"SANTANDER\",\"fechaPago\":\"01/12/2017\",\"importeTotal\":\"50,00\",\"importeImpuestos\":\"0,00\",\"importeIntereses\":\"0,00\",\"importeAAcreditar\":\"50,00\"}],\"listaRechazados\":[],\"comisionAdic\":\"50,00\",\"tasaAplicada\":\"0,00 %\",\"tasaEfectivaAnual\":\"0,00 %\",\"costoFinancieroTotal\":\"0,00 %\",\"fechaAlta\":\"02/11/2016\",\"legal1\":\"legal1\",\"legal2\":\"Esta comisión se verá reflejada en el resumen de cuenta\",\"legal3\":\"Sujeto a confirmación de la presente operación de parte de la Sucursal respectiva.\",\"legal4\":\"La tasa mencionada se encuentra sujeta a confirmación por parte de Banco Santander Río S.A. La simulación es al solo efecto de la consulta y no de carácter contractual.\",\"mensajeFeedback\":\"mensaje feedback\",\"fechaHora\":\"06/06/2018 - 11:41\"}",
				AltaChequesViewOut.class);
		return viewOut.obtenerParametrosPDF();
	}
}

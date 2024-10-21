package ar.com.santanderrio.obp.servicios.prestamos.dao;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ComprobanteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ImporteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.TasaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.UvaView;
import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.ReportePrestamoDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.view.ItemView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximaCuotaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportSimulacionPrestamosView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ResultadoSimulacionView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;

@RunWith(MockitoJUnitRunner.class)
public class ReportePrestamoDAOTest {
	
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
	@InjectMocks
	ReportePrestamoDAO prestamoDAO = new ReportePrestamoDAOImpl();
	
	@Mock
	ComprobanteView detalleView;
	
	@Mock
	ReportSimulacionPrestamosView reportSimulacionPrestamosView;
	
	@Mock
	ProximaCuotaView proximaCuota;
	
	private ApplicationContext appContext = new ClassPathXmlApplicationContext();
	
	
	@InjectMocks
    ReportSimulacionPrestamosView reportSimulacionPrestamosViewImpl = new ReportSimulacionPrestamosView();
	
	
	@Test
	public void testReporteComprobantePDFTransferencia() throws IllegalAccessException, DAOException, IOException {
		FieldUtils.writeField(prestamoDAO, "imagenLogoTop", appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"), true);
		FieldUtils.writeField(prestamoDAO, "imagenLogoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(prestamoDAO, "imagenLogoPie", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
		FieldUtils.writeField(prestamoDAO, "imagenLogoPrendario", appContext.getResource("classpath:/report/comprobantes/super-prestamo-prendario.png"), true);
		FieldUtils.writeField(prestamoDAO, "imagenLogoPersonal", appContext.getResource("classpath:/report/comprobantes/super-prestamo-personal.png"), true);
		FieldUtils.writeField(prestamoDAO, "imagenLogoHipotecario", appContext.getResource("classpath:/report/comprobantes/super-prestamo-hipotecario.png"), true);
		Mockito.when(detalleView.obtenerJasper()).thenReturn(appContext.getResource("classpath:/report/prestamos/comprobante/pago-prestamo.jasper").getFile().getPath());
		Mockito.when(detalleView.obtenerParametrosPDF(false)).thenReturn(obtenerParametrosPDF());
		Mockito.when(detalleView.getTituloComprobante()).thenReturn("Comprobante de pago de cuota de Préstamo");
		Mockito.when(detalleView.getTipoPrestamo()).thenReturn("UVA");
		Reporte reporte = prestamoDAO.obtenerReportePrestamoPDF(detalleView);
		FileUtils.writeByteArrayToFile(new File("C:/tools/pagoPrestamo.pdf"), reporte.getBytes());
		Assert.assertNotNull(reporte.getBytes());
	}

	private HashMap<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("TITULO_COMPROBANTE", "Comprobante de pago de cuota de Préstamo");
		parametros.put("IMPORTE_ACREDITAR", "$ 734,00");
		parametros.put("TIPO_PRESTAMO", "Super Préstamo Personal");
		parametros.put("NUMERO_PRESTAMO", "066-03519064787/6");
		parametros.put("TITULAR", "Vitoldo sofanor alain conquista");
		parametros.put("TIPO_DOC", "CUIL");
		parametros.put("NRO_DOC", "20-21767058-7");
		parametros.put("CONDICION_IVA", "Consumidor final");
		parametros.put("NRO_CUENTA_DEBITO", "066-102066/7");
		parametros.put("TIPO_CUENTA_DEBITO", "Cuenta única");
		parametros.put("CUOTA_PRESTAMO", "3");
		parametros.put("PLAZO_PRESTAMO", "25");
		parametros.put("SALDO_SIN_AJUSTAR", "$ 10.164,35");
		parametros.put("IMPORTE_CUOTA", "734,00");
		List<ImporteView> importes = new ArrayList<ImporteView>();
		ImporteView importeView = new ImporteView();
		importeView.setLabel("Capital  para que se alargue y todo eso pero nasidn ai nasdi nasdi uuu yy er");
		importeView.setImporte("$ 286,16");
		importes.add(importeView);
		parametros.put("IMPORTES", importes);
		List<TasaView> tasas = new ArrayList<TasaView>();
		TasaView tasa = new TasaView();
		tasa.setLabel("Tasa Nominal Anual de nuevo para que se alargue y todo eso");
		tasa.setTasa("45,00 %");
		tasas.add(tasa);
		parametros.put("TASAS", tasas);
		List<UvaView> ubas = new ArrayList<UvaView>();
		UvaView uba = new UvaView();
		uba.setLabel("ubaDato para que la banda se alargue pero si sigo probando no se si la banda");
		uba.setUva("pepepepepe");
		ubas.add(uba);
		UvaView uba2 = new UvaView();
		uba2.setLabel("ubaDato para que la banda se alargue pero si sigo probando no se si la banda(mrk2)");
		uba2.setUva("pepepepepe");
		ubas.add(uba2);
		parametros.put("UBAS", ubas);
		parametros.put("SALDO_DEUDA_CAPITAL", "$ 9.878,19");
		parametros.put("FECHA_VENCIMIENTO", "15/03/2018");
		parametros.put("NRO_COMPROBANTE", "27162602");
		parametros.put("COSTO_FINANCIERO_CON_IMPUESTOS", "CFTEA : 45.82000%");
		parametros.put("COSTO_FINANCIERO_SIN_IMPUESTOS", "CFTEA : 45.82000%");
		parametros.put("FECHA_ACTUAL", "29/06/2018 - 15:05");
		return parametros;
	}
	
	
	
	@Test
	public void testReporteComprobantePDFCalculador() throws IllegalAccessException, DAOException, IOException {
		FieldUtils.writeField(prestamoDAO, "imagenLogoTop",
				appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"), true);
		FieldUtils.writeField(prestamoDAO, "imagenLogoCabecera",
				appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(prestamoDAO, "imagenLogoPie",
				appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);;
		FieldUtils.writeField(prestamoDAO, "imagenLogoPrendario",
		        appContext.getResource("classpath:/report/comprobantes/super-prestamo-prendario.png"), true);
		FieldUtils.writeField(prestamoDAO, "imagenLogoPersonal",
				appContext.getResource("classpath:/report/comprobantes/super-prestamo-personal.png"), true);
        FieldUtils.writeField(prestamoDAO, "imagenLogoHipotecario",
                appContext.getResource("classpath:/report/comprobantes/super-prestamo-hipotecario.png"), true);
		Mockito.when(reportSimulacionPrestamosView.obtenerJasper()).thenReturn(
				appContext.getResource("classpath:/report/prestamos/comprobante/calculador-prestamo.jasper").getFile().getPath());
		Mockito.when(reportSimulacionPrestamosView.obtenerParametrosPDF(false)).thenReturn(obtenerParametrosCalculadorPDF());
		Mockito.when(reportSimulacionPrestamosView.getTituloComprobante())
				.thenReturn("Comprobante de solicitud de Súper Préstamo Personal UVA");
		Mockito.when(reportSimulacionPrestamosView.getTipoPrestamo()).thenReturn("PERSONAL");
		
		Reporte reporte = prestamoDAO.obtenerReportePrestamoPDF(reportSimulacionPrestamosView);
        
		FileUtils.writeByteArrayToFile(new File("C:/tools/calculadorComprobanteTest.pdf"),reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
	}

	private HashMap<String, Object> obtenerParametrosCalculadorPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("IMPORTE_ACREDITAR", "$ 734,00");
		List<ItemView> datosDelPrestamo = new ArrayList<ItemView>();
		ItemView itemView = new ItemView();
		itemView.setLabel("Tipo cuenta");
		itemView.setValor("066-102066/7 <br/> Cuenta unica");
		datosDelPrestamo.add(itemView);
		parametros.put("DATOS_DEL_PRESTAMO", datosDelPrestamo);
		List<ItemView> datosPrimeraCuota = new ArrayList<ItemView>();
		ItemView itemView2 = new ItemView();
		itemView2.setLabel("label muy largo para testear que se agrande numero 2");
		itemView2.setValor("$ 1250,688 \n Cuenta unica");
		datosPrimeraCuota.add(itemView2);
		parametros.put("DATOS_PRIMERA_CUOTA", datosPrimeraCuota);
		List<ItemView> tasas = new ArrayList<ItemView>();
		ItemView itemView3 = new ItemView();
		itemView3.setLabel("label muy largo para testear que se agrandenumero 3");
		itemView3.setValor("$ 12555,688");
		tasas.add(itemView3);
		parametros.put("TASAS", tasas);
		parametros.put("NRO_COMPROBANTE", "27162602");
		parametros.put("FECHA_ACTUAL", "29/06/2018 - 15:05");
		parametros.put("COSTO_FINANCIERO_CON_IMPUESTOS", "CFTEA : 27.82000%");
		parametros.put("COSTO_FINANCIERO_SIN_IMPUESTOS", "CFTEA : 27.82000%");
		parametros.put("LEGAL1", "legal1");
		return parametros;
	}
	
//	@Ignore
	@Test
	public void testReporteComprobantePDFDetalleCuotaPaga() throws IllegalAccessException, DAOException, IOException {
		FieldUtils.writeField(prestamoDAO, "imagenLogoTop",
				appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"), true);
		FieldUtils.writeField(prestamoDAO, "imagenLogoCabecera",
				appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(prestamoDAO, "imagenLogoPie",
				appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);;
		FieldUtils.writeField(prestamoDAO, "imagenLogoPersonal",
				appContext.getResource("classpath:/report/comprobantes/super-prestamo-personal.png"), true);
		Mockito.when(proximaCuota.obtenerJasper()).thenReturn(
				appContext.getResource("classpath:/report/prestamos/comprobante/detalle-prestamo.jasper").getFile().getPath());
		Mockito.when(proximaCuota.obtenerParametrosPDF(false)).thenReturn(obtenerDetalleCuotaPagaPDF());
		Mockito.when(proximaCuota.getTituloComprobante())
				.thenReturn("Detalle de pago");
		Mockito.when(proximaCuota.getTipoPrestamo()).thenReturn("PERSONAL");
		Reporte reporte = prestamoDAO.obtenerReportePrestamoPDF(proximaCuota);
        FileUtils.writeByteArrayToFile(new File("C:/tools/detalleCuota.pdf"),reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
	}

	private HashMap<String, Object> obtenerDetalleCuotaPagaPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("TITULO_COMPROBANTE", "Detalle de pago");
		parametros.put("IMPORTE_ACREDITAR", "$ 734,00");
		parametros.put("TIPO_PRESTAMO", "Súper Préstamo Personal");
		parametros.put("NUMERO_PRESTAMO", "066-03519064789/0");
		parametros.put("TITULAR", "PEPEPEPEPE PPEPEPE PEPPEPEPE PE");
		parametros.put("CUIL", "20-37866881-7");
		parametros.put("CUENTA_DEBITO", "<b>Cuenta \"pepe\"</b><br/>Cuenta única en $<br/>066-102066/7");
		parametros.put("NRO_CUOTA", "2");
		parametros.put("IMPORTE_CUOTA", "$ 687,98");
		parametros.put("CAPITAL", "$ 171,60");
		parametros.put("INTERESES_COMPENSATORIOS", "$ 245,81");
		parametros.put("SEGURO", "$ 0,00");
		parametros.put("TITULO_SEGURO", "Seguro (2)");
		parametros.put("OTROS_IMPUESTOS", "$ 11,00");
		parametros.put("TITULO_OTROS_IMPUESTOS", "Otros impuestos (3)");
		parametros.put("GASTOS_ADMINISTRATIVOS", "$ 734,00");
		parametros.put("TASA_EFECTIVA_ANUAL", "55,55 %");
		parametros.put("TASA_NOMINAL_ANUAL", "45,00 %");
		parametros.put("FECHA_VENCIMIENTO", "15/01/2018");
		parametros.put("FECHA_ACTUAL", "29/06/2018 - 15:05");
		parametros.put("COSTO_EFECTIVO_CON_IMPUESTOS", "CFTEA : 45.82000%");
		parametros.put("COSTO_EFECTIVO_SIN_IMPUESTOS", "CFTEA : 45.82000%");
		parametros.put("LEGAL1", "(1) De existir diferencia entre la sumatoria de los rubros que se detalla y el \"Importe de la cuota\", la misma corresponde a los intereses punitorios.");
		parametros.put("LEGAL2", "(2) Incluye Cargo por Seguro de Vida, e invalidez total y permanente solo si el Préstamo fue otorgado y desembolsado hasta el 31/08/2016, inclusive.");
		parametros.put("LEGAL3", "(3) IVA y Sellados.");
		return parametros;
	}
	

	@Test
    public void obtenerParametrosPDFTest() {
        
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("343243242323");
        cuenta.setNroSucursal("4343");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        ResultadoSimulacionView resultadoSimulacionView = new ResultadoSimulacionView.ResultadoSimulacionViewBuilder()
               .importe(new BigDecimal("13261")).importeNetoItem("00000000132610000").cuentaDestinoItem(cuenta)
               .cantidadCuotasItem("6").motivoPrestamoItem(new DestinoPrestamo())
               .importePrimeraCuotaItem("00000000026142500").fechaPrimerPagoItem("23/05/2017")
               .capitalInteresesPeriodoItem("00000000025027900").cargoSeguroVidaItem("00000000000000000")
               .ivaItem("00000000000975100").otrosImpuestosItem("00000000000139500").tipoTasaItem("F")
               .tnaItem("038730000").teaItem("046401294").cftnaItem("046800000").cftnaSinImpItem("038500000").build();
        reportSimulacionPrestamosViewImpl.setSimulacion(resultadoSimulacionView);
        HashMap<String, Object> hash =  reportSimulacionPrestamosViewImpl.obtenerParametrosPDF(false);
        for (String name: hash.keySet()){
           Object value = hash.get(name);
           System.out.println(name + " " + value);
        }
        Assert.assertNotNull(hash);
        
    }
	
}

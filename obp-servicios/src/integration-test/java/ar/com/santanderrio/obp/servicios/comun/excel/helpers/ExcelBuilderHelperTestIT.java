/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.excel.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.excel.helpers.ExcelBuilderHelperTestIT.ExcelBuilderHelperTestConfiguration;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientoExcelItem;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosExcel;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosExcelCabecera;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.excel.FciCuentaTitulosExcel;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.excel.FciExcel;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.excel.FciGeneralExcel;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.excel.FciTotalesCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.excel.CuentaTitulosPFExcelGeneral;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.excel.TenenciaPlazoFijoExcel;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.excel.TenenciaPlazoFijoExcelValores;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.excel.TotalesPlazoFijoExcel;
import ar.com.santanderrio.obp.servicios.inversiones.tenenciaconsolidada.excel.InfoTenenciaConsolidadaCuentaExcel;
import ar.com.santanderrio.obp.servicios.inversiones.tenenciaconsolidada.excel.InfoTenenciaConsolidadaExcel;
import ar.com.santanderrio.obp.servicios.inversiones.tenenciaconsolidada.excel.InfoTenenciaConsolidadaGeneralExcel;
import ar.com.santanderrio.obp.servicios.inversiones.tenenciaconsolidada.excel.InfoTenenciaConsolidadaPorMonedaExcel;
import ar.com.santanderrio.obp.servicios.inversiones.tenenciaconsolidada.excel.TotalesTenenciaConsolidadaExcel;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.excel.CuentaTitulosExcel;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.excel.CuentasTitulosExcelGeneral;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.excel.InfoTenenciasExcel;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.excel.InfoTotalesTenenciasExcel;
import ar.com.santanderrio.obp.servicios.prestamos.view.CuotasFechaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximaCuotaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximasCuotasView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionDetalleDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionLineaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultarFinanciacionWrapper;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesLineaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.MovimientosTarjetaExcel2;

/**
 * The Class ExcelBuilderHelperTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ExcelBuilderHelperTestConfiguration.class })
@ActiveProfiles(Profiles.TEST)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ExcelBuilderHelperTestIT {

	/** The helper. */
	@Autowired
	private ExcelBuilderHelper helper1;
	/** The helper. */
	@Autowired
	private ExcelBuilderHelper2 helper2;
	
	/**
	 * The Class ExcelBuilderHelperTestConfiguration.
	 */
	@Configuration
	@ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.comun.excel.helpers",
			"ar.com.santanderrio.obp.base.jexl" })
	public static class ExcelBuilderHelperTestConfiguration {

	}

	@Test
	public void testUltimosConsumosDeTarjetas() throws Exception {
		Cliente cliente = new Cliente();
		Segmento segmento = new Segmento();
		
		cliente.setSegmento(segmento);

		try {
			byte[] excel = helper2.hacerExcel(cliente, "excelUltimosConsumosYPendientes",
					new MovimientosTarjetaExcel2(conseguirConfirmados(false), conseguirPendientes(false)));

			FileOutputStream out = new FileOutputStream(new File(
					"C://Temp/tarjetas4.xls"));
			out.write(excel);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUltimosConsumosDeTarjetasRecargables() throws Exception {
		Cliente cliente = new Cliente();
		Segmento segmento = new Segmento();
		segmento.setUniversidad(true);
		cliente.setSegmento(segmento);

		try {
			byte[] excel = helper2.hacerExcel(cliente, "excelUltimosConsumosYPendientesRecargables",
					new MovimientosTarjetaExcel2(conseguirConfirmados(true), conseguirPendientes(true)));

			FileOutputStream out = new FileOutputStream(new File(
					"C://Temp/tarjetas3.xls"));
			out.write(excel);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConsultaDeFinanciaciones() throws Exception {
		Cliente cliente = new Cliente();
		Segmento segmento = new Segmento();
		segmento.setUniversidad(true);
		cliente.setSegmento(segmento);

		try {
			byte[] excel = helper2.hacerExcel(cliente, "consultadeFinanciaciones",
					new ConsultarFinanciacionWrapper("12/12/2017", obtenerListaConsultaFinanciaciones1()));

			FileOutputStream out = new FileOutputStream(new File(
					"C://Temp/tarjetas5.xls"));
			out.write(excel);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConsultadeCuotasPendientes() throws Exception {
		Cliente cliente = new Cliente();
		Segmento segmento = new Segmento();
		segmento.setUniversidad(true);
		cliente.setSegmento(segmento);

		try {
			byte[] excel = helper2.hacerExcel(cliente, "consultadeCuotasPendientes",
					new CuotasPendientesDTO(new BigDecimal("12.00"), obtenerListaCuotasPendientes(), "12/12/2018"));

			FileOutputStream out = new FileOutputStream(new File(
					"C://Temp/tarjetas6.xls"));
			out.write(excel);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void plazosFijosBancaRetailOK() throws Exception {
		Cliente cliente = new Cliente();
		Segmento segmento = new Segmento();
		cliente.setSegmento(segmento);

		TenenciaPlazoFijoExcel infoPlazoFijoExcel = crearInfoPlazoFijoExcel();
		
		try {
			byte[] excel = helper2.hacerExcel(cliente, "PlazosFijos", infoPlazoFijoExcel);

			FileOutputStream out = new FileOutputStream(new File("C://Temp/PlazoFijoRTL.xls"));
			out.write(excel);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void plazosFijosBancaPrivadaOK() throws Exception {
		Cliente cliente = new Cliente();
		Segmento segmento = new Segmento();
		cliente.setSegmento(segmento);

		CuentaTitulosPFExcelGeneral infoPlazoFijoExcel = crearInfoPlazoFijoBPExcel();
		
		try {
			byte[] excel = helper2.hacerExcel(cliente, "PlazosFijosBP", infoPlazoFijoExcel);

			FileOutputStream out = new FileOutputStream(new File("C://Temp/PlazoFijoBP.xls"));
			out.write(excel);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private CuentaTitulosPFExcelGeneral crearInfoPlazoFijoBPExcel() {
		
		CuentaTitulosPFExcelGeneral cuentaTitulosPFExcelGeneral = new CuentaTitulosPFExcelGeneral();
		List<TenenciaPlazoFijoExcel> listaCuentasTitulosExcel = new ArrayList<TenenciaPlazoFijoExcel>();
		
		TenenciaPlazoFijoExcel tenenciaPlazoFijoExcel = new TenenciaPlazoFijoExcel();
		tenenciaPlazoFijoExcel.setIntervinientes("Sanchez Ferrer, Amelio Ramiro");
		tenenciaPlazoFijoExcel.setNumeroCuenta("250-354914/0");
		
		TenenciaPlazoFijoExcelValores tenenciaVacia = new TenenciaPlazoFijoExcelValores();
		tenenciaVacia.setTipo("");
		tenenciaVacia.setFechaVencimiento("");
		tenenciaVacia.setCapitalInicial("");
		tenenciaVacia.setTenenciaValuadaHoy("");
		tenenciaVacia.setTna("");
		tenenciaVacia.setResultado("");
		
		List<TenenciaPlazoFijoExcelValores> tenenciaPlazoFijoPesos = new ArrayList<TenenciaPlazoFijoExcelValores>();
		TenenciaPlazoFijoExcelValores tenenciaValor1 = new TenenciaPlazoFijoExcelValores();
		tenenciaValor1.setTipo("Tradicional");
		tenenciaValor1.setFechaVencimiento("21/01/2019");
		tenenciaValor1.setCapitalInicial("$ 500,00");
		tenenciaValor1.setTenenciaValuadaHoy("$ 508,45");
		tenenciaValor1.setTna("31,00%");
		tenenciaValor1.setResultado("$ 8,45");
		tenenciaPlazoFijoPesos.add(tenenciaVacia);
		
		TenenciaPlazoFijoExcelValores tenenciaValor2 = new TenenciaPlazoFijoExcelValores();
		tenenciaValor2.setTipo("Tradicional");
		tenenciaValor2.setFechaVencimiento("21/05/2019");
		tenenciaValor2.setCapitalInicial("$ 600,00");
		tenenciaValor2.setTenenciaValuadaHoy("$ 608,45");
		tenenciaValor2.setTna("22,00%");
		tenenciaValor2.setResultado("$ 8,45");
//		tenenciaPlazoFijoPesos.add(tenenciaValor2);
		
		tenenciaPlazoFijoExcel.setTenenciaPlazoFijoPesos(tenenciaPlazoFijoPesos);
		
		List<TenenciaPlazoFijoExcelValores> tenenciaPlazoFijoDolares = new ArrayList<TenenciaPlazoFijoExcelValores>();
		TenenciaPlazoFijoExcelValores tenenciaValor3 = new TenenciaPlazoFijoExcelValores();
		tenenciaValor3.setTipo("Tradicional");
		tenenciaValor3.setFechaVencimiento("21/01/2019");
		tenenciaValor3.setCapitalInicial("u$s 500,00");
		tenenciaValor3.setTenenciaValuadaHoy("u$s 508,45");
		tenenciaValor3.setTna("31,00%");
		tenenciaValor3.setResultado("u$s 8,45");
		tenenciaPlazoFijoDolares.add(tenenciaValor3);
		
		TenenciaPlazoFijoExcelValores tenenciaValor4 = new TenenciaPlazoFijoExcelValores();
		tenenciaValor4.setTipo("Tradicional");
		tenenciaValor4.setFechaVencimiento("21/05/2019");
		tenenciaValor4.setCapitalInicial("u$s 600,00");
		tenenciaValor4.setTenenciaValuadaHoy("u$s 608,45");
		tenenciaValor4.setTna("22,00%");
		tenenciaValor4.setResultado("u$s 8,45");
		tenenciaPlazoFijoDolares.add(tenenciaValor4);
		
		tenenciaPlazoFijoExcel.setTenenciaPlazoFijoDolares(tenenciaPlazoFijoDolares);
		
		TotalesPlazoFijoExcel totalesGrilla = new TotalesPlazoFijoExcel();
		
		totalesGrilla.setCapitalInicialArs("$ 437,228");
		totalesGrilla.setCapitalInicialUsd("u$s 4.721,00");
		totalesGrilla.setResultadoArs("$ 372,00");
		totalesGrilla.setResultadoUsd("u$s 3.721,00");
		totalesGrilla.setTenenciaHoyArs("$ 473,621");
		totalesGrilla.setTenenciaHoyUsd("u$s 57.643,00");

		tenenciaPlazoFijoExcel.setTotalesGrilla(totalesGrilla);
		tenenciaPlazoFijoExcel.setTienePlazosFijosPesos(Boolean.FALSE);
		tenenciaPlazoFijoExcel.setTienePlazosFijosDolares(Boolean.TRUE);

		listaCuentasTitulosExcel.add(tenenciaPlazoFijoExcel);
		
		TenenciaPlazoFijoExcel tenenciaPlazoFijoExcel2 = new TenenciaPlazoFijoExcel();
		tenenciaPlazoFijoExcel2.setIntervinientes("Pereira Fernando, Carrera Agustina");
		tenenciaPlazoFijoExcel2.setNumeroCuenta("250-639111/2");
		
		List<TenenciaPlazoFijoExcelValores> tenenciaPlazoFijoPesos2 = new ArrayList<TenenciaPlazoFijoExcelValores>();

		tenenciaPlazoFijoPesos2.add(tenenciaValor1);
		tenenciaPlazoFijoPesos2.add(tenenciaValor2);
		
		List<TenenciaPlazoFijoExcelValores> tenenciaPlazoFijoDolares2 = new ArrayList<TenenciaPlazoFijoExcelValores>();


		
		tenenciaPlazoFijoDolares2.add(tenenciaVacia);
		
		tenenciaPlazoFijoExcel2.setTenenciaPlazoFijoPesos(tenenciaPlazoFijoPesos2);
		tenenciaPlazoFijoExcel2.setTenenciaPlazoFijoDolares(tenenciaPlazoFijoDolares2);
		
		tenenciaPlazoFijoExcel2.setTienePlazosFijosPesos(Boolean.TRUE);
		
		TotalesPlazoFijoExcel totalesGrilla2 = new TotalesPlazoFijoExcel();
		
		totalesGrilla2.setCapitalInicialArs("$ 437,228");
		totalesGrilla2.setResultadoArs("$ 372,00");
		totalesGrilla2.setTenenciaHoyArs("$ 473,621");
		
		tenenciaPlazoFijoExcel2.setTotalesGrilla(totalesGrilla2);
		listaCuentasTitulosExcel.add(tenenciaPlazoFijoExcel2);
		
		cuentaTitulosPFExcelGeneral.setListaCuentasTitulosExcel(listaCuentasTitulosExcel);

		return cuentaTitulosPFExcelGeneral;
		
	}
	
	
	@Test
	public void titulosValoresBancaRetailOK() throws Exception {
		Cliente cliente = new Cliente();
		Segmento segmento = new Segmento();
		cliente.setSegmento(segmento);

		CuentasTitulosExcelGeneral cuentasTitulosExcel = crearInfoTitulosValoresExcel();
		
		try {
			byte[] excel = helper2.hacerExcel(cliente, "titulosValores", cuentasTitulosExcel);

			FileOutputStream out = new FileOutputStream(new File("C://Temp/titulosValoresRTL.xls"));
			out.write(excel);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private	CuentasTitulosExcelGeneral crearInfoTitulosValoresExcel() {

		CuentasTitulosExcelGeneral cuentaTitulosGeneralExcel = new CuentasTitulosExcelGeneral(); 
		List<CuentaTitulosExcel> listaCuentaTitulosExcel = new ArrayList<CuentaTitulosExcel>();
		CuentaTitulosExcel cuentaTitulosExcel = new CuentaTitulosExcel();
		
		cuentaTitulosExcel.setNumeroCuenta("388234/5");
		cuentaTitulosExcel.setIntervinientes("Tarjota, Pepe / Alfaro, Gustavo");
		
		List<InfoTenenciasExcel> listaVacia = new ArrayList<InfoTenenciasExcel>();
		
		InfoTenenciasExcel tenencia0 = new InfoTenenciasExcel();
		tenencia0.setTipo("");
		tenencia0.setDescripcion("");
		tenencia0.setCantidadValorNominal("");
		tenencia0.setPrecioMercado("");
		tenencia0.setTenenciaValuadaHoy("");
		tenencia0.setResultado("");
		
		listaVacia.add(tenencia0);
		
		List<InfoTenenciasExcel> tenenciasPesos = new ArrayList<InfoTenenciasExcel>();
		InfoTenenciasExcel tenencia1 = new InfoTenenciasExcel();
		tenencia1.setTipo("Títulos Públicos");
		tenencia1.setDescripcion("BONOS DEL TESORO $ TASA POL. MONETARIA 21/06/20");
		tenencia1.setCantidadValorNominal("1000");
		tenencia1.setPrecioMercado("$ 13,40");
		tenencia1.setTenenciaValuadaHoy("$ 1.259,50");
		tenencia1.setResultado("$ 1.259,50");
		
		InfoTenenciasExcel tenencia2 = new InfoTenenciasExcel();
		tenencia2.setTipo("Títulos Públicos");
		tenencia2.setDescripcion("LETRAS DEL BCRA VTO 07/06/2017");
		tenencia2.setCantidadValorNominal("1000");
		tenencia2.setPrecioMercado("$ 5,40");
		tenencia2.setTenenciaValuadaHoy("$ 540,00");
		tenencia2.setResultado("$ 1.259,50");
		
		InfoTenenciasExcel tenencia3 = new InfoTenenciasExcel();
		tenencia3.setTipo("Títulos Privados");
		tenencia3.setDescripcion("F.F.VD GARBARINO S.A. 132 CL. A VTO 10/11/2017");
		tenencia3.setCantidadValorNominal("1000");
		tenencia3.setPrecioMercado("$ 120,00");
		tenencia3.setTenenciaValuadaHoy("$ 540,00");
		tenencia3.setResultado("$ 1.259,50");
		
		InfoTenenciasExcel tenencia4 = new InfoTenenciasExcel();
		tenencia4.setTipo("Acciones");
		tenencia4.setDescripcion("SAN MIGUEL S.A. B 1 V. ESC.");
		tenencia4.setCantidadValorNominal("1000");
		tenencia4.setPrecioMercado("$ 5,40");
		tenencia4.setTenenciaValuadaHoy("$ 540,00");
		tenencia4.setResultado("$ 1.259,50");
		
		tenenciasPesos.add(tenencia1);
		tenenciasPesos.add(tenencia2);
		tenenciasPesos.add(tenencia3);
		tenenciasPesos.add(tenencia4);
		
		InfoTotalesTenenciasExcel totalesCuenta1 = new InfoTotalesTenenciasExcel();
		totalesCuenta1.setResultadoArs("$ 2.879,50");
		totalesCuenta1.setTenenciaValuadaHoyArs("$ 2.879,50");
		totalesCuenta1.setResultadoUsd("u$s 2.879,50");
		totalesCuenta1.setTenenciaValuadaHoyUsd("u$s 2.879,50");
		
		cuentaTitulosExcel.setIsTenenciasPesos(Boolean.FALSE);
		cuentaTitulosExcel.setIsTenenciasDolares(Boolean.TRUE);
		
		cuentaTitulosExcel.setTotales(totalesCuenta1);
		cuentaTitulosExcel.setTenenciasPesos(listaVacia);
		cuentaTitulosExcel.setTenenciasDolares(tenenciasPesos);

		listaCuentaTitulosExcel.add(cuentaTitulosExcel);
		
		CuentaTitulosExcel cuentaTitulosExcel2 = new CuentaTitulosExcel();
				
		cuentaTitulosExcel2.setNumeroCuenta("576834/5");
		cuentaTitulosExcel2.setIntervinientes("Caruso, Ricardo");
		cuentaTitulosExcel2.setTenenciasPesos(tenenciasPesos);
		cuentaTitulosExcel2.setTenenciasDolares(listaVacia);
		
		InfoTotalesTenenciasExcel totalesCuenta2 = new InfoTotalesTenenciasExcel();
		totalesCuenta2.setResultadoArs("$ 2.879,50");
		totalesCuenta2.setTenenciaValuadaHoyArs("$ 2.879,50");
		totalesCuenta2.setResultadoUsd("u$s 2.879,50");
		totalesCuenta2.setTenenciaValuadaHoyUsd("u$s 2.879,50");
		
		cuentaTitulosExcel2.setTotales(totalesCuenta2);
		cuentaTitulosExcel2.setIsTenenciasPesos(Boolean.TRUE);
		cuentaTitulosExcel2.setIsTenenciasDolares(Boolean.FALSE);
				
		listaCuentaTitulosExcel.add(cuentaTitulosExcel2);
		
		CuentaTitulosExcel cuentaTitulosExcel3 = new CuentaTitulosExcel();
		
		cuentaTitulosExcel3.setNumeroCuenta("44432/5");
		cuentaTitulosExcel3.setIntervinientes("Messi, Lionel");
		cuentaTitulosExcel3.setTenenciasPesos(tenenciasPesos);
		cuentaTitulosExcel3.setTenenciasDolares(listaVacia);
		
		InfoTotalesTenenciasExcel totalesCuenta3 = new InfoTotalesTenenciasExcel();
		totalesCuenta3.setResultadoArs("$ 2.879,50");
		totalesCuenta3.setTenenciaValuadaHoyArs("$ 2.879,50");
		totalesCuenta3.setResultadoUsd("u$s 2.879,50");
		totalesCuenta3.setTenenciaValuadaHoyUsd("u$s 2.879,50");
		
		cuentaTitulosExcel3.setTotales(totalesCuenta3);
		cuentaTitulosExcel3.setIsTenenciasPesos(Boolean.TRUE);
		cuentaTitulosExcel3.setIsTenenciasDolares(Boolean.FALSE);
		
		listaCuentaTitulosExcel.add(cuentaTitulosExcel3);
		
		cuentaTitulosGeneralExcel.setCuentasTitulos(listaCuentaTitulosExcel);
		
		return cuentaTitulosGeneralExcel;
		
	}
	
	@Test
	public void FondosBancaRetailOK() throws Exception {
		Cliente cliente = new Cliente();
		Segmento segmento = new Segmento();
		cliente.setSegmento(segmento);

		FciGeneralExcel fondosExcel = crearInfoFondosExcel();
		
		try {
			byte[] excel = helper2.hacerExcel(cliente, "fondosComunesInversion", fondosExcel);

			FileOutputStream out = new FileOutputStream(new File("C://Temp/FondosRTL.xls"));
			out.write(excel);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private FciGeneralExcel crearInfoFondosExcel() {
		
		FciGeneralExcel fondosExcel = new FciGeneralExcel();
		List<FciCuentaTitulosExcel> listaCuentasTitulos = new ArrayList<FciCuentaTitulosExcel>();
		FciCuentaTitulosExcel cuentaTitulosExcel = new FciCuentaTitulosExcel();
		
		cuentaTitulosExcel.setNumeroCuenta("565029/3");
		cuentaTitulosExcel.setIntervinientes("Otero Aenlle, Mensajem Casildo Eufemio / Oschust, Matias Ronan Xavier / Sanchez Ferrer, Amelio Ramiro / Santander Almiron, Pelayo Cirguilo / Zampa De Fiorenza, Jacob Mustafa");
		
		List<FciExcel> listaFondosPesos = new ArrayList<FciExcel>();
		FciExcel fondo1 = new FciExcel();
		fondo1.setTipo("Money Market");
		fondo1.setFondo("Super Ahorro $");
		fondo1.setCuotapartes("136,6906");
		fondo1.setValorCuotaparte("$ 2,284738");
		fondo1.setTenenciaValuada("$ 312,30");
		fondo1.setResultado("$ 312,30");

		FciExcel fondo2 = new FciExcel();
		fondo2.setTipo("Money Market");
		fondo2.setFondo("Super Ahorro PLUS A");
		fondo2.setCuotapartes("51.7435,2293");
		fondo2.setValorCuotaparte("$ 20,281514");
		fondo2.setTenenciaValuada("$ 10.494.369,79");
		fondo2.setResultado("$ 10.494.369,79");

		FciExcel fondo3 = new FciExcel();
		fondo3.setTipo("Renta Fija en Pesos");
		fondo3.setFondo("Supergestión Mix VI");
		fondo3.setCuotapartes("136,6906");
		fondo3.setValorCuotaparte("$ 2,284738");
		fondo3.setTenenciaValuada("$ 312,30");
		fondo3.setResultado("$ 312,30");
		
		FciExcel fondo4 = new FciExcel();
		fondo4.setTipo("Renta Fija en Pesos");
		fondo4.setFondo("Superfondo Renta $");
		fondo4.setCuotapartes("51.7435,2293");
		fondo4.setValorCuotaparte("$ 20,281514");
		fondo4.setTenenciaValuada("$ 10.494.369,79");
		fondo4.setResultado("$ 10.494.369,79");
		
		listaFondosPesos.add(fondo1);
		listaFondosPesos.add(fondo2);
		listaFondosPesos.add(fondo3);
		listaFondosPesos.add(fondo4);
		
		cuentaTitulosExcel.setFciPesos(listaFondosPesos);
		cuentaTitulosExcel.setTieneFciPesos(Boolean.TRUE);
		
		List<FciExcel> listaFondosDolares = new ArrayList<FciExcel>();
		FciExcel fondoVacio = new FciExcel();
		fondoVacio.setTipo("");
		fondoVacio.setFondo("");
		fondoVacio.setCuotapartes("");
		fondoVacio.setValorCuotaparte("");
		fondoVacio.setTenenciaValuada("");
		fondoVacio.setResultado("");
		
		listaFondosDolares.add(fondoVacio);
		cuentaTitulosExcel.setFciDolares(listaFondosDolares);
		
		FciTotalesCuenta totales1 = new FciTotalesCuenta();
		totales1.setTotalTenenciaValuadaArs("$ 20.988.739,58");
		totales1.setTotalResultadoArs("$ 20.988.739,58");
		cuentaTitulosExcel.setTotales(totales1);
		
		FciCuentaTitulosExcel cuentaTitulosExcel2 = new FciCuentaTitulosExcel();

		cuentaTitulosExcel2.setNumeroCuenta("874029/3");
		cuentaTitulosExcel2.setIntervinientes("Tarjota, Pepe");
		
		List<FciExcel> listaFondosPesos2 = new ArrayList<FciExcel>();
		listaFondosPesos2.add(fondoVacio);
		
		List<FciExcel> listaFondosDolares2 = new ArrayList<FciExcel>();
		listaFondosDolares2.add(fondo1);
		listaFondosDolares2.add(fondo2);
		listaFondosDolares2.add(fondo3);
		listaFondosDolares2.add(fondo4);
		
		cuentaTitulosExcel2.setFciPesos(listaFondosPesos2);
		cuentaTitulosExcel2.setFciDolares(listaFondosDolares2);
		cuentaTitulosExcel2.setTieneFciDolares(Boolean.TRUE);
		
		FciTotalesCuenta totales2 = new FciTotalesCuenta();
		totales2.setTotalTenenciaValuadaUsd("U$S 20.988.739,58");
		totales2.setTotalResultadoUsd("U$S 20.988.739,58");
		cuentaTitulosExcel2.setTotales(totales2);
		
		listaCuentasTitulos.add(cuentaTitulosExcel);
		listaCuentasTitulos.add(cuentaTitulosExcel2);

		fondosExcel.setListaCuentasTitulos(listaCuentasTitulos);
		
		return fondosExcel;
	}
	
	
	@Test
	public void tenenciaConsolidadaRetailOK() throws Exception {
		Cliente cliente = new Cliente();
		Segmento segmento = new Segmento();
		cliente.setSegmento(segmento);

		InfoTenenciaConsolidadaExcel infoTenenciaConsolidadaExcel = crearInfoTenenciaConsolidadaExcel();
		
		try {
			byte[] excel = helper2.hacerExcel(cliente, "TenenciaConsolidadaRTL", infoTenenciaConsolidadaExcel);

			FileOutputStream out = new FileOutputStream(new File("C://Temp/TenenciaConsolidadaRTLPrueba.xls"));
			out.write(excel);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void tenenciaConsolidadaPrivadaOK() throws Exception {
		Cliente cliente = new Cliente();
		Segmento segmento = new Segmento();
		cliente.setSegmento(segmento);

		InfoTenenciaConsolidadaGeneralExcel infoTenenciaConsolidadaExcel = crearInfoTenenciaConsolidadaBPExcel();
		
		try {
			byte[] excel = helper2.hacerExcel(cliente, "TenenciaConsolidadaBP", infoTenenciaConsolidadaExcel);

			FileOutputStream out = new FileOutputStream(new File("C://Temp/TenenciaConsolidadaBPPrueba.xls"));
			out.write(excel);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void ultimosMovimientosRetailOK() throws ExcelBuildException {
		
		Cliente cliente = new Cliente();
		Segmento segmento = new Segmento();
		cliente.setSegmento(segmento);

		MovimientosExcel movimientosExcel = crearMovimientosExcel();
		formatearMontos(movimientosExcel);
		
		try {
			byte[] excel = helper1.hacerExcel(cliente, "UltimosMovimientos", movimientosExcel);

			FileOutputStream out = new FileOutputStream(new File("C://Temp/UltimosMovimientosRetailPrueba.xls"));
			out.write(excel);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	private MovimientosExcel crearMovimientosExcel() {
		
		MovimientosExcel movimientosExcel = new MovimientosExcel();
		MovimientosExcelCabecera movimientosExcelCabecera = new MovimientosExcelCabecera();
		
		movimientosExcelCabecera.setHasCtaCte(true);
		movimientosExcelCabecera.setIsConvenioPAS(true);
		movimientosExcelCabecera.setMostrarFiltroFecha(true);
		movimientosExcelCabecera.setMostrarFiltroImportes(false);
		movimientosExcelCabecera.setMostrarFiltroPalabraClave(false);
		movimientosExcelCabecera.setMostrarSaldoAperturado(true);
		movimientosExcelCabecera.setDispositivo("desktop");
		movimientosExcelCabecera.setFechaDesde("22/07/2019");
		movimientosExcelCabecera.setFechaHasta("22/07/2019");
		movimientosExcelCabecera.setImporteDesde("");
		movimientosExcelCabecera.setImporteHasta("");
		movimientosExcelCabecera.setMoneda("Pesos");
		movimientosExcelCabecera.setNumeroCuenta("000-063880/1");
		movimientosExcelCabecera.setPalabraClave("");
		movimientosExcelCabecera.setTipoCuenta("Cuenta única");
		movimientosExcel.setCabecera(movimientosExcelCabecera);
				
		List<MovimientoExcelItem> movimientos = new ArrayList<MovimientoExcelItem>();
		
		MovimientoExcelItem movimiento1 = new MovimientoExcelItem();
		movimiento1.setFechaHora("26/07/2019 12:21");
		movimiento1.setDescripcion("ACRED HABERES POR ONLINE BANKING   00720084002004516635ARS");
		movimiento1.setSucursalOrigen("0000 CASA CENTRA");
		movimiento1.setReferencia("001576531");
		movimiento1.setImporte(200.0);
		movimiento1.setSaldo(159211.04);
		movimiento1.setIsCajaDeAhoroEnPesos(true);
		movimiento1.setIsCuentaCorrienteEnPesos(false);
		movimiento1.setIsDelDia(false);
		movimiento1.setIsMovimientoEnDolares(false);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		movimientos.add(movimiento1);
		
		
		movimientosExcel.setMovimientos(movimientos);
		return movimientosExcel;
	}
	
	private void formatearMontos (MovimientosExcel movimientosExcel) {
		
		for (MovimientoExcelItem movimiento : movimientosExcel.getMovimientos()) {
			movimiento.setImporteString(ISBANStringUtils.formatearSaldo(new BigDecimal(movimiento.getImporte().toString())));
			movimiento.setSaldoString(ISBANStringUtils.formatearSaldo(new BigDecimal(movimiento.getSaldo().toString())));
		}
		
	}
	
	private InfoTenenciaConsolidadaGeneralExcel crearInfoTenenciaConsolidadaBPExcel() {
		
		InfoTenenciaConsolidadaGeneralExcel infoTenenciaConsolidadaGeneralExcel = new InfoTenenciaConsolidadaGeneralExcel();
		List<InfoTenenciaConsolidadaCuentaExcel> listaCuentas = new ArrayList<InfoTenenciaConsolidadaCuentaExcel>();
		InfoTenenciaConsolidadaCuentaExcel infoTenenciaConsolidadaCuentaExcel = new InfoTenenciaConsolidadaCuentaExcel();
		
		infoTenenciaConsolidadaCuentaExcel.setNumeroCuenta("250-354956/0");
		infoTenenciaConsolidadaCuentaExcel.setIntervinientes("Amelio Ramiro, Sanchez Pablo");
		
		List<InfoTenenciaConsolidadaPorMonedaExcel> listaTenenciasPesos = new ArrayList<InfoTenenciaConsolidadaPorMonedaExcel>();
		InfoTenenciaConsolidadaPorMonedaExcel tenencia1 = new InfoTenenciaConsolidadaPorMonedaExcel();
		tenencia1.setProducto("Plazos Fijos");
		tenencia1.setTenenciaValuadaCosto("$ 122.234,78");
		tenencia1.setTenenciaValuadaHoy("$ 125.303,34");
		tenencia1.setResultado("$ 3.068,56");
		
		InfoTenenciaConsolidadaPorMonedaExcel tenencia2 = new InfoTenenciaConsolidadaPorMonedaExcel();
		tenencia2.setProducto("Fondos Comunes de Inversión");
		tenencia2.setTenenciaValuadaCosto("$ 0,00");
		tenencia2.setTenenciaValuadaHoy("$ 48.822,78");
		tenencia2.setResultado("$ 48.822,78");
		
		InfoTenenciaConsolidadaPorMonedaExcel tenencia3 = new InfoTenenciaConsolidadaPorMonedaExcel();
		tenencia3.setProducto("Títulos Valores");
		tenencia3.setTenenciaValuadaCosto("$ 470,86");
		tenencia3.setTenenciaValuadaHoy("$ 57.080,00");
		tenencia3.setResultado("$ 56.609,14");
		
		InfoTenenciaConsolidadaPorMonedaExcel tenencia4 = new InfoTenenciaConsolidadaPorMonedaExcel();
		tenencia4.setProducto("Custodia monetaria");
		tenencia4.setTenenciaValuadaCosto("-");
		tenencia4.setTenenciaValuadaHoy("$ 120,00");
		tenencia4.setResultado("-");
		
		listaTenenciasPesos.add(tenencia1);
		listaTenenciasPesos.add(tenencia2);
		listaTenenciasPesos.add(tenencia3);
		listaTenenciasPesos.add(tenencia4);

		infoTenenciaConsolidadaCuentaExcel.setListaTenenciasPesos(listaTenenciasPesos);
		infoTenenciaConsolidadaCuentaExcel.setListaTenenciasDolares(listaTenenciasPesos);
		
		TotalesTenenciaConsolidadaExcel totales = new TotalesTenenciaConsolidadaExcel();
		
		totales.setTenenciaValuadaCostoArs("$ 122.705,64");
		totales.setTenenciaValuadaHoyArs("$ 231.326,12");
		totales.setResultadoArs("$ 108.500,48");
		totales.setTenenciaValuadaCostoUsd("u$s 122.705,64");
		totales.setTenenciaValuadaHoyUsd("u$s 231.326,12");
		totales.setResultadoUsd("u$s 108.500,48");
		
		infoTenenciaConsolidadaCuentaExcel.setTotales(totales);
		
		listaCuentas.add(infoTenenciaConsolidadaCuentaExcel);
		
		InfoTenenciaConsolidadaCuentaExcel infoTenenciaConsolidadaCuentaExcel2 = new InfoTenenciaConsolidadaCuentaExcel();
		
		infoTenenciaConsolidadaCuentaExcel2.setNumeroCuenta("250-354956/0");
		infoTenenciaConsolidadaCuentaExcel2.setIntervinientes("Amelio Ramiro, Sanchez Pablo");
		infoTenenciaConsolidadaCuentaExcel2.setListaTenenciasPesos(listaTenenciasPesos);
		infoTenenciaConsolidadaCuentaExcel2.setListaTenenciasDolares(listaTenenciasPesos);
		infoTenenciaConsolidadaCuentaExcel2.setTotales(totales);
		
		listaCuentas.add(infoTenenciaConsolidadaCuentaExcel2);
		infoTenenciaConsolidadaGeneralExcel.setListaCuentas(listaCuentas);
	
		return infoTenenciaConsolidadaGeneralExcel;
	
	}
	
	private InfoTenenciaConsolidadaExcel crearInfoTenenciaConsolidadaExcel() {
		
		InfoTenenciaConsolidadaExcel infoTenenciaConsolidadaExcel = new InfoTenenciaConsolidadaExcel();
		
		List<InfoTenenciaConsolidadaPorMonedaExcel> listaTenenciasPesos = new ArrayList<InfoTenenciaConsolidadaPorMonedaExcel>();
		InfoTenenciaConsolidadaPorMonedaExcel tenencia1 = new InfoTenenciaConsolidadaPorMonedaExcel();
		tenencia1.setProducto("Plazos Fijos");
		tenencia1.setTenenciaValuadaCosto("$ 122.234,78");
		tenencia1.setTenenciaValuadaHoy("$ 125.303,34");
		tenencia1.setResultado("$ 3.068,56");
		
		InfoTenenciaConsolidadaPorMonedaExcel tenencia2 = new InfoTenenciaConsolidadaPorMonedaExcel();
		tenencia2.setProducto("Fondos Comunes de Inversión");
		tenencia2.setTenenciaValuadaCosto("$ 0,00");
		tenencia2.setTenenciaValuadaHoy("$ 48.822,78");
		tenencia2.setResultado("$ 48.822,78");
		
		InfoTenenciaConsolidadaPorMonedaExcel tenencia3 = new InfoTenenciaConsolidadaPorMonedaExcel();
		tenencia3.setProducto("Títulos Valores");
		tenencia3.setTenenciaValuadaCosto("$ 470,86");
		tenencia3.setTenenciaValuadaHoy("$ 57.080,00");
		tenencia3.setResultado("$ 56.609,14");
		
		InfoTenenciaConsolidadaPorMonedaExcel tenencia4 = new InfoTenenciaConsolidadaPorMonedaExcel();
		tenencia4.setProducto("Custodia monetaria");
		tenencia4.setTenenciaValuadaCosto("-");
		tenencia4.setTenenciaValuadaHoy("$ 120,00");
		tenencia4.setResultado("-");
		
		listaTenenciasPesos.add(tenencia1);
		listaTenenciasPesos.add(tenencia2);
		listaTenenciasPesos.add(tenencia3);
		listaTenenciasPesos.add(tenencia4);
		
		infoTenenciaConsolidadaExcel.setListaTenenciasPesos(listaTenenciasPesos);
		infoTenenciaConsolidadaExcel.setListaTenenciasDolares(listaTenenciasPesos);
		
		TotalesTenenciaConsolidadaExcel totales = new TotalesTenenciaConsolidadaExcel();
		
		totales.setTenenciaValuadaCostoArs("$ 122.705,64");
		totales.setTenenciaValuadaHoyArs("$ 231.326,12");
		totales.setResultadoArs("$ 108.500,48");
		
		infoTenenciaConsolidadaExcel.setTotales(totales);
		
		return infoTenenciaConsolidadaExcel;
	
	}
	

	
	private TenenciaPlazoFijoExcel crearInfoPlazoFijoExcel() {
		
		TenenciaPlazoFijoExcel tenenciaPlazoFijoExcel = new TenenciaPlazoFijoExcel();
		
		List<TenenciaPlazoFijoExcelValores> tenenciaPlazoFijoPesos = new ArrayList<TenenciaPlazoFijoExcelValores>();
		TenenciaPlazoFijoExcelValores tenenciaValor1 = new TenenciaPlazoFijoExcelValores();
		tenenciaValor1.setTipo("Tradicional");
		tenenciaValor1.setFechaVencimiento("21/01/2019");
		tenenciaValor1.setCapitalInicial("$ 500,00");
		tenenciaValor1.setTenenciaValuadaHoy("$ 508,45");
		tenenciaValor1.setTna("31,00%");
		tenenciaValor1.setResultado("$ 8,45");
		tenenciaPlazoFijoPesos.add(tenenciaValor1);
		
		TenenciaPlazoFijoExcelValores tenenciaValor2 = new TenenciaPlazoFijoExcelValores();
		tenenciaValor2.setTipo("Tradicional");
		tenenciaValor2.setFechaVencimiento("21/05/2019");
		tenenciaValor2.setCapitalInicial("$ 600,00");
		tenenciaValor2.setTenenciaValuadaHoy("$ 608,45");
		tenenciaValor2.setTna("22,00%");
		tenenciaValor2.setResultado("$ 8,45");
		tenenciaPlazoFijoPesos.add(tenenciaValor2);
		
		tenenciaPlazoFijoExcel.setTenenciaPlazoFijoPesos(tenenciaPlazoFijoPesos);
		
		List<TenenciaPlazoFijoExcelValores> tenenciaPlazoFijoDolares = new ArrayList<TenenciaPlazoFijoExcelValores>();
		TenenciaPlazoFijoExcelValores tenenciaValor3 = new TenenciaPlazoFijoExcelValores();
		tenenciaValor3.setTipo("Tradicional");
		tenenciaValor3.setFechaVencimiento("21/01/2019");
		tenenciaValor3.setCapitalInicial("u$s 500,00");
		tenenciaValor3.setTenenciaValuadaHoy("u$s 508,45");
		tenenciaValor3.setTna("31,00%");
		tenenciaValor3.setResultado("u$s 8,45");
		tenenciaPlazoFijoDolares.add(tenenciaValor3);
		
		TenenciaPlazoFijoExcelValores tenenciaValor4 = new TenenciaPlazoFijoExcelValores();
		tenenciaValor4.setTipo("Tradicional");
		tenenciaValor4.setFechaVencimiento("21/05/2019");
		tenenciaValor4.setCapitalInicial("u$s 600,00");
		tenenciaValor4.setTenenciaValuadaHoy("u$s 608,45");
		tenenciaValor4.setTna("22,00%");
		tenenciaValor4.setResultado("u$s 8,45");
		tenenciaPlazoFijoDolares.add(tenenciaValor4);
		
		tenenciaPlazoFijoExcel.setTenenciaPlazoFijoDolares(tenenciaPlazoFijoDolares);
		
		for (int i = 0; i < tenenciaPlazoFijoExcel.getTenenciaPlazoFijoDolares().size(); i++) {
			
		}
		
		
		TotalesPlazoFijoExcel totalesGrilla = new TotalesPlazoFijoExcel();
		
		totalesGrilla.setCapitalInicialArs("$ 437,228");
		totalesGrilla.setCapitalInicialUsd("u$s 4.721,00");
		totalesGrilla.setResultadoArs("$ 372,00");
		totalesGrilla.setResultadoUsd("u$s 3.721,00");
		totalesGrilla.setTenenciaHoyArs("$ 473,621");
		totalesGrilla.setTenenciaHoyUsd("u$s 57.643,00");

		tenenciaPlazoFijoExcel.setTotalesGrilla(totalesGrilla);
		
		return tenenciaPlazoFijoExcel;
		
	}
	
	private List<CuotasPendientesTarjetaDTO> obtenerListaCuotasPendientes() {
		List<CuotasPendientesTarjetaDTO> lista = new ArrayList<CuotasPendientesTarjetaDTO>();
		CuotasPendientesTarjetaDTO cuota = new CuotasPendientesTarjetaDTO();
		cuota.setMarca("VISA");
		cuota.setNumero("XXXX-2525");
		cuota.setEsTitular(false);
		cuota.setNombreAdicional("Jose Ortiz");
		cuota.setTotal(new BigDecimal("16.00"));
		cuota.setLineasCuotasPendientes(obtenerLineas());
		lista.add(cuota);
		CuotasPendientesTarjetaDTO cuota2 = new CuotasPendientesTarjetaDTO();
		cuota2.setMarca("AMEX");
		cuota2.setNumero("XXXX-2525");
		cuota2.setEsTitular(false);
		cuota2.setNombreAdicional("Jose Ortiz");
		cuota2.setTotal(new BigDecimal("16.00"));
		cuota2.setLineasCuotasPendientes(obtenerLineas());
		lista.add(cuota2);
		return lista;
	}

	private List<CuotasPendientesLineaDTO> obtenerLineas() {
		List<CuotasPendientesLineaDTO> lineas = new ArrayList<CuotasPendientesLineaDTO>();
		CuotasPendientesLineaDTO consulta = new CuotasPendientesLineaDTO();
		consulta.setFechaExcel("12/12/2012");
		consulta.setEstablecimiento("KEVINGSTON");
		consulta.setCuotasPendientes(2);
		consulta.setRestante(new BigDecimal("22.00"));
		consulta.setComprobante("12341412");
		consulta.setPlanDeCuotas("3");
		consulta.setCantidadCuotas(4);
		lineas.add(consulta);
		return lineas;
	}

	private List<ConsultaFinanciacionLineaDTO> obtenerFinanciaciones2() {
		List<ConsultaFinanciacionLineaDTO> linea = new ArrayList<ConsultaFinanciacionLineaDTO>();
		ConsultaFinanciacionLineaDTO consulta = new ConsultaFinanciacionLineaDTO();
		consulta.setFechaExcel("21/21/2121");
		consulta.setNumeroDeSolicitud("12341234");
		consulta.setMontoTotalDelPlanEnPesosExcel("12,00");
		consulta.setMontoCuotaDelPlanExcel("13,00");
		consulta.setCantidadCuotas(3);
		consulta.setCuotasPagadas(14);
		consulta.setTasaAnualAplicadaExcel("14,00");
		consulta.setCostoFinancieroExcel("16,00");
		linea.add(consulta);
		return linea;
	}

	private List<ConsultaFinanciacionLineaDTO> obtenerFinanciaciones1() {
		List<ConsultaFinanciacionLineaDTO> linea = new ArrayList<ConsultaFinanciacionLineaDTO>();
		ConsultaFinanciacionLineaDTO consulta = new ConsultaFinanciacionLineaDTO();
		consulta.setFechaExcel("20/20/2020");
		consulta.setNumeroDeSolicitud("12341234");
		consulta.setMontoTotalDelPlanEnPesosExcel("12,00");
		consulta.setMontoCuotaDelPlanExcel("13,00");
		consulta.setCantidadCuotas(3);
		consulta.setCuotasPagadas(14);
		consulta.setTasaAnualAplicadaExcel("14,00");
		consulta.setCostoFinancieroExcel("16,00");
		linea.add(consulta);
		return linea;
	}

	private List<ConsultaFinanciacionDetalleDTO> obtenerListaConsultaFinanciaciones1() {
		List<ConsultaFinanciacionDetalleDTO> lista = new ArrayList<ConsultaFinanciacionDetalleDTO>();
		ConsultaFinanciacionDetalleDTO consulta1 = new ConsultaFinanciacionDetalleDTO();
		consulta1.setMarca("VISA");
		consulta1.setNumeroTarjeta("XXXX-1856");
		consulta1.setFinanciaciones(obtenerFinanciaciones1());
		ConsultaFinanciacionDetalleDTO consulta2 = new ConsultaFinanciacionDetalleDTO();
		consulta2.setMarca("AMEX");
		consulta2.setNumeroTarjeta("XXXXX-234");
		consulta2.setFinanciaciones(obtenerFinanciaciones2());
		lista.add(consulta1);
		lista.add(consulta2);
		return lista;
	}

	private List<ConsumoTarjetaDTO> conseguirConfirmados(boolean esRecargable) {
		List<ConsumoTarjetaDTO> consumos = new ArrayList<ConsumoTarjetaDTO>();
		ConsumoTarjetaDTO consumo = new ConsumoTarjetaDTO();
		consumo.setMarca("VISA");
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
		if(esRecargable){
			consumo2.setMarca(consumo2.getMarca()+" Recargable");
		}
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
		lineas.add(lineaDTO2);
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
//		LineaDetalleConsumoTarjetaDTO lineaDTO2 = new LineaDetalleConsumoTarjetaDTO();
//		lineaDTO2.setFecha(ISBANStringUtils.formatearFecha("26/01/2018"));
//		lineaDTO2.setFechaExcel("26/02/2018");
//		lineaDTO2.setDescripcion("Su Pago En Pesos");
//		lineaDTO2.setCodigoEstablecimiento("");
//		lineaDTO2.setNroReferencia("2326897   ");
//		lineaDTO2.setImportePesos(new BigDecimal("0.00"));
//		lineaDTO2.setImporteDolares(new BigDecimal("12,01".replace(",", ".")));
//		lineas.add(lineaDTO2);
		return lineas;
	}

	private List<ConsumoTarjetaDTO> conseguirPendientes(boolean esRecargable) {
		List<ConsumoTarjetaDTO> consumos = new ArrayList<ConsumoTarjetaDTO>();
		ConsumoTarjetaDTO consumo = new ConsumoTarjetaDTO();
		consumo.setMarca("VISA");
		consumo.setNumero("XXXX-3838");
		consumo.setConsumoPesos(new BigDecimal("615,48".replace(",", ".")));
		consumo.setConsumoDolares(new BigDecimal("0,00".replace(",", ".")));
		consumo.setIsTitular(true);
		consumo.setIsAdicional(esRecargable);
		consumo.setIsTarjetaRecargable(true);
		consumo.setNombreAdicional("Nose nose");
		consumo.setLineas(obtenerLineasPendientes());
		consumos.add(consumo);
		ConsumoTarjetaDTO consumo2 = new ConsumoTarjetaDTO();
		consumo2.setMarca("AMEX");
		if(esRecargable){
			consumo2.setMarca(consumo2.getMarca()+" Recargable");
		}
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
		return lineas;
	}
	
	@Test
    public void testPrestamoCuotasPagas() throws Exception {
        Cliente cliente = new Cliente();
        Segmento segmento = new Segmento();
        
        cliente.setSegmento(segmento);

        try {
            byte[] excel = helper2.hacerExcel(cliente, "prestamoCuotasPagas", cuotasPagasView());

            FileOutputStream out = new FileOutputStream(new File(
                    "C://Temp/TESTprestamoCuotasPagas.xls"));
            out.write(excel);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@Test
    public void testPrestamoProximasCuotas() throws Exception {
        Cliente cliente = new Cliente();
        Segmento segmento = new Segmento();
        
        cliente.setSegmento(segmento);

        try {
            byte[] excel = helper2.hacerExcel(cliente, "prestamoProximasCuotas", proximasCuotasView());

            FileOutputStream out = new FileOutputStream(new File(
                    "C://Temp/TESTprestamoProximasCuotas.xls"));
            out.write(excel);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private ProximasCuotasView cuotasPagasView() {
	    ProximasCuotasView proximasCuotas = new ProximasCuotasView();
	    proximasCuotas.setTitulo("Préstamo Personal");
	    proximasCuotas.setNumero("418-03510002788/2");
	    proximasCuotas.getLegales().add("Incluye Cargo por Seguro de Vida, e invalidez total y permanente solo si el Préstamo fue otorgado y desembolsado hasta el 31/08/2016, inclusive.");
	    List<CuotasFechaView> cuotasFechas = new ArrayList<CuotasFechaView>();
	    CuotasFechaView cuotasFecha2018 = new CuotasFechaView("2018");
	    
	    ProximaCuotaView cuota27 = new ProximaCuotaView();
	    cuota27.setTipoPrestamo("PERSONAL");
	    cuota27.setCuota("27");
	    cuota27.setVencimiento("30/08/2017");
	    cuota27.setDeuda("$ 31.189,00");
	    cuota27.setCapital("$ 428,56");
	    cuota27.setIntereses("$ 597,79");
	    cuota27.setSeguro("$ 57,23");
	    cuota27.setTotal("$ 1.243,90");
	    cuota27.setImpuestos("$ 160,32");
	    cuotasFecha2018.addCuota(cuota27);
	    cuotasFechas.add(cuotasFecha2018);
	    CuotasFechaView cuotasFecha2017 = new CuotasFechaView("2017");
	    
	    ProximaCuotaView cuota26 = new ProximaCuotaView();
	    cuota26.setTipoPrestamo("PERSONAL");
	    cuota26.setCuota("26");
	    cuota26.setVencimiento("30/07/2017");
	    cuota26.setDeuda("$ 31.609,50");
	    cuota26.setCapital("$ 420,50");
	    cuota26.setIntereses("$ 605,85");
	    cuota26.setSeguro("$ 56,13");
	    cuota26.setTotal("$ 1.277,76");
	    cuota26.setImpuestos("$ 194,90");
	    cuotasFecha2017.addCuota(cuota26);
	    
	    ProximaCuotaView cuota25 = new ProximaCuotaView();
	    cuota25.setTipoPrestamo("PERSONAL");
	    cuota25.setCuota("25");
	    cuota25.setVencimiento("30/06/2017");
	    cuota25.setDeuda("$ 32.022,09");
	    cuota25.setCapital("$ 412,59");
	    cuota25.setIntereses("$ 613,76");
	    cuota25.setSeguro("$ 58,76");
	    cuota25.setTotal("$ 1.283,47");
	    cuota25.setImpuestos("$ 198,36");
        cuotasFecha2017.addCuota(cuota25);
        
        cuotasFechas.add(cuotasFecha2017);
	    proximasCuotas.setCuotasFechas(cuotasFechas);
	    return proximasCuotas;
	}
	
	
	private ProximasCuotasView proximasCuotasView() {
        ProximasCuotasView proximasCuotas = new ProximasCuotasView();
        proximasCuotas.setTitulo("Préstamo Personal");
        proximasCuotas.setNumero("418-03510002788/2");
        proximasCuotas.getLegales().add("Incluye Cargo por Seguro de Vida, e invalidez total y permanente solo si el Préstamo fue otorgado y desembolsado hasta el 31/08/2016, inclusive.");
        proximasCuotas.setMonto("$ 39.900,00");
        proximasCuotas.setCuotasPagadas("27");
        proximasCuotas.setPlazo("72");
        List<ProximaCuotaView> cuotas = new ArrayList<ProximaCuotaView>();

        ProximaCuotaView cuota28 = new ProximaCuotaView();
        cuota28.setTipoPrestamo("PERSONAL");
        cuota28.setCuota("28");
        cuota28.setVencimiento("30/09/2017");
        cuota28.setDeuda("$ 30.760,44");
        cuota28.setCapital("$ 436,77");
        cuota28.setIntereses("$ 589,58");
        cuota28.setSeguro("$ 56,44");
        cuota28.setTotal("$ 1.570,14");
        cuota28.setImpuestos("$ 230,73");
        cuotas.add(cuota28);

        ProximaCuotaView cuota29 = new ProximaCuotaView();
        cuota29.setTipoPrestamo("PERSONAL");
        cuota29.setCuota("29");
        cuota29.setVencimiento("30/10/2017");
        cuota29.setDeuda("$ 30.323,67");
        cuota29.setCapital("$ 445,15");
        cuota29.setIntereses("$ 581,20");
        cuota29.setSeguro("$ 53,85");
        cuota29.setTotal("$ 1.543,70");
        cuota29.setImpuestos("$ 222,82");
        cuotas.add(cuota29);
        
        ProximaCuotaView cuota30 = new ProximaCuotaView();
        cuota30.setTipoPrestamo("PERSONAL");
        cuota30.setCuota("30");
        cuota30.setVencimiento("30/11/2017");
        cuota30.setDeuda("$ 29.878,52");
        cuota30.setCapital("$ 453,68");
        cuota30.setIntereses("$ 572,67");
        cuota30.setSeguro("$ 54,82");
        cuota30.setTotal("$ 1.523,38");
        cuota30.setImpuestos("$ 216,97");
        cuotas.add(cuota30);

        proximasCuotas.setCuotas(cuotas);
        return proximasCuotas;
    }
	
	public static void main(String[] args) {
		
		Double numero = 200.0;
		
		Double importe = 159211.04;
		
		String numeroString = ISBANStringUtils.formatearSaldo(new BigDecimal(numero.toString()));
		String importeString = ISBANStringUtils.formatearSaldo(new BigDecimal(importe.toString()));
		
		System.out.println("numero es: " + numeroString);
		System.out.println("importe es: " + importeString);
		
	}

}

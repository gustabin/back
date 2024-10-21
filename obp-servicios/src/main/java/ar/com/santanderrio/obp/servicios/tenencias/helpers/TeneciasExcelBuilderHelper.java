/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.helpers;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.excel.helpers.ExcelBuildException;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FondoDetalleEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.ResumenFondoDetalleEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TendenciasExcelEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasTitulosExcel;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TitulosResumenImpositivoExcel;
import ar.com.santanderrio.obp.servicios.tenencias.view.CuentaView;
import ar.com.santanderrio.obp.servicios.tenencias.view.CustodiaResumenView;
import ar.com.santanderrio.obp.servicios.tenencias.view.CustodiaView;
import ar.com.santanderrio.obp.servicios.tenencias.view.DatosParticipantesPLView;
import ar.com.santanderrio.obp.servicios.tenencias.view.DetalleCuotasPrestamosView;
import ar.com.santanderrio.obp.servicios.tenencias.view.DetalleMensualImpuestosView;
import ar.com.santanderrio.obp.servicios.tenencias.view.DetallePlazoFijoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.FondoResumenView;
import ar.com.santanderrio.obp.servicios.tenencias.view.FondoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ImpuestoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ParticipantesPLView;
import ar.com.santanderrio.obp.servicios.tenencias.view.PlazoFijoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.PrestamoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ResumenCuentaInversionesView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasDetalleView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasLegalView;

/**
 * The Class TeneciasExcelBuilderHelper.
 */
@Component
@Scope("prototype")
public class TeneciasExcelBuilderHelper {
	/** The Constant NOHAY. */
	private static final String NOHAY = "No hay datos para mostrar";

	/** The Constant VACIO. */
	private static final String VACIO = "-";

	/** The Constant FORMATO_CUATRO_DECIMALES. */
	private static final String FORMATO_CUATRO_DECIMALES = "0,0000";

	/** The Constant SIGNO_PESO. */
	private static final String SIGNO_PESO = Character.toString('\u0024');

	/** The Constant CELDA_1. */
	private static final int CELDA_1 = 1;

	/** The Constant CELDA_2. */
	private static final int CELDA_2 = 2;

	/** The Constant CELDA_3. */
	private static final int CELDA_3 = 3;

	/** The Constant CELDA_4. */
	private static final int CELDA_4 = 4;

	/** The Constant CELDA_5. */
	private static final int CELDA_5 = 5;

	/** The Constant CELDA_6. */
	private static final int CELDA_6 = 6;

	/** The Constant CELDA_7. */
	private static final int CELDA_7 = 7;

	/** The Constant CELDA_8. */
	private static final int CELDA_8 = 8;

	/** The Constant CELDA_9. */
	private static final int CELDA_9 = 9;

	/** The Constant CELDA_10. */
	private static final int CELDA_10 = 10;

	/** The Constant CELDA_11. */
	private static final int CELDA_11 = 11;

	/** The Constant CELDA_12. */
	private static final int CELDA_12 = 12;

	/** The Constant CELDA_13. */
	private static final int CELDA_13 = 13;

	/** The Constant CELDA_13. */
	private static final int CELDA_14 = 14;

	/** The Constant CELDA_13. */
	private static final int CELDA_15 = 15;

	/** The Constant SOLAPADO. */
	private static final String SOLAPADO = "SOLAPADO";

	/** The Constant CITI. */
	private static final String CITI = "CITI";

	/** The Constant CAMBIO SOURCE FONDOS A PL. */
	private static final int ANIO_PL = 2019;

	/** The resource loader. */
	@Autowired
	private ResourceLoader resourceLoader;

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TeneciasExcelBuilderHelper.class);

	private int anioConsulta;

	/**
	 * Hacer excel.
	 *
	 * @param cliente the cliente
	 * @param body    the body
	 * @return the byte[]
	 * @throws ExcelBuildException the excel build exception
	 */
	public byte[] hacerExcel(Cliente cliente, Object body) throws ExcelBuildException {
		TendenciasExcelEntity tee = (TendenciasExcelEntity) body;
		this.anioConsulta = Integer.parseInt(tee.getTenenciasExcelView().getAnio());
		TenenciasLegalView tenenciasLegalView = new TenenciasLegalView();
		String tipoCliente = "";
		if (tee.getTenenciasExcelView() != null && tee.getTenenciasExcelView().getResumen() != null
				&& tee.getTenenciasExcelView().getResumen().getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			tenenciasLegalView = tee.getTenenciasExcelView().getResumen().getRespuesta().getTenenciasLegal();
			tipoCliente = tee.getTenenciasExcelView().getResumen().getRespuesta().getTipoCliente();
		}
		HSSFWorkbook libro = null;

		if (cliente.getSegmento().isDuo() || cliente.getSegmento().isPyme()) {
			libro = getWorkbook("Tenencias_santander_advanced");
		} else if (cliente.getSegmento().isSelect()) {
			libro = getWorkbook("Tenencias_santander_select");
		} else {
			libro = getWorkbook("Tenencias_santander");
		}

		// obtengo hoja 0 para resumen general
		HSSFSheet resumen = libro.getSheetAt(0);
		generarResumen(resumen, libro, tee);
		legales(resumen, libro, tipoCliente, tenenciasLegalView);
		// obtengo hoja 1 para detalle inversiones
		resumen = libro.getSheetAt(CELDA_1);
		detalleInversiones(resumen, libro, tee, tipoCliente, tenenciasLegalView);
		// obtengo hoja 2 para detalle prestamos
		resumen = libro.getSheetAt(CELDA_2);
		detallePrestamos(resumen, libro, tee, tipoCliente, tenenciasLegalView);
		// obtengo hoja 3 para detalle impuestos
		resumen = libro.getSheetAt(CELDA_3);
		detalleImpuestos(resumen, libro, tee, tipoCliente, tenenciasLegalView);

		resumen = libro.getSheetAt(0);
		resumen.getRow(CELDA_3).getCell(CELDA_6).setCellValue("");
		resumen.getRow(CELDA_3).getCell(CELDA_7).setCellValue("");
		resumen.getRow(CELDA_3).getCell(CELDA_8).setCellValue("");
		resumen.getRow(CELDA_3).getCell(CELDA_9).setCellValue("");
		resumen.getRow(CELDA_3).getCell(CELDA_10).setCellValue("");
		resumen.getRow(CELDA_3).getCell(CELDA_11).setCellValue("");

		for (int i = 0; i < 3; i++) {
			for (int j = 6; j < 20; j++) {
				resumen.getRow(i).removeCell(resumen.getRow(i).getCell(j));
			}
		}
		resumen.getRow(3).removeCell(resumen.getRow(3).getCell(24));
		resumen.getRow(3).removeCell(resumen.getRow(3).getCell(25));
		resumen.getRow(3).removeCell(resumen.getRow(3).getCell(26));

		resumen.autoSizeColumn(3);
		resumen.autoSizeColumn(4);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			libro.write(os);
		} catch (IOException e) {
			LOGGER.error("Error de io del excel para el cliente {}.", cliente, e);
			throw new ExcelBuildException(e);
		}
		return os.toByteArray();
	}

	/**
	 * Detalle prestamos.
	 *
	 * @param resumen            the resumen
	 * @param libro              the libro
	 * @param tee                the tee
	 * @param tipoCliente        the tipo cliente
	 * @param tenenciasLegalView the tenencias legal view
	 */
	private void detallePrestamos(HSSFSheet resumen, HSSFWorkbook libro, TendenciasExcelEntity tee, String tipoCliente,
			TenenciasLegalView tenenciasLegalView) {
		BigDecimal totalImporteDeCuota = BigDecimal.ZERO;
		BigDecimal totalCapital = BigDecimal.ZERO;
		BigDecimal totalIntereses = BigDecimal.ZERO;
		BigDecimal totalInteresesCompensatorios = BigDecimal.ZERO;
		BigDecimal totalGestYControl = BigDecimal.ZERO;
		BigDecimal totalImpuestos = BigDecimal.ZERO;

		int linea = 4;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "Banco Santander S.A. - CUIT 30-50000845-4", libro, 3);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"SUS TENENCIAS AL  31/12/" + tee.getTenenciasExcelView().getAnio(), libro, 1);
		linea += 3;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"Detalle de la información de sus tenencias de productos y servicios con Santander útiles para el análisis de su situación impositiva",
				libro, 4);
		linea += 1;
		if (tee.getTenenciasExcelView().getResumen().getRespuesta().getPrestamos() == null
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getPrestamos().size() == 0) {
			linea += 2;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
		} else {
			for (PrestamoView prestamo : tee.getTenenciasExcelView().getResumen().getRespuesta().getPrestamos()) {
				String divisa = prestamo.getDivisa();
				linea += 2;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "PRÉSTAMO Hipotecario Nro. " + prestamo.getCuenta(),
						libro, 3);
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Destino de los fondos:", libro, 1);
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						"Fecha en la que se otorgó el préstamo: " + prestamo.getFechaFormalizacion(), libro, 1);
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Detalle de cuotas pagas", libro, 1);

				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha vto.", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), "Nro. de Recibo", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_3);
				setCelda(resumen.getRow(linea).getCell(CELDA_3), "Saldo anterior de capital sin ajustar", libro,
						CELDA_5);
				resumen.getRow(linea).createCell(CELDA_4);
				setCelda(resumen.getRow(linea).getCell(CELDA_4), "Importe de la cuota", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_5);
				setCelda(resumen.getRow(linea).getCell(CELDA_5), "Capital", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_6);
				setCelda(resumen.getRow(linea).getCell(CELDA_6), "Intereses", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_7);
				setCelda(resumen.getRow(linea).getCell(CELDA_7), "Intereses compensatorios", libro, 5);
				resumen.getRow(linea).createCell(CELDA_8);
				setCelda(resumen.getRow(linea).getCell(CELDA_8), "TNA", libro, 5);
				resumen.getRow(linea).createCell(CELDA_9);
				setCelda(resumen.getRow(linea).getCell(CELDA_9), "Gest. Contr. y Otorg. Cob. Vida y Seguro del Bien",
						libro, 5);
				resumen.getRow(linea).createCell(CELDA_10);
				setCelda(resumen.getRow(linea).getCell(CELDA_10), "Impuestos", libro, 5);

				for (DetalleCuotasPrestamosView detallePrestamo : tee.getTenenciasExcelView().getDetalle()
						.getRespuesta().getDetalleCuotasPrestamosViews()) {
					if (prestamo.getCuenta()
							.equals(formatearNroCuenta(detallePrestamo.getCuenta(), detallePrestamo.getPecodofi()))) {
						if (0 == Integer.valueOf(detallePrestamo.getNroRecibo())) {
							continue;
						} else {
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1), detallePrestamo.getFechaVto(), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), detallePrestamo.getNroRecibo(), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_3);
							setCelda(resumen.getRow(linea).getCell(CELDA_3),
									divisa + detallePrestamo.getSdoAntSinAjustar(), libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_4);
							setCelda(resumen.getRow(linea).getCell(CELDA_4), divisa + detallePrestamo.getImporteCuota(),
									libro, CELDA_5);
							totalImporteDeCuota = totalImporteDeCuota
									.add(formatearABigDecimal(detallePrestamo.getImporteCuota()));
							resumen.getRow(linea).createCell(CELDA_5);
							setCelda(resumen.getRow(linea).getCell(CELDA_5), divisa + detallePrestamo.getCapital(),
									libro, CELDA_5);
							totalCapital = totalCapital.add(formatearABigDecimal(detallePrestamo.getCapital()));
							resumen.getRow(linea).createCell(CELDA_6);
							setCelda(resumen.getRow(linea).getCell(CELDA_6),
									divisa + formatearValorConPuntoComoDecimal(detallePrestamo.getIntereses()), libro,
									CELDA_5);
							totalIntereses = totalIntereses.add(formatearABigDecimal(detallePrestamo.getIntereses()));
							resumen.getRow(linea).createCell(CELDA_7);
							setCelda(resumen.getRow(linea).getCell(CELDA_7), divisa + detallePrestamo.getIntComp(),
									libro, CELDA_5);
							totalInteresesCompensatorios = totalInteresesCompensatorios
									.add(formatearABigDecimal(detallePrestamo.getIntComp()));
							resumen.getRow(linea).createCell(CELDA_8);
							setCelda(resumen.getRow(linea).getCell(CELDA_8),
									formatearValorConPuntoComoDecimal(detallePrestamo.getTna()) + " %", libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_9);
							setCelda(resumen.getRow(linea).getCell(CELDA_9), divisa + detallePrestamo.getSeguros(),
									libro, CELDA_5);
							totalGestYControl = totalGestYControl
									.add(formatearABigDecimal(detallePrestamo.getSeguros()));
							resumen.getRow(linea).createCell(CELDA_10);
							setCelda(resumen.getRow(linea).getCell(CELDA_10), divisa + detallePrestamo.getImpuestos(),
									libro, CELDA_5);
							totalImpuestos = totalImpuestos.add(formatearABigDecimal(detallePrestamo.getImpuestos()));
						}

					}
				}

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Total", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), "", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_3);
				setCelda(resumen.getRow(linea).getCell(CELDA_3), "", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_4);
				setCelda(resumen.getRow(linea).getCell(CELDA_4),
						divisa + formatearValorConPuntoComoDecimal(totalImporteDeCuota.toString()), libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_5);
				setCelda(resumen.getRow(linea).getCell(CELDA_5),
						divisa + formatearValorConPuntoComoDecimal(totalCapital.toString()), libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_6);
				setCelda(resumen.getRow(linea).getCell(CELDA_6),
						divisa + formatearValorConPuntoComoDecimal(totalIntereses.toString()), libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_7);
				setCelda(resumen.getRow(linea).getCell(CELDA_7),
						divisa + formatearValorConPuntoComoDecimal(totalInteresesCompensatorios.toString()), libro,
						CELDA_5);
				resumen.getRow(linea).createCell(CELDA_8);
				setCelda(resumen.getRow(linea).getCell(CELDA_8), "", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_9);
				setCelda(resumen.getRow(linea).getCell(CELDA_9),
						divisa + formatearValorConPuntoComoDecimal(totalGestYControl.toString()), libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_10);
				setCelda(resumen.getRow(linea).getCell(CELDA_10),
						divisa + formatearValorConPuntoComoDecimal(totalImpuestos.toString()), libro, CELDA_5);
			}
		}

		linea += 3;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				obtenerLegalesDetallePrestamos(tipoCliente, tenenciasLegalView), libro, CELDA_6);
	}

	/**
	 * Obtener legales detalle prestamos.
	 *
	 * @param tipoCliente        the tipo cliente
	 * @param tenenciasLegalView the tenencias legal view
	 * @return the string
	 */
	private String obtenerLegalesDetallePrestamos(String tipoCliente, TenenciasLegalView tenenciasLegalView) {
		StringBuilder stringBuilder = new StringBuilder();
		if (SOLAPADO.equals(tipoCliente)) {
			for (String i : tenenciasLegalView.getLegalesDetalleSolapadoPrestamos()) {
				stringBuilder.append(i);
				stringBuilder.append(" ");
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * Formatear nro cuenta.
	 *
	 * @param nroCuenta the nro cuenta
	 * @param sucursal  the sucursal
	 * @return the string
	 */
	private String formatearNroCuenta(String nroCuenta, String sucursal) {
		int lengthNC = nroCuenta.length();
		return sucursal.substring(1, sucursal.length()).concat("-").concat(nroCuenta.substring(4, lengthNC - 1))
				.concat("/").concat(nroCuenta.substring(lengthNC - 1, lengthNC));
	}

	/**
	 * Formatear A BigDecimal.
	 *
	 * @param numero the v BigDecimal
	 * @return the BigDecimal
	 */
	private BigDecimal formatearABigDecimal(String numero) {
		String vFormateado = numero.replace(".", "").replace(",", ".");
		return new BigDecimal(vFormateado);
	}

	/**
	 * Sets the celda.
	 *
	 * @param celda   the celda
	 * @param valor   the valor
	 * @param libro   the libro
	 * @param formato the formato
	 */
	private void setCelda(HSSFCell celda, String valor, HSSFWorkbook libro, int formato) {
		switch (formato) {
		case CELDA_1:// titulo 1 g5
			celda.setCellStyle(libro.getSheetAt(0).getRow(CELDA_3).getCell(CELDA_6).getCellStyle());
			celda.setCellValue(valor);
			break;
		case CELDA_2:// titulo 1 g5
			celda.setCellStyle(libro.getSheetAt(0).getRow(CELDA_3).getCell(CELDA_7).getCellStyle());
			celda.setCellValue(valor);
			break;
		case CELDA_3:// titulo 1 g5
			celda.setCellStyle(libro.getSheetAt(0).getRow(CELDA_3).getCell(CELDA_8).getCellStyle());
			celda.setCellValue(valor);
			break;
		case CELDA_4:// titulo 1 g5
			celda.setCellStyle(libro.getSheetAt(0).getRow(CELDA_3).getCell(CELDA_9).getCellStyle());
			celda.setCellValue(valor);
			break;
		case CELDA_5:// titulo 1 g5
			celda.setCellStyle(libro.getSheetAt(0).getRow(CELDA_3).getCell(CELDA_10).getCellStyle());
			celda.setCellValue(valor);
			break;
		case CELDA_6:// titulo 1 g5
			celda.setCellStyle(libro.getSheetAt(0).getRow(CELDA_3).getCell(CELDA_11).getCellStyle());
			celda.setCellValue(valor);
			break;
		case 7:
			celda.setCellStyle(libro.getSheetAt(0).getRow(CELDA_3).getCell(24).getCellStyle());
			celda.setCellValue(valor);
			break;
		case 8:
			celda.setCellStyle(libro.getSheetAt(0).getRow(CELDA_3).getCell(25).getCellStyle());
			celda.setCellValue(valor);
			break;
		case 9:
			celda.setCellStyle(libro.getSheetAt(0).getRow(CELDA_3).getCell(26).getCellStyle());
			celda.setCellValue(valor);
			break;
		case 10:
			celda.setCellStyle(libro.getSheetAt(0).getRow(2).getCell(2).getCellStyle());
			celda.setCellValue(valor);
			break;
		default:
			celda.setCellValue(valor);
		}
	}

	/**
	 * Generar resumen.
	 *
	 * @param resumen the resumen
	 * @param libro   the libro
	 * @param tee     the tee
	 */
	private void generarResumen(HSSFSheet resumen, HSSFWorkbook libro, TendenciasExcelEntity tee) {
		String cotizacion = SIGNO_PESO + " " + tee.getTenenciasExcelView().getResumen().getRespuesta().getCotiDolar();
		int linea = 4;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "Banco Santander S.A. - CUIT 30-50000845-4", libro, 3);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"SUS TENENCIAS AL  31/12/" + tee.getTenenciasExcelView().getAnio(), libro, 1);
		linea += 3;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"Detalle de la información de sus tenencias de productos y servicios con Santander útiles para el análisis de su situación impositiva",
				libro, 4);
		linea += 3;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "CUENTAS", libro, 7);

		for (int i = 2; i < 5; i++) {
			resumen.getRow(linea).createCell(i);
			setCelda(resumen.getRow(linea).getCell(i), " ", libro, 7);
		}

		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		if (tee.getTenenciasExcelView().getResumen().getRespuesta().getCuentas() == null
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getCuentas().size() == 0) {
			setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
		} else {
			setCelda(resumen.getRow(linea).getCell(CELDA_1), "Descripción", libro, CELDA_8);
			resumen.getRow(linea).createCell(CELDA_2);
			setCelda(resumen.getRow(linea).getCell(CELDA_2), "Importe", libro, CELDA_8);
			for (CuentaView cuentas : tee.getTenenciasExcelView().getResumen().getRespuesta().getCuentas()) {
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						cuentas.getTipoCuenta() + " Nro." + cuentas.getCuenta() + " CBU " + cuentas.getCbu(), libro,
						CELDA_5);
				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), SIGNO_PESO + " " + cuentas.getSaldo(), libro, CELDA_5);
				if (!cuentas.getTipoCuenta().contains("corriente")) {
					if (cuentas.getIntDevengado() != "") {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1),
								"Intereses Devengados " + cuentas.getTipoCuenta() + " Nro." + cuentas.getCuenta(),
								libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), SIGNO_PESO + " " + cuentas.getImporteIntDev(),
								libro, CELDA_5);
					}
				}
			}
		}

		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		if (tee.getTenenciasExcelView().getResumen().getRespuesta().getCuentasME() == null
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getCuentasME().size() == 0) {
			// SE COMENTA DEBIDO A QUE MONEDA EXTRANJERA PASA A SER PARTE DE CUENTAS, NO ES
			// NECESARIO MOSTRAR EL NOHAY CUANDO NO EXISTA MONEJA EXTRANJERA
			// setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
		} else {
			setCelda(resumen.getRow(linea).getCell(CELDA_1), "Moneda Extranjera", libro, CELDA_8);
			resumen.getRow(linea).createCell(CELDA_2);
			setCelda(resumen.getRow(linea).getCell(CELDA_2), "Importe", libro, CELDA_8);
			resumen.getRow(linea).createCell(CELDA_3);
			setCelda(resumen.getRow(linea).getCell(CELDA_3), "Tenencia", libro, CELDA_8);
			resumen.getRow(linea).createCell(CELDA_4);
			setCelda(resumen.getRow(linea).getCell(CELDA_4), "Cotización al 31/12*", libro, CELDA_8);
			for (CuentaView cuentas : tee.getTenenciasExcelView().getResumen().getRespuesta().getCuentasME()) {
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						cuentas.getTipoCuenta() + " Nro." + cuentas.getCuenta() + " CBU " + cuentas.getCbu(), libro,
						CELDA_5);
				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), cuentas.getImporte(), libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_3);
				setCelda(resumen.getRow(linea).getCell(CELDA_3), cuentas.getSaldo(), libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_4);
				setCelda(resumen.getRow(linea).getCell(CELDA_4), cotizacion, libro, CELDA_5);
				if (!cuentas.getTipoCuenta().contains("corriente")) {
					if (cuentas.getIntDevengado() != "") {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1),
								"Intereses Devengados " + cuentas.getTipoCuenta() + " Nro." + cuentas.getCuenta(),
								libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), cuentas.getImporteIntDev(), libro, CELDA_5);
						// verificar datos
						resumen.getRow(linea).createCell(CELDA_3);
						setCelda(resumen.getRow(linea).getCell(CELDA_3), cuentas.getIntDevengado(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_4);
						setCelda(resumen.getRow(linea).getCell(CELDA_4), cotizacion, libro, CELDA_5);
					}
				}
			}
		}
		linea += 2;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "INVERSIONES", libro, 7);
		for (int i = 2; i < 5; i++) {
			resumen.getRow(linea).createCell(i);
			setCelda(resumen.getRow(linea).getCell(i), " ", libro, 7);
		}

		// PLAZO FIJO
		linea += 2;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "Plazo Fijo", libro, 8);
		resumen.getRow(linea).createCell(CELDA_2);
		setCelda(resumen.getRow(linea).getCell(CELDA_2), " ", libro, 8);
		resumen.getRow(linea).createCell(CELDA_3);
		setCelda(resumen.getRow(linea).getCell(CELDA_3), "Importe", libro, 8);
		resumen.getRow(linea).createCell(CELDA_4);
		setCelda(resumen.getRow(linea).getCell(CELDA_4), "Tipo de Cambio al 31/12*", libro, 8);
		linea++;
		if (tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getPlazoFijos() == null || tee
				.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getPlazoFijos().size() == 0) {
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
		} else {
			for (PlazoFijoView plazoFijoView : tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones()
					.getPlazoFijos()) {
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Cuenta Inversor Nro. " + plazoFijoView.getPenumcon(),
						libro, 9);
				for (int i = 2; i < 5; i++) {
					resumen.getRow(linea).createCell(i);
					setCelda(resumen.getRow(linea).getCell(i), " ", libro, 9);
				}

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						"Tenencia de Plazo Fijo al 31/12/" + tee.getTenenciasExcelView().getAnio(), libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), " ", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_3);
				setCelda(resumen.getRow(linea).getCell(CELDA_3), SIGNO_PESO + " " + plazoFijoView.getTotalTenencia()
						+ " U" + SIGNO_PESO + "D " + plazoFijoView.getTotalTenenciaUS(), libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_4);
				setCelda(resumen.getRow(linea).getCell(CELDA_4), cotizacion, libro, CELDA_5);
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Total de Intereses Devengados", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), " ", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_3);
				setCelda(resumen.getRow(linea).getCell(CELDA_3), SIGNO_PESO + " " + plazoFijoView.getTotalDevengado()
						+ " U" + SIGNO_PESO + "D " + plazoFijoView.getTotalDevengadoUS(), libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_4);
				setCelda(resumen.getRow(linea).getCell(CELDA_4), cotizacion, libro, CELDA_5);
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Total de Intereses Percibidos", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), " ", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_3);
				setCelda(resumen.getRow(linea).getCell(CELDA_3), SIGNO_PESO + " " + plazoFijoView.getTotalPercibido()
						+ " U" + SIGNO_PESO + "D " + plazoFijoView.getTotalPercibidoUS(), libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_4);
				setCelda(resumen.getRow(linea).getCell(CELDA_4), cotizacion, libro, CELDA_5);

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Total percibido por unidad de ajuste", libro,
						CELDA_5);
				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), " ", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_3);
				setCelda(resumen.getRow(linea).getCell(CELDA_3), SIGNO_PESO + " " + plazoFijoView.getTotalAjustePorCer()
						+ " U" + SIGNO_PESO + "D " + plazoFijoView.getTotalAjustePorCerUS(), libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_4);
				setCelda(resumen.getRow(linea).getCell(CELDA_4), cotizacion, libro, CELDA_5);

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Total devengado por unidad de ajuste", libro,
						CELDA_5);
				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), " ", libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_3);
				setCelda(resumen.getRow(linea).getCell(CELDA_3),
						SIGNO_PESO + " " + plazoFijoView.getTotalAjustePorCerDev() + " U" + SIGNO_PESO + "D "
								+ plazoFijoView.getTotalAjustePorCerDevUs(),
						libro, CELDA_5);
				resumen.getRow(linea).createCell(CELDA_4);
				setCelda(resumen.getRow(linea).getCell(CELDA_4), cotizacion, libro, CELDA_5);

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						"Total Tenencia de Plazos Fijos al 31/12/" + tee.getTenenciasExcelView().getAnio(), libro,
						CELDA_5);
				resumen.getRow(linea).createCell(CELDA_3);
				setCelda(resumen.getRow(linea).getCell(CELDA_3), SIGNO_PESO + " " + plazoFijoView.getTotalTenencia()
						+ " U" + SIGNO_PESO + "D " + plazoFijoView.getTotalTenenciaUS(), libro, CELDA_5);
				linea++;
			}
		}
		// DATOS SUPERFONDOS
		linea += 2;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "Superfondos", libro, 8);
		resumen.getRow(linea).createCell(CELDA_2);
		setCelda(resumen.getRow(linea).getCell(CELDA_2), "Tenencia Valuada", libro, 8);
		resumen.getRow(linea).createCell(CELDA_3);
		setCelda(resumen.getRow(linea).getCell(CELDA_3), "Cantidad de cuotapartes", libro, 8);
		resumen.getRow(linea).createCell(CELDA_4);
		setCelda(resumen.getRow(linea).getCell(CELDA_4), "Valor cuotaparte al 31/12*", libro, 8);
		linea++;
		if (tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getFondos() == null
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getFondos().size() == 0) {
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
		} else {
			for (FondoResumenView fondoResumenView : tee.getTenenciasExcelView().getResumen().getRespuesta()
					.getInversiones().getFondos()) {
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Cuenta Nro. " + fondoResumenView.getCuenta(), libro,
						9);
				for (int i = 2; i < 5; i++) {
					resumen.getRow(linea).createCell(i);
					setCelda(resumen.getRow(linea).getCell(i), " ", libro, 9);
				}
				for (FondoView fondo : fondoResumenView.getFondos()) {
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Moneda Fondo " + fondo.getDivisa() + " " + fondo.getDescripcion(), libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), fondo.getDivisa() + " " + fondo.getImporte(),
							libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_3);
					setCelda(resumen.getRow(linea).getCell(CELDA_3), fondo.getSaldoCuotas(), libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_4);
					setCelda(resumen.getRow(linea).getCell(CELDA_4), fondo.getDivisa() + " " + fondo.getCotiAfip(),
							libro, CELDA_5);
				}

//				boolean cuentaConTotales = false;
//
//				linea++;
//				resumen.createRow(linea);
//				resumen.getRow(linea).createCell(CELDA_1);
//				setCelda(resumen.getRow(linea).getCell(CELDA_1),
//						"Total Rendimiento al Rescate Cuenta " + fondoResumenView.getCuenta(), libro, CELDA_5);
//				resumen.getRow(linea).createCell(CELDA_2);
//				setCelda(resumen.getRow(linea).getCell(CELDA_2), "-", libro, CELDA_5);
//
//				if (!(tee.getTenenciasExcelView().getResumen().getRespuesta().getRendimientoFondos() == null
//						|| tee.getTenenciasExcelView().getResumen().getRespuesta().getRendimientoFondos().isEmpty())) {
//					for (RendimientoFondoView rendimientoView : tee.getTenenciasExcelView().getResumen().getRespuesta()
//							.getRendimientoFondos()) {
//						if (rendimientoView.getCuenta().equals(fondoResumenView.getCuenta())) {
//							if (cuentaConTotales) {
//								resumen.createRow(linea);
//							}
//							resumen.getRow(linea).createCell(CELDA_2);
//							setCelda(resumen.getRow(linea).getCell(CELDA_2),
//									rendimientoView.getDivisa() + " "
//											+ ISBANStringUtils
//													.formatearConComaYDosDecimales(rendimientoView.getSumRenta()),
//									libro, CELDA_5);
//							cuentaConTotales = true;
//							linea++;
//						}
//						// linea++;
//					}
//					cuentaConTotales = false;
//				}
			}
		}

		// BONOS
		linea += 2;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "Títulos públicos y privados", libro, 8);
		resumen.getRow(linea).createCell(CELDA_2);
		setCelda(resumen.getRow(linea).getCell(CELDA_2), "Tenencia Valuada", libro, 8);
		resumen.getRow(linea).createCell(CELDA_3);
		setCelda(resumen.getRow(linea).getCell(CELDA_3), "Cantidad Nominales", libro, 8);
		resumen.getRow(linea).createCell(CELDA_4);
		setCelda(resumen.getRow(linea).getCell(CELDA_4), "Cotización al 31/12*", libro, 8);
		if (tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getBonos() == null
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getBonos().size() == 0) {
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
		} else {
			for (CustodiaResumenView bono : tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones()
					.getBonos()) {
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Cuenta " + bono.getCuenta(), libro, 9);
				for (int i = 2; i < 5; i++) {
					resumen.getRow(linea).createCell(i);
					setCelda(resumen.getRow(linea).getCell(i), " ", libro, 9);
				}
				for (CustodiaView custodia : bono.getCustodias()) {
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Moneda de Emisión " + custodia.getDivisa() + " " + custodia.getEspeNom(), libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), custodia.getDivisa() + " " + custodia.getImporte(),
							libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_3);
					setCelda(resumen.getRow(linea).getCell(CELDA_3), ISBANStringUtils.formatearConComaYVariosDecimales2(custodia.getValorNominal(),2), libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_4);
					if (custodia.getCotiAfip().equals("-")) {
						setCelda(resumen.getRow(linea).getCell(CELDA_4), custodia.getCotiAfip(), libro, CELDA_5);
					} else {
						setCelda(resumen.getRow(linea).getCell(CELDA_4),
								custodia.getDivisa() + " " + custodia.getCotiAfip(), libro, CELDA_5);
					}

				}
			}
		}

		// ACCIONES
		linea += 2;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "Acciones y CEDEARs", libro, 8);
		resumen.getRow(linea).createCell(CELDA_2);
		setCelda(resumen.getRow(linea).getCell(CELDA_2), "Tenencia Valuada", libro, 8);
		resumen.getRow(linea).createCell(CELDA_3);
		setCelda(resumen.getRow(linea).getCell(CELDA_3), "Cantidad Nominales", libro, 8);
		resumen.getRow(linea).createCell(CELDA_4);
		setCelda(resumen.getRow(linea).getCell(CELDA_4), "Cotización al 31/12*", libro, 8);
		if (tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getAcciones() == null
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getAcciones().size() == 0) {
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
		} else {
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), "Cuenta " + tee.getTenenciasExcelView().getResumen()
					.getRespuesta().getInversiones().getAcciones().get(0).getCuenta(), libro, 9);
			for (int i = 2; i < 5; i++) {
				resumen.getRow(linea).createCell(i);
				setCelda(resumen.getRow(linea).getCell(i), " ", libro, 9);
			}
			for (CustodiaResumenView accion : tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones()
					.getAcciones()) {
				for (CustodiaView custodia : accion.getCustodias()) {
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Moneda de Emisión " + custodia.getDivisa() + " " + custodia.getEspeNom(), libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), custodia.getDivisa() + " " + custodia.getImporte(),
							libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_3);
					setCelda(resumen.getRow(linea).getCell(CELDA_3), ISBANStringUtils.formatearConComaYVariosDecimales2(custodia.getValorNominal(),2), libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_4);
					if (custodia.getCotiAfip().equals("-")) {
						setCelda(resumen.getRow(linea).getCell(CELDA_4), custodia.getCotiAfip(), libro, CELDA_5);
					} else {
						setCelda(resumen.getRow(linea).getCell(CELDA_4),
								custodia.getDivisa() + " " + custodia.getCotiAfip(), libro, CELDA_5);
					}

				}
			}
		}

		// PRESTAMOS
		linea += 2;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "PRESTAMOS", libro, 7);
		for (int i = 2; i < 5; i++) {
			resumen.getRow(linea).createCell(i);
			setCelda(resumen.getRow(linea).getCell(i), " ", libro, 7);
		}
		if (tee.getTenenciasExcelView().getResumen().getRespuesta().getPrestamos() == null
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getPrestamos().size() == 0) {
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
		} else {
			for (PrestamoView prestamo : tee.getTenenciasExcelView().getResumen().getRespuesta().getPrestamos()) {
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						"Préstamo " + prestamo.getTipoProducto() + " " + prestamo.getCuenta(), libro, CELDA_5);

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Destino de los Fondos", libro, CELDA_5);

				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2),
						prestamo.getDestinoDescripcion() == null ? "" : prestamo.getDestinoDescripcion().trim(), libro,
						CELDA_5);

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha en la que se otorgó el préstamo", libro,
						CELDA_5);

				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), prestamo.getFechaFormalizacion(), libro, CELDA_5);

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Plazo", libro, CELDA_5);

				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), prestamo.getPlazo(), libro, CELDA_5);

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Saldo de deuda pendiente a facturar", libro, CELDA_5);

				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), prestamo.getDivisa() + " " + prestamo.getSaldoDeuda(),
						libro, CELDA_5);

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						"Sumatoria de capital de las cuotas vencidas impagas al 31/12/"
								+ tee.getTenenciasExcelView().getAnio(),
						libro, CELDA_5);

				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2),
						prestamo.getDivisa() + " " + prestamo.getSaldoVencido(), libro, CELDA_5);

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Intereses devengados al 31/12/"
						+ tee.getTenenciasExcelView().getAnio() + " desde la ultima cuota facturada del mismo año",
						libro, CELDA_5);

				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2),
						prestamo.getDivisa() + " " + prestamo.getIntDevengado(), libro, CELDA_5);

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						"Sumatoria de intereses cobrados al 31/12/" + tee.getTenenciasExcelView().getAnio(), libro,
						CELDA_5);

				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), prestamo.getDivisa() + " " + prestamo.getIntCobrado(),
						libro, CELDA_5);

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Sumatoria de intereses de cuotas vencidas imapagas",
						libro, CELDA_5);

				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), prestamo.getDivisa() + " " + prestamo.getIntVencido(),
						libro, CELDA_5);

				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Situación del Préstamo", libro, CELDA_5);

				resumen.getRow(linea).createCell(CELDA_2);
				setCelda(resumen.getRow(linea).getCell(CELDA_2), prestamo.getSituacion(), libro, CELDA_5);

			}
		}
		// IMPUESTOS
		linea += 2;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "IMPUESTOS", libro, 7);
		for (int i = 2; i < 5; i++) {
			resumen.getRow(linea).createCell(i);
			setCelda(resumen.getRow(linea).getCell(i), " ", libro, 7);
		}
		if (tee.getTenenciasExcelView().getResumen().getRespuesta().getImpuestos() == null
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getImpuestos().size() == 0) {
			linea += 2;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
		} else {
			for (ImpuestoView impuestos : tee.getTenenciasExcelView().getResumen().getRespuesta().getImpuestos()) {
				int ivaDev = Integer.parseInt(impuestos.getIvaDev().substring(0, 1));
				// DEVOLUCION IVA
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Devolución IVA", libro, CELDA_5);
				if (ivaDev > 0) {
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), "Caja de ahorro en Pesos " + impuestos.getCuenta(),
							libro, CELDA_5);
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), "Total devolución de IVA. Tarjeta de débito",
							libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), SIGNO_PESO + " " + impuestos.getIvaDev(), libro,
							CELDA_5);
				} else {
					// Impuestos Ley 25.413
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), "Impuestos Ley 25.413", libro, CELDA_5);
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Cuenta corriente en pesos " + impuestos.getCuenta(), libro, CELDA_5);
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Total Retención Impuesto Ley 25.413 por Débitos(1)", libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), SIGNO_PESO + " " + impuestos.getSumImpDebito(),
							libro, CELDA_5);
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Total Retención Impuesto Ley 25.413 por Créditos(2)", libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), SIGNO_PESO + " " + impuestos.getSumImpCredito(),
							libro, CELDA_5);
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Total Retención Impuesto Ley 25.413 por Creditos alícuota 6 por mil(3)", libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2),
							SIGNO_PESO + " " + impuestos.getSumImpCreditoAlicuota6(), libro, CELDA_5);
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Importe susceptible de ser computado contra otros tributos(5) ", libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), SIGNO_PESO + " " + impuestos.getSumImpComputable(),
							libro, CELDA_5);
				}
			}

			// PIE DE PAGINA
			linea += 2;
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1),
					"(1) En este total se incluyen los impuestos calculados sobre movimientos de débito en la cuenta extractada, sin diferenciar la alícuota de cálculo del impuesto.",
					libro, CELDA_5);
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1),
					"(2) En este total se incluyen los impuestos calculados sobre movimientos de crédito en la cuenta extractada, sin diferenciar la alícuota de cálculo del impuesto.",
					libro, CELDA_5);
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1),
					"(3) En este total se incluyen los impuestos calculados con la alícuota de 6%o sobre movimientos de crédito en la cuenta extractada.",
					libro, CELDA_5);
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1),
					"(5) En este total se incluyen los importes que surjan de aplicar un 34% sobre impuestos por créditos al 6%o o bien un 17% sobre impuestos por crédito al 12%o.",
					libro, CELDA_5);
//			linea++;
//			resumen.createRow(linea);
//			resumen.getRow(linea).createCell(CELDA_1);
//			setCelda(resumen.getRow(linea).getCell(CELDA_1),
//					"(*) Valuaciones vigentes Resolución pendiente de publicación por parte del AFIP", libro, CELDA_5);

		}

		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"(*) Solo se mostrarán valores de cuotaparte y precios brindados por AFIP para su valuación al 31/12 o valuación de mercado por modelo de datos.",
				libro, CELDA_5);

		linea += 2;
		resumen.createRow(linea);

		for (int i = 0; i < 6; i++) {
			resumen.getRow(linea).createCell(i);
			setCelda(resumen.getRow(linea).getCell(i), " ", libro, 10);
		}

	}

	/**
	 * Legales.
	 *
	 * @param resumen            the resumen
	 * @param libro              the libro
	 * @param tipoCliente        the tipo cliente
	 * @param tenenciasLegalView the tenencias legal view
	 */
	private void legales(HSSFSheet resumen, HSSFWorkbook libro, String tipoCliente,
			TenenciasLegalView tenenciasLegalView) {
		int linea = resumen.getLastRowNum();

		linea += 3;
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), obtenerLegalesResumen(tipoCliente, tenenciasLegalView), libro,
				6);

	}

	/**
	 * Obtener legales resumen.
	 *
	 * @param tipoCliente        the tipo cliente
	 * @param tenenciasLegalView the tenencias legal view
	 * @return the string
	 */
	private String obtenerLegalesResumen(String tipoCliente, TenenciasLegalView tenenciasLegalView) {
		StringBuilder stringBuilder = new StringBuilder();
		if (SOLAPADO.equals(tipoCliente)) {
			for (String i : tenenciasLegalView.getLegalesPrincipalSolapadoCuentas()) {
				stringBuilder.append(i.replace("@", ""));
				stringBuilder.append(" ");
			}
		} else if (CITI.equals(tipoCliente)) {
			for (String i : tenenciasLegalView.getLegalesPrincipalExclusivoCuentas()) {
				stringBuilder.append(i.replace("@", ""));
				stringBuilder.append(" ");
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * Detalle impuestos.
	 *
	 * @param resumen            the resumen
	 * @param libro              the libro
	 * @param tee                the tee
	 * @param tipoCliente        the tipo cliente
	 * @param tenenciasLegalView the tenencias legal view
	 */
	private void detalleImpuestos(HSSFSheet resumen, HSSFWorkbook libro, TendenciasExcelEntity tee, String tipoCliente,
			TenenciasLegalView tenenciasLegalView) {
		// HOJA DE IMPUESTOS
		int linea = CELDA_4;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "Banco Santander S.A. - CUIT 30-50000845-4", libro, CELDA_3);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"SUS TENENCIAS AL  31/12/" + tee.getTenenciasExcelView().getAnio(), libro, 1);
		linea += 3;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"Detalle de la información de sus tenencias de productos y servicios con Santander útiles para el análisis de su situación impositiva",
				libro, 4);
		if (null == tee.getTenenciasExcelView().getDetalle().getRespuesta()
				|| null == tee.getTenenciasExcelView().getDetalle().getRespuesta().getDetalleMensualImpuestosViews()
				|| tee.getTenenciasExcelView().getDetalle().getRespuesta().getDetalleMensualImpuestosViews()
						.size() == 0) {
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, 5);
		} else {
			for (DetalleMensualImpuestosView impuestos : tee.getTenenciasExcelView().getDetalle().getRespuesta()
					.getDetalleMensualImpuestosViews()) {
				linea += CELDA_2;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						"CUENTA: " + impuestos.getTipoCuenta() + " " + impuestos.getCuenta(), libro, 5);
				// DEBITOS
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Retención Impuesto Ley 25.413 por Débitos(1)", libro,
						5);
				Boolean existeImpDebito = evaluarExisteImpDebito(impuestos);
				if (!existeImpDebito) {
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
				} else {
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha", libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), "Importe", libro, CELDA_5);
					if (!impuestos.getImpDebito01().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "ene-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpDebito01()).toString(), libro, CELDA_5);

					}
					if (!impuestos.getImpDebito02().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "feb-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpDebito02()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpDebito03().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "mar-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpDebito03()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpDebito04().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "abr-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpDebito04()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpDebito05().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "may-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpDebito05()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpDebito06().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "jun-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpDebito06()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpDebito07().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "jul-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpDebito07()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpDebito08().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "ago-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpDebito08()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpDebito09().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "sep-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpDebito09()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpDebito10().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "oct-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpDebito10()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpDebito11().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "nov-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpDebito11()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpDebito12().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "dic-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpDebito12()).toString(), libro, CELDA_5);
					}
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Total Retención Impuesto Ley 25.413 por Débitos (1) ", libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), obtenerTotalDebito(impuestos).toString(), libro,
							CELDA_5);
				}
				// CREDITOS
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), "Retención Impuesto Ley 25.413 por créditos (2)",
						libro, CELDA_5);
				Boolean existeImpCredito = evaluarExisteImpCredito(impuestos);
				if (!existeImpCredito) {
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
				} else {
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha", libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), "Importe", libro, CELDA_5);
					if (!impuestos.getImpCredito01().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "ene-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpCredito01()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpCredito02().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "feb-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpCredito02()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpCredito03().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "mar-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpCredito03()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpCredito04().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "abr-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpCredito04()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpCredito05().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "may-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpCredito05()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpCredito06().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "jun-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpCredito06()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpCredito07().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "jul-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpCredito07()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpCredito08().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "ago-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpCredito08()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpCredito09().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "sep-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpCredito09()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpCredito10().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "oct-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpCredito10()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpCredito11().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "nov-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpCredito11()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpCredito12().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "dic-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpCredito12()).toString(), libro, CELDA_5);
					}
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Total Retención Impuesto Ley 25.413 por créditos (2) ", libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), obtenerTotalCredito(impuestos).toString(), libro,
							CELDA_5);
				}
				// COMPUTADO
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						"Importe susceptible de ser computado contra otros tributos (5)", libro, CELDA_5);
				Boolean existeImpComputable = evaluarExisteImpComputable(impuestos);
				if (!existeImpComputable) {
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
				} else {
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha", libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), "Importe", libro, CELDA_5);
					if (!impuestos.getImpComputable01().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "ene-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpComputable01()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpComputable02().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "feb-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpComputable02()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpComputable03().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "mar-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpComputable03()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpComputable04().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "abr-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpComputable04()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpComputable05().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "may-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpComputable05()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpComputable06().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "jun-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpComputable06()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpComputable07().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "jul-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpComputable07()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpComputable08().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "ago-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpComputable08()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpComputable09().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "sep-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpComputable09()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpComputable10().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "oct-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpComputable10()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpComputable11().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "nov-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpComputable11()).toString(), libro, CELDA_5);
					}
					if (!impuestos.getImpComputable12().equals(FORMATO_CUATRO_DECIMALES)) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "dic-" + impuestos.getAnio(), libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								obtenerFormatoDecimal(impuestos.getImpComputable12()).toString(), libro, CELDA_5);
					}
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Total Importe susceptible de ser computado contra otros tributos (5) ", libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), obtenerTotalComputable(impuestos).toString(),
							libro, CELDA_5);
				}
			}
		}

		linea += 3;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				obtenerLegalesDetalleImpuestos(tipoCliente, tenenciasLegalView), libro, CELDA_6);

	}

	/**
	 * Obtener legales detalle impuestos.
	 *
	 * @param tipoCliente        the tipo cliente
	 * @param tenenciasLegalView the tenencias legal view
	 * @return the string
	 */
	private String obtenerLegalesDetalleImpuestos(String tipoCliente, TenenciasLegalView tenenciasLegalView) {
		StringBuilder stringBuilder = new StringBuilder();
		if (SOLAPADO.equals(tipoCliente)) {
			for (String i : tenenciasLegalView.getLegalesDetalleSolapadoImpuestos()) {
				stringBuilder.append(i);
				stringBuilder.append(" ");
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * Obtener total computable.
	 *
	 * @param impuestos the impuestos
	 * @return the BigDecimal
	 */
	private BigDecimal obtenerTotalComputable(DetalleMensualImpuestosView impuestos) {
		BigDecimal retornoTotal = obtenerFormatoDecimal(impuestos.getImpComputable01())
				.add(obtenerFormatoDecimal(impuestos.getImpComputable02()))
				.add(obtenerFormatoDecimal(impuestos.getImpComputable03()))
				.add(obtenerFormatoDecimal(impuestos.getImpComputable04()))
				.add(obtenerFormatoDecimal(impuestos.getImpComputable05()))
				.add(obtenerFormatoDecimal(impuestos.getImpComputable06()))
				.add(obtenerFormatoDecimal(impuestos.getImpComputable07()))
				.add(obtenerFormatoDecimal(impuestos.getImpComputable08()))
				.add(obtenerFormatoDecimal(impuestos.getImpComputable09()))
				.add(obtenerFormatoDecimal(impuestos.getImpComputable10()))
				.add(obtenerFormatoDecimal(impuestos.getImpComputable11()))
				.add(obtenerFormatoDecimal(impuestos.getImpComputable12()));
		return retornoTotal;
	}

	/**
	 * Evaluar existe imp computable.
	 *
	 * @param impuestos the impuestos
	 * @return the boolean
	 */
	private Boolean evaluarExisteImpComputable(DetalleMensualImpuestosView impuestos) {
		if (!impuestos.getImpComputable01().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpComputable02().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpComputable03().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpComputable04().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpComputable05().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpComputable06().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpComputable07().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpComputable08().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpComputable09().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpComputable10().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpComputable11().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpComputable12().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		return false;
	}

	/**
	 * Obtener total credito.
	 *
	 * @param impuestos the impuestos
	 * @return the BigDecimal
	 */
	private BigDecimal obtenerTotalCredito(DetalleMensualImpuestosView impuestos) {
		BigDecimal retornoTotal = obtenerFormatoDecimal(impuestos.getImpCredito01())
				.add(obtenerFormatoDecimal(impuestos.getImpCredito02()))
				.add(obtenerFormatoDecimal(impuestos.getImpCredito03()))
				.add(obtenerFormatoDecimal(impuestos.getImpCredito04()))
				.add(obtenerFormatoDecimal(impuestos.getImpCredito05()))
				.add(obtenerFormatoDecimal(impuestos.getImpCredito06()))
				.add(obtenerFormatoDecimal(impuestos.getImpCredito07()))
				.add(obtenerFormatoDecimal(impuestos.getImpCredito08()))
				.add(obtenerFormatoDecimal(impuestos.getImpCredito09()))
				.add(obtenerFormatoDecimal(impuestos.getImpCredito10()))
				.add(obtenerFormatoDecimal(impuestos.getImpCredito11()))
				.add(obtenerFormatoDecimal(impuestos.getImpCredito12()));
		return retornoTotal;
	}

	/**
	 * Evaluar existe imp credito.
	 *
	 * @param impuestos the impuestos
	 * @return the boolean
	 */
	private Boolean evaluarExisteImpCredito(DetalleMensualImpuestosView impuestos) {
		if (!impuestos.getImpCredito01().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpCredito02().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpCredito03().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpCredito04().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpCredito05().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpCredito06().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpCredito07().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpCredito08().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpCredito09().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpCredito10().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpCredito11().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpCredito12().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}

		return false;
	}

	/**
	 * Obtener total debito.
	 *
	 * @param impuestos the impuestos
	 * @return the BigDecimal
	 */
	private BigDecimal obtenerTotalDebito(DetalleMensualImpuestosView impuestos) {
		BigDecimal retornoTotal = obtenerFormatoDecimal(impuestos.getImpDebito01())
				.add(obtenerFormatoDecimal(impuestos.getImpDebito02()))
				.add(obtenerFormatoDecimal(impuestos.getImpDebito03()))
				.add(obtenerFormatoDecimal(impuestos.getImpDebito04()))
				.add(obtenerFormatoDecimal(impuestos.getImpDebito05()))
				.add(obtenerFormatoDecimal(impuestos.getImpDebito06()))
				.add(obtenerFormatoDecimal(impuestos.getImpDebito07()))
				.add(obtenerFormatoDecimal(impuestos.getImpDebito08()))
				.add(obtenerFormatoDecimal(impuestos.getImpDebito09()))
				.add(obtenerFormatoDecimal(impuestos.getImpDebito10()))
				.add(obtenerFormatoDecimal(impuestos.getImpDebito11()))
				.add(obtenerFormatoDecimal(impuestos.getImpDebito12()));
		return retornoTotal;
	}

	/**
	 * Obtener formato decimal.
	 *
	 * @param param the param
	 * @return the BigDecimal
	 */
	private BigDecimal obtenerFormatoDecimal(String param) {
		BigDecimal big = null;

		if (param.contains(".")) {
			param = param.replace(".", "");
			param = param.replace(",", ".");
		} else {
			param = param.replace(",", ".");
		}
		big = new BigDecimal(param);
		big = big.setScale(2, RoundingMode.HALF_UP);
		return big;
	}

	/**
	 * Evaluar existe imp debito.
	 *
	 * @param impuestos the impuestos
	 * @return the boolean
	 */
	private Boolean evaluarExisteImpDebito(DetalleMensualImpuestosView impuestos) {
		if (!impuestos.getImpDebito01().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpDebito02().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpDebito03().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpDebito04().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpDebito05().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpDebito06().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpDebito07().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpDebito08().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpDebito09().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpDebito10().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpDebito11().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}
		if (!impuestos.getImpDebito12().equals(FORMATO_CUATRO_DECIMALES)) {
			return true;
		}

		return false;
	}

	/**
	 * Detalle inversiones.
	 *
	 * @param resumen            the resumen
	 * @param libro              the libro
	 * @param tee                the tee
	 * @param tipoCliente        the tipo cliente
	 * @param tenenciasLegalView the tenencias legal view
	 */
	private void detalleInversiones(HSSFSheet resumen, HSSFWorkbook libro, TendenciasExcelEntity tee,
			String tipoCliente, TenenciasLegalView tenenciasLegalView) {
		TenenciasDetalleView tdv = tee.getTenenciasExcelView().getDetalle().getRespuesta();

		int linea = CELDA_4;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "Banco Santander S.A. - CUIT 30-50000845-4", libro, CELDA_3);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"SUS TENENCIAS AL  31/12/" + tee.getTenenciasExcelView().getAnio(), libro, CELDA_1);
		linea += CELDA_3;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"Detalle de la información de sus tenencias de productos y servicios con Santander útiles para el análisis de su situación impositiva",
				libro, 4);

		// DETALLE PLAZO FIJO
		linea += CELDA_2;
		Integer contPfUVA=0;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "Detalle Plazo Fijo", libro, 7);// CELDA_4);
		for (int i = 2; i < 5; i++) {
			resumen.getRow(linea).createCell(i);
			setCelda(resumen.getRow(linea).getCell(i), " ", libro, 7);
		}
		if (null == tdv || null == tdv.getDetallePlazoFijoViews() || tdv.getDetallePlazoFijoViews().size() == 0) {
			linea += CELDA_2;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
		} else {
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			if (anioConsulta>= ANIO_PL) {
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						"Cuenta Inversor Nro. " + tdv.getDetallePlazoFijoViews().get(0).getCuentaInversor(), libro,
						CELDA_5);
			}else {
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						"Cuenta Inversor Nro. " + tdv.getDetallePlazoFijoViews().get(0).getPenumcon(), libro, CELDA_5);
			}
			
			for (DetallePlazoFijoView plazoFijo : tdv.getDetallePlazoFijoViews()) {
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), "Plazo Fijo Nro. " + plazoFijo.getCertificado(),
							libro, CELDA_1);
				if (anioConsulta >= ANIO_PL) {
					if (plazoFijo.getParticipante() != null || (!plazoFijo.getParticipante().isEmpty())) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Partícipes  ", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getParticipante(), libro, CELDA_5);
					}
				}
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha de Constitución", libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getFechaConstitucion(), libro, CELDA_5);
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), "Plazo", libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2),
							plazoFijo.getPlazo() + (Integer.parseInt(plazoFijo.getPlazo()) > 1 ? " días " : " día "),
							libro, CELDA_5);
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), "Tipo Plazo Fijo", libro, CELDA_5);
					resumen.getRow(linea).createCell(CELDA_2);
					setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getNombrePlazoFijo(), libro, CELDA_5);
					//setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getTipo(), libro, CELDA_5);

					if (anioConsulta < ANIO_PL) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Intereses Percibidos", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								TenenciasHelper.monedaCanonico(plazoFijo.getDivisa()) + " " + plazoFijo.getIntCobrado(),
								libro, CELDA_5);
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Intereses Devengados", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								TenenciasHelper.monedaCanonico(plazoFijo.getDivisa()) + "  "
										+ plazoFijo.getIntDevengado(),
								libro, CELDA_5);
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha de Vencimiento ", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getFechaVencimiento(), libro,
								CELDA_5);
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Estado", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						if ("V".equals(plazoFijo.getEstado())) {
							setCelda(resumen.getRow(linea).getCell(CELDA_2), "Vencido cobrado", libro, CELDA_5);
						} else if ("C".equals(plazoFijo.getEstado())) {
							setCelda(resumen.getRow(linea).getCell(CELDA_2), "Cobrado", libro, CELDA_5);
						}
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Cuenta Origen / Destino", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								"Cuenta Corriente en " + TenenciasHelper.monedaCanonico(plazoFijo.getDivisa()) + " "
										+ plazoFijo.getCuentaCreditoNro(),
								libro, CELDA_5);
					} else {
						if(Integer.parseInt(plazoFijo.getPecodsub())==60 || Integer.parseInt(plazoFijo.getPecodsub())==160) {
						contPfUVA++;
						}
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha de Vencimiento ", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getFechaVencimiento(), libro,
								CELDA_5);
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Capital Invertido ", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),  plazoFijo.getCapitalInvertido() == null ? VACIO :
								TenenciasHelper.monedaCanonico(plazoFijo.getDivisa()) + " "
										+ plazoFijo.getCapitalInvertido() ,
								libro, CELDA_5);
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Tasa Nominal Anual ", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getTasaNominalAnual() + " % ", libro,
								CELDA_5);
						if (Double.parseDouble(plazoFijo.getTasaEfectivaAnual()) != 0) {
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1), "Tasa Efectiva Anual ", libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getTasaEfectivaAnual() + " % ",
									libro, CELDA_5);
						}
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), plazoFijo.getTipo().equals("UVA") ?  "Intereses Totales (*)" : "Intereses Totales ", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								TenenciasHelper.monedaCanonico(plazoFijo.getDivisa()) + " "
										+ plazoFijo.getInteresesTotales(),
								libro, CELDA_5);

						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Intereses Percibidos ", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								TenenciasHelper.monedaCanonico(plazoFijo.getDivisa()) + " " + plazoFijo.getIntCobrado(),
								libro, CELDA_5);
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Intereses Devengados ", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								TenenciasHelper.monedaCanonico(plazoFijo.getDivisa()) + " "
										+ plazoFijo.getIntDevengado(),
								libro, CELDA_5);
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Acción al Vencimiento ", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getAccionAlVencimiento(), libro,
								CELDA_5);
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Cuenta Destino", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								"Cuenta en " + TenenciasHelper.monedaCanonico(plazoFijo.getDivisa()) + " "
										+ plazoFijo.getCuentaCreditoNro(),
								libro, CELDA_5);
						if (Double.parseDouble(plazoFijo.getFrecuenciaCobroInteres()) != 0) {
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1), "Frecuencia de cobro de intereses ", libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getFrecuenciaCobroInteres(),
									libro, CELDA_5);
						}
						if (Double.parseDouble(plazoFijo.getFrecuenciaPrecancelacion()) >= 0 && 
								("0116".equals(plazoFijo.getPecodsub())
								|| "0016".equals(plazoFijo.getPecodsub())
								|| "0061".equals(plazoFijo.getPecodsub())
								|| "0161".equals(plazoFijo.getPecodsub()))){

								linea++;
								resumen.createRow(linea);
								resumen.getRow(linea).createCell(CELDA_1);
								
								if("0061".equals(plazoFijo.getPecodsub()) || "0161".equals(plazoFijo.getPecodsub())) {
									setCelda(resumen.getRow(linea).getCell(CELDA_1), "TNA de cancelación", libro, CELDA_5);
								}
								
								if("0116".equals(plazoFijo.getPecodsub()) || "0016".equals(plazoFijo.getPecodsub())) {
									setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fee de precancelación", libro, CELDA_5);
								}
								
								resumen.getRow(linea).createCell(CELDA_2);
								setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getFrecuenciaPrecancelacion() + " %",
										libro, CELDA_5);
							}
						
						if (plazoFijo.getFechaMinimaCancelacion() != null) {
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha mínima de cancelación", libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getFechaMinimaCancelacion(),
									libro, CELDA_5);
						}
						if (!plazoFijo.getFechaSolicitudCancelacion().equals("31/12/9999")) {
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha de solicitud de cancelación", libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getFechaSolicitudCancelacion(),
									libro, CELDA_5);
						}
						if (!plazoFijo.getFechaLiquidacion().equals("31/12/9999")) {
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha de liquidación", libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getFechaLiquidacion(), libro,
									CELDA_5);
						}
						if (plazoFijo.getClausulaAjuste() != null) {
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1), "Clausula de ajuste", libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getClausulaAjuste(), libro,
									CELDA_5);
						}
						if (!plazoFijo.getImpuestos().equals("-")) {
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1), "Impuestos", libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getImpuestos(), libro, CELDA_5);
						}
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Custodia", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getCustodia(), libro, CELDA_5);
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Modalidad", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getModalidad(), libro, CELDA_5);
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Comprobante / Certificado", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getPenumcon(), libro, CELDA_5);
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Estado", libro, CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), plazoFijo.getEstado(), libro, CELDA_5);
					}
					if (plazoFijo.getAjuCobrado() != null) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Percibido por unidad de ajuste", libro,
								CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								TenenciasHelper.monedaCanonico(plazoFijo.getDivisa()) + " " + plazoFijo.getAjuCobrado(),
								libro, CELDA_5);
					}

					if (Double.parseDouble(plazoFijo.getAjuDevengado().replace(",", ".")) != 0) {

						linea++;	
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Devengado por unidad de ajuste", libro,
								CELDA_5);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2),
								TenenciasHelper.monedaCanonico(plazoFijo.getDivisa()) + " "
										+ plazoFijo.getAjuDevengado(),
								libro, CELDA_5);
					}

					
					linea++;
			}
			
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1),
					"Total Tenencia de Plazo Fijos al 31-12-" + tee.getTenenciasExcelView().getAnio(), libro, CELDA_5);
			resumen.getRow(linea).createCell(CELDA_2);
			setCelda(resumen.getRow(linea).getCell(CELDA_2),
					SIGNO_PESO + " "
							+ tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones()
									.getTotalTenenciasPlazosFijos()
							+ " U" + SIGNO_PESO + "D " + tee.getTenenciasExcelView().getResumen().getRespuesta()
									.getInversiones().getTotalTenenciasPlazosFijosUS(),
					libro, CELDA_5);
			
			if (contPfUVA>0) {
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1),"(*) PF UVA Intereses Totales al vto. calculado sobre capital ajustado una vez vencido el plazo fijo. No incluye ajuste de capital.",
						libro, CELDA_5);
			}

		}

		// DETALLE FONDOS
		List<DatosParticipantesPLView> particiPL = new ArrayList<DatosParticipantesPLView>();
		
		linea += 2;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "Detalle Fondos", libro, 7);
		for (int i = 2; i < 5; i++) {
			resumen.getRow(linea).createCell(i);
			setCelda(resumen.getRow(linea).getCell(i), " ", libro, 7);
		}
		if (null == tdv || null == tdv.getResumenCuentaInversionesViewsFON()
				|| tdv.getResumenCuentaInversionesViewsFON().size() == 0
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getFondos() == null
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getFondos().size() == 0) {
			linea += 2;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
		} else {
			linea++;
			Map<String, ResumenFondoDetalleEntity> infoFondos = obtenerInfoFondos(
					tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getFondos(),
					tdv.getResumenCuentaInversionesViewsFON());
			if (infoFondos == null || infoFondos.isEmpty()) {
				linea += 2;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
			} else {
				particiPL = tee.getTenenciasExcelView().getDetalle().getRespuesta().getParticipantesViews();
				for (Map.Entry<String, ResumenFondoDetalleEntity> cuenta : infoFondos.entrySet()) {

					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Nro. Cuenta títulos: " + cuenta.getValue().getCuenta(), libro, CELDA_1);
					if (anioConsulta >= ANIO_PL) {
						for (DatosParticipantesPLView item : particiPL) {
							for (ParticipantesPLView partView : item.getParticipantesView()) {
								if ((cuenta.getValue().getCuenta()
										.equals(TenenciasHelper.nroCuentaCanonico(item.getNumeroCuenta())))
										&& partView.getTipoCuenta().equals("CT")) {
									linea++;
									resumen.createRow(linea);
									resumen.getRow(linea).createCell(CELDA_1);
									setCelda(resumen.getRow(linea).getCell(CELDA_1),
											"Comitente: " + partView.getApellido().trim() + " , "
													+ partView.getNombre().trim() + " (participación desde "
													+ convFecha(null, partView.getFechaAlta()) + " - "
													+ convFecha(cuenta.getValue().getAnio(), partView.getFechaBaja())
													+ " )",
											libro, CELDA_1);
								}
							}
						}
					}
					Map<String, FondoDetalleEntity> mapFondos = cuenta.getValue().getFondos();
					if (mapFondos == null || mapFondos.isEmpty()) {
						linea += 2;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
					} else {
						List<FondoDetalleEntity> fondos = new ArrayList<FondoDetalleEntity>();
						for (Map.Entry<String, FondoDetalleEntity> fondo : mapFondos.entrySet()) {
							fondos.add(fondo.getValue());
						}
						Collections.sort(fondos, new Comparator<FondoDetalleEntity>() {
							@Override
							public int compare(FondoDetalleEntity f1, FondoDetalleEntity f2) {
								return f1.getDivisa().compareTo(f2.getDivisa());
							}
						});
//						for (Map.Entry<String, FondoDetalleEntity> fondo : mapFondos.entrySet()) {
						for (FondoDetalleEntity fondo : fondos) {
							String divisa = fondo.getDivisa();
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1), fondo.getDescripcion(), libro,
									CELDA_1);

							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha Solicitud", libro, CELDA_1);

							if (anioConsulta < ANIO_PL) {

								resumen.getRow(linea).createCell(CELDA_2);
								setCelda(resumen.getRow(linea).getCell(CELDA_2), "Concepto", libro, CELDA_1);
								resumen.getRow(linea).createCell(CELDA_3);
								setCelda(resumen.getRow(linea).getCell(CELDA_3), "Cantidad cuotapartes", libro,
										CELDA_1);
								resumen.getRow(linea).createCell(CELDA_4);
								setCelda(resumen.getRow(linea).getCell(CELDA_4), "Valor de cuotaparte", libro, CELDA_1);
								resumen.getRow(linea).createCell(CELDA_5);
								setCelda(resumen.getRow(linea).getCell(CELDA_5), "Importe bruto", libro, CELDA_1);
								resumen.getRow(linea).createCell(CELDA_6);
								setCelda(resumen.getRow(linea).getCell(CELDA_6), "Cuenta Origen / Destino", libro,
										CELDA_1);
								resumen.getRow(linea).createCell(CELDA_7);
								setCelda(resumen.getRow(linea).getCell(CELDA_7), "Comprobante ", libro, CELDA_1);

							} else {

								resumen.getRow(linea).createCell(CELDA_2);
								setCelda(resumen.getRow(linea).getCell(CELDA_2), "Fecha Liquidación", libro, CELDA_1);
								resumen.getRow(linea).createCell(CELDA_3);
								setCelda(resumen.getRow(linea).getCell(CELDA_3), "Concepto", libro, CELDA_1);
								resumen.getRow(linea).createCell(CELDA_4);
								setCelda(resumen.getRow(linea).getCell(CELDA_4), "Cantidad cuotapartes", libro,
										CELDA_1);
								resumen.getRow(linea).createCell(CELDA_5);
								setCelda(resumen.getRow(linea).getCell(CELDA_5), "Valor de cuotaparte", libro, CELDA_1);
								resumen.getRow(linea).createCell(CELDA_6);
								setCelda(resumen.getRow(linea).getCell(CELDA_6), "Importe", libro, CELDA_1);
								resumen.getRow(linea).createCell(CELDA_7);
								setCelda(resumen.getRow(linea).getCell(CELDA_7), "Cuenta Origen / Destino", libro,
										CELDA_1);
								resumen.getRow(linea).createCell(CELDA_8);
								setCelda(resumen.getRow(linea).getCell(CELDA_8), "Nro. Comprobante", libro, CELDA_1);
								resumen.getRow(linea).createCell(CELDA_9);
								setCelda(resumen.getRow(linea).getCell(CELDA_9), "Gasto de entrada", libro, CELDA_1);
								resumen.getRow(linea).createCell(CELDA_10);
								setCelda(resumen.getRow(linea).getCell(CELDA_10), "Gasto de Salida", libro, CELDA_1);

							}
							if (fondo.getDetalles() == null || fondo.getDetalles().size() == 0) {
								linea += 2;
								resumen.createRow(linea);
								resumen.getRow(linea).createCell(CELDA_1);
								setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
							} else {
								Collections.sort(fondo.getDetalles(), new Comparator<ResumenCuentaInversionesView>() {
									@Override
									public int compare(ResumenCuentaInversionesView f1, ResumenCuentaInversionesView f2) {
										SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
										String strFecha1 = f1.getFecha();
										String strFecha2 = f2.getFecha();
										Date   datFecha1 =null;
										Date   datFecha2 = null;
										try {
											datFecha1 = formato.parse(strFecha1);
											datFecha2 = formato.parse(strFecha2);
								        } 
								        catch (ParseException ex) 
								        {
//								            System.out.println(ex);
								        }
										return datFecha1.compareTo(datFecha2);
									}
								});
								for (ResumenCuentaInversionesView detalle : fondo.getDetalles()) {
									linea++;
									resumen.createRow(linea);
									resumen.getRow(linea).createCell(CELDA_1);
									setCelda(resumen.getRow(linea).getCell(CELDA_1), detalle.getFecha(), libro,
											CELDA_5);
									if (anioConsulta < ANIO_PL) {
										resumen.getRow(linea).createCell(CELDA_2);
										setCelda(resumen.getRow(linea).getCell(CELDA_2), detalle.getConcepto(), libro,
												CELDA_5);
										resumen.getRow(linea).createCell(CELDA_3);
										setCelda(resumen.getRow(linea).getCell(CELDA_3), detalle.getCantidad(), libro,
												CELDA_5);
										resumen.getRow(linea).createCell(CELDA_4);
										setCelda(resumen.getRow(linea).getCell(CELDA_4),
												 detalle.getCotizacion() == null
														|| detalle.getCotizacion().isEmpty() ? VACIO
																: divisa + " " + obtenerImporteDecimalFormateador(
																		detalle.getCotizacion()),
												libro, CELDA_5);
										resumen.getRow(linea).createCell(CELDA_5);
										setCelda(resumen.getRow(linea).getCell(CELDA_5),
												divisa + " " + detalle.getImporte(), libro, CELDA_5);
										resumen.getRow(linea).createCell(CELDA_6);
										setCelda(resumen.getRow(linea).getCell(CELDA_6), detalle.getNroCtaOri(), libro,
												CELDA_5);
										resumen.getRow(linea).createCell(CELDA_7);
										setCelda(resumen.getRow(linea).createCell(CELDA_7), detalle.getComprobante(),
												libro, CELDA_5);

									} else {
										resumen.getRow(linea).createCell(CELDA_2);
										setCelda(resumen.getRow(linea).getCell(CELDA_2), detalle.getFecha_liq(), libro,
												CELDA_5);
										resumen.getRow(linea).createCell(CELDA_3);
										setCelda(resumen.getRow(linea).getCell(CELDA_3), detalle.getConcepto(), libro,
												CELDA_5);
										resumen.getRow(linea).createCell(CELDA_4);
										setCelda(resumen.getRow(linea).getCell(CELDA_4), detalle.getCantidad()== null || detalle.getCantidad().equals("0,0000") ? VACIO : detalle.getCantidad(), libro,
												CELDA_5);
										resumen.getRow(linea).createCell(CELDA_5);
										setCelda(resumen.getRow(linea).getCell(CELDA_5),  detalle.getCotizacion() ==null || detalle.getCotizacion().equals("0,000000") ? VACIO :
												divisa + " " + detalle.getCotizacion(), libro, CELDA_5);
										resumen.getRow(linea).createCell(CELDA_6);
										setCelda(resumen.getRow(linea).getCell(CELDA_6), detalle.getImporte() == null || detalle.getImporte().equals("0,00") ? VACIO :
												divisa + " " + detalle.getImporte(), libro, CELDA_5);
										resumen.getRow(linea).createCell(CELDA_7);
										setCelda(resumen.getRow(linea).getCell(CELDA_7), detalle.getNroCtaOri()==null || detalle.getNroCtaOri().isEmpty() ? VACIO: detalle.getNroCtaOri(), libro,
												CELDA_5);
										resumen.getRow(linea).createCell(CELDA_8);
										setCelda(resumen.getRow(linea).getCell(CELDA_8),  detalle.getComprobante()==null ? VACIO :detalle.getComprobante(),
												libro, CELDA_5);
										resumen.getRow(linea).createCell(CELDA_9);
										setCelda(resumen.getRow(linea).getCell(CELDA_9),
												 detalle.getGastoEntrada() == null || Double.parseDouble(detalle.getGastoEntrada())==0
																? VACIO
																: ISBANStringUtils.formatearConComaYDosDecimales2(
																		detalle.getGastoEntrada()) + " %",
												libro, CELDA_5);
										resumen.getRow(linea).createCell(CELDA_10);
										setCelda(resumen.getRow(linea).getCell(CELDA_10), detalle.getGastoSalida()==null || Double.parseDouble(detalle.getGastoSalida())==0 ? VACIO :
												 ISBANStringUtils.formatearConComaYDosDecimales2(
														detalle.getGastoSalida()) + " %",
												libro, CELDA_5);
									}

								}
							}
						}
					}
				}
			}
		}

		// DETALLE BONOS
		linea += 2;
		String monedatrxTit;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "Detalle Títulos públicos y privados", libro, 7);
		for (int i = 2; i < 5; i++) {
			resumen.getRow(linea).createCell(i);
			setCelda(resumen.getRow(linea).getCell(i), " ", libro, 7);
		}
		if (null == tdv || null == tdv.getResumenCuentaInversionesViewsBON()
				|| tdv.getResumenCuentaInversionesViewsBON().size() == 0
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getBonos() == null
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getBonos().isEmpty()) {
			linea += 2;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
		} else {
			List<TenenciasTitulosExcel> bonos = armarListaTitulos(tdv.getResumenCuentaInversionesViewsBON(),
					tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getBonos());
			for (TenenciasTitulosExcel tenencia : bonos) {
				linea += 2;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						"Nro. Cuenta títulos: " + TenenciasHelper.nroCuentaCanonico(tenencia.getCuenta()), libro,
						CELDA_1);
				if (anioConsulta >= ANIO_PL) {
					for (DatosParticipantesPLView item : particiPL) {
						for (ParticipantesPLView partView : item.getParticipantesView()) {
							if ((tenencia.getCuenta().equals(item.getNumeroCuenta()))
									&& partView.getTipoCuenta().equals("CT")) {
								linea++;
								resumen.createRow(linea);
								resumen.getRow(linea).createCell(CELDA_1);
								setCelda(resumen.getRow(linea).getCell(CELDA_1), "Comitente: "
										+ partView.getApellido().trim() + " , " + partView.getNombre().trim()
										+ " (participación desde " + convFecha(null, partView.getFechaAlta()) + " - "
										+ convFecha(tee.getTenenciasExcelView().getAnio(), partView.getFechaBaja())
										+ " )", libro, CELDA_1);
							}
						}
					}
				}
				for (TitulosResumenImpositivoExcel resumenBonos : tenencia.getTitulos()) {
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), resumenBonos.getNombre(), libro, CELDA_1);
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					if (resumenBonos.getList().get(0).getDivisa() != null) {
						setCelda(resumen.getRow(linea).getCell(CELDA_1),
								"Moneda de emisión: " + resumenBonos.getList().get(0).getDivisa(), libro, CELDA_1);
					} else {
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Moneda de emisión: ", libro, CELDA_1);
					}
					if (anioConsulta < ANIO_PL) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha Liquidacion", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), "Concepto", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_3);
						setCelda(resumen.getRow(linea).getCell(CELDA_3), "Comprobante/Nro. de operación", libro,
								CELDA_1);
						resumen.getRow(linea).createCell(CELDA_4);
						setCelda(resumen.getRow(linea).getCell(CELDA_4), "Cantidad nominales", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_5);
						setCelda(resumen.getRow(linea).getCell(CELDA_5), "Cotización", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_6);
						setCelda(resumen.getRow(linea).getCell(CELDA_6), "Importe", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_7);
						setCelda(resumen.getRow(linea).getCell(CELDA_7), "Cuenta origen/destino", libro, CELDA_1);
					} else {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha Liquidación", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), "Concepto", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_3);
						setCelda(resumen.getRow(linea).getCell(CELDA_3), "Comprobante/Nro. operación", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_4);
						setCelda(resumen.getRow(linea).getCell(CELDA_4), "Cantidad nominales", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_5);
						setCelda(resumen.getRow(linea).getCell(CELDA_5), "Cotización", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_6);
						setCelda(resumen.getRow(linea).getCell(CELDA_6), "Renta", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_7);
						setCelda(resumen.getRow(linea).getCell(CELDA_7), "Amortización", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_8);
						setCelda(resumen.getRow(linea).getCell(CELDA_8), "Importe bruto", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_9);
						setCelda(resumen.getRow(linea).getCell(CELDA_9), "Gastos", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_10);
						setCelda(resumen.getRow(linea).getCell(CELDA_10), "Impuestos", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_11);
						setCelda(resumen.getRow(linea).getCell(CELDA_11), "Importe neto", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_12);
						setCelda(resumen.getRow(linea).getCell(CELDA_12), "Cuenta origen/destino", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_13);
						setCelda(resumen.getRow(linea).getCell(CELDA_13), "Moneda transacción", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_14);
						setCelda(resumen.getRow(linea).getCell(CELDA_14), "Nro. de orden", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_15);
						setCelda(resumen.getRow(linea).getCell(CELDA_15), "Nro. de boleto", libro, CELDA_1);
					}
					for (ResumenCuentaInversionesView resumenBono : resumenBonos.getList()) {
						if (anioConsulta < ANIO_PL) {
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1), formatearFechaTitulos(resumenBono.getFecha()), libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), resumenBono.getConcepto(), libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_3);
							setCelda(resumen.getRow(linea).getCell(CELDA_3), resumenBono.getComprobante(), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_4);
							setCelda(resumen.getRow(linea).getCell(CELDA_4),
									ISBANStringUtils.formatearConComaYVariosDecimales2(resumenBono.getCantidad(), 2),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_5);
							setCelda(resumen.getRow(linea).getCell(CELDA_5),
									Double.parseDouble(resumenBono.getCotizacion()) == 0 ? "-"
											: resumenBono.getDivisa() + " " + ISBANStringUtils
													.formatearConComaYVariosDecimales2(resumenBono.getCotizacion(), 4),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_9);
							setCelda(resumen.getRow(linea).getCell(CELDA_9),
									resumenBono.getDivisa() + " "
											+ ISBANStringUtils.formatearConComaYDosDecimales2(resumenBono.getImporte()),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_10);
							setCelda(resumen.getRow(linea).getCell(CELDA_10),
									obtenerCtaOri(resumenBono.getSucCtaOri(), resumenBono.getNroCtaOri()), libro,
									CELDA_5);
						} else {
							monedatrxTit = resumenBono.getMonedaTransaccion() == null || resumenBono.getMonedaTransaccion().isEmpty() ? VACIO:TenenciasHelper.monedaCanonico(resumenBono.getMonedaTransaccion());
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1),
									formatearFechaTitulos(resumenBono.getFecha()), libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), resumenBono.getConcepto(), libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_3);
							setCelda(resumen.getRow(linea).getCell(CELDA_3), resumenBono.getComprobante()== null || resumenBono.getComprobante().isEmpty() ? VACIO : resumenBono.getComprobante(), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_4);
							setCelda(resumen.getRow(linea).getCell(CELDA_4),
									(resumenBono.getCantidad() == null || Double.parseDouble(resumenBono.getCantidad())== 0) ? VACIO
											: ISBANStringUtils
													.formatearConComaYVariosDecimales2(resumenBono.getCantidad(), 2),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_5);
							setCelda(resumen.getRow(linea).getCell(CELDA_5),
									resumenBono.getCotizacion() == null || Double.parseDouble(resumenBono.getCotizacion())==0
											? VACIO
											: monedatrxTit + " "+ ISBANStringUtils.formatearConComaYVariosDecimales2(resumenBono.getCotizacion(), 4),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_6);
							setCelda(resumen.getRow(linea).getCell(CELDA_6),
									resumenBono.getRenta() == null || Double.parseDouble(resumenBono.getRenta())==0 ? VACIO
											: monedatrxTit +" " + ISBANStringUtils.formatearConComaYVariosDecimales2(resumenBono.getRenta(),
													2),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_7);
							setCelda(resumen.getRow(linea).getCell(CELDA_7),
									resumenBono.getAmortizacion() == null
											|| Double.parseDouble(resumenBono.getAmortizacion()) == 0 ? VACIO
													: monedatrxTit+ " "+ ISBANStringUtils.formatearConComaYVariosDecimales2(
															resumenBono.getAmortizacion(), 2),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_8);
							setCelda(resumen.getRow(linea).getCell(CELDA_8),
									resumenBono.getImporteBruto() == null
											|| Double.parseDouble(resumenBono.getImporteBruto()) == 0
													? VACIO
													: monedatrxTit+ " " + ISBANStringUtils.formatearConComaYDosDecimales2(
																	resumenBono.getImporteBruto()),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_9);
							setCelda(resumen.getRow(linea).getCell(CELDA_9),
									(resumenBono.getGastoEntrada() == null || Double.parseDouble(resumenBono.getGastoEntrada()) == 0
											|| resumenBono.getGastoEntrada().isEmpty())
													? VACIO
													: (monedatrxTit + " "
															+ ISBANStringUtils.formatearConComaYDosDecimales2(
																	resumenBono.getGastoEntrada())),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_10);
							setCelda(resumen.getRow(linea).getCell(CELDA_10),
									Double.parseDouble(resumenBono.getImpuestos()) == 0
											|| resumenBono.getImpuestos().isEmpty()
											|| resumenBono.getImpuestos() == null
													? VACIO
													: (monedatrxTit + " "
															+ ISBANStringUtils.formatearConComaYDosDecimales2(
																	resumenBono.getImpuestos())),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_11);
							setCelda(resumen.getRow(linea).getCell(CELDA_11),
									resumenBono.getImporte() == null ||Double.parseDouble(resumenBono.getImporte()) == 0
											|| resumenBono.getImporte().isEmpty()
													? VACIO
													: (monedatrxTit + " "
															+ ISBANStringUtils.formatearConComaYDosDecimales2(
																	resumenBono.getImporte())),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_12);
							setCelda(resumen.getRow(linea).getCell(CELDA_12),resumenBono.getNroCtaOri()== null  || resumenBono.getNroCtaOri().isEmpty() ? VACIO : 
									obtenerCtaOri(resumenBono.getSucCtaOri(), resumenBono.getNroCtaOri()), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_13);
							setCelda(resumen.getRow(linea).getCell(CELDA_13), resumenBono.getMonedaTransaccion() == null || resumenBono.getMonedaTransaccion().isEmpty() ? VACIO : monedaDescripcion(resumenBono.getMonedaTransaccion()), libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_14);
							setCelda(resumen.getRow(linea).getCell(CELDA_14), resumenBono.getNroOrden()==null || Integer.parseInt(resumenBono.getNroOrden())==0 ? VACIO : resumenBono.getNroOrden(), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_15);
							setCelda(resumen.getRow(linea).getCell(CELDA_15), resumenBono.getNroBoleto()==null || Integer.parseInt(resumenBono.getNroBoleto())==0 ? VACIO : resumenBono.getNroBoleto(), libro,
									CELDA_5);
						}
					}
				}
			}
		}

		// DETALLE ACCIONES
		linea += 2;
		String monedaTrx;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "Detalle Acciones y CEDEARs", libro, 7);
		for (int i = 2; i < 5; i++) {
			resumen.getRow(linea).createCell(i);
			setCelda(resumen.getRow(linea).getCell(i), " ", libro, 7);
		}
		if (null == tdv || null == tdv.getResumenCuentaInversionesViewsSHS()
				|| tdv.getResumenCuentaInversionesViewsSHS().size() == 0
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getAcciones() == null
				|| tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getAcciones().isEmpty()) {
			linea += 2;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
		} else {
			List<TenenciasTitulosExcel> acciones = armarListaTitulos(tdv.getResumenCuentaInversionesViewsSHS(),
					tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getAcciones());
			linea++;
			for (TenenciasTitulosExcel tenencia : acciones) {
				linea += 2;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1),
						"Nro. Cuenta títulos: " + TenenciasHelper.nroCuentaCanonico(tenencia.getCuenta()), libro,
						CELDA_1);
				if (anioConsulta >= ANIO_PL) {
					for (DatosParticipantesPLView item : particiPL) {
						for (ParticipantesPLView partView : item.getParticipantesView()) {
							if ((tenencia.getCuenta().equals(item.getNumeroCuenta()))
									&& partView.getTipoCuenta().equals("CT")) {
								linea++;
								resumen.createRow(linea);
								resumen.getRow(linea).createCell(CELDA_1);
								setCelda(resumen.getRow(linea).getCell(CELDA_1), "Comitente: "
										+ partView.getApellido().trim() + " , " + partView.getNombre().trim()
										+ " (participación desde " + convFecha(null, partView.getFechaAlta()) + " - "
										+ convFecha(tee.getTenenciasExcelView().getAnio(), partView.getFechaBaja())
										+ " )", libro, CELDA_1);
							}
						}
					}
				}
				for (TitulosResumenImpositivoExcel resumenAcciones : tenencia.getTitulos()) {
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1), resumenAcciones.getNombre(), libro, CELDA_1);
					linea++;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					if (resumenAcciones.getList().get(0).getDivisa() != null) {
						setCelda(resumen.getRow(linea).getCell(CELDA_1),
								"Moneda de emisión: " + resumenAcciones.getList().get(0).getDivisa(), libro, CELDA_1);
					} else {
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Moneda de emisión:", libro, CELDA_1);
					}
					if (anioConsulta < ANIO_PL) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha Liquidación", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), "Concepto", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_3);
						setCelda(resumen.getRow(linea).getCell(CELDA_3), "Comprobante/Nro. de operación", libro,
								CELDA_1);
						resumen.getRow(linea).createCell(CELDA_4);
						setCelda(resumen.getRow(linea).getCell(CELDA_4), "Cantidad nominales", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_5);
						setCelda(resumen.getRow(linea).getCell(CELDA_5), "Cotización", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_6);
						setCelda(resumen.getRow(linea).getCell(CELDA_6), "Importe", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_7);
						setCelda(resumen.getRow(linea).getCell(CELDA_7), "Cuenta origen/destino", libro, CELDA_1);
					} else {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha Liquidación", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), "Concepto", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_3);
						setCelda(resumen.getRow(linea).getCell(CELDA_3), "Comprobante/Nro. operación", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_4);
						setCelda(resumen.getRow(linea).getCell(CELDA_4), "Cantidad nominales", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_5);
						setCelda(resumen.getRow(linea).getCell(CELDA_5), "Cotización", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_6);
						setCelda(resumen.getRow(linea).getCell(CELDA_6), "Importe bruto", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_7);
						setCelda(resumen.getRow(linea).getCell(CELDA_7), "Gastos", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_8);
						setCelda(resumen.getRow(linea).getCell(CELDA_8), "Impuestos", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_9);
						setCelda(resumen.getRow(linea).getCell(CELDA_9), "Importe neto", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_10);
						setCelda(resumen.getRow(linea).getCell(CELDA_10), "Cuenta origen/destino", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_11);
						setCelda(resumen.getRow(linea).getCell(CELDA_11), "Moneda transacción", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_12);
						setCelda(resumen.getRow(linea).getCell(CELDA_12), "Nro. de orden", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_13);
						setCelda(resumen.getRow(linea).getCell(CELDA_13), "Nro. de boleto", libro, CELDA_1);
					}
					for (ResumenCuentaInversionesView resumenAccion : resumenAcciones.getList()) {
						if (anioConsulta < ANIO_PL) {
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1), formatearFechaTitulos(resumenAccion.getFecha()), libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), resumenAccion.getConcepto(), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_3);
							setCelda(resumen.getRow(linea).getCell(CELDA_3), resumenAccion.getComprobante()==null ? VACIO :resumenAccion.getComprobante(), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_4);
							setCelda(resumen.getRow(linea).getCell(CELDA_4),
									resumenAccion.getCantidad() == null
											|| Double.parseDouble(resumenAccion.getCantidad()) == 0 ? VACIO
													: ISBANStringUtils.formatearConComaYVariosDecimales2(
															resumenAccion.getCantidad(), 2),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_5);
							setCelda(resumen.getRow(linea).getCell(CELDA_5), Double.parseDouble(resumenAccion.getCotizacion())==0
									? "-"
									: TenenciasHelper.monedaCanonico(resumenAccion.getDivisa()) + " " + ISBANStringUtils
											.formatearConComaYVariosDecimales2(resumenAccion.getCotizacion(), 4),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_6);
							setCelda(resumen.getRow(linea).getCell(CELDA_6),
									TenenciasHelper.monedaCanonico(resumenAccion.getDivisa()) + " "
											+ ISBANStringUtils
													.formatearConComaYDosDecimales2(resumenAccion.getImporte()),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_7);
							setCelda(resumen.getRow(linea).getCell(CELDA_7),
									obtenerCtaOri(resumenAccion.getSucCtaOri(), resumenAccion.getNroCtaOri()), libro,
									CELDA_5);
						} else {
							monedaTrx= resumenAccion.getMonedaTransaccion() == null || resumenAccion.getMonedaTransaccion().isEmpty() ? " " : TenenciasHelper.monedaCanonico(resumenAccion.getMonedaTransaccion());
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1),
									formatearFechaTitulos(resumenAccion.getFecha()), libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), resumenAccion.getConcepto(), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_3);
							setCelda(resumen.getRow(linea).getCell(CELDA_3), resumenAccion.getComprobante() == null || resumenAccion.getComprobante().isEmpty()? VACIO : resumenAccion.getComprobante(), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_4);
							setCelda(resumen.getRow(linea).getCell(CELDA_4), resumenAccion.getCantidad()== null || Double.parseDouble(resumenAccion.getCantidad())==0 ? VACIO :
									ISBANStringUtils.formatearConComaYVariosDecimales2(resumenAccion.getCantidad(), 2),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_5);
							setCelda(resumen.getRow(linea).getCell(CELDA_5),
									resumenAccion.getCotizacion() == null || Double.parseDouble(resumenAccion.getCotizacion())==0
											? VACIO
											: monedaTrx + " "+ ISBANStringUtils.formatearConComaYVariosDecimales2(
															resumenAccion.getCotizacion(), 4),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_6);
							setCelda(resumen.getRow(linea).getCell(CELDA_6), resumenAccion.getImporteBruto()==null || Double.parseDouble(resumenAccion.getImporteBruto())==0 ? VACIO :
								monedaTrx + " "	+ ISBANStringUtils.formatearConComaYDosDecimales2(resumenAccion.getImporteBruto()),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_7);
							setCelda(resumen.getRow(linea).getCell(CELDA_7),resumenAccion.getGastoEntrada() == null || Double.parseDouble(resumenAccion.getGastoEntrada())==0 ? VACIO :
								monedaTrx+ " "	+ ISBANStringUtils.formatearConComaYDosDecimales2(resumenAccion.getGastoEntrada()),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_8);
							setCelda(resumen.getRow(linea).getCell(CELDA_8), resumenAccion.getImpuestos() == null || Double.parseDouble(resumenAccion.getImpuestos()) == 0 ? VACIO :
								monedaTrx + " "	+ ISBANStringUtils.formatearConComaYDosDecimales2(resumenAccion.getImpuestos()),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_9);
							setCelda(resumen.getRow(linea).getCell(CELDA_9), resumenAccion.getImporte() == null || Double.parseDouble(resumenAccion.getImporte())== 0 ? VACIO :
								monedaTrx + " "	+ ISBANStringUtils.formatearConComaYDosDecimales2(resumenAccion.getImporte()),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_10);
							setCelda(resumen.getRow(linea).getCell(CELDA_10),
									obtenerCtaOri(resumenAccion.getSucCtaOri(), resumenAccion.getNroCtaOri()== null || resumenAccion.getNroCtaOri().isEmpty()? VACIO : resumenAccion.getNroCtaOri()), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_11);
							setCelda(resumen.getRow(linea).getCell(CELDA_11),resumenAccion.getMonedaTransaccion()==null || resumenAccion.getMonedaTransaccion().isEmpty() ? VACIO :monedaDescripcion(resumenAccion.getMonedaTransaccion()), libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_12);
							setCelda(resumen.getRow(linea).getCell(CELDA_12), resumenAccion.getNroOrden() == null || Double.parseDouble(resumenAccion.getNroOrden())==0 ? VACIO : resumenAccion.getNroOrden(), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_13);
							setCelda(resumen.getRow(linea).getCell(CELDA_13), resumenAccion.getNroBoleto() == null || Double.parseDouble(resumenAccion.getNroBoleto())==0 ? VACIO : resumenAccion.getNroBoleto(), libro,
									CELDA_5);
						}
					}
				}
			}
		}

		if (anioConsulta >= ANIO_PL) {
			// DETALLE CUSTODIA MONETARIA
			linea += 2;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), "Custodia Monetaria", libro, 7);
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), "Detalle Divisas", libro, 7);
			for (int i = 2; i < 5; i++) {
				resumen.getRow(linea).createCell(i);
				setCelda(resumen.getRow(linea).getCell(i), " ", libro, 7);
			}
			if (null == tdv || null == tdv.getResumenCuentaInversionesViewsMON()
					|| tdv.getResumenCuentaInversionesViewsMON().size() == 0
					|| tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones()
							.getMonedaExtranjera() == null
					|| tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getMonedaExtranjera()
							.isEmpty()) {
				linea += 2;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
			} else {
				List<TenenciasTitulosExcel> custodia = armarListaTitulos(tdv.getResumenCuentaInversionesViewsMON(),
						tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getMonedaExtranjera());
				linea++;
				for (TenenciasTitulosExcel tenencia : custodia) {
					linea += 2;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Nro. Cuenta títulos:  " + TenenciasHelper.nroCuentaCanonico(tenencia.getCuenta()), libro,
							CELDA_1);
					for (DatosParticipantesPLView item : particiPL) {
						for (ParticipantesPLView partView : item.getParticipantesView()) {
							if ((tenencia.getCuenta().equals(item.getNumeroCuenta()))
									&& partView.getTipoCuenta().equals("CT")) {
								linea++;
								resumen.createRow(linea);
								resumen.getRow(linea).createCell(CELDA_1);
								setCelda(resumen.getRow(linea).getCell(CELDA_1), "Comitente: "
										+ partView.getApellido().trim() + " , " + partView.getNombre().trim()
										+ " (participación desde " + convFecha(null, partView.getFechaAlta()) + " - "
										+ convFecha(tee.getTenenciasExcelView().getAnio(), partView.getFechaBaja())
										+ " )", libro, CELDA_1);
							}
						}
					}
					for (TitulosResumenImpositivoExcel resumenCustodiasMON : tenencia.getTitulos()) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), resumenCustodiasMON.getNombre(), libro,
								CELDA_1);
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						if (resumenCustodiasMON.getList().get(0).getDivisa() != null) {
							setCelda(resumen.getRow(linea).getCell(CELDA_1),
									"Moneda de emisión: " + resumenCustodiasMON.getList().get(0).getDivisa(), libro,
									CELDA_1);
						} else {
							setCelda(resumen.getRow(linea).getCell(CELDA_1), "Moneda de emisión: ", libro, CELDA_1);
						}
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha Liquidación", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), "Concepto", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_3);
						setCelda(resumen.getRow(linea).getCell(CELDA_3), "Comprobante/Nro. operación", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_4);
						setCelda(resumen.getRow(linea).getCell(CELDA_4), "Cantidad nominales", libro, CELDA_1);

						for (ResumenCuentaInversionesView resumenCustodiaMon : resumenCustodiasMON.getList()) {
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1),
									formatearFechaTitulos(resumenCustodiaMon.getFecha()), libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), resumenCustodiaMon.getConcepto(), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_3);
							setCelda(resumen.getRow(linea).getCell(CELDA_3),
									resumenCustodiaMon.getComprobante() == null
											|| resumenCustodiaMon.getComprobante().isEmpty() ? VACIO
													: resumenCustodiaMon.getComprobante(),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_4);
							setCelda(resumen.getRow(linea).getCell(CELDA_4),
									resumenCustodiaMon.getCantidad() == null
											|| resumenCustodiaMon.getCantidad().isEmpty() ? VACIO
													: ISBANStringUtils.formatearConComaYVariosDecimales2(
															resumenCustodiaMon.getCantidad(), 2),
									libro, CELDA_5);
						}
					}
				}
			}

			// DETALLE CUSTODIA EFECTIVA
			linea += 2;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1), "Detalle Efectivo", libro, 7);
			for (int i = 2; i < 5; i++) {
				resumen.getRow(linea).createCell(i);
				setCelda(resumen.getRow(linea).getCell(i), " ", libro, 7);
			}
			if (null == tdv || tdv.getResumenCuentaInversionesViewsCEF() == null
					|| tdv.getResumenCuentaInversionesViewsCEF().size() == 0
					|| tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones()
							.getMonedaExtranjera() == null
					|| tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getMonedaExtranjera()
							.isEmpty()) {
				linea++;
				resumen.createRow(linea);
				resumen.getRow(linea).createCell(CELDA_1);
				setCelda(resumen.getRow(linea).getCell(CELDA_1), NOHAY, libro, CELDA_5);
			} else {
				List<TenenciasTitulosExcel> custodia = armarListaTitulos(tdv.getResumenCuentaInversionesViewsCEF(),
						tee.getTenenciasExcelView().getResumen().getRespuesta().getInversiones().getMonedaExtranjera());
				linea++;
				for (TenenciasTitulosExcel tenencia : custodia) {
					linea += 2;
					resumen.createRow(linea);
					resumen.getRow(linea).createCell(CELDA_1);
					setCelda(resumen.getRow(linea).getCell(CELDA_1),
							"Nro. Cuenta títulos: " + TenenciasHelper.nroCuentaCanonico(tenencia.getCuenta()), libro,
							CELDA_1);
					for (DatosParticipantesPLView item : particiPL) {
						for (ParticipantesPLView partView : item.getParticipantesView()) {
							if ((tenencia.getCuenta().equals(item.getNumeroCuenta()))
									&& partView.getTipoCuenta().equals("CT")) {
								linea++;
								resumen.createRow(linea);
								resumen.getRow(linea).createCell(CELDA_1);
								setCelda(resumen.getRow(linea).getCell(CELDA_1), "Comitente: "
										+ partView.getApellido().trim() + " , " + partView.getNombre().trim()
										+ " (participación desde " + convFecha(null, partView.getFechaAlta()) + " - "
										+ convFecha(tee.getTenenciasExcelView().getAnio(), partView.getFechaBaja())
										+ " )", libro, CELDA_1);
							}
						}
					}
					for (TitulosResumenImpositivoExcel resumenCustodiaCEF : tenencia.getTitulos()) {
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), resumenCustodiaCEF.getNombre(), libro,
								CELDA_1);
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						if (resumenCustodiaCEF.getList().get(0).getDivisa() != null) {
							setCelda(resumen.getRow(linea).getCell(CELDA_1),
									"Moneda de emisión: " + resumenCustodiaCEF.getList().get(0).getDivisa(), libro,
									CELDA_1);
						} else {
							setCelda(resumen.getRow(linea).getCell(CELDA_1), "Moneda de emisión: ", libro, CELDA_1);
						}
						linea++;
						resumen.createRow(linea);
						resumen.getRow(linea).createCell(CELDA_1);
						setCelda(resumen.getRow(linea).getCell(CELDA_1), "Fecha Liquidación", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_2);
						setCelda(resumen.getRow(linea).getCell(CELDA_2), "Concepto", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_3);
						setCelda(resumen.getRow(linea).getCell(CELDA_3), "Comprobante/Nro. operación", libro, CELDA_1);
						resumen.getRow(linea).createCell(CELDA_4);
						setCelda(resumen.getRow(linea).getCell(CELDA_4), "Cantidad nominales", libro, CELDA_1);

						for (ResumenCuentaInversionesView resumenCustodiaCef : resumenCustodiaCEF.getList()) {
							linea++;
							resumen.createRow(linea);
							resumen.getRow(linea).createCell(CELDA_1);
							setCelda(resumen.getRow(linea).getCell(CELDA_1),
									formatearFechaTitulos(resumenCustodiaCef.getFecha()), libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_2);
							setCelda(resumen.getRow(linea).getCell(CELDA_2), resumenCustodiaCef.getConcepto(), libro,
									CELDA_5);
							resumen.getRow(linea).createCell(CELDA_3);
							setCelda(resumen.getRow(linea).getCell(CELDA_3),
									resumenCustodiaCef.getComprobante() == null
											|| resumenCustodiaCef.getComprobante().isEmpty() ? VACIO
													: resumenCustodiaCef.getComprobante(),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_4);
							setCelda(resumen.getRow(linea).getCell(CELDA_4),
									resumenCustodiaCef.getCantidad() == null
											|| Double.parseDouble(resumenCustodiaCef.getCantidad()) == 0 ? VACIO
													: ISBANStringUtils.formatearConComaYVariosDecimales2(
															resumenCustodiaCef.getCantidad(), 2),
									libro, CELDA_5);
							resumen.getRow(linea).createCell(CELDA_5);
						}
					}
				}
			}

		
		linea += 2;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"La información expuesta en el presente resumen respecto de acciones, bonos y cedears es provisoria hasta que AFIP publique la valuación correspondiente para el último día de " + anioConsulta + ".",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "El informe es solamente a efecto informativo y no debe ser considerado como declaración jurada. Debe consultar con su abogado o contador con respecto a la confección de la declaración jurada.", libro,
				CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1), "Los depósitos en pesos y en moneda extranjera cuentan con la garantía de hasta $ 1.500.000. En las operaciones a nombre de dos o más personas, la garantía se prorrateará entre sus titulares.", libro,
				CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"En ningún caso, el total de la garantía por persona y por depósito podrá exceder de $ 1.500.000, cualquiera sea el número de cuentas y/o depósitos. Ley 24.485, Decreto Nº 540/95 y modificatorios y Com. \"A\" 2337 y sus modificatorias y complementarias.",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"Se encuentran excluidos los captados a tasas superiores a la de referencia conforme a los límites establecidos por el Banco Central, los adquiridos por endoso y los efectuados por personas vinculadas a la entidad financiera.",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"Las inversiones en cuotapartes de fondos no constituyen depósitos en Banco Santander Río S.A a los fines de la ley de entidades financieras ni cuentan con ninguna de las garantías que tales depósitos a la vista o a plazo",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"puedan gozar de acuerdo a la legislación y reglamentación aplicables en materia de depósitos en entidades financieras. Asimismo, Banco Santander Río S.A se encuentra impedido por normas del Banco Central de la República Argentina a asumir tácita",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"o expresamente compromiso alguno en cuanto al mantenimiento en cualquier momento del valor del capital inicial invertido, al rendimiento, al valor de rescate de las cuotapartes o del otorgamiento de la liquidez a tal fin.",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"Las inversiones en Fondos Comunes de Inversión pueden importar riesgos, incluyendo la posible pérdida de la inversión. Los rendimientos pasados no son indicativos de rendimientos futuros. Nada de lo manifestado podrá",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"ser tomado como una recomendación de compra o asesoramiento para invertir. Se aconseja a los potenciales inversores efectuar un análisis e investigación independiente antes de tomar cualquier decisión de inversión.",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"Se advierte que la operatoria con valores implica riesgos, que el valor de las inversiones puede subir o bajar, circunstancia que el inversor debe asumir, incluso a riesgo de no recuperar el importe invertido,",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"en parte o en su totalidad. Asimismo se advierte que el anuncio de rentabilidades pasadas, no constituye promesa o garantía de rentabilidades futuras y que la diversificación no garantiza un retorno sobre su inversión.",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"El precio de las acciones puede subir o bajar por motivos originados en el Mercado o en la condición financiera de la compañía, a veces de forma rápida e impredecible.",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"Las operaciones bursátiles son cursadas a través del Banco Santander Río S.A. en su carácter de Agente de Liquidación y Compensación N°72 inscripto ante la Comisión Nacional de Valores ",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"Se debe verificar los datos finales según los comprobantes entregados al momento que efectuó la operación.",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"Los accionistas de Banco Santander Rio S.A. limitan su responsabilidad a la integración de las acciones suscriptas.",
				libro, CELDA_5);
		linea++;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				"Están a disposición los comprobantes de respaldo de las operaciones.",
				libro, CELDA_5);
		
		if(anioConsulta != ANIO_PL) {
		
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1),
					"La información sobre la tenencia de los fondos comunes de inversión administrados por Investis Asset Management S.A.S.G.F.C.I y Delta Asset Management S.A. al 31/12/" + anioConsulta + " se encuentra disponible en el resumen trimestral de fondos que la sociedad ",
					libro, CELDA_5);
			linea++;
			resumen.createRow(linea);
			resumen.getRow(linea).createCell(CELDA_1);
			setCelda(resumen.getRow(linea).getCell(CELDA_1),
					"depositaria, Banco de Valores S.A., ponga a disposición de los cuotapartistas de acuerdo a sus obligaciones.",
					libro, CELDA_5);
			}
		
		}
		linea += 3;
		resumen.createRow(linea);
		resumen.getRow(linea).createCell(CELDA_1);
		setCelda(resumen.getRow(linea).getCell(CELDA_1),
				obtenerLegalesDetalleInversiones(tipoCliente, tenenciasLegalView), libro, CELDA_6);
		linea += 2;
		resumen.createRow(linea);
		for (int i = 0; i < 9; i++) {
			resumen.getRow(linea).createCell(i);
			setCelda(resumen.getRow(linea).getCell(i), " ", libro, 10);
		}
		resumen.autoSizeColumn(3);
		resumen.autoSizeColumn(4);
		resumen.autoSizeColumn(5);
		resumen.autoSizeColumn(6);
	}

	private List<TenenciasTitulosExcel> armarListaTitulos(List<ResumenCuentaInversionesView> resumenCuentaInversiones,
			List<CustodiaResumenView> custodias) {
		List<TenenciasTitulosExcel> tenenciaList = new ArrayList<TenenciasTitulosExcel>();
		Map<String, List<ResumenCuentaInversionesView>> mapMovimientosCuenta = new HashMap<String, List<ResumenCuentaInversionesView>>();
		Map<String, String> mapNombreTitulo = new HashMap<String, String>();
		for (CustodiaResumenView custodia : custodias) {
			for (CustodiaView cus : custodia.getCustodias()) {
				if (mapNombreTitulo.get(cus.getEspeCod()) == null) {
					mapNombreTitulo.put(cus.getEspeCod(), cus.getEspeNom());
				}
			}
		}
		for (ResumenCuentaInversionesView titulo : resumenCuentaInversiones) {
			if (mapMovimientosCuenta.get(titulo.getCuenta()) == null) {
				mapMovimientosCuenta.put(titulo.getCuenta(), new ArrayList<ResumenCuentaInversionesView>());
			}
			mapMovimientosCuenta.get(titulo.getCuenta()).add(titulo);
		}
		for (Map.Entry<String, List<ResumenCuentaInversionesView>> entry : mapMovimientosCuenta.entrySet()) {
			Map<String, List<ResumenCuentaInversionesView>> mapMovimientosCuentaEspecie = new HashMap<String, List<ResumenCuentaInversionesView>>();
			for (ResumenCuentaInversionesView res : entry.getValue()) {
				if (mapMovimientosCuentaEspecie.get(res.getEspeCod()) == null) {
					mapMovimientosCuentaEspecie.put(res.getEspeCod(), new ArrayList<ResumenCuentaInversionesView>());
				}
				mapMovimientosCuentaEspecie.get(res.getEspeCod()).add(res);
			}
			List<TitulosResumenImpositivoExcel> titulos = new ArrayList<TitulosResumenImpositivoExcel>();
			for (Map.Entry<String, List<ResumenCuentaInversionesView>> entry2 : mapMovimientosCuentaEspecie
					.entrySet()) {
				TitulosResumenImpositivoExcel tit = new TitulosResumenImpositivoExcel();
				tit.setNombre(mapNombreTitulo.get(entry2.getKey()));
				tit.setList(entry2.getValue());
				titulos.add(tit);
			}

			TenenciasTitulosExcel ten = new TenenciasTitulosExcel();
			ten.setCuenta(entry.getKey());
			ten.setTitulos(titulos);
			tenenciaList.add(ten);
		}

		return tenenciaList;
	}

	private String formatearFechaTitulos(String fecha) {
		String fcParseada;
		SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fechaConstitucion = fechaFormat.parse(fecha);
			fcParseada = ISBANStringUtils.formatearFecha(fechaConstitucion);
		} catch (ParseException e) {
			fcParseada = fecha;
		}
		return fcParseada;
	}

	private String convFecha(String anio, String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/yyyy");
		Date fechaHasta = null;
		Date fecFinAnio;
		String fechaOut = null;
		try {
			if (anio == null) {
				Date vFeHastal = sdf.parse(fecha);
				fechaOut = sdf1.format(vFeHastal);
			} else {
				fecFinAnio = sdf.parse(anio + "-12-31");
				Date vFeHastal = sdf.parse(fecha);
				fechaHasta = (vFeHastal.after(fecFinAnio) ? fecFinAnio : vFeHastal);
				fechaOut = sdf1.format(fechaHasta);
			}
		} catch (ParseException e) {
			LOGGER.error("Error parse Fecha reporteExcel");
			e.printStackTrace();
		}
		return fechaOut;
	}

	private String obtenerCtaOri(String suc, String cta) {
		String nroCtaOri;
		if (suc != null && cta != null) {
			nroCtaOri = TenenciasHelper.nroCuentaCanonico(suc, cta);
		} else {
			nroCtaOri = "";
		}
		return nroCtaOri;
	}

	/**
	 * Obtener legales detalle inversiones.
	 *
	 * @param tipoCliente        the tipo cliente
	 * @param tenenciasLegalView the tenencias legal view
	 * @return the string
	 */
	private String obtenerLegalesDetalleInversiones(String tipoCliente, TenenciasLegalView tenenciasLegalView) {
		StringBuilder stringBuilder = new StringBuilder();
		if (SOLAPADO.equals(tipoCliente)) {
			for (String i : tenenciasLegalView.getLegalesDetalleSolapadoInversiones()) {
				stringBuilder.append(i);
				stringBuilder.append(" ");
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * Formatear valor con punto como decimal.
	 *
	 * @param valor the valor
	 * @return the string
	 */
	private String formatearValorConPuntoComoDecimal(String valor) {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("#,###,###,##0.00", otherSymbols);
		return df.format(new BigDecimal(valor.replace(",", "")));
	}

	/**
	 * Obtener importe decimal formateador.
	 *
	 * @param cotizacion the cotizacion
	 * @return the string
	 */
	private String obtenerImporteDecimalFormateador(String cotizacion) {
		String retorno = "";
		retorno = obtenerFormatoDecimal(cotizacion).toString();
		retorno = retorno.replace(".", ",");
		return retorno;
	}

	/**
	 * Obtener info fondos.
	 *
	 * @param fondos      the fondos
	 * @param listaFondos the lista fondos
	 * @return the map
	 */
	private Map<String, ResumenFondoDetalleEntity> obtenerInfoFondos(List<FondoResumenView> fondos,
			List<ResumenCuentaInversionesView> listaFondos) {
		Map<String, ResumenFondoDetalleEntity> infoFondos = new HashMap<String, ResumenFondoDetalleEntity>();
		for (FondoResumenView fondoResumenView : fondos) {
			ResumenFondoDetalleEntity resumenFondo = new ResumenFondoDetalleEntity();
			resumenFondo.setCuenta(fondoResumenView.getCuenta());
			resumenFondo.setAnio(fondoResumenView.getAnio());
			Map<String, FondoDetalleEntity> mapFondoDetalleEntity = new HashMap<String, FondoDetalleEntity>();
			for (FondoView fondo : fondoResumenView.getFondos()) {
				FondoDetalleEntity codMap = new FondoDetalleEntity();
				codMap.setCodFondo(fondo.getCodFondo());
				codMap.setDescripcion(fondo.getDescripcion());
				codMap.setDivisa(fondo.getDivisa());
				codMap.setSaldo(fondo.getSaldoCuotas());
				codMap.setDetalles(new ArrayList<ResumenCuentaInversionesView>());
				mapFondoDetalleEntity.put(fondo.getCodFondo(), codMap);
			}
			resumenFondo.setFondos(mapFondoDetalleEntity);
			infoFondos.put(fondoResumenView.getCuenta(), resumenFondo);
		}
		for (ResumenCuentaInversionesView detalle : listaFondos) {
			if (infoFondos.containsKey(detalle.getCuenta())) {
				Map<String, FondoDetalleEntity> codMap = infoFondos.get(detalle.getCuenta()).getFondos();
				if (codMap.containsKey(detalle.getEspeCod())) {
					codMap.get(detalle.getEspeCod()).getDetalles().add(detalle);
				}
			}
		}
		return infoFondos;
	}

	/**
	 * Obtiene un workbook por nombre.
	 *
	 * @param nombre the nombre
	 * @return the workbook
	 * @throws ExcelBuildException the excel build exception
	 */
	private HSSFWorkbook getWorkbook(String nombre) throws ExcelBuildException {
		FileInputStream file;
		try {
			Resource resource = resourceLoader.getResource("classpath:/excel/HSSF/" + nombre + ".xls");
			file = new FileInputStream(resource.getFile());
			return new HSSFWorkbook(file);
		} catch (FileNotFoundException e) {
			LOGGER.error("Error al obtener el excel {}", nombre, e);
			throw new ExcelBuildException(e);
		} catch (IOException e) {
			LOGGER.error("Error al obtener el excel {}", nombre, e);
			throw new ExcelBuildException(e);
		}
	}

	/**
	 * Moneda canonico.
	 *
	 * @param moneda the moneda
	 * @return the string
	 */
	public static String monedaDescripcion(String moneda) {
		if ("ARS".equals(moneda) || "ARP".equals(moneda)) {
			return "Pesos";
		} else if ("USD".equals(moneda)) {
			return "Dólares";
		} else if ("EUR".equals(moneda)) {
			return "Euros";
		} else {
			return "";
		}
	}
}

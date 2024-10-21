/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.pagos.bo.ComprobantePrestamoReporte;
import ar.com.santanderrio.obp.servicios.pagos.bo.ItemReporteDetalle;
import ar.com.santanderrio.obp.servicios.pagos.bo.ReporteComprobanteBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.ReporteComprobanteDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Class ReporteComprobanteBOImpl.
 */
@Component
public class ReporteComprobanteBOImpl implements ReporteComprobanteBO {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteComprobanteBOImpl.class);

	/** The nombre archivo. */
	private String nombreArchivo = "Detalle_de_Cuota_de_prestamo_";

	/** The nombre archivo comprobante. */
	private String nombreArchivoComprobante = "Cuota_de_prestamo_";

	/** The Constant SIN_PLAZO. */
	private static final String SIN_PLAZO = "-";

	/** The Constant SIGNO_PESO. */
	private static final String SIGNO_PESO = "$";

	/** The reporte comprobante DAO. */
	@Autowired
	private ReporteComprobanteDAO reporteComprobanteDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.bo.ReporteComprobanteBO#
	 * obtenerReporte(ar.com.santanderrio.obp.servicios.pagos.entities.
	 * PagoPendientePrestamo,
	 * ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo)
	 */
	@Override
	public Respuesta<Reporte> obtenerReporte(PagoPendientePrestamo pagoPendientePrestamo,
			ComprobantePrestamo comprobantePrestamo) throws BusinessException {
		try {
			ComprobantePrestamoReporte comprobantePrestamoReporte = buildComprobantePrestamoReporte(
					pagoPendientePrestamo, comprobantePrestamo);
			Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
			Reporte reporte = new Reporte();
			String nombre = nombreArchivoComprobante + comprobantePrestamoReporte.getCuotaPrestamo();
			if (!SIN_PLAZO.equals(comprobantePrestamoReporte.getPlazoPrestamo())) {
				reporte.setNombre(nombre + "-" + comprobantePrestamoReporte.getPlazoPrestamo() + ".pdf");
			} else {
				reporte.setNombre(nombre + ".pdf");
			}
			reporte.setBytes(reporteComprobanteDAO.buildReportComprobante(comprobantePrestamoReporte));
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			respuesta.setRespuesta(reporte);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuestaVacia(false);
			return respuesta;
		} catch (DAOException e) {
			throw new BusinessException(e);
		} catch (ImporteConvertException e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Builds the comprobante prestamo reporte.
	 *
	 * @param pagoPendientePrestamo
	 *            the pago pendiente prestamo
	 * @param comprobantePrestamo
	 *            the comprobante prestamo
	 * @return the comprobante prestamo reporte
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	private ComprobantePrestamoReporte buildComprobantePrestamoReporte(PagoPendientePrestamo pagoPendientePrestamo,
			ComprobantePrestamo comprobantePrestamo) throws ImporteConvertException {
		ComprobantePrestamoReporte comprobantePrestamoReporte = new ComprobantePrestamoReporte();

		Prestamo prestamo = pagoPendientePrestamo.getPrestamo();

		comprobantePrestamoReporte.setImportes(buildImportes(comprobantePrestamoReporte, comprobantePrestamo));
		comprobantePrestamoReporte.setTasas(buildTasas(comprobantePrestamoReporte, comprobantePrestamo));

		comprobantePrestamoReporte.setDescripcionPrestamo(pagoPendientePrestamo.getTipoPrestamo());
		comprobantePrestamoReporte
				.setValorDescripcionPrestamo(ISBANStringUtils.formatearSucursal(prestamo.getCuenta().getNroSucursal())
						+ "-" + ISBANStringUtils.agregarBarraNumeroPrestamo(
								ISBANStringUtils.formateadorConCerosIzq(prestamo.getNumeroCuentaProducto(), 12)));

		Interviniente interviniente = comprobantePrestamo.getInterviniente();
		comprobantePrestamoReporte.setTitular(StringUtils.capitalize(StringUtils.lowerCase(interviniente.getNombre()))
				+ " " + StringUtils.capitalize(StringUtils.lowerCase(interviniente.getApellido())));
		comprobantePrestamoReporte.setCuil(ISBANStringUtils.formatearCuil(interviniente.getCuitcuil()));
		comprobantePrestamoReporte.setCondicionIVA(
				StringUtils.capitalize(StringUtils.lowerCase(comprobantePrestamo.getDescripcionIVA())));
		comprobantePrestamoReporte
				.setCuotaPrestamo(String.valueOf(Long.parseLong(pagoPendientePrestamo.getNumeroCuotas())));
		if (pagoPendientePrestamo.getPreFormalizacion() != null) {
			comprobantePrestamoReporte.setPlazoPrestamo(
					String.valueOf(Long.parseLong(pagoPendientePrestamo.getPreFormalizacion().getPlazo())));
		} else {
			comprobantePrestamoReporte.setPlazoPrestamo(SIN_PLAZO);
		}
		comprobantePrestamoReporte.setImporteCuota(
				SIGNO_PESO + ISBANStringUtils.formatearSaldoConSigno(pagoPendientePrestamo.getImporte()));
		comprobantePrestamoReporte.setImporteCuotaPrincipal(
				SIGNO_PESO + ISBANStringUtils.formatearSaldoConSigno(pagoPendientePrestamo.getImporte()));
		Cuenta cuenta = comprobantePrestamo.getCuenta();
		comprobantePrestamoReporte.setCuentaDebito(ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()) + "-"
				+ ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto()));
		comprobantePrestamoReporte.setTipoCuenta(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcion());
		comprobantePrestamoReporte
				.setFechaVencimiento(ISBANStringUtils.formatearFecha(pagoPendientePrestamo.getVencimiento()));
		comprobantePrestamoReporte.setTipoPrestamo(pagoPendientePrestamo.getPrestamo().getTipoPrestamoEnum());
		comprobantePrestamoReporte.setNroComprobante(comprobantePrestamo.getNio());
		comprobantePrestamoReporte.setAliasCuentaDebito(cuenta.getAlias());

		return comprobantePrestamoReporte;
	}

	/**
	 * Checks if is zero.
	 *
	 * @param value
	 *            the value
	 * @param cantEnteros
	 *            the cant enteros
	 * @param cantDecimales
	 *            the cant decimales
	 * @param tieneSigno
	 *            the tiene signo
	 * @return true, if is zero
	 */
	private boolean isZero(BigDecimal value, int cantEnteros, int cantDecimales, boolean tieneSigno) {
		Boolean isZero = true;
		BigDecimal newImporte = null;
		if (value != null) {
			if (StringUtils.countMatches(value.toString(), ".") > 0
					|| StringUtils.countMatches(value.toString(), ",") > 0) {
				newImporte = value;
			} else {
				newImporte = ISBANStringUtils.stringToBigDecimal(value.toString(), cantEnteros, cantDecimales,
						tieneSigno);
			}
			if (newImporte != null && newImporte.compareTo(BigDecimal.ZERO) != 0) {
				isZero = false;
			}
		}
		return isZero;
	}

	/**
	 * Checks if is valor minimo seguros.
	 *
	 * @param value
	 *            the value
	 * @param cantEnteros
	 *            the cant enteros
	 * @param cantDecimales
	 *            the cant decimales
	 * @param tieneSigno
	 *            the tiene signo
	 * @return true, if is valor minimo seguros
	 */
	private boolean isValorMinimoSeguros(BigDecimal value, int cantEnteros, int cantDecimales, boolean tieneSigno) {
		BigDecimal valorMinimo = new BigDecimal("0.009");
		Boolean isMenosValorMinimo = true;
		if (value != null) {
			if (valorMinimo.compareTo(value) <= 0) {
				isMenosValorMinimo = false;
			}
		}
		return isMenosValorMinimo;
	}

	/**
	 * Builds the tasas.
	 *
	 * @param comprobantePrestamoReporte
	 *            the comprobante prestamo reporte
	 * @param comprobantePrestamo
	 *            the comprobante prestamo
	 * @return the list
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	private List<ItemReporteDetalle> buildTasas(ComprobantePrestamoReporte comprobantePrestamoReporte,
			ComprobantePrestamo comprobantePrestamo) throws ImporteConvertException {

		List<ItemReporteDetalle> tasas = new ArrayList<ItemReporteDetalle>();
		if (comprobantePrestamo.getTasaPrestamo() != null
				&& !isZero(comprobantePrestamo.getTasaPrestamo(), 13, 4, false)) {
			tasas.add(getImporteDetalle("Tasa Nominal Anual (T.N.A)", comprobantePrestamo.getTasaPrestamo()));
		}
		if (comprobantePrestamo.getTea() != null && !isZero(comprobantePrestamo.getTea(), 13, 4, false)) {
			tasas.add(getImporteDetalle("Tasa Efectiva Anual (T.E.A)", comprobantePrestamo.getTea()));
		}
		if (comprobantePrestamo.getCftna() != null && !isZero(comprobantePrestamo.getCftna(), 13, 4, false)) {
			tasas.add(getImporteDetalle("Costo Financiero Total Efectivo Anual", comprobantePrestamo.getCftna()));
		}
		if (comprobantePrestamo.getCftnaSinImp() != null
				&& !isZero(comprobantePrestamo.getCftnaSinImp(), 13, 4, false)) {
			tasas.add(getImporteDetalle("Costo Financiero Total Efectivo Anual (Sin Impuestos)",
					comprobantePrestamo.getCftnaSinImp()));
		}
		return tasas;
	}

	/**
	 * Gets the importe detalle.
	 *
	 * @param label
	 *            the label
	 * @param importe
	 *            the importe
	 * @return the importe detalle
	 */
	private ItemReporteDetalle getImporteDetalle(String label, BigDecimal importe) {
		ItemReporteDetalle itemReporteDetalle = new ItemReporteDetalle();
		itemReporteDetalle.setLabel(label);
		itemReporteDetalle.setValue(SIGNO_PESO + ISBANStringUtils.formatearSaldoConSigno(importe));
		return itemReporteDetalle;
	}

	/**
	 * Builds the importes.
	 *
	 * @param comprobantePrestamoReporte
	 *            the comprobante prestamo reporte
	 * @param comprobantePrestamo
	 *            the comprobante prestamo
	 * @return the list
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	private List<ItemReporteDetalle> buildImportes(ComprobantePrestamoReporte comprobantePrestamoReporte,
			ComprobantePrestamo comprobantePrestamo) throws ImporteConvertException {
		List<ItemReporteDetalle> importes = new ArrayList<ItemReporteDetalle>();
		addToListaImporte1(comprobantePrestamo, comprobantePrestamoReporte, importes);
		addToListaImporte2(comprobantePrestamo, comprobantePrestamoReporte, importes);
		addToListaImporte3(comprobantePrestamo, comprobantePrestamoReporte, importes);

		if (comprobantePrestamo.getOtrosImpuestos() != null
				&& !isZero(comprobantePrestamo.getOtrosImpuestos(), 13, 4, false)) {
			importes.add(getImporteDetalle("Otros Impuestos", comprobantePrestamo.getOtrosImpuestos()));
		}
		if (comprobantePrestamo.getGastos() != null && !isZero(comprobantePrestamo.getGastos(), 13, 4, false)) {
			importes.add(getImporteDetalle("Gastos Anexos", comprobantePrestamo.getGastos()));
		}
		if (comprobantePrestamo.getComisiones() != null && !isZero(comprobantePrestamo.getComisiones(), 13, 4, false)) {
			importes.add(getImporteDetalle("Comisiones", comprobantePrestamo.getComisiones()));
		}
		return importes;
	}

	/**
	 * Adds the to lista importe 1.
	 *
	 * @param comprobantePrestamo
	 *            the comprobante prestamo
	 * @param comprobantePrestamoReporte
	 *            the comprobante prestamo reporte
	 * @param importes
	 *            the importes
	 */
	private void addToListaImporte1(ComprobantePrestamo comprobantePrestamo,
			ComprobantePrestamoReporte comprobantePrestamoReporte, List<ItemReporteDetalle> importes) {
		if (comprobantePrestamo.getImporteAmortizacion() != null
				&& !isZero(comprobantePrestamo.getImporteAmortizacion(), 13, 4, false)) {
			importes.add(getImporteDetalle("Capital", comprobantePrestamo.getImporteAmortizacion()));
		}
		if (comprobantePrestamo.getImporteIntereses() != null
				&& !isZero(comprobantePrestamo.getImporteIntereses(), 13, 4, false)) {
			importes.add(getImporteDetalle("Intereses compensatorios del periodo",
					comprobantePrestamo.getImporteIntereses()));
		}
		if (comprobantePrestamo.getImpAjusCapmor() != null
				&& !isZero(comprobantePrestamo.getImpAjusCapmor(), 13, 4, false)) {
			importes.add(getImporteDetalle("Ajuste de Capital en Mora", comprobantePrestamo.getImpAjusCapmor()));
		}
		if (comprobantePrestamo.getImportePunitorios() != null
				&& !isZero(comprobantePrestamo.getImportePunitorios(), 13, 4, false)) {
			importes.add(getImporteDetalle("Intereses Punitorios", comprobantePrestamo.getImportePunitorios()));
		}
	}

	/**
	 * Adds the to lista importe 2.
	 *
	 * @param comprobantePrestamo
	 *            the comprobante prestamo
	 * @param comprobantePrestamoReporte
	 *            the comprobante prestamo reporte
	 * @param importes
	 *            the importes
	 */
	private void addToListaImporte2(ComprobantePrestamo comprobantePrestamo,
			ComprobantePrestamoReporte comprobantePrestamoReporte, List<ItemReporteDetalle> importes) {
		if (comprobantePrestamo.getImporteComplementario() != null
				&& !isZero(comprobantePrestamo.getImporteComplementario(), 13, 4, false)) {
			importes.add(getImporteDetalle("Intereses compensatorios posteriores al vto.",
					comprobantePrestamo.getImporteComplementario()));
		}

		if (comprobantePrestamo.getImporteSeguroVida() != null
				&& !isZero(comprobantePrestamo.getImporteSeguroVida(), 13, 4, false)) {
			importes.add(getImporteDetalle("Cargo por Seguro de Vida", comprobantePrestamo.getImporteSeguroVida()));
		}
		if (comprobantePrestamo.getImporteSeguroBien() != null
				&& !isZero(comprobantePrestamo.getImporteSeguroBien(), 13, 4, false)) {
			importes.add(getImporteDetalle("Seguro del Bien", comprobantePrestamo.getImporteSeguroBien()));
		}
		// Aca valido los otros seguros
		if (comprobantePrestamo.getImporteTotalSeguros() != null
				&& !isZero(comprobantePrestamo.getImporteTotalSeguros(), 13, 4, false)) {
			BigDecimal resultado = comprobantePrestamo.getImporteTotalSeguros()
					.add(comprobantePrestamo.getImporteTotalSeguros());
			if (isValorMinimoSeguros(resultado, 13, 4, false)) {
				importes.add(getImporteDetalle("Otros seguros", comprobantePrestamo.getImporteTotalSeguros()));
			}
		}

	}

	/**
	 * Adds the to lista importe 3.
	 *
	 * @param comprobantePrestamo
	 *            the comprobante prestamo
	 * @param comprobantePrestamoReporte
	 *            the comprobante prestamo reporte
	 * @param importes
	 *            the importes
	 */
	private void addToListaImporte3(ComprobantePrestamo comprobantePrestamo,
			ComprobantePrestamoReporte comprobantePrestamoReporte, List<ItemReporteDetalle> importes) {
		if (comprobantePrestamo.getImporteIVA() != null && !isZero(comprobantePrestamo.getImporteIVA(), 13, 4, false)) {
			importes.add(getImporteDetalle("I.V.A. Tasa General", comprobantePrestamo.getImporteIVA()));
		}
		if (comprobantePrestamo.getImporteIVAAdicional() != null
				&& !isZero(comprobantePrestamo.getImporteIVAAdicional(), 13, 4, false)) {
			importes.add(getImporteDetalle("I.V.A. Adicional", comprobantePrestamo.getImporteIVAAdicional()));
		}
		if (comprobantePrestamo.getImpuestoEndFinal() != null
				&& !isZero(comprobantePrestamo.getImpuestoEndFinal(), 13, 4, false)) {
			importes.add(getImporteDetalle("Impuesto al Endeudamiento", comprobantePrestamo.getImpuestoEndFinal()));
		}
		if (comprobantePrestamo.getIngresosBrutos() != null
				&& !isZero(comprobantePrestamo.getIngresosBrutos(), 13, 4, false)) {
			importes.add(getImporteDetalle("Percepcion Ingresos Brutos", comprobantePrestamo.getIngresosBrutos()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.bo.ReporteComprobanteBO#
	 * obtenerReporteDetallePrestamo(ar.com.santanderrio.obp.servicios.pagos.bo.
	 * ComprobantePrestamoReporte)
	 */
	@Override
	public Respuesta<Reporte> obtenerReporteDetallePrestamo(ComprobantePrestamoReporte comprobantePrestamoReporte)
			throws BusinessException {
		try {
			Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
			Reporte reporte = new Reporte();
			reporte.setNombre(nombreArchivo + comprobantePrestamoReporte.getCuotaPrestamo() + "-"
					+ comprobantePrestamoReporte.getPlazoPrestamo() + ".pdf");
			reporte.setBytes(reporteComprobanteDAO.buildReportComprobanteDetalle(comprobantePrestamoReporte));
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			respuesta.setRespuesta(reporte);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuestaVacia(false);
			return respuesta;
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

}

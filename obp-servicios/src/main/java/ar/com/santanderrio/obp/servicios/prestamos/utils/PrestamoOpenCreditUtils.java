/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class PrestamoOpenCreditUtils.
 */
public final class PrestamoOpenCreditUtils {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoOpenCreditUtils.class);

	/** Formato porcentaje. */
	private static final String SIMBOLO_PORCENTAJE = "%";

	/**
	 * Instantiates a new prestamo open credit utils.
	 */
	private PrestamoOpenCreditUtils() {

	}

	/**
	 * Formato de valor con simbolo de moneda.
	 *
	 * @param valor
	 *            the valor
	 * @param moneda
	 *            the moneda
	 * @return the string
	 */
	public static String formatearValorMoneda(BigDecimal valor, String moneda) {
		return moneda + " " + ISBANStringUtils.formatearSaldo(valor);
	}

	/**
	 * Formato de valor con simbolo de moneda.
	 *
	 * @param valor
	 *            the valor
	 * @return the string
	 */
	public static String formatearPorcentaje(BigDecimal valor) {
		BigDecimal abs = valor == null ? BigDecimal.ZERO : valor.abs();
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.getDefault());
		simbolos.setDecimalSeparator(',');
		simbolos.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("#,###0.000", simbolos);
		return df.format(abs) + " " + SIMBOLO_PORCENTAJE;
	}

	/**
	 * Cambia el formato de fecha.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 */
	public static String formatearFecha(String fecha) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String finalFormat = "";
		try {
			date = formatter.parse(fecha);
			SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
			finalFormat = newFormat.format(date);
		} catch (ParseException e) {
			LOGGER.error("Error al formatear la fecha , se devuelve formato vacio.");
			return finalFormat;
		}
		return finalFormat;
	}

}

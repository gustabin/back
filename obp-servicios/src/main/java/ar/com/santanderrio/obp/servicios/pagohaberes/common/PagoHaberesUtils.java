/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.common;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class PagoHaberesUtils.
 */
public final class PagoHaberesUtils {

	/** The Constant GUION_STRING. */
	public static final String GUION_STRING = "-";

	/** The Constant GUION_STRING. */
	public static final String GUION_BARRRA = "/";

	/** The Constant GUION_STRING. */
	public static final String CONCEPTO = "Sueldo";

	/** The Constant GUION_STRING. */
	public static final String BLANK = " ";

	/**
	 * Instantiates a new pago haberes utils.
	 */
	private PagoHaberesUtils() {

	}

	/**
	 * Obtiene tipo documento.
	 *
	 * @param tipoDocumento
	 *            the tipo documento
	 * @return the string
	 */
	public static String obtieneTipoDocumento(String tipoDocumento) {
		return ("T").equals(tipoDocumento) ? "CUIT" : "CUIL";
	}

	/**
	 * Obtiene tipo pago.
	 *
	 * @param tipoPago
	 *            the tipo pago
	 * @return the string
	 */
	public static String obtieneTipoPago(String tipoPago) {
		return ("S").equals(tipoPago) ? "Sueldo" : "Honorario";
	}

	/**
	 * Obtiene tipo agendamiento.
	 *
	 * @param tipoAgendamiento
	 *            the tipo agendamiento
	 * @return the string
	 */
	public static String obtieneTipoAgendamiento(String tipoAgendamiento) {
		return ("E").equals(tipoAgendamiento) ? "Pago Programado" : "-";
	}

	/**
	 * Obtiene nro cuenta credito.
	 *
	 * @param nroCta
	 *            the nro cta
	 * @return the string
	 */
	// 000-402621/1
	public static String obtieneNroCuentaCredito(String nroCta) {
		String nroCtaFormateado = "";
		if (nroCta != null) {
			nroCtaFormateado = formateadorNroCta(ISBANStringUtils.formateadorConCerosIzq(nroCta, 3));
		}
		return nroCtaFormateado;
	}

	/**
	 * Formateador nro cta.
	 *
	 * @param nroCta
	 *            the nro cta
	 * @return the string
	 */
	public static String formateadorNroCta(String nroCta) {
		String nroCtaFormateada = "";
		if (nroCta != null) {
			StringBuilder sb = new StringBuilder();
			nroCtaFormateada = sb.append(StringUtils.substring(nroCta, 0, 2)).append(GUION_STRING)
					.append(StringUtils.substring(nroCta, 2, 8)).append(GUION_BARRRA).toString();
		}
		return nroCtaFormateada;
	}

	/**
	 * Formateador importe.
	 *
	 * @param importe
	 *            the importe
	 * @return the string
	 */
	public static String formateadorImporte(BigDecimal importe) {
		return "$" + BLANK + ISBANStringUtils.formatearSaldo(importe);
	}

}

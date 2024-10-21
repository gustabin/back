/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.utils;

/**
 * ExtraccionYComprasExteriorUtils.
 *
 * @author Silvina_Luque
 */
public final class ExtraccionYComprasExteriorUtils {

	/** The Constant CTA_ACAD. */
	private static final String CTA_ACAD = "ACAD";

	/** The Constant CTA_ACAH. */
	private static final String CTA_ACAH = "ACAH";

	/** The Constant CTA_ACCD. */
	private static final String CTA_ACCD = "ACCD";

	/** The Constant CTA_ACTE. */
	private static final String CTA_ACTE = "ACTE";

	/** The Constant DESC_CUENTA_DOLARES. */
	private static final String DESC_CUENTA_DOLARES = "Cuenta en Dólares";

	/** The Constant DESC_CUENTA_PESOS. */
	private static final String DESC_CUENTA_PESOS = "Cuenta en Pesos";

	/** The Constant OFF_APLICACION_INI. */
	public static final int OFF_APLICACION_INI = 0;

	/** The Constant OFF_APLICACION_END. */
	public static final int OFF_APLICACION_END = 4;

	/** The Constant OFF_NRO_INI. */
	public static final int OFF_NRO_INI = 5;

	/** The Constant OFF_NROCTA_INI. */
	public static final int OFF_NROCTA_INI = 8;

	/** The Constant OFF_NROCTA_END. */
	public static final int OFF_NROCTA_END = 20;

	/** The Constant OFF_SUC_INI. */
	public static final int OFF_SUC_INI = 1;

	/** The Constant OFF_SUCURSAL_INI. */
	public static final int OFF_SUCURSAL_INI = 4;

	/** The Constant OFF_SUCURSAL_END. */
	public static final int OFF_SUCURSAL_END = 8;

	/**
	 * Instantiates a new extraccion Y compras exterior utils.
	 */
	private ExtraccionYComprasExteriorUtils() {

	}

	/**
	 * Gets the moneda cta.
	 *
	 * @param ctaExt
	 *            the cta ext
	 * @return the moneda cta
	 */
	public static String getMonedaCta(String ctaExt) {
		String aplicacion = ctaExt.substring(OFF_APLICACION_INI, OFF_APLICACION_END);
		String moneda = "";
		if (CTA_ACAH.equals(aplicacion) || CTA_ACTE.equals(aplicacion)) {
			moneda = "$";
		} else if (CTA_ACAD.equals(aplicacion) || CTA_ACCD.equals(aplicacion)) {
			moneda = "u$s";
		}
		return moneda;
	}

	/**
	 * Gets the desc cta.
	 *
	 * @param moneda
	 *            the moneda
	 * @return the desc cta
	 */
	public static String getDescCta(String moneda) {
		String desc = "";
		if ("$".equals(moneda)) {
			desc = DESC_CUENTA_PESOS;
		} else if ("u$s".equals(moneda)) {
			desc = DESC_CUENTA_DOLARES;
		}
		return desc;
	}

	/**
	 * Gets the nro cta.
	 *
	 * @param ctaExt
	 *            the cta ext
	 * @return the nro cta
	 */
	public static String getNroCta(String ctaExt) {
		String suc = ctaExt.substring(OFF_SUCURSAL_INI, OFF_SUCURSAL_END);
		String nro = ctaExt.substring(OFF_NROCTA_INI, OFF_NROCTA_END);
		return nroCuentaCanonico(suc.substring(OFF_SUC_INI), nro.substring(OFF_NRO_INI));
	}

	/**
	 * Gets the nro cta altair.
	 *
	 * @param ctaExt
	 *            the cta ext
	 * @return the nro cta altair
	 */
	public static String getNroCtaAltair(String ctaExt) {
		return ctaExt.substring(OFF_SUCURSAL_INI, OFF_NROCTA_END);
	}

	/**
	 * Nro cuenta canonico.
	 *
	 * @param sucursal
	 *            the sucursal
	 * @param numero
	 *            the numero
	 * @return the string
	 */
	public static String nroCuentaCanonico(String sucursal, String numero) {
		String suc = llenarConCerosIzqOTruncar(sucursal, 3);
		String nro = llenarConCerosIzqOTruncar(numero, 7);
		return suc + "-" + nro.substring(0, 6) + "/" + nro.substring(6, 7);
	}

	/**
	 * Llenar con ceros izq O truncar.
	 *
	 * @param s
	 *            the s
	 * @param size
	 *            the size
	 * @return the string
	 */
	public static String llenarConCerosIzqOTruncar(String s, int size) {
		int l = s.length();
		if (l >= size) {
			return s.substring(l - size, l);
		}
		StringBuilder ceros = new StringBuilder();
		for (int n = 0; n < size - l; ++n) {
			ceros.append("0");
		}

		return ceros + s;
	}

	/**
	 * Crear mascara nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @return the string
	 */
	public static String crearMascaraNroTarjeta(String nroTarjeta) {
		int nroLen = nroTarjeta.trim().length();
		return "XXXX-" + nroTarjeta.substring(nroLen - 4, nroLen);

	}

}

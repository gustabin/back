/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

/**
 * The Enum ComprobantesBOEnum.
 *
 * @author sabrina.cis
 */
public enum ComprobantesBOEnum {

	/** The obtener detalle. */
	OBTENER_COMPROBANTE("Obteniendo Comprobantes Scomp", ""),
	/** The obtener detalle. */
	OBTENER_DETALLE("Obteniendo detalle de comprobante", ""),
	/** The error calculo informes. */
	ERROR_CALCULO_INFORMES("Error en calcular los informes de debitos automaticos", ""),
	/** The error validacion. */
	ERROR_VALIDACION("No se pudo validar el detalle solicitado en el servicio PESPAGE", ""),
	/** The logger error visa. */
	LOGGER_ERROR_VISA("Error recuperando productos Visa...", ""),
	/** The logger error parceador. */
	LOGGER_ERROR_PARCEADOR("Error procesando respuesta de Visa...", ""),
	/** The error en finalizacion thread. */
	ERROR_EN_FINALIZACION_THREAD("Error en la espera de la finalizacion de los threads:", ""),
	/** The medio de pago detalle. */
	MEDIO_DE_PAGO_DETALLE("Tarjeta", ""),
	/** The error detalle comprobantes. */
	ERROR_DETALLE_COMPROBANTES("1551", "errorDetalle"),
	/** The pmc detalle codigo retorno extendido. */
	PMC_DETALLE_CODIGO_RETORNO_EXTENDIDO("00000000", ""),
	/** The pmc detalle codigo retorno extendido. */
	PMC_DETALLE_CODIGO_RETORNO_SIN_COMPROBAR_DEUDA("10000065", ""),
	/** The cero. */
	CERO("0", ""),
	/** The uno. */
	UNO("1", ""),
	/** The dos. */
	DOS("2", ""),
	/** The tres. */
	TRES("3", ""),
	/** The cuatro. */
	CUATRO("4", ""),
	/** The cinco. */
	CINCO("5", ""),
	/** The seis. */
	SEIS("6", ""),
	/** The siete. */
	SIETE("7", ""),
	/** The ocho. */
	OCHO("8", ""),
	/** The menos. */
	MENOS("-", ""),
	/** The r. */
	R("R", ""),
	/** The n. */
	N("N", ""),
	/** The s. */
	S("S", ""),
	/** The c. */
	C("C", ""),
	/** The m. */
	M("M", ""),
	/** The signo pesos. */
	SIGNO_PESOS("$", ""),
	/** The moneda arg. */
	MONEDA_ARG("ARS", "$ "),
	/** The moneda usd. */
	MONEDA_USD("USD", "U$D "),
	/** The anticipo cuota. */
	ANTICIPO_CUOTA(" ANTICIPO/CUOTA ", ""),
	/** The pmc. */
	PMC("PagoMisCuentas", ""),
	/** The pmc vep. */
	PMC_VEP("PagoVEP", ""),
	/** The pmc vep2. */
	PMC_VEP2("PagoVEP2", ""),
	/** The pmc afip. */
	PMC_AFIP("PagoAFIP", ""),
	/** The pmc afip2. */
	PMC_AFIP2("PagoAFIP2", ""),
	/** The pmc sin deuda. */
	PMC_SIN_DEUDA("PagoSinDeuda", ""),
	/** The pmc con deuda. */
	PMC_CON_DEUDA("PagoConDeuda", ""),
	/** The pmc autonomo. */
	PMC_AUTONOMO("PagoAutonomo", ""),
	/** The fiid suss. */
	FIID_SUSS("SUSS", ""),
	/** The error de servicio. */
	ERROR_DE_SERVICIO("Error en servicio", ""),
	/** The error reporte pdf. */
	MENSAJE_ERROR_REPORTE_PDF("mensajeError", "Ocurrió un error y no se pudo realizar la descarga. Por favor volvé a intentar.");

	/** The id. */
	private String id;

	/** The detalle. */
	private String detalle;

	/**
	 * Instantiates a new comprobantes BO enum.
	 *
	 * @param id
	 *            the id
	 * @param detalle
	 *            the detalle
	 */
	ComprobantesBOEnum(String id, String detalle) {
		this.id = id;
		this.detalle = detalle;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the detalle.
	 *
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

}

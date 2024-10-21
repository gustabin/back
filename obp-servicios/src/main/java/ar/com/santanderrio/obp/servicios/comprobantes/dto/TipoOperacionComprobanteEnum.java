/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

/**
 * Enum de tipo de comprobantes.
 */
public enum TipoOperacionComprobanteEnum {

	/** The transferencia inmediata. */
	TRANSFERENCIA_INMEDIATA(51, "Transferencia", "Transferencia"),

	/** The transferencia programada. */
	TRANSFERENCIA_PROGRAMADA(52, "Transferencia", "Transferencia"),

	/** The recarga visa. */
	RECARGA_VISA(53, "Recarga", "Recarga"),

	/** The pago puntual. */
	PAGO_PUNTUAL(54, "Pago de compras", ""),

	/** The pago sueldos. */
	PAGO_SUELDOS(55, "Pago de Sueldos", ""),

	/** The pago honorarios. */
	PAGO_HONORARIOS(56, "Pago de Honorarios", ""),

	/** The informe debitos automaticos. */
	INFORME_DEBITOS_AUTOMATICOS(55, "Débito Automático", "DebitoAutoTarjeta"),

	/** The pago tarjeta. */
	PAGO_TARJETA(56, "Pago de Tarjeta Santander", ""),

	/** The pago servicios. */
	PAGO_SERVICIOS(57, "Pago de Servicios", "PagoMisCuentas"),

	/** The pago servicios. */
	PAGO_SERVICIOS_FILTRADOS(67, "Pago de Servicios", "PagoMisCuentas"),

	/** The pago mis cuentas. */
	PAGO_MIS_CUENTAS(58, "Pago mis cuentas", "PagoMisCuentas"),

	/** The debito automatico cuenta. */
	DEBITO_AUTOMATICO_CUENTA(59, "Débito Automático en Cuenta", ""),

	/** The debito automatico cuenta rechazado. */
	DEBITO_AUTOMATICO_CUENTA_RECHAZADO(59, "Débito Automático en Cuenta Rechazado", "Débito Rechazado"),

	/** The scomp pago servicios. */
	SCOMP_PAGO_SERVICIOS(60, "Pago de Servicios", "PagoMisCuentas"),

	/** The scomp pago servicios2. */
	SCOMP_PAGO_SERVICIOS2(61, "Pago de servicios", "PagoMisCuentas"),

	/** The transferencia. */
	TRANSFERENCIA(62, "Transferencia", "Transferencia"),

	/** The comprobante pago tarjeta. */
	COMPROBANTE_PAGO_TARJETA(63, "Comprobante de Pago de Tarjetas", ""),

	/** The comprobante financiacion resumen. */
	COMPROBANTE_FINANCIACION_RESUMEN(64, "Financiación de resumen de tarjeta", ""),

	/** The compra dolares. */
	COMPRA_DOLARES(65, "Compra de dólares", ""),

	/** The venta dolares. */
	VENTA_DOLARES(66, "Venta de dólares", "");

	/** The prioridad. */
	private Integer prioridad;

	/** The etiqueta. */
	private String etiqueta;

	/** The tag. */
	private String tag;

	/**
	 * Instantiates a new tipo operacion comprobante enum.
	 *
	 * @param prioridad
	 *            the prioridad
	 * @param etiqueta
	 *            the etiqueta
	 * @param tag
	 *            the tag
	 */
	TipoOperacionComprobanteEnum(Integer prioridad, String etiqueta, String tag) {
		this.prioridad = prioridad;
		this.etiqueta = etiqueta;
		this.tag = tag;
	}

	/**
	 * Gets the prioridad.
	 *
	 * @return the prioridad
	 */
	public Integer getPrioridad() {
		return prioridad;
	}

	/**
	 * Gets the etiqueta.
	 *
	 * @return the etiqueta
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

}

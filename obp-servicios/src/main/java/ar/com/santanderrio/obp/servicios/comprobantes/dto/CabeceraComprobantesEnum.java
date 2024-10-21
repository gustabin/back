/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

/**
 * The Enum CabeceraComprobantesEnum.
 *
 * @author sabrina.cis
 */
public enum CabeceraComprobantesEnum {
	/** The transferencia inmediata. */
	COMPROBANTE_TRANSFERENCIA(51, "Comprobante de transferencia", "tituloComprobanteTransferencia"),
	/** The comprobante transferencia programada. */
	COMPROBANTE_TRANSFERENCIA_PROGRAMADA(52, "Comprobante de transferencia programada", "tituloComprobanteTransferenciaProgramada"),
	/** The comprobante transferencia programada rechazada. */
	COMPROBANTE_TRANSFERENCIA_PROGRAMADA_RECHAZADA(53, "Comprobante de transferencia programada rechazada", "tituloComprobanteTransferenciaProgramadaRechazada"),
	/** The comprobante de pago. */
	COMPROBANTE_DE_PAGO(54, "Comprobante de pago", "tituloComprobanteDePago"),
	/** The comprobante de pago rechazado. */
	COMPROBANTE_DE_PAGO_RECHAZADO(55, "Comprobante de pago rechazado", "tituloComprobanteDePagoRechazado"),
	/** The comprobante de pago santander. */
	COMPROBANTE_DE_PAGO_SANTANDER_RIO(56, "Comprobante de pago de tarjeta Santander", "tituloComprobantePagoTarjeta"),
	/** The rechazada. */
	RECHAZADA(57, "Rechazado", "tagRechazada"),
	/** The Transferencia. */
	TRANSFERENCIA(58, "Transferencia", "tagTransferencia"),
	/** The Recarga. */
	RECARGA(59, "Recarga", "recarga"),

	/** The debito automatico en cuenta. */
	DEBITO_AUTOMATICO_EN_CUENTA(58, "Comprobante de débito automático en cuenta", "comprobanteDebitoAutomaticoEnCuenta"),

	/** The debito automatico en tarjeta. */
	DEBITO_AUTOMATICO_EN_TARJETA(61, "Comprobante de débito automático en tarjeta", "DebitoAutoTarjeta"),

	/** The debito automatico en tarjeta. */
	RECARGA_TARJETA_RECARGABLE(62, "Comprobante de recarga de tarjeta recargable", "RecargaTarjeta"),

	/** The debito automatico en cuenta rechazado. */
	DEBITO_AUTOMATICO_EN_CUENTA_RECHAZADO(60, "Comprobante de débito automático en cuenta rechazado", "comprobanteDebitoAutomaticoEnCuentaRechazado"),

	/** The compra dolares. */
	COMPRA_DOLARES(61, "Comprobante de compra de dólares", "CompraVentaDolares"),

	/** The venta dolares. */
	VENTA_DOLARES(62, "Comprobante de venta de dólares", "CompraVentaDolares"),
	
	/** Consulta operacion de compra titulos. */
	CONSULTA_OPERACION_TITULO_COMPRA(63, "Comprobante de operación de compra de Títulos Valores", "consultaOperacionTituloCompra"),

	/** Consulta operacion de venta titulos. */
	CONSULTA_OPERACION_TITULO_VENTA(64, "Comprobante de operación de venta de Títulos Valores", "consultaOperacionTituloVenta"),

	/** Consulta operacion de licitacion titulos. */
	CONSULTA_OPERACION_TITULO_LICITACION(65, "Comprobante de operación de licitación", "consultaOperacionLicitacion"),
	
	/** solicitud de liquidación de orden de pago. */
	SOLICITUD_LIQUIDACION_ORDEN_PAGO(66, "Comprobante de solicitud de liquidación de la orden de pago del exterior", "solicitudOrdenPago");

	/** The id. */
	private Integer id;

	/** The detalle. */
	private String detalle;

	/** The etiqueta. */
	private String etiqueta;

	/**
	 * Instantiates a new cabecera comprobantes enum.
	 *
	 * @param id
	 *            the id
	 * @param detalle
	 *            the detalle
	 * @param etiqueta
	 *            the etiqueta
	 */
	CabeceraComprobantesEnum(Integer id, String detalle, String etiqueta) {
		this.id = id;
		this.detalle = detalle;
		this.etiqueta = etiqueta;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
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

	/**
	 * Gets the etiqueta.
	 *
	 * @return the etiqueta
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

}

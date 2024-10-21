/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;

/**
 * The Enum TransaccionEnum.
 * 
 */
public enum TransaccionEnum {

	/** The pagos de servicios. */
	PAGOS_DE_SERVICIOS(0, "Pagos de servicios", 1, EstadisticasConstants.SOLAPA_COMPROBANTES_PMC),

	/** The debito automatico. */
	DEBITO_AUTOMATICO(1, "Debito automatico", 2, EstadisticasConstants.SOLAPA_COMPROBANTES_DEBITOS_AUTOMATICOS),

	/** The transferencias. */
	TRANSFERENCIAS(2, "Transferencias", 3, EstadisticasConstants.SOLAPA_COMPROBANTES_TRANSFERENCIAS),

	/** The pago de tarjeta. */
	PAGO_DE_TARJETA(3, "Pago de tarjeta", 4, EstadisticasConstants.SOLAPA_COMPROBANTES_PAGO_DE_TARJETAS),

	/** The otros. */
	OTROS(4, "Otros", 5, EstadisticasConstants.SOLAPA_COMPROBANTES_OTROS),

	/** The recarga. */
	PAGO_HABERES_HONORARIOS(5, "Pago haberes y honorarios"),

	/** The recarga. */
	COMPRA_VENTA(6, "CompraVenta"),

	/** Pagos de servicios (no incluye pago de compras). */
	PAGO_SERVICIOS(7, "Pago de Servicios"),

	/** Pagos de servicios (incluye pago de compras). */
	PAGO_COMPRAS(8, "Pago de Compras"),

	GOTO_TRANSFERENCIAS(9, "Go To desde transferencias", null,
			EstadisticasConstants.SOLAPA_COMPROBANTES_GOTO_TRANSFERENCIAS),
	
	GOTO_PAGOSERVICIOS(10, "Go To desde pago servicios", null, EstadisticasConstants.SOLAPA_COMPROBANTES_GOTO_PAGO_DE_SERVICIOS),
	
	/** The transacciones. */
	TRANSACCIONES(99, "Transacciones", 6);
	
	/** The id. */
	private Integer id;

	/** The descripcion. */
	private String descripcion;

	/** The id config. */
	private Integer configuracionId;

	/** The codigo estadistica. */
	private String codigoEstadistica;

	public static TransaccionEnum getTransaccionByID(Integer id) {
		for (TransaccionEnum transaccion : TransaccionEnum.values()) {
			if (transaccion.getId().equals(id)) {
				return transaccion;
			}
		}
		return null;
	}

	public static TransaccionEnum getTransaccionByConfiguracion(Integer id) {
		for (TransaccionEnum transaccion : TransaccionEnum.values()) {
			if (transaccion.getConfiguracionId() != null && transaccion.getConfiguracionId().equals(id)) {
				return transaccion;
			}
		}
		return null;
	}

	/**
	 * Instantiates a new transaccion enum.
	 * 
	 * @param id
	 *            the id
	 * @param descripcion
	 *            the descripcion
	 */
	TransaccionEnum(Integer id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	
	TransaccionEnum(Integer id, String descripcion, Integer configuracionId) {
		this.id = id;
		this.descripcion = descripcion;
		this.configuracionId = configuracionId;
	}


	TransaccionEnum(Integer id, String descripcion, Integer configuracionId, String codigoEstadistica) {
		this.id = id;
		this.descripcion = descripcion;
		this.configuracionId = configuracionId;
		this.codigoEstadistica = codigoEstadistica;
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
	 * Gets the descripcion.
	 * 
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	public Integer getConfiguracionId() {
		return configuracionId;
	}

	/**
	 * @return the codigoEstadistica
	 */
	public String getCodigoEstadistica() {
		return codigoEstadistica;
	}

}

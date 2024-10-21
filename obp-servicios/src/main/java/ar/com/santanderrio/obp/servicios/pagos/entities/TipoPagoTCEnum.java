/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;

/**
 * Enumera los tipos de pago de tarjetas de creditos.
 */
public enum TipoPagoTCEnum {

	/** The minimo. */
	MINIMO("0", "00") {
		@Override
		public String getEstadisticaPago(boolean esPagoProgramado) {
			return esPagoProgramado ? EstadisticasConstants.PAGO_TARJETA_PROGRAMADO_IMPORTE_MINIMO
					: EstadisticasConstants.PAGO_TARJETA_IMPORTE_MINIMO;
		}
	},

	/** The total. */
	TOTAL("1", "01") {
		@Override
		public String getEstadisticaPago(boolean esPagoProgramado) {
			return esPagoProgramado ? EstadisticasConstants.PAGO_TARJETA_PROGRAMADO_IMPORTE_TOTAL
					: EstadisticasConstants.PAGO_TARJETA_IMPORTE_TOTAL;
		}
	},

	/** The otro. */
	OTRO("2", "02") {
		@Override
		public String getEstadisticaPago(boolean esPagoProgramado) {
			return esPagoProgramado ? EstadisticasConstants.PAGO_TARJETA_PROGRAMADO_OTRO_IMPORTE
					: EstadisticasConstants.PAGO_TARJETA_OTRO_IMPORTE;
		}
	},

	/** The saldo. */
	SALDO("3", "02") {
		@Override
		public String getEstadisticaPago(boolean esPagoProgramado) {
			return esPagoProgramado ? EstadisticasConstants.PAGO_TARJETA_PROGRAMADO_SALDO_A_PAGAR
					: EstadisticasConstants.PAGO_TARJETA_SALDO_A_PAGAR;
		}
	};

	/** The value. */
	private String value;

	/** The valor servicio. */
	private String valorServicio;

	/**
	 * Instantiates a new tipo pago TC enum.
	 *
	 * @param value
	 *            the value
	 * @param valorServicio
	 *            the valor servicio
	 */
	TipoPagoTCEnum(String value, String valorServicio) {
		this.value = value;
		this.valorServicio = valorServicio;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Gets the valor servicio.
	 *
	 * @return the valor servicio
	 */
	public String getValorServicio() {
		return valorServicio;
	}

	/**
	 * From value.
	 *
	 * @param value
	 *            the value
	 * @return the tipo pago TC enum
	 */
	public static TipoPagoTCEnum fromValue(String value) {
		for (TipoPagoTCEnum tipoPagoTC : TipoPagoTCEnum.values()) {
			if (tipoPagoTC.getValue().equals(value)) {
				return tipoPagoTC;
			}
		}
		return null;
	}

	/**
	 * Gets the estadistica pago.
	 *
	 * @param esPagoProgramado
	 *            the es pago programado
	 * @return the estadistica pago
	 */
	public abstract String getEstadisticaPago(boolean esPagoProgramado);
}

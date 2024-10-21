/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

/**
 * The Enum TipoConsumoTarjeta.
 *
 * @author federico.n.flores
 */
public enum TipoConsumoTarjeta {

	/** The consumo pendiente. */
	CONSUMO_PENDIENTE(1),
	/** The pago. */
	PAGO(2),
	/** The ultimo consumo. */
	ULTIMO_CONSUMO(3);

	/** The prioridad. */
	private Integer prioridad;

	/**
	 * Instantiates a new tipo consumo tarjeta.
	 *
	 * @param prioridad
	 *            the prioridad
	 */
	TipoConsumoTarjeta(Integer prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * Gets the prioridad.
	 *
	 * @return the prioridad
	 */
	public Integer getPrioridad() {
		return prioridad;
	}

}

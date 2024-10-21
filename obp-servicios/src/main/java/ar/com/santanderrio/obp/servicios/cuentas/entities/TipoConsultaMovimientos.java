/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Enum TipoConsultaMovimientos.
 */
public enum TipoConsultaMovimientos {

	/** The movimiento consolidados. */
	MOVIMIENTO_CONSOLIDADOS("C"),

	/** The movimientos del dia. */
	MOVIMIENTOS_DEL_DIA("D"),

	/** The ultimos movimientos. */
	ULTIMOS_MOVIMIENTOS("U");

	/** The tipo. */
	private String tipo;

	/**
	 * Instantiates a new tipo consulta movimientos.
	 *
	 * @param tipo
	 *            the tipo
	 */
	TipoConsultaMovimientos(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

}

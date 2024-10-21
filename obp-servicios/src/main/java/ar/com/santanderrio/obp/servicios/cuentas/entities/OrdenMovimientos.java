/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Enum OrdenMovimientos.
 */
public enum OrdenMovimientos {

	/** The ascensdente. */
	ASCENSDENTE("A"),

	/** The descendente. */
	DESCENDENTE("D");

	/** The orden. */
	private String orden;

	/**
	 * Instantiates a new orden movimientos.
	 *
	 * @param orden
	 *            the orden
	 */
	OrdenMovimientos(String orden) {
		this.orden = orden;
	}

	/**
	 * Gets the orden.
	 *
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}
}

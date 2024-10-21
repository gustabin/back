/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;

/**
 * The Enum OrdenPrestamos.
 */
public enum OrdenPrestamos {

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
	OrdenPrestamos(String orden) {
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

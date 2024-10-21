/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

/**
 * The Enum PlazoAcreditacion.
 *
 * @author B015167
 */
public enum PlazoAcreditacion {

	/** The inmediato. */
	INMEDIATO("000", "Inmediata"),
	/** The plazo 24 hs. */
	PLAZO_24_HS("001", "48 hs"),
	/** The plazo 48 hs. */
	PLAZO_48_HS("002", "48 hs"),
	/** The inmediato coelsa. */
	INMEDIATO_COELSA("999", "Inmediata");

	/** The codigo trfcci. */
	private String codigoTrfcci;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new plazo acreditacion.
	 *
	 * @param codigoTrfcci
	 *            the codigo trfcci
	 * @param descripcion
	 *            the descripcion
	 */
	private PlazoAcreditacion(String codigoTrfcci, String descripcion) {
		this.codigoTrfcci = codigoTrfcci;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the codigo trfcci.
	 *
	 * @return the codigoTrfcci
	 */
	public String getCodigoTrfcci() {
		return codigoTrfcci;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
}

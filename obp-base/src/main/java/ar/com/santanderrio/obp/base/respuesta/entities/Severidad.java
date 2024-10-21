/**
 * 
 */
package ar.com.santanderrio.obp.base.respuesta.entities;

// TODO: Auto-generated Javadoc
/**
 * The Enum Severidad.
 *
 * @author Federico_Juliano
 */
public enum Severidad {

	/** The warning. */
	WARNING("WARNING"),

	/** The info. */
	INFO("INFO"),

	/** The error. */
	ERROR("ERROR");

	/** The descripcion. */
	private String descripcion;

	/**
	 * Constructor.
	 *
	 * @param descripcion
	 *            the descripcion
	 */
	Severidad(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the severidad.
	 *
	 * @return the descripcion
	 */
	public String getSeveridad() {
		return descripcion;
	}

}

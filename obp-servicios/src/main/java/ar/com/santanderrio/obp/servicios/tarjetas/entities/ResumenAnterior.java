/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

/**
 * Representa un elemento resumen de tarjeta de la pantalla de resumenes
 * anteriores de tarjeta.
 */
public class ResumenAnterior {

	/** The fecha. */
	private String fecha;

	/** The id. */
	private int id;

	/** The is visto. */
	private Boolean isVisto;

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResumenAnterior [fecha=" + fecha + ", id=" + id + "]";
	}

	/**
	 * Gets the checks if is visto.
	 *
	 * @return the checks if is visto
	 */
	public Boolean getIsVisto() {
		return isVisto;
	}

	/**
	 * Sets the checks if is visto.
	 *
	 * @param isVisto
	 *            the new checks if is visto
	 */
	public void setIsVisto(Boolean isVisto) {
		this.isVisto = isVisto;
	}

}

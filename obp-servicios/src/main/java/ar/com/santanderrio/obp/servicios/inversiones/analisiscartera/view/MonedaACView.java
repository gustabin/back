/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

/**
 * The Class MonedaACView.
 */
public class MonedaACView {

	/** The id. */
	private String id;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The defecto. */
	private Boolean defecto = Boolean.FALSE;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param codigo
	 *            the new id
	 */
	public void setId(String codigo) {
		this.id = codigo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the defecto.
	 *
	 * @return the defecto
	 */
	public Boolean getDefecto() {
		return defecto;
	}

	/**
	 * Sets the defecto.
	 *
	 * @param defecto
	 *            the new defecto
	 */
	public void setDefecto(Boolean defecto) {
		this.defecto = defecto;
	}
	
}

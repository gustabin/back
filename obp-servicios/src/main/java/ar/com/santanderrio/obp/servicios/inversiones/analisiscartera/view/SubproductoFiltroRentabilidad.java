/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

/**
 * The Class SubproductoFiltroRentabilidad.
 */
public class SubproductoFiltroRentabilidad {

	/** The id. */
	private String id;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The por defecto. */
	private Boolean porDefecto = Boolean.FALSE;
	

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
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * Gets the por defecto.
	 *
	 * @return the por defecto
	 */
	public Boolean getPorDefecto() {
		return porDefecto;
	}

	/**
	 * Sets the por defecto.
	 *
	 * @param porDefecto
	 *            the new por defecto
	 */
	public void setPorDefecto(Boolean porDefecto) {
		this.porDefecto = porDefecto;
	}
		
}

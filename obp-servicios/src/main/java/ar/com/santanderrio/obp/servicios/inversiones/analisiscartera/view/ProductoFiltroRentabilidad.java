/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.List;

/**
 * The Class ProductoFiltroRentabilidad.
 */
public class ProductoFiltroRentabilidad {

	/** The id. */
	private String id;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The por defecto. */
	private Boolean porDefecto = Boolean.FALSE;
	
	/** The lista subproductos. */
	private List<SubproductoFiltroRentabilidad> listaSubproductos;

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

	/**
	 * Gets the lista subproductos.
	 *
	 * @return the lista subproductos
	 */
	public List<SubproductoFiltroRentabilidad> getListaSubproductos() {
		return listaSubproductos;
	}

	/**
	 * Sets the lista subproductos.
	 *
	 * @param listaSubproductos
	 *            the new lista subproductos
	 */
	public void setListaSubproductos(List<SubproductoFiltroRentabilidad> listaSubproductos) {
		this.listaSubproductos = listaSubproductos;
	}
		
}

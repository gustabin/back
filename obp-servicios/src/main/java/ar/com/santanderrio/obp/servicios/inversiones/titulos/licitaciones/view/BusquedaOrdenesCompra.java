/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class BusquedaOrdenesCompra.
 */
public class BusquedaOrdenesCompra extends View{
	
	/** The serial VersionUID. */
	private static final long serialVersionUID = 3236954006795175095L;
	
	/** The descripcion especie. */
	private String descripcionEspecie;

	/**
	 * Gets the descripcion especie.
	 *
	 * @return the descripcionEspecie
	 */
	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	/**
	 * Sets the descripcion especie.
	 *
	 * @param descripcionEspecie
	 *            the descripcionEspecie to set
	 */
	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}
}

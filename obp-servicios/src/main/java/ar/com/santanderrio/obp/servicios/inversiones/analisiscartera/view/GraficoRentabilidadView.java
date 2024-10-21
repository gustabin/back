/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class GraficoRentabilidadView.
 */
public class GraficoRentabilidadView {

	/** The lista productos. */
	List<GraficoRentabilidad> listaProductos = new ArrayList<GraficoRentabilidad>();

	/**
	 * Gets the lista productos.
	 *
	 * @return the lista productos
	 */
	public List<GraficoRentabilidad> getListaProductos() {
		return listaProductos;
	}

	/**
	 * Sets the lista productos.
	 *
	 * @param listaProductos
	 *            the new lista productos
	 */
	public void setListaProductos(List<GraficoRentabilidad> listaProductos) {
		this.listaProductos = listaProductos;
	}
}

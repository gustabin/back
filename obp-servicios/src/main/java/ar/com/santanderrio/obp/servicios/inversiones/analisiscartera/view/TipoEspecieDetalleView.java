/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.List;

/**
 * The Class TipoEspecieDetalleView.
 */
public class TipoEspecieDetalleView {

	/** The cabecera. */
	private ProductoDetalleView cabecera;
	
	/** The lista productos. */
	private List<ProductoDetalleView> listaProductos;
		
	/**
	 * Gets the cabecera.
	 *
	 * @return the cabecera
	 */
	public ProductoDetalleView getCabecera() {
		return cabecera;
	}

	/**
	 * Sets the cabecera.
	 *
	 * @param cabecera
	 *            the new cabecera
	 */
	public void setCabecera(ProductoDetalleView cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * Gets the lista productos.
	 *
	 * @return the lista productos
	 */
	public List<ProductoDetalleView> getListaProductos() {
		return listaProductos;
	}

	/**
	 * Sets the lista productos.
	 *
	 * @param listaProductos
	 *            the new lista productos
	 */
	public void setListaProductos(List<ProductoDetalleView> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
}

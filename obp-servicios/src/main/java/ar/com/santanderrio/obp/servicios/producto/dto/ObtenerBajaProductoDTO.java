/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.dto;

import java.util.List;

/**
 * The Class ObtenerBajaProductoDTO.
 */
public class ObtenerBajaProductoDTO {

	/** The productos. */
	private List<ProductoDTO> productos;

	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 */
	public List<ProductoDTO> getProductos() {
		return productos;
	}

	/**
	 * Sets the productos.
	 *
	 * @param productos
	 *            the new productos
	 */
	public void setProductos(List<ProductoDTO> productos) {
		this.productos = productos;
	}

}

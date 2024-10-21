/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class DetalleProductoView.
 */
public class DetalleProductoView {

	/** The codigo producto. */
	private String codigoProducto;

	/** The descripcion producto. */
	private String descripcionProducto;

	/** The rentabilidad cabecera. */
	private String rentabilidadCabecera;

	/** The rendimiento cabecera. */
	private BigDecimal rendimientoCabecera;
	
	/** The rendimiento cabecera txt. */
	private String rendimientoCabeceraTxt;
	
	/** The list detalle producto. */
	private List<DetalleItemProducto> listDetalleProducto = new ArrayList<DetalleItemProducto>();

	/**
	 * Gets the codigo producto.
	 *
	 * @return the codigo producto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * Sets the codigo producto.
	 *
	 * @param codigoProducto
	 *            the new codigo producto
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * Gets the descripcion producto.
	 *
	 * @return the descripcion producto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * Sets the descripcion producto.
	 *
	 * @param descripcionProducto
	 *            the new descripcion producto
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * Gets the rentabilidad cabecera.
	 *
	 * @return the rentabilidad cabecera
	 */
	public String getRentabilidadCabecera() {
		return rentabilidadCabecera;
	}

	/**
	 * Sets the rentabilidad cabecera.
	 *
	 * @param rentabilidadCabecera
	 *            the new rentabilidad cabecera
	 */
	public void setRentabilidadCabecera(String rentabilidadCabecera) {
		this.rentabilidadCabecera = rentabilidadCabecera;
	}

	/**
	 * Gets the rendimiento cabecera.
	 *
	 * @return the rendimiento cabecera
	 */
	public BigDecimal getRendimientoCabecera() {
		return rendimientoCabecera;
	}

	/**
	 * Sets the rendimiento cabecera.
	 *
	 * @param rendimientoCabecera
	 *            the new rendimiento cabecera
	 */
	public void setRendimientoCabecera(BigDecimal rendimientoCabecera) {
		this.rendimientoCabecera = rendimientoCabecera;
	}

	/**
	 * Gets the rendimiento cabecera txt.
	 *
	 * @return the rendimiento cabecera txt
	 */
	public String getRendimientoCabeceraTxt() {
		return rendimientoCabeceraTxt;
	}

	/**
	 * Sets the rendimiento cabecera txt.
	 *
	 * @param rendimientoCabeceraTxt
	 *            the new rendimiento cabecera txt
	 */
	public void setRendimientoCabeceraTxt(String rendimientoCabeceraTxt) {
		this.rendimientoCabeceraTxt = rendimientoCabeceraTxt;
	}

	/**
	 * Gets the list detalle producto.
	 *
	 * @return the list detalle producto
	 */
	public List<DetalleItemProducto> getListDetalleProducto() {
		return listDetalleProducto;
	}

	/**
	 * Sets the list detalle producto.
	 *
	 * @param listDetalleProducto
	 *            the new list detalle producto
	 */
	public void setListDetalleProducto(List<DetalleItemProducto> listDetalleProducto) {
		this.listDetalleProducto = listDetalleProducto;
	}
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.view;

import java.util.List;

/**
 * The Class BajaProductoView.
 */
public class BajaProductoView {

	/** The tipo operacion. */
	private TipoOperacionBajaProductoEnum tipoOperacion;

	/** The productos. */
	private List<ProductoView> productos;

	/** The codigo operacion. */
	private String codigoOperacion;

	/** The codigo producto. */
	private ProductoView codigoProducto;

	/** The texto legal. */
	private String legales;
	
	/** The bajaCA. */
	private Boolean bajaCA;

	private String mensajeOferta;
	
	private Boolean recargable;
	
	private Boolean arrepentimiento = Boolean.FALSE;
	
	private String descripcion;
	
	private String producto;
	
	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 */
	public List<ProductoView> getProductos() {
		return productos;
	}

	/**
	 * Sets the productos.
	 *
	 * @param productos
	 *            the new productos
	 */
	public void setProductos(List<ProductoView> productos) {
		this.productos = productos;
	}

	/**
	 * Gets the tipo operacion.
	 *
	 * @return the tipo operacion
	 */
	public TipoOperacionBajaProductoEnum getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 *
	 * @param tipoOperacion
	 *            the new tipo operacion
	 */
	public void setTipoOperacion(TipoOperacionBajaProductoEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * Gets the codigo operacion.
	 *
	 * @return the codigo operacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * Sets the codigo operacion.
	 *
	 * @param codigoOperacion
	 *            the new codigo operacion
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	/**
	 * Gets the codigo producto.
	 *
	 * @return the codigo producto
	 */
	public ProductoView getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * Sets the codigo producto.
	 *
	 * @param codigoProducto
	 *            the new codigo producto
	 */
	public void setCodigoProducto(ProductoView codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the baja CA.
	 *
	 * @return the bajaCA
	 */
	public Boolean getBajaCA() {
		return bajaCA;
	}

	/**
	 * Sets the baja CA.
	 *
	 * @param bajaCA
	 *            the bajaCA to set
	 */
	public void setBajaCA(Boolean bajaCA) {
		this.bajaCA = bajaCA;
	}

	public String getMensajeOferta() {
		return mensajeOferta;
	}
	
	public void setMensajeOferta(String mensajeOferta) {
		this.mensajeOferta = mensajeOferta;
	}

	public Boolean getRecargable() {
		return recargable;
	}

	public void setRecargable(Boolean recargable) {
		this.recargable = recargable;
	}

	public Boolean getArrepentimiento() {
		return arrepentimiento;
	}

	public void setArrepentimiento(Boolean arrepentimiento) {
		this.arrepentimiento = arrepentimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}
		
}

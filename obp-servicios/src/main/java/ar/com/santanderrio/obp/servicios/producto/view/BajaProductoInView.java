/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.view;

/**
 * The Class BajaProductoInView.
 */
public class BajaProductoInView {

	/** The tipo operacion. */
	private TipoOperacionBajaProductoEnum tipoOperacion;

	/** The codigo operacion. */
	private String codigoOperacion;

	/** The codigo producto. */
	private String codigoProducto;

	/** The descripcion. */
	private String descripcion;

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

}

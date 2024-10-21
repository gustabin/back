/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.view;

/**
 * The Class ProductoView.
 */
public class ProductoView {

	/** The descripcion. */
	private String descripcion;

	/** The tipo producto. */
	private String tipoProducto;

	/** The codigo. */
	private String codigo;

	/** The cuenta. */
	private int cuenta;
	
	/** The numero. */
	private String numero;
	
	/** The recargable. */
	private Boolean recargable;
	
	

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
	 * @param descripcionProducto
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcionProducto) {
		this.descripcion = descripcionProducto;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigoProducto
	 *            the new codigo
	 */
	public void setCodigo(String codigoProducto) {
		this.codigo = codigoProducto;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public int getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the tipo producto.
	 *
	 * @return the tipo producto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * Sets the tipo producto.
	 *
	 * @param tipoProducto
	 *            the new tipo producto
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Boolean getRecargable() {
		return recargable;
	}

	public void setRecargable(Boolean recargable) {
		this.recargable = recargable;
	}
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.dto;

/**
 * The Class ProductoDTO.
 */
public class ProductoDTO {

	/** The codigo. */
	private String codigo;

	/** The paq. */
	private String paq;

	/** The descripcion. */
	private String descripcion;

	/** The tipo producto. */
	private String tipoProducto;

	/** The cuenta. */
	private int cuenta;
	
	/** The numero. */
	private String numero;
	
	/** The recargable. */
	private Boolean recargable;

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
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * Gets the paq.
	 *
	 * @return the paq
	 */
	public String getPaq() {
		return paq;
	}

	/**
	 * Sets the paq.
	 *
	 * @param paq
	 *            the new paq
	 */
	public void setPaq(String paq) {
		this.paq = paq;
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
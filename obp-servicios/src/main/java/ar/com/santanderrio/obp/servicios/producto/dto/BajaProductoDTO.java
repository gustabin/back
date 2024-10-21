/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.dto;

import ar.com.santanderrio.obp.servicios.producto.view.TipoOperacionBajaProductoEnum;

/**
 * The Class BajaProductoDTO.
 */
public class BajaProductoDTO {

	/** The tipo operacion. */
	private TipoOperacionBajaProductoEnum tipoOperacion;

	/** The mensaje. */
	private String mensaje;

	/** The codigo. */
	private String codigo;

	/** The comprobante. */
	private String comprobante;

	/** The descripcion. */
	private String descripcion;

	/** The tipo producto. */
	private String tipoProducto;

	/** The fecha baja. */
	private String fechaBaja;

	/** The cuenta. */
	private String cuenta;
	
	/** The bajaCA. */
	private Boolean bajaCA;
	
	/** The recargable. */
	private Boolean recargable;
	
	private Boolean arrepentimiento;
	
	private String descripcionArrepentimiento;

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
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the new comprobante
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
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
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;

	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
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
	 * Gets the fecha baja.
	 *
	 * @return the fecha baja
	 */
	public String getFechaBaja() {
		return fechaBaja;
	}

	/**
	 * Sets the fecha baja.
	 *
	 * @param fechaBaja
	 *            the new fecha baja
	 */
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	/**
	 * Gets the bajaCA.
	 *
	 * @return the bajaCA
	 */
	public Boolean getBajaCA() {
		return bajaCA;
	}

	/**
	 * Sets the bajaCA.
	 *
	 * @param bajaCA
	 *            the new bajaCA
	 */
	public void setBajaCA(Boolean bajaCA) {
		this.bajaCA = bajaCA;
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

	public String getDescripcionArrepentimiento() {
		return descripcionArrepentimiento;
	}

	public void setDescripcionArrepentimiento(String descripcionArrepentimiento) {
		this.descripcionArrepentimiento = descripcionArrepentimiento;
	}
	
	
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.view;

/**
 * The Class ComprobanteBajaProductoView.
 */
public class ComprobanteBajaProductoView {

	/** The mensaje. */
	private String mensaje;

	/** The nro comprobante. */
	private String nroComprobante;

	/** The fecha de baja. */
	private String fechaDeBaja;
	
	/** The fecha de operacion. */
	private String fechaOperacion;

	/** The descripcion producto. */
	private String descripcionProducto;

	/** The tipo operacion. */
	private TipoOperacionBajaProductoEnum tipoOperacion;
	
	/** The tipo producto. */
	private String tipoProducto;
	
	/** The numero producto. */
	private String numero;
	
	/** The mantieneCajaAhorro. */
	private Boolean mantieneCajaAhorro;

	private Boolean arrepentimiento = false;
	
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
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the fecha de baja.
	 *
	 * @return the fecha de baja
	 */
	public String getFechaDeBaja() {
		return fechaDeBaja;
	}

	/**
	 * Sets the fecha de baja.
	 *
	 * @param fechaDeBaja
	 *            the new fecha de baja
	 */
	public void setFechaDeBaja(String fechaDeBaja) {
		this.fechaDeBaja = fechaDeBaja;
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
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the mantiene caja ahorro.
	 *
	 * @return the mantieneCajaAhorro
	 */
	public Boolean getMantieneCajaAhorro() {
		return mantieneCajaAhorro;
	}

	/**
	 * Sets the mantiene caja ahorro.
	 *
	 * @param mantieneCajaAhorro
	 *            the mantieneCajaAhorro to set
	 */
	public void setMantieneCajaAhorro(Boolean mantieneCajaAhorro) {
		this.mantieneCajaAhorro = mantieneCajaAhorro;
	}

	/**
	 * Gets the tipo producto.
	 *
	 * @return the tipoProducto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * Sets the tipo producto.
	 *
	 * @param tipoProducto
	 *            the tipoProducto to set
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	/**
	 * Gets the fecha operacion.
	 *
	 * @return the fechaOperacion
	 */
	public String getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * Sets the fecha operacion.
	 *
	 * @param fechaOperacion
	 *            the fechaOperacion to set
	 */
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public Boolean getArrepentimiento() {
		return arrepentimiento;
	}

	public void setArrepentimiento(Boolean arrepentimiento) {
		this.arrepentimiento = arrepentimiento;
	}
	
}

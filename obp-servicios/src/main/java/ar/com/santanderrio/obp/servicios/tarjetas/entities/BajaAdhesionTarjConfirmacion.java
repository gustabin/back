/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

/**
 * The Class BajaAdhesionTarjConfirmacion.
 */
public class BajaAdhesionTarjConfirmacion {

	/** The mensaje. */
	private String mensaje;

	/** The nro tarjeta. */
	private String nroTarjeta;

	/** The tipo tarjeta. */
	private String tipoTarjeta;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The nro cuenta producto. */
	private String nroCuentaProducto;

	/** The alias cuenta. */
	private String aliasCuenta;

	/** The tipo pago. */
	private String tipoPago;

	/** The fecha prox recarga. */
	private String fechaProxRecarga;

	/** The is dar baja recarga. */
	private Boolean isDarBajaRecarga;

	/** The stop debit recarga. */
	private Boolean stopDebitRecarga;

	/** The importe. */
	private String importe;

	/** The fecha inicio. */
	private String fechaInicio;

	/** The frecuencia. */
	private String frecuencia;

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
	 * Gets the nro tarjeta.
	 *
	 * @return the nro tarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the new nro tarjeta
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the alias cuenta.
	 *
	 * @return the alias cuenta
	 */
	public String getAliasCuenta() {
		return aliasCuenta;
	}

	/**
	 * Sets the alias cuenta.
	 *
	 * @param aliasCuenta
	 *            the new alias cuenta
	 */
	public void setAliasCuenta(String aliasCuenta) {
		this.aliasCuenta = aliasCuenta;
	}

	/**
	 * Gets the tipo pago.
	 *
	 * @return the tipo pago
	 */
	public String getTipoPago() {
		return tipoPago;
	}

	/**
	 * Sets the tipo pago.
	 *
	 * @param tipoPago
	 *            the new tipo pago
	 */
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	/**
	 * Gets the nro cuenta producto.
	 *
	 * @return the nro cuenta producto
	 */
	public String getNroCuentaProducto() {
		return nroCuentaProducto;
	}

	/**
	 * Sets the nro cuenta producto.
	 *
	 * @param nroCuentaProducto
	 *            the new nro cuenta producto
	 */
	public void setNroCuentaProducto(String nroCuentaProducto) {
		this.nroCuentaProducto = nroCuentaProducto;
	}

	/**
	 * Gets the checks if is dar baja recarga.
	 *
	 * @return the checks if is dar baja recarga
	 */
	public Boolean getIsDarBajaRecarga() {
		return isDarBajaRecarga;
	}

	/**
	 * Sets the checks if is dar baja recarga.
	 *
	 * @param isDarBajaRecarga
	 *            the new checks if is dar baja recarga
	 */
	public void setIsDarBajaRecarga(Boolean isDarBajaRecarga) {
		this.isDarBajaRecarga = isDarBajaRecarga;
	}

	/**
	 * Gets the fecha prox recarga.
	 *
	 * @return the fecha prox recarga
	 */
	public String getFechaProxRecarga() {
		return fechaProxRecarga;
	}

	/**
	 * Sets the fecha prox recarga.
	 *
	 * @param fechaProxRecarga
	 *            the new fecha prox recarga
	 */
	public void setFechaProxRecarga(String fechaProxRecarga) {
		this.fechaProxRecarga = fechaProxRecarga;
	}

	/**
	 * Gets the frecuencia.
	 *
	 * @return the frecuencia
	 */
	public String getFrecuencia() {
		return frecuencia;
	}

	/**
	 * Sets the frecuencia.
	 *
	 * @param frecuencia
	 *            the new frecuencia
	 */
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	/**
	 * Gets the stop debit recarga.
	 *
	 * @return the stop debit recarga
	 */
	public Boolean getStopDebitRecarga() {
		return stopDebitRecarga;
	}

	/**
	 * Sets the stop debit recarga.
	 *
	 * @param stopDebitRecarga
	 *            the new stop debit recarga
	 */
	public void setStopDebitRecarga(Boolean stopDebitRecarga) {
		this.stopDebitRecarga = stopDebitRecarga;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio
	 *            the new fecha inicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Gets the tipo tarjeta.
	 *
	 * @return the tipo tarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Sets the tipo tarjeta.
	 *
	 * @param tipoTarjeta
	 *            the new tipo tarjeta
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

}

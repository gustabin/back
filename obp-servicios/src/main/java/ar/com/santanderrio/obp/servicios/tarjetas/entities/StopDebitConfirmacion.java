/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.io.Serializable;

/**
 * The Class StopDebitConfirmacion.
 */
public class StopDebitConfirmacion implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The mensaje. */
	private String mensaje;

	/** The nro tarjeta. */
	private String nroTarjeta;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The fecha vencimiento mobile. */
	private String fechaVencimientoMobile;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The alias cuenta. */
	private String aliasCuenta;

	/** The importe. */
	private String importe;

	/** The tipo pago. */
	private String tipoPago;

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
	 * Gets the fecha vencimiento mobile.
	 *
	 * @return the fecha vencimiento mobile
	 */
	public String getFechaVencimientoMobile() {
		return fechaVencimientoMobile;
	}

	/**
	 * Sets the fecha vencimiento mobile.
	 *
	 * @param fechaVencimientoMobile
	 *            the new fecha vencimiento mobile
	 */
	public void setFechaVencimientoMobile(String fechaVencimientoMobile) {
		this.fechaVencimientoMobile = fechaVencimientoMobile;
	}

}

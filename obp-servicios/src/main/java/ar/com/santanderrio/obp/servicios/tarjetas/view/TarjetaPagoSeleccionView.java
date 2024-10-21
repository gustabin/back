/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import java.io.Serializable;

/**
 * The Class TarjetaPagoSeleccionView.
 */
public class TarjetaPagoSeleccionView implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9197878197537007924L;

	/** The fecha vencimientof. */
	private String fechaVencimiento;

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The tipo de pago. */
	private String tipoDePago;

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
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * Gets the tipo de pago.
	 *
	 * @return the tipo de pago
	 */
	public String getTipoDePago() {
		return tipoDePago;
	}

	/**
	 * Sets the tipo de pago.
	 *
	 * @param tipoDePago
	 *            the new tipo de pago
	 */
	public void setTipoDePago(String tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

}

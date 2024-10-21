/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.view;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class FeedbackStopDebitView.
 */
public class FeedbackStopDebitView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The mensaje. */
	private String mensaje;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The fecha comprobante. */
	private String fechaComprobante;

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
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the fecha comprobante.
	 *
	 * @return the fecha comprobante
	 */
	public String getFechaComprobante() {
		return fechaComprobante;
	}

	/**
	 * Sets the fecha comprobante.
	 *
	 * @param fechaComprobante
	 *            the new fecha comprobante
	 */
	public void setFechaComprobante(String fechaComprobante) {
		this.fechaComprobante = fechaComprobante;
	}

}

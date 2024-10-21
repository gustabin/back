/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.web.view;

/**
 * The Class AdhesionRespuestaView.
 */
public class AdhesionRespuestaView {

	/** The mensaje. */
	private String mensaje;

	/** The fecha. */
	private String fecha;

	/** The hora. */
	private String hora;

	/** The feedback. */
	private FeedbackView feedback;

	/** The tipo error. */
	private String tipoError;
	
	/** The nro comprobante. */
	private String nroComprobante;

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
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the new hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the feedback.
	 *
	 * @return the feedback
	 */
	public FeedbackView getFeedback() {
		return feedback;
	}

	/**
	 * Sets the feedback.
	 *
	 * @param feedback
	 *            the new feedback
	 */
	public void setFeedback(FeedbackView feedback) {
		this.feedback = feedback;
	}

	/**
	 * Gets the tipo error.
	 *
	 * @return the tipo error
	 */
	public String getTipoError() {
		return tipoError;
	}

	/**
	 * Sets the tipo error.
	 *
	 * @param tipoError
	 *            the new tipo error
	 */
	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nroComprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the nroComprobante to set
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	
	
}

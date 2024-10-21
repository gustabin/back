/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.view;

/**
 * The Class ConfirmacionModificacionView.
 */
public class ConfirmacionModificacionView extends TransferenciaAgendadaDetalleView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The email activo. */
	private Boolean emailActivo;

	/** The email. */
	private String email;

	/** The mensaje email. */
	private String mensajeEmail;

	/**
	 * Gets the email activo.
	 *
	 * @return the emailActivo
	 */
	public Boolean isEmailActivo() {
		return emailActivo;
	}

	/**
	 * Sets the email activo.
	 *
	 * @param emailActivo
	 *            the emailActivo to set
	 */
	public void setEmailActivo(Boolean emailActivo) {
		this.emailActivo = emailActivo;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the mensaje email.
	 *
	 * @return the mensajeEmail
	 */
	public String getMensajeEmail() {
		return mensajeEmail;
	}

	/**
	 * Sets the mensaje email.
	 *
	 * @param mensajeEmail
	 *            the mensajeEmail to set
	 */
	public void setMensajeEmail(String mensajeEmail) {
		this.mensajeEmail = mensajeEmail;
	}

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.web.view;

import java.util.List;

/**
 * The Class AltaUsuarioBilleteraView.
 */
public class FeedbackBilleteraView {

	/** The cuenta acreditacion ok. */
	private String cuentaAcreditacionOk;

	/** The email. */
	private String email;

	/** The mensaje. */
	private String mensaje;

	/** The mensaje error. */
	private String mensajeError;

	/** The mode. */
	private String mode;

	/** The tarjetas ok. */
	private List<MedioDePagoBilleteraView> tarjetasOk;

	/**
	 * Gets the cuenta acreditacion ok.
	 *
	 * @return the cuentaAcreditacionOk
	 */
	public String getCuentaAcreditacionOk() {
		return cuentaAcreditacionOk;
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
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Gets the mensaje error.
	 *
	 * @return the mensajeError
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * Gets the tarjetas ok.
	 *
	 * @return the tarjetasOk
	 */
	public List<MedioDePagoBilleteraView> getTarjetasOk() {
		return tarjetasOk;
	}

	/**
	 * Sets the cuenta acreditacion ok.
	 *
	 * @param cuentaAcreditacionOk
	 *            the cuentaAcreditacionOk to set
	 */
	public void setCuentaAcreditacionOk(String cuentaAcreditacionOk) {
		this.cuentaAcreditacionOk = cuentaAcreditacionOk;
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
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Sets the mensaje error.
	 *
	 * @param mensajeError
	 *            the mensajeError to set
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * Sets the mode.
	 *
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * Sets the tarjetas ok.
	 *
	 * @param tarjetasOk
	 *            the tarjetasOk to set
	 */
	public void setTarjetasOk(List<MedioDePagoBilleteraView> tarjetasOk) {
		this.tarjetasOk = tarjetasOk;
	}

}

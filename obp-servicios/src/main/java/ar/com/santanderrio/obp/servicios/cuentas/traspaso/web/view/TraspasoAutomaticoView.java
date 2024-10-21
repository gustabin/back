/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view;

import java.io.Serializable;

/**
 * The Class TraspasoView.
 */
public class TraspasoAutomaticoView implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The is activo. */
	private Boolean isActivo = Boolean.FALSE;

	/** The estado actual. */
	private String estadoActual;

	/** The is activo. */
	private Boolean isPendiente = Boolean.FALSE;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The alias cuenta. */
	private String aliasCuenta;

	/** The mensaje feedback. */
	private String mensajeFeedback;

	/**
	 * Gets the checks if is activo.
	 *
	 * @return the isActivo
	 */
	public Boolean getIsActivo() {
		return isActivo;
	}

	/**
	 * Sets the checks if is activo.
	 *
	 * @param isActivo
	 *            the isActivo to set
	 */
	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	/**
	 * Gets the estado actual.
	 *
	 * @return the estadoActual
	 */
	public String getEstadoActual() {
		return estadoActual;
	}

	/**
	 * Sets the estado actual.
	 *
	 * @param estadoActual
	 *            the estadoActual to set
	 */
	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the alias cuenta.
	 *
	 * @return the aliasCuenta
	 */
	public String getAliasCuenta() {
		return aliasCuenta;
	}

	/**
	 * Sets the alias cuenta.
	 *
	 * @param aliasCuenta
	 *            the aliasCuenta to set
	 */
	public void setAliasCuenta(String aliasCuenta) {
		this.aliasCuenta = aliasCuenta;
	}

	/**
	 * Gets the checks if is pendiente.
	 *
	 * @return the isPendiente
	 */
	public Boolean getIsPendiente() {
		return isPendiente;
	}

	/**
	 * Gets the mensaje feedback.
	 *
	 * @return the mensaje feedback
	 */
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	/**
	 * Sets the mensaje feedback.
	 *
	 * @param mensajeFeedback
	 *            the new mensaje feedback
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	/**
	 * Sets the checks if is pendiente.
	 *
	 * @param isPendiente
	 *            the isPendiente to set
	 */
	public void setIsPendiente(Boolean isPendiente) {
		this.isPendiente = isPendiente;
	}

}

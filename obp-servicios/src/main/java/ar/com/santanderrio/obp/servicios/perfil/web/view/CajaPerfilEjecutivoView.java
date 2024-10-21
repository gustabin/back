/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.web.view;

/**
 * The Class CajaPerfilEjecutivoView.
 */
public class CajaPerfilEjecutivoView extends CajaPerfil {

	/** The is ejecutivo. */
	private Boolean isEjecutivo;
	
	/** The mailEjecutivo. */
	private String mailEjecutivo;

	/** The has error. */
	private Boolean hasError;

	/**
	 * Gets the checks if is ejecutivo.
	 *
	 * @return the checks if is ejecutivo
	 */
	public Boolean getIsEjecutivo() {
		return isEjecutivo;
	}

	/**
	 * Sets the checks if is ejecutivo.
	 *
	 * @param isEjecutivo
	 *            the new checks if is ejecutivo
	 */
	public void setIsEjecutivo(Boolean isEjecutivo) {
		this.isEjecutivo = isEjecutivo;
	}
	
	/**
	 * Gets the mailEjecutivo.
	 *
	 * @return the mailEjecutivo
	 */
	public String getMailEjecutivo() {
		return mailEjecutivo;
	}

	/**
	 * Sets the mailEjecutivo.
	 *
	 * @param mailEjecutivo
	 *            the mailEjecutivo
	 */
	public void setMailEjecutivo(String mailEjecutivo) {
		this.mailEjecutivo = mailEjecutivo;
	}

	/**
	 * Gets the checks for error.
	 *
	 * @return the checks for error
	 */
	public Boolean getHasError() {
		return hasError;
	}

	/**
	 * Sets the checks for error.
	 *
	 * @param hasError
	 *            the new checks for error
	 */
	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	/**
	 * Instantiates a new caja perfil ejecutivo view.
	 */
	public CajaPerfilEjecutivoView() {
		isEjecutivo = true;
	}
}

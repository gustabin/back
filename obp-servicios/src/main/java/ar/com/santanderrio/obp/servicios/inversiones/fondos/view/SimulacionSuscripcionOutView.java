/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import ar.com.santanderrio.obp.servicios.inversiones.comun.view.SimulacionOutView;

/**
 * The Class SimulacionSuscripcionOutView.
 */
public class SimulacionSuscripcionOutView extends SimulacionOutView {
	/** The dentro del perfil. */
	private String dentroDelPerfil;

	/** urlReglamento. */
	private String urlReglamento;

	/** urlTerminosYCondiciones. */
	private String terminosYCondiciones;

	/**
	 * Gets the dentro del perfil.
	 *
	 * @return the dentro del perfil
	 */
	public String getDentroDelPerfil() {
		return dentroDelPerfil;
	}

	/**
	 * Sets the dentro del perfil.
	 *
	 * @param dentroDelPerfil
	 *            the new dentro del perfil
	 */
	public void setDentroDelPerfil(String dentroDelPerfil) {
		this.dentroDelPerfil = dentroDelPerfil;
	}

	/**
	 * Gets the url reglamento.
	 *
	 * @return the url reglamento
	 */
	public String getUrlReglamento() {
		return urlReglamento;
	}

	/**
	 * Sets the url reglamento.
	 *
	 * @param urlReglamento
	 *            the new url reglamento
	 */
	public void setUrlReglamento(String urlReglamento) {
		this.urlReglamento = urlReglamento;
	}

	/**
	 * Gets the terminos Y condiciones.
	 *
	 * @return the terminos Y condiciones
	 */
	public String getTerminosYCondiciones() {
		return terminosYCondiciones;
	}

	/**
	 * Sets the terminos Y condiciones.
	 *
	 * @param terminosYCondiciones
	 *            the new terminos Y condiciones
	 */
	public void setTerminosYCondiciones(String terminosYCondiciones) {
		this.terminosYCondiciones = terminosYCondiciones;
	}

}

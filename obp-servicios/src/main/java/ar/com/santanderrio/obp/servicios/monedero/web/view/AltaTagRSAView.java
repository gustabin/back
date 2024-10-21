/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.view;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

/**
 * The Class AltaTagRSAView.
 */
public class AltaTagRSAView {

	/** The challenge. */
	private String challenge;

	/** The desafio. */
	private AutentificacionDTO desafio;

	/** The reintentar. */
	private String reintentar;

	/**
	 * Gets the challenge.
	 *
	 * @return the challenge
	 */
	public String getChallenge() {
		return challenge;
	}

	/**
	 * Sets the challenge.
	 *
	 * @param challenge
	 *            the new challenge
	 */
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	/**
	 * Gets the desafio.
	 *
	 * @return the desafio
	 */
	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	/**
	 * Sets the desafio.
	 *
	 * @param desafio
	 *            the new desafio
	 */
	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/**
	 * Gets the reintentar.
	 *
	 * @return the reintentar
	 */
	public String getReintentar() {
		return reintentar;
	}

	/**
	 * Sets the reintentar.
	 *
	 * @param reintentar
	 *            the new reintentar
	 */
	public void setReintentar(String reintentar) {
		this.reintentar = reintentar;
	}

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.view;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.BanelcoOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.TokenOperacionDTO;

/**
 * The Class AltaTagRSAReqView.
 */
public class AltaTagRSAReqView {

	/** The token operacion. */
	private TokenOperacionDTO tokenOperacion;

	/** The coordenadas operacion. */
	private CoordenadasOperacionDTO coordenadasOperacion;

	/** The banelco operacion. */
	private BanelcoOperacionDTO banelcoOperacion;

	/** The tipo desafio. */
	private String tipoDesafio;

	/**
	 * Gets the token operacion.
	 *
	 * @return the token operacion
	 */
	public TokenOperacionDTO getTokenOperacion() {
		return tokenOperacion;
	}

	/**
	 * Sets the token operacion.
	 *
	 * @param tokenOperacion
	 *            the new token operacion
	 */
	public void setTokenOperacion(TokenOperacionDTO tokenOperacion) {
		this.tokenOperacion = tokenOperacion;
	}

	/**
	 * Gets the coordenadas operacion.
	 *
	 * @return the coordenadas operacion
	 */
	public CoordenadasOperacionDTO getCoordenadasOperacion() {
		return coordenadasOperacion;
	}

	/**
	 * Sets the coordenadas operacion.
	 *
	 * @param coordenadasOperacion
	 *            the new coordenadas operacion
	 */
	public void setCoordenadasOperacion(CoordenadasOperacionDTO coordenadasOperacion) {
		this.coordenadasOperacion = coordenadasOperacion;
	}

	/**
	 * Gets the banelco operacion.
	 *
	 * @return the banelco operacion
	 */
	public BanelcoOperacionDTO getBanelcoOperacion() {
		return banelcoOperacion;
	}

	/**
	 * Sets the banelco operacion.
	 *
	 * @param banelcoOperacion
	 *            the new banelco operacion
	 */
	public void setBanelcoOperacion(BanelcoOperacionDTO banelcoOperacion) {
		this.banelcoOperacion = banelcoOperacion;
	}

	/**
	 * Gets the tipo desafio.
	 *
	 * @return the tipo desafio
	 */
	public String getTipoDesafio() {
		return tipoDesafio;
	}

	/**
	 * Sets the tipo desafio.
	 *
	 * @param tipoDesafio
	 *            the new tipo desafio
	 */
	public void setTipoDesafio(String tipoDesafio) {
		this.tipoDesafio = tipoDesafio;
	}

}

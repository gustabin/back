/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.SimulacionBaseOutDTO;

/**
 * The Class SimulacionOutDTO.
 */
public class SimularSuscripcionOutDTO extends SimulacionBaseOutDTO {

	/** urlReglamento. */
	private String urlReglamento;

	/** terminosYCondiciones. */
	private String terminosYCondiciones;

	/** The legales informacion. */
	private String legalesInformacion;

	/** The cuotas parte. */
	private String cuotasParte;

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

	/**
	 * Gets the legales informacion.
	 *
	 * @return the legales informacion
	 */
	public String getLegalesInformacion() {
		return legalesInformacion;
	}

	/**
	 * Sets the legales informacion.
	 *
	 * @param legalesInformacion
	 *            the new legales informacion
	 */
	public void setLegalesInformacion(String legalesInformacion) {
		this.legalesInformacion = legalesInformacion;
	}

	/**
	 * Gets the cuotas parte.
	 *
	 * @return the cuotas parte
	 */
	public String getCuotasParte() {
		return cuotasParte;
	}

	/**
	 * Sets the cuotas parte.
	 *
	 * @param cuotasParte
	 *            the new cuotas parte
	 */
	public void setCuotasParte(String cuotasParte) {
		this.cuotasParte = cuotasParte;
	}

}

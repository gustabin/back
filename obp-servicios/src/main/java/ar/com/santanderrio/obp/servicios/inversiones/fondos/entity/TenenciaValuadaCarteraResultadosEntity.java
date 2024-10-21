/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class TenenciaValuadaCarteraResultadosEntity.
 */
public class TenenciaValuadaCarteraResultadosEntity {

	/** The dolares. */
	@JsonProperty("Dolares")
	private String dolares;

	/** The pesos. */
	@JsonProperty("Pesos")
	private String pesos;

	/**
	 * Gets the dolares.
	 *
	 * @return the dolares
	 */
	public String getDolares() {
		return dolares;
	}

	/**
	 * Sets the dolares.
	 *
	 * @param dolares
	 *            the new dolares
	 */
	public void setDolares(String dolares) {
		this.dolares = dolares;
	}

	/**
	 * Gets the pesos.
	 *
	 * @return the pesos
	 */
	public String getPesos() {
		return pesos;
	}

	/**
	 * Sets the pesos.
	 *
	 * @param pesos
	 *            the new pesos
	 */
	public void setPesos(String pesos) {
		this.pesos = pesos;
	}

}

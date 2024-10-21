package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class DatosFondosAgendEntity {

	@JsonProperty("CodigoFondo")
	private String codigoFondo;
	
	@JsonProperty("DescripcionFondo")
	private String descripcionFondo;

	@JsonProperty("MonedaFondo")
	private String monedaFondo;
	
	@JsonProperty("PuedeRescatar")
	private boolean puedeRescatar;
	
	@JsonProperty("PuedeSuscribir")
	private boolean puedeSuscribir;

	/**
	 * @return the codigoFondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * @param codigoFondo the codigoFondo to set
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * @return the descripcionFondo
	 */
	public String getDescripcionFondo() {
		return descripcionFondo;
	}

	/**
	 * @param descripcionFondo the descripcionFondo to set
	 */
	public void setDescripcionFondo(String descripcionFondo) {
		this.descripcionFondo = descripcionFondo;
	}

	/**
	 * @return the monedaFondo
	 */
	public String getMonedaFondo() {
		return monedaFondo;
	}

	/**
	 * @param monedaFondo the monedaFondo to set
	 */
	public void setMonedaFondo(String monedaFondo) {
		this.monedaFondo = monedaFondo;
	}

	/**
	 * @return the puedeRescatar
	 */
	public boolean isPuedeRescatar() {
		return puedeRescatar;
	}

	/**
	 * @param puedeRescatar the puedeRescatar to set
	 */
	public void setPuedeRescatar(boolean puedeRescatar) {
		this.puedeRescatar = puedeRescatar;
	}

	/**
	 * @return the puedeSuscribir
	 */
	public boolean isPuedeSuscribir() {
		return puedeSuscribir;
	}

	/**
	 * @param puedeSuscribir the puedeSuscribir to set
	 */
	public void setPuedeSuscribir(boolean puedeSuscribir) {
		this.puedeSuscribir = puedeSuscribir;
	}

}

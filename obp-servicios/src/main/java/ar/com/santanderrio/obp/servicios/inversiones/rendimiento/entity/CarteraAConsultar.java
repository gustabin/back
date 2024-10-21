/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class CarteraAConsultar.
 */
public class CarteraAConsultar {
	
	/** The guide error. */
	@JsonProperty("GUIDError")
	private String guideError;
	
	/** The codigo cartera. */
	@JsonProperty("CodigoCartera")
	private String codigoCartera;
	
	/** The descripcion cartera. */
	@JsonProperty("DescripcionCartera")
	private String descripcionCartera;
	
	/** The cuenta. */
	@JsonProperty("Cuenta")
	private SucNroCuenta cuenta;
	
	/** The por defecto. */
	@JsonProperty("PorDefecto")
	private String porDefecto;

	/**
	 * Gets the guide error.
	 *
	 * @return the guide error
	 */
	public String getGuideError() {
		return guideError;
	}

	/**
	 * Sets the guide error.
	 *
	 * @param guideError
	 *            the new guide error
	 */
	public void setGuideError(String guideError) {
		this.guideError = guideError;
	}

	/**
	 * Gets the codigo cartera.
	 *
	 * @return the codigo cartera
	 */
	public String getCodigoCartera() {
		return codigoCartera;
	}

	/**
	 * Sets the codigo cartera.
	 *
	 * @param codigoCartera
	 *            the new codigo cartera
	 */
	public void setCodigoCartera(String codigoCartera) {
		this.codigoCartera = codigoCartera;
	}

	/**
	 * Gets the descripcion cartera.
	 *
	 * @return the descripcion cartera
	 */
	public String getDescripcionCartera() {
		return descripcionCartera;
	}

	/**
	 * Sets the descripcion cartera.
	 *
	 * @param descripcionCartera
	 *            the new descripcion cartera
	 */
	public void setDescripcionCartera(String descripcionCartera) {
		this.descripcionCartera = descripcionCartera;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public SucNroCuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(SucNroCuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the por defecto.
	 *
	 * @return the por defecto
	 */
	public String getPorDefecto() {
		return porDefecto;
	}

	/**
	 * Sets the por defecto.
	 *
	 * @param porDefecto
	 *            the new por defecto
	 */
	public void setPorDefecto(String porDefecto) {
		this.porDefecto = porDefecto;
	}
	
	

}

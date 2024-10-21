/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ListaConsultaCuentaTitulosResponse.
 */
public class ListaConsultaCuentaTitulosResponse {
	
	/** The nro cuenta. */
	@JsonProperty("CtaTitulo")
	private String nroCuenta;
	
	/** The estado cuenta. */
	@JsonProperty("EstadoCuentaTitulo")
	private String estadoCuenta;
	
	/** The operacion disponible. */
	@JsonProperty("OperacionDisponible")
	private String operacionDisponible;

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nroCuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the nroCuenta to set
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the estado cuenta.
	 *
	 * @return the estadoCuenta
	 */
	public String getEstadoCuenta() {
		return estadoCuenta;
	}

	/**
	 * Sets the estado cuenta.
	 *
	 * @param estadoCuenta
	 *            the estadoCuenta to set
	 */
	public void setEstadoCuenta(String estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	/**
	 * Gets the operacion disponible.
	 *
	 * @return the operacionDisponible
	 */
	public String getOperacionDisponible() {
		return operacionDisponible;
	}

	/**
	 * Sets the operacion disponible.
	 *
	 * @param operacionDisponible
	 *            the operacionDisponible to set
	 */
	public void setOperacionDisponible(String operacionDisponible) {
		this.operacionDisponible = operacionDisponible;
	}
	
	

}

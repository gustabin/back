/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dto;

import java.io.Serializable;

/**
 * DTO de recarga de tarjeta.
 */

public class AltaTag implements Serializable {

	/** Versión autogenerado. */
	private static final long serialVersionUID = -8025657193098205169L;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The adicional. */
	private boolean adicional;

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
	 * Checks if is adicional.
	 *
	 * @return true, if is adicional
	 */
	public boolean isAdicional() {
		return adicional;
	}

	/**
	 * Sets the adicional.
	 *
	 * @param adicional
	 *            the new adicional
	 */
	public void setAdicional(boolean adicional) {
		this.adicional = adicional;
	}

}

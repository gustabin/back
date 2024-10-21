/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Objeto utilizado para el Input del FondoSEI.
 * 
 * @author
 *
 */
public class ConfiguracionRescateInView {

	/** The moneda. */
	@NotNull
	@Pattern(regexp = "(u\\$s)|(\\$)")
	private String moneda;

	private String codigoFondo;

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the codigoFondo.
	 *
	 * @return the codigoFondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigoFondo.
	 *
	 * @param codigoFondo
	 *            the new codigoFondo
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

}
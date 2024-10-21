/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * The Class SimulacionTransferenciaInView.
 */
public class SimulacionTransferenciaInView {

	/** The codigo fondo. */
	@NotNull
	private String codigoFondo;

	/** The cuenta titulo. */
	@NotNull
	private String cuentaTitulo;

	/** The importe. */
	@NotNull
	private String importeNeto;

	/** The codigo destino. */
	@NotNull
	private String codigoFondoDest;

	/** The moneda. */
	@NotNull
	@Pattern(regexp = "(u\\$s)|(\\$)")
	private String moneda;

	/**
	 * Gets the codigo fondo dest.
	 *
	 * @return the codigoFondoDest
	 */
	public String getCodigoFondoDest() {
		return codigoFondoDest;
	}

	/**
	 * Sets the codigo fondo dest.
	 *
	 * @param codigoFondoDest
	 *            the codigoFondoDest to set
	 */
	public void setCodigoFondoDest(String codigoFondoDest) {
		this.codigoFondoDest = codigoFondoDest;
	}

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigoFondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the codigoFondo to set
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

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
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the importe neto.
	 *
	 * @return the importeNeto
	 */
	public String getImporteNeto() {
		return importeNeto;
	}

	/**
	 * Sets the importe neto.
	 *
	 * @param importeNeto
	 *            the importeNeto to set
	 */
	public void setImporteNeto(String importeNeto) {
		this.importeNeto = importeNeto;
	}

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuentaTitulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the cuentaTitulo to set
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

}

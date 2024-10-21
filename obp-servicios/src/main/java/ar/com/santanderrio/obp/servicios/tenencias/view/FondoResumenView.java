/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import java.util.List;

/**
 * The Class FondoResumenView.
 */
public class FondoResumenView {

	/** The cuenta. */
	private String cuenta;

	/** The anio. */
	private String anio;

	/** The fondos. */
	private List<FondoView> fondos;

	/** The firmantes. */
	private List<FirmanteView> firmantes;

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio
	 *            the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * Gets the fondos.
	 *
	 * @return the fondos
	 */
	public List<FondoView> getFondos() {
		return fondos;
	}

	/**
	 * Sets the fondos.
	 *
	 * @param fondos
	 *            the fondos to set
	 */
	public void setFondos(List<FondoView> fondos) {
		this.fondos = fondos;
	}

	/**
	 * Gets the firmantes.
	 *
	 * @return the firmantes
	 */
	public List<FirmanteView> getFirmantes() {
		return firmantes;
	}

	/**
	 * Sets the firmantes.
	 *
	 * @param firmantes
	 *            the firmantes to set
	 */
	public void setFirmantes(List<FirmanteView> firmantes) {
		this.firmantes = firmantes;
	}

}

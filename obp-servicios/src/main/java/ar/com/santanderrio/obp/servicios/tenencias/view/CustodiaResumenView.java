/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import java.util.List;

/**
 * The Class CustodiaResumenView.
 */
public class CustodiaResumenView {

	/** The cuenta. */
	private String cuenta;

	/** The anio. */
	private String anio;

	/** The custodias. */
	private List<CustodiaView> custodias;

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
	 * Gets the custodias.
	 *
	 * @return the custodias
	 */
	public List<CustodiaView> getCustodias() {
		return custodias;
	}

	/**
	 * Sets the custodias.
	 *
	 * @param custodias
	 *            the custodias to set
	 */
	public void setCustodias(List<CustodiaView> custodias) {
		this.custodias = custodias;
	}

}

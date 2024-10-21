/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

import java.util.List;

/**
 * The Class GrupoCajaHomeView.
 */
public class GrupoCajaHomeView {
	/** The cajas. */
	private List<Caja> cajas;

	/** The sin error. */
	private Boolean sinError = Boolean.TRUE;

	/**
	 * Gets the cajas.
	 *
	 * @return the cajas
	 */
	public List<Caja> getCajas() {
		return cajas;
	}

	/**
	 * Sets the cajas.
	 *
	 * @param cajas
	 *            the new cajas
	 */
	public void setCajas(List<Caja> cajas) {
		this.cajas = cajas;
	}

	/**
	 * Gets the sin error.
	 *
	 * @return the sinError
	 */
	public Boolean getSinError() {
		return sinError;
	}

	/**
	 * Sets the sin error.
	 *
	 * @param sinError
	 *            the sinError to set
	 */
	public void setSinError(Boolean sinError) {
		this.sinError = sinError;
	}

}

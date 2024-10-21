/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities;

import java.util.List;

/**
 * The Class LimitesTarjetaBanelco.
 */
public class LimitesTarjetaBanelco {

	/** The limites. */
	private List<LimiteClase> limites;

	/**
	 * Instantiates a new limites tarjeta banelco.
	 *
	 * @param limites
	 *            the limites
	 */
	public LimitesTarjetaBanelco(List<LimiteClase> limites) {
		super();
		this.limites = limites;
	}

	/**
	 * Gets the limites.
	 *
	 * @return the limites
	 */
	public List<LimiteClase> getLimites() {
		return limites;
	}

	/**
	 * Sets the limites.
	 *
	 * @param limites
	 *            the new limites
	 */
	public void setLimites(List<LimiteClase> limites) {
		this.limites = limites;
	}

}

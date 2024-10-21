/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

/**
 * The Class ComprobanteSuscripcionView.
 */
public class ComprobanteSuscripcionView {

	/** The legales. */
	private String legales;

	/** The seuo. */
	private final String seuo = "Conserve este ticket como<br/>comprobante S.E.U.O.";

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the seuo.
	 *
	 * @return the seuo
	 */
	public String getSeuo() {
		return seuo;
	}

}

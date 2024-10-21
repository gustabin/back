/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

/**
 * The Class SimulacionOutView.
 */
public abstract class SimulacionOutView {

	/** The disclaimer. */
	private String disclaimer;

	/** legales. */
	private String legales;

	/** The legales Super Ahorro Pesos */
	private String legalSuperAhorroPesos;

	/**
	 * Gets the disclaimer.
	 *
	 * @return the disclaimer
	 */
	public String getDisclaimer() {
		return disclaimer;
	}

	/**
	 * Sets the disclaimer.
	 *
	 * @param disclaimer
	 *            the new disclaimer
	 */
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

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
	 * Gets the legalSuperAhorroPesos.
	 *
	 * @return the legalSuperAhorroPesos
	 */
	public String getLegalSuperAhorroPesos() {
		return legalSuperAhorroPesos;
	}

	/**
	 * Sets the legalSuperAhorroPesos.
	 *
	 * @param legalSuperAhorroPesos
	 *            the new legalSuperAhorroPesos
	 */
	public void setLegalSuperAhorroPesos(String legalSuperAhorroPesos) {
		this.legalSuperAhorroPesos = legalSuperAhorroPesos;
	}

}

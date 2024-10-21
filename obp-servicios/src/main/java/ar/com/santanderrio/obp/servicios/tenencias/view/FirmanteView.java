/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class FirmanteView.
 */
public class FirmanteView extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The anio. */
	private String anio;

	/** The cuenta. */
	private int cuenta;

	/** The pecodofi. */
	private String pecodofi;

	/** The pepriape. */
	private String pepriape;

	/** The penomper. */
	private String penomper;

	/** The petidoc. */
	private String petidoc;

	/** The penumdoc. */
	private String penumdoc;

	/** The penumcom. */
	private String penumcom;

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
	 * Gets the pecodofi.
	 *
	 * @return the pecodofi
	 */
	public String getPecodofi() {
		return pecodofi;
	}

	/**
	 * Sets the pecodofi.
	 *
	 * @param pecodofi
	 *            the pecodofi to set
	 */
	public void setPecodofi(String pecodofi) {
		this.pecodofi = pecodofi;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public int getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the pepriape.
	 *
	 * @return the pepriape
	 */
	public String getPepriape() {
		return pepriape;
	}

	/**
	 * Sets the pepriape.
	 *
	 * @param pepriape
	 *            the pepriape to set
	 */
	public void setPepriape(String pepriape) {
		this.pepriape = pepriape;
	}

	/**
	 * Gets the penomper.
	 *
	 * @return the penomper
	 */
	public String getPenomper() {
		return penomper;
	}

	/**
	 * Sets the penomper.
	 *
	 * @param penomper
	 *            the penomper to set
	 */
	public void setPenomper(String penomper) {
		this.penomper = penomper;
	}

	/**
	 * Gets the petidoc.
	 *
	 * @return the petidoc
	 */
	public String getPetidoc() {
		return petidoc;
	}

	/**
	 * Sets the petidoc.
	 *
	 * @param petidoc
	 *            the petidoc to set
	 */
	public void setPetidoc(String petidoc) {
		this.petidoc = petidoc;
	}

	/**
	 * Gets the penumdoc.
	 *
	 * @return the penumdoc
	 */
	public String getPenumdoc() {
		return penumdoc;
	}

	/**
	 * Sets the penumdoc.
	 *
	 * @param penumdoc
	 *            the penumdoc to set
	 */
	public void setPenumdoc(String penumdoc) {
		this.penumdoc = penumdoc;
	}

	/**
	 * Gets the penumcom.
	 *
	 * @return the penumcom
	 */
	public String getPenumcom() {
		return penumcom;
	}

	/**
	 * Sets the penumcom.
	 *
	 * @param penumcom
	 *            the penumcom to set
	 */
	public void setPenumcom(String penumcom) {
		this.penumcom = penumcom;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Firmante [anio=" + anio + ", cuenta=" + cuenta + ", pecodofi=" + pecodofi + ", pepriape=" + pepriape
				+ ", penomper=" + penomper + ", petidoc=" + petidoc + ", penumdoc=" + penumdoc + ", penumcom="
				+ penumcom + "]";
	}

}

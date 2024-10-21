/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import org.beanio.annotation.Field;

/**
 * The Class TasaIndicativa.
 */
public class TasaIndicativa {

	/** The plazo. */
	@Field
	private String plazo;
	
	/** The tasa. */
	@Field
	private String tasa;
	
	/** The spread S. */
	@Field
	private String spreadS;
	
	/** The monto limite S. */
	@Field
	private String montoLimiteS;
	
	/** The comision adicional S. */
	@Field
	private String comisionAdicionalS;

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the new plazo
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the tasa.
	 *
	 * @return the tasa
	 */
	public String getTasa() {
		return tasa;
	}

	/**
	 * Sets the tasa.
	 *
	 * @param tasa
	 *            the new tasa
	 */
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}

	/**
	 * Gets the spread S.
	 *
	 * @return the spread S
	 */
	public String getSpreadS() {
		return spreadS;
	}

	/**
	 * Sets the spread S.
	 *
	 * @param spreadS
	 *            the new spread S
	 */
	public void setSpreadS(String spreadS) {
		this.spreadS = spreadS;
	}

	/**
	 * Gets the monto limite S.
	 *
	 * @return the monto limite S
	 */
	public String getMontoLimiteS() {
		return montoLimiteS;
	}

	/**
	 * Sets the monto limite S.
	 *
	 * @param montoLimiteS
	 *            the new monto limite S
	 */
	public void setMontoLimiteS(String montoLimiteS) {
		this.montoLimiteS = montoLimiteS;
	}

	/**
	 * Gets the comision adicional S.
	 *
	 * @return the comision adicional S
	 */
	public String getComisionAdicionalS() {
		return comisionAdicionalS;
	}

	/**
	 * Sets the comision adicional S.
	 *
	 * @param comisionAdicionalS
	 *            the new comision adicional S
	 */
	public void setComisionAdicionalS(String comisionAdicionalS) {
		this.comisionAdicionalS = comisionAdicionalS;
	}
	
	
}

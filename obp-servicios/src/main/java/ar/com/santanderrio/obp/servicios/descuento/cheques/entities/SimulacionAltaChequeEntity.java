/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import org.beanio.annotation.Field;

/**
 * The Class SimulacionAltaChequeEntity.
 */
public class SimulacionAltaChequeEntity extends OperacionesPrecargadasEntity{

	/** The porc interes adel. */
	@Field
	private String porcInteresAdel;
	
	/**
	 * Gets the porc interes adel.
	 *
	 * @return the porc interes adel
	 */
	public String getPorcInteresAdel() {
		return porcInteresAdel;
	}

	/**
	 * Sets the porc interes adel.
	 *
	 * @param porcInteresAdel
	 *            the new porc interes adel
	 */
	public void setPorcInteresAdel(String porcInteresAdel) {
		this.porcInteresAdel = porcInteresAdel;
	}
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import org.beanio.annotation.Field;

/**
 * The Class DetalleOperacionPrecargadaEntity.
 */
public class DetalleOperacionPrecargadaEntity extends OperacionesPrecargadasEntity{

	
	/** The motivo ret SS. */
	@Field
	private String motivoRetSS;
	
	/** The porc interes adel. */
	@Field
	private String porcInteresAdel;


	/**
	 * Gets the motivo ret SS.
	 *
	 * @return the motivo ret SS
	 */
	public String getMotivoRetSS() {
		return motivoRetSS;
	}

	/**
	 * Sets the motivo ret SS.
	 *
	 * @param motivoRetSS
	 *            the new motivo ret SS
	 */
	public void setMotivoRetSS(String motivoRetSS) {
		this.motivoRetSS = motivoRetSS;
	}

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

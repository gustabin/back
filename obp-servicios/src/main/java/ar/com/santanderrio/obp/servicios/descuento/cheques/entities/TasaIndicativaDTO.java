/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.math.BigDecimal;

/**
 * The Class TasaIndicativaDTO.
 */
public class TasaIndicativaDTO {

	/** The plazo. */
	private Integer plazo;
	
	/** The tasa. */
	private BigDecimal tasa;

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public Integer getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param integer
	 *            the new plazo
	 */
	public void setPlazo(Integer integer) {
		this.plazo = integer;
	}

	/**
	 * Gets the tasa.
	 *
	 * @return the tasa
	 */
	public BigDecimal getTasa() {
		return tasa;
	}

	/**
	 * Sets the tasa.
	 *
	 * @param tasa
	 *            the new tasa
	 */
	public void setTasa(BigDecimal tasa) {
		this.tasa = tasa;
	}
	
	
}

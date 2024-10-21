/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.math.BigDecimal;

/**
 * The Class PorcentajesGrillaRendimiento.
 */
public class PorcentajesGrillaRendimiento {
	
	/** The porcentaje parcial. */
	private BigDecimal porcentajeParcial = new BigDecimal("0.00");
	
	/** The porcentaje. */
	private String porcentaje;

	/**
	 * Gets the porcentaje parcial.
	 *
	 * @return the porcentaje parcial
	 */
	public BigDecimal getPorcentajeParcial() {
		return porcentajeParcial;
	}

	/**
	 * Sets the porcentaje parcial.
	 *
	 * @param porcentajeParcial
	 *            the new porcentaje parcial
	 */
	public void setPorcentajeParcial(BigDecimal porcentajeParcial) {
		this.porcentajeParcial = porcentajeParcial;
	}

	/**
	 * Gets the porcentaje.
	 *
	 * @return the porcentaje
	 */
	public String getPorcentaje() {
		return porcentaje;
	}

	/**
	 * Sets the porcentaje.
	 *
	 * @param porcentaje
	 *            the new porcentaje
	 */
	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}
	
}

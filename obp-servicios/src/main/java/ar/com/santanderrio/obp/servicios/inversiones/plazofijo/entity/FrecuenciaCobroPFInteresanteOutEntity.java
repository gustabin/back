/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.beanio.annotation.Field;

/**
 * The Class FrecuenciaCobroPFInteresanteOutEntity.
 */
public class FrecuenciaCobroPFInteresanteOutEntity {

	/** The fecha liquidacion. */
	@Field
	private String fechaLiquidacion;
	
	/** The fecha prox liquidacion. */
	@Field
	private String fechaProxLiquidacion;
	
	/** The capital. */
	@Field
	private String capital;
	
	/** The intereses. */
	@Field
	private String intereses;
	
	/** The impuestos. */
	@Field
	private String impuestos;
	
	/** The total. */
	@Field
	private String total;

	
	/**
	 * Gets the fecha liquidacion.
	 *
	 * @return the fecha liquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the fecha liquidacion.
	 *
	 * @param fechaLiquidacion
	 *            the new fecha liquidacion
	 */
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the new capital
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * Gets the intereses.
	 *
	 * @return the intereses
	 */
	public String getIntereses() {
		return intereses;
	}

	/**
	 * Sets the intereses.
	 *
	 * @param intereses
	 *            the new intereses
	 */
	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}

	/**
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public String getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos
	 *            the new impuestos
	 */
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total
	 *            the new total
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * Gets the fecha prox liquidacion.
	 *
	 * @return the fecha prox liquidacion
	 */
	public String getFechaProxLiquidacion() {
		return fechaProxLiquidacion;
	}

	/**
	 * Sets the fecha prox liquidacion.
	 *
	 * @param fechaProxLiquidacion
	 *            the new fecha prox liquidacion
	 */
	public void setFechaProxLiquidacion(String fechaProxLiquidacion) {
		this.fechaProxLiquidacion = fechaProxLiquidacion;
	}
		
}

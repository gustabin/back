/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto;

import java.math.BigDecimal;

/**
 * The Class VariacionInfoMercadoDTO.
 */
public class VariacionInfoMercadoDTO {
	
	/** The variacion hoy. */
	private BigDecimal variacionHoy;
	
	/** The variacion mensual. */
	private BigDecimal variacionMensual;
	
	/** The variacion anual. */
	private BigDecimal variacionAnual;
	
	/**
	 * Gets the variacion hoy.
	 *
	 * @return the variacionHoy
	 */
	public BigDecimal getVariacionHoy() {
		return variacionHoy;
	}
	
	/**
	 * Sets the variacion hoy.
	 *
	 * @param variacionHoy
	 *            the variacionHoy to set
	 */
	public void setVariacionHoy(BigDecimal variacionHoy) {
		this.variacionHoy = variacionHoy;
	}
	
	/**
	 * Gets the variacion mensual.
	 *
	 * @return the variacionMensual
	 */
	public BigDecimal getVariacionMensual() {
		return variacionMensual;
	}
	
	/**
	 * Sets the variacion mensual.
	 *
	 * @param variacionMensual
	 *            the variacionMensual to set
	 */
	public void setVariacionMensual(BigDecimal variacionMensual) {
		this.variacionMensual = variacionMensual;
	}
	
	/**
	 * Gets the variacion anual.
	 *
	 * @return the variacionAnual
	 */
	public BigDecimal getVariacionAnual() {
		return variacionAnual;
	}
	
	/**
	 * Sets the variacion anual.
	 *
	 * @param variacionAnual
	 *            the variacionAnual to set
	 */
	public void setVariacionAnual(BigDecimal variacionAnual) {
		this.variacionAnual = variacionAnual;
	}
	
	

}

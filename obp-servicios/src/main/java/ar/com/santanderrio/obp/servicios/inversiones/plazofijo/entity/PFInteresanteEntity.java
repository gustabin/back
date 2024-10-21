/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;

/**
 * The Class PFInteresanteEntity.
 *
 * @author juan.pablo.picate
 */
public class PFInteresanteEntity {

	/** The fecha ultima liquidacion. */
	@Field
	private String fechaUltimaLiquidacion;

	/** The fecha proxima liquidacion. */
	@Field
	private String fechaProximaLiquidacion;

	/**
	 * The capital. 13 enteros, 2 decimales
	 */
	@Field
	private String capital;

	/** The intereses 13 enteros, 2 decimales. */
	@Field
	private String intereses;

	/** The impuesto 13 enteros, 2 decimales. */
	@Field
	private String impuesto;

	/** The total 13 enteros, 2 decimales. */
	@Field
	private String total;

	/**
	 * Gets the fecha ultima liquidacion.
	 *
	 * @return the fechaUltimaLiquidacion
	 */
	public String getFechaUltimaLiquidacion() {
		return fechaUltimaLiquidacion;
	}

	/**
	 * Sets the fecha ultima liquidacion.
	 *
	 * @param fechaUltimaLiquidacion
	 *            the fechaUltimaLiquidacion to set
	 */
	public void setFechaUltimaLiquidacion(String fechaUltimaLiquidacion) {
		this.fechaUltimaLiquidacion = fechaUltimaLiquidacion;
	}

	/**
	 * Gets the fecha proxima liquidacion.
	 *
	 * @return the fechaProximaLiquidacion
	 */
	public String getFechaProximaLiquidacion() {
		return fechaProximaLiquidacion;
	}

	/**
	 * Sets the fecha proxima liquidacion.
	 *
	 * @param fechaProximaLiquidacion
	 *            the fechaProximaLiquidacion to set
	 */
	public void setFechaProximaLiquidacion(String fechaProximaLiquidacion) {
		this.fechaProximaLiquidacion = fechaProximaLiquidacion;
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
	 *            the capital to set
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
	 *            the intereses to set
	 */
	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}

	/**
	 * Gets the impuesto.
	 *
	 * @return the impuesto
	 */
	public String getImpuesto() {
		return impuesto;
	}

	/**
	 * Sets the impuesto.
	 *
	 * @param impuesto
	 *            the impuesto to set
	 */
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
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
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				// .append("headerTrama", headerTrama)
				.toString();
	}

}

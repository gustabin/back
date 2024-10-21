/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;

/**
 * The Class ImposicionInteresantePlazoFijoEntity.
 *
 * @author juan.pablo.picate
 */
public class ImposicionInteresantePlazoFijoEntity {

	/** AAAAMMDD. */
	@Field
	private String fechaProximaLiquidacion;

	/** 13 enteros, 2 decimales. */
	@Field
	private String capital;

	/** 13 enteros, 2 decimales. */
	@Field
	private String interes;

	/** 13 enteros, 2 decimales. */
	@Field
	private String impuestos;

	/** 13 enteros, 2 decimales. */
	@Field
	private String total;

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
	 * Gets the interes.
	 *
	 * @return the interes
	 */
	public String getInteres() {
		return interes;
	}

	/**
	 * Sets the interes.
	 *
	 * @param interes
	 *            the interes to set
	 */
	public void setInteres(String interes) {
		this.interes = interes;
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
	 *            the impuestos to set
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
		return new ToStringBuilder(this).toString();
	}

}

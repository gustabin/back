/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ResultadoComparativaCartera.
 */
public class ResultadoComparativaCartera {

	/** The guid error. */
	@JsonProperty("GUIDError")
	private String guidError;
	
	/** The fecha. */
	@JsonProperty("Fecha")
	private String fecha;

	/** The rendimiento. */
	@JsonProperty("Rendimiento")
	private int rendimiento;

	/**
	 * Gets the guid error.
	 *
	 * @return the guid error
	 */
	public String getGuidError() {
		return guidError;
	}

	/**
	 * Sets the guid error.
	 *
	 * @param guidError
	 *            the new guid error
	 */
	public void setGuidError(String guidError) {
		this.guidError = guidError;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the rendimiento.
	 *
	 * @return the rendimiento
	 */
	public int getRendimiento() {
		return rendimiento;
	}

	/**
	 * Sets the rendimiento.
	 *
	 * @param rendimiento
	 *            the new rendimiento
	 */
	public void setRendimiento(int rendimiento) {
		this.rendimiento = rendimiento;
	}
	
	
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ResultadoRendimientoTenenciaEntity.
 */
public class ResultadoRendimientoTenenciaEntity {

	/** The guid error. */
	@JsonProperty("GUIDError")
	private String guidError;

	/** The CodigoPeriodo. */
	@JsonProperty("CodigoPeriodo")
	private String codigoPeriodo;

	/** The DescripcionPeriodo. */
	@JsonProperty("DescripcionPeriodo")
	private String descripcionPeriodo;
	
	/** The FechaInicio. */
	@JsonProperty("FechaInicio")
	private String fechaInicio;
	
	/** The FechaFin. */
	@JsonProperty("FechaFin")
	private String fechaFin;
	
	/** The RendimientoPesos. */
	@JsonProperty("RendimientoPesos")
	private String rendimientoPesos;
	
	/** The RendimientoDolares. */
	@JsonProperty("RendimientoDolares")
	private String rendimientoDolares;
	
	/** The RentabilidadPesos. */
	@JsonProperty("RentabilidadPesos")
	private String rentabilidadPesos;
	
	/** The RentabilidadDolares. */
	@JsonProperty("RentabilidadDolares")
	private String rentabilidadDolares;

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
	 * Gets the codigo periodo.
	 *
	 * @return the codigo periodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * Sets the codigo periodo.
	 *
	 * @param codigoPeriodo
	 *            the new codigo periodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * Gets the descripcion periodo.
	 *
	 * @return the descripcion periodo
	 */
	public String getDescripcionPeriodo() {
		return descripcionPeriodo;
	}

	/**
	 * Sets the descripcion periodo.
	 *
	 * @param descripcionPeriodo
	 *            the new descripcion periodo
	 */
	public void setDescripcionPeriodo(String descripcionPeriodo) {
		this.descripcionPeriodo = descripcionPeriodo;
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio
	 *            the new fecha inicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Gets the fecha fin.
	 *
	 * @return the fecha fin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * Sets the fecha fin.
	 *
	 * @param fechaFin
	 *            the new fecha fin
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Gets the rendimiento pesos.
	 *
	 * @return the rendimiento pesos
	 */
	public String getRendimientoPesos() {
		return rendimientoPesos;
	}

	/**
	 * Sets the rendimiento pesos.
	 *
	 * @param rendimientoPesos
	 *            the new rendimiento pesos
	 */
	public void setRendimientoPesos(String rendimientoPesos) {
		this.rendimientoPesos = rendimientoPesos;
	}

	/**
	 * Gets the rendimiento dolares.
	 *
	 * @return the rendimiento dolares
	 */
	public String getRendimientoDolares() {
		return rendimientoDolares;
	}

	/**
	 * Sets the rendimiento dolares.
	 *
	 * @param rendimientoDolares
	 *            the new rendimiento dolares
	 */
	public void setRendimientoDolares(String rendimientoDolares) {
		this.rendimientoDolares = rendimientoDolares;
	}

	/**
	 * Gets the rentabilidad pesos.
	 *
	 * @return the rentabilidad pesos
	 */
	public String getRentabilidadPesos() {
		return rentabilidadPesos;
	}

	/**
	 * Sets the rentabilidad pesos.
	 *
	 * @param rentabilidadPesos
	 *            the new rentabilidad pesos
	 */
	public void setRentabilidadPesos(String rentabilidadPesos) {
		this.rentabilidadPesos = rentabilidadPesos;
	}

	/**
	 * Gets the rentabilidad dolares.
	 *
	 * @return the rentabilidad dolares
	 */
	public String getRentabilidadDolares() {
		return rentabilidadDolares;
	}

	/**
	 * Sets the rentabilidad dolares.
	 *
	 * @param rentabilidadDolares
	 *            the new rentabilidad dolares
	 */
	public void setRentabilidadDolares(String rentabilidadDolares) {
		this.rentabilidadDolares = rentabilidadDolares;
	}
}

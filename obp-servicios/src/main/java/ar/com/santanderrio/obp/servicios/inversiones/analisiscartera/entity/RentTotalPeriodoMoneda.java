/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class RentTotalPeriodoMoneda.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentTotalPeriodoMoneda {

	/** The guid error. */
	@JsonProperty("GUIDError")
	private String guidError;
	
	/** The codigo periodo. */
	@JsonProperty("CodigoPeriodo")
	private String codigoPeriodo;
	
	/** The descripcion periodo. */
	@JsonProperty("DescripcionPeriodo")
	private String descripcionPeriodo;
	
	/** The fecha inicio. */
	@JsonProperty("FechaInicio")
	private String fechaInicio;
	
	/** The fecha fin. */
	@JsonProperty("FechaFin")
	private String fechaFin;
	
	/** The rentabilidad pesos. */
	@JsonProperty("RentabilidadPesos")
	private BigDecimal rentabilidadPesos;
	
	/** The rentabilidad dolares. */
	@JsonProperty("RentabilidadDolares")
	private BigDecimal rentabilidadDolares;
	
	/** The fecha info disponible. */
	@JsonProperty("FechaInfoDisponible")
	private String fechaInfoDisponible;
	
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
	 * Gets the rentabilidad pesos.
	 *
	 * @return the rentabilidad pesos
	 */
	public BigDecimal getRentabilidadPesos() {
		return rentabilidadPesos;
	}

	/**
	 * Sets the rentabilidad pesos.
	 *
	 * @param rentabilidadPesos
	 *            the new rentabilidad pesos
	 */
	public void setRentabilidadPesos(BigDecimal rentabilidadPesos) {
		this.rentabilidadPesos = rentabilidadPesos;
	}

	/**
	 * Gets the rentabilidad dolares.
	 *
	 * @return the rentabilidad dolares
	 */
	public BigDecimal getRentabilidadDolares() {
		return rentabilidadDolares;
	}

	/**
	 * Sets the rentabilidad dolares.
	 *
	 * @param rentabilidadDolares
	 *            the new rentabilidad dolares
	 */
	public void setRentabilidadDolares(BigDecimal rentabilidadDolares) {
		this.rentabilidadDolares = rentabilidadDolares;
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
	 * Gets the fecha info disponible.
	 *
	 * @return the fecha info disponible
	 */
	public String getFechaInfoDisponible() {
		return fechaInfoDisponible;
	}

	/**
	 * Sets the fecha info disponible.
	 *
	 * @param fechaInfoDisponible
	 *            the new fecha info disponible
	 */
	public void setFechaInfoDisponible(String fechaInfoDisponible) {
		this.fechaInfoDisponible = fechaInfoDisponible;
	}
	
	
}

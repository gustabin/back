/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DetallePorSubCabecera.
 */
public class DetallePorSubCabecera {

	/** The guidError. */
	@JsonProperty("GUID")
	private String guidError;
	
	/** The Codigo Sub Cabecera. */
	@JsonProperty("CodigoSubCabecera")
	private String codigoSubCabecera;
	
	/** The Descripcion SubCabecera. */
	@JsonProperty("DescripcionSubCabecera")
	private String descripcionSubCabecera;
	
	/** The Rentabilidad. */
	@JsonProperty("Rentabilidad")
	private BigDecimal rentabilidad;
	
	/** The Rendimiento. */
	@JsonProperty("Rendimiento")
	private BigDecimal rendimiento;
	
	/** The Resultado Detalle. */
	@JsonProperty("ResultadoDetalle")
	private List<DetallePorItem> resultadoDetalle;

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
	 * Gets the codigo sub cabecera.
	 *
	 * @return the codigo sub cabecera
	 */
	public String getCodigoSubCabecera() {
		return codigoSubCabecera;
	}

	/**
	 * Sets the codigo sub cabecera.
	 *
	 * @param codigoSubCabecera
	 *            the new codigo sub cabecera
	 */
	public void setCodigoSubCabecera(String codigoSubCabecera) {
		this.codigoSubCabecera = codigoSubCabecera;
	}

	/**
	 * Gets the descripcion sub cabecera.
	 *
	 * @return the descripcion sub cabecera
	 */
	public String getDescripcionSubCabecera() {
		return descripcionSubCabecera;
	}

	/**
	 * Sets the descripcion sub cabecera.
	 *
	 * @param descripcionSubCabecera
	 *            the new descripcion sub cabecera
	 */
	public void setDescripcionSubCabecera(String descripcionSubCabecera) {
		this.descripcionSubCabecera = descripcionSubCabecera;
	}

	/**
	 * Gets the rentabilidad.
	 *
	 * @return the rentabilidad
	 */
	public BigDecimal getRentabilidad() {
		return rentabilidad;
	}

	/**
	 * Sets the rentabilidad.
	 *
	 * @param rentabilidad
	 *            the new rentabilidad
	 */
	public void setRentabilidad(BigDecimal rentabilidad) {
		this.rentabilidad = rentabilidad;
	}

	/**
	 * Gets the rendimiento.
	 *
	 * @return the rendimiento
	 */
	public BigDecimal getRendimiento() {
		return rendimiento;
	}

	/**
	 * Sets the rendimiento.
	 *
	 * @param rendimiento
	 *            the new rendimiento
	 */
	public void setRendimiento(BigDecimal rendimiento) {
		this.rendimiento = rendimiento;
	}

	/**
	 * Gets the resultado detalle.
	 *
	 * @return the resultado detalle
	 */
	public List<DetallePorItem> getResultadoDetalle() {
		return resultadoDetalle;
	}

	/**
	 * Sets the resultado detalle.
	 *
	 * @param resultadoDetalle
	 *            the new resultado detalle
	 */
	public void setResultadoDetalle(List<DetallePorItem> resultadoDetalle) {
		this.resultadoDetalle = resultadoDetalle;
	}
	
}

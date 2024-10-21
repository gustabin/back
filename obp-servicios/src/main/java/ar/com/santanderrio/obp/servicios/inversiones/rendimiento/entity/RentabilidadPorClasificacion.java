/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class RentabilidadPorClasificacion.
 */
public class RentabilidadPorClasificacion {
	
	/** The guid. */
	@JsonProperty("GUID")
	private String guid;
	
	/** The codigo subclasificacion. */
	@JsonProperty("CodigoSubclasificacion")
	private String codigoSubclasificacion;
	
	/** The descripcion subclasificacion. */
	@JsonProperty("DescripcionSubclasificacion")
	private String descripcionSubclasificacion;
	
	/** The rentabilidad neta. */
	@JsonProperty("RentabilidadNeta")
	private BigDecimal rentabilidadNeta;
	
	/** The distribucion. */
	@JsonProperty("Distribucion")
	private BigDecimal distribucion;

	/**
	 * Gets the guid.
	 *
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * Sets the guid.
	 *
	 * @param guid
	 *            the new guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * Gets the codigo subclasificacion.
	 *
	 * @return the codigo subclasificacion
	 */
	public String getCodigoSubclasificacion() {
		return codigoSubclasificacion;
	}

	/**
	 * Sets the codigo subclasificacion.
	 *
	 * @param codigoSubclasificacion
	 *            the new codigo subclasificacion
	 */
	public void setCodigoSubclasificacion(String codigoSubclasificacion) {
		this.codigoSubclasificacion = codigoSubclasificacion;
	}

	/**
	 * Gets the descripcion subclasificacion.
	 *
	 * @return the descripcion subclasificacion
	 */
	public String getDescripcionSubclasificacion() {
		return descripcionSubclasificacion;
	}

	/**
	 * Sets the descripcion subclasificacion.
	 *
	 * @param descripcionSubclasificacion
	 *            the new descripcion subclasificacion
	 */
	public void setDescripcionSubclasificacion(String descripcionSubclasificacion) {
		this.descripcionSubclasificacion = descripcionSubclasificacion;
	}

	/**
	 * Gets the rentabilidad neta.
	 *
	 * @return the rentabilidad neta
	 */
	public BigDecimal getRentabilidadNeta() {
		return rentabilidadNeta;
	}

	/**
	 * Sets the rentabilidad neta.
	 *
	 * @param rentabilidadNeta
	 *            the new rentabilidad neta
	 */
	public void setRentabilidadNeta(BigDecimal rentabilidadNeta) {
		this.rentabilidadNeta = rentabilidadNeta;
	}

	/**
	 * Gets the distribucion.
	 *
	 * @return the distribucion
	 */
	public BigDecimal getDistribucion() {
		return distribucion;
	}

	/**
	 * Sets the distribucion.
	 *
	 * @param distribucion
	 *            the new distribucion
	 */
	public void setDistribucion(BigDecimal distribucion) {
		this.distribucion = distribucion;
	}

}

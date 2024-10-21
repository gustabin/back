/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class GraficoRentabilidadRespuesta.
 */
public class GraficoRentabilidadRespuesta {
	
	/** The descripcion periodo agrup. */
	@JsonProperty("DescripcionPeriodoAgrup")
	private String descripcionPeriodoAgrup;
	
	/** The rentabilidad realizada. */
	@JsonProperty("RentabilidadRealizada")
	private BigDecimal rentabilidadRealizada;
	
	/** The rentabilidad no realizada. */
	@JsonProperty("RentabilidadNoRealizada")
	private BigDecimal rentabilidadNoRealizada;
	
	/** The rentabilidad neta. */
	@JsonProperty("RentabilidadNeta")
	private BigDecimal rentabilidadNeta;
	
	/** The guid error. */
	@JsonProperty("GUIDError")
	private String guidError;
	
	/** The codigo cabecera. */
	@JsonProperty("CodigoCabecera")
	private String codigoCabecera;
	
	/** The descripcion cabecera. */
	@JsonProperty("DescripcionCabecera")
	private String descripcionCabecera;

	/**
	 * Gets the descripcion periodo agrup.
	 *
	 * @return the descripcion periodo agrup
	 */
	public String getDescripcionPeriodoAgrup() {
		return descripcionPeriodoAgrup;
	}

	/**
	 * Sets the descripcion periodo agrup.
	 *
	 * @param descripcionPeriodoAgrup
	 *            the new descripcion periodo agrup
	 */
	public void setDescripcionPeriodoAgrup(String descripcionPeriodoAgrup) {
		this.descripcionPeriodoAgrup = descripcionPeriodoAgrup;
	}

	/**
	 * Gets the rentabilidad realizada.
	 *
	 * @return the rentabilidad realizada
	 */
	public BigDecimal getRentabilidadRealizada() {
		return rentabilidadRealizada;
	}

	/**
	 * Sets the rentabilidad realizada.
	 *
	 * @param rentabilidadRealizada
	 *            the new rentabilidad realizada
	 */
	public void setRentabilidadRealizada(BigDecimal rentabilidadRealizada) {
		this.rentabilidadRealizada = rentabilidadRealizada;
	}

	/**
	 * Gets the rentabilidad no realizada.
	 *
	 * @return the rentabilidad no realizada
	 */
	public BigDecimal getRentabilidadNoRealizada() {
		return rentabilidadNoRealizada;
	}

	/**
	 * Sets the rentabilidad no realizada.
	 *
	 * @param rentabilidadNoRealizada
	 *            the new rentabilidad no realizada
	 */
	public void setRentabilidadNoRealizada(BigDecimal rentabilidadNoRealizada) {
		this.rentabilidadNoRealizada = rentabilidadNoRealizada;
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
	 * Gets the codigo cabecera.
	 *
	 * @return the codigo cabecera
	 */
	public String getCodigoCabecera() {
		return codigoCabecera;
	}

	/**
	 * Sets the codigo cabecera.
	 *
	 * @param codigoCabecera
	 *            the new codigo cabecera
	 */
	public void setCodigoCabecera(String codigoCabecera) {
		this.codigoCabecera = codigoCabecera;
	}

	/**
	 * Gets the descripcion cabecera.
	 *
	 * @return the descripcion cabecera
	 */
	public String getDescripcionCabecera() {
		return descripcionCabecera;
	}

	/**
	 * Sets the descripcion cabecera.
	 *
	 * @param descripcionCabecera
	 *            the new descripcion cabecera
	 */
	public void setDescripcionCabecera(String descripcionCabecera) {
		this.descripcionCabecera = descripcionCabecera;
	}
	
}

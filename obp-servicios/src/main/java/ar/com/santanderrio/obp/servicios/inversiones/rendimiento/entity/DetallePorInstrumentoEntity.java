/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DetallePorInstrumentoEntity.
 */
public class DetallePorInstrumentoEntity {

	/** The codigo instrumento. */
	@JsonProperty("CodigoInstrumento")
	private String codigoInstrumento;
	
	/** The descripcion instrumento. */
	@JsonProperty("DescripcionInstrumento")
	private String descripcionInstrumento;
	
	/** The rentabilidad neta. */
	@JsonProperty("RentabilidadNeta")
	private BigDecimal rentabilidadNeta;
	
	/** The rendimiento. */
	@JsonProperty("Rendimiento")
	private BigDecimal rendimiento;
	
	/** The tna. */
	@JsonProperty("TNA")
	private BigDecimal tna;
	
	/** The alias instrumento. */
	@JsonProperty("AliasInstrumento")
	private String aliasInstrumento;

	
	/**
	 * Gets the codigo instrumento.
	 *
	 * @return the codigo instrumento
	 */
	public String getCodigoInstrumento() {
		return codigoInstrumento;
	}

	/**
	 * Sets the codigo instrumento.
	 *
	 * @param codigoInstrumento
	 *            the new codigo instrumento
	 */
	public void setCodigoInstrumento(String codigoInstrumento) {
		this.codigoInstrumento = codigoInstrumento;
	}

	/**
	 * Gets the descripcion instrumento.
	 *
	 * @return the descripcion instrumento
	 */
	public String getDescripcionInstrumento() {
		return descripcionInstrumento;
	}

	/**
	 * Sets the descripcion instrumento.
	 *
	 * @param descripcionInstrumento
	 *            the new descripcion instrumento
	 */
	public void setDescripcionInstrumento(String descripcionInstrumento) {
		this.descripcionInstrumento = descripcionInstrumento;
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
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public BigDecimal getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna
	 *            the new tna
	 */
	public void setTna(BigDecimal tna) {
		this.tna = tna;
	}

	/**
	 * Gets the alias instrumento.
	 *
	 * @return the alias instrumento
	 */
	public String getAliasInstrumento() {
		return aliasInstrumento;
	}

	/**
	 * Sets the alias instrumento.
	 *
	 * @param aliasInstrumento
	 *            the new alias instrumento
	 */
	public void setAliasInstrumento(String aliasInstrumento) {
		this.aliasInstrumento = aliasInstrumento;
	}
		
}
/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DetallePorItem.
 */
public class DetallePorItem {

	/** The guidError. */
	@JsonProperty("GUID")
	private String guidError;
	
	/** The Codigo Item. */
	@JsonProperty("CodigoItem")
	private String codigoItem;
	
	/** The Descripcion Item. */
	@JsonProperty("DescripcionItem")
	private String descripcionItem;
		
	/** The Distribucion. */
	@JsonProperty("Distribucion")
	private String distribucion;
	
	/** The Rendimiento. */
	@JsonProperty("RentabilidadRealizada")
	private BigDecimal rentabilidadRealizada;
	
	/** The Rendimiento. */
	@JsonProperty("RentabilidadNoRealizada")
	private BigDecimal rentabilidadNoRealizada;
	
	/** The Rendimiento. */
	@JsonProperty("RentabilidadNeta")
	private BigDecimal rentabilidadNeta;
	
	/** The Rendimiento. */
	@JsonProperty("Rendimiento")
	private BigDecimal rendimiento;
	
	/** The Rendimiento. */
	@JsonProperty("TNA")
	private BigDecimal tna;

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
	 * Gets the codigo item.
	 *
	 * @return the codigo item
	 */
	public String getCodigoItem() {
		return codigoItem;
	}

	/**
	 * Sets the codigo item.
	 *
	 * @param codigoItem
	 *            the new codigo item
	 */
	public void setCodigoItem(String codigoItem) {
		this.codigoItem = codigoItem;
	}

	/**
	 * Gets the descripcion item.
	 *
	 * @return the descripcion item
	 */
	public String getDescripcionItem() {
		return descripcionItem;
	}

	/**
	 * Sets the descripcion item.
	 *
	 * @param descripcionItem
	 *            the new descripcion item
	 */
	public void setDescripcionItem(String descripcionItem) {
		this.descripcionItem = descripcionItem;
	}

	/**
	 * Gets the distribucion.
	 *
	 * @return the distribucion
	 */
	public String getDistribucion() {
		return distribucion;
	}

	/**
	 * Sets the distribucion.
	 *
	 * @param distribucion
	 *            the new distribucion
	 */
	public void setDistribucion(String distribucion) {
		this.distribucion = distribucion;
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
	
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto;

import java.math.BigDecimal;

/**
 * The Class PlazoAcreditacionOrdenVentaDTO.
 */
public class PlazoAcreditacionOrdenVentaDTO {

	/** The id. */
	private Integer id;
	
	/** The nombre. */
	private String nombre;
	
	/** The tenencia negociable. */
	private BigDecimal tenenciaNegociable;
	
	/** The precio referencia. */
	private BigDecimal precioReferencia;
	
	/** The fecha referencia. */
	private String fechaReferencia;
	
	/** The hora referencia. */
	private String horaReferencia;
	
	/** The nominal minimo value. */
	private BigDecimal nominalMinimoValue;
	
	/** The nominal maximo value. */
	private BigDecimal nominalMaximoValue;
	
	/** The nominal incremento value. */
	private BigDecimal nominalIncrementoValue;
	
	/** The es pesos. */
	private Boolean esPesos;
	
	/** The precio minimo value. */
	private BigDecimal precioMinimoValue;
	
	/** The precio maximo value. */
	private BigDecimal precioMaximoValue;
	
	/** The precio incremento value. */
	private BigDecimal precioIncrementoValue;
	
	/** The requiere precio limite. */
	private Boolean requierePrecioLimite;
	
	/** The fecha de liquidacion. */
	private String fechaDeLiquidacion;
	

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the tenencia negociable.
	 *
	 * @return the tenenciaNegociable
	 */
	public BigDecimal getTenenciaNegociable() {
		return tenenciaNegociable;
	}

	/**
	 * Sets the tenencia negociable.
	 *
	 * @param tenenciaNegociable
	 *            the tenenciaNegociable to set
	 */
	public void setTenenciaNegociable(BigDecimal tenenciaNegociable) {
		this.tenenciaNegociable = tenenciaNegociable;
	}

	/**
	 * Gets the precio referencia.
	 *
	 * @return the precioReferencia
	 */
	public BigDecimal getPrecioReferencia() {
		return precioReferencia;
	}

	/**
	 * Sets the precio referencia.
	 *
	 * @param precioReferencia
	 *            the precioReferencia to set
	 */
	public void setPrecioReferencia(BigDecimal precioReferencia) {
		this.precioReferencia = precioReferencia;
	}

	/**
	 * Gets the fecha referencia.
	 *
	 * @return the fechaReferencia
	 */
	public String getFechaReferencia() {
		return fechaReferencia;
	}

	/**
	 * Sets the fecha referencia.
	 *
	 * @param fechaReferencia
	 *            the fechaReferencia to set
	 */
	public void setFechaReferencia(String fechaReferencia) {
		this.fechaReferencia = fechaReferencia;
	}

	/**
	 * Gets the hora referencia.
	 *
	 * @return the horaReferencia
	 */
	public String getHoraReferencia() {
		return horaReferencia;
	}

	/**
	 * Sets the hora referencia.
	 *
	 * @param horaReferencia
	 *            the horaReferencia to set
	 */
	public void setHoraReferencia(String horaReferencia) {
		this.horaReferencia = horaReferencia;
	}

	/**
	 * Gets the nominal minimo value.
	 *
	 * @return the nominalMinimoValue
	 */
	public BigDecimal getNominalMinimoValue() {
		return nominalMinimoValue;
	}

	/**
	 * Sets the nominal minimo value.
	 *
	 * @param nominalMinimoValue
	 *            the nominalMinimoValue to set
	 */
	public void setNominalMinimoValue(BigDecimal nominalMinimoValue) {
		this.nominalMinimoValue = nominalMinimoValue;
	}

	/**
	 * Gets the nominal maximo value.
	 *
	 * @return the nominalMaximoValue
	 */
	public BigDecimal getNominalMaximoValue() {
		return nominalMaximoValue;
	}

	/**
	 * Sets the nominal maximo value.
	 *
	 * @param nominalMaximoValue
	 *            the nominalMaximoValue to set
	 */
	public void setNominalMaximoValue(BigDecimal nominalMaximoValue) {
		this.nominalMaximoValue = nominalMaximoValue;
	}

	/**
	 * Gets the nominal incremento value.
	 *
	 * @return the nominalIncrementoValue
	 */
	public BigDecimal getNominalIncrementoValue() {
		return nominalIncrementoValue;
	}

	/**
	 * Sets the nominal incremento value.
	 *
	 * @param nominalIncrementoValue
	 *            the nominalIncrementoValue to set
	 */
	public void setNominalIncrementoValue(BigDecimal nominalIncrementoValue) {
		this.nominalIncrementoValue = nominalIncrementoValue;
	}

	/**
	 * Gets the es pesos.
	 *
	 * @return the esPesos
	 */
	public Boolean getEsPesos() {
		return esPesos;
	}

	/**
	 * Sets the es pesos.
	 *
	 * @param esPesos
	 *            the esPesos to set
	 */
	public void setEsPesos(Boolean esPesos) {
		this.esPesos = esPesos;
	}

	/**
	 * Gets the precio minimo value.
	 *
	 * @return the precioMinimoValue
	 */
	public BigDecimal getPrecioMinimoValue() {
		return precioMinimoValue;
	}

	/**
	 * Sets the precio minimo value.
	 *
	 * @param precioMinimoValue
	 *            the precioMinimoValue to set
	 */
	public void setPrecioMinimoValue(BigDecimal precioMinimoValue) {
		this.precioMinimoValue = precioMinimoValue;
	}

	/**
	 * Gets the precio maximo value.
	 *
	 * @return the precioMaximoValue
	 */
	public BigDecimal getPrecioMaximoValue() {
		return precioMaximoValue;
	}

	/**
	 * Sets the precio maximo value.
	 *
	 * @param precioMaximoValue
	 *            the precioMaximoValue to set
	 */
	public void setPrecioMaximoValue(BigDecimal precioMaximoValue) {
		this.precioMaximoValue = precioMaximoValue;
	}

	/**
	 * Gets the precio incremento value.
	 *
	 * @return the precioIncrementoValue
	 */
	public BigDecimal getPrecioIncrementoValue() {
		return precioIncrementoValue;
	}

	/**
	 * Sets the precio incremento value.
	 *
	 * @param precioIncrementoValue
	 *            the precioIncrementoValue to set
	 */
	public void setPrecioIncrementoValue(BigDecimal precioIncrementoValue) {
		this.precioIncrementoValue = precioIncrementoValue;
	}

	/**
	 * Gets the requiere precio limite.
	 *
	 * @return the requierePrecioLimite
	 */
	public Boolean getRequierePrecioLimite() {
		return requierePrecioLimite;
	}

	/**
	 * Sets the requiere precio limite.
	 *
	 * @param requierePrecioLimite
	 *            the requierePrecioLimite to set
	 */
	public void setRequierePrecioLimite(Boolean requierePrecioLimite) {
		this.requierePrecioLimite = requierePrecioLimite;
	}

	/**
	 * Gets the fecha de liquidacion.
	 *
	 * @return the fecha de liquidacion
	 */
	public String getFechaDeLiquidacion() {
		return fechaDeLiquidacion;
	}

	/**
	 * Sets the fecha de liquidacion.
	 *
	 * @param fechaDeLiquidacion
	 *            the new fecha de liquidacion
	 */
	public void setFechaDeLiquidacion(String fechaDeLiquidacion) {
		this.fechaDeLiquidacion = fechaDeLiquidacion;
	}

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view;

import java.math.BigDecimal;

/**
 * The Class PlazoAcreditacionOrdenVentaView.
 */
public class PlazoAcreditacionOrdenVentaView {

	/** The id. */
	private Integer id;
	
	/** The nombre. */
	private String nombre;
	
	/** The tenencia negociable. */
	private String tenenciaNegociable;
	
	/** The precio referencia. */
	private String precioReferencia;
	
	/** The fecha hora referencia. */
	private String fechaHoraReferencia;
	
	/** The fecha liquidacion. */
	private String fechaLiquidacion;
	
	/** The nominal minimo. */
	private String nominalMinimo;
	
	/** The nominal minimo value. */
	private BigDecimal nominalMinimoValue;
	
	/** The nominal maximo. */
	private String nominalMaximo;
	
	/** The nominal maximo value. */
	private BigDecimal nominalMaximoValue;
	
	/** The nominal incremento. */
	private String nominalIncremento;
	
	/** The nominal incremento value. */
	private BigDecimal nominalIncrementoValue;
	
	/** The es pesos. */
	private Boolean esPesos;
	
	/** The precio minimo. */
	private String precioMinimo;
	
	/** The precio minimo value. */
	private BigDecimal precioMinimoValue;
	
	/** The precio maximo. */
	private String precioMaximo;
	
	/** The precio maximo value. */
	private BigDecimal precioMaximoValue;
	
	/** The precio incremento. */
	private String precioIncremento;
	
	/** The precio incremento value. */
	private BigDecimal precioIncrementoValue;
	
	/** The requiere precio limite. */
	private Boolean requierePrecioLimite;
	
	/** The fecha cotizacion especie. */
	private String fechaCotizacionEspecie;
	
	/** The hora cotizacion especie. */
	private String horaCotizacionEspecie;
	
	
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
	public String getTenenciaNegociable() {
		return tenenciaNegociable;
	}

	/**
	 * Sets the tenencia negociable.
	 *
	 * @param tenenciaNegociable
	 *            the tenenciaNegociable to set
	 */
	public void setTenenciaNegociable(String tenenciaNegociable) {
		this.tenenciaNegociable = tenenciaNegociable;
	}

	/**
	 * Gets the precio referencia.
	 *
	 * @return the precioReferencia
	 */
	public String getPrecioReferencia() {
		return precioReferencia;
	}

	/**
	 * Sets the precio referencia.
	 *
	 * @param precioReferencia
	 *            the precioReferencia to set
	 */
	public void setPrecioReferencia(String precioReferencia) {
		this.precioReferencia = precioReferencia;
	}

	/**
	 * Gets the fecha hora referencia.
	 *
	 * @return the fechaHoraReferencia
	 */
	public String getFechaHoraReferencia() {
		return fechaHoraReferencia;
	}

	/**
	 * Sets the fecha hora referencia.
	 *
	 * @param fechaHoraReferencia
	 *            the fechaHoraReferencia to set
	 */
	public void setFechaHoraReferencia(String fechaHoraReferencia) {
		this.fechaHoraReferencia = fechaHoraReferencia;
	}

	/**
	 * Gets the nominal minimo.
	 *
	 * @return the nominalMinimo
	 */
	public String getNominalMinimo() {
		return nominalMinimo;
	}

	/**
	 * Sets the nominal minimo.
	 *
	 * @param nominalMinimo
	 *            the nominalMinimo to set
	 */
	public void setNominalMinimo(String nominalMinimo) {
		this.nominalMinimo = nominalMinimo;
	}

	/**
	 * Gets the nominal maximo.
	 *
	 * @return the nominalMaximo
	 */
	public String getNominalMaximo() {
		return nominalMaximo;
	}

	/**
	 * Sets the nominal maximo.
	 *
	 * @param nominalMaximo
	 *            the nominalMaximo to set
	 */
	public void setNominalMaximo(String nominalMaximo) {
		this.nominalMaximo = nominalMaximo;
	}

	/**
	 * Gets the nominal incremento.
	 *
	 * @return the nominalIncremento
	 */
	public String getNominalIncremento() {
		return nominalIncremento;
	}

	/**
	 * Sets the nominal incremento.
	 *
	 * @param nominalIncremento
	 *            the nominalIncremento to set
	 */
	public void setNominalIncremento(String nominalIncremento) {
		this.nominalIncremento = nominalIncremento;
	}

	/**
	 * Gets the fecha liquidacion.
	 *
	 * @return the fecha liquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the fecha liquidacion.
	 *
	 * @param fechaLiquidacion
	 *            the new fecha liquidacion
	 */
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
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
	 * Gets the precio minimo.
	 *
	 * @return the precioMinimo
	 */
	public String getPrecioMinimo() {
		return precioMinimo;
	}

	/**
	 * Sets the precio minimo.
	 *
	 * @param precioMinimo
	 *            the precioMinimo to set
	 */
	public void setPrecioMinimo(String precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	/**
	 * Gets the precio maximo.
	 *
	 * @return the precioMaximo
	 */
	public String getPrecioMaximo() {
		return precioMaximo;
	}

	/**
	 * Sets the precio maximo.
	 *
	 * @param precioMaximo
	 *            the precioMaximo to set
	 */
	public void setPrecioMaximo(String precioMaximo) {
		this.precioMaximo = precioMaximo;
	}

	/**
	 * Gets the precio incremento.
	 *
	 * @return the precioIncremento
	 */
	public String getPrecioIncremento() {
		return precioIncremento;
	}

	/**
	 * Sets the precio incremento.
	 *
	 * @param precioIncremento
	 *            the precioIncremento to set
	 */
	public void setPrecioIncremento(String precioIncremento) {
		this.precioIncremento = precioIncremento;
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
	 * Gets the fecha cotizacion especie.
	 *
	 * @return the fecha cotizacion especie
	 */
	public String getFechaCotizacionEspecie() {
		return fechaCotizacionEspecie;
	}

	/**
	 * Sets the fecha cotizacion especie.
	 *
	 * @param fechaCotizacionEspecie
	 *            the new fecha cotizacion especie
	 */
	public void setFechaCotizacionEspecie(String fechaCotizacionEspecie) {
		this.fechaCotizacionEspecie = fechaCotizacionEspecie;
	}

	/**
	 * Gets the hora cotizacion especie.
	 *
	 * @return the hora cotizacion especie
	 */
	public String getHoraCotizacionEspecie() {
		return horaCotizacionEspecie;
	}

	/**
	 * Sets the hora cotizacion especie.
	 *
	 * @param horaCotizacionEspecie
	 *            the new hora cotizacion especie
	 */
	public void setHoraCotizacionEspecie(String horaCotizacionEspecie) {
		this.horaCotizacionEspecie = horaCotizacionEspecie;
	}

}

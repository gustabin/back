/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class AperturaEspecieResponse.
 */
public class AperturaEspecieResponse {

	/** The plazo. */
	@JsonProperty("Plazo")
	private Integer plazo;

	/** The fecha liquidacion. */
	@JsonProperty("FechaLiquidacion")
	private String fechaLiquidacion;

	/** The moneda negociacion. */
	@JsonProperty("MonedaDeNegociacion")
	private String monedaNegociacion;

	/** The cod habilitacion. */
	@JsonProperty("CodHabilitacion")
	private String codHabilitacion;

	/** The cotizacion especie. */
	@JsonProperty("CotizacionEspecie")
	private BigDecimal cotizacionEspecie;

	/** The fecha cotizacion especie. */
	@JsonProperty("FechaCotizacionEspecie")
	private String fechaCotizacionEspecie;

	/** The hora cotizacion especie. */
	@JsonProperty("HoraCotizacionEspecie")
	private String horaCotizacionEspecie;

	/** The cantidad minimo incremento. */
	@JsonProperty("CantidadMinimoIncremento")
	private BigDecimal cantidadMinimoIncremento;

	/** The cantidad minima negociable. */
	@JsonProperty("CantidadMinimaNegociable")
	private BigDecimal cantidadMinimaNegociable;

	/** The cantidad maxima negociable. */
	@JsonProperty("CantidadMaximaNegociable")
	private BigDecimal cantidadMaximaNegociable;

	/** The importe minimo incremento. */
	@JsonProperty("ImporteMinimoIncremento")
	private BigDecimal importeMinimoIncremento;

	/** The importe minimo invertir. */
	@JsonProperty("ImporteMinimoInvertir")
	private BigDecimal importeMinimoInvertir;

	/** The importe maximo invertir. */
	@JsonProperty("ImporteMaximoInvertir")
	private BigDecimal importeMaximoInvertir;

	/** The requiere precio limite. */
	@JsonProperty("RequierePrecioLimite")
	private String requierePrecioLimite;

	/** The precio limite incremento. */
	@JsonProperty("PrecioLimiteIncremento")
	private BigDecimal precioLimiteIncremento;

	/** The precio limite minimo. */
	@JsonProperty("PrecioLimiteminimo")
	private BigDecimal precioLimiteMinimo;

	/** The precio limite maximo. */
	@JsonProperty("PrecioLimiteMaximo")
	private BigDecimal precioLimiteMaximo;

	/** The permite monto. */
	@JsonProperty("PermiteMonto")
	private String permiteMonto;

	/** The tenencia nominal negociable. */
	@JsonProperty("TenenciaNominalNegociable")
	private BigDecimal tenenciaNominalNegociable;

	/** The tenencia nominal. */
	@JsonProperty("TenenciaNominal")
	private BigDecimal tenenciaNominal;

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public Integer getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the fecha liquidacion.
	 *
	 * @return the fechaLiquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the fecha liquidacion.
	 *
	 * @param fechaLiquidacion
	 *            the fechaLiquidacion to set
	 */
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Gets the moneda negociacion.
	 *
	 * @return the monedaNegociacion
	 */
	public String getMonedaNegociacion() {
		return monedaNegociacion;
	}

	/**
	 * Sets the moneda negociacion.
	 *
	 * @param monedaNegociacion
	 *            the monedaNegociacion to set
	 */
	public void setMonedaNegociacion(String monedaNegociacion) {
		this.monedaNegociacion = monedaNegociacion;
	}

	/**
	 * Gets the cod habilitacion.
	 *
	 * @return the codHabilitacion
	 */
	public String getCodHabilitacion() {
		return codHabilitacion;
	}

	/**
	 * Sets the cod habilitacion.
	 *
	 * @param codHabilitacion
	 *            the codHabilitacion to set
	 */
	public void setCodHabilitacion(String codHabilitacion) {
		this.codHabilitacion = codHabilitacion;
	}

	/**
	 * Gets the cotizacion especie.
	 *
	 * @return the cotizacionEspecie
	 */
	public BigDecimal getCotizacionEspecie() {
		return cotizacionEspecie;
	}

	/**
	 * Sets the cotizacion especie.
	 *
	 * @param cotizacionEspecie
	 *            the cotizacionEspecie to set
	 */
	public void setCotizacionEspecie(BigDecimal cotizacionEspecie) {
		this.cotizacionEspecie = cotizacionEspecie;
	}

	/**
	 * Gets the fecha cotizacion especie.
	 *
	 * @return the fechaCotizacionEspecie
	 */
	public String getFechaCotizacionEspecie() {
		return fechaCotizacionEspecie;
	}

	/**
	 * Sets the fecha cotizacion especie.
	 *
	 * @param fechaCotizacionEspecie
	 *            the fechaCotizacionEspecie to set
	 */
	public void setFechaCotizacionEspecie(String fechaCotizacionEspecie) {
		this.fechaCotizacionEspecie = fechaCotizacionEspecie;
	}

	/**
	 * Gets the hora cotizacion especie.
	 *
	 * @return the horaCotizacionEspecie
	 */
	public String getHoraCotizacionEspecie() {
		return horaCotizacionEspecie;
	}

	/**
	 * Sets the hora cotizacion especie.
	 *
	 * @param horaCotizacionEspecie
	 *            the horaCotizacionEspecie to set
	 */
	public void setHoraCotizacionEspecie(String horaCotizacionEspecie) {
		this.horaCotizacionEspecie = horaCotizacionEspecie;
	}

	/**
	 * Gets the cantidad minimo incremento.
	 *
	 * @return the cantidadMinimoIncremento
	 */
	public BigDecimal getCantidadMinimoIncremento() {
		return cantidadMinimoIncremento;
	}

	/**
	 * Sets the cantidad minimo incremento.
	 *
	 * @param cantidadMinimoIncremento
	 *            the cantidadMinimoIncremento to set
	 */
	public void setCantidadMinimoIncremento(BigDecimal cantidadMinimoIncremento) {
		this.cantidadMinimoIncremento = cantidadMinimoIncremento;
	}

	/**
	 * Gets the cantidad minima negociable.
	 *
	 * @return the cantidadMinimaNegociable
	 */
	public BigDecimal getCantidadMinimaNegociable() {
		return cantidadMinimaNegociable;
	}

	/**
	 * Sets the cantidad minima negociable.
	 *
	 * @param cantidadMinimaNegociable
	 *            the cantidadMinimaNegociable to set
	 */
	public void setCantidadMinimaNegociable(BigDecimal cantidadMinimaNegociable) {
		this.cantidadMinimaNegociable = cantidadMinimaNegociable;
	}

	/**
	 * Gets the cantidad maxima negociable.
	 *
	 * @return the cantidadMaximaNegociable
	 */
	public BigDecimal getCantidadMaximaNegociable() {
		return cantidadMaximaNegociable;
	}

	/**
	 * Sets the cantidad maxima negociable.
	 *
	 * @param cantidadMaximaNegociable
	 *            the cantidadMaximaNegociable to set
	 */
	public void setCantidadMaximaNegociable(BigDecimal cantidadMaximaNegociable) {
		this.cantidadMaximaNegociable = cantidadMaximaNegociable;
	}

	/**
	 * Gets the importe minimo incremento.
	 *
	 * @return the importeMinimoIncremento
	 */
	public BigDecimal getImporteMinimoIncremento() {
		return importeMinimoIncremento;
	}

	/**
	 * Sets the importe minimo incremento.
	 *
	 * @param importeMinimoIncremento
	 *            the importeMinimoIncremento to set
	 */
	public void setImporteMinimoIncremento(BigDecimal importeMinimoIncremento) {
		this.importeMinimoIncremento = importeMinimoIncremento;
	}

	/**
	 * Gets the importe minimo invertir.
	 *
	 * @return the importeMinimoInvertir
	 */
	public BigDecimal getImporteMinimoInvertir() {
		return importeMinimoInvertir;
	}

	/**
	 * Sets the importe minimo invertir.
	 *
	 * @param importeMinimoInvertir
	 *            the importeMinimoInvertir to set
	 */
	public void setImporteMinimoInvertir(BigDecimal importeMinimoInvertir) {
		this.importeMinimoInvertir = importeMinimoInvertir;
	}

	/**
	 * Gets the importe maximo invertir.
	 *
	 * @return the importeMaximoInvertir
	 */
	public BigDecimal getImporteMaximoInvertir() {
		return importeMaximoInvertir;
	}

	/**
	 * Sets the importe maximo invertir.
	 *
	 * @param importeMaximoInvertir
	 *            the importeMaximoInvertir to set
	 */
	public void setImporteMaximoInvertir(BigDecimal importeMaximoInvertir) {
		this.importeMaximoInvertir = importeMaximoInvertir;
	}

	/**
	 * Gets the requiere precio limite.
	 *
	 * @return the requierePrecioLimite
	 */
	public String getRequierePrecioLimite() {
		return requierePrecioLimite;
	}

	/**
	 * Sets the requiere precio limite.
	 *
	 * @param requierePrecioLimite
	 *            the requierePrecioLimite to set
	 */
	public void setRequierePrecioLimite(String requierePrecioLimite) {
		this.requierePrecioLimite = requierePrecioLimite;
	}

	/**
	 * Gets the precio limite incremento.
	 *
	 * @return the precioLimiteIncremento
	 */
	public BigDecimal getPrecioLimiteIncremento() {
		return precioLimiteIncremento;
	}

	/**
	 * Sets the precio limite incremento.
	 *
	 * @param precioLimiteIncremento
	 *            the precioLimiteIncremento to set
	 */
	public void setPrecioLimiteIncremento(BigDecimal precioLimiteIncremento) {
		this.precioLimiteIncremento = precioLimiteIncremento;
	}

	/**
	 * Gets the precio limite minimo.
	 *
	 * @return the precioLimiteMinimo
	 */
	public BigDecimal getPrecioLimiteMinimo() {
		return precioLimiteMinimo;
	}

	/**
	 * Sets the precio limite minimo.
	 *
	 * @param precioLimiteMinimo
	 *            the precioLimiteMinimo to set
	 */
	public void setPrecioLimiteMinimo(BigDecimal precioLimiteMinimo) {
		this.precioLimiteMinimo = precioLimiteMinimo;
	}

	/**
	 * Gets the precio limite maximo.
	 *
	 * @return the precioLimiteMaximo
	 */
	public BigDecimal getPrecioLimiteMaximo() {
		return precioLimiteMaximo;
	}

	/**
	 * Sets the precio limite maximo.
	 *
	 * @param precioLimiteMaximo
	 *            the precioLimiteMaximo to set
	 */
	public void setPrecioLimiteMaximo(BigDecimal precioLimiteMaximo) {
		this.precioLimiteMaximo = precioLimiteMaximo;
	}

	/**
	 * Gets the permite monto.
	 *
	 * @return the permiteMonto
	 */
	public String getPermiteMonto() {
		return permiteMonto;
	}

	/**
	 * Sets the permite monto.
	 *
	 * @param permiteMonto
	 *            the permiteMonto to set
	 */
	public void setPermiteMonto(String permiteMonto) {
		this.permiteMonto = permiteMonto;
	}

	/**
	 * Gets the tenencia nominal negociable.
	 *
	 * @return the tenenciaNominalNegociable
	 */
	public BigDecimal getTenenciaNominalNegociable() {
		return tenenciaNominalNegociable;
	}

	/**
	 * Sets the tenencia nominal negociable.
	 *
	 * @param tenenciaNominalNegociable
	 *            the tenenciaNominalNegociable to set
	 */
	public void setTenenciaNominalNegociable(BigDecimal tenenciaNominalNegociable) {
		this.tenenciaNominalNegociable = tenenciaNominalNegociable;
	}

	/**
	 * Gets the tenencia nominal.
	 *
	 * @return the tenenciaNominal
	 */
	public BigDecimal getTenenciaNominal() {
		return tenenciaNominal;
	}

	/**
	 * Sets the tenencia nominal.
	 *
	 * @param tenenciaNominal
	 *            the tenenciaNominal to set
	 */
	public void setTenenciaNominal(BigDecimal tenenciaNominal) {
		this.tenenciaNominal = tenenciaNominal;
	}

}
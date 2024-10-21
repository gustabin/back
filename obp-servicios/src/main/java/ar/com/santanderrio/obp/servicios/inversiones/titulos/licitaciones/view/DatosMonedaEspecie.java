/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.math.BigDecimal;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Class DatosMonedaEspecie.
 */
public class DatosMonedaEspecie {

	/** The fecha liquidacion. */
	private String fechaLiquidacion;

	/** The fecha sin formato. */
	@JsonIgnore
	private String fechaSinFormato;

	/** The moneda negociacion. */
	private String monedaNegociacion;

	/** The cod habilitacion. */
	private String codHabilitacion;

	/** The cotizacion especie. */
	private BigDecimal cotizacionEspecie;

	/** The fecha cotizacion especie. */
	private String fechaCotizacionEspecie;

	/** The hora cotizacion especie. */
	private String horaCotizacionEspecie;

	/** The cantidad minimo incremento. */
	private BigDecimal cantidadMinimoIncremento;

	/** The cantidad minima negociable. */
	private BigDecimal cantidadMinimaNegociable;

	/** The cantidad maxima negociable. */
	private BigDecimal cantidadMaximaNegociable;

	/** The importe minimo incremento. */
	private BigDecimal importeMinimoIncremento;

	/** The importe minimo invertir. */
	private BigDecimal importeMinimoInvertir;

	/** The importe maximo invertir. */
	private BigDecimal importeMaximoInvertir;

	/** The requiere precio limite. */
	private String requierePrecioLimite;

	/** The precio limite incremento. */
	private BigDecimal precioLimiteIncremento;

	/** The precio limite minimo. */
	private BigDecimal precioLimiteMinimo;

	/** The precio limite maximo. */
	private BigDecimal precioLimiteMaximo;

	/** The permite monto. */
	private String permiteMonto;

	/** The tenencia nominal negociable. */
	private BigDecimal tenenciaNominalNegociable;

	/** The tenencia nominal. */
	private BigDecimal tenenciaNominal;

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
	 * Gets the moneda negociacion.
	 *
	 * @return the moneda negociacion
	 */
	public String getMonedaNegociacion() {
		return monedaNegociacion;
	}

	/**
	 * Sets the moneda negociacion.
	 *
	 * @param monedaNegociacion
	 *            the new moneda negociacion
	 */
	public void setMonedaNegociacion(String monedaNegociacion) {
		this.monedaNegociacion = monedaNegociacion;
	}

	/**
	 * Gets the cod habilitacion.
	 *
	 * @return the cod habilitacion
	 */
	public String getCodHabilitacion() {
		return codHabilitacion;
	}

	/**
	 * Sets the cod habilitacion.
	 *
	 * @param codHabilitacion
	 *            the new cod habilitacion
	 */
	public void setCodHabilitacion(String codHabilitacion) {
		this.codHabilitacion = codHabilitacion;
	}

	/**
	 * Gets the cotizacion especie.
	 *
	 * @return the cotizacion especie
	 */
	public BigDecimal getCotizacionEspecie() {
		return cotizacionEspecie;
	}

	/**
	 * Sets the cotizacion especie.
	 *
	 * @param cotizacionEspecie
	 *            the new cotizacion especie
	 */
	public void setCotizacionEspecie(BigDecimal cotizacionEspecie) {
		this.cotizacionEspecie = cotizacionEspecie;
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

	/**
	 * Gets the cantidad minimo incremento.
	 *
	 * @return the cantidad minimo incremento
	 */
	public BigDecimal getCantidadMinimoIncremento() {
		return cantidadMinimoIncremento;
	}

	/**
	 * Sets the cantidad minimo incremento.
	 *
	 * @param cantidadMinimoIncremento
	 *            the new cantidad minimo incremento
	 */
	public void setCantidadMinimoIncremento(BigDecimal cantidadMinimoIncremento) {
		this.cantidadMinimoIncremento = cantidadMinimoIncremento;
	}

	/**
	 * Gets the cantidad minima negociable.
	 *
	 * @return the cantidad minima negociable
	 */
	public BigDecimal getCantidadMinimaNegociable() {
		return cantidadMinimaNegociable;
	}

	/**
	 * Sets the cantidad minima negociable.
	 *
	 * @param cantidadMinimaNegociable
	 *            the new cantidad minima negociable
	 */
	public void setCantidadMinimaNegociable(BigDecimal cantidadMinimaNegociable) {
		this.cantidadMinimaNegociable = cantidadMinimaNegociable;
	}

	/**
	 * Gets the cantidad maxima negociable.
	 *
	 * @return the cantidad maxima negociable
	 */
	public BigDecimal getCantidadMaximaNegociable() {
		return cantidadMaximaNegociable;
	}

	/**
	 * Sets the cantidad maxima negociable.
	 *
	 * @param cantidadMaximaNegociable
	 *            the new cantidad maxima negociable
	 */
	public void setCantidadMaximaNegociable(BigDecimal cantidadMaximaNegociable) {
		this.cantidadMaximaNegociable = cantidadMaximaNegociable;
	}

	/**
	 * Gets the importe minimo incremento.
	 *
	 * @return the importe minimo incremento
	 */
	public BigDecimal getImporteMinimoIncremento() {
		return importeMinimoIncremento;
	}

	/**
	 * Sets the importe minimo incremento.
	 *
	 * @param importeMinimoIncremento
	 *            the new importe minimo incremento
	 */
	public void setImporteMinimoIncremento(BigDecimal importeMinimoIncremento) {
		this.importeMinimoIncremento = importeMinimoIncremento;
	}

	/**
	 * Gets the importe minimo invertir.
	 *
	 * @return the importe minimo invertir
	 */
	public BigDecimal getImporteMinimoInvertir() {
		return importeMinimoInvertir;
	}

	/**
	 * Sets the importe minimo invertir.
	 *
	 * @param importeMinimoInvertir
	 *            the new importe minimo invertir
	 */
	public void setImporteMinimoInvertir(BigDecimal importeMinimoInvertir) {
		this.importeMinimoInvertir = importeMinimoInvertir;
	}

	/**
	 * Gets the importe maximo invertir.
	 *
	 * @return the importe maximo invertir
	 */
	public BigDecimal getImporteMaximoInvertir() {
		return importeMaximoInvertir;
	}

	/**
	 * Sets the importe maximo invertir.
	 *
	 * @param importeMaximoInvertir
	 *            the new importe maximo invertir
	 */
	public void setImporteMaximoInvertir(BigDecimal importeMaximoInvertir) {
		this.importeMaximoInvertir = importeMaximoInvertir;
	}

	/**
	 * Gets the requiere precio limite.
	 *
	 * @return the requiere precio limite
	 */
	public String getRequierePrecioLimite() {
		return requierePrecioLimite;
	}

	/**
	 * Sets the requiere precio limite.
	 *
	 * @param requierePrecioLimite
	 *            the new requiere precio limite
	 */
	public void setRequierePrecioLimite(String requierePrecioLimite) {
		this.requierePrecioLimite = requierePrecioLimite;
	}

	/**
	 * Gets the precio limite incremento.
	 *
	 * @return the precio limite incremento
	 */
	public BigDecimal getPrecioLimiteIncremento() {
		return precioLimiteIncremento;
	}

	/**
	 * Sets the precio limite incremento.
	 *
	 * @param precioLimiteIncremento
	 *            the new precio limite incremento
	 */
	public void setPrecioLimiteIncremento(BigDecimal precioLimiteIncremento) {
		this.precioLimiteIncremento = precioLimiteIncremento;
	}

	/**
	 * Gets the precio limite minimo.
	 *
	 * @return the precio limite minimo
	 */
	public BigDecimal getPrecioLimiteMinimo() {
		return precioLimiteMinimo;
	}

	/**
	 * Sets the precio limite minimo.
	 *
	 * @param precioLimiteMinimo
	 *            the new precio limite minimo
	 */
	public void setPrecioLimiteMinimo(BigDecimal precioLimiteMinimo) {
		this.precioLimiteMinimo = precioLimiteMinimo;
	}

	/**
	 * Gets the precio limite maximo.
	 *
	 * @return the precio limite maximo
	 */
	public BigDecimal getPrecioLimiteMaximo() {
		return precioLimiteMaximo;
	}

	/**
	 * Sets the precio limite maximo.
	 *
	 * @param precioLimiteMaximo
	 *            the new precio limite maximo
	 */
	public void setPrecioLimiteMaximo(BigDecimal precioLimiteMaximo) {
		this.precioLimiteMaximo = precioLimiteMaximo;
	}

	/**
	 * Gets the permite monto.
	 *
	 * @return the permite monto
	 */
	public String getPermiteMonto() {
		return permiteMonto;
	}

	/**
	 * Sets the permite monto.
	 *
	 * @param permiteMonto
	 *            the new permite monto
	 */
	public void setPermiteMonto(String permiteMonto) {
		this.permiteMonto = permiteMonto;
	}

	/**
	 * Gets the tenencia nominal negociable.
	 *
	 * @return the tenencia nominal negociable
	 */
	public BigDecimal getTenenciaNominalNegociable() {
		return tenenciaNominalNegociable;
	}

	/**
	 * Sets the tenencia nominal negociable.
	 *
	 * @param tenenciaNominalNegociable
	 *            the new tenencia nominal negociable
	 */
	public void setTenenciaNominalNegociable(BigDecimal tenenciaNominalNegociable) {
		this.tenenciaNominalNegociable = tenenciaNominalNegociable;
	}

	/**
	 * Gets the tenencia nominal.
	 *
	 * @return the tenencia nominal
	 */
	public BigDecimal getTenenciaNominal() {
		return tenenciaNominal;
	}

	/**
	 * Sets the tenencia nominal.
	 *
	 * @param tenenciaNominal
	 *            the new tenencia nominal
	 */
	public void setTenenciaNominal(BigDecimal tenenciaNominal) {
		this.tenenciaNominal = tenenciaNominal;
	}

	/**
	 * Gets the fecha sin formato.
	 *
	 * @return the fecha sin formato
	 */
	public String getFechaSinFormato() {
		return fechaSinFormato;
	}

	/**
	 * Sets the fecha sin formato.
	 *
	 * @param fechaSinFormato
	 *            the new fecha sin formato
	 */
	public void setFechaSinFormato(String fechaSinFormato) {
		this.fechaSinFormato = fechaSinFormato;
	}
}

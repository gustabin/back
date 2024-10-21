/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class InfoMercadoResponse.
 */
public class InfoMercadoResponse {

	/** The codigo amigable. */
	@JsonProperty("CodigoAmigable")
	private String codigoAmigable;

	/** The codigo especie rossi. */
	@JsonProperty("CodigoEspecieRossi")
	private String codigoEspecieRossi;

	/** The descripcion especie. */
	@JsonProperty("DescripcionEspecie")
	private String descripcionEspecie;

	/** The codigo especie caja valores. */
	@JsonProperty("CodigoEspecieCajaValores")
	private String codigoEspecieCajaValores;

	/** The tipo especie. */
	@JsonProperty("TipoEspecie")
	private String tipoEspecie;

	/** The instrumento. */
	@JsonProperty("Instrumento")
	private String instrumento;

	/** The instrumento codigo. */
	@JsonProperty("InstrumentoCodigo")
	private String instrumentoCodigo;

	/** The emision moneda especie. */
	@JsonProperty("EmisionMonedaEspecie")
	private String emisionMonedaEspecie;

	/** The descripcion moneda emision. */
	@JsonProperty("DescripcionMonedaEmision")
	private String descripcionMonedaEmision;

	/** The precio referencia. */
	@JsonProperty("PrecioReferencia")
	private BigDecimal precioReferencia;

	/** The fecha precio ref. */
	@JsonProperty("FechaPrecioRef")
	private String fechaPrecioRef;

	/** The hora precio ref. */
	@JsonProperty("HoraPrecioRef")
	private String horaPrecioRef;

	/** The plazo. */
	@JsonProperty("Plazo")
	private Integer plazo;

	/** The moneda de negociacion. */
	@JsonProperty("MonedaDeNegociacion")
	private String monedaDeNegociacion;

	/** The variacion. */
	@JsonProperty("Variacion")
	private BigDecimal variacion;

	/** The precio maximo. */
	@JsonProperty("PrecioMaximo")
	private BigDecimal precioMaximo;

	/** The precio minimo. */
	@JsonProperty("PrecioMinimo")
	private BigDecimal precioMinimo;

	/** The volumen nominal. */
	@JsonProperty("VolumenNominal")
	private BigDecimal volumenNominal;

	/** The monto operado. */
	@JsonProperty("MontoOperado")
	private BigDecimal montoOperado;

	/** The precio cierre dia anterior. */
	@JsonProperty("PrecioCierreDiaAnterior")
	private BigDecimal precioCierreDiaAnterior;

	/** The informacion legal. */
	@JsonProperty("InformacionLegal")
	private String informacionLegal;

	/** The defecto. */
	@JsonProperty("Default")
	private String defecto;

	/** The marca negociable. */
	@JsonProperty("MarcaNegociable")
	private String marcaNegociable;
	
	/** The habilitado. */
	@JsonProperty("Habilitado")
	private String habilitado;


	/**
	 * Gets the codigo amigable.
	 *
	 * @return the codigoAmigable
	 */
	public String getCodigoAmigable() {
		return codigoAmigable;
	}

	/**
	 * Sets the codigo amigable.
	 *
	 * @param codigoAmigable
	 *            the codigoAmigable to set
	 */
	public void setCodigoAmigable(String codigoAmigable) {
		this.codigoAmigable = codigoAmigable;
	}

	/**
	 * Gets the codigo especie rossi.
	 *
	 * @return the codigoEspecieRossi
	 */
	public String getCodigoEspecieRossi() {
		return codigoEspecieRossi;
	}

	/**
	 * Sets the codigo especie rossi.
	 *
	 * @param codigoEspecieRossi
	 *            the codigoEspecieRossi to set
	 */
	public void setCodigoEspecieRossi(String codigoEspecieRossi) {
		this.codigoEspecieRossi = codigoEspecieRossi;
	}

	/**
	 * Gets the descripcion especie.
	 *
	 * @return the descripcionEspecie
	 */
	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	/**
	 * Sets the descripcion especie.
	 *
	 * @param descripcionEspecie
	 *            the descripcionEspecie to set
	 */
	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}

	/**
	 * Gets the codigo especie caja valores.
	 *
	 * @return the codigoEspecieCajaValores
	 */
	public String getCodigoEspecieCajaValores() {
		return codigoEspecieCajaValores;
	}

	/**
	 * Sets the codigo especie caja valores.
	 *
	 * @param codigoEspecieCajaValores
	 *            the codigoEspecieCajaValores to set
	 */
	public void setCodigoEspecieCajaValores(String codigoEspecieCajaValores) {
		this.codigoEspecieCajaValores = codigoEspecieCajaValores;
	}

	/**
	 * Gets the tipo especie.
	 *
	 * @return the tipoEspecie
	 */
	public String getTipoEspecie() {
		return tipoEspecie;
	}

	/**
	 * Sets the tipo especie.
	 *
	 * @param tipoEspecie
	 *            the tipoEspecie to set
	 */
	public void setTipoEspecie(String tipoEspecie) {
		this.tipoEspecie = tipoEspecie;
	}

	/**
	 * Gets the instrumento.
	 *
	 * @return the instrumento
	 */
	public String getInstrumento() {
		return instrumento;
	}

	/**
	 * Sets the instrumento.
	 *
	 * @param instrumento
	 *            the instrumento to set
	 */
	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}

	/**
	 * Gets the instrumento codigo.
	 *
	 * @return the instrumentoCodigo
	 */
	public String getInstrumentoCodigo() {
		return instrumentoCodigo;
	}

	/**
	 * Sets the instrumento codigo.
	 *
	 * @param instrumentoCodigo
	 *            the instrumentoCodigo to set
	 */
	public void setInstrumentoCodigo(String instrumentoCodigo) {
		this.instrumentoCodigo = instrumentoCodigo;
	}

	/**
	 * Gets the emision moneda especie.
	 *
	 * @return the emisionMonedaEspecie
	 */
	public String getEmisionMonedaEspecie() {
		return emisionMonedaEspecie;
	}

	/**
	 * Sets the emision moneda especie.
	 *
	 * @param emisionMonedaEspecie
	 *            the emisionMonedaEspecie to set
	 */
	public void setEmisionMonedaEspecie(String emisionMonedaEspecie) {
		this.emisionMonedaEspecie = emisionMonedaEspecie;
	}

	/**
	 * Gets the descripcion moneda emision.
	 *
	 * @return the descripcionMonedaEmision
	 */
	public String getDescripcionMonedaEmision() {
		return descripcionMonedaEmision;
	}

	/**
	 * Sets the descripcion moneda emision.
	 *
	 * @param descripcionMonedaEmision
	 *            the descripcionMonedaEmision to set
	 */
	public void setDescripcionMonedaEmision(String descripcionMonedaEmision) {
		this.descripcionMonedaEmision = descripcionMonedaEmision;
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
	 * Gets the fecha precio ref.
	 *
	 * @return the fechaPrecioRef
	 */
	public String getFechaPrecioRef() {
		return fechaPrecioRef;
	}

	/**
	 * Sets the fecha precio ref.
	 *
	 * @param fechaPrecioRef
	 *            the fechaPrecioRef to set
	 */
	public void setFechaPrecioRef(String fechaPrecioRef) {
		this.fechaPrecioRef = fechaPrecioRef;
	}

	/**
	 * Gets the hora precio ref.
	 *
	 * @return the horaPrecioRef
	 */
	public String getHoraPrecioRef() {
		return horaPrecioRef;
	}

	/**
	 * Sets the hora precio ref.
	 *
	 * @param horaPrecioRef
	 *            the horaPrecioRef to set
	 */
	public void setHoraPrecioRef(String horaPrecioRef) {
		this.horaPrecioRef = horaPrecioRef;
	}

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
	 * Gets the moneda de negociacion.
	 *
	 * @return the monedaDeNegociacion
	 */
	public String getMonedaDeNegociacion() {
		return monedaDeNegociacion;
	}

	/**
	 * Sets the moneda de negociacion.
	 *
	 * @param monedaDeNegociacion
	 *            the monedaDeNegociacion to set
	 */
	public void setMonedaDeNegociacion(String monedaDeNegociacion) {
		this.monedaDeNegociacion = monedaDeNegociacion;
	}

	/**
	 * Gets the variacion.
	 *
	 * @return the variacion
	 */
	public BigDecimal getVariacion() {
		return variacion;
	}

	/**
	 * Sets the variacion.
	 *
	 * @param variacion
	 *            the variacion to set
	 */
	public void setVariacion(BigDecimal variacion) {
		this.variacion = variacion;
	}

	/**
	 * Gets the precio maximo.
	 *
	 * @return the precioMaximo
	 */
	public BigDecimal getPrecioMaximo() {
		return precioMaximo;
	}

	/**
	 * Sets the precio maximo.
	 *
	 * @param precioMaximo
	 *            the precioMaximo to set
	 */
	public void setPrecioMaximo(BigDecimal precioMaximo) {
		this.precioMaximo = precioMaximo;
	}

	/**
	 * Gets the precio minimo.
	 *
	 * @return the precioMinimo
	 */
	public BigDecimal getPrecioMinimo() {
		return precioMinimo;
	}

	/**
	 * Sets the precio minimo.
	 *
	 * @param precioMinimo
	 *            the precioMinimo to set
	 */
	public void setPrecioMinimo(BigDecimal precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	/**
	 * Gets the volumen nominal.
	 *
	 * @return the volumenNominal
	 */
	public BigDecimal getVolumenNominal() {
		return volumenNominal;
	}

	/**
	 * Sets the volumen nominal.
	 *
	 * @param volumenNominal
	 *            the volumenNominal to set
	 */
	public void setVolumenNominal(BigDecimal volumenNominal) {
		this.volumenNominal = volumenNominal;
	}

	/**
	 * Gets the monto operado.
	 *
	 * @return the montoOperado
	 */
	public BigDecimal getMontoOperado() {
		return montoOperado;
	}

	/**
	 * Sets the monto operado.
	 *
	 * @param montoOperado
	 *            the montoOperado to set
	 */
	public void setMontoOperado(BigDecimal montoOperado) {
		this.montoOperado = montoOperado;
	}

	/**
	 * Gets the precio cierre dia anterior.
	 *
	 * @return the precioCierreDiaAnterior
	 */
	public BigDecimal getPrecioCierreDiaAnterior() {
		return precioCierreDiaAnterior;
	}

	/**
	 * Sets the precio cierre dia anterior.
	 *
	 * @param precioCierreDiaAnterior
	 *            the precioCierreDiaAnterior to set
	 */
	public void setPrecioCierreDiaAnterior(BigDecimal precioCierreDiaAnterior) {
		this.precioCierreDiaAnterior = precioCierreDiaAnterior;
	}

	/**
	 * Gets the informacion legal.
	 *
	 * @return the informacionLegal
	 */
	public String getInformacionLegal() {
		return informacionLegal;
	}

	/**
	 * Sets the informacion legal.
	 *
	 * @param informacionLegal
	 *            the informacionLegal to set
	 */
	public void setInformacionLegal(String informacionLegal) {
		this.informacionLegal = informacionLegal;
	}

	/**
	 * Gets the defecto.
	 *
	 * @return the defecto
	 */
	public String getDefecto() {
		return defecto;
	}

	/**
	 * Sets the defecto.
	 *
	 * @param defecto
	 *            the defecto to set
	 */
	public void setDefecto(String defecto) {
		this.defecto = defecto;
	}

	/**
	 * Gets the marca negociable.
	 *
	 * @return the marcaNegociable
	 */
	public String getMarcaNegociable() {
		return marcaNegociable;
	}

	/**
	 * Sets the marca negociable.
	 *
	 * @param marcaNegociable
	 *            the marcaNegociable to set
	 */
	public void setMarcaNegociable(String marcaNegociable) {
		this.marcaNegociable = marcaNegociable;
	}

	/**
	 * Gets the habilitado.
	 *
	 * @return the habilitado
	 */
	public String getHabilitado() {
		return habilitado;
	}

	/**
	 * Sets the habilitado.
	 *
	 * @param habilitado
	 *            the habilitado to set
	 */
	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

}

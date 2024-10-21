/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class EspeciesEntity.
 */
public class EspeciesEntity {

	/** The descripcion especie. */
	@JsonProperty("DescripcionEspecie")
	private String descripcionEspecie;
	
	/** The codigo amigable. */
	@JsonProperty("CodigoAmigable")
	private String codigoAmigable;
	
	/** The codigo especie rossi. */
	@JsonProperty("CodigoEspecieRossi")
	private String codigoEspecieRossi;
	
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
	
	/** The requiere precio limite. */
	@JsonProperty("RequierePrecioLimite")
	private String requierePrecioLimite;
	
	/** The permite monto. */
	@JsonProperty("PermiteMonto")
	private String permiteMonto;
	
	/** The emision moneda especie. */
	@JsonProperty("EmisionMonedaEspecie")
	private String emisionMonedaEspecie;

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
	
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ListaAperturaEspecieResponse.
 */
public class ListaAperturaEspecieResponse {

	/** The descripcion especie. */
	@JsonProperty("DescripcionEspecie")
	private String descripcionEspecie;

	/** The codigo amigable. */
	@JsonProperty("CodigoAmigable")
	private String codigoAmigable;

	/** The codigo especie rossi. */
	@JsonProperty("CodigoEspecieRossi")
	private String codigoEspecieRossi;

	/** The tipo especie. */
	@JsonProperty("TipoEspecie")
	private String tipoEspecie;

	/** The instrumento. */
	@JsonProperty("Instrumento")
	private String instrumento;

	/** The emision moneda especie. */
	@JsonProperty("EmisionMonedaEspecie")
	private String emisionMonedaEspecie;

	/** The leyenda legal. */
	@JsonProperty("LeyendaLegal")
	private String leyendaLegal;

	/** The aperturas especie. */
	@JsonProperty("AperturasEspecie")
	private List<AperturaEspecieResponse> aperturasEspecie;

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
	 * Gets the leyenda legal.
	 *
	 * @return the leyendaLegal
	 */
	public String getLeyendaLegal() {
		return leyendaLegal;
	}

	/**
	 * Sets the leyenda legal.
	 *
	 * @param leyendaLegal
	 *            the leyendaLegal to set
	 */
	public void setLeyendaLegal(String leyendaLegal) {
		this.leyendaLegal = leyendaLegal;
	}

	/**
	 * Gets the aperturas especie.
	 *
	 * @return the aperturasEspecie
	 */
	public List<AperturaEspecieResponse> getAperturasEspecie() {
		return aperturasEspecie;
	}

	/**
	 * Sets the aperturas especie.
	 *
	 * @param aperturasEspecie
	 *            the aperturasEspecie to set
	 */
	public void setAperturasEspecie(List<AperturaEspecieResponse> aperturasEspecie) {
		this.aperturasEspecie = aperturasEspecie;
	}

}
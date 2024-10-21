/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.EntityBaseTitulos;

/**
 * The Class DatosVariacionInfoMercadoRequest.
 */
public class DatosVariacionInfoMercadoRequest extends EntityBaseTitulos {

	/** The codigo especie rossi. */
	@JsonProperty("CodigoEspecieRossi")
	private String codigoEspecieRossi;

	/** The tipo especie. */
	@JsonProperty("TipoEspecie")
	private String tipoEspecie;

	/** The moneda cotizacion. */
	@JsonProperty("MonedaCotizacion")
	private String monedaCotizacion;

	/** The plazo. */
	@JsonProperty("Plazo")
	private String plazo;

	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;

	/** The canal. */
	@JsonProperty("Canal")
	private String canal;

	/** The subcanal. */
	@JsonProperty("SubCanal")
	private String subcanal;

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
	 * Gets the moneda cotizacion.
	 *
	 * @return the monedaCotizacion
	 */
	public String getMonedaCotizacion() {
		return monedaCotizacion;
	}

	/**
	 * Sets the moneda cotizacion.
	 *
	 * @param monedaCotizacion
	 *            the monedaCotizacion to set
	 */
	public void setMonedaCotizacion(String monedaCotizacion) {
		this.monedaCotizacion = monedaCotizacion;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * Sets the segmento.
	 *
	 * @param segmento
	 *            the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the canal to set
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the subcanal.
	 *
	 * @return the subcanal
	 */
	public String getSubcanal() {
		return subcanal;
	}

	/**
	 * Sets the subcanal.
	 *
	 * @param subcanal
	 *            the subcanal to set
	 */
	public void setSubcanal(String subcanal) {
		this.subcanal = subcanal;
	}

}

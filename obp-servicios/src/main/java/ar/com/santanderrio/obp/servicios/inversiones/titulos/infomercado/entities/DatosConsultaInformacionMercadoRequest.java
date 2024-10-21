/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.EntityBaseTitulos;

/**
 * The Class DatosConsultaInformacionMercadoRequest.
 */
public class DatosConsultaInformacionMercadoRequest extends EntityBaseTitulos {

	/** The agrupamiento id. */
	@JsonProperty("AgrupamientoId")
	private String agrupamientoId;

	/** The moneda negociacion. */
	@JsonProperty("MonedaNegociacion")
	private String monedaNegociacion;

	/** The plazo. */
	@JsonProperty("Plazo")
	private Integer plazo;

	/** The tipo busqueda. */
	@JsonProperty("TipoBusqueda")
	private String tipoBusqueda;

	/** The cuenta titulo. */
	@JsonProperty("CuentaTitulo")
	private String cuentaTitulo;

	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;

	/** The canal. */
	@JsonProperty("Canal")
	private String canal;;

	/** The sub canal. */
	@JsonProperty("SubCanal")
	private String subCanal;

	/**
	 * Gets the agrupamiento id.
	 *
	 * @return the agrupamientoId
	 */
	public String getAgrupamientoId() {
		return agrupamientoId;
	}

	/**
	 * Sets the agrupamiento id.
	 *
	 * @param agrupamientoId
	 *            the agrupamientoId to set
	 */
	public void setAgrupamientoId(String agrupamientoId) {
		this.agrupamientoId = agrupamientoId;
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
	 * Gets the tipo busqueda.
	 *
	 * @return the tipoBusqueda
	 */
	public String getTipoBusqueda() {
		return tipoBusqueda;
	}

	/**
	 * Sets the tipo busqueda.
	 *
	 * @param tipoBusqueda
	 *            the tipoBusqueda to set
	 */
	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuentaTitulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the cuentaTitulo to set
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
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
	 * Gets the sub canal.
	 *
	 * @return the subCanal
	 */
	public String getSubCanal() {
		return subCanal;
	}

	/**
	 * Sets the sub canal.
	 *
	 * @param subCanal
	 *            the subCanal to set
	 */
	public void setSubCanal(String subCanal) {
		this.subCanal = subCanal;
	}

}

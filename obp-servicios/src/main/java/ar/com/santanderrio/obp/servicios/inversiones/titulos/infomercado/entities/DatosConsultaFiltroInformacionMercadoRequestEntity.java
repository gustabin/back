/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.EntityBaseTitulos;

/**
 * The Class DatosConsultaFiltroInformacionMercadoRequestEntity.
 */
public class DatosConsultaFiltroInformacionMercadoRequestEntity extends EntityBaseTitulos {

	/** The fecha. */
	@JsonProperty("Fecha")
	private String fecha;

	/** The descripcion parametro. */
	@JsonProperty("DescripcionParametro ")
	private String descripcionParametro;

	/** The tipo instrumento. */
	@JsonProperty("TipoInstrumento")
	private String tipoInstrumento;

	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;

	/** The canal. */
	@JsonProperty("Canal")
	private String canal;

	/** The sub canal. */
	@JsonProperty("SubCanal")
	private String subCanal;

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the descripcion parametro.
	 *
	 * @return the descripcionParametro
	 */
	public String getDescripcionParametro() {
		return descripcionParametro;
	}

	/**
	 * Sets the descripcion parametro.
	 *
	 * @param descripcionParametro
	 *            the descripcionParametro to set
	 */
	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
	}

	/**
	 * Gets the tipo instrumento.
	 *
	 * @return the tipoInstrumento
	 */
	public String getTipoInstrumento() {
		return tipoInstrumento;
	}

	/**
	 * Sets the tipo instrumento.
	 *
	 * @param tipoInstrumento
	 *            the tipoInstrumento to set
	 */
	public void setTipoInstrumento(String tipoInstrumento) {
		this.tipoInstrumento = tipoInstrumento;
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

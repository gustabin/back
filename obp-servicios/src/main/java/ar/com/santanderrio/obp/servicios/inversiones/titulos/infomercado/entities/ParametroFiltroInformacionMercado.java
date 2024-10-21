/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ParametroFiltroInformacionMercado.
 */
public class ParametroFiltroInformacionMercado {

	/** The agrupamiento id. */
	@JsonProperty("AgrupamientoId")
	private String agrupamientoId;

	/** The descripcion parametro. */
	@JsonProperty("DescripcionParametro")
	private String descripcionParametro;

	/** The orden. */
	@JsonProperty("Orden")
	private Integer orden;

	/** The defecto. */
	@JsonProperty("Default")
	private String defecto;

	/** The leyenda legal. */
	@JsonProperty("LeyendaLegal")
	private String leyendaLegal;

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
	 * Gets the orden.
	 *
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * Sets the orden.
	 *
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
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

}

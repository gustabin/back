/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaEspeciesRequestEntity.
 */
public class DatosConsultaEspeciesRequestEntity extends EntityBaseTitulos{
	
	/** The descripcion especie. */
	@JsonProperty("DescripcionEspecie")
	private String descripcionEspecie;
	
	/** The tipo especie. */
	@JsonProperty("TipoEspecie")
	private String tipoEspecie;
	
	/** The indices. */
	@JsonProperty("Indices")
	private String indices;
	
	/** The fecha. */
	@JsonProperty("Fecha")
	private String fecha;
	
	/** The tipo operacion. */
	@JsonProperty("TipoOperacion")
	private String tipoOperacion;
	
	/** The operacion moneda especie. */
	@JsonProperty("OperacionMonedaEspecie")
	private String operacionMonedaEspecie;
	
	/** The tipo de negociacion. */
	@JsonProperty("TipoDeNegociacion")
	private String tipoDeNegociacion;
	
	/** The especie negociable. */
	@JsonProperty("EspecieNegociable")
	private String especieNegociable;
	
	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;
	
	/** The cuenta titulo. */
	@JsonProperty("CuentaTitulo")
	private String cuentaTitulo;

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
	 * Gets the indices.
	 *
	 * @return the indices
	 */
	public String getIndices() {
		return indices;
	}

	/**
	 * Sets the indices.
	 *
	 * @param indices
	 *            the indices to set
	 */
	public void setIndices(String indices) {
		this.indices = indices;
	}

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
	 * Gets the tipo operacion.
	 *
	 * @return the tipoOperacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 *
	 * @param tipoOperacion
	 *            the tipoOperacion to set
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * Gets the operacion moneda especie.
	 *
	 * @return the operacionMonedaEspecie
	 */
	public String getOperacionMonedaEspecie() {
		return operacionMonedaEspecie;
	}

	/**
	 * Sets the operacion moneda especie.
	 *
	 * @param operacionMonedaEspecie
	 *            the operacionMonedaEspecie to set
	 */
	public void setOperacionMonedaEspecie(String operacionMonedaEspecie) {
		this.operacionMonedaEspecie = operacionMonedaEspecie;
	}

	/**
	 * Gets the tipo de negociacion.
	 *
	 * @return the tipoDeNegociacion
	 */
	public String getTipoDeNegociacion() {
		return tipoDeNegociacion;
	}

	/**
	 * Sets the tipo de negociacion.
	 *
	 * @param tipoDeNegociacion
	 *            the tipoDeNegociacion to set
	 */
	public void setTipoDeNegociacion(String tipoDeNegociacion) {
		this.tipoDeNegociacion = tipoDeNegociacion;
	}

	/**
	 * Gets the especie negociable.
	 *
	 * @return the especieNegociable
	 */
	public String getEspecieNegociable() {
		return especieNegociable;
	}

	/**
	 * Sets the especie negociable.
	 *
	 * @param especieNegociable
	 *            the especieNegociable to set
	 */
	public void setEspecieNegociable(String especieNegociable) {
		this.especieNegociable = especieNegociable;
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

}

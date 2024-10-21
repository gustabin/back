/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.EntityBaseTitulos;

/**
 * The Class DatosConsultaAperturaEspecieRequestEntity.
 */
public class DatosConsultaAperturaEspecieRequestEntity extends EntityBaseTitulos {

	/** The codigo especie rossi. */
	@JsonProperty("CodigoEspecieRossi")
	private String codigoEspecieRossi;

	/** The fecha. */
	@JsonProperty("Fecha")
	private String fecha;

	/** The tipo operacion. */
	@JsonProperty("TipoOperacion")
	private String tipoOperacion;

	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;

	/** The cuenta titulo. */
	@JsonProperty("CuentaTitulo")
	private Integer cuentaTitulo;

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
	public Integer getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the cuentaTitulo to set
	 */
	public void setCuentaTitulo(Integer cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

}
/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class IndiceResponse.
 */
public class IndiceResponse {

	/** The sec id. */
	@JsonProperty("Secid")
	private String secId;

	/** The nombre indice. */
	@JsonProperty("NombreIndice")
	private String nombreIndice;

	/** The codigo indice. */
	@JsonProperty("CodigoIndice")
	private String codigoIndice;

	/** The ultimo precio operado. */
	@JsonProperty("UltimoPrecioOperado")
	private BigDecimal ultimoPrecioOperado;

	/** The variacion. */
	@JsonProperty("Variacion")
	private BigDecimal variacion;

	/** The fecha. */
	@JsonProperty("Fecha")
	private String fecha;

	/** The hora. */
	@JsonProperty("Hora")
	private String hora;

	/**
	 * Gets the sec id.
	 *
	 * @return the secId
	 */
	public String getSecId() {
		return secId;
	}

	/**
	 * Sets the sec id.
	 *
	 * @param secId
	 *            the secId to set
	 */
	public void setSecId(String secId) {
		this.secId = secId;
	}

	/**
	 * Gets the nombre indice.
	 *
	 * @return the nombreIndice
	 */
	public String getNombreIndice() {
		return nombreIndice;
	}

	/**
	 * Sets the nombre indice.
	 *
	 * @param nombreIndice
	 *            the nombreIndice to set
	 */
	public void setNombreIndice(String nombreIndice) {
		this.nombreIndice = nombreIndice;
	}

	/**
	 * Gets the codigo indice.
	 *
	 * @return the codigoIndice
	 */
	public String getCodigoIndice() {
		return codigoIndice;
	}

	/**
	 * Sets the codigo indice.
	 *
	 * @param codigoIndice
	 *            the codigoIndice to set
	 */
	public void setCodigoIndice(String codigoIndice) {
		this.codigoIndice = codigoIndice;
	}

	/**
	 * Gets the ultimo precio operado.
	 *
	 * @return the ultimoPrecioOperado
	 */
	public BigDecimal getUltimoPrecioOperado() {
		return ultimoPrecioOperado;
	}

	/**
	 * Sets the ultimo precio operado.
	 *
	 * @param ultimoPrecioOperado
	 *            the ultimoPrecioOperado to set
	 */
	public void setUltimoPrecioOperado(BigDecimal ultimoPrecioOperado) {
		this.ultimoPrecioOperado = ultimoPrecioOperado;
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
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

}

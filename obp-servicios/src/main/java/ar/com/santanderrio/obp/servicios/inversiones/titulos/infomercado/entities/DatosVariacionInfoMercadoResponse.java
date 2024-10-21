/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosVariacionInfoMercadoResponse.
 */
public class DatosVariacionInfoMercadoResponse {

	/** The descripcion especie. */
	@JsonProperty("DescripcionEspecie")
	private String descripcionEspecie;

	/** The codigo amigable. */
	@JsonProperty("CodigoAmigable")
	private String codigoAmigable;

	/** The cotizacion actual. */
	@JsonProperty("CotizacionActual")
	private BigDecimal cotizacionActual;

	/** The cotizacion ultimo cierre. */
	@JsonProperty("CotizacionUltimoCierre")
	private BigDecimal cotizacionUltimoCierre;

	/** The variacion dia hoy. */
	@JsonProperty("VariacionDiaHoy")
	private BigDecimal variacionDiaHoy;

	/** The cotizacion 30 dias. */
	@JsonProperty("Cotizacion30Dias")
	private BigDecimal cotizacion30Dias;

	/** The variacion 30 dias. */
	@JsonProperty("Variacion30Dias")
	private BigDecimal variacion30Dias;

	/** The cotizacion 365 dias. */
	@JsonProperty("Cotizacion365Dias")
	private BigDecimal cotizacion365Dias;

	/** The variacion 365 dias. */
	@JsonProperty("Variacion365Dias")
	private BigDecimal variacion365Dias;

	/** The codigo error middleware. */
	@JsonProperty("CodigoErrorMiddleware")
	private String codigoErrorMiddleware;

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
	 * Gets the cotizacion actual.
	 *
	 * @return the cotizacionActual
	 */
	public BigDecimal getCotizacionActual() {
		return cotizacionActual;
	}

	/**
	 * Sets the cotizacion actual.
	 *
	 * @param cotizacionActual
	 *            the cotizacionActual to set
	 */
	public void setCotizacionActual(BigDecimal cotizacionActual) {
		this.cotizacionActual = cotizacionActual;
	}

	/**
	 * Gets the cotizacion ultimo cierre.
	 *
	 * @return the cotizacionUltimoCierre
	 */
	public BigDecimal getCotizacionUltimoCierre() {
		return cotizacionUltimoCierre;
	}

	/**
	 * Sets the cotizacion ultimo cierre.
	 *
	 * @param cotizacionUltimoCierre
	 *            the cotizacionUltimoCierre to set
	 */
	public void setCotizacionUltimoCierre(BigDecimal cotizacionUltimoCierre) {
		this.cotizacionUltimoCierre = cotizacionUltimoCierre;
	}

	/**
	 * Gets the variacion dia hoy.
	 *
	 * @return the variacionDiaHoy
	 */
	public BigDecimal getVariacionDiaHoy() {
		return variacionDiaHoy;
	}

	/**
	 * Sets the variacion dia hoy.
	 *
	 * @param variacionDiaHoy
	 *            the variacionDiaHoy to set
	 */
	public void setVariacionDiaHoy(BigDecimal variacionDiaHoy) {
		this.variacionDiaHoy = variacionDiaHoy;
	}

	/**
	 * Gets the cotizacion 30 dias.
	 *
	 * @return the cotizacion30Dias
	 */
	public BigDecimal getCotizacion30Dias() {
		return cotizacion30Dias;
	}

	/**
	 * Sets the cotizacion 30 dias.
	 *
	 * @param cotizacion30Dias
	 *            the cotizacion30Dias to set
	 */
	public void setCotizacion30Dias(BigDecimal cotizacion30Dias) {
		this.cotizacion30Dias = cotizacion30Dias;
	}

	/**
	 * Gets the variacion 30 dias.
	 *
	 * @return the variacion30Dias
	 */
	public BigDecimal getVariacion30Dias() {
		return variacion30Dias;
	}

	/**
	 * Sets the variacion 30 dias.
	 *
	 * @param variacion30Dias
	 *            the variacion30Dias to set
	 */
	public void setVariacion30Dias(BigDecimal variacion30Dias) {
		this.variacion30Dias = variacion30Dias;
	}

	/**
	 * Gets the cotizacion 365 dias.
	 *
	 * @return the cotizacion365Dias
	 */
	public BigDecimal getCotizacion365Dias() {
		return cotizacion365Dias;
	}

	/**
	 * Sets the cotizacion 365 dias.
	 *
	 * @param cotizacion365Dias
	 *            the cotizacion365Dias to set
	 */
	public void setCotizacion365Dias(BigDecimal cotizacion365Dias) {
		this.cotizacion365Dias = cotizacion365Dias;
	}

	/**
	 * Gets the variacion 365 dias.
	 *
	 * @return the variacion365Dias
	 */
	public BigDecimal getVariacion365Dias() {
		return variacion365Dias;
	}

	/**
	 * Sets the variacion 365 dias.
	 *
	 * @param variacion365Dias
	 *            the variacion365Dias to set
	 */
	public void setVariacion365Dias(BigDecimal variacion365Dias) {
		this.variacion365Dias = variacion365Dias;
	}

	/**
	 * Gets the codigo error middleware.
	 *
	 * @return the codigoErrorMiddleware
	 */
	public String getCodigoErrorMiddleware() {
		return codigoErrorMiddleware;
	}

	/**
	 * Sets the codigo error middleware.
	 *
	 * @param codigoErrorMiddleware
	 *            the codigoErrorMiddleware to set
	 */
	public void setCodigoErrorMiddleware(String codigoErrorMiddleware) {
		this.codigoErrorMiddleware = codigoErrorMiddleware;
	}

}

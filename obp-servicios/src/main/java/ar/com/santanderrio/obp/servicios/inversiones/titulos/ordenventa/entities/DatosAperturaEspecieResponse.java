/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosAperturaEspecieResponse.
 */
public class DatosAperturaEspecieResponse {

	/** The codigo error middleware. */
	@JsonProperty("CodigoErrorMiddleware")
	private String codigoErrorMiddleware;

	/** The cantidad detalle. */
	@JsonProperty("CantidadDetalle")
	private int cantidadDetalle;

	/** The lista apertura especie. */
	@JsonProperty("ListaAperturaEspecie")
	private ListaAperturaEspecieResponse listaAperturaEspecie;

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

	/**
	 * Gets the cantidad detalle.
	 *
	 * @return the cantidadDetalle
	 */
	public int getCantidadDetalle() {
		return cantidadDetalle;
	}

	/**
	 * Sets the cantidad detalle.
	 *
	 * @param cantidadDetalle
	 *            the cantidadDetalle to set
	 */
	public void setCantidadDetalle(int cantidadDetalle) {
		this.cantidadDetalle = cantidadDetalle;
	}

	/**
	 * Gets the lista apertura especie.
	 *
	 * @return the listaAperturaEspecie
	 */
	public ListaAperturaEspecieResponse getListaAperturaEspecie() {
		return listaAperturaEspecie;
	}

	/**
	 * Sets the lista apertura especie.
	 *
	 * @param listaAperturaEspecie
	 *            the listaAperturaEspecie to set
	 */
	public void setListaAperturaEspecie(ListaAperturaEspecieResponse listaAperturaEspecie) {
		this.listaAperturaEspecie = listaAperturaEspecie;
	}

}
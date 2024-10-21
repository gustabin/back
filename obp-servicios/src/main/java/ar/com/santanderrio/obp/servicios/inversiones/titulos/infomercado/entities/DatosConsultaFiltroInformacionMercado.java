/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaFiltroInformacionMercado.
 */
public class DatosConsultaFiltroInformacionMercado {

	/** The codigo error middleware. */
	@JsonProperty("CodigoErrorMiddleware")
	private String codigoErrorMiddleware;

	/** The cantidad. */
	@JsonProperty("Cantidad")
	private Integer cantidad;

	/** The lista parametros. */
	@JsonProperty("ListaParametros")
	private List<ParametroFiltroInformacionMercado> listaParametros;

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
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the lista parametros.
	 *
	 * @return the listaParametros
	 */
	public List<ParametroFiltroInformacionMercado> getListaParametros() {
		return listaParametros;
	}

	/**
	 * Sets the lista parametros.
	 *
	 * @param listaParametros
	 *            the listaParametros to set
	 */
	public void setListaParametros(List<ParametroFiltroInformacionMercado> listaParametros) {
		this.listaParametros = listaParametros;
	}

}

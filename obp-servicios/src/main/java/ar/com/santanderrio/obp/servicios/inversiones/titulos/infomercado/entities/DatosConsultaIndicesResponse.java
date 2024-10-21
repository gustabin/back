/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaIndicesResponse.
 */
public class DatosConsultaIndicesResponse {

	/** The codigo error middleware. */
	@JsonProperty("CodigoErrorMiddleware")
	private String codigoErrorMiddleware;

	/** The cantidad indice. */
	@JsonProperty("CantidadIndice")
	private Integer cantidadIndice;

	/** The lista indices. */
	@JsonProperty("ListaIndice")
	private List<IndiceResponse> listaIndices;

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
	 * Gets the cantidad indice.
	 *
	 * @return the cantidadIndice
	 */
	public Integer getCantidadIndice() {
		return cantidadIndice;
	}

	/**
	 * Sets the cantidad indice.
	 *
	 * @param cantidadIndice
	 *            the cantidadIndice to set
	 */
	public void setCantidadIndice(Integer cantidadIndice) {
		this.cantidadIndice = cantidadIndice;
	}

	/**
	 * Gets the lista indices.
	 *
	 * @return the listaIndices
	 */
	public List<IndiceResponse> getListaIndices() {
		return listaIndices;
	}

	/**
	 * Sets the lista indices.
	 *
	 * @param listaIndices
	 *            the listaIndices to set
	 */
	public void setListaIndices(List<IndiceResponse> listaIndices) {
		this.listaIndices = listaIndices;
	}

}

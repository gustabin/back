/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaEspeciesResponse.
 */
public class DatosConsultaEspeciesResponse {
	
	/** The cantidad detalle. */
	@JsonProperty("CantidadDetalle")
	private int cantidadDetalle;
	
	/** The codigo error middleware. */
	@JsonProperty("CodigoErrorMiddleware")
	private String codigoErrorMiddleware;
	
	/** The lista especies. */
	@JsonProperty("ListaEspecies")
	private List<EspeciesEntity> listaEspecies = new ArrayList<EspeciesEntity>();

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
	 * Gets the lista especies.
	 *
	 * @return the listaEspecies
	 */
	public List<EspeciesEntity> getListaEspecies() {
		return listaEspecies;
	}

	/**
	 * Sets the lista especies.
	 *
	 * @param listaEspecies
	 *            the listaEspecies to set
	 */
	public void setListaEspecies(List<EspeciesEntity> listaEspecies) {
		this.listaEspecies = listaEspecies;
	}
}

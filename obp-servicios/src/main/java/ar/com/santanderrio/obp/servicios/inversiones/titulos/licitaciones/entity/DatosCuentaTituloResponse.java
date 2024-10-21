/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosCuentaTituloResponse.
 */
public class DatosCuentaTituloResponse {

	/** The cantidad detalle. */
	@JsonProperty("CantidadDetalle")
	private int cantidadDetalle;
	
	/** The lista cuentas. */
	@JsonProperty("ListaCuentaTitulos")
	List<ListaConsultaCuentaTitulosResponse> listaCuentas = new ArrayList<ListaConsultaCuentaTitulosResponse>();
	
	/** The codigo error middleware. */
	@JsonProperty("CodigoErrorMiddleware")
	private String codigoErrorMiddleware;

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
	 * Gets the lista cuentas.
	 *
	 * @return the listaCuentas
	 */
	public List<ListaConsultaCuentaTitulosResponse> getListaCuentas() {
		return listaCuentas;
	}

	/**
	 * Sets the lista cuentas.
	 *
	 * @param listaCuentas
	 *            the listaCuentas to set
	 */
	public void setListaCuentas(List<ListaConsultaCuentaTitulosResponse> listaCuentas) {
		this.listaCuentas = listaCuentas;
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

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ColeccionConsultaOrdenes.
 */
public class ColeccionConsultaOrdenes {
	
	/** The lista consulta ordenes. */
	@JsonProperty("Ordenes")
	private List<DatosConsultaOrdenesResponse> listaConsultaOrdenes = new ArrayList<DatosConsultaOrdenesResponse>();

	/** The codigo error middleware. */
	@JsonProperty("CodigoErrorMiddleware")
	private String codigoErrorMiddleware;
	
	
	/**
	 * Gets the lista consulta ordenes.
	 *
	 * @return the lista consulta ordenes
	 */
	public List<DatosConsultaOrdenesResponse> getListaConsultaOrdenes() {
		return listaConsultaOrdenes;
	}

	/**
	 * Sets the lista consulta ordenes.
	 *
	 * @param listaConsultaOrdenes
	 *            the new lista consulta ordenes
	 */
	public void setListaConsultaOrdenes(List<DatosConsultaOrdenesResponse> listaConsultaOrdenes) {
		this.listaConsultaOrdenes = listaConsultaOrdenes;
	}

	/**
	 * Gets the codigo error middleware.
	 *
	 * @return the codigo error middleware
	 */
	public String getCodigoErrorMiddleware() {
		return codigoErrorMiddleware;
	}

	/**
	 * Sets the codigo error middleware.
	 *
	 * @param codigoErrorMiddleware
	 *            the new codigo error middleware
	 */
	public void setCodigoErrorMiddleware(String codigoErrorMiddleware) {
		this.codigoErrorMiddleware = codigoErrorMiddleware;
	}

	
		

}

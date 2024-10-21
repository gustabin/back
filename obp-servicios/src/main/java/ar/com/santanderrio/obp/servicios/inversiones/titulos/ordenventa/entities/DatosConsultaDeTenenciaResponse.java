/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaDeTenenciaResponse.
 */
public class DatosConsultaDeTenenciaResponse {

	/** The lista tenencia. */
	@JsonProperty("ListaDeTenencias")
	private List<DatosTenencia> listaTenencia = new ArrayList<DatosTenencia>();
	
	/** The codigo error middleware. */
	@JsonProperty("CodigoErrorMiddleware")
	private String codigoErrorMiddleware;

	
	/**
	 * Gets the lista tenencia.
	 *
	 * @return the lista tenencia
	 */
	public List<DatosTenencia> getListaTenencia() {
		return listaTenencia;
	}

	/**
	 * Sets the lista tenencia.
	 *
	 * @param listaTenencia
	 *            the new lista tenencia
	 */
	public void setListaTenencia(List<DatosTenencia> listaTenencia) {
		this.listaTenencia = listaTenencia;
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

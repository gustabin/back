/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ObtenerSaldoCuentasCustodiaResponse.
 */
public class ObtenerSaldoCuentasCustodiaResponse{
	
	/** The datos. */
	@JsonProperty("Datos")
	private List<DatosObtenerSaldoCuentasCustodiaResponse> datos = new ArrayList<DatosObtenerSaldoCuentasCustodiaResponse>();

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public List<DatosObtenerSaldoCuentasCustodiaResponse> getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(List<DatosObtenerSaldoCuentasCustodiaResponse> datos) {
		this.datos = datos;
	}



}

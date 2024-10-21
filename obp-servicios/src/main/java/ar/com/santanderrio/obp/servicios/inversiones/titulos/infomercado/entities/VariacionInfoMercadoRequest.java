/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class VariacionInfoMercadoRequest.
 */
public class VariacionInfoMercadoRequest extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosVariacionInfoMercadoRequest datos = new DatosVariacionInfoMercadoRequest();

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosVariacionInfoMercadoRequest getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosVariacionInfoMercadoRequest datos) {
		this.datos = datos;
	}

}

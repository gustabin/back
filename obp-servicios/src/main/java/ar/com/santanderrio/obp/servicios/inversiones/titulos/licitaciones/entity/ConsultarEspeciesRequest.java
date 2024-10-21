/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class ConsultarEspeciesRequest.
 */
public class ConsultarEspeciesRequest extends RequestBaseFirmado{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3390999183068306638L;
	
	/** The datos. */
	@JsonProperty("Datos")
	DatosConsultaEspeciesRequestEntity datos = new DatosConsultaEspeciesRequestEntity();

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConsultaEspeciesRequestEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosConsultaEspeciesRequestEntity datos) {
		this.datos = datos;
	}
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class ConsultaInformacionMercadoRequest.
 */
public class ConsultaInformacionMercadoRequest extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosConsultaInformacionMercadoRequest datos = new DatosConsultaInformacionMercadoRequest();

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConsultaInformacionMercadoRequest getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosConsultaInformacionMercadoRequest datos) {
		this.datos = datos;
	}

}

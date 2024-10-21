/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class ConsultaCuentaTitulosRequestEntity.
 */
public class ConsultaCuentaTitulosRequestEntity extends RequestBaseFirmado{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -129908082557510559L;
	
	/** The datos. */
	@JsonProperty("Datos")
	DatosConsultaCuentaTitulosRequestEntity datos = new DatosConsultaCuentaTitulosRequestEntity();

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConsultaCuentaTitulosRequestEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosConsultaCuentaTitulosRequestEntity datos) {
		this.datos = datos;
	}
}

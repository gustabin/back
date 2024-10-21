/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class ConsultaDeTenenciaRequestEntity.
 */
public class ConsultaDeTenenciaRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The datos. */
	@JsonProperty("Datos")
	DatosConsultaTenenciaRequestEntity datos = new DatosConsultaTenenciaRequestEntity();

	
	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConsultaTenenciaRequestEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosConsultaTenenciaRequestEntity datos) {
		this.datos = datos;
	}
	
	
}

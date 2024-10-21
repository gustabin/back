/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class AperturaGraficaRequestEntity.
 */
public class AperturaGraficaRequestEntity extends RequestBaseFirmado{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7296809536411406850L;
	
	/** The datos. */
	@JsonProperty("Datos")
	DatosAperturaGraficaRequest datos = new DatosAperturaGraficaRequest();

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosAperturaGraficaRequest getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosAperturaGraficaRequest datos) {
		this.datos = datos;
	}
}

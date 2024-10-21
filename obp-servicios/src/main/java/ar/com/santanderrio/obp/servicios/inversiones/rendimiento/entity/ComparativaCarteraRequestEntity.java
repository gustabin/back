/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class ComparativaCarteraRequestEntity.
 */
public class ComparativaCarteraRequestEntity extends RequestBaseFirmado{


	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The datos. */
	@JsonProperty("Datos")
	private DatosComparativaCarteraRequest datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosComparativaCarteraRequest getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosComparativaCarteraRequest datos) {
		this.datos = datos;
	}

}

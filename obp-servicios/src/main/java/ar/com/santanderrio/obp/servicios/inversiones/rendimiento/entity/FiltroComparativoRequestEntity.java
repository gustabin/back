/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class FiltroComparativoRequestEntity.
 */
public class FiltroComparativoRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The datos. */
	@JsonProperty("Datos")
	private DatosFiltroComparativoRequest datos = new DatosFiltroComparativoRequest();
	
	
	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosFiltroComparativoRequest getDatos() {
		return datos;
	}
	
	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosFiltroComparativoRequest datos) {
		this.datos = datos;
	}
}

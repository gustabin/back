/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class RentabilidadTotalRequestEntity.
 */
public class RentabilidadTotalRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosRentabilidadTotalRequestEntity datos = new DatosRentabilidadTotalRequestEntity();

	
	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosRentabilidadTotalRequestEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosRentabilidadTotalRequestEntity datos) {
		this.datos = datos;
	}
	
	
}

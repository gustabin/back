/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class RentabilidadPeriodoRequestEntity.
 */
public class RentabilidadPeriodoRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The datos. */
	@JsonProperty("Datos")
	private DatosRentailidadPeriodo datos = new DatosRentailidadPeriodo();

	
	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosRentailidadPeriodo getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosRentailidadPeriodo datos) {
		this.datos = datos;
	}
	
	
}

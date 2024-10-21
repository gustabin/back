/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto;

import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RespuestaRentabilidadPeriodoEntity;

/**
 * The Class RentabilidadPeriodoDTO.
 */
public class RentabilidadPeriodoDTO {

	/** The datos. */
	private RespuestaRentabilidadPeriodoEntity datos;
	
	/** The hay error. */
	private Boolean hayError = false;

	
	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public RespuestaRentabilidadPeriodoEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(RespuestaRentabilidadPeriodoEntity datos) {
		this.datos = datos;
	}

	/**
	 * Gets the hay error.
	 *
	 * @return the hay error
	 */
	public Boolean getHayError() {
		return hayError;
	}

	/**
	 * Sets the hay error.
	 *
	 * @param hayError
	 *            the new hay error
	 */
	public void setHayError(Boolean hayError) {
		this.hayError = hayError;
	}
	
}

	


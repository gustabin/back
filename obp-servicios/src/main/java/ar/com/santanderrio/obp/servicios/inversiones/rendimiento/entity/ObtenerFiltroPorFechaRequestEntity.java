/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class ObtenerFiltroPorFechaRequestEntity.
 */
public class ObtenerFiltroPorFechaRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**
	 * Entidad que se usa para generar el request para el servicio
	 * ObtenerFiltroPorFecha.
	 * 
	 */
	/** The datos. */
	@JsonProperty("Datos")
	private DatosFiltroPorFechaRequestEntity datos = new DatosFiltroPorFechaRequestEntity();

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosFiltroPorFechaRequestEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosFiltroPorFechaRequestEntity datos) {
		this.datos = datos;
	}

}

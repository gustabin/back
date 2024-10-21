/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class ObtenerFiltroCarteraRequestEntity.
 */
public class ObtenerFiltroCarteraRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**
	 * Entidad que se usa para generar el request para el servicio
	 * ObtenerFiltroPorFecha.
	 * 
	 */
	/** The datos. */
	@JsonProperty("Datos")
	private DatosFiltroCarteraRequestEntity datos = new DatosFiltroCarteraRequestEntity();

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosFiltroCarteraRequestEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosFiltroCarteraRequestEntity datos) {
		this.datos = datos;
	}

}

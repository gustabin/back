/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * Entidad que se usa para generar el request para el servicio
 * ObtenerRendimientoConsolidado.
 * 
 * @author b039920
 */
public class RendimientoConsolidadoRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -573863231903350561L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosRendimientoConsolidadoRequestEntity datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosRendimientoConsolidadoRequestEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosRendimientoConsolidadoRequestEntity datos) {
		this.datos = datos;
	}
}

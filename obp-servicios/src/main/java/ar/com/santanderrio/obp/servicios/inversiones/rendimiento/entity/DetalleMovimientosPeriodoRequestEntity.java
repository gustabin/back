/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class DetalleMovimientosPeriodoRequestEntity.
 */
public class DetalleMovimientosPeriodoRequestEntity extends RequestBaseFirmado{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3594291434473350284L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosDetalleMovimientosPeriodoRequest datos;

	
	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosDetalleMovimientosPeriodoRequest getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosDetalleMovimientosPeriodoRequest datos) {
		this.datos = datos;
	}
	
}

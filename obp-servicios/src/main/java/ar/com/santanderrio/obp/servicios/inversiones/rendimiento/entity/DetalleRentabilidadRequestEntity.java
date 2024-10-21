/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class DetalleRentabilidadRequestEntity.
 */
public class DetalleRentabilidadRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The datos. */
	@JsonProperty("Datos")
	private DatosDetalleRentabilidad datos = new DatosDetalleRentabilidad();
	
	
	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosDetalleRentabilidad getDatos() {
		return datos;
	}
	
	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosDetalleRentabilidad datos) {
		this.datos = datos;
	}
}




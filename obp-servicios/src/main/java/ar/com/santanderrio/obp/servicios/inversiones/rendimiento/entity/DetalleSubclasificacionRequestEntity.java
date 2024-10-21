/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class DetalleSubclasificacionRequestEntity.
 */
public class DetalleSubclasificacionRequestEntity extends RequestBaseFirmado{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The datos. */
	@JsonProperty("Datos")
	private DatosDetalleSubclasificacionRequest datos = new DatosDetalleSubclasificacionRequest();

	
	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosDetalleSubclasificacionRequest getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosDetalleSubclasificacionRequest datos) {
		this.datos = datos;
	}

	
}

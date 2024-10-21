/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class GraficoRentabilidadRequestEntity.
 */
public class GraficoRentabilidadRequestEntity extends RequestBaseFirmado{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The datos. */
	@JsonProperty("Datos")
	private DatosGraficoRentabilidad datos = new DatosGraficoRentabilidad();
	
	
	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosGraficoRentabilidad getDatos() {
		return datos;
	}
	
	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosGraficoRentabilidad datos) {
		this.datos = datos;
	}
}

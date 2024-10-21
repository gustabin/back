/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class ConsultaAperturaEspecieRequestEntity.
 */
public class ConsultaAperturaEspecieRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosConsultaAperturaEspecieRequestEntity datos = new DatosConsultaAperturaEspecieRequestEntity();

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConsultaAperturaEspecieRequestEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosConsultaAperturaEspecieRequestEntity datos) {
		this.datos = datos;
	}

}
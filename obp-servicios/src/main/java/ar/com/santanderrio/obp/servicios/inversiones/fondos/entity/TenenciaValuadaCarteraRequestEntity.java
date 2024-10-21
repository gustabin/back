/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosDetalleFondoCarteraRequestEntity.
 *
 * @author Miguel.Muller
 **/

public class TenenciaValuadaCarteraRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7584621978746520338L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosTenenciaValuadaCarteraRequest datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosTenenciaValuadaCarteraRequest getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosTenenciaValuadaCarteraRequest datos) {
		this.datos = datos;
	}
}

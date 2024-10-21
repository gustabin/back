/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Entidad que mapea los datos para el llamado al servicio
 * ObtenerTenenciaValuadaDetalleFondoOnline.
 *
 * @author marcelo.ruiz
 */
public class DetalleFondoRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7584621978746520338L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosDetalleFondoRequestEntity datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosDetalleFondoRequestEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosDetalleFondoRequestEntity datos) {
		this.datos = datos;
	}
}

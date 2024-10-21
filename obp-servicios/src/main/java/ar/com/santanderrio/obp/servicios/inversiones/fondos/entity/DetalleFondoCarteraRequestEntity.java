/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Entidad que mapea los datos para el llamado al servicio
 * ObtenerTenenciaValuadaCarteraTotalOnline.
 *
 * @author Miguel.Mueller
 */
public class DetalleFondoCarteraRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7584621978746520338L;

	/** The datos. */
	@JsonProperty("Datos")
	private TenenciaValuadaCarteraRequestEntity datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public TenenciaValuadaCarteraRequestEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(TenenciaValuadaCarteraRequestEntity datos) {
		this.datos = datos;
	}

}

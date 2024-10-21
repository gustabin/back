/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DownloadArchivo.
 */
public class DownloadArchivo extends BaseRequestEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -533873258920203702L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosDownloadArchivo datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosDownloadArchivo getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosDownloadArchivo datos) {
		this.datos = datos;
	}
}

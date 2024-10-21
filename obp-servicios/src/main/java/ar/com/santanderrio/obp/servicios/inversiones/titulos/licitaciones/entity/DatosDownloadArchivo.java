/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosDownloadArchivo.
 */
public class DatosDownloadArchivo implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3960248303238981406L;

	/** The NombreArchivo. */
	@JsonProperty("NombreArchivo")
	private String nombreArchivo;

	/** The Repositorio. */
	@JsonProperty("Repositorio")
	private String repositorio;

	/**
	 * Gets the nombre archivo.
	 *
	 * @return the nombre archivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * Gets the repositorio.
	 *
	 * @return the repositorio
	 */
	public String getRepositorio() {
		return repositorio;
	}

	/**
	 * Sets the nombre archivo.
	 *
	 * @param nombreArchivo
	 *            the new nombre archivo
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * Sets the repositorio.
	 *
	 * @param repositorio
	 *            the new repositorio
	 */
	public void setRepositorio(String repositorio) {
		this.repositorio = repositorio;
	}
}

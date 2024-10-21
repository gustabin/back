/*
 * 
 */
package ar.com.santanderrio.obp.servicios.seguros.dto;

import ar.com.santanderrio.obp.servicios.seguros.entities.TipoSeguroImagenEnum;

/**
 * The Class TenenciasPolizaDTO.
 */
public class TenenciasPolizaDTO {

	/** The titulo. */
	private String titulo;

	/** The nombre imagen. */
	private TipoSeguroImagenEnum nombreImagen;

	/**
	 * Gets the nombre imagen.
	 *
	 * @return the nombre imagen
	 */
	public TipoSeguroImagenEnum getNombreImagen() {
		return nombreImagen;
	}

	/**
	 * Sets the nombre imagen.
	 *
	 * @param nombreImagen
	 *            the new nombre imagen
	 */
	public void setNombreImagen(TipoSeguroImagenEnum nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}

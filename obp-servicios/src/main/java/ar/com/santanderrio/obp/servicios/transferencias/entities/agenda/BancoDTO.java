/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

import ar.com.santanderrio.obp.base.entities.DTO;

/**
 * TODO: VER COMUN.
 *
 * @author B039543
 */
public class BancoDTO extends DTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The detalle. */
	private String detalle;

	/** The nombre. */
	private String nombre;

	/**
	 * Instantiates a new banco DTO.
	 */
	public BancoDTO() {
	}

	/**
	 * Instantiates a new banco DTO.
	 *
	 * @param nombre
	 *            the nombre
	 */
	public BancoDTO(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the detalle.
	 *
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * Sets the detalle.
	 *
	 * @param detalle
	 *            the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

import ar.com.santanderrio.obp.base.entities.DTO;

/**
 * The Class DestinatarioDTO.
 *
 * @author B039543
 */
public class DestinatarioDTO extends DTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The descripcion. */
	private String descripcion;

	/** The nombre. */
	private String nombre;

	/** The banco. */
	private BancoDTO banco;

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	/**
	 * Gets the banco.
	 *
	 * @return the banco
	 */
	public BancoDTO getBanco() {
		return banco;
	}

	/**
	 * Sets the banco.
	 *
	 * @param banco
	 *            the banco to set
	 */
	public void setBanco(BancoDTO banco) {
		this.banco = banco;
	}

}

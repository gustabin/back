/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity;

import java.util.Date;

/**
 * The Class OrdenBaseDTO.
 */
public abstract class OrdenBaseDTO implements Comparable<OrdenBaseDTO>{

	/** The numero. */
	private String numero;

	/** The fecha. */
	private Date fecha;

	/** The estado. */
	private String estado;

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado.trim();
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

}

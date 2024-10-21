/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.comprobante.entities;

import java.util.Date;

import ar.com.santanderrio.obp.base.entities.DTO;

/**
 * Comprobante abstracto
 * 
 * Resultado de las operaciones transaccionales.
 *
 * @author B039543
 * @param <E>
 *            tipo del numero de comprobante
 */
public abstract class AbstractComprobante<E> extends DTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The numero comprobante. */
	private E numeroComprobante;

	/** The fecha hora comprobante. */
	private Date fechaHoraComprobante;

	/**
	 * Gets the fecha hora comprobante.
	 *
	 * @return the fechaHoraComprobante
	 */
	public Date getFechaHoraComprobante() {
		return fechaHoraComprobante;
	}

	/**
	 * Sets the fecha hora comprobante.
	 *
	 * @param fechaHoraComprobante
	 *            the fechaHoraComprobante to set
	 */
	public void setFechaHoraComprobante(Date fechaHoraComprobante) {
		this.fechaHoraComprobante = fechaHoraComprobante;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numeroComprobante
	 */
	public E getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the numeroComprobante to set
	 */
	public void setNumeroComprobante(E numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

}
/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.comprobante.entities;

import java.util.Date;

/**
 * The Class BasicComprobante.
 *
 * @author dante.omar.olmedo
 */
public class BasicComprobante {

	/** The mensaje efectivizacion. */
	private String mensajeEfectivizacion;

	/** The fecha. */
	private Date fecha;

	/** The hora. */
	private String hora;

	/** The nro comprobante. */
	private String nroComprobante;

	/**
	 * Gets the mensaje efectivizacion.
	 *
	 * @return the mensajeEfectivizacion
	 */
	public String getMensajeEfectivizacion() {
		return mensajeEfectivizacion;
	}

	/**
	 * Sets the mensaje efectivizacion.
	 *
	 * @param mensajeEfectivizacion
	 *            the mensajeEfectivizacion to set
	 */
	public void setMensajeEfectivizacion(String mensajeEfectivizacion) {
		this.mensajeEfectivizacion = mensajeEfectivizacion;
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
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

}

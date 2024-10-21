/*
 * 
 */
package ar.com.santanderrio.obp.servicios.suscripciones.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.suscripciones.entities.MensajeSuscripcion;

/**
 * The Class SuscripcionesDTO.
 */
public class SuscripcionesDTO {

	/** The titulo suscripcion. */
	private String tituloSuscripcion;

	/** The mensajes. */
	private List<MensajeSuscripcion> mensajes;

	/** The mail uno. */
	private String mailUno;

	/** The mail dos. */
	private String mailDos;

	/** The celular uno. */
	private String celularUno;

	/** The celular dos. */
	private String celularDos;

	/**
	 * Gets the titulo suscripcion.
	 *
	 * @return the titulo suscripcion
	 */
	public String getTituloSuscripcion() {
		return tituloSuscripcion;
	}

	/**
	 * Sets the titulo suscripcion.
	 *
	 * @param tituloSuscripcion
	 *            the new titulo suscripcion
	 */
	public void setTituloSuscripcion(String tituloSuscripcion) {
		this.tituloSuscripcion = tituloSuscripcion;
	}

	/**
	 * Gets the mensajes.
	 *
	 * @return the mensajes
	 */
	public List<MensajeSuscripcion> getMensajes() {
		return mensajes;
	}

	/**
	 * Sets the mensajes.
	 *
	 * @param mensajes
	 *            the new mensajes
	 */
	public void setMensajes(List<MensajeSuscripcion> mensajes) {
		this.mensajes = mensajes;
	}

	/**
	 * Gets the mail uno.
	 *
	 * @return the mail uno
	 */
	public String getMailUno() {
		return mailUno;
	}

	/**
	 * Sets the mail uno.
	 *
	 * @param mailUno
	 *            the new mail uno
	 */
	public void setMailUno(String mailUno) {
		this.mailUno = mailUno;
	}

	/**
	 * Gets the mail dos.
	 *
	 * @return the mail dos
	 */
	public String getMailDos() {
		return mailDos;
	}

	/**
	 * Sets the mail dos.
	 *
	 * @param mailDos
	 *            the new mail dos
	 */
	public void setMailDos(String mailDos) {
		this.mailDos = mailDos;
	}

	/**
	 * Gets the celular uno.
	 *
	 * @return the celular uno
	 */
	public String getCelularUno() {
		return celularUno;
	}

	/**
	 * Sets the celular uno.
	 *
	 * @param celularUno
	 *            the new celular uno
	 */
	public void setCelularUno(String celularUno) {
		this.celularUno = celularUno;
	}

	/**
	 * Gets the celular dos.
	 *
	 * @return the celular dos
	 */
	public String getCelularDos() {
		return celularDos;
	}

	/**
	 * Sets the celular dos.
	 *
	 * @param celularDos
	 *            the new celular dos
	 */
	public void setCelularDos(String celularDos) {
		this.celularDos = celularDos;
	}

}
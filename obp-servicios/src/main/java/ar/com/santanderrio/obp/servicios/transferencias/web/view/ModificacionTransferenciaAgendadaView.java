/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.view;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class ModificacionTransferenciaAgendadaView.
 */
public class ModificacionTransferenciaAgendadaView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The importe. */
	// campos del formulario con tipo concreto
	private String importe;

	/** The fecha ejecucion. */
	private String fechaEjecucion;

	/** The email activo. */
	private Boolean emailActivo;

	/** The email. */
	private String email;

	/** The mensaje email. */
	private String mensajeEmail;

	/** The concepto. */
	private String concepto;

	/** The descripcion. */
	private String descripcion;

	/** The frecuencia. */
	private String frecuencia;

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the fecha ejecucion.
	 *
	 * @return the fechaEjecucion
	 */
	public String getFechaEjecucion() {
		return fechaEjecucion;
	}

	/**
	 * Sets the fecha ejecucion.
	 *
	 * @param fechaEjecucion
	 *            the fechaEjecucion to set
	 */
	public void setFechaEjecucion(String fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	/**
	 * Gets the email activo.
	 *
	 * @return the emailActivo
	 */
	public Boolean getEmailActivo() {
		return emailActivo;
	}

	/**
	 * Sets the email activo.
	 *
	 * @param emailActivo
	 *            the emailActivo to set
	 */
	public void setEmailActivo(Boolean emailActivo) {
		this.emailActivo = emailActivo;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the mensaje email.
	 *
	 * @return the mensajeEmail
	 */
	public String getMensajeEmail() {
		return mensajeEmail;
	}

	/**
	 * Sets the mensaje email.
	 *
	 * @param mensajeEmail
	 *            the mensajeEmail to set
	 */
	public void setMensajeEmail(String mensajeEmail) {
		this.mensajeEmail = mensajeEmail;
	}

	/**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto
	 *            the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

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
	 * Gets the frecuencia.
	 *
	 * @return the frecuencia
	 */
	public String getFrecuencia() {
		return frecuencia;
	}

	/**
	 * Sets the frecuencia.
	 *
	 * @param frecuencia
	 *            the frecuencia to set
	 */
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;

/**
 * The Class ModificacionTransferenciaAgendadaDTO.
 */
public class ModificacionTransferenciaAgendadaDTO
		extends AbstractAccionTransferenciaAgendada<ComprobanteModificacionDTO> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The importe. */
	// campos del formulario con tipo concreto
	private BigDecimal importe;

	/** The fecha ejecucion. */
	private Date fechaEjecucion;

	/** The email activo. */
	private Boolean emailActivo;

	/** The email. */
	private String email;

	/** The mensaje email. */
	private String mensajeEmail;

	/** The concepto. */
	private ConceptoTransferenciaEnum concepto;

	/** The descripcion. */
	// TODO: ver que hace este campo
	private String descripcion;

	/** The frecuencia. */
	private FrecuenciaTransferenciaAgendada frecuencia;

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the fecha ejecucion.
	 *
	 * @return the fechaEjecucion
	 */
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	/**
	 * Sets the fecha ejecucion.
	 *
	 * @param fechaEjecucion
	 *            the fechaEjecucion to set
	 */
	public void setFechaEjecucion(Date fechaEjecucion) {
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
	public ConceptoTransferenciaEnum getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto
	 *            the concepto to set
	 */
	public void setConcepto(ConceptoTransferenciaEnum concepto) {
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
	public FrecuenciaTransferenciaAgendada getFrecuencia() {
		return frecuencia;
	}

	/**
	 * Sets the frecuencia.
	 *
	 * @param frecuencia
	 *            the frecuencia to set
	 */
	public void setFrecuencia(FrecuenciaTransferenciaAgendada frecuencia) {
		this.frecuencia = frecuencia;
	}

}

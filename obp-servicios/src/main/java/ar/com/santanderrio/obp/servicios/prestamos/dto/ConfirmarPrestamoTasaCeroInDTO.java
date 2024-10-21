/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;


// TODO: Auto-generated Javadoc
/**
 * The Class ConfirmarPrestamoTasaCeroInDTO.
 */
public class ConfirmarPrestamoTasaCeroInDTO {

	/** The celular. */
	private String celular;

	/** The email. */	
	private String email;
	
	/** The cuit. */
	private String cuit;
	
	/** The legal. */	
	private String legal;
	
	/** The tarjeta. */
	private String tarjeta;
	
	/** The importe solicitado. */
	private String importeSolicitado;
	
	/** The tipo operacion. */
	private String tipoOperacion;
	
	/** The tipo solicitud. */
	private String tipoSolicitud;
	
	/** The emitir tarjeta. */
	private Boolean emitirTarjeta;
	
	/** The acepto TY cemitir tarjeta. */
	private Boolean aceptoTYCemitirTarjeta;
	
	/** The acepto TYC prestamo tasa cero. */
	private Boolean aceptoTYCPrestamoTasaCero;
	
	
	/**
	 * Gets the importe solicitado.
	 *
	 * @return the importe solicitado
	 */
	public String getImporteSolicitado() {
		return importeSolicitado;
	}

	/**
	 * Sets the importe solicitado.
	 *
	 * @param importeSolicitado the new importe solicitado
	 */
	public void setImporteSolicitado(String importeSolicitado) {
		this.importeSolicitado = importeSolicitado;
	}

	/**
	 * Gets the celular.
	 *
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * Sets the celular.
	 *
	 * @param celular the new celular
	 */
	public void setCelular(String celular) {
		this.celular = celular;
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
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit the new cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal the new legal
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	/**
	 * Gets the tarjeta.
	 *
	 * @return the tarjeta
	 */
	public String getTarjeta() {
		return tarjeta;
	}

	/**
	 * Sets the tarjeta.
	 *
	 * @param tarjeta the new tarjeta
	 */
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	/**
	 * Gets the emitir tarjeta.
	 *
	 * @return the emitir tarjeta
	 */
	public Boolean getEmitirTarjeta() {
		return emitirTarjeta;
	}

	/**
	 * Sets the emitir tarjeta.
	 *
	 * @param emitirTarjeta the new emitir tarjeta
	 */
	public void setEmitirTarjeta(Boolean emitirTarjeta) {
		this.emitirTarjeta = emitirTarjeta;
	}

	/**
	 * Gets the acepto TY cemitir tarjeta.
	 *
	 * @return the acepto TY cemitir tarjeta
	 */
	public Boolean getAceptoTYCemitirTarjeta() {
		return aceptoTYCemitirTarjeta;
	}

	/**
	 * Sets the acepto TY cemitir tarjeta.
	 *
	 * @param aceptoTYCemitirTarjeta the new acepto TY cemitir tarjeta
	 */
	public void setAceptoTYCemitirTarjeta(Boolean aceptoTYCemitirTarjeta) {
		this.aceptoTYCemitirTarjeta = aceptoTYCemitirTarjeta;
	}

	/**
	 * Gets the acepto TYC prestamo tasa cero.
	 *
	 * @return the acepto TYC prestamo tasa cero
	 */
	public Boolean getAceptoTYCPrestamoTasaCero() {
		return aceptoTYCPrestamoTasaCero;
	}

	/**
	 * Sets the acepto TYC prestamo tasa cero.
	 *
	 * @param aceptoTYCPrestamoTasaCero the new acepto TYC prestamo tasa cero
	 */
	public void setAceptoTYCPrestamoTasaCero(Boolean aceptoTYCPrestamoTasaCero) {
		this.aceptoTYCPrestamoTasaCero = aceptoTYCPrestamoTasaCero;
	}

	/**
	 * Gets the tipo operacion.
	 *
	 * @return the tipo operacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 *
	 * @param tipoOperacion the new tipo operacion
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * Gets the tipo solicitud.
	 *
	 * @return the tipo solicitud
	 */
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	/**
	 * Sets the tipo solicitud.
	 *
	 * @param tipoSolicitud the new tipo solicitud
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

    @Override
    public String toString() {
        return "ConfirmarPrestamoTasaCeroInDTO [celular=" + celular + ", email=" + email + ", cuit=" + cuit + ", legal="
                + legal + ", tarjeta=" + tarjeta + ", importeSolicitado=" + importeSolicitado + ", tipoOperacion="
                + tipoOperacion + ", tipoSolicitud=" + tipoSolicitud + ", emitirTarjeta=" + emitirTarjeta
                + ", aceptoTYCemitirTarjeta=" + aceptoTYCemitirTarjeta + ", aceptoTYCPrestamoTasaCero="
                + aceptoTYCPrestamoTasaCero + "]";
    }

}

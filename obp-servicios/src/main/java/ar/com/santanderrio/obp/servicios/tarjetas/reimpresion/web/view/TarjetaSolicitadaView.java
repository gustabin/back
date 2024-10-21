/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view;

/**
 * The Class tarjetaAdicionalSolicitadaView.
 */
public class TarjetaSolicitadaView {

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The nroTarjetaConFormato. */
	private String nroTarjetaConFormato;

	/** The motivo. */
	private String motivo;

	/** The solicitud nro. */
	private String solicitudNro;

	/** The titular. */
	private String titular;

	/** The alias. */
	private String alias;

	/** The is ok. */
	private Boolean isOk;

	/** The mensaje. */
	private String mensaje;

	/** The solicitud en curso. */
	private Boolean solicitudEnCurso;

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipoCuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the solicitud nro.
	 *
	 * @return the solicitudNro
	 */
	public String getNroTarjetaConFormato() {
		return nroTarjetaConFormato;
	}

	/**
	 * Sets the nroTarjetaConFormato.
	 *
	 * @param nroTarjetaConFormato
	 *            the nroTarjetaConFormato to set
	 */
	public void setNroTarjetaConFormato(String nroTarjetaConFormato) {
		this.nroTarjetaConFormato = nroTarjetaConFormato;
	}

	/**
	 * Gets the solicitud nro.
	 *
	 * @return the solicitudNro
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * Sets the motivo.
	 *
	 * @param motivo
	 *            the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * Gets the solicitud nro.
	 *
	 * @return the solicitudNro
	 */
	public String getSolicitudNro() {
		return solicitudNro;
	}

	/**
	 * Sets the solicitud nro.
	 *
	 * @param solicitudNro
	 *            the solicitudNro to set
	 */
	public void setSolicitudNro(String solicitudNro) {
		this.solicitudNro = solicitudNro;
	}

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the isOK.
	 *
	 * @return the alias
	 */
	public Boolean getIsOk() {
		return isOk;
	}

	/**
	 * Sets the checks if is ok.
	 *
	 * @param isOk
	 *            the new checks if is ok
	 */
	public void setIsOk(Boolean isOk) {
		this.isOk = isOk;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the solicitud en curso.
	 *
	 * @return the solicitud en curso
	 */
	public Boolean getSolicitudEnCurso() {
		return solicitudEnCurso;
	}

	/**
	 * Sets the solicitud en curso.
	 *
	 * @param solicitudEnCurso
	 *            the new solicitud en curso
	 */
	public void setSolicitudEnCurso(Boolean solicitudEnCurso) {
		this.solicitudEnCurso = solicitudEnCurso;
	}

}

package ar.com.santanderrio.obp.servicios.ejecutivoselect.entities;

/**
 * The Class ConsultaTelefonoOperadorInEntity.
 */
public class ConsultaTelefonoOperadorInEntity {

	/** The session id. */
	private String sessionId;
	
	/** The nup cliente. */
	private String nupCliente;

	/** The telefono. */
	private String telefono;

	/** The canal. */
	private String canal;

	/** The usuario racf. */
	private String usuarioRacf;

	/** The password racf. */
	private String passwordRacf;

	/** The codigo accion. */
	private String codigoAccion;

	/** The nro doc. */
	private String nroDoc;

	/** The tipo persona. */
	private String tipoPersona;

	/**
	 * Instantiates a new consulta telefono operador in entity.
	 */
	public ConsultaTelefonoOperadorInEntity() {
		super();
	}

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionId
	 *            the new session id
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Sets the telefono.
	 *
	 * @param telefono
	 *            the new telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the new canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the usuario racf.
	 *
	 * @return the usuario racf
	 */
	public String getUsuarioRacf() {
		return usuarioRacf;
	}

	/**
	 * Sets the usuario racf.
	 *
	 * @param usuarioRacf
	 *            the new usuario racf
	 */
	public void setUsuarioRacf(String usuarioRacf) {
		this.usuarioRacf = usuarioRacf;
	}

	/**
	 * Gets the password racf.
	 *
	 * @return the password racf
	 */
	public String getPasswordRacf() {
		return passwordRacf;
	}

	/**
	 * Sets the password racf.
	 *
	 * @param passwordRacf
	 *            the new password racf
	 */
	public void setPasswordRacf(String passwordRacf) {
		this.passwordRacf = passwordRacf;
	}

	/**
	 * Gets the codigo accion.
	 *
	 * @return the codigo accion
	 */
	public String getCodigoAccion() {
		return codigoAccion;
	}

	/**
	 * Sets the codigo accion.
	 *
	 * @param codigoAccion
	 *            the new codigo accion
	 */
	public void setCodigoAccion(String codigoAccion) {
		this.codigoAccion = codigoAccion;
	}

	/**
	 * Gets the nro doc.
	 *
	 * @return the nro doc
	 */
	public String getNroDoc() {
		return nroDoc;
	}

	/**
	 * Sets the nro doc.
	 *
	 * @param nroDoc
	 *            the new nro doc
	 */
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	/**
	 * Gets the tipo persona.
	 *
	 * @return the tipo persona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * Sets the tipo persona.
	 *
	 * @param tipoPersona
	 *            the new tipo persona
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Gets the nup cliente.
	 *
	 * @return the nup cliente
	 */
	public String getNupCliente() {
		return nupCliente;
	}

	/**
	 * Sets the nup cliente.
	 *
	 * @param nupCliente the new nup cliente
	 */
	public void setNupCliente(String nupCliente) {
		this.nupCliente = nupCliente;
	}

	@Override
	public String toString() {
		return "ConsultaTelefonoOperadorInEntity [sessionId=" + sessionId + ", nupCliente=" + nupCliente + ", telefono="
				+ telefono + ", canal=" + canal + ", usuarioRacf=" + usuarioRacf + ", passwordRacf=" + passwordRacf
				+ ", codigoAccion=" + codigoAccion + ", nroDoc=" + nroDoc + ", tipoPersona=" + tipoPersona + "]";
	}

}

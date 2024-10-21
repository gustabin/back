package ar.com.santanderrio.obp.servicios.ejecutivoselect.dto;

/**
 * The Class NotificacionLlamadaPorTelDTOIn.
 */
public class NotificacionLlamadaPorTelDTOIn {

	/** The session id. */
	private String sessionId;

	
	/**
	 * Instantiates a new notificacion llamada por tel DTO in.
	 */
	public NotificacionLlamadaPorTelDTOIn() {
		super();
	}

	
	public NotificacionLlamadaPorTelDTOIn(String sessionId) {
		super();
		this.sessionId = sessionId;
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

}

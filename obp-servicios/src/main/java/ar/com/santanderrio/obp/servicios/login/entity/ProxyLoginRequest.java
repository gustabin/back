package ar.com.santanderrio.obp.servicios.login.entity;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * The Class ProxyLoginRequest.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProxyLoginRequest {

	/** The actual pin. */
	private String actualPin;

	/** The new pin. */
	private String newPin;

	/** The actual password. */
	private String actualPassword;

	/** The new password. */
	private String newPassword;

	/** The session id. */
	private String sessionId;

	/**
	 * Instantiates a new proxy login request.
	 */
	public ProxyLoginRequest() {
	}

	/**
	 * Gets the actual pin.
	 *
	 * @return the actual pin
	 */
	public String getActualPin() {
		return actualPin;
	}

	/**
	 * Sets the actual pin.
	 *
	 * @param actualPin the new actual pin
	 */
	public void setActualPin(String actualPin) {
		this.actualPin = actualPin;
	}

	/**
	 * Gets the new pin.
	 *
	 * @return the new pin
	 */
	public String getNewPin() {
		return newPin;
	}

	/**
	 * Sets the new pin.
	 *
	 * @param newPin the new new pin
	 */
	public void setNewPin(String newPin) {
		this.newPin = newPin;
	}

	/**
	 * Gets the actual password.
	 *
	 * @return the actual password
	 */
	public String getActualPassword() {
		return actualPassword;
	}

	/**
	 * Sets the actual password.
	 *
	 * @param actualPassword the new actual password
	 */
	public void setActualPassword(String actualPassword) {
		this.actualPassword = actualPassword;
	}

	/**
	 * Gets the new password.
	 *
	 * @return the new password
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Sets the new password.
	 *
	 * @param newPassword the new new password
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
	 * @param sessionId the new session id
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}

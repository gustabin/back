package ar.com.santanderrio.obp.servicios.queue.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class TurnLoginRequest.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnLoginRequest {

	/** The application name. */
	@JsonProperty("applicationName")
	private String applicationName;

	/** The password. */
	@JsonProperty("password")
	private String password;

	/**
	 * Instantiates a new queue turn login request.
	 *
	 * @param applicationName the application name
	 * @param password the password
	 */
	public TurnLoginRequest(String applicationName, String password) {
		super();
		this.applicationName = applicationName;
		this.password = password;
	}

	/**
	 * Gets the application name.
	 *
	 * @return the application name
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * Sets the application name.
	 *
	 * @param applicationName the new application name
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}

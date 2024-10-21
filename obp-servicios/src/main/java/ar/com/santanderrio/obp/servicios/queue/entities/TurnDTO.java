package ar.com.santanderrio.obp.servicios.queue.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class TurnDTO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnDTO {

	/** The date created. */
	@JsonProperty("dateCreated")
	private String dateCreated;

	/** The waiting time. */
	@JsonProperty("waitingTime")
	private String waitingTime;

	/** The remaining time. */
	@JsonProperty("remainingTime")
	private String remainingTime;

	/** The is used. */
	@JsonProperty("isUsed")
	private String isUsed;

	/** The status. */
	@JsonProperty("status")
	private String status;

	/** The config id. */
	@JsonProperty("configId")
	private String configId;

	/** The lifetime. */
	@JsonProperty("lifetime")
	private String lifetime;

	/** The nup. */
	@JsonProperty("nup")
	private String nup;

	/** The dni. */
	@JsonProperty("dni")
	private String dni;

	/** The id. */
	@JsonProperty("_id")
	private String id;

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public String getDateCreated() {
		return dateCreated;
	}

	/**
	 * Sets the date created.
	 *
	 * @param dateCreated the new date created
	 */
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * Gets the waiting time.
	 *
	 * @return the waiting time
	 */
	public String getWaitingTime() {
		return waitingTime;
	}

	/**
	 * Sets the waiting time.
	 *
	 * @param waitingTime the new waiting time
	 */
	public void setWaitingTime(String waitingTime) {
		this.waitingTime = waitingTime;
	}

	/**
	 * Gets the remaining time.
	 *
	 * @return the remaining time
	 */
	public String getRemainingTime() {
		return remainingTime;
	}

	/**
	 * Sets the remaining time.
	 *
	 * @param remainingTime the new remaining time
	 */
	public void setRemainingTime(String remainingTime) {
		this.remainingTime = remainingTime;
	}

	/**
	 * Gets the checks if is used.
	 *
	 * @return the checks if is used
	 */
	public String getIsUsed() {
		return isUsed;
	}

	/**
	 * Sets the checks if is used.
	 *
	 * @param isUsed the new checks if is used
	 */
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the config id.
	 *
	 * @return the config id
	 */
	public String getConfigId() {
		return configId;
	}

	/**
	 * Sets the config id.
	 *
	 * @param configId the new config id
	 */
	public void setConfigId(String configId) {
		this.configId = configId;
	}

	/**
	 * Gets the lifetime.
	 *
	 * @return the lifetime
	 */
	public String getLifetime() {
		return lifetime;
	}

	/**
	 * Sets the lifetime.
	 *
	 * @param lifetime the new lifetime
	 */
	public void setLifetime(String lifetime) {
		this.lifetime = lifetime;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni the new dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

}

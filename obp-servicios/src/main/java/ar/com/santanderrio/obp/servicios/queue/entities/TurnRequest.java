package ar.com.santanderrio.obp.servicios.queue.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class TurnRequest.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class TurnRequest {

	/** The dni. */
	@JsonProperty("dni")
	private Integer dni;

	/** The status. */
	@JsonProperty("status")
	private String status;

	/**
	 * Instantiates a new turn request.
	 *
	 * @param dni the dni
	 * @param status the status
	 */
	public TurnRequest(Integer dni, String status) {
		super();
		this.dni = dni;
		this.status = status;
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public Integer getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni the new dni
	 */
	public void setDni(Integer dni) {
		this.dni = dni;
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

}

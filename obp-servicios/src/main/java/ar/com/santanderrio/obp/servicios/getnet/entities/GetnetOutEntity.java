package ar.com.santanderrio.obp.servicios.getnet.entities;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class GetNetOutEntity.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class GetnetOutEntity {

	/** The enabled. */
	@JsonProperty("enabled")
	private Boolean enabled = false;
	
	/** The cuit. */
	@JsonProperty("cuit")
	private String cuit;
	
	/** The cvu. */
	@JsonProperty("cvu")
	private String cvu;
	
	/** The email address. */
	@JsonProperty("email_address")
	private String email;
	
	/** The external id. */
	@JsonProperty("external_id")
	private Long externalId;
	
	/** The id. */
	@JsonProperty("id")
	private Integer id;
	
	/** The nup. */
	@JsonProperty("nup")
	private Integer nup;
	
	/** The person type. */
	@JsonProperty("person_type")
	private String personType;
	
	/** The state. */
	@JsonProperty("state")
	private String state;
	
	/** The error. */
	@JsonProperty("error")
	private GetnetErrorOutEntity error;
	
	/**
	 * Is enabled.
	 *
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled
	 *            the new enabled
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
	 * @param cuit
	 *            the new cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	/**
	 * Gets the cvu.
	 *
	 * @return the cvu
	 */
	public String getCvu() {
		return cvu;
	}

	/**
	 * Sets the cvu.
	 *
	 * @param cvu
	 *            the new cvu
	 */
	public void setCvu(String cvu) {
		this.cvu = cvu;
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
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the external id.
	 *
	 * @return the external id
	 */
	public Long getExternalId() {
		return externalId;
	}

	/**
	 * Sets the external id.
	 *
	 * @param externalId.
	 *            the new external id.
	 */
	public void setExternalId(Long externalId) {
		this.externalId = externalId;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public Integer getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(Integer nup) {
		this.nup = nup;
	}
	
	/**
	 * Gets the person type.
	 *
	 * @return the person type
	 */
	public String getPersonType() {
		return personType;
	}

	/**
	 * Sets the person type.
	 *
	 * @param personType
	 *            the new person type
	 */
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state
	 *            the new state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public GetnetErrorOutEntity getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error
	 *            the new GetNetErrorOutEntity
	 */
	public void setError(GetnetErrorOutEntity error) {
		this.error = error;
	}
	
}

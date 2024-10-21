/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * The Class RsaCreateUserRequestData.
 *
 * @author Ignacio_Valek
 */
public class RsaCreateUserRequestData extends RsaRequestData {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The event type. */
	private String eventType;

	/** The user type. */
	private String userType;

	/**
	 * Instantiates a new rsa create user request data.
	 */
	public RsaCreateUserRequestData() {
		super();
	}

	/**
	 * Gets the event type.
	 *
	 * @return the event type
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * Setter para event type.
	 *
	 * @param eventType
	 *            el nuevo event type
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * Setter para user type.
	 *
	 * @param userType
	 *            el nuevo user type
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((getRsaGenericRequestData() == null) ? 0 : getRsaGenericRequestData().hashCode());
		result = prime * result + ((getUserStatus() == null) ? 0 : getUserStatus().hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj.getClass().equals(this.getClass()))) {
			return false;
		}
		RsaCreateUserRequestData other = (RsaCreateUserRequestData) obj;

		EqualsBuilder eb = new EqualsBuilder().append(eventType, other.eventType)
				.append(getRsaGenericRequestData(), other.getRsaGenericRequestData())
				.append(getUserStatus(), other.getRsaGenericRequestData())
				.append(getUserStatus(), other.getUserStatus()).append(userType, other.userType);
		return eb.isEquals();

	}

}

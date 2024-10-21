/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * The Class RsaCreateUserResponseData.
 *
 * @author Ignacio_Valek
 */
public class RsaCreateUserResponseData extends AbstractRsaResponseData {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new rsa create user response data.
	 */
	public RsaCreateUserResponseData() {
		this.setRsaGenericResponseData(new RsaGenericResponseData());
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
		RsaCreateUserResponseData other = (RsaCreateUserResponseData) obj;
		EqualsBuilder eb = new EqualsBuilder().append(getRsaGenericResponseData(), other.getRsaGenericResponseData());
		return eb.isEquals();
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
		result = prime * result + ((getRsaGenericResponseData() == null) ? 0 : getRsaGenericResponseData().hashCode());
		return result;
	}
}

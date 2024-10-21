/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.entities;

/**
 * The Class RsaNotifyResponseData.
 *
 * @author Ignacio_Valek
 */
public class RsaNotifyResponseData extends AbstractRsaResponseData {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new rsa notify response data.
	 */
	public RsaNotifyResponseData() {
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
		RsaNotifyResponseData other = (RsaNotifyResponseData) obj;
		if (getRsaGenericResponseData() == null) {
			if (other.getRsaGenericResponseData() != null) {
				return false;
			}
		} else if (!getRsaGenericResponseData().equals(other.getRsaGenericResponseData())) {
			return false;
		}
		return true;
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

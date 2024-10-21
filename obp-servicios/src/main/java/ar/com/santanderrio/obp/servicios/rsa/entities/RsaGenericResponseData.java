/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class RsaGenericResponseData.
 *
 * @author Ignacio_Valek
 */
public class RsaGenericResponseData extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dr status code. */
	// device result
	private String drStatusCode;

	/** The dr status description. */
	private String drStatusDescription;

	/** The device token cookie. */
	private String deviceTokenCookie;

	/** The device token fso. */
	private String deviceTokenFso;

	/** The sh reason code. */
	// Status header
	private String shReasonCode;

	/** The sh reason description. */
	private String shReasonDescription;

	/** The sh status code. */
	private String shStatusCode;

	/** The rsa cookie. */
	// RSA_COOKIE
	private String rsaCookie;

	/** The transaction id. */
	// identificationData
	private String transactionId;

	/**
	 * Gets the dr status code.
	 *
	 * @return the dr status code
	 */
	public String getDrStatusCode() {
		return drStatusCode;
	}

	/**
	 * Setter para dr status code.
	 *
	 * @param drStatusCode
	 *            el nuevo dr status code
	 */
	public void setDrStatusCode(String drStatusCode) {
		this.drStatusCode = drStatusCode;
	}

	/**
	 * Gets the dr status description.
	 *
	 * @return the dr status description
	 */
	public String getDrStatusDescription() {
		return drStatusDescription;
	}

	/**
	 * Setter para dr status description.
	 *
	 * @param drStatusDescription
	 *            el nuevo dr status description
	 */
	public void setDrStatusDescription(String drStatusDescription) {
		this.drStatusDescription = drStatusDescription;
	}

	/**
	 * Gets the dr device token cookie.
	 *
	 * @return the dr device token cookie
	 */
	public String getDeviceTokenCookie() {
		return deviceTokenCookie;
	}

	/**
	 * Setter para dr device token cookie.
	 *
	 * @param deviceTokenCookie
	 *            el nuevo dr device token cookie
	 */
	public void setDeviceTokenCookie(String deviceTokenCookie) {
		this.deviceTokenCookie = deviceTokenCookie;
	}

	/**
	 * Gets the dr device token fso.
	 *
	 * @return the dr device token fso
	 */
	public String getDeviceTokenFso() {
		return deviceTokenFso;
	}

	/**
	 * Setter para dr device token fso.
	 *
	 * @param deviceTokenFso
	 *            el nuevo dr device token fso
	 */
	public void setDeviceTokenFso(String deviceTokenFso) {
		this.deviceTokenFso = deviceTokenFso;
	}

	/**
	 * Gets the sh reason code.
	 *
	 * @return the sh reason code
	 */
	public String getShReasonCode() {
		return shReasonCode;
	}

	/**
	 * Setter para sh reason code.
	 *
	 * @param shReasonCode
	 *            el nuevo sh reason code
	 */
	public void setShReasonCode(String shReasonCode) {
		this.shReasonCode = shReasonCode;
	}

	/**
	 * Gets the sh reason description.
	 *
	 * @return the sh reason description
	 */
	public String getShReasonDescription() {
		return shReasonDescription;
	}

	/**
	 * Setter para sh reason description.
	 *
	 * @param shReasonDescription
	 *            el nuevo sh reason description
	 */
	public void setShReasonDescription(String shReasonDescription) {
		this.shReasonDescription = shReasonDescription;
	}

	/**
	 * Gets the sh status code.
	 *
	 * @return the sh status code
	 */
	public String getShStatusCode() {
		return shStatusCode;
	}

	/**
	 * Setter para sh status code.
	 *
	 * @param shStatusCode
	 *            el nuevo sh status code
	 */
	public void setShStatusCode(String shStatusCode) {
		this.shStatusCode = shStatusCode;
	}

	/**
	 * Gets the rsa cookie.
	 *
	 * @return the rsa cookie
	 */
	public String getRsaCookie() {
		return rsaCookie;
	}

	/**
	 * Setter para rsa cookie.
	 *
	 * @param rsaCookie
	 *            el nuevo rsa cookie
	 */
	public void setRsaCookie(String rsaCookie) {
		this.rsaCookie = rsaCookie;
	}
	
	/*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RsaGenericResponseData ["
                + (transactionId != null ? "transactionId=" + transactionId + ", " : "")
                + (drStatusCode != null ? "drStatusCode=" + drStatusCode + ", " : "")
                + (drStatusDescription != null ? "drStatusDescription=" + drStatusDescription + ", " : "")
                + (deviceTokenCookie != null ? "deviceTokenCookie=" + deviceTokenCookie + ", " : "")
                + (deviceTokenFso != null ? "deviceTokenFso=" + deviceTokenFso + ", " : "")
                + (shReasonDescription != null ? "shReasonDescription=" + shReasonDescription + ", " : "")
                + (shStatusCode != null ? "shStatusCode=" + shStatusCode + ", " : "")
                + (shReasonCode != null ? "shReasonCode=" + shReasonCode : "") + "]";
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	/*
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(deviceTokenCookie);
		hcb.append(deviceTokenFso);
		hcb.append(drStatusCode);
		hcb.append(shReasonCode);
		hcb.append(shStatusCode);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.entities.Entity#equals(java.lang.Object)
	 */
	/*
	 * 
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
		RsaGenericResponseData other = (RsaGenericResponseData) obj;
		return new EqualsBuilder().append(deviceTokenCookie, other.deviceTokenCookie)
				.append(deviceTokenFso, other.deviceTokenFso).append(drStatusCode, other.drStatusCode)
				.append(shReasonCode, other.shReasonCode).append(shStatusCode, other.shStatusCode).isEquals();
	}

	/**
	 * Gets the transaction id.
	 *
	 * @return the transaction id
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * Sets the transaction id.
	 *
	 * @param transactionId
	 *            the new transaction id
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}

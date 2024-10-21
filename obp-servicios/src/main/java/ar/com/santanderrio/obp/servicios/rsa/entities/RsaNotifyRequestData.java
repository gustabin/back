/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class RsaNotifyRequestData.
 *
 * @author Ignacio_Valek
 */
public class RsaNotifyRequestData extends RsaRequestData {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The event type. */
	/* Parametros que se pasan al crear un RSANotify */
	private String eventType;

	/** The try count. */
	private Integer tryCount;

	/** The level. */
	private Integer level;

	/** The success. */
	private boolean success;

	/**
	 * Instantiates a new rsa notify request data.
	 */
	public RsaNotifyRequestData() {
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
	 * Gets the try count.
	 *
	 * @return the try count
	 */
	public Integer getTryCount() {
		return tryCount;
	}

	/**
	 * Setter para try count.
	 *
	 * @param tryCount
	 *            el nuevo try count
	 */
	public void setTryCount(Integer tryCount) {
		this.tryCount = tryCount;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * Setter para level.
	 *
	 * @param level
	 *            el nuevo level
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * Checks if is success.
	 *
	 * @return true, if is success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Setter para success.
	 *
	 * @param success
	 *            el nuevo success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(eventType);
		hcb.append(level);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
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
		if (!(obj.getClass().equals((this.getClass())))) {
			return false;
		}
		RsaNotifyRequestData other = (RsaNotifyRequestData) obj;
		return new EqualsBuilder().append(eventType, other.eventType).append(level, other.level).isEquals();
	}

}

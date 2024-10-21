/*
 * 
 */
package ar.com.santanderrio.obp.servicios.session.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by pablo.martin.gore on 9/1/2016.
 */
public class SessionControlDTO {

	/** The nup. */
	private Long nup;

	/** The token. */
	private String token;

	/** The insert. */
	private Date insert;

	/** The update. */
	private Date update;

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public Long getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(Long nup) {
		this.nup = nup;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token
	 *            the new token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the insert.
	 *
	 * @return the insert
	 */
	public Date getInsert() {
		return insert;
	}

	/**
	 * Sets the insert.
	 *
	 * @param insert
	 *            the new insert
	 */
	public void setInsert(Date insert) {
		this.insert = insert;
	}

	/**
	 * Gets the update.
	 *
	 * @return the update
	 */
	public Date getUpdate() {
		return update;
	}

	/**
	 * Sets the update.
	 *
	 * @param update
	 *            the new update
	 */
	public void setUpdate(Date update) {
		this.update = update;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("nup", nup).append("insert", insert).append("update", update)
				.append("token", token).toString();
	}
}
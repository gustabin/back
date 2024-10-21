/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.common;

import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;

/**
 * The Class RSAResponse.
 */
public class RSAResponse {

	/** The action code. */
	private ActionCode actionCode;

	/** The user status. */
	private String userStatus;

	/**
	 * Gets the action code.
	 *
	 * @return the action code
	 */
	public ActionCode getActionCode() {
		return actionCode;
	}

	/**
	 * Sets the action code.
	 *
	 * @param actionCode
	 *            the new action code
	 */
	public void setActionCode(ActionCode actionCode) {
		this.actionCode = actionCode;
	}

	/**
	 * Gets the user status.
	 *
	 * @return the user status
	 */
	public String getUserStatus() {
		return userStatus;
	}

	/**
	 * Sets the user status.
	 *
	 * @param userStatus
	 *            the new user status
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

}

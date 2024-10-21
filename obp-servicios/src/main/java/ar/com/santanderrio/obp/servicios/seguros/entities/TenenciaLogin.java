/**
 * 
 */
package ar.com.santanderrio.obp.servicios.seguros.entities;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * The Class TenenciaLogin.
 *
 * @author sergio.e.goldentair
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TenenciaLogin implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1727804524879188137L;

	/** The user. */
	private String user;

	/** The password. */
	private String password;

	/** The canal. */
	@JsonIgnore
	private String canal;

	/** token de login. */
	private String loginToken = null;

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the canal to set
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the login token.
	 *
	 * @return the loginToken
	 */
	public String getLoginToken() {
		return loginToken;
	}

	/**
	 * Sets the login token.
	 *
	 * @param loginToken
	 *            the loginToken to set
	 */
	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

}

package ar.com.santanderrio.obp.servicios.login.entity;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * The Class ApiAuthRequest.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ApiAuthRequest {

	private String documentNumber;
	private String password;
	private String username;
	private String newPassword;
	private String newUserName;
	private String birthDate;
	private String client_id;
	private String client_secret;
	/**
	 * @return the documentNumber
	 */
	public String getDocumentNumber() {
		return documentNumber;
	}
	/**
	 * @param documentNumber the documentNumber to set
	 */
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	/**
	 * @return the newUserName
	 */
	public String getNewUserName() {
		return newUserName;
	}
	/**
	 * @param newUserName the newUserName to set
	 */
	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}
	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return the client_id
	 */
	public String getClient_id() {
		return client_id;
	}
	/**
	 * @param client_id the client_id to set
	 */
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	/**
	 * @return the client_secret
	 */
	public String getClient_secret() {
		return client_secret;
	}
	/**
	 * @param client_secret the client_secret to set
	 */
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}
	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	private String scope;


}

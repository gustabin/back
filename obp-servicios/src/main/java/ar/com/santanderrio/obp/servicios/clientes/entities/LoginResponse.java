package ar.com.santanderrio.obp.servicios.clientes.entities;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class LoginResponse.
 */
public class LoginResponse implements Serializable {

	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;

	/** The exp. */
	@JsonProperty("exp")
	private String exp;

	/** The iat. */
	@JsonProperty("iat")
	private String iat;

	/** The unique person number. */
	@JsonProperty("uniquePersonNumber")
	private String uniquePersonNumber;

	/** The birth date. */
	@JsonProperty("birthDate")
	private String birthDate;

	/** The user type. */
	@JsonProperty("userType")
	private String userType;

	/** The anph flag. */
	@JsonProperty("anphFlag")
	private String anphFlag;

	/** The password type. */
	@JsonProperty("passwordType")
	private String passwordType;

	/** The ticket number. */
	@JsonProperty("ticketNumber")
	private String ticketNumber;

	/**
	 * Gets the exp.
	 *
	 * @return the exp
	 */
	public String getExp() {
		return exp;
	}

	/**
	 * Sets the exp.
	 *
	 * @param exp the new exp
	 */
	public void setExp(String exp) {
		this.exp = exp;
	}

	/**
	 * Gets the iat.
	 *
	 * @return the iat
	 */
	public String getIat() {
		return iat;
	}

	/**
	 * Sets the iat.
	 *
	 * @param iat the new iat
	 */
	public void setIat(String iat) {
		this.iat = iat;
	}

	/**
	 * Gets the unique person number.
	 *
	 * @return the unique person number
	 */
	public String getUniquePersonNumber() {
		return uniquePersonNumber;
	}

	/**
	 * Sets the unique person number.
	 *
	 * @param uniquePersonNumber the new unique person number
	 */
	public void setUniquePersonNumber(String uniquePersonNumber) {
		this.uniquePersonNumber = uniquePersonNumber;
	}

	/**
	 * Gets the birth date.
	 *
	 * @return the birth date
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the birth date.
	 *
	 * @param birthDate the new birth date
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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
	 * Sets the user type.
	 *
	 * @param userType the new user type
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * Gets the anph flag.
	 *
	 * @return the anph flag
	 */
	public String getAnphFlag() {
		return anphFlag;
	}

	/**
	 * Sets the anph flag.
	 *
	 * @param anphFlag the new anph flag
	 */
	public void setAnphFlag(String anphFlag) {
		this.anphFlag = anphFlag;
	}

	/**
	 * Gets the password type.
	 *
	 * @return the password type
	 */
	public String getPasswordType() {
		return passwordType;
	}

	/**
	 * Sets the password type.
	 *
	 * @param passwordType the new password type
	 */
	public void setPasswordType(String passwordType) {
		this.passwordType = passwordType;
	}

	/**
	 * Gets the ticket number.
	 *
	 * @return the ticket number
	 */
	public String getTicketNumber() {
		return ticketNumber;
	}

	/**
	 * Sets the ticket number.
	 *
	 * @param ticketNumber the new ticket number
	 */
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

}

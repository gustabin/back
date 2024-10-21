/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.entities;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class RequestChatEmailEntity.
 *
 * @author Federico_Puente
 */
public class ChatEmailRequestEntity {
    
	/** The first name. */
    @JsonProperty("firstName")
	private String firstName;

	/** The from address. */
    @JsonProperty("fromAddress")
	private String fromAddress;

    /** The last name. */
    @JsonProperty("lastName")
    private String lastName;

    /** The subject. */
    @JsonProperty("subject")
    private String subject;

    /** The tenantName. */
    @JsonProperty("tenantName")
    private String tenantName;

	/** The text. */
    @JsonProperty("text")
    private String text;
    
	/** The text. */
    @JsonProperty("mailbox")
    private String mailBox;
    
    /** The user data. */
    @JsonProperty("userData")
    private String userData;
    
    /** The user data. */
    @JsonProperty("userData")
    private String userData2;

    /**
	 * Gets the first name.
	 *
	 * @return the firstName
	 */
    public String getFirstName() {
        return firstName;
    }

    /**
	 * Sets the first name.
	 *
	 * @param firstName
	 *            the firstName to set
	 */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
	 * Gets the from address.
	 *
	 * @return the fromAddress
	 */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
	 * Sets the from address.
	 *
	 * @param fromAddress
	 *            the fromAddress to set
	 */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
	 * Gets the last name.
	 *
	 * @return the lastName
	 */
    public String getLastName() {
        return lastName;
    }

    /**
	 * Sets the last name.
	 *
	 * @param lastName
	 *            the lastName to set
	 */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
    public String getSubject() {
        return subject;
    }

    /**
	 * Sets the subject.
	 *
	 * @param subject
	 *            the subject to set
	 */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
	 * Gets the tenant name.
	 *
	 * @return the tenantName
	 */
    public String getTenantName() {
        return tenantName;
    }

    /**
	 * Sets the tenant name.
	 *
	 * @param tenantName
	 *            the tenantName to set
	 */
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    /**
	 * Gets the text.
	 *
	 * @return the text
	 */
    public String getText() {
        return text;
    }

    /**
	 * Sets the text.
	 *
	 * @param text
	 *            the text to set
	 */
    public void setText(String text) {
        this.text = text;
    }

    /**
	 * Gets the user data.
	 *
	 * @return the userData
	 */
    public String getUserData() {
        return userData;
    }

    /**
	 * Sets the user data.
	 *
	 * @param userData
	 *            the userData to set
	 */
    public void setUserData(String userData) {
        this.userData = userData;
    }

    /**
	 * Gets the user data 2.
	 *
	 * @return the userData2
	 */
    public String getUserData2() {
        return userData2;
    }

    /**
	 * Sets the user data 2.
	 *
	 * @param userData2
	 *            the userData2 to set
	 */
    public void setUserData2(String userData2) {
        this.userData2 = userData2;
    }

	/**
	 * Gets the mail box.
	 *
	 * @return the mail box
	 */
	public String getMailBox() {
		return mailBox;
	}

	/**
	 * Sets the mail box.
	 *
	 * @param mailBox the new mail box
	 */
	public void setMailBox(String mailBox) {
		this.mailBox = mailBox;
	}
    
}

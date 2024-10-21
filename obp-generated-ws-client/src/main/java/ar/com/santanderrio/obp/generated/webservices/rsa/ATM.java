
package ar.com.santanderrio.obp.generated.webservices.rsa;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ATM complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ATM">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}DeviceIdentifier">
 *       &lt;sequence>
 *         &lt;element name="timezone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="atmOwner" type="{http://ws.csd.rsa.com}ATMOwnerType" minOccurs="0"/>
 *         &lt;element name="atmID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationType" type="{http://ws.csd.rsa.com}ATMLocationTypes" minOccurs="0"/>
 *         &lt;element name="cardIssueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="atmLanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="location" type="{http://ws.csd.rsa.com}ATMLocation" minOccurs="0"/>
 *         &lt;element name="atmIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userGender" type="{http://ws.csd.rsa.com}Gender" minOccurs="0"/>
 *         &lt;element name="atmExternalScore" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="loginFailureReason" type="{http://ws.csd.rsa.com}LoginFailureType" minOccurs="0"/>
 *         &lt;element name="numberOfFailedLogins" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="userYearOfBirth" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="cardPINChangeDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="atmModel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="atmOS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="atmOwnerOther" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardIssuerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="atmDailyLimit" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="cardDailyLimit" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ATM", propOrder = {
    "timezone",
    "atmOwner",
    "atmID",
    "locationType",
    "cardIssueDate",
    "atmLanguage",
    "location",
    "atmIP",
    "userGender",
    "atmExternalScore",
    "loginFailureReason",
    "numberOfFailedLogins",
    "userYearOfBirth",
    "cardPINChangeDate",
    "atmModel",
    "atmOS",
    "atmOwnerOther",
    "cardIssuerID",
    "cardType",
    "atmDailyLimit",
    "cardDailyLimit"
})
public class ATM
    extends DeviceIdentifier
{

    protected String timezone;
    protected ATMOwnerType atmOwner;
    @XmlElement(required = true)
    protected String atmID;
    protected ATMLocationTypes locationType;
    protected String cardIssueDate;
    protected String atmLanguage;
    protected ATMLocation location;
    protected String atmIP;
    protected Gender userGender;
    protected BigInteger atmExternalScore;
    protected LoginFailureType loginFailureReason;
    protected BigInteger numberOfFailedLogins;
    protected BigInteger userYearOfBirth;
    protected String cardPINChangeDate;
    protected String atmModel;
    protected String atmOS;
    protected String atmOwnerOther;
    protected String cardIssuerID;
    protected String cardType;
    protected Amount atmDailyLimit;
    protected Amount cardDailyLimit;

    /**
     * Gets the value of the timezone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Sets the value of the timezone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimezone(String value) {
        this.timezone = value;
    }

    /**
     * Gets the value of the atmOwner property.
     * 
     * @return
     *     possible object is
     *     {@link ATMOwnerType }
     *     
     */
    public ATMOwnerType getAtmOwner() {
        return atmOwner;
    }

    /**
     * Sets the value of the atmOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link ATMOwnerType }
     *     
     */
    public void setAtmOwner(ATMOwnerType value) {
        this.atmOwner = value;
    }

    /**
     * Gets the value of the atmID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtmID() {
        return atmID;
    }

    /**
     * Sets the value of the atmID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtmID(String value) {
        this.atmID = value;
    }

    /**
     * Gets the value of the locationType property.
     * 
     * @return
     *     possible object is
     *     {@link ATMLocationTypes }
     *     
     */
    public ATMLocationTypes getLocationType() {
        return locationType;
    }

    /**
     * Sets the value of the locationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ATMLocationTypes }
     *     
     */
    public void setLocationType(ATMLocationTypes value) {
        this.locationType = value;
    }

    /**
     * Gets the value of the cardIssueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardIssueDate() {
        return cardIssueDate;
    }

    /**
     * Sets the value of the cardIssueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardIssueDate(String value) {
        this.cardIssueDate = value;
    }

    /**
     * Gets the value of the atmLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtmLanguage() {
        return atmLanguage;
    }

    /**
     * Sets the value of the atmLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtmLanguage(String value) {
        this.atmLanguage = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link ATMLocation }
     *     
     */
    public ATMLocation getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link ATMLocation }
     *     
     */
    public void setLocation(ATMLocation value) {
        this.location = value;
    }

    /**
     * Gets the value of the atmIP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtmIP() {
        return atmIP;
    }

    /**
     * Sets the value of the atmIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtmIP(String value) {
        this.atmIP = value;
    }

    /**
     * Gets the value of the userGender property.
     * 
     * @return
     *     possible object is
     *     {@link Gender }
     *     
     */
    public Gender getUserGender() {
        return userGender;
    }

    /**
     * Sets the value of the userGender property.
     * 
     * @param value
     *     allowed object is
     *     {@link Gender }
     *     
     */
    public void setUserGender(Gender value) {
        this.userGender = value;
    }

    /**
     * Gets the value of the atmExternalScore property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAtmExternalScore() {
        return atmExternalScore;
    }

    /**
     * Sets the value of the atmExternalScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAtmExternalScore(BigInteger value) {
        this.atmExternalScore = value;
    }

    /**
     * Gets the value of the loginFailureReason property.
     * 
     * @return
     *     possible object is
     *     {@link LoginFailureType }
     *     
     */
    public LoginFailureType getLoginFailureReason() {
        return loginFailureReason;
    }

    /**
     * Sets the value of the loginFailureReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoginFailureType }
     *     
     */
    public void setLoginFailureReason(LoginFailureType value) {
        this.loginFailureReason = value;
    }

    /**
     * Gets the value of the numberOfFailedLogins property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumberOfFailedLogins() {
        return numberOfFailedLogins;
    }

    /**
     * Sets the value of the numberOfFailedLogins property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumberOfFailedLogins(BigInteger value) {
        this.numberOfFailedLogins = value;
    }

    /**
     * Gets the value of the userYearOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUserYearOfBirth() {
        return userYearOfBirth;
    }

    /**
     * Sets the value of the userYearOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUserYearOfBirth(BigInteger value) {
        this.userYearOfBirth = value;
    }

    /**
     * Gets the value of the cardPINChangeDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardPINChangeDate() {
        return cardPINChangeDate;
    }

    /**
     * Sets the value of the cardPINChangeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardPINChangeDate(String value) {
        this.cardPINChangeDate = value;
    }

    /**
     * Gets the value of the atmModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtmModel() {
        return atmModel;
    }

    /**
     * Sets the value of the atmModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtmModel(String value) {
        this.atmModel = value;
    }

    /**
     * Gets the value of the atmOS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtmOS() {
        return atmOS;
    }

    /**
     * Sets the value of the atmOS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtmOS(String value) {
        this.atmOS = value;
    }

    /**
     * Gets the value of the atmOwnerOther property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtmOwnerOther() {
        return atmOwnerOther;
    }

    /**
     * Sets the value of the atmOwnerOther property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtmOwnerOther(String value) {
        this.atmOwnerOther = value;
    }

    /**
     * Gets the value of the cardIssuerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardIssuerID() {
        return cardIssuerID;
    }

    /**
     * Sets the value of the cardIssuerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardIssuerID(String value) {
        this.cardIssuerID = value;
    }

    /**
     * Gets the value of the cardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the value of the cardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardType(String value) {
        this.cardType = value;
    }

    /**
     * Gets the value of the atmDailyLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAtmDailyLimit() {
        return atmDailyLimit;
    }

    /**
     * Sets the value of the atmDailyLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAtmDailyLimit(Amount value) {
        this.atmDailyLimit = value;
    }

    /**
     * Gets the value of the cardDailyLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getCardDailyLimit() {
        return cardDailyLimit;
    }

    /**
     * Sets the value of the cardDailyLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setCardDailyLimit(Amount value) {
        this.cardDailyLimit = value;
    }

}

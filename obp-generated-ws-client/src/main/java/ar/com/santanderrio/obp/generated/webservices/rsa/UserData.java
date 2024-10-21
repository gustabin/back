
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VIP" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="business" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="lastAccountOpenDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastOnlineServicePasswordChangeDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="onlineServiceEnrollDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalAvailableBalance" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="totalCreditLimit" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="totalCreditsUsed" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="userAddress" type="{http://ws.csd.rsa.com}UserAddress" minOccurs="0"/>
 *         &lt;element name="userNameData" type="{http://ws.csd.rsa.com}UserName" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserData", propOrder = {
    "vip",
    "business",
    "lastAccountOpenDate",
    "lastOnlineServicePasswordChangeDate",
    "onlineServiceEnrollDate",
    "totalAvailableBalance",
    "totalCreditLimit",
    "totalCreditsUsed",
    "userAddress",
    "userNameData"
})
public class UserData {

    @XmlElement(name = "VIP")
    protected Boolean vip;
    protected Boolean business;
    protected String lastAccountOpenDate;
    protected String lastOnlineServicePasswordChangeDate;
    protected String onlineServiceEnrollDate;
    protected Amount totalAvailableBalance;
    protected Amount totalCreditLimit;
    protected Amount totalCreditsUsed;
    protected UserAddress userAddress;
    protected UserName userNameData;

    /**
     * Gets the value of the vip property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVIP() {
        return vip;
    }

    /**
     * Sets the value of the vip property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVIP(Boolean value) {
        this.vip = value;
    }

    /**
     * Gets the value of the business property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBusiness() {
        return business;
    }

    /**
     * Sets the value of the business property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBusiness(Boolean value) {
        this.business = value;
    }

    /**
     * Gets the value of the lastAccountOpenDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastAccountOpenDate() {
        return lastAccountOpenDate;
    }

    /**
     * Sets the value of the lastAccountOpenDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastAccountOpenDate(String value) {
        this.lastAccountOpenDate = value;
    }

    /**
     * Gets the value of the lastOnlineServicePasswordChangeDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastOnlineServicePasswordChangeDate() {
        return lastOnlineServicePasswordChangeDate;
    }

    /**
     * Sets the value of the lastOnlineServicePasswordChangeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastOnlineServicePasswordChangeDate(String value) {
        this.lastOnlineServicePasswordChangeDate = value;
    }

    /**
     * Gets the value of the onlineServiceEnrollDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnlineServiceEnrollDate() {
        return onlineServiceEnrollDate;
    }

    /**
     * Sets the value of the onlineServiceEnrollDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnlineServiceEnrollDate(String value) {
        this.onlineServiceEnrollDate = value;
    }

    /**
     * Gets the value of the totalAvailableBalance property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalAvailableBalance() {
        return totalAvailableBalance;
    }

    /**
     * Sets the value of the totalAvailableBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalAvailableBalance(Amount value) {
        this.totalAvailableBalance = value;
    }

    /**
     * Gets the value of the totalCreditLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalCreditLimit() {
        return totalCreditLimit;
    }

    /**
     * Sets the value of the totalCreditLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalCreditLimit(Amount value) {
        this.totalCreditLimit = value;
    }

    /**
     * Gets the value of the totalCreditsUsed property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalCreditsUsed() {
        return totalCreditsUsed;
    }

    /**
     * Sets the value of the totalCreditsUsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalCreditsUsed(Amount value) {
        this.totalCreditsUsed = value;
    }

    /**
     * Gets the value of the userAddress property.
     * 
     * @return
     *     possible object is
     *     {@link UserAddress }
     *     
     */
    public UserAddress getUserAddress() {
        return userAddress;
    }

    /**
     * Sets the value of the userAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserAddress }
     *     
     */
    public void setUserAddress(UserAddress value) {
        this.userAddress = value;
    }

    /**
     * Gets the value of the userNameData property.
     * 
     * @return
     *     possible object is
     *     {@link UserName }
     *     
     */
    public UserName getUserNameData() {
        return userNameData;
    }

    /**
     * Sets the value of the userNameData property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserName }
     *     
     */
    public void setUserNameData(UserName value) {
        this.userNameData = value;
    }

}

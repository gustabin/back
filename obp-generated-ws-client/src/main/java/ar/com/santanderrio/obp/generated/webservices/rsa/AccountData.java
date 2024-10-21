
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;


/**
 * This type defines account information for a user
 * 
 * <p>Java class for AccountData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountBalance" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="accountCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountCreditLimit" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="accountCreditsTurnover" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="accountCreditsUsed" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="accountDailyLimit" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="accountLastCreditGrantDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountNickName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="internationalAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountOpenedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountOwnershipType" type="{http://ws.csd.rsa.com}AccountOwnershipType" minOccurs="0"/>
 *         &lt;element name="accountRelationType" type="{http://ws.csd.rsa.com}AccountRelationType" minOccurs="0"/>
 *         &lt;element name="accountType" type="{http://ws.csd.rsa.com}AccountType" minOccurs="0"/>
 *         &lt;element name="clientDefinedAccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalRiskScore" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="liquid" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nextLiquidDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="referenceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="routingCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="swiftCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountData", propOrder = {
    "accountBalance",
    "accountCategory",
    "accountCountry",
    "accountCreditLimit",
    "accountCreditsTurnover",
    "accountCreditsUsed",
    "accountDailyLimit",
    "accountLastCreditGrantDate",
    "accountName",
    "accountNickName",
    "accountNumber",
    "internationalAccountNumber",
    "accountOpenedDate",
    "accountOwnershipType",
    "accountRelationType",
    "accountType",
    "clientDefinedAccountType",
    "externalRiskScore",
    "liquid",
    "nextLiquidDate",
    "referenceCode",
    "routingCode",
    "swiftCode"
})
public class AccountData {

    protected Amount accountBalance;
    protected String accountCategory;
    protected String accountCountry;
    protected Amount accountCreditLimit;
    protected Amount accountCreditsTurnover;
    protected Amount accountCreditsUsed;
    protected Amount accountDailyLimit;
    protected String accountLastCreditGrantDate;
    protected String accountName;
    protected String accountNickName;
    protected String accountNumber;
    protected String internationalAccountNumber;
    protected String accountOpenedDate;
    protected AccountOwnershipType accountOwnershipType;
    protected AccountRelationType accountRelationType;
    protected AccountType accountType;
    protected String clientDefinedAccountType;
    protected Integer externalRiskScore;
    protected Boolean liquid;
    protected String nextLiquidDate;
    protected String referenceCode;
    protected String routingCode;
    protected String swiftCode;

    /**
     * Gets the value of the accountBalance property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAccountBalance() {
        return accountBalance;
    }

    /**
     * Sets the value of the accountBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAccountBalance(Amount value) {
        this.accountBalance = value;
    }

    /**
     * Gets the value of the accountCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountCategory() {
        return accountCategory;
    }

    /**
     * Sets the value of the accountCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCategory(String value) {
        this.accountCategory = value;
    }

    /**
     * Gets the value of the accountCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountCountry() {
        return accountCountry;
    }

    /**
     * Sets the value of the accountCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCountry(String value) {
        this.accountCountry = value;
    }

    /**
     * Gets the value of the accountCreditLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAccountCreditLimit() {
        return accountCreditLimit;
    }

    /**
     * Sets the value of the accountCreditLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAccountCreditLimit(Amount value) {
        this.accountCreditLimit = value;
    }

    /**
     * Gets the value of the accountCreditsTurnover property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAccountCreditsTurnover() {
        return accountCreditsTurnover;
    }

    /**
     * Sets the value of the accountCreditsTurnover property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAccountCreditsTurnover(Amount value) {
        this.accountCreditsTurnover = value;
    }

    /**
     * Gets the value of the accountCreditsUsed property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAccountCreditsUsed() {
        return accountCreditsUsed;
    }

    /**
     * Sets the value of the accountCreditsUsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAccountCreditsUsed(Amount value) {
        this.accountCreditsUsed = value;
    }

    /**
     * Gets the value of the accountDailyLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAccountDailyLimit() {
        return accountDailyLimit;
    }

    /**
     * Sets the value of the accountDailyLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAccountDailyLimit(Amount value) {
        this.accountDailyLimit = value;
    }

    /**
     * Gets the value of the accountLastCreditGrantDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountLastCreditGrantDate() {
        return accountLastCreditGrantDate;
    }

    /**
     * Sets the value of the accountLastCreditGrantDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountLastCreditGrantDate(String value) {
        this.accountLastCreditGrantDate = value;
    }

    /**
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * Gets the value of the accountNickName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNickName() {
        return accountNickName;
    }

    /**
     * Sets the value of the accountNickName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNickName(String value) {
        if(StringUtils.isNotBlank(value)) {
            this.accountNickName = value.replaceAll("[^a-zA-Z0-9\\s+]", " ").trim();
        } else {
            this.accountNickName = value;
        }
    }
    
    public void setAccountNickName(StringBuilder value) {
    	if(value != null) {
    		this.accountNickName = value.toString();
    	} else {
    		this.accountNickName = StringUtils.EMPTY;
    	}
    }

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of the internationalAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternationalAccountNumber() {
        return internationalAccountNumber;
    }

    /**
     * Sets the value of the internationalAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternationalAccountNumber(String value) {
        this.internationalAccountNumber = value;
    }

    /**
     * Gets the value of the accountOpenedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountOpenedDate() {
        return accountOpenedDate;
    }

    /**
     * Sets the value of the accountOpenedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountOpenedDate(String value) {
        this.accountOpenedDate = value;
    }

    /**
     * Gets the value of the accountOwnershipType property.
     * 
     * @return
     *     possible object is
     *     {@link AccountOwnershipType }
     *     
     */
    public AccountOwnershipType getAccountOwnershipType() {
        return accountOwnershipType;
    }

    /**
     * Sets the value of the accountOwnershipType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountOwnershipType }
     *     
     */
    public void setAccountOwnershipType(AccountOwnershipType value) {
        this.accountOwnershipType = value;
    }

    /**
     * Gets the value of the accountRelationType property.
     * 
     * @return
     *     possible object is
     *     {@link AccountRelationType }
     *     
     */
    public AccountRelationType getAccountRelationType() {
        return accountRelationType;
    }

    /**
     * Sets the value of the accountRelationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountRelationType }
     *     
     */
    public void setAccountRelationType(AccountRelationType value) {
        this.accountRelationType = value;
    }

    /**
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link AccountType }
     *     
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountType }
     *     
     */
    public void setAccountType(AccountType value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the clientDefinedAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientDefinedAccountType() {
        return clientDefinedAccountType;
    }

    /**
     * Sets the value of the clientDefinedAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientDefinedAccountType(String value) {
        this.clientDefinedAccountType = value;
    }

    /**
     * Gets the value of the externalRiskScore property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExternalRiskScore() {
        return externalRiskScore;
    }

    /**
     * Sets the value of the externalRiskScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExternalRiskScore(Integer value) {
        this.externalRiskScore = value;
    }

    /**
     * Gets the value of the liquid property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLiquid() {
        return liquid;
    }

    /**
     * Sets the value of the liquid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLiquid(Boolean value) {
        this.liquid = value;
    }

    /**
     * Gets the value of the nextLiquidDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextLiquidDate() {
        return nextLiquidDate;
    }

    /**
     * Sets the value of the nextLiquidDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextLiquidDate(String value) {
        this.nextLiquidDate = value;
    }

    /**
     * Gets the value of the referenceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceCode() {
        return referenceCode;
    }

    /**
     * Sets the value of the referenceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceCode(String value) {
        this.referenceCode = value;
    }

    /**
     * Gets the value of the routingCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoutingCode() {
        return routingCode;
    }

    /**
     * Sets the value of the routingCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoutingCode(String value) {
        this.routingCode = value;
    }

    /**
     * Gets the value of the swiftCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwiftCode() {
        return swiftCode;
    }

    /**
     * Sets the value of the swiftCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwiftCode(String value) {
        this.swiftCode = value;
    }

}

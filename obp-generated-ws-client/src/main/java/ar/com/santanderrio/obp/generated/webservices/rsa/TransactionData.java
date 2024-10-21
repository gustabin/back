
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This defines the specific detials of a transaction
 * 
 * <p>Java class for TransactionData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransactionData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="dueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estimatedDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="executionSpeed" type="{http://ws.csd.rsa.com}ExecutionSpeed" minOccurs="0"/>
 *         &lt;element name="myAccountData" type="{http://ws.csd.rsa.com}AccountData" minOccurs="0"/>
 *         &lt;element name="otherAccountBankType" type="{http://ws.csd.rsa.com}OtherAccountBankType" minOccurs="0"/>
 *         &lt;element name="otherAccountData" type="{http://ws.csd.rsa.com}AccountData" minOccurs="0"/>
 *         &lt;element name="otherAccountOwnershipType" type="{http://ws.csd.rsa.com}OtherAccountOwnershipType" minOccurs="0"/>
 *         &lt;element name="otherAccountType" type="{http://ws.csd.rsa.com}OtherAccountType" minOccurs="0"/>
 *         &lt;element name="previousAmount" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="recurringFrequency" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="schedule" type="{http://ws.csd.rsa.com}Schedule" minOccurs="0"/>
 *         &lt;element name="transferMediumType" type="{http://ws.csd.rsa.com}TransferMediumType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionData", propOrder = {
    "amount",
    "dueDate",
    "estimatedDeliveryDate",
    "executionSpeed",
    "myAccountData",
    "otherAccountBankType",
    "otherAccountData",
    "otherAccountOwnershipType",
    "otherAccountType",
    "previousAmount",
    "recurringFrequency",
    "schedule",
    "transferMediumType"
})
public class TransactionData {

    protected Amount amount;
    protected String dueDate;
    protected String estimatedDeliveryDate;
    protected ExecutionSpeed executionSpeed;
    protected AccountData myAccountData;
    protected OtherAccountBankType otherAccountBankType;
    protected AccountData otherAccountData;
    protected OtherAccountOwnershipType otherAccountOwnershipType;
    protected OtherAccountType otherAccountType;
    protected Amount previousAmount;
    protected Integer recurringFrequency;
    protected Schedule schedule;
    protected TransferMediumType transferMediumType;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAmount(Amount value) {
        this.amount = value;
    }

    /**
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDueDate(String value) {
        this.dueDate = value;
    }

    /**
     * Gets the value of the estimatedDeliveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    /**
     * Sets the value of the estimatedDeliveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstimatedDeliveryDate(String value) {
        this.estimatedDeliveryDate = value;
    }

    /**
     * Gets the value of the executionSpeed property.
     * 
     * @return
     *     possible object is
     *     {@link ExecutionSpeed }
     *     
     */
    public ExecutionSpeed getExecutionSpeed() {
        return executionSpeed;
    }

    /**
     * Sets the value of the executionSpeed property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExecutionSpeed }
     *     
     */
    public void setExecutionSpeed(ExecutionSpeed value) {
        this.executionSpeed = value;
    }

    /**
     * Gets the value of the myAccountData property.
     * 
     * @return
     *     possible object is
     *     {@link AccountData }
     *     
     */
    public AccountData getMyAccountData() {
        return myAccountData;
    }

    /**
     * Sets the value of the myAccountData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountData }
     *     
     */
    public void setMyAccountData(AccountData value) {
        this.myAccountData = value;
    }

    /**
     * Gets the value of the otherAccountBankType property.
     * 
     * @return
     *     possible object is
     *     {@link OtherAccountBankType }
     *     
     */
    public OtherAccountBankType getOtherAccountBankType() {
        return otherAccountBankType;
    }

    /**
     * Sets the value of the otherAccountBankType property.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherAccountBankType }
     *     
     */
    public void setOtherAccountBankType(OtherAccountBankType value) {
        this.otherAccountBankType = value;
    }

    /**
     * Gets the value of the otherAccountData property.
     * 
     * @return
     *     possible object is
     *     {@link AccountData }
     *     
     */
    public AccountData getOtherAccountData() {
        return otherAccountData;
    }

    /**
     * Sets the value of the otherAccountData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountData }
     *     
     */
    public void setOtherAccountData(AccountData value) {
        this.otherAccountData = value;
    }

    /**
     * Gets the value of the otherAccountOwnershipType property.
     * 
     * @return
     *     possible object is
     *     {@link OtherAccountOwnershipType }
     *     
     */
    public OtherAccountOwnershipType getOtherAccountOwnershipType() {
        return otherAccountOwnershipType;
    }

    /**
     * Sets the value of the otherAccountOwnershipType property.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherAccountOwnershipType }
     *     
     */
    public void setOtherAccountOwnershipType(OtherAccountOwnershipType value) {
        this.otherAccountOwnershipType = value;
    }

    /**
     * Gets the value of the otherAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link OtherAccountType }
     *     
     */
    public OtherAccountType getOtherAccountType() {
        return otherAccountType;
    }

    /**
     * Sets the value of the otherAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherAccountType }
     *     
     */
    public void setOtherAccountType(OtherAccountType value) {
        this.otherAccountType = value;
    }

    /**
     * Gets the value of the previousAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getPreviousAmount() {
        return previousAmount;
    }

    /**
     * Sets the value of the previousAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setPreviousAmount(Amount value) {
        this.previousAmount = value;
    }

    /**
     * Gets the value of the recurringFrequency property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRecurringFrequency() {
        return recurringFrequency;
    }

    /**
     * Sets the value of the recurringFrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRecurringFrequency(Integer value) {
        this.recurringFrequency = value;
    }

    /**
     * Gets the value of the schedule property.
     * 
     * @return
     *     possible object is
     *     {@link Schedule }
     *     
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Sets the value of the schedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link Schedule }
     *     
     */
    public void setSchedule(Schedule value) {
        this.schedule = value;
    }

    /**
     * Gets the value of the transferMediumType property.
     * 
     * @return
     *     possible object is
     *     {@link TransferMediumType }
     *     
     */
    public TransferMediumType getTransferMediumType() {
        return transferMediumType;
    }

    /**
     * Sets the value of the transferMediumType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransferMediumType }
     *     
     */
    public void setTransferMediumType(TransferMediumType value) {
        this.transferMediumType = value;
    }

}

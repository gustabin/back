
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This defines all relevant information about an event
 * 
 * <p>Java class for EventData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authenticationLevel" type="{http://ws.csd.rsa.com}AuthenticationLevel" minOccurs="0"/>
 *         &lt;element name="clientDefinedAttributeList" type="{http://ws.csd.rsa.com}FactList" minOccurs="0"/>
 *         &lt;element name="clientDefinedEventType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventReferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventType" type="{http://ws.csd.rsa.com}EventType" minOccurs="0"/>
 *         &lt;element name="newUserData" type="{http://ws.csd.rsa.com}UserData" minOccurs="0"/>
 *         &lt;element name="stockTradeData" type="{http://ws.csd.rsa.com}StockTradeData" minOccurs="0"/>
 *         &lt;element name="timeOfOccurrence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionData" type="{http://ws.csd.rsa.com}TransactionData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventData", propOrder = {
    "authenticationLevel",
    "clientDefinedAttributeList",
    "clientDefinedEventType",
    "eventDescription",
    "eventId",
    "eventReferenceId",
    "eventType",
    "newUserData",
    "stockTradeData",
    "timeOfOccurrence",
    "transactionData"
})
public class EventData {

    protected AuthenticationLevel authenticationLevel;
    protected FactList clientDefinedAttributeList;
    protected String clientDefinedEventType;
    protected String eventDescription;
    protected String eventId;
    protected String eventReferenceId;
    protected EventType eventType;
    protected UserData newUserData;
    protected StockTradeData stockTradeData;
    protected String timeOfOccurrence;
    protected TransactionData transactionData;

    /**
     * Gets the value of the authenticationLevel property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationLevel }
     *     
     */
    public AuthenticationLevel getAuthenticationLevel() {
        return authenticationLevel;
    }

    /**
     * Sets the value of the authenticationLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationLevel }
     *     
     */
    public void setAuthenticationLevel(AuthenticationLevel value) {
        this.authenticationLevel = value;
    }

    /**
     * Gets the value of the clientDefinedAttributeList property.
     * 
     * @return
     *     possible object is
     *     {@link FactList }
     *     
     */
    public FactList getClientDefinedAttributeList() {
        return clientDefinedAttributeList;
    }

    /**
     * Sets the value of the clientDefinedAttributeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactList }
     *     
     */
    public void setClientDefinedAttributeList(FactList value) {
        this.clientDefinedAttributeList = value;
    }

    /**
     * Gets the value of the clientDefinedEventType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientDefinedEventType() {
        return clientDefinedEventType;
    }

    /**
     * Sets the value of the clientDefinedEventType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientDefinedEventType(String value) {
        this.clientDefinedEventType = value;
    }

    /**
     * Gets the value of the eventDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * Sets the value of the eventDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventDescription(String value) {
        this.eventDescription = value;
    }

    /**
     * Gets the value of the eventId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Sets the value of the eventId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventId(String value) {
        this.eventId = value;
    }

    /**
     * Gets the value of the eventReferenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventReferenceId() {
        return eventReferenceId;
    }

    /**
     * Sets the value of the eventReferenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventReferenceId(String value) {
        this.eventReferenceId = value;
    }

    /**
     * Gets the value of the eventType property.
     * 
     * @return
     *     possible object is
     *     {@link EventType }
     *     
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Sets the value of the eventType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventType }
     *     
     */
    public void setEventType(EventType value) {
        this.eventType = value;
    }

    /**
     * Gets the value of the newUserData property.
     * 
     * @return
     *     possible object is
     *     {@link UserData }
     *     
     */
    public UserData getNewUserData() {
        return newUserData;
    }

    /**
     * Sets the value of the newUserData property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserData }
     *     
     */
    public void setNewUserData(UserData value) {
        this.newUserData = value;
    }

    /**
     * Gets the value of the stockTradeData property.
     * 
     * @return
     *     possible object is
     *     {@link StockTradeData }
     *     
     */
    public StockTradeData getStockTradeData() {
        return stockTradeData;
    }

    /**
     * Sets the value of the stockTradeData property.
     * 
     * @param value
     *     allowed object is
     *     {@link StockTradeData }
     *     
     */
    public void setStockTradeData(StockTradeData value) {
        this.stockTradeData = value;
    }

    /**
     * Gets the value of the timeOfOccurrence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeOfOccurrence() {
        return timeOfOccurrence;
    }

    /**
     * Sets the value of the timeOfOccurrence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeOfOccurrence(String value) {
        this.timeOfOccurrence = value;
    }

    /**
     * Gets the value of the transactionData property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionData }
     *     
     */
    public TransactionData getTransactionData() {
        return transactionData;
    }

    /**
     * Sets the value of the transactionData property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionData }
     *     
     */
    public void setTransactionData(TransactionData value) {
        this.transactionData = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AnalyzeRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AnalyzeRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}GenericRequest">
 *       &lt;sequence>
 *         &lt;element name="channel" type="{http://ws.csd.rsa.com}DeviceIdentifier" minOccurs="0"/>
 *         &lt;element name="autoCreateUserFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="clientReturnData" type="{http://ws.csd.rsa.com}ClientReturnData" minOccurs="0"/>
 *         &lt;element name="collectionRequest" type="{http://ws.csd.rsa.com}CollectionRequest" minOccurs="0"/>
 *         &lt;element name="credentialDataList" type="{http://ws.csd.rsa.com}CredentialDataList" minOccurs="0"/>
 *         &lt;element name="deviceManagementRequest" type="{http://ws.csd.rsa.com}DeviceManagementRequestPayload" minOccurs="0"/>
 *         &lt;element name="eventDataList" type="{http://ws.csd.rsa.com}EventDataList"/>
 *         &lt;element name="runRiskType" type="{http://ws.csd.rsa.com}RunRiskType"/>
 *         &lt;element name="userData" type="{http://ws.csd.rsa.com}UserData" minOccurs="0"/>
 *         &lt;element name="channelIndicator" type="{http://ws.csd.rsa.com}ChannelIndicatorType" minOccurs="0"/>
 *         &lt;element name="clientDefinedChannelIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalyzeRequest", propOrder = {
    "channel",
    "autoCreateUserFlag",
    "clientReturnData",
    "collectionRequest",
    "credentialDataList",
    "deviceManagementRequest",
    "eventDataList",
    "runRiskType",
    "userData",
    "channelIndicator",
    "clientDefinedChannelIndicator"
})
public class AnalyzeRequest
    extends GenericRequest
{

    protected DeviceIdentifier channel;
    protected Boolean autoCreateUserFlag;
    protected ClientReturnData clientReturnData;
    protected CollectionRequest collectionRequest;
    protected CredentialDataList credentialDataList;
    protected DeviceManagementRequestPayload deviceManagementRequest;
    @XmlElement(required = true)
    protected EventDataList eventDataList;
    @XmlElement(required = true)
    protected RunRiskType runRiskType;
    protected UserData userData;
    protected ChannelIndicatorType channelIndicator;
    protected String clientDefinedChannelIndicator;

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceIdentifier }
     *     
     */
    public DeviceIdentifier getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceIdentifier }
     *     
     */
    public void setChannel(DeviceIdentifier value) {
        this.channel = value;
    }

    /**
     * Gets the value of the autoCreateUserFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoCreateUserFlag() {
        return autoCreateUserFlag;
    }

    /**
     * Sets the value of the autoCreateUserFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoCreateUserFlag(Boolean value) {
        this.autoCreateUserFlag = value;
    }

    /**
     * Gets the value of the clientReturnData property.
     * 
     * @return
     *     possible object is
     *     {@link ClientReturnData }
     *     
     */
    public ClientReturnData getClientReturnData() {
        return clientReturnData;
    }

    /**
     * Sets the value of the clientReturnData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientReturnData }
     *     
     */
    public void setClientReturnData(ClientReturnData value) {
        this.clientReturnData = value;
    }

    /**
     * Gets the value of the collectionRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CollectionRequest }
     *     
     */
    public CollectionRequest getCollectionRequest() {
        return collectionRequest;
    }

    /**
     * Sets the value of the collectionRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionRequest }
     *     
     */
    public void setCollectionRequest(CollectionRequest value) {
        this.collectionRequest = value;
    }

    /**
     * Gets the value of the credentialDataList property.
     * 
     * @return
     *     possible object is
     *     {@link CredentialDataList }
     *     
     */
    public CredentialDataList getCredentialDataList() {
        return credentialDataList;
    }

    /**
     * Sets the value of the credentialDataList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredentialDataList }
     *     
     */
    public void setCredentialDataList(CredentialDataList value) {
        this.credentialDataList = value;
    }

    /**
     * Gets the value of the deviceManagementRequest property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceManagementRequestPayload }
     *     
     */
    public DeviceManagementRequestPayload getDeviceManagementRequest() {
        return deviceManagementRequest;
    }

    /**
     * Sets the value of the deviceManagementRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceManagementRequestPayload }
     *     
     */
    public void setDeviceManagementRequest(DeviceManagementRequestPayload value) {
        this.deviceManagementRequest = value;
    }

    /**
     * Gets the value of the eventDataList property.
     * 
     * @return
     *     possible object is
     *     {@link EventDataList }
     *     
     */
    public EventDataList getEventDataList() {
        return eventDataList;
    }

    /**
     * Sets the value of the eventDataList property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventDataList }
     *     
     */
    public void setEventDataList(EventDataList value) {
        this.eventDataList = value;
    }

    /**
     * Gets the value of the runRiskType property.
     * 
     * @return
     *     possible object is
     *     {@link RunRiskType }
     *     
     */
    public RunRiskType getRunRiskType() {
        return runRiskType;
    }

    /**
     * Sets the value of the runRiskType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RunRiskType }
     *     
     */
    public void setRunRiskType(RunRiskType value) {
        this.runRiskType = value;
    }

    /**
     * Gets the value of the userData property.
     * 
     * @return
     *     possible object is
     *     {@link UserData }
     *     
     */
    public UserData getUserData() {
        return userData;
    }

    /**
     * Sets the value of the userData property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserData }
     *     
     */
    public void setUserData(UserData value) {
        this.userData = value;
    }

    /**
     * Gets the value of the channelIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link ChannelIndicatorType }
     *     
     */
    public ChannelIndicatorType getChannelIndicator() {
        return channelIndicator;
    }

    /**
     * Sets the value of the channelIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelIndicatorType }
     *     
     */
    public void setChannelIndicator(ChannelIndicatorType value) {
        this.channelIndicator = value;
    }

    /**
     * Gets the value of the clientDefinedChannelIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientDefinedChannelIndicator() {
        return clientDefinedChannelIndicator;
    }

    /**
     * Sets the value of the clientDefinedChannelIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientDefinedChannelIndicator(String value) {
        this.clientDefinedChannelIndicator = value;
    }

}

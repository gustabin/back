
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * This defines the contents of an abstract Generic Request
 * 
 * <p>Java class for GenericRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenericRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actionTypeList" type="{http://ws.csd.rsa.com}GenericActionTypeList" minOccurs="0"/>
 *         &lt;element name="configurationHeader" type="{http://ws.csd.rsa.com}ConfigurationHeader" minOccurs="0"/>
 *         &lt;element name="deviceRequest" type="{http://ws.csd.rsa.com}DeviceRequest" minOccurs="0"/>
 *         &lt;element name="identificationData" type="{http://ws.csd.rsa.com}IdentificationData" minOccurs="0"/>
 *         &lt;element name="messageHeader" type="{http://ws.csd.rsa.com}MessageHeader"/>
 *         &lt;element name="securityHeader" type="{http://ws.csd.rsa.com}SecurityHeader"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericRequest", propOrder = {
    "actionTypeList",
    "configurationHeader",
    "deviceRequest",
    "identificationData",
    "messageHeader",
    "securityHeader"
})
@XmlSeeAlso({
    QueryAuthStatusRequest.class,
    ChallengeRequest.class,
    CreateUserRequest.class,
    QueryRequest.class,
    NotifyRequest.class,
    AnalyzeRequest.class,
    UpdateUserRequest.class,
    AuthenticateRequest.class
})
public abstract class GenericRequest {

    protected GenericActionTypeList actionTypeList;
    protected ConfigurationHeader configurationHeader;
    protected DeviceRequest deviceRequest;
    protected IdentificationData identificationData;
    @XmlElement(required = true)
    protected MessageHeader messageHeader;
    @XmlElement(required = true)
    protected SecurityHeader securityHeader;

    /**
     * Gets the value of the actionTypeList property.
     * 
     * @return
     *     possible object is
     *     {@link GenericActionTypeList }
     *     
     */
    public GenericActionTypeList getActionTypeList() {
        return actionTypeList;
    }

    /**
     * Sets the value of the actionTypeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenericActionTypeList }
     *     
     */
    public void setActionTypeList(GenericActionTypeList value) {
        this.actionTypeList = value;
    }

    /**
     * Gets the value of the configurationHeader property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurationHeader }
     *     
     */
    public ConfigurationHeader getConfigurationHeader() {
        return configurationHeader;
    }

    /**
     * Sets the value of the configurationHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurationHeader }
     *     
     */
    public void setConfigurationHeader(ConfigurationHeader value) {
        this.configurationHeader = value;
    }

    /**
     * Gets the value of the deviceRequest property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceRequest }
     *     
     */
    public DeviceRequest getDeviceRequest() {
        return deviceRequest;
    }

    /**
     * Sets the value of the deviceRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceRequest }
     *     
     */
    public void setDeviceRequest(DeviceRequest value) {
        this.deviceRequest = value;
    }

    /**
     * Gets the value of the identificationData property.
     * 
     * @return
     *     possible object is
     *     {@link IdentificationData }
     *     
     */
    public IdentificationData getIdentificationData() {
        return identificationData;
    }

    /**
     * Sets the value of the identificationData property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentificationData }
     *     
     */
    public void setIdentificationData(IdentificationData value) {
        this.identificationData = value;
    }

    /**
     * Gets the value of the messageHeader property.
     * 
     * @return
     *     possible object is
     *     {@link MessageHeader }
     *     
     */
    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    /**
     * Sets the value of the messageHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageHeader }
     *     
     */
    public void setMessageHeader(MessageHeader value) {
        this.messageHeader = value;
    }

    /**
     * Gets the value of the securityHeader property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityHeader }
     *     
     */
    public SecurityHeader getSecurityHeader() {
        return securityHeader;
    }

    /**
     * Sets the value of the securityHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityHeader }
     *     
     */
    public void setSecurityHeader(SecurityHeader value) {
        this.securityHeader = value;
    }

}

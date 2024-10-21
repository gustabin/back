
package ar.com.santanderrio.obp.generated.webservices.rsa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueryResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}GenericResponse">
 *       &lt;sequence>
 *         &lt;element name="browsableGroupNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="credentialManagementResponseList" type="{http://ws.csd.rsa.com}CredentialManagementResponseList" minOccurs="0"/>
 *         &lt;element name="deviceManagementResponse" type="{http://ws.csd.rsa.com}DeviceManagementResponsePayload" minOccurs="0"/>
 *         &lt;element name="systemCredentials" type="{http://ws.csd.rsa.com}CredentialList" minOccurs="0"/>
 *         &lt;element name="userCredentials" type="{http://ws.csd.rsa.com}CredentialList" minOccurs="0"/>
 *         &lt;element name="userPreference" type="{http://ws.csd.rsa.com}UserPreference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryResponse", propOrder = {
    "browsableGroupNames",
    "credentialManagementResponseList",
    "deviceManagementResponse",
    "systemCredentials",
    "userCredentials",
    "userPreference"
})
public class QueryResponse2
    extends GenericResponse
{

    protected List<String> browsableGroupNames;
    protected CredentialManagementResponseList credentialManagementResponseList;
    protected DeviceManagementResponsePayload deviceManagementResponse;
    protected CredentialList systemCredentials;
    protected CredentialList userCredentials;
    protected UserPreference userPreference;

    /**
     * Gets the value of the browsableGroupNames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the browsableGroupNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBrowsableGroupNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getBrowsableGroupNames() {
        if (browsableGroupNames == null) {
            browsableGroupNames = new ArrayList<String>();
        }
        return this.browsableGroupNames;
    }

    /**
     * Gets the value of the credentialManagementResponseList property.
     * 
     * @return
     *     possible object is
     *     {@link CredentialManagementResponseList }
     *     
     */
    public CredentialManagementResponseList getCredentialManagementResponseList() {
        return credentialManagementResponseList;
    }

    /**
     * Sets the value of the credentialManagementResponseList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredentialManagementResponseList }
     *     
     */
    public void setCredentialManagementResponseList(CredentialManagementResponseList value) {
        this.credentialManagementResponseList = value;
    }

    /**
     * Gets the value of the deviceManagementResponse property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceManagementResponsePayload }
     *     
     */
    public DeviceManagementResponsePayload getDeviceManagementResponse() {
        return deviceManagementResponse;
    }

    /**
     * Sets the value of the deviceManagementResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceManagementResponsePayload }
     *     
     */
    public void setDeviceManagementResponse(DeviceManagementResponsePayload value) {
        this.deviceManagementResponse = value;
    }

    /**
     * Gets the value of the systemCredentials property.
     * 
     * @return
     *     possible object is
     *     {@link CredentialList }
     *     
     */
    public CredentialList getSystemCredentials() {
        return systemCredentials;
    }

    /**
     * Sets the value of the systemCredentials property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredentialList }
     *     
     */
    public void setSystemCredentials(CredentialList value) {
        this.systemCredentials = value;
    }

    /**
     * Gets the value of the userCredentials property.
     * 
     * @return
     *     possible object is
     *     {@link CredentialList }
     *     
     */
    public CredentialList getUserCredentials() {
        return userCredentials;
    }

    /**
     * Sets the value of the userCredentials property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredentialList }
     *     
     */
    public void setUserCredentials(CredentialList value) {
        this.userCredentials = value;
    }

    /**
     * Gets the value of the userPreference property.
     * 
     * @return
     *     possible object is
     *     {@link UserPreference }
     *     
     */
    public UserPreference getUserPreference() {
        return userPreference;
    }

    /**
     * Sets the value of the userPreference property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserPreference }
     *     
     */
    public void setUserPreference(UserPreference value) {
        this.userPreference = value;
    }

}

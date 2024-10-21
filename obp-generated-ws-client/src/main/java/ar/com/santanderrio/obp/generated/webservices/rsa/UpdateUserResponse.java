
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateUserResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateUserResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}GenericResponse">
 *       &lt;sequence>
 *         &lt;element name="credentialManagementResponseList" type="{http://ws.csd.rsa.com}CredentialManagementResponseList" minOccurs="0"/>
 *         &lt;element name="deviceManagementResponse" type="{http://ws.csd.rsa.com}DeviceManagementResponsePayload" minOccurs="0"/>
 *         &lt;element name="riskResult" type="{http://ws.csd.rsa.com}RiskResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateUserResponse", propOrder = {
    "credentialManagementResponseList",
    "deviceManagementResponse",
    "riskResult"
})
public class UpdateUserResponse
    extends GenericResponse
{

    protected CredentialManagementResponseList credentialManagementResponseList;
    protected DeviceManagementResponsePayload deviceManagementResponse;
    protected RiskResult riskResult;

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
     * Gets the value of the riskResult property.
     * 
     * @return
     *     possible object is
     *     {@link RiskResult }
     *     
     */
    public RiskResult getRiskResult() {
        return riskResult;
    }

    /**
     * Sets the value of the riskResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RiskResult }
     *     
     */
    public void setRiskResult(RiskResult value) {
        this.riskResult = value;
    }

}

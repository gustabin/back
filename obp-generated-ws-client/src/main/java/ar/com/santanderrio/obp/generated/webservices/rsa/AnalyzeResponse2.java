
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AnalyzeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AnalyzeResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}GenericResponse">
 *       &lt;sequence>
 *         &lt;element name="collectableCredentialList" type="{http://ws.csd.rsa.com}CollectableCredentialList" minOccurs="0"/>
 *         &lt;element name="credentialAuthResultList" type="{http://ws.csd.rsa.com}CredentialAuthResultList" minOccurs="0"/>
 *         &lt;element name="deviceManagementResponse" type="{http://ws.csd.rsa.com}DeviceManagementResponsePayload" minOccurs="0"/>
 *         &lt;element name="requiredCredentialList" type="{http://ws.csd.rsa.com}RequiredCredentialList" minOccurs="0"/>
 *         &lt;element name="riskResult" type="{http://ws.csd.rsa.com}RiskResult" minOccurs="0"/>
 *         &lt;element name="serverRedirectData" type="{http://ws.csd.rsa.com}ServerRedirectData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalyzeResponse", propOrder = {
    "collectableCredentialList",
    "credentialAuthResultList",
    "deviceManagementResponse",
    "requiredCredentialList",
    "riskResult",
    "serverRedirectData"
})
public class AnalyzeResponse2
    extends GenericResponse
{

    protected CollectableCredentialList collectableCredentialList;
    protected CredentialAuthResultList credentialAuthResultList;
    protected DeviceManagementResponsePayload deviceManagementResponse;
    protected RequiredCredentialList requiredCredentialList;
    protected RiskResult riskResult;
    protected ServerRedirectData serverRedirectData;

    /**
     * Gets the value of the collectableCredentialList property.
     * 
     * @return
     *     possible object is
     *     {@link CollectableCredentialList }
     *     
     */
    public CollectableCredentialList getCollectableCredentialList() {
        return collectableCredentialList;
    }

    /**
     * Sets the value of the collectableCredentialList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectableCredentialList }
     *     
     */
    public void setCollectableCredentialList(CollectableCredentialList value) {
        this.collectableCredentialList = value;
    }

    /**
     * Gets the value of the credentialAuthResultList property.
     * 
     * @return
     *     possible object is
     *     {@link CredentialAuthResultList }
     *     
     */
    public CredentialAuthResultList getCredentialAuthResultList() {
        return credentialAuthResultList;
    }

    /**
     * Sets the value of the credentialAuthResultList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredentialAuthResultList }
     *     
     */
    public void setCredentialAuthResultList(CredentialAuthResultList value) {
        this.credentialAuthResultList = value;
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
     * Gets the value of the requiredCredentialList property.
     * 
     * @return
     *     possible object is
     *     {@link RequiredCredentialList }
     *     
     */
    public RequiredCredentialList getRequiredCredentialList() {
        return requiredCredentialList;
    }

    /**
     * Sets the value of the requiredCredentialList property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequiredCredentialList }
     *     
     */
    public void setRequiredCredentialList(RequiredCredentialList value) {
        this.requiredCredentialList = value;
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

    /**
     * Gets the value of the serverRedirectData property.
     * 
     * @return
     *     possible object is
     *     {@link ServerRedirectData }
     *     
     */
    public ServerRedirectData getServerRedirectData() {
        return serverRedirectData;
    }

    /**
     * Sets the value of the serverRedirectData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServerRedirectData }
     *     
     */
    public void setServerRedirectData(ServerRedirectData value) {
        this.serverRedirectData = value;
    }

}

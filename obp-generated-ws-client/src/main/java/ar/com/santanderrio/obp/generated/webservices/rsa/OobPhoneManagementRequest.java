
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This type defines the OOB Phone MANAGEMENT Request Payload
 * 
 * <p>Java class for OobPhoneManagementRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OobPhoneManagementRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="credentialProvisioningStatus" type="{http://ws.csd.rsa.com}CredentialProvisioningStatus" minOccurs="0"/>
 *         &lt;element name="payload" type="{http://ws.csd.rsa.com}PhoneManagementRequestPayload" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OobPhoneManagementRequest", propOrder = {
    "credentialProvisioningStatus",
    "payload"
})
public class OobPhoneManagementRequest {

    protected CredentialProvisioningStatus credentialProvisioningStatus;
    protected PhoneManagementRequestPayload payload;

    /**
     * Gets the value of the credentialProvisioningStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CredentialProvisioningStatus }
     *     
     */
    public CredentialProvisioningStatus getCredentialProvisioningStatus() {
        return credentialProvisioningStatus;
    }

    /**
     * Sets the value of the credentialProvisioningStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredentialProvisioningStatus }
     *     
     */
    public void setCredentialProvisioningStatus(CredentialProvisioningStatus value) {
        this.credentialProvisioningStatus = value;
    }

    /**
     * Gets the value of the payload property.
     * 
     * @return
     *     possible object is
     *     {@link PhoneManagementRequestPayload }
     *     
     */
    public PhoneManagementRequestPayload getPayload() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhoneManagementRequestPayload }
     *     
     */
    public void setPayload(PhoneManagementRequestPayload value) {
        this.payload = value;
    }

}

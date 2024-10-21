
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This requests the results of the challenge question management method
 * 
 * <p>Java class for ChallengeQuestionManagementRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChallengeQuestionManagementRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="credentialProvisioningStatus" type="{http://ws.csd.rsa.com}CredentialProvisioningStatus" minOccurs="0"/>
 *         &lt;element name="payload" type="{http://ws.csd.rsa.com}ChallengeQuestionManagementRequestPayload" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChallengeQuestionManagementRequest", propOrder = {
    "credentialProvisioningStatus",
    "payload"
})
public class ChallengeQuestionManagementRequest {

    protected CredentialProvisioningStatus credentialProvisioningStatus;
    protected ChallengeQuestionManagementRequestPayload payload;

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
     *     {@link ChallengeQuestionManagementRequestPayload }
     *     
     */
    public ChallengeQuestionManagementRequestPayload getPayload() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionManagementRequestPayload }
     *     
     */
    public void setPayload(ChallengeQuestionManagementRequestPayload value) {
        this.payload = value;
    }

}

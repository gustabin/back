
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CredentialManagementRequestList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CredentialManagementRequestList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}CredentialRequestList">
 *       &lt;sequence>
 *         &lt;element name="challengeQuestionManagementRequest" type="{http://ws.csd.rsa.com}ChallengeQuestionManagementRequest" minOccurs="0"/>
 *         &lt;element name="oobEmailManagementRequest" type="{http://ws.csd.rsa.com}OobEmailManagementRequest" minOccurs="0"/>
 *         &lt;element name="oobPhoneManagementRequest" type="{http://ws.csd.rsa.com}OobPhoneManagementRequest" minOccurs="0"/>
 *         &lt;element name="acspManagementRequestData" type="{http://ws.csd.rsa.com}AcspManagementRequestData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CredentialManagementRequestList", propOrder = {
    "challengeQuestionManagementRequest",
    "oobEmailManagementRequest",
    "oobPhoneManagementRequest",
    "acspManagementRequestData"
})
public class CredentialManagementRequestList
    extends CredentialRequestList
{

    protected ChallengeQuestionManagementRequest challengeQuestionManagementRequest;
    protected OobEmailManagementRequest oobEmailManagementRequest;
    protected OobPhoneManagementRequest oobPhoneManagementRequest;
    protected AcspManagementRequestData acspManagementRequestData;

    /**
     * Gets the value of the challengeQuestionManagementRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeQuestionManagementRequest }
     *     
     */
    public ChallengeQuestionManagementRequest getChallengeQuestionManagementRequest() {
        return challengeQuestionManagementRequest;
    }

    /**
     * Sets the value of the challengeQuestionManagementRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionManagementRequest }
     *     
     */
    public void setChallengeQuestionManagementRequest(ChallengeQuestionManagementRequest value) {
        this.challengeQuestionManagementRequest = value;
    }

    /**
     * Gets the value of the oobEmailManagementRequest property.
     * 
     * @return
     *     possible object is
     *     {@link OobEmailManagementRequest }
     *     
     */
    public OobEmailManagementRequest getOobEmailManagementRequest() {
        return oobEmailManagementRequest;
    }

    /**
     * Sets the value of the oobEmailManagementRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobEmailManagementRequest }
     *     
     */
    public void setOobEmailManagementRequest(OobEmailManagementRequest value) {
        this.oobEmailManagementRequest = value;
    }

    /**
     * Gets the value of the oobPhoneManagementRequest property.
     * 
     * @return
     *     possible object is
     *     {@link OobPhoneManagementRequest }
     *     
     */
    public OobPhoneManagementRequest getOobPhoneManagementRequest() {
        return oobPhoneManagementRequest;
    }

    /**
     * Sets the value of the oobPhoneManagementRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobPhoneManagementRequest }
     *     
     */
    public void setOobPhoneManagementRequest(OobPhoneManagementRequest value) {
        this.oobPhoneManagementRequest = value;
    }

    /**
     * Gets the value of the acspManagementRequestData property.
     * 
     * @return
     *     possible object is
     *     {@link AcspManagementRequestData }
     *     
     */
    public AcspManagementRequestData getAcspManagementRequestData() {
        return acspManagementRequestData;
    }

    /**
     * Sets the value of the acspManagementRequestData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcspManagementRequestData }
     *     
     */
    public void setAcspManagementRequestData(AcspManagementRequestData value) {
        this.acspManagementRequestData = value;
    }

}

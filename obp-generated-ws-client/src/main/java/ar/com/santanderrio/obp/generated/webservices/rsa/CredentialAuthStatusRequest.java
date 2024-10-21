
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CredentialAuthStatusRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CredentialAuthStatusRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}CredentialRequestList">
 *       &lt;sequence>
 *         &lt;element name="challengeQuestionAuthStatusRequest" type="{http://ws.csd.rsa.com}ChallengeQuestionAuthStatusRequest" minOccurs="0"/>
 *         &lt;element name="oobEmailAuthStatusRequest" type="{http://ws.csd.rsa.com}OobEmailAuthStatusRequest" minOccurs="0"/>
 *         &lt;element name="oobPhoneAuthStatusRequest" type="{http://ws.csd.rsa.com}OobPhoneAuthStatusRequest" minOccurs="0"/>
 *         &lt;element name="acspAuthStatusRequestData" type="{http://ws.csd.rsa.com}AcspAuthStatusRequestData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CredentialAuthStatusRequest", propOrder = {
    "challengeQuestionAuthStatusRequest",
    "oobEmailAuthStatusRequest",
    "oobPhoneAuthStatusRequest",
    "acspAuthStatusRequestData"
})
public class CredentialAuthStatusRequest
    extends CredentialRequestList
{

    protected ChallengeQuestionAuthStatusRequest challengeQuestionAuthStatusRequest;
    protected OobEmailAuthStatusRequest oobEmailAuthStatusRequest;
    protected OobPhoneAuthStatusRequest oobPhoneAuthStatusRequest;
    protected AcspAuthStatusRequestData acspAuthStatusRequestData;

    /**
     * Gets the value of the challengeQuestionAuthStatusRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeQuestionAuthStatusRequest }
     *     
     */
    public ChallengeQuestionAuthStatusRequest getChallengeQuestionAuthStatusRequest() {
        return challengeQuestionAuthStatusRequest;
    }

    /**
     * Sets the value of the challengeQuestionAuthStatusRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionAuthStatusRequest }
     *     
     */
    public void setChallengeQuestionAuthStatusRequest(ChallengeQuestionAuthStatusRequest value) {
        this.challengeQuestionAuthStatusRequest = value;
    }

    /**
     * Gets the value of the oobEmailAuthStatusRequest property.
     * 
     * @return
     *     possible object is
     *     {@link OobEmailAuthStatusRequest }
     *     
     */
    public OobEmailAuthStatusRequest getOobEmailAuthStatusRequest() {
        return oobEmailAuthStatusRequest;
    }

    /**
     * Sets the value of the oobEmailAuthStatusRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobEmailAuthStatusRequest }
     *     
     */
    public void setOobEmailAuthStatusRequest(OobEmailAuthStatusRequest value) {
        this.oobEmailAuthStatusRequest = value;
    }

    /**
     * Gets the value of the oobPhoneAuthStatusRequest property.
     * 
     * @return
     *     possible object is
     *     {@link OobPhoneAuthStatusRequest }
     *     
     */
    public OobPhoneAuthStatusRequest getOobPhoneAuthStatusRequest() {
        return oobPhoneAuthStatusRequest;
    }

    /**
     * Sets the value of the oobPhoneAuthStatusRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobPhoneAuthStatusRequest }
     *     
     */
    public void setOobPhoneAuthStatusRequest(OobPhoneAuthStatusRequest value) {
        this.oobPhoneAuthStatusRequest = value;
    }

    /**
     * Gets the value of the acspAuthStatusRequestData property.
     * 
     * @return
     *     possible object is
     *     {@link AcspAuthStatusRequestData }
     *     
     */
    public AcspAuthStatusRequestData getAcspAuthStatusRequestData() {
        return acspAuthStatusRequestData;
    }

    /**
     * Sets the value of the acspAuthStatusRequestData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcspAuthStatusRequestData }
     *     
     */
    public void setAcspAuthStatusRequestData(AcspAuthStatusRequestData value) {
        this.acspAuthStatusRequestData = value;
    }

}

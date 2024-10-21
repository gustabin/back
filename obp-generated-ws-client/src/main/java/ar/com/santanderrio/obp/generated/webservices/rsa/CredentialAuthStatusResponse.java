
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CredentialAuthStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CredentialAuthStatusResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}CredentialResponseList">
 *       &lt;sequence>
 *         &lt;element name="challengeQuestionAuthStatusResponse" type="{http://ws.csd.rsa.com}ChallengeQuestionAuthStatusResponse" minOccurs="0"/>
 *         &lt;element name="oobEmailAuthStatusResponse" type="{http://ws.csd.rsa.com}OobEmailAuthStatusResponse" minOccurs="0"/>
 *         &lt;element name="oobPhoneAuthStatusResponse" type="{http://ws.csd.rsa.com}OobPhoneAuthStatusResponse" minOccurs="0"/>
 *         &lt;element name="acspAuthStatusResponseData" type="{http://ws.csd.rsa.com}AcspAuthStatusResponseData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CredentialAuthStatusResponse", propOrder = {
    "challengeQuestionAuthStatusResponse",
    "oobEmailAuthStatusResponse",
    "oobPhoneAuthStatusResponse",
    "acspAuthStatusResponseData"
})
public class CredentialAuthStatusResponse
    extends CredentialResponseList
{

    protected ChallengeQuestionAuthStatusResponse challengeQuestionAuthStatusResponse;
    protected OobEmailAuthStatusResponse oobEmailAuthStatusResponse;
    protected OobPhoneAuthStatusResponse oobPhoneAuthStatusResponse;
    protected AcspAuthStatusResponseData acspAuthStatusResponseData;

    /**
     * Gets the value of the challengeQuestionAuthStatusResponse property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeQuestionAuthStatusResponse }
     *     
     */
    public ChallengeQuestionAuthStatusResponse getChallengeQuestionAuthStatusResponse() {
        return challengeQuestionAuthStatusResponse;
    }

    /**
     * Sets the value of the challengeQuestionAuthStatusResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionAuthStatusResponse }
     *     
     */
    public void setChallengeQuestionAuthStatusResponse(ChallengeQuestionAuthStatusResponse value) {
        this.challengeQuestionAuthStatusResponse = value;
    }

    /**
     * Gets the value of the oobEmailAuthStatusResponse property.
     * 
     * @return
     *     possible object is
     *     {@link OobEmailAuthStatusResponse }
     *     
     */
    public OobEmailAuthStatusResponse getOobEmailAuthStatusResponse() {
        return oobEmailAuthStatusResponse;
    }

    /**
     * Sets the value of the oobEmailAuthStatusResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobEmailAuthStatusResponse }
     *     
     */
    public void setOobEmailAuthStatusResponse(OobEmailAuthStatusResponse value) {
        this.oobEmailAuthStatusResponse = value;
    }

    /**
     * Gets the value of the oobPhoneAuthStatusResponse property.
     * 
     * @return
     *     possible object is
     *     {@link OobPhoneAuthStatusResponse }
     *     
     */
    public OobPhoneAuthStatusResponse getOobPhoneAuthStatusResponse() {
        return oobPhoneAuthStatusResponse;
    }

    /**
     * Sets the value of the oobPhoneAuthStatusResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobPhoneAuthStatusResponse }
     *     
     */
    public void setOobPhoneAuthStatusResponse(OobPhoneAuthStatusResponse value) {
        this.oobPhoneAuthStatusResponse = value;
    }

    /**
     * Gets the value of the acspAuthStatusResponseData property.
     * 
     * @return
     *     possible object is
     *     {@link AcspAuthStatusResponseData }
     *     
     */
    public AcspAuthStatusResponseData getAcspAuthStatusResponseData() {
        return acspAuthStatusResponseData;
    }

    /**
     * Sets the value of the acspAuthStatusResponseData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcspAuthStatusResponseData }
     *     
     */
    public void setAcspAuthStatusResponseData(AcspAuthStatusResponseData value) {
        this.acspAuthStatusResponseData = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CredentialAuthResultList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CredentialAuthResultList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}CredentialResponseList">
 *       &lt;sequence>
 *         &lt;element name="challengeQuestionAuthResult" type="{http://ws.csd.rsa.com}ChallengeQuestionAuthResult" minOccurs="0"/>
 *         &lt;element name="oobEmailAuthResult" type="{http://ws.csd.rsa.com}OobEmailAuthResult" minOccurs="0"/>
 *         &lt;element name="oobPhoneAuthResult" type="{http://ws.csd.rsa.com}OobPhoneAuthResult" minOccurs="0"/>
 *         &lt;element name="acspAuthenticationResponseData" type="{http://ws.csd.rsa.com}AcspAuthenticationResponseData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CredentialAuthResultList", propOrder = {
    "challengeQuestionAuthResult",
    "oobEmailAuthResult",
    "oobPhoneAuthResult",
    "acspAuthenticationResponseData"
})
public class CredentialAuthResultList
    extends CredentialResponseList
{

    protected ChallengeQuestionAuthResult challengeQuestionAuthResult;
    protected OobEmailAuthResult oobEmailAuthResult;
    protected OobPhoneAuthResult oobPhoneAuthResult;
    protected AcspAuthenticationResponseData acspAuthenticationResponseData;

    /**
     * Gets the value of the challengeQuestionAuthResult property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeQuestionAuthResult }
     *     
     */
    public ChallengeQuestionAuthResult getChallengeQuestionAuthResult() {
        return challengeQuestionAuthResult;
    }

    /**
     * Sets the value of the challengeQuestionAuthResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionAuthResult }
     *     
     */
    public void setChallengeQuestionAuthResult(ChallengeQuestionAuthResult value) {
        this.challengeQuestionAuthResult = value;
    }

    /**
     * Gets the value of the oobEmailAuthResult property.
     * 
     * @return
     *     possible object is
     *     {@link OobEmailAuthResult }
     *     
     */
    public OobEmailAuthResult getOobEmailAuthResult() {
        return oobEmailAuthResult;
    }

    /**
     * Sets the value of the oobEmailAuthResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobEmailAuthResult }
     *     
     */
    public void setOobEmailAuthResult(OobEmailAuthResult value) {
        this.oobEmailAuthResult = value;
    }

    /**
     * Gets the value of the oobPhoneAuthResult property.
     * 
     * @return
     *     possible object is
     *     {@link OobPhoneAuthResult }
     *     
     */
    public OobPhoneAuthResult getOobPhoneAuthResult() {
        return oobPhoneAuthResult;
    }

    /**
     * Sets the value of the oobPhoneAuthResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobPhoneAuthResult }
     *     
     */
    public void setOobPhoneAuthResult(OobPhoneAuthResult value) {
        this.oobPhoneAuthResult = value;
    }

    /**
     * Gets the value of the acspAuthenticationResponseData property.
     * 
     * @return
     *     possible object is
     *     {@link AcspAuthenticationResponseData }
     *     
     */
    public AcspAuthenticationResponseData getAcspAuthenticationResponseData() {
        return acspAuthenticationResponseData;
    }

    /**
     * Sets the value of the acspAuthenticationResponseData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcspAuthenticationResponseData }
     *     
     */
    public void setAcspAuthenticationResponseData(AcspAuthenticationResponseData value) {
        this.acspAuthenticationResponseData = value;
    }

}

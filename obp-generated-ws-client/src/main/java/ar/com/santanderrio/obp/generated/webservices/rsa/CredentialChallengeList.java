
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CredentialChallengeList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CredentialChallengeList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}CredentialResponseList">
 *       &lt;sequence>
 *         &lt;element name="challengeQuestionChallenge" type="{http://ws.csd.rsa.com}ChallengeQuestionChallenge" minOccurs="0"/>
 *         &lt;element name="oobEmailChallenge" type="{http://ws.csd.rsa.com}OobEmailChallenge" minOccurs="0"/>
 *         &lt;element name="oobPhoneChallenge" type="{http://ws.csd.rsa.com}OobPhoneChallenge" minOccurs="0"/>
 *         &lt;element name="acspChallengeResponseData" type="{http://ws.csd.rsa.com}AcspChallengeResponseData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CredentialChallengeList", propOrder = {
    "challengeQuestionChallenge",
    "oobEmailChallenge",
    "oobPhoneChallenge",
    "acspChallengeResponseData"
})
public class CredentialChallengeList
    extends CredentialResponseList
{

    protected ChallengeQuestionChallenge challengeQuestionChallenge;
    protected OobEmailChallenge oobEmailChallenge;
    protected OobPhoneChallenge oobPhoneChallenge;
    protected AcspChallengeResponseData acspChallengeResponseData;

    /**
     * Gets the value of the challengeQuestionChallenge property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeQuestionChallenge }
     *     
     */
    public ChallengeQuestionChallenge getChallengeQuestionChallenge() {
        return challengeQuestionChallenge;
    }

    /**
     * Sets the value of the challengeQuestionChallenge property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionChallenge }
     *     
     */
    public void setChallengeQuestionChallenge(ChallengeQuestionChallenge value) {
        this.challengeQuestionChallenge = value;
    }

    /**
     * Gets the value of the oobEmailChallenge property.
     * 
     * @return
     *     possible object is
     *     {@link OobEmailChallenge }
     *     
     */
    public OobEmailChallenge getOobEmailChallenge() {
        return oobEmailChallenge;
    }

    /**
     * Sets the value of the oobEmailChallenge property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobEmailChallenge }
     *     
     */
    public void setOobEmailChallenge(OobEmailChallenge value) {
        this.oobEmailChallenge = value;
    }

    /**
     * Gets the value of the oobPhoneChallenge property.
     * 
     * @return
     *     possible object is
     *     {@link OobPhoneChallenge }
     *     
     */
    public OobPhoneChallenge getOobPhoneChallenge() {
        return oobPhoneChallenge;
    }

    /**
     * Sets the value of the oobPhoneChallenge property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobPhoneChallenge }
     *     
     */
    public void setOobPhoneChallenge(OobPhoneChallenge value) {
        this.oobPhoneChallenge = value;
    }

    /**
     * Gets the value of the acspChallengeResponseData property.
     * 
     * @return
     *     possible object is
     *     {@link AcspChallengeResponseData }
     *     
     */
    public AcspChallengeResponseData getAcspChallengeResponseData() {
        return acspChallengeResponseData;
    }

    /**
     * Sets the value of the acspChallengeResponseData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcspChallengeResponseData }
     *     
     */
    public void setAcspChallengeResponseData(AcspChallengeResponseData value) {
        this.acspChallengeResponseData = value;
    }

}

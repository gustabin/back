
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This returns the results of the challenge question authentication
 * 
 * <p>Java class for ChallengeQuestionAuthResultPayload complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChallengeQuestionAuthResultPayload">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authenticationResult" type="{http://ws.csd.rsa.com}AuthenticationResult" minOccurs="0"/>
 *         &lt;element name="callStatus" type="{http://ws.csd.rsa.com}CallStatus" minOccurs="0"/>
 *         &lt;element name="challengeQuestionMatchResult" type="{http://ws.csd.rsa.com}ChallengeQuestionMatchResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChallengeQuestionAuthResultPayload", propOrder = {
    "authenticationResult",
    "callStatus",
    "challengeQuestionMatchResult"
})
public class ChallengeQuestionAuthResultPayload {

    protected AuthenticationResult authenticationResult;
    protected CallStatus callStatus;
    protected ChallengeQuestionMatchResult challengeQuestionMatchResult;

    /**
     * Gets the value of the authenticationResult property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationResult }
     *     
     */
    public AuthenticationResult getAuthenticationResult() {
        return authenticationResult;
    }

    /**
     * Sets the value of the authenticationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationResult }
     *     
     */
    public void setAuthenticationResult(AuthenticationResult value) {
        this.authenticationResult = value;
    }

    /**
     * Gets the value of the callStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CallStatus }
     *     
     */
    public CallStatus getCallStatus() {
        return callStatus;
    }

    /**
     * Sets the value of the callStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallStatus }
     *     
     */
    public void setCallStatus(CallStatus value) {
        this.callStatus = value;
    }

    /**
     * Gets the value of the challengeQuestionMatchResult property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeQuestionMatchResult }
     *     
     */
    public ChallengeQuestionMatchResult getChallengeQuestionMatchResult() {
        return challengeQuestionMatchResult;
    }

    /**
     * Sets the value of the challengeQuestionMatchResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionMatchResult }
     *     
     */
    public void setChallengeQuestionMatchResult(ChallengeQuestionMatchResult value) {
        this.challengeQuestionMatchResult = value;
    }

}

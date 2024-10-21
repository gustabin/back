
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CredentialChallengeRequestList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CredentialChallengeRequestList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}CredentialRequestList">
 *       &lt;sequence>
 *         &lt;element name="challengeQuestionChallengeRequest" type="{http://ws.csd.rsa.com}ChallengeQuestionChallengeRequest" minOccurs="0"/>
 *         &lt;element name="oobEmailChallengeRequest" type="{http://ws.csd.rsa.com}OobEmailChallengeRequest" minOccurs="0"/>
 *         &lt;element name="oobPhoneChallengeRequest" type="{http://ws.csd.rsa.com}OobPhoneChallengeRequest" minOccurs="0"/>
 *         &lt;element name="acspChallengeRequestData" type="{http://ws.csd.rsa.com}AcspChallengeRequestData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CredentialChallengeRequestList", propOrder = {
    "challengeQuestionChallengeRequest",
    "oobEmailChallengeRequest",
    "oobPhoneChallengeRequest",
    "acspChallengeRequestData"
})
public class CredentialChallengeRequestList
    extends CredentialRequestList
{

    protected ChallengeQuestionChallengeRequest challengeQuestionChallengeRequest;
    protected OobEmailChallengeRequest oobEmailChallengeRequest;
    protected OobPhoneChallengeRequest oobPhoneChallengeRequest;
    protected AcspChallengeRequestData acspChallengeRequestData;

    /**
     * Gets the value of the challengeQuestionChallengeRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeQuestionChallengeRequest }
     *     
     */
    public ChallengeQuestionChallengeRequest getChallengeQuestionChallengeRequest() {
        return challengeQuestionChallengeRequest;
    }

    /**
     * Sets the value of the challengeQuestionChallengeRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionChallengeRequest }
     *     
     */
    public void setChallengeQuestionChallengeRequest(ChallengeQuestionChallengeRequest value) {
        this.challengeQuestionChallengeRequest = value;
    }

    /**
     * Gets the value of the oobEmailChallengeRequest property.
     * 
     * @return
     *     possible object is
     *     {@link OobEmailChallengeRequest }
     *     
     */
    public OobEmailChallengeRequest getOobEmailChallengeRequest() {
        return oobEmailChallengeRequest;
    }

    /**
     * Sets the value of the oobEmailChallengeRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobEmailChallengeRequest }
     *     
     */
    public void setOobEmailChallengeRequest(OobEmailChallengeRequest value) {
        this.oobEmailChallengeRequest = value;
    }

    /**
     * Gets the value of the oobPhoneChallengeRequest property.
     * 
     * @return
     *     possible object is
     *     {@link OobPhoneChallengeRequest }
     *     
     */
    public OobPhoneChallengeRequest getOobPhoneChallengeRequest() {
        return oobPhoneChallengeRequest;
    }

    /**
     * Sets the value of the oobPhoneChallengeRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobPhoneChallengeRequest }
     *     
     */
    public void setOobPhoneChallengeRequest(OobPhoneChallengeRequest value) {
        this.oobPhoneChallengeRequest = value;
    }

    /**
     * Gets the value of the acspChallengeRequestData property.
     * 
     * @return
     *     possible object is
     *     {@link AcspChallengeRequestData }
     *     
     */
    public AcspChallengeRequestData getAcspChallengeRequestData() {
        return acspChallengeRequestData;
    }

    /**
     * Sets the value of the acspChallengeRequestData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcspChallengeRequestData }
     *     
     */
    public void setAcspChallengeRequestData(AcspChallengeRequestData value) {
        this.acspChallengeRequestData = value;
    }

}

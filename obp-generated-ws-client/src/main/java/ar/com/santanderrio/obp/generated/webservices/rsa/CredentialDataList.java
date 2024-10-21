
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CredentialDataList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CredentialDataList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}CredentialRequestList">
 *       &lt;sequence>
 *         &lt;element name="challengeQuestionData" type="{http://ws.csd.rsa.com}ChallengeQuestionData" minOccurs="0"/>
 *         &lt;element name="oobEmailData" type="{http://ws.csd.rsa.com}OobEmailData" minOccurs="0"/>
 *         &lt;element name="oobPhoneData" type="{http://ws.csd.rsa.com}OobPhoneData" minOccurs="0"/>
 *         &lt;element name="acspAuthenticationRequestData" type="{http://ws.csd.rsa.com}AcspAuthenticationRequestData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CredentialDataList", propOrder = {
    "challengeQuestionData",
    "oobEmailData",
    "oobPhoneData",
    "acspAuthenticationRequestData"
})
public class CredentialDataList
    extends CredentialRequestList
{

    protected ChallengeQuestionData challengeQuestionData;
    protected OobEmailData oobEmailData;
    protected OobPhoneData oobPhoneData;
    protected AcspAuthenticationRequestData acspAuthenticationRequestData;

    /**
     * Gets the value of the challengeQuestionData property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeQuestionData }
     *     
     */
    public ChallengeQuestionData getChallengeQuestionData() {
        return challengeQuestionData;
    }

    /**
     * Sets the value of the challengeQuestionData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionData }
     *     
     */
    public void setChallengeQuestionData(ChallengeQuestionData value) {
        this.challengeQuestionData = value;
    }

    /**
     * Gets the value of the oobEmailData property.
     * 
     * @return
     *     possible object is
     *     {@link OobEmailData }
     *     
     */
    public OobEmailData getOobEmailData() {
        return oobEmailData;
    }

    /**
     * Sets the value of the oobEmailData property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobEmailData }
     *     
     */
    public void setOobEmailData(OobEmailData value) {
        this.oobEmailData = value;
    }

    /**
     * Gets the value of the oobPhoneData property.
     * 
     * @return
     *     possible object is
     *     {@link OobPhoneData }
     *     
     */
    public OobPhoneData getOobPhoneData() {
        return oobPhoneData;
    }

    /**
     * Sets the value of the oobPhoneData property.
     * 
     * @param value
     *     allowed object is
     *     {@link OobPhoneData }
     *     
     */
    public void setOobPhoneData(OobPhoneData value) {
        this.oobPhoneData = value;
    }

    /**
     * Gets the value of the acspAuthenticationRequestData property.
     * 
     * @return
     *     possible object is
     *     {@link AcspAuthenticationRequestData }
     *     
     */
    public AcspAuthenticationRequestData getAcspAuthenticationRequestData() {
        return acspAuthenticationRequestData;
    }

    /**
     * Sets the value of the acspAuthenticationRequestData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcspAuthenticationRequestData }
     *     
     */
    public void setAcspAuthenticationRequestData(AcspAuthenticationRequestData value) {
        this.acspAuthenticationRequestData = value;
    }

}

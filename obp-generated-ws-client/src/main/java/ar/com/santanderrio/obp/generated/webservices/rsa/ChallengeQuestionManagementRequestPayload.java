
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This defines the Challenge Question Management request
 * 
 * <p>Java class for ChallengeQuestionManagementRequestPayload complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChallengeQuestionManagementRequestPayload">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actionTypeList" type="{http://ws.csd.rsa.com}ChallengeQuestionActionTypeList" minOccurs="0"/>
 *         &lt;element name="challengeQuestionConfig" type="{http://ws.csd.rsa.com}ChallengeQuestionConfig" minOccurs="0"/>
 *         &lt;element name="challengeQuestionList" type="{http://ws.csd.rsa.com}ChallengeQuestionList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChallengeQuestionManagementRequestPayload", propOrder = {
    "actionTypeList",
    "challengeQuestionConfig",
    "challengeQuestionList"
})
public class ChallengeQuestionManagementRequestPayload {

    protected ChallengeQuestionActionTypeList actionTypeList;
    protected ChallengeQuestionConfig challengeQuestionConfig;
    protected ChallengeQuestionList challengeQuestionList;

    /**
     * Gets the value of the actionTypeList property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeQuestionActionTypeList }
     *     
     */
    public ChallengeQuestionActionTypeList getActionTypeList() {
        return actionTypeList;
    }

    /**
     * Sets the value of the actionTypeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionActionTypeList }
     *     
     */
    public void setActionTypeList(ChallengeQuestionActionTypeList value) {
        this.actionTypeList = value;
    }

    /**
     * Gets the value of the challengeQuestionConfig property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeQuestionConfig }
     *     
     */
    public ChallengeQuestionConfig getChallengeQuestionConfig() {
        return challengeQuestionConfig;
    }

    /**
     * Sets the value of the challengeQuestionConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionConfig }
     *     
     */
    public void setChallengeQuestionConfig(ChallengeQuestionConfig value) {
        this.challengeQuestionConfig = value;
    }

    /**
     * Gets the value of the challengeQuestionList property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeQuestionList }
     *     
     */
    public ChallengeQuestionList getChallengeQuestionList() {
        return challengeQuestionList;
    }

    /**
     * Sets the value of the challengeQuestionList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionList }
     *     
     */
    public void setChallengeQuestionList(ChallengeQuestionList value) {
        this.challengeQuestionList = value;
    }

}

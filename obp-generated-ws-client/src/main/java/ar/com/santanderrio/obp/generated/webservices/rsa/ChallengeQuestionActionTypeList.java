
package ar.com.santanderrio.obp.generated.webservices.rsa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This defines a list of challenge question action types
 * 
 * <p>Java class for ChallengeQuestionActionTypeList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChallengeQuestionActionTypeList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}ActionTypeList">
 *       &lt;sequence>
 *         &lt;element name="challengeQuestionActionType" type="{http://ws.csd.rsa.com}ChallengeQuestionActionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChallengeQuestionActionTypeList", propOrder = {
    "challengeQuestionActionType"
})
public class ChallengeQuestionActionTypeList
    extends ActionTypeList
{

    protected List<ChallengeQuestionActionType> challengeQuestionActionType;

    /**
     * Gets the value of the challengeQuestionActionType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the challengeQuestionActionType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChallengeQuestionActionType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChallengeQuestionActionType }
     * 
     * 
     */
    public List<ChallengeQuestionActionType> getChallengeQuestionActionType() {
        if (challengeQuestionActionType == null) {
            challengeQuestionActionType = new ArrayList<ChallengeQuestionActionType>();
        }
        return this.challengeQuestionActionType;
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This requests the results of the challenge question challenge method
 * 
 * <p>Java class for ChallengeQuestionChallengeRequestPayload complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChallengeQuestionChallengeRequestPayload">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="excludeQuestions" type="{http://ws.csd.rsa.com}ChallengeQuestionIdList" minOccurs="0"/>
 *         &lt;element name="numberOfQuestion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChallengeQuestionChallengeRequestPayload", propOrder = {
    "excludeQuestions",
    "numberOfQuestion"
})
public class ChallengeQuestionChallengeRequestPayload {

    protected ChallengeQuestionIdList excludeQuestions;
    protected Integer numberOfQuestion;

    /**
     * Gets the value of the excludeQuestions property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeQuestionIdList }
     *     
     */
    public ChallengeQuestionIdList getExcludeQuestions() {
        return excludeQuestions;
    }

    /**
     * Sets the value of the excludeQuestions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionIdList }
     *     
     */
    public void setExcludeQuestions(ChallengeQuestionIdList value) {
        this.excludeQuestions = value;
    }

    /**
     * Gets the value of the numberOfQuestion property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    /**
     * Sets the value of the numberOfQuestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfQuestion(Integer value) {
        this.numberOfQuestion = value;
    }

}

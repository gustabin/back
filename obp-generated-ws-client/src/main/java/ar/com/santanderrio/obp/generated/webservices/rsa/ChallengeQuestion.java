
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This defines challenge question metadata
 * 
 * <p>Java class for ChallengeQuestion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChallengeQuestion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actualAnswer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actualAnswerOnFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="questionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="questionText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAnswer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChallengeQuestion", propOrder = {
    "actualAnswer",
    "actualAnswerOnFile",
    "questionId",
    "questionText",
    "userAnswer"
})
public class ChallengeQuestion {

    protected String actualAnswer;
    protected String actualAnswerOnFile;
    protected String questionId;
    protected String questionText;
    protected String userAnswer;

    /**
     * Gets the value of the actualAnswer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActualAnswer() {
        return actualAnswer;
    }

    /**
     * Sets the value of the actualAnswer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActualAnswer(String value) {
        this.actualAnswer = value;
    }

    /**
     * Gets the value of the actualAnswerOnFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActualAnswerOnFile() {
        return actualAnswerOnFile;
    }

    /**
     * Sets the value of the actualAnswerOnFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActualAnswerOnFile(String value) {
        this.actualAnswerOnFile = value;
    }

    /**
     * Gets the value of the questionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * Sets the value of the questionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionId(String value) {
        this.questionId = value;
    }

    /**
     * Gets the value of the questionText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Sets the value of the questionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionText(String value) {
        this.questionText = value;
    }

    /**
     * Gets the value of the userAnswer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAnswer() {
        return userAnswer;
    }

    /**
     * Sets the value of the userAnswer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAnswer(String value) {
        this.userAnswer = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This defines the configuration data for challenge questions
 * 
 * <p>Java class for ChallengeQuestionConfig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChallengeQuestionConfig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="excludeQuestionList" type="{http://ws.csd.rsa.com}ChallengeQuestionIdList" minOccurs="0"/>
 *         &lt;element name="excludeUserQuestions" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="groupCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="includeRetired" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="questionCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChallengeQuestionConfig", propOrder = {
    "excludeQuestionList",
    "excludeUserQuestions",
    "groupCount",
    "includeRetired",
    "questionCount"
})
public class ChallengeQuestionConfig {

    protected ChallengeQuestionIdList excludeQuestionList;
    protected Boolean excludeUserQuestions;
    protected Integer groupCount;
    protected Boolean includeRetired;
    protected Integer questionCount;

    /**
     * Gets the value of the excludeQuestionList property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeQuestionIdList }
     *     
     */
    public ChallengeQuestionIdList getExcludeQuestionList() {
        return excludeQuestionList;
    }

    /**
     * Sets the value of the excludeQuestionList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeQuestionIdList }
     *     
     */
    public void setExcludeQuestionList(ChallengeQuestionIdList value) {
        this.excludeQuestionList = value;
    }

    /**
     * Gets the value of the excludeUserQuestions property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExcludeUserQuestions() {
        return excludeUserQuestions;
    }

    /**
     * Sets the value of the excludeUserQuestions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExcludeUserQuestions(Boolean value) {
        this.excludeUserQuestions = value;
    }

    /**
     * Gets the value of the groupCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGroupCount() {
        return groupCount;
    }

    /**
     * Sets the value of the groupCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGroupCount(Integer value) {
        this.groupCount = value;
    }

    /**
     * Gets the value of the includeRetired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeRetired() {
        return includeRetired;
    }

    /**
     * Sets the value of the includeRetired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeRetired(Boolean value) {
        this.includeRetired = value;
    }

    /**
     * Gets the value of the questionCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuestionCount() {
        return questionCount;
    }

    /**
     * Sets the value of the questionCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuestionCount(Integer value) {
        this.questionCount = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KBAAuthenticationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KBAAuthenticationResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}AcspAuthenticationResponse">
 *       &lt;sequence>
 *         &lt;element name="resultStatus" type="{http://ws.kba.csd.rsa.com}ResultStatus" minOccurs="0"/>
 *         &lt;element name="questions" type="{http://ws.kba.csd.rsa.com}Question" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KBAAuthenticationResponse", namespace = "http://ws.kba.csd.rsa.com", propOrder = {
    "resultStatus",
    "questions"
})
public class KBAAuthenticationResponse
    extends AcspAuthenticationResponse
{

    protected ResultStatus resultStatus;
    protected List<Question> questions;

    /**
     * Gets the value of the resultStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ResultStatus }
     *     
     */
    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    /**
     * Sets the value of the resultStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultStatus }
     *     
     */
    public void setResultStatus(ResultStatus value) {
        this.resultStatus = value;
    }

    /**
     * Gets the value of the questions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the questions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuestions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Question }
     * 
     * 
     */
    public List<Question> getQuestions() {
        if (questions == null) {
            questions = new ArrayList<Question>();
        }
        return this.questions;
    }

}

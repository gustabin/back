
package ar.com.santanderrio.obp.generated.webservices.rsa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KBAChallengeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KBAChallengeResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}AcspChallengeResponse">
 *       &lt;sequence>
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
@XmlType(name = "KBAChallengeResponse", namespace = "http://ws.kba.csd.rsa.com", propOrder = {
    "questions"
})
public class KBAChallengeResponse
    extends AcspChallengeResponse
{

    protected List<Question> questions;

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

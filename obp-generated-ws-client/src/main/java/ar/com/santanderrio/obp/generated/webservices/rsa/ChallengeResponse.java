
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="challengeReturn" type="{http://ws.csd.rsa.com}ChallengeResponse"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "challengeReturn"
})
@XmlRootElement(name = "challengeResponse")
public class ChallengeResponse {

    @XmlElement(required = true)
    protected ChallengeResponse2 challengeReturn;

    /**
     * Gets the value of the challengeReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ChallengeResponse2 }
     *     
     */
    public ChallengeResponse2 getChallengeReturn() {
        return challengeReturn;
    }

    /**
     * Sets the value of the challengeReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChallengeResponse2 }
     *     
     */
    public void setChallengeReturn(ChallengeResponse2 value) {
        this.challengeReturn = value;
    }

}

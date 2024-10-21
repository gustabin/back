
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This type defines the OOB Email CHALLENGE Request Payload
 * 
 * <p>Java class for OobEmailChallengeRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OobEmailChallengeRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payload" type="{http://ws.csd.rsa.com}OOBEmailChallengeRequestPayload" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OobEmailChallengeRequest", propOrder = {
    "payload"
})
public class OobEmailChallengeRequest {

    protected OOBEmailChallengeRequestPayload payload;

    /**
     * Gets the value of the payload property.
     * 
     * @return
     *     possible object is
     *     {@link OOBEmailChallengeRequestPayload }
     *     
     */
    public OOBEmailChallengeRequestPayload getPayload() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     * 
     * @param value
     *     allowed object is
     *     {@link OOBEmailChallengeRequestPayload }
     *     
     */
    public void setPayload(OOBEmailChallengeRequestPayload value) {
        this.payload = value;
    }

}

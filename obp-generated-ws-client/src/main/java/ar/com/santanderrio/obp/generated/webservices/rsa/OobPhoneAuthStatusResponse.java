
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This type defines the OOB Phone AUTHSTATUS Response Payload
 * 
 * <p>Java class for OobPhoneAuthStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OobPhoneAuthStatusResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payload" type="{http://ws.csd.rsa.com}OOBInfoResponsePayload" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OobPhoneAuthStatusResponse", propOrder = {
    "payload"
})
public class OobPhoneAuthStatusResponse {

    protected OOBInfoResponsePayload payload;

    /**
     * Gets the value of the payload property.
     * 
     * @return
     *     possible object is
     *     {@link OOBInfoResponsePayload }
     *     
     */
    public OOBInfoResponsePayload getPayload() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     * 
     * @param value
     *     allowed object is
     *     {@link OOBInfoResponsePayload }
     *     
     */
    public void setPayload(OOBInfoResponsePayload value) {
        this.payload = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This type defines the Credential AuthStatus Response Payload
 * 
 * <p>Java class for AcspAuthStatusResponseData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AcspAuthStatusResponseData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acspAccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="callStatus" type="{http://ws.csd.rsa.com}CallStatus" minOccurs="0"/>
 *         &lt;element name="payload" type="{http://ws.csd.rsa.com}AcspAuthStatusResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcspAuthStatusResponseData", propOrder = {
    "acspAccountId",
    "callStatus",
    "payload"
})
public class AcspAuthStatusResponseData {

    protected String acspAccountId;
    protected CallStatus callStatus;
    protected AcspAuthStatusResponse payload;

    /**
     * Gets the value of the acspAccountId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcspAccountId() {
        return acspAccountId;
    }

    /**
     * Sets the value of the acspAccountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcspAccountId(String value) {
        this.acspAccountId = value;
    }

    /**
     * Gets the value of the callStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CallStatus }
     *     
     */
    public CallStatus getCallStatus() {
        return callStatus;
    }

    /**
     * Sets the value of the callStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallStatus }
     *     
     */
    public void setCallStatus(CallStatus value) {
        this.callStatus = value;
    }

    /**
     * Gets the value of the payload property.
     * 
     * @return
     *     possible object is
     *     {@link AcspAuthStatusResponse }
     *     
     */
    public AcspAuthStatusResponse getPayload() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcspAuthStatusResponse }
     *     
     */
    public void setPayload(AcspAuthStatusResponse value) {
        this.payload = value;
    }

}

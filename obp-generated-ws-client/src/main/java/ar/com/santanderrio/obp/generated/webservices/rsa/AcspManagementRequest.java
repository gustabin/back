
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * This type defines Interface for Management Request
 * 
 * <p>Java class for AcspManagementRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AcspManagementRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="opcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcspManagementRequest", propOrder = {
    "opcode"
})
@XmlSeeAlso({
    OTPManagementRequest.class,
    KBAManagementRequest.class,
    OOBGenManagementRequest.class
})
public abstract class AcspManagementRequest {

    protected String opcode;

    /**
     * Gets the value of the opcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpcode() {
        return opcode;
    }

    /**
     * Sets the value of the opcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpcode(String value) {
        this.opcode = value;
    }

}

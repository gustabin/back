
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This object defines the ssn information for a user
 * 
 * <p>Java class for SSNInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SSNInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ssn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ssnType" type="{http://ws.kba.csd.rsa.com}SSNType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SSNInfo", namespace = "http://ws.kba.csd.rsa.com", propOrder = {
    "ssn",
    "ssnType"
})
public class SSNInfo {

    protected String ssn;
    protected SSNType ssnType;

    /**
     * Gets the value of the ssn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Sets the value of the ssn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsn(String value) {
        this.ssn = value;
    }

    /**
     * Gets the value of the ssnType property.
     * 
     * @return
     *     possible object is
     *     {@link SSNType }
     *     
     */
    public SSNType getSsnType() {
        return ssnType;
    }

    /**
     * Sets the value of the ssnType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SSNType }
     *     
     */
    public void setSsnType(SSNType value) {
        this.ssnType = value;
    }

}

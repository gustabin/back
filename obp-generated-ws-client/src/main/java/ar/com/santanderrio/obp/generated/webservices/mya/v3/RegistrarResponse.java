
package ar.com.santanderrio.obp.generated.webservices.mya.v3;

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
 *         &lt;element name="registrarReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "registrarReturn"
})
@XmlRootElement(name = "registrarResponse")
public class RegistrarResponse {

    @XmlElement(required = true, nillable = true)
    protected String registrarReturn;

    /**
     * Gets the value of the registrarReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrarReturn() {
        return registrarReturn;
    }

    /**
     * Sets the value of the registrarReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrarReturn(String value) {
        this.registrarReturn = value;
    }

}

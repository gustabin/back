
package ar.com.santanderrio.obp.generated.webservices.mya.v2;

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
 *         &lt;element name="registrarConDestinoReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "registrarConDestinoReturn"
})
@XmlRootElement(name = "registrarConDestinoResponse")
public class RegistrarConDestinoResponse {

    @XmlElement(required = true, nillable = true)
    protected String registrarConDestinoReturn;

    /**
     * Gets the value of the registrarConDestinoReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrarConDestinoReturn() {
        return registrarConDestinoReturn;
    }

    /**
     * Sets the value of the registrarConDestinoReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrarConDestinoReturn(String value) {
        this.registrarConDestinoReturn = value;
    }

}

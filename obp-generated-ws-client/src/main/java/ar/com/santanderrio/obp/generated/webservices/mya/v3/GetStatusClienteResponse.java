
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
 *         &lt;element name="getStatusClienteReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getStatusClienteReturn"
})
@XmlRootElement(name = "getStatusClienteResponse")
public class GetStatusClienteResponse {

    @XmlElement(required = true, nillable = true)
    protected String getStatusClienteReturn;

    /**
     * Gets the value of the getStatusClienteReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetStatusClienteReturn() {
        return getStatusClienteReturn;
    }

    /**
     * Sets the value of the getStatusClienteReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetStatusClienteReturn(String value) {
        this.getStatusClienteReturn = value;
    }

}

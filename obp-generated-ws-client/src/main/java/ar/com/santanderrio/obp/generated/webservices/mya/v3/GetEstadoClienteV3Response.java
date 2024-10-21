
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
 *         &lt;element name="getEstadoClienteV3Return" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getEstadoClienteV3Return"
})
@XmlRootElement(name = "getEstadoClienteV3Response")
public class GetEstadoClienteV3Response {

    @XmlElement(required = true, nillable = true)
    protected String getEstadoClienteV3Return;

    /**
     * Gets the value of the getEstadoClienteV3Return property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetEstadoClienteV3Return() {
        return getEstadoClienteV3Return;
    }

    /**
     * Sets the value of the getEstadoClienteV3Return property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetEstadoClienteV3Return(String value) {
        this.getEstadoClienteV3Return = value;
    }

}

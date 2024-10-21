
package ar.com.santanderrio.obp.generated.webservices.productos;

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
 *         &lt;element name="avanzarGestionReturn" type="{http://webService.core.ges.rio.com}RetornoWS"/>
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
    "avanzarGestionReturn"
})
@XmlRootElement(name = "avanzarGestionResponse")
public class AvanzarGestionResponse {

    @XmlElement(required = true, nillable = true)
    protected RetornoWS avanzarGestionReturn;

    /**
     * Gets the value of the avanzarGestionReturn property.
     * 
     * @return
     *     possible object is
     *     {@link RetornoWS }
     *     
     */
    public RetornoWS getAvanzarGestionReturn() {
        return avanzarGestionReturn;
    }

    /**
     * Sets the value of the avanzarGestionReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetornoWS }
     *     
     */
    public void setAvanzarGestionReturn(RetornoWS value) {
        this.avanzarGestionReturn = value;
    }

}

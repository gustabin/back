
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
 *         &lt;element name="altaGestionReturn" type="{http://webService.core.ges.rio.com}ResultadoAltaWS"/>
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
    "altaGestionReturn"
})
@XmlRootElement(name = "altaGestionResponse")
public class AltaGestionResponse {

    @XmlElement(required = true, nillable = true)
    protected ResultadoAltaWS altaGestionReturn;

    /**
     * Gets the value of the altaGestionReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoAltaWS }
     *     
     */
    public ResultadoAltaWS getAltaGestionReturn() {
        return altaGestionReturn;
    }

    /**
     * Sets the value of the altaGestionReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoAltaWS }
     *     
     */
    public void setAltaGestionReturn(ResultadoAltaWS value) {
        this.altaGestionReturn = value;
    }

}

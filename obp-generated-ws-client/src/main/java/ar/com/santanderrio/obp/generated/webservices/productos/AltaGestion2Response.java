
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
 *         &lt;element name="altaGestion2Return" type="{http://webService.core.ges.rio.com}ResultadoAltaWS"/>
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
    "altaGestion2Return"
})
@XmlRootElement(name = "altaGestion2Response")
public class AltaGestion2Response {

    @XmlElement(required = true, nillable = true)
    protected ResultadoAltaWS altaGestion2Return;

    /**
     * Gets the value of the altaGestion2Return property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoAltaWS }
     *     
     */
    public ResultadoAltaWS getAltaGestion2Return() {
        return altaGestion2Return;
    }

    /**
     * Sets the value of the altaGestion2Return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoAltaWS }
     *     
     */
    public void setAltaGestion2Return(ResultadoAltaWS value) {
        this.altaGestion2Return = value;
    }

}

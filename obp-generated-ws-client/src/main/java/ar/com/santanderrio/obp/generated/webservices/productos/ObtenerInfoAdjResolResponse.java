
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
 *         &lt;element name="obtenerInfoAdjResolReturn" type="{http://webService.core.ges.rio.com}RetornoListaInfoAdjWS"/>
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
    "obtenerInfoAdjResolReturn"
})
@XmlRootElement(name = "obtenerInfoAdjResolResponse")
public class ObtenerInfoAdjResolResponse {

    @XmlElement(required = true, nillable = true)
    protected RetornoListaInfoAdjWS obtenerInfoAdjResolReturn;

    /**
     * Gets the value of the obtenerInfoAdjResolReturn property.
     * 
     * @return
     *     possible object is
     *     {@link RetornoListaInfoAdjWS }
     *     
     */
    public RetornoListaInfoAdjWS getObtenerInfoAdjResolReturn() {
        return obtenerInfoAdjResolReturn;
    }

    /**
     * Sets the value of the obtenerInfoAdjResolReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetornoListaInfoAdjWS }
     *     
     */
    public void setObtenerInfoAdjResolReturn(RetornoListaInfoAdjWS value) {
        this.obtenerInfoAdjResolReturn = value;
    }

}

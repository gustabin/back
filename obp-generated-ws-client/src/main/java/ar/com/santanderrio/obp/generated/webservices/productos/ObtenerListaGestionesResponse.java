
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
 *         &lt;element name="obtenerListaGestionesReturn" type="{http://webService.core.ges.rio.com}RetornoListaWS"/>
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
    "obtenerListaGestionesReturn"
})
@XmlRootElement(name = "obtenerListaGestionesResponse")
public class ObtenerListaGestionesResponse {

    @XmlElement(required = true, nillable = true)
    protected RetornoListaWS obtenerListaGestionesReturn;

    /**
     * Gets the value of the obtenerListaGestionesReturn property.
     * 
     * @return
     *     possible object is
     *     {@link RetornoListaWS }
     *     
     */
    public RetornoListaWS getObtenerListaGestionesReturn() {
        return obtenerListaGestionesReturn;
    }

    /**
     * Sets the value of the obtenerListaGestionesReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetornoListaWS }
     *     
     */
    public void setObtenerListaGestionesReturn(RetornoListaWS value) {
        this.obtenerListaGestionesReturn = value;
    }

}

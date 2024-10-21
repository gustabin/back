
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
 *         &lt;element name="obtenerGestionesSucuAdminReturn" type="{http://webService.core.ges.rio.com}RetornoListaGestionClienteWS"/>
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
    "obtenerGestionesSucuAdminReturn"
})
@XmlRootElement(name = "obtenerGestionesSucuAdminResponse")
public class ObtenerGestionesSucuAdminResponse {

    @XmlElement(required = true, nillable = true)
    protected RetornoListaGestionClienteWS obtenerGestionesSucuAdminReturn;

    /**
     * Gets the value of the obtenerGestionesSucuAdminReturn property.
     * 
     * @return
     *     possible object is
     *     {@link RetornoListaGestionClienteWS }
     *     
     */
    public RetornoListaGestionClienteWS getObtenerGestionesSucuAdminReturn() {
        return obtenerGestionesSucuAdminReturn;
    }

    /**
     * Sets the value of the obtenerGestionesSucuAdminReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetornoListaGestionClienteWS }
     *     
     */
    public void setObtenerGestionesSucuAdminReturn(RetornoListaGestionClienteWS value) {
        this.obtenerGestionesSucuAdminReturn = value;
    }

}

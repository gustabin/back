
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
 *         &lt;element name="obtenerGestionesEjecutivoReturn" type="{http://webService.core.ges.rio.com}RetornoListaGestionClienteWS"/>
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
    "obtenerGestionesEjecutivoReturn"
})
@XmlRootElement(name = "obtenerGestionesEjecutivoResponse")
public class ObtenerGestionesEjecutivoResponse {

    @XmlElement(required = true, nillable = true)
    protected RetornoListaGestionClienteWS obtenerGestionesEjecutivoReturn;

    /**
     * Gets the value of the obtenerGestionesEjecutivoReturn property.
     * 
     * @return
     *     possible object is
     *     {@link RetornoListaGestionClienteWS }
     *     
     */
    public RetornoListaGestionClienteWS getObtenerGestionesEjecutivoReturn() {
        return obtenerGestionesEjecutivoReturn;
    }

    /**
     * Sets the value of the obtenerGestionesEjecutivoReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetornoListaGestionClienteWS }
     *     
     */
    public void setObtenerGestionesEjecutivoReturn(RetornoListaGestionClienteWS value) {
        this.obtenerGestionesEjecutivoReturn = value;
    }

}

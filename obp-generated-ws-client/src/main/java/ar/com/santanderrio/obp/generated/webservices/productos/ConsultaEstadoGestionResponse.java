
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
 *         &lt;element name="consultaEstadoGestionReturn" type="{http://webService.core.ges.rio.com}EstadoGestionWS"/>
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
    "consultaEstadoGestionReturn"
})
@XmlRootElement(name = "consultaEstadoGestionResponse")
public class ConsultaEstadoGestionResponse {

    @XmlElement(required = true, nillable = true)
    protected EstadoGestionWS consultaEstadoGestionReturn;

    /**
     * Gets the value of the consultaEstadoGestionReturn property.
     * 
     * @return
     *     possible object is
     *     {@link EstadoGestionWS }
     *     
     */
    public EstadoGestionWS getConsultaEstadoGestionReturn() {
        return consultaEstadoGestionReturn;
    }

    /**
     * Sets the value of the consultaEstadoGestionReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoGestionWS }
     *     
     */
    public void setConsultaEstadoGestionReturn(EstadoGestionWS value) {
        this.consultaEstadoGestionReturn = value;
    }

}

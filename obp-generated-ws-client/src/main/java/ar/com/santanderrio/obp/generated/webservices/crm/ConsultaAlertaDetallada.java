
package ar.com.santanderrio.obp.generated.webservices.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="requestCliente" type="{http://model.alertas.crm.bancorio.com/}RequestCliente" minOccurs="0"/>
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
    "requestCliente"
})
@XmlRootElement(name = "ConsultaAlertaDetallada")
public class ConsultaAlertaDetallada {

    protected RequestCliente requestCliente;

    /**
     * Gets the value of the requestCliente property.
     * 
     * @return
     *     possible object is
     *     {@link RequestCliente }
     *     
     */
    public RequestCliente getRequestCliente() {
        return requestCliente;
    }

    /**
     * Sets the value of the requestCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestCliente }
     *     
     */
    public void setRequestCliente(RequestCliente value) {
        this.requestCliente = value;
    }

}

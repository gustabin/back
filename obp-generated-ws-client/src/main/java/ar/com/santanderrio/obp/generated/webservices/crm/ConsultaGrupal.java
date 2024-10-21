
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
 *         &lt;element name="requestGrupal" type="{http://model.alertas.crm.bancorio.com/}RequestGrupal" minOccurs="0"/>
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
    "requestGrupal"
})
@XmlRootElement(name = "ConsultaGrupal")
public class ConsultaGrupal {

    protected RequestGrupal requestGrupal;

    /**
     * Gets the value of the requestGrupal property.
     * 
     * @return
     *     possible object is
     *     {@link RequestGrupal }
     *     
     */
    public RequestGrupal getRequestGrupal() {
        return requestGrupal;
    }

    /**
     * Sets the value of the requestGrupal property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestGrupal }
     *     
     */
    public void setRequestGrupal(RequestGrupal value) {
        this.requestGrupal = value;
    }

}

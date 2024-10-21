
package ar.com.santanderrio.obp.generated.webservices.visa.planv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for confirmarSolicitudPlanVResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="confirmarSolicitudPlanVResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://services.planv.visa.com/}confirmacionSolicitudPlanV" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmarSolicitudPlanVResponse", propOrder = {
    "_return"
})
public class ConfirmarSolicitudPlanVResponse {

    @XmlElement(name = "return")
    protected ConfirmacionSolicitudPlanV _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link ConfirmacionSolicitudPlanV }
     *     
     */
    public ConfirmacionSolicitudPlanV getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfirmacionSolicitudPlanV }
     *     
     */
    public void setReturn(ConfirmacionSolicitudPlanV value) {
        this._return = value;
    }

}

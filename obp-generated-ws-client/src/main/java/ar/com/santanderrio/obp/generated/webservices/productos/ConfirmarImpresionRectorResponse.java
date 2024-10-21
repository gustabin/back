
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
 *         &lt;element name="confirmarImpresionRectorReturn" type="{http://webService.gestcli.ges.rio.com}ArrayOf_1587703434_1440052060_nillable_string"/>
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
    "confirmarImpresionRectorReturn"
})
@XmlRootElement(name = "confirmarImpresionRectorResponse")
public class ConfirmarImpresionRectorResponse {

    @XmlElement(required = true, nillable = true)
    protected ArrayOf15877034341440052060NillableString confirmarImpresionRectorReturn;

    /**
     * Gets the value of the confirmarImpresionRectorReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOf15877034341440052060NillableString }
     *     
     */
    public ArrayOf15877034341440052060NillableString getConfirmarImpresionRectorReturn() {
        return confirmarImpresionRectorReturn;
    }

    /**
     * Sets the value of the confirmarImpresionRectorReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOf15877034341440052060NillableString }
     *     
     */
    public void setConfirmarImpresionRectorReturn(ArrayOf15877034341440052060NillableString value) {
        this.confirmarImpresionRectorReturn = value;
    }

}

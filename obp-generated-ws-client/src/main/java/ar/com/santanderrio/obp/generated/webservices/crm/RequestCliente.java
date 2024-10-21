
package ar.com.santanderrio.obp.generated.webservices.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestCliente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestCliente">
 *   &lt;complexContent>
 *     &lt;extension base="{http://model.alertas.crm.bancorio.com/}Request">
 *       &lt;sequence>
 *         &lt;element name="repeticiones" type="{http://model.alertas.crm.bancorio.com/}ArrayOfSubRequestCliente" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestCliente", propOrder = {
    "repeticiones"
})
public class RequestCliente
    extends Request
{

    protected ArrayOfSubRequestCliente repeticiones;

    /**
     * Gets the value of the repeticiones property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSubRequestCliente }
     *     
     */
    public ArrayOfSubRequestCliente getRepeticiones() {
        return repeticiones;
    }

    /**
     * Sets the value of the repeticiones property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSubRequestCliente }
     *     
     */
    public void setRepeticiones(ArrayOfSubRequestCliente value) {
        this.repeticiones = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseCliente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseCliente">
 *   &lt;complexContent>
 *     &lt;extension base="{http://model.alertas.crm.bancorio.com/}Response">
 *       &lt;sequence>
 *         &lt;element name="nup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repeticiones" type="{http://model.alertas.crm.bancorio.com/}ArrayOfSubResponseCliente" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseCliente", propOrder = {
    "nup",
    "repeticiones"
})
public class ResponseCliente
    extends Response
{

    protected String nup;
    protected ArrayOfSubResponseCliente repeticiones;

    /**
     * Gets the value of the nup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNup() {
        return nup;
    }

    /**
     * Sets the value of the nup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNup(String value) {
        this.nup = value;
    }

    /**
     * Gets the value of the repeticiones property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSubResponseCliente }
     *     
     */
    public ArrayOfSubResponseCliente getRepeticiones() {
        return repeticiones;
    }

    /**
     * Sets the value of the repeticiones property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSubResponseCliente }
     *     
     */
    public void setRepeticiones(ArrayOfSubResponseCliente value) {
        this.repeticiones = value;
    }

}

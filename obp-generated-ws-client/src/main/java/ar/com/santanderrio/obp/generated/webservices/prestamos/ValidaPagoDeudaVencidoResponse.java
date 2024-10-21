
package ar.com.santanderrio.obp.generated.webservices.prestamos;

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
 *         &lt;element name="Valida_Pago_Deuda_VencidoResult" type="{http://tempuri.org/}BCRAResponse" minOccurs="0"/>
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
    "validaPagoDeudaVencidoResult"
})
@XmlRootElement(name = "Valida_Pago_Deuda_VencidoResponse")
public class ValidaPagoDeudaVencidoResponse {

    @XmlElement(name = "Valida_Pago_Deuda_VencidoResult")
    protected BCRAResponse validaPagoDeudaVencidoResult;

    /**
     * Gets the value of the validaPagoDeudaVencidoResult property.
     * 
     * @return
     *     possible object is
     *     {@link BCRAResponse }
     *     
     */
    public BCRAResponse getValidaPagoDeudaVencidoResult() {
        return validaPagoDeudaVencidoResult;
    }

    /**
     * Sets the value of the validaPagoDeudaVencidoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BCRAResponse }
     *     
     */
    public void setValidaPagoDeudaVencidoResult(BCRAResponse value) {
        this.validaPagoDeudaVencidoResult = value;
    }

}

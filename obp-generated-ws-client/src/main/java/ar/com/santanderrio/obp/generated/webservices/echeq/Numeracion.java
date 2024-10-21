
package ar.com.santanderrio.obp.generated.webservices.echeq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Numeracion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Numeracion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cheque_numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cmc7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Numeracion", propOrder = {
    "chequeNumero",
    "cmc7"
})
public class Numeracion {

    @XmlElement(name = "cheque_numero")
    protected String chequeNumero;
    protected String cmc7;

    /**
     * Gets the value of the chequeNumero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeNumero() {
        return chequeNumero;
    }

    /**
     * Sets the value of the chequeNumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeNumero(String value) {
        this.chequeNumero = value;
    }

    /**
     * Gets the value of the cmc7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmc7() {
        return cmc7;
    }

    /**
     * Sets the value of the cmc7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmc7(String value) {
        this.cmc7 = value;
    }

}

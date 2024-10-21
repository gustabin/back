
package ar.com.santanderrio.obp.generated.webservices.echeq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Deposito complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Deposito">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intcheque_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beneficiario_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beneficiario_documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beneficiario_cbu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Deposito", propOrder = {
    "intchequeId",
    "beneficiarioDocumentoTipo",
    "beneficiarioDocumento",
    "beneficiarioCbu"
})
public class Deposito {

    @XmlElement(name = "intcheque_id")
    protected String intchequeId;
    @XmlElement(name = "beneficiario_documento_tipo")
    protected String beneficiarioDocumentoTipo;
    @XmlElement(name = "beneficiario_documento")
    protected String beneficiarioDocumento;
    @XmlElement(name = "beneficiario_cbu")
    protected String beneficiarioCbu;

    /**
     * Gets the value of the intchequeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchequeId() {
        return intchequeId;
    }

    /**
     * Sets the value of the intchequeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchequeId(String value) {
        this.intchequeId = value;
    }

    /**
     * Gets the value of the beneficiarioDocumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiarioDocumentoTipo() {
        return beneficiarioDocumentoTipo;
    }

    /**
     * Sets the value of the beneficiarioDocumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiarioDocumentoTipo(String value) {
        this.beneficiarioDocumentoTipo = value;
    }

    /**
     * Gets the value of the beneficiarioDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiarioDocumento() {
        return beneficiarioDocumento;
    }

    /**
     * Sets the value of the beneficiarioDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiarioDocumento(String value) {
        this.beneficiarioDocumento = value;
    }

    /**
     * Gets the value of the beneficiarioCbu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiarioCbu() {
        return beneficiarioCbu;
    }

    /**
     * Sets the value of the beneficiarioCbu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiarioCbu(String value) {
        this.beneficiarioCbu = value;
    }

}

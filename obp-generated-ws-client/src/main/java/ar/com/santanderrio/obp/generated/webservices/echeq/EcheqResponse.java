
package ar.com.santanderrio.obp.generated.webservices.echeq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EcheqResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EcheqResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://echeq.amco.com.ar/}bae_CCE_Stat" minOccurs="0"/>
 *         &lt;element name="cheque" type="{http://echeq.amco.com.ar/}Cheque"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EcheqResponse", propOrder = {
    "code",
    "message",
    "status",
    "cheque"
})
public class EcheqResponse {

    protected String code;
    protected String message;
    protected BaeCCEStat status;
    @XmlElement(required = true)
    protected Cheque cheque;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link BaeCCEStat }
     *     
     */
    public BaeCCEStat getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaeCCEStat }
     *     
     */
    public void setStatus(BaeCCEStat value) {
        this.status = value;
    }

    /**
     * Gets the value of the cheque property.
     * 
     * @return
     *     possible object is
     *     {@link Cheque }
     *     
     */
    public Cheque getCheque() {
        return cheque;
    }

    /**
     * Sets the value of the cheque property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cheque }
     *     
     */
    public void setCheque(Cheque value) {
        this.cheque = value;
    }

}

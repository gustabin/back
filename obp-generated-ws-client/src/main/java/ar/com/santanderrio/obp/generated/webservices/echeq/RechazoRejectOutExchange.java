
package ar.com.santanderrio.obp.generated.webservices.echeq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RechazoRejectOutExchange complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RechazoRejectOutExchange">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intcheque_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_rechazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivo_rechazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RechazoRejectOutExchange", propOrder = {
    "intchequeId",
    "codigoRechazo",
    "motivoRechazo"
})
public class RechazoRejectOutExchange {

    @XmlElement(name = "intcheque_id")
    protected String intchequeId;
    @XmlElement(name = "codigo_rechazo")
    protected String codigoRechazo;
    @XmlElement(name = "motivo_rechazo")
    protected String motivoRechazo;

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
     * Gets the value of the codigoRechazo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoRechazo() {
        return codigoRechazo;
    }

    /**
     * Sets the value of the codigoRechazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoRechazo(String value) {
        this.codigoRechazo = value;
    }

    /**
     * Gets the value of the motivoRechazo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    /**
     * Sets the value of the motivoRechazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoRechazo(String value) {
        this.motivoRechazo = value;
    }

}

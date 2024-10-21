
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TiposConcepto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TiposConcepto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Cod_Tipo_Transferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Transferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TiposConcepto", propOrder = {
    "codTipoTransferencia",
    "tipoTransferencia"
})
public class TiposConcepto {

    @XmlElementRef(name = "Cod_Tipo_Transferencia", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> codTipoTransferencia;
    @XmlElementRef(name = "Tipo_Transferencia", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> tipoTransferencia;

    /**
     * Gets the value of the codTipoTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodTipoTransferencia() {
        return codTipoTransferencia;
    }

    /**
     * Sets the value of the codTipoTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodTipoTransferencia(JAXBElement<String> value) {
        this.codTipoTransferencia = value;
    }

    /**
     * Gets the value of the tipoTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoTransferencia() {
        return tipoTransferencia;
    }

    /**
     * Sets the value of the tipoTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoTransferencia(JAXBElement<String> value) {
        this.tipoTransferencia = value;
    }

}

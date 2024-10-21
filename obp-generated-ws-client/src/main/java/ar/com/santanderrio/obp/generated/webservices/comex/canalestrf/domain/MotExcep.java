
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MotExcep complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MotExcep">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Codigo_Excepcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Motivo_Excepcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MotExcep", propOrder = {
    "codigoExcepcion",
    "motivoExcepcion"
})
public class MotExcep {

    @XmlElementRef(name = "Codigo_Excepcion", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> codigoExcepcion;
    @XmlElementRef(name = "Motivo_Excepcion", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> motivoExcepcion;

    /**
     * Gets the value of the codigoExcepcion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoExcepcion() {
        return codigoExcepcion;
    }

    /**
     * Sets the value of the codigoExcepcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoExcepcion(JAXBElement<String> value) {
        this.codigoExcepcion = value;
    }

    /**
     * Gets the value of the motivoExcepcion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMotivoExcepcion() {
        return motivoExcepcion;
    }

    /**
     * Sets the value of the motivoExcepcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMotivoExcepcion(JAXBElement<String> value) {
        this.motivoExcepcion = value;
    }

}

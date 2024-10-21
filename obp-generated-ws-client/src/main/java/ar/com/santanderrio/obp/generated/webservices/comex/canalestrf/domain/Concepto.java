
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Concepto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Concepto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Texto_Declaracion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Declaracion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Concepto", propOrder = {
    "textoDeclaracion",
    "tipoDeclaracion"
})
public class Concepto {

    @XmlElementRef(name = "Texto_Declaracion", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> textoDeclaracion;
    @XmlElementRef(name = "Tipo_Declaracion", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> tipoDeclaracion;

    /**
     * Gets the value of the textoDeclaracion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTextoDeclaracion() {
        return textoDeclaracion;
    }

    /**
     * Sets the value of the textoDeclaracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTextoDeclaracion(JAXBElement<String> value) {
        this.textoDeclaracion = value;
    }

    /**
     * Gets the value of the tipoDeclaracion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoDeclaracion() {
        return tipoDeclaracion;
    }

    /**
     * Sets the value of the tipoDeclaracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoDeclaracion(JAXBElement<String> value) {
        this.tipoDeclaracion = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CondicionVenta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CondicionVenta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codnum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codvalor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CondicionVenta", propOrder = {
    "codnum",
    "codvalor"
})
public class CondicionVenta {

    @XmlElementRef(name = "codnum", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> codnum;
    @XmlElementRef(name = "codvalor", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> codvalor;

    /**
     * Obtiene el valor de la propiedad codnum.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodnum() {
        return codnum;
    }

    /**
     * Define el valor de la propiedad codnum.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodnum(JAXBElement<String> value) {
        this.codnum = value;
    }

    /**
     * Obtiene el valor de la propiedad codvalor.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodvalor() {
        return codvalor;
    }

    /**
     * Define el valor de la propiedad codvalor.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodvalor(JAXBElement<String> value) {
        this.codvalor = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Moneda complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Moneda">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Cod_BCRA_Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Codigo_Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Descripcion_Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Moneda", propOrder = {
    "codBCRAMoneda",
    "codigoMoneda",
    "descripcionMoneda"
})
public class Moneda {

    @XmlElementRef(name = "Cod_BCRA_Moneda", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> codBCRAMoneda;
    @XmlElementRef(name = "Codigo_Moneda", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> codigoMoneda;
    @XmlElementRef(name = "Descripcion_Moneda", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> descripcionMoneda;

    /**
     * Obtiene el valor de la propiedad codBCRAMoneda.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodBCRAMoneda() {
        return codBCRAMoneda;
    }

    /**
     * Define el valor de la propiedad codBCRAMoneda.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodBCRAMoneda(JAXBElement<String> value) {
        this.codBCRAMoneda = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoMoneda.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoMoneda() {
        return codigoMoneda;
    }

    /**
     * Define el valor de la propiedad codigoMoneda.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoMoneda(JAXBElement<String> value) {
        this.codigoMoneda = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionMoneda.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionMoneda() {
        return descripcionMoneda;
    }

    /**
     * Define el valor de la propiedad descripcionMoneda.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionMoneda(JAXBElement<String> value) {
        this.descripcionMoneda = value;
    }

}

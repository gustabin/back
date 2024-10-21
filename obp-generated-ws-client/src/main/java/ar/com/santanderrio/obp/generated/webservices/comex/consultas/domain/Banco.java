
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Banco complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Banco">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Aba_Banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Domicilio_Banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Localidad_Banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nombre_Banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pais_Banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Swift_Banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Banco", propOrder = {
    "abaBanco",
    "domicilioBanco",
    "localidadBanco",
    "nombreBanco",
    "paisBanco",
    "swiftBanco"
})
public class Banco {

    @XmlElementRef(name = "Aba_Banco", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> abaBanco;
    @XmlElementRef(name = "Domicilio_Banco", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> domicilioBanco;
    @XmlElementRef(name = "Localidad_Banco", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> localidadBanco;
    @XmlElementRef(name = "Nombre_Banco", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> nombreBanco;
    @XmlElementRef(name = "Pais_Banco", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> paisBanco;
    @XmlElementRef(name = "Swift_Banco", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> swiftBanco;

    /**
     * Obtiene el valor de la propiedad abaBanco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAbaBanco() {
        return abaBanco;
    }

    /**
     * Define el valor de la propiedad abaBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAbaBanco(JAXBElement<String> value) {
        this.abaBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad domicilioBanco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomicilioBanco() {
        return domicilioBanco;
    }

    /**
     * Define el valor de la propiedad domicilioBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomicilioBanco(JAXBElement<String> value) {
        this.domicilioBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad localidadBanco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocalidadBanco() {
        return localidadBanco;
    }

    /**
     * Define el valor de la propiedad localidadBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocalidadBanco(JAXBElement<String> value) {
        this.localidadBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreBanco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreBanco() {
        return nombreBanco;
    }

    /**
     * Define el valor de la propiedad nombreBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreBanco(JAXBElement<String> value) {
        this.nombreBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad paisBanco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisBanco() {
        return paisBanco;
    }

    /**
     * Define el valor de la propiedad paisBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisBanco(JAXBElement<String> value) {
        this.paisBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad swiftBanco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSwiftBanco() {
        return swiftBanco;
    }

    /**
     * Define el valor de la propiedad swiftBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSwiftBanco(JAXBElement<String> value) {
        this.swiftBanco = value;
    }

}

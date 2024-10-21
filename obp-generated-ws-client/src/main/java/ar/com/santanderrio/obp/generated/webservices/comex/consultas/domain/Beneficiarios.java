
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.base.Entity;


/**
 * <p>Clase Java para Beneficiarios complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Beneficiarios">
 *   &lt;complexContent>
 *     &lt;extension base="{Domain/Base}Entity">
 *       &lt;sequence>
 *         &lt;element name="Domicilio_Benef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_nif" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Localidad_Benef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nombre_Benef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pais_Benef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Beneficiarios", propOrder = {
    "domicilioBenef",
    "idNif",
    "localidadBenef",
    "nif",
    "nombreBenef",
    "paisBenef"
})
public class Beneficiarios
    extends Entity
{

    @XmlElementRef(name = "Domicilio_Benef", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> domicilioBenef;
    @XmlElementRef(name = "Id_nif", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> idNif;
    @XmlElementRef(name = "Localidad_Benef", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> localidadBenef;
    @XmlElementRef(name = "Nif", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> nif;
    @XmlElementRef(name = "Nombre_Benef", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> nombreBenef;
    @XmlElementRef(name = "Pais_Benef", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> paisBenef;

    /**
     * Obtiene el valor de la propiedad domicilioBenef.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomicilioBenef() {
        return domicilioBenef;
    }

    /**
     * Define el valor de la propiedad domicilioBenef.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomicilioBenef(JAXBElement<String> value) {
        this.domicilioBenef = value;
    }

    /**
     * Obtiene el valor de la propiedad idNif.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getIdNif() {
        return idNif;
    }

    /**
     * Define el valor de la propiedad idNif.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setIdNif(JAXBElement<BigDecimal> value) {
        this.idNif = value;
    }

    /**
     * Obtiene el valor de la propiedad localidadBenef.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocalidadBenef() {
        return localidadBenef;
    }

    /**
     * Define el valor de la propiedad localidadBenef.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocalidadBenef(JAXBElement<String> value) {
        this.localidadBenef = value;
    }

    /**
     * Obtiene el valor de la propiedad nif.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNif() {
        return nif;
    }

    /**
     * Define el valor de la propiedad nif.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNif(JAXBElement<String> value) {
        this.nif = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreBenef.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreBenef() {
        return nombreBenef;
    }

    /**
     * Define el valor de la propiedad nombreBenef.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreBenef(JAXBElement<String> value) {
        this.nombreBenef = value;
    }

    /**
     * Obtiene el valor de la propiedad paisBenef.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisBenef() {
        return paisBenef;
    }

    /**
     * Define el valor de la propiedad paisBenef.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisBenef(JAXBElement<String> value) {
        this.paisBenef = value;
    }

}

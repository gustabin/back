
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.base.Entity;


/**
 * <p>Clase Java para Agenda complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Agenda">
 *   &lt;complexContent>
 *     &lt;extension base="{Domain/Base}Entity">
 *       &lt;sequence>
 *         &lt;element name="Codigo_Agenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Domicilio_Agenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Es_Aba" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Agenda" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Localidad_Agenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nif" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Nombre_Agenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pais_Agenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Agenda", propOrder = {
    "codigoAgenda",
    "domicilioAgenda",
    "esAba",
    "idAgenda",
    "localidadAgenda",
    "nif",
    "nombreAgenda",
    "paisAgenda"
})
public class Agenda
    extends Entity
{

    @XmlElementRef(name = "Codigo_Agenda", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> codigoAgenda;
    @XmlElementRef(name = "Domicilio_Agenda", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> domicilioAgenda;
    @XmlElementRef(name = "Es_Aba", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> esAba;
    @XmlElement(name = "Id_Agenda")
    protected Long idAgenda;
    @XmlElementRef(name = "Localidad_Agenda", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> localidadAgenda;
    @XmlElementRef(name = "Nif", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> nif;
    @XmlElementRef(name = "Nombre_Agenda", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> nombreAgenda;
    @XmlElementRef(name = "Pais_Agenda", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> paisAgenda;

    /**
     * Obtiene el valor de la propiedad codigoAgenda.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoAgenda() {
        return codigoAgenda;
    }

    /**
     * Define el valor de la propiedad codigoAgenda.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoAgenda(JAXBElement<String> value) {
        this.codigoAgenda = value;
    }

    /**
     * Obtiene el valor de la propiedad domicilioAgenda.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomicilioAgenda() {
        return domicilioAgenda;
    }

    /**
     * Define el valor de la propiedad domicilioAgenda.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomicilioAgenda(JAXBElement<String> value) {
        this.domicilioAgenda = value;
    }

    /**
     * Obtiene el valor de la propiedad esAba.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEsAba() {
        return esAba;
    }

    /**
     * Define el valor de la propiedad esAba.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEsAba(JAXBElement<String> value) {
        this.esAba = value;
    }

    /**
     * Obtiene el valor de la propiedad idAgenda.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAgenda() {
        return idAgenda;
    }

    /**
     * Define el valor de la propiedad idAgenda.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAgenda(Long value) {
        this.idAgenda = value;
    }

    /**
     * Obtiene el valor de la propiedad localidadAgenda.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocalidadAgenda() {
        return localidadAgenda;
    }

    /**
     * Define el valor de la propiedad localidadAgenda.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocalidadAgenda(JAXBElement<String> value) {
        this.localidadAgenda = value;
    }

    /**
     * Obtiene el valor de la propiedad nif.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getNif() {
        return nif;
    }

    /**
     * Define el valor de la propiedad nif.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setNif(JAXBElement<BigDecimal> value) {
        this.nif = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreAgenda.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreAgenda() {
        return nombreAgenda;
    }

    /**
     * Define el valor de la propiedad nombreAgenda.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreAgenda(JAXBElement<String> value) {
        this.nombreAgenda = value;
    }

    /**
     * Obtiene el valor de la propiedad paisAgenda.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisAgenda() {
        return paisAgenda;
    }

    /**
     * Define el valor de la propiedad paisAgenda.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisAgenda(JAXBElement<String> value) {
        this.paisAgenda = value;
    }

}

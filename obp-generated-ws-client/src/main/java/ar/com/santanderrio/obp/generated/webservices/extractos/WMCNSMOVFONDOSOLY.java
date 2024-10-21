
package ar.com.santanderrio.obp.generated.webservices.extractos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CTA_TITULOS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="COD_FONDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECHA_DESDE" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="FECHA_HASTA" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="canal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subcanal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ctatitulos",
    "codfondo",
    "fechadesde",
    "fechahasta",
    "canal",
    "subcanal",
    "firma"
})
@XmlRootElement(name = "WM_CNS_MOV_FONDOS_OLY")
public class WMCNSMOVFONDOSOLY {

    @XmlElementRef(name = "CTA_TITULOS", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> ctatitulos;
    @XmlElementRef(name = "COD_FONDO", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> codfondo;
    @XmlElement(name = "FECHA_DESDE")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechadesde;
    @XmlElement(name = "FECHA_HASTA")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechahasta;
    @XmlElementRef(name = "canal", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> canal;
    @XmlElementRef(name = "subcanal", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> subcanal;
    @XmlElementRef(name = "firma", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> firma;

    /**
     * Gets the value of the ctatitulos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCTATITULOS() {
        return ctatitulos;
    }

    /**
     * Sets the value of the ctatitulos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCTATITULOS(JAXBElement<String> value) {
        this.ctatitulos = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the codfondo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCODFONDO() {
        return codfondo;
    }

    /**
     * Sets the value of the codfondo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCODFONDO(JAXBElement<String> value) {
        this.codfondo = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the fechadesde property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFECHADESDE() {
        return fechadesde;
    }

    /**
     * Sets the value of the fechadesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFECHADESDE(XMLGregorianCalendar value) {
        this.fechadesde = value;
    }

    /**
     * Gets the value of the fechahasta property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFECHAHASTA() {
        return fechahasta;
    }

    /**
     * Sets the value of the fechahasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFECHAHASTA(XMLGregorianCalendar value) {
        this.fechahasta = value;
    }

    /**
     * Gets the value of the canal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCanal() {
        return canal;
    }

    /**
     * Sets the value of the canal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCanal(JAXBElement<String> value) {
        this.canal = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the subcanal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubcanal() {
        return subcanal;
    }

    /**
     * Sets the value of the subcanal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubcanal(JAXBElement<String> value) {
        this.subcanal = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the firma property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirma() {
        return firma;
    }

    /**
     * Sets the value of the firma property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirma(JAXBElement<String> value) {
        this.firma = ((JAXBElement<String> ) value);
    }

}

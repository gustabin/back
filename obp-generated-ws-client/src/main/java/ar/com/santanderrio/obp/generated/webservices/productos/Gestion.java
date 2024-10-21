
package ar.com.santanderrio.obp.generated.webservices.productos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Gestion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Gestion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codBandejaActual" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descConcepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descProducto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descSubConcepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descSubProducto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fecBandejaActual" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fecGestionAlta" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ideGestionNro" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ideGestionSector" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Gestion", namespace = "http://webService.core.ges.rio.com", propOrder = {
    "codBandejaActual",
    "codUser",
    "descConcepto",
    "descProducto",
    "descSubConcepto",
    "descSubProducto",
    "fecBandejaActual",
    "fecGestionAlta",
    "ideGestionNro",
    "ideGestionSector"
})
public class Gestion {

    @XmlElement(required = true, nillable = true)
    protected String codBandejaActual;
    @XmlElement(required = true, nillable = true)
    protected String codUser;
    @XmlElement(required = true, nillable = true)
    protected String descConcepto;
    @XmlElement(required = true, nillable = true)
    protected String descProducto;
    @XmlElement(required = true, nillable = true)
    protected String descSubConcepto;
    @XmlElement(required = true, nillable = true)
    protected String descSubProducto;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecBandejaActual;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecGestionAlta;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer ideGestionNro;
    @XmlElement(required = true, nillable = true)
    protected String ideGestionSector;

    /**
     * Gets the value of the codBandejaActual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodBandejaActual() {
        return codBandejaActual;
    }

    /**
     * Sets the value of the codBandejaActual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodBandejaActual(String value) {
        this.codBandejaActual = value;
    }

    /**
     * Gets the value of the codUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodUser() {
        return codUser;
    }

    /**
     * Sets the value of the codUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodUser(String value) {
        this.codUser = value;
    }

    /**
     * Gets the value of the descConcepto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescConcepto() {
        return descConcepto;
    }

    /**
     * Sets the value of the descConcepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescConcepto(String value) {
        this.descConcepto = value;
    }

    /**
     * Gets the value of the descProducto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescProducto() {
        return descProducto;
    }

    /**
     * Sets the value of the descProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescProducto(String value) {
        this.descProducto = value;
    }

    /**
     * Gets the value of the descSubConcepto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescSubConcepto() {
        return descSubConcepto;
    }

    /**
     * Sets the value of the descSubConcepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescSubConcepto(String value) {
        this.descSubConcepto = value;
    }

    /**
     * Gets the value of the descSubProducto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescSubProducto() {
        return descSubProducto;
    }

    /**
     * Sets the value of the descSubProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescSubProducto(String value) {
        this.descSubProducto = value;
    }

    /**
     * Gets the value of the fecBandejaActual property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecBandejaActual() {
        return fecBandejaActual;
    }

    /**
     * Sets the value of the fecBandejaActual property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecBandejaActual(XMLGregorianCalendar value) {
        this.fecBandejaActual = value;
    }

    /**
     * Gets the value of the fecGestionAlta property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecGestionAlta() {
        return fecGestionAlta;
    }

    /**
     * Sets the value of the fecGestionAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecGestionAlta(XMLGregorianCalendar value) {
        this.fecGestionAlta = value;
    }

    /**
     * Gets the value of the ideGestionNro property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdeGestionNro() {
        return ideGestionNro;
    }

    /**
     * Sets the value of the ideGestionNro property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdeGestionNro(Integer value) {
        this.ideGestionNro = value;
    }

    /**
     * Gets the value of the ideGestionSector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdeGestionSector() {
        return ideGestionSector;
    }

    /**
     * Sets the value of the ideGestionSector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdeGestionSector(String value) {
        this.ideGestionSector = value;
    }

}

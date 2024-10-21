
package ar.com.santanderrio.obp.generated.webservices.productos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EstadoGestionWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EstadoGestionWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codRetorno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="descRetorno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estadoGestion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fecSectorActualGestion" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="setorActualGestion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstadoGestionWS", namespace = "http://webService.core.ges.rio.com", propOrder = {
    "codRetorno",
    "descRetorno",
    "estadoGestion",
    "fecSectorActualGestion",
    "setorActualGestion"
})
public class EstadoGestionWS {

    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer codRetorno;
    @XmlElement(required = true, nillable = true)
    protected String descRetorno;
    @XmlElement(required = true, nillable = true)
    protected String estadoGestion;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecSectorActualGestion;
    @XmlElement(required = true, nillable = true)
    protected String setorActualGestion;

    /**
     * Gets the value of the codRetorno property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodRetorno() {
        return codRetorno;
    }

    /**
     * Sets the value of the codRetorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodRetorno(Integer value) {
        this.codRetorno = value;
    }

    /**
     * Gets the value of the descRetorno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescRetorno() {
        return descRetorno;
    }

    /**
     * Sets the value of the descRetorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescRetorno(String value) {
        this.descRetorno = value;
    }

    /**
     * Gets the value of the estadoGestion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoGestion() {
        return estadoGestion;
    }

    /**
     * Sets the value of the estadoGestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoGestion(String value) {
        this.estadoGestion = value;
    }

    /**
     * Gets the value of the fecSectorActualGestion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecSectorActualGestion() {
        return fecSectorActualGestion;
    }

    /**
     * Sets the value of the fecSectorActualGestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecSectorActualGestion(XMLGregorianCalendar value) {
        this.fecSectorActualGestion = value;
    }

    /**
     * Gets the value of the setorActualGestion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetorActualGestion() {
        return setorActualGestion;
    }

    /**
     * Sets the value of the setorActualGestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetorActualGestion(String value) {
        this.setorActualGestion = value;
    }

}

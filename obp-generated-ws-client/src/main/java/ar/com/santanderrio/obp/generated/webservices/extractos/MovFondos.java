
package ar.com.santanderrio.obp.generated.webservices.extractos;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for MovFondos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MovFondos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COD_FONDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CONCEPTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="COTIZACION" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CTA_TIT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CUOTASPARTES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECHA_LIQ" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="FECHA_SOL" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="IMPORTE" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="NR_LIQUIDACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NR_SOLICITUD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MovFondos", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", propOrder = {
    "codfondo",
    "concepto",
    "cotizacion",
    "ctatit",
    "cuotaspartes",
    "fechaliq",
    "fechasol",
    "importe",
    "nrliquidacion",
    "nrsolicitud"
})
public class MovFondos {

    @XmlElementRef(name = "COD_FONDO", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", type = JAXBElement.class)
    protected JAXBElement<String> codfondo;
    @XmlElementRef(name = "CONCEPTO", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", type = JAXBElement.class)
    protected JAXBElement<String> concepto;
    @XmlElement(name = "COTIZACION")
    protected BigDecimal cotizacion;
    @XmlElementRef(name = "CTA_TIT", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", type = JAXBElement.class)
    protected JAXBElement<String> ctatit;
    @XmlElementRef(name = "CUOTASPARTES", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", type = JAXBElement.class)
    protected JAXBElement<String> cuotaspartes;
    @XmlElement(name = "FECHA_LIQ")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaliq;
    @XmlElement(name = "FECHA_SOL")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechasol;
    @XmlElement(name = "IMPORTE")
    protected BigDecimal importe;
    @XmlElementRef(name = "NR_LIQUIDACION", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", type = JAXBElement.class)
    protected JAXBElement<String> nrliquidacion;
    @XmlElementRef(name = "NR_SOLICITUD", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", type = JAXBElement.class)
    protected JAXBElement<String> nrsolicitud;

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
     * Gets the value of the concepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCONCEPTO() {
        return concepto;
    }

    /**
     * Sets the value of the concepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCONCEPTO(JAXBElement<String> value) {
        this.concepto = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the cotizacion property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCOTIZACION() {
        return cotizacion;
    }

    /**
     * Sets the value of the cotizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCOTIZACION(BigDecimal value) {
        this.cotizacion = value;
    }

    /**
     * Gets the value of the ctatit property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCTATIT() {
        return ctatit;
    }

    /**
     * Sets the value of the ctatit property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCTATIT(JAXBElement<String> value) {
        this.ctatit = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the cuotaspartes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCUOTASPARTES() {
        return cuotaspartes;
    }

    /**
     * Sets the value of the cuotaspartes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCUOTASPARTES(JAXBElement<String> value) {
        this.cuotaspartes = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the fechaliq property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFECHALIQ() {
        return fechaliq;
    }

    /**
     * Sets the value of the fechaliq property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFECHALIQ(XMLGregorianCalendar value) {
        this.fechaliq = value;
    }

    /**
     * Gets the value of the fechasol property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFECHASOL() {
        return fechasol;
    }

    /**
     * Sets the value of the fechasol property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFECHASOL(XMLGregorianCalendar value) {
        this.fechasol = value;
    }

    /**
     * Gets the value of the importe property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIMPORTE() {
        return importe;
    }

    /**
     * Sets the value of the importe property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIMPORTE(BigDecimal value) {
        this.importe = value;
    }

    /**
     * Gets the value of the nrliquidacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNRLIQUIDACION() {
        return nrliquidacion;
    }

    /**
     * Sets the value of the nrliquidacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNRLIQUIDACION(JAXBElement<String> value) {
        this.nrliquidacion = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the nrsolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNRSOLICITUD() {
        return nrsolicitud;
    }

    /**
     * Sets the value of the nrsolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNRSOLICITUD(JAXBElement<String> value) {
        this.nrsolicitud = ((JAXBElement<String> ) value);
    }

}


package ar.com.santanderrio.obp.generated.webservices.mercado.canal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ConsultarOperacionesMultimercadoParameter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultarOperacionesMultimercadoParameter">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.Mercados.ServiceContracts.Parameters}BaseParameter">
 *       &lt;sequence>
 *         &lt;element name="Cno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaTitulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaDesde" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="FechaHasta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarOperacionesMultimercadoParameter", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.Mercados.ServiceContracts", propOrder = {
    "cno",
    "cuentaTitulo",
    "fechaDesde",
    "fechaHasta"
})
public class ConsultarOperacionesMultimercadoParameter
    extends BaseParameter
{

    @XmlElementRef(name = "Cno", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.Mercados.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> cno;
    @XmlElementRef(name = "CuentaTitulo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.Mercados.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> cuentaTitulo;
    @XmlElement(name = "FechaDesde")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaDesde;
    @XmlElement(name = "FechaHasta")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaHasta;

    /**
     * Gets the value of the cno property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCno() {
        return cno;
    }

    /**
     * Sets the value of the cno property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCno(JAXBElement<String> value) {
        this.cno = value;
    }

    /**
     * Gets the value of the cuentaTitulo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaTitulo() {
        return cuentaTitulo;
    }

    /**
     * Sets the value of the cuentaTitulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaTitulo(JAXBElement<String> value) {
        this.cuentaTitulo = value;
    }

    /**
     * Gets the value of the fechaDesde property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaDesde() {
        return fechaDesde;
    }

    /**
     * Sets the value of the fechaDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaDesde(XMLGregorianCalendar value) {
        this.fechaDesde = value;
    }

    /**
     * Gets the value of the fechaHasta property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Sets the value of the fechaHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaHasta(XMLGregorianCalendar value) {
        this.fechaHasta = value;
    }

}

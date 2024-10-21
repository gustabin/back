
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx4006PromesaPagoMoraRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx4006PromesaPagoMoraRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="FechaPromesa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LecturaMora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NupAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Transfirio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx4006PromesaPagoMoraRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4006", propOrder = {
    "fechaPromesa",
    "importe",
    "lecturaMora",
    "nupAltair",
    "sesion",
    "transfirio"
})
public class Trx4006PromesaPagoMoraRequest
    extends RequestBase
{

    @XmlElementRef(name = "FechaPromesa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4006", type = JAXBElement.class)
    protected JAXBElement<String> fechaPromesa;
    @XmlElementRef(name = "Importe", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4006", type = JAXBElement.class)
    protected JAXBElement<String> importe;
    @XmlElementRef(name = "LecturaMora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4006", type = JAXBElement.class)
    protected JAXBElement<String> lecturaMora;
    @XmlElementRef(name = "NupAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4006", type = JAXBElement.class)
    protected JAXBElement<String> nupAltair;
    @XmlElementRef(name = "Sesion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4006", type = JAXBElement.class)
    protected JAXBElement<String> sesion;
    @XmlElementRef(name = "Transfirio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4006", type = JAXBElement.class)
    protected JAXBElement<String> transfirio;

    /**
     * Gets the value of the fechaPromesa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaPromesa() {
        return fechaPromesa;
    }

    /**
     * Sets the value of the fechaPromesa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaPromesa(JAXBElement<String> value) {
        this.fechaPromesa = value;
    }

    /**
     * Gets the value of the importe property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporte() {
        return importe;
    }

    /**
     * Sets the value of the importe property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporte(JAXBElement<String> value) {
        this.importe = value;
    }

    /**
     * Gets the value of the lecturaMora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLecturaMora() {
        return lecturaMora;
    }

    /**
     * Sets the value of the lecturaMora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLecturaMora(JAXBElement<String> value) {
        this.lecturaMora = value;
    }

    /**
     * Gets the value of the nupAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNupAltair() {
        return nupAltair;
    }

    /**
     * Sets the value of the nupAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNupAltair(JAXBElement<String> value) {
        this.nupAltair = value;
    }

    /**
     * Gets the value of the sesion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSesion() {
        return sesion;
    }

    /**
     * Sets the value of the sesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSesion(JAXBElement<String> value) {
        this.sesion = value;
    }

    /**
     * Gets the value of the transfirio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTransfirio() {
        return transfirio;
    }

    /**
     * Sets the value of the transfirio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTransfirio(JAXBElement<String> value) {
        this.transfirio = value;
    }

}

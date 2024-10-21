
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx4002PreprocesoMSGResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx4002PreprocesoMSGResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="ExCiti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HizoPromesa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MensajeRespuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MontoMoraTemprana" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="MoraTardia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MoraTemprana" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx4002PreprocesoMSGResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4002PreprocesoMSG", propOrder = {
    "exCiti",
    "hizoPromesa",
    "mensajeRespuesta",
    "montoMoraTemprana",
    "moraTardia",
    "moraTemprana",
    "nup",
    "sessionID"
})
public class Trx4002PreprocesoMSGResponse
    extends ResponseBase
{

    @XmlElementRef(name = "ExCiti", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4002PreprocesoMSG", type = JAXBElement.class)
    protected JAXBElement<String> exCiti;
    @XmlElementRef(name = "HizoPromesa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4002PreprocesoMSG", type = JAXBElement.class)
    protected JAXBElement<String> hizoPromesa;
    @XmlElementRef(name = "MensajeRespuesta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4002PreprocesoMSG", type = JAXBElement.class)
    protected JAXBElement<String> mensajeRespuesta;
    @XmlElement(name = "MontoMoraTemprana")
    protected Double montoMoraTemprana;
    @XmlElementRef(name = "MoraTardia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4002PreprocesoMSG", type = JAXBElement.class)
    protected JAXBElement<String> moraTardia;
    @XmlElementRef(name = "MoraTemprana", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4002PreprocesoMSG", type = JAXBElement.class)
    protected JAXBElement<String> moraTemprana;
    @XmlElementRef(name = "Nup", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4002PreprocesoMSG", type = JAXBElement.class)
    protected JAXBElement<String> nup;
    @XmlElement(name = "SessionID")
    protected Integer sessionID;

    /**
     * Gets the value of the exCiti property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExCiti() {
        return exCiti;
    }

    /**
     * Sets the value of the exCiti property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExCiti(JAXBElement<String> value) {
        this.exCiti = value;
    }

    /**
     * Gets the value of the hizoPromesa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHizoPromesa() {
        return hizoPromesa;
    }

    /**
     * Sets the value of the hizoPromesa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHizoPromesa(JAXBElement<String> value) {
        this.hizoPromesa = value;
    }

    /**
     * Gets the value of the mensajeRespuesta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    /**
     * Sets the value of the mensajeRespuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMensajeRespuesta(JAXBElement<String> value) {
        this.mensajeRespuesta = value;
    }

    /**
     * Gets the value of the montoMoraTemprana property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMontoMoraTemprana() {
        return montoMoraTemprana;
    }

    /**
     * Sets the value of the montoMoraTemprana property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMontoMoraTemprana(Double value) {
        this.montoMoraTemprana = value;
    }

    /**
     * Gets the value of the moraTardia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMoraTardia() {
        return moraTardia;
    }

    /**
     * Sets the value of the moraTardia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMoraTardia(JAXBElement<String> value) {
        this.moraTardia = value;
    }

    /**
     * Gets the value of the moraTemprana property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMoraTemprana() {
        return moraTemprana;
    }

    /**
     * Sets the value of the moraTemprana property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMoraTemprana(JAXBElement<String> value) {
        this.moraTemprana = value;
    }

    /**
     * Gets the value of the nup property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNup() {
        return nup;
    }

    /**
     * Sets the value of the nup property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNup(JAXBElement<String> value) {
        this.nup = value;
    }

    /**
     * Gets the value of the sessionID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSessionID() {
        return sessionID;
    }

    /**
     * Sets the value of the sessionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSessionID(Integer value) {
        this.sessionID = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.banelco;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para WSTerminalData complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="WSTerminalData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datosTerminal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="futureUse1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="futureUse2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idSession" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSTerminalData", namespace = "http://model.webservices.banelco.com", propOrder = {
    "datosTerminal",
    "futureUse1",
    "futureUse2",
    "idSession",
    "ipOrigen"
})
public class WSTerminalData {

    @XmlElementRef(name = "datosTerminal", namespace = "http://model.webservices.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> datosTerminal;
    @XmlElementRef(name = "futureUse1", namespace = "http://model.webservices.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> futureUse1;
    @XmlElementRef(name = "futureUse2", namespace = "http://model.webservices.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> futureUse2;
    @XmlElementRef(name = "idSession", namespace = "http://model.webservices.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> idSession;
    @XmlElementRef(name = "ipOrigen", namespace = "http://model.webservices.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> ipOrigen;

    /**
     * Obtiene el valor de la propiedad datosTerminal.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDatosTerminal() {
        return datosTerminal;
    }

    /**
     * Define el valor de la propiedad datosTerminal.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDatosTerminal(JAXBElement<String> value) {
        this.datosTerminal = value;
    }

    /**
     * Obtiene el valor de la propiedad futureUse1.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFutureUse1() {
        return futureUse1;
    }

    /**
     * Define el valor de la propiedad futureUse1.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFutureUse1(JAXBElement<String> value) {
        this.futureUse1 = value;
    }

    /**
     * Obtiene el valor de la propiedad futureUse2.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFutureUse2() {
        return futureUse2;
    }

    /**
     * Define el valor de la propiedad futureUse2.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFutureUse2(JAXBElement<String> value) {
        this.futureUse2 = value;
    }

    /**
     * Obtiene el valor de la propiedad idSession.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdSession() {
        return idSession;
    }

    /**
     * Define el valor de la propiedad idSession.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdSession(JAXBElement<String> value) {
        this.idSession = value;
    }

    /**
     * Obtiene el valor de la propiedad ipOrigen.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIpOrigen() {
        return ipOrigen;
    }

    /**
     * Define el valor de la propiedad ipOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIpOrigen(JAXBElement<String> value) {
        this.ipOrigen = value;
    }

}

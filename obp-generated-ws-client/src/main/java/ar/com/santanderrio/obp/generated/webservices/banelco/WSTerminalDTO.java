
package ar.com.santanderrio.obp.generated.webservices.banelco;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para WSTerminalDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="WSTerminalDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="canal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datosTerminal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direccionIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSTerminalDTO", namespace = "http://model.webservices.banelco.com", propOrder = {
    "canal",
    "datosTerminal",
    "direccionIP",
    "terminal"
})
public class WSTerminalDTO {

    @XmlElementRef(name = "canal", namespace = "http://model.webservices.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> canal;
    @XmlElementRef(name = "datosTerminal", namespace = "http://model.webservices.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> datosTerminal;
    @XmlElementRef(name = "direccionIP", namespace = "http://model.webservices.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> direccionIP;
    @XmlElementRef(name = "terminal", namespace = "http://model.webservices.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> terminal;

    /**
     * Obtiene el valor de la propiedad canal.
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
     * Define el valor de la propiedad canal.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCanal(JAXBElement<String> value) {
        this.canal = value;
    }

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
     * Obtiene el valor de la propiedad direccionIP.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDireccionIP() {
        return direccionIP;
    }

    /**
     * Define el valor de la propiedad direccionIP.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDireccionIP(JAXBElement<String> value) {
        this.direccionIP = value;
    }

    /**
     * Obtiene el valor de la propiedad terminal.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTerminal() {
        return terminal;
    }

    /**
     * Define el valor de la propiedad terminal.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTerminal(JAXBElement<String> value) {
        this.terminal = value;
    }

}

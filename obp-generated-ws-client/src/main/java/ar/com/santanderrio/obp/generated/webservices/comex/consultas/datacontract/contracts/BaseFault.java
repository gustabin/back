
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.contracts;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BaseFault complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="BaseFault">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FaultInterna" type="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts}BaseFault" minOccurs="0"/>
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Origen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Stack" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseFault", propOrder = {
    "faultInterna",
    "mensaje",
    "origen",
    "stack"
})
public class BaseFault {

    @XmlElementRef(name = "FaultInterna", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", type = JAXBElement.class)
    protected JAXBElement<BaseFault> faultInterna;
    @XmlElementRef(name = "Mensaje", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", type = JAXBElement.class)
    protected JAXBElement<String> mensaje;
    @XmlElementRef(name = "Origen", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", type = JAXBElement.class)
    protected JAXBElement<String> origen;
    @XmlElementRef(name = "Stack", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", type = JAXBElement.class)
    protected JAXBElement<String> stack;

    /**
     * Obtiene el valor de la propiedad faultInterna.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BaseFault }{@code >}
     *     
     */
    public JAXBElement<BaseFault> getFaultInterna() {
        return faultInterna;
    }

    /**
     * Define el valor de la propiedad faultInterna.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BaseFault }{@code >}
     *     
     */
    public void setFaultInterna(JAXBElement<BaseFault> value) {
        this.faultInterna = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMensaje(JAXBElement<String> value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad origen.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrigen() {
        return origen;
    }

    /**
     * Define el valor de la propiedad origen.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrigen(JAXBElement<String> value) {
        this.origen = value;
    }

    /**
     * Obtiene el valor de la propiedad stack.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStack() {
        return stack;
    }

    /**
     * Define el valor de la propiedad stack.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStack(JAXBElement<String> value) {
        this.stack = value;
    }

}

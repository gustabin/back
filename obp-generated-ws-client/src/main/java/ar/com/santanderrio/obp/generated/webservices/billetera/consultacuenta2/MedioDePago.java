
package ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for medioDePago complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="medioDePago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idMedioDePago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="favorito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoMedioPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validaBanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoMedioPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flagMedioPagoBanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "medioDePago", propOrder = {
    "numeroTarjeta",
    "idMedioDePago",
    "favorito",
    "estadoMedioPago",
    "fechaVencimiento",
    "validaBanco",
    "tipoMedioPago",
    "flagMedioPagoBanco"
})
public class MedioDePago {

    @XmlElementRef(name = "numeroTarjeta", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> numeroTarjeta;
    @XmlElementRef(name = "idMedioDePago", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> idMedioDePago;
    @XmlElementRef(name = "favorito", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> favorito;
    @XmlElementRef(name = "estadoMedioPago", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> estadoMedioPago;
    @XmlElementRef(name = "fechaVencimiento", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> fechaVencimiento;
    @XmlElementRef(name = "validaBanco", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> validaBanco;
    @XmlElementRef(name = "tipoMedioPago", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> tipoMedioPago;
    @XmlElementRef(name = "flagMedioPagoBanco", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> flagMedioPagoBanco;

    /**
     * Gets the value of the numeroTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * Sets the value of the numeroTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroTarjeta(JAXBElement<String> value) {
        this.numeroTarjeta = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the idMedioDePago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdMedioDePago() {
        return idMedioDePago;
    }

    /**
     * Sets the value of the idMedioDePago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdMedioDePago(JAXBElement<String> value) {
        this.idMedioDePago = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the favorito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFavorito() {
        return favorito;
    }

    /**
     * Sets the value of the favorito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFavorito(JAXBElement<String> value) {
        this.favorito = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the estadoMedioPago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoMedioPago() {
        return estadoMedioPago;
    }

    /**
     * Sets the value of the estadoMedioPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoMedioPago(JAXBElement<String> value) {
        this.estadoMedioPago = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the fechaVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the value of the fechaVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaVencimiento(JAXBElement<String> value) {
        this.fechaVencimiento = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the validaBanco property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getValidaBanco() {
        return validaBanco;
    }

    /**
     * Sets the value of the validaBanco property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setValidaBanco(JAXBElement<String> value) {
        this.validaBanco = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the tipoMedioPago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoMedioPago() {
        return tipoMedioPago;
    }

    /**
     * Sets the value of the tipoMedioPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoMedioPago(JAXBElement<String> value) {
        this.tipoMedioPago = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the flagMedioPagoBanco property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFlagMedioPagoBanco() {
        return flagMedioPagoBanco;
    }

    /**
     * Sets the value of the flagMedioPagoBanco property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFlagMedioPagoBanco(JAXBElement<String> value) {
        this.flagMedioPagoBanco = ((JAXBElement<String> ) value);
    }

}

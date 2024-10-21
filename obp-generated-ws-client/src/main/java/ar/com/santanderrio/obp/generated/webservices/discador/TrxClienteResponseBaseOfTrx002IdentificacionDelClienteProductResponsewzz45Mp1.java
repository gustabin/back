
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrxClienteResponseBaseOfTrx002IdentificacionDelClienteProductResponsewzz45mp1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrxClienteResponseBaseOfTrx002IdentificacionDelClienteProductResponsewzz45mp1">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CantidadProductos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdClienteRacfAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdContactoVm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdConversacionVm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdServicioVm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndBd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroUnicoPersonaAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrimerApellidoAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Productos" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx002}ArrayOfTrx002IdentificacionDelClienteProductResponse" minOccurs="0"/>
 *         &lt;element name="PwdClienteRacfAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SegundoApellidoAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemaforoFacturacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemaforoRentabilidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemaforoRiesgo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPublicidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrxClienteResponseBaseOfTrx002IdentificacionDelClienteProductResponsewzz45mp1", propOrder = {
    "cantidadProductos",
    "fechaNacimiento",
    "idCliente",
    "idClienteRacfAltair",
    "idContactoVm",
    "idConversacionVm",
    "idServicioVm",
    "indBd",
    "nombreAltair",
    "numeroUnicoPersonaAltair",
    "primerApellidoAltair",
    "productos",
    "pwdClienteRacfAltair",
    "segundoApellidoAltair",
    "semaforoFacturacion",
    "semaforoRentabilidad",
    "semaforoRiesgo",
    "tipoId",
    "tipoPersona",
    "tipoPublicidad"
})
@XmlSeeAlso({
    Trx002IdentificacionDelClienteResponse.class
})
public class TrxClienteResponseBaseOfTrx002IdentificacionDelClienteProductResponsewzz45Mp1
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CantidadProductos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> cantidadProductos;
    @XmlElementRef(name = "FechaNacimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> fechaNacimiento;
    @XmlElementRef(name = "IdCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> idCliente;
    @XmlElementRef(name = "IdClienteRacfAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> idClienteRacfAltair;
    @XmlElementRef(name = "IdContactoVm", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> idContactoVm;
    @XmlElementRef(name = "IdConversacionVm", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> idConversacionVm;
    @XmlElementRef(name = "IdServicioVm", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> idServicioVm;
    @XmlElementRef(name = "IndBd", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> indBd;
    @XmlElementRef(name = "NombreAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> nombreAltair;
    @XmlElementRef(name = "NumeroUnicoPersonaAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> numeroUnicoPersonaAltair;
    @XmlElementRef(name = "PrimerApellidoAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> primerApellidoAltair;
    @XmlElementRef(name = "Productos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrx002IdentificacionDelClienteProductResponse> productos;
    @XmlElementRef(name = "PwdClienteRacfAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> pwdClienteRacfAltair;
    @XmlElementRef(name = "SegundoApellidoAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> segundoApellidoAltair;
    @XmlElementRef(name = "SemaforoFacturacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> semaforoFacturacion;
    @XmlElementRef(name = "SemaforoRentabilidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> semaforoRentabilidad;
    @XmlElementRef(name = "SemaforoRiesgo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> semaforoRiesgo;
    @XmlElementRef(name = "TipoId", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> tipoId;
    @XmlElementRef(name = "TipoPersona", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> tipoPersona;
    @XmlElementRef(name = "TipoPublicidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> tipoPublicidad;

    /**
     * Gets the value of the cantidadProductos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantidadProductos() {
        return cantidadProductos;
    }

    /**
     * Sets the value of the cantidadProductos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantidadProductos(JAXBElement<String> value) {
        this.cantidadProductos = value;
    }

    /**
     * Gets the value of the fechaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the value of the fechaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaNacimiento(JAXBElement<String> value) {
        this.fechaNacimiento = value;
    }

    /**
     * Gets the value of the idCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdCliente() {
        return idCliente;
    }

    /**
     * Sets the value of the idCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdCliente(JAXBElement<String> value) {
        this.idCliente = value;
    }

    /**
     * Gets the value of the idClienteRacfAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdClienteRacfAltair() {
        return idClienteRacfAltair;
    }

    /**
     * Sets the value of the idClienteRacfAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdClienteRacfAltair(JAXBElement<String> value) {
        this.idClienteRacfAltair = value;
    }

    /**
     * Gets the value of the idContactoVm property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdContactoVm() {
        return idContactoVm;
    }

    /**
     * Sets the value of the idContactoVm property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdContactoVm(JAXBElement<String> value) {
        this.idContactoVm = value;
    }

    /**
     * Gets the value of the idConversacionVm property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdConversacionVm() {
        return idConversacionVm;
    }

    /**
     * Sets the value of the idConversacionVm property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdConversacionVm(JAXBElement<String> value) {
        this.idConversacionVm = value;
    }

    /**
     * Gets the value of the idServicioVm property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdServicioVm() {
        return idServicioVm;
    }

    /**
     * Sets the value of the idServicioVm property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdServicioVm(JAXBElement<String> value) {
        this.idServicioVm = value;
    }

    /**
     * Gets the value of the indBd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndBd() {
        return indBd;
    }

    /**
     * Sets the value of the indBd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndBd(JAXBElement<String> value) {
        this.indBd = value;
    }

    /**
     * Gets the value of the nombreAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreAltair() {
        return nombreAltair;
    }

    /**
     * Sets the value of the nombreAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreAltair(JAXBElement<String> value) {
        this.nombreAltair = value;
    }

    /**
     * Gets the value of the numeroUnicoPersonaAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroUnicoPersonaAltair() {
        return numeroUnicoPersonaAltair;
    }

    /**
     * Sets the value of the numeroUnicoPersonaAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroUnicoPersonaAltair(JAXBElement<String> value) {
        this.numeroUnicoPersonaAltair = value;
    }

    /**
     * Gets the value of the primerApellidoAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPrimerApellidoAltair() {
        return primerApellidoAltair;
    }

    /**
     * Sets the value of the primerApellidoAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPrimerApellidoAltair(JAXBElement<String> value) {
        this.primerApellidoAltair = value;
    }

    /**
     * Gets the value of the productos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx002IdentificacionDelClienteProductResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrx002IdentificacionDelClienteProductResponse> getProductos() {
        return productos;
    }

    /**
     * Sets the value of the productos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx002IdentificacionDelClienteProductResponse }{@code >}
     *     
     */
    public void setProductos(JAXBElement<ArrayOfTrx002IdentificacionDelClienteProductResponse> value) {
        this.productos = value;
    }

    /**
     * Gets the value of the pwdClienteRacfAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPwdClienteRacfAltair() {
        return pwdClienteRacfAltair;
    }

    /**
     * Sets the value of the pwdClienteRacfAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPwdClienteRacfAltair(JAXBElement<String> value) {
        this.pwdClienteRacfAltair = value;
    }

    /**
     * Gets the value of the segundoApellidoAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSegundoApellidoAltair() {
        return segundoApellidoAltair;
    }

    /**
     * Sets the value of the segundoApellidoAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSegundoApellidoAltair(JAXBElement<String> value) {
        this.segundoApellidoAltair = value;
    }

    /**
     * Gets the value of the semaforoFacturacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSemaforoFacturacion() {
        return semaforoFacturacion;
    }

    /**
     * Sets the value of the semaforoFacturacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSemaforoFacturacion(JAXBElement<String> value) {
        this.semaforoFacturacion = value;
    }

    /**
     * Gets the value of the semaforoRentabilidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSemaforoRentabilidad() {
        return semaforoRentabilidad;
    }

    /**
     * Sets the value of the semaforoRentabilidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSemaforoRentabilidad(JAXBElement<String> value) {
        this.semaforoRentabilidad = value;
    }

    /**
     * Gets the value of the semaforoRiesgo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSemaforoRiesgo() {
        return semaforoRiesgo;
    }

    /**
     * Sets the value of the semaforoRiesgo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSemaforoRiesgo(JAXBElement<String> value) {
        this.semaforoRiesgo = value;
    }

    /**
     * Gets the value of the tipoId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoId() {
        return tipoId;
    }

    /**
     * Sets the value of the tipoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoId(JAXBElement<String> value) {
        this.tipoId = value;
    }

    /**
     * Gets the value of the tipoPersona property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Sets the value of the tipoPersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoPersona(JAXBElement<String> value) {
        this.tipoPersona = value;
    }

    /**
     * Gets the value of the tipoPublicidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoPublicidad() {
        return tipoPublicidad;
    }

    /**
     * Sets the value of the tipoPublicidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoPublicidad(JAXBElement<String> value) {
        this.tipoPublicidad = value;
    }

}

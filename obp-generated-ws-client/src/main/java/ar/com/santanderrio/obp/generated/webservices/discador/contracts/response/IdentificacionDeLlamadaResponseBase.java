
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.ResponseBase;
import ar.com.santanderrio.obp.generated.webservices.discador.Trx001IdentificacionDeLlamadaAltairPrestamosResponse;
import ar.com.santanderrio.obp.generated.webservices.discador.Trx201IdentificacionDeLlamadaResponse;


/**
 * <p>Java class for IdentificacionDeLlamadaResponseBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentificacionDeLlamadaResponseBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CantidadFaxPendientes" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CantidadProductos" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="FechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirmanteSesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdClienteRacfAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdContactoVM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdConversacionVM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdFirmante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdServicioVM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndSinonimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaAnph" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaCv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MdlwOk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Nocturno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NodoArbolIvr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroUnicoPersonaAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PinCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PinFirmante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrimerApellidoAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PwdClienteRacfAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SegundoApellidoAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemaforoFacturacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemaforoRentabilidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemaforoRiesgo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipopublicidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentificacionDeLlamadaResponseBase", propOrder = {
    "cantidadFaxPendientes",
    "cantidadProductos",
    "fechaNacimiento",
    "firmanteSesion",
    "idCliente",
    "idClienteRacfAltair",
    "idContactoVM",
    "idConversacionVM",
    "idFirmante",
    "idServicioVM",
    "indSinonimo",
    "marcaAnph",
    "marcaCv",
    "marcaIp",
    "mdlwOk",
    "nocturno",
    "nodoArbolIvr",
    "nombreAltair",
    "numeroUnicoPersonaAltair",
    "pinCliente",
    "pinFirmante",
    "primerApellidoAltair",
    "pwdClienteRacfAltair",
    "segundoApellidoAltair",
    "semaforoFacturacion",
    "semaforoRentabilidad",
    "semaforoRiesgo",
    "tipoId",
    "tipoPersona",
    "tipopublicidad"
})
@XmlSeeAlso({
    Trx201IdentificacionDeLlamadaResponse.class,
    IdentificacionDeLlamadaResponse.class,
    Trx001IdentificacionDeLlamadaAltairPrestamosResponse.class
})
public class IdentificacionDeLlamadaResponseBase
    extends ResponseBase
{

    @XmlElement(name = "CantidadFaxPendientes")
    protected Integer cantidadFaxPendientes;
    @XmlElement(name = "CantidadProductos")
    protected Integer cantidadProductos;
    @XmlElementRef(name = "FechaNacimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> fechaNacimiento;
    @XmlElementRef(name = "FirmanteSesion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> firmanteSesion;
    @XmlElementRef(name = "IdCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> idCliente;
    @XmlElementRef(name = "IdClienteRacfAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> idClienteRacfAltair;
    @XmlElementRef(name = "IdContactoVM", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> idContactoVM;
    @XmlElementRef(name = "IdConversacionVM", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> idConversacionVM;
    @XmlElementRef(name = "IdFirmante", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> idFirmante;
    @XmlElementRef(name = "IdServicioVM", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> idServicioVM;
    @XmlElementRef(name = "IndSinonimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indSinonimo;
    @XmlElementRef(name = "MarcaAnph", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> marcaAnph;
    @XmlElementRef(name = "MarcaCv", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> marcaCv;
    @XmlElementRef(name = "MarcaIp", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> marcaIp;
    @XmlElement(name = "MdlwOk")
    protected Integer mdlwOk;
    @XmlElementRef(name = "Nocturno", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nocturno;
    @XmlElementRef(name = "NodoArbolIvr", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nodoArbolIvr;
    @XmlElementRef(name = "NombreAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nombreAltair;
    @XmlElementRef(name = "NumeroUnicoPersonaAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> numeroUnicoPersonaAltair;
    @XmlElementRef(name = "PinCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> pinCliente;
    @XmlElementRef(name = "PinFirmante", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> pinFirmante;
    @XmlElementRef(name = "PrimerApellidoAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> primerApellidoAltair;
    @XmlElementRef(name = "PwdClienteRacfAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> pwdClienteRacfAltair;
    @XmlElementRef(name = "SegundoApellidoAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> segundoApellidoAltair;
    @XmlElementRef(name = "SemaforoFacturacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> semaforoFacturacion;
    @XmlElementRef(name = "SemaforoRentabilidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> semaforoRentabilidad;
    @XmlElementRef(name = "SemaforoRiesgo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> semaforoRiesgo;
    @XmlElementRef(name = "TipoId", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> tipoId;
    @XmlElementRef(name = "TipoPersona", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> tipoPersona;
    @XmlElementRef(name = "Tipopublicidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> tipopublicidad;

    /**
     * Gets the value of the cantidadFaxPendientes property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidadFaxPendientes() {
        return cantidadFaxPendientes;
    }

    /**
     * Sets the value of the cantidadFaxPendientes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidadFaxPendientes(Integer value) {
        this.cantidadFaxPendientes = value;
    }

    /**
     * Gets the value of the cantidadProductos property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidadProductos() {
        return cantidadProductos;
    }

    /**
     * Sets the value of the cantidadProductos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidadProductos(Integer value) {
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
     * Gets the value of the firmanteSesion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirmanteSesion() {
        return firmanteSesion;
    }

    /**
     * Sets the value of the firmanteSesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirmanteSesion(JAXBElement<String> value) {
        this.firmanteSesion = value;
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
     * Gets the value of the idContactoVM property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdContactoVM() {
        return idContactoVM;
    }

    /**
     * Sets the value of the idContactoVM property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdContactoVM(JAXBElement<String> value) {
        this.idContactoVM = value;
    }

    /**
     * Gets the value of the idConversacionVM property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdConversacionVM() {
        return idConversacionVM;
    }

    /**
     * Sets the value of the idConversacionVM property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdConversacionVM(JAXBElement<String> value) {
        this.idConversacionVM = value;
    }

    /**
     * Gets the value of the idFirmante property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdFirmante() {
        return idFirmante;
    }

    /**
     * Sets the value of the idFirmante property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdFirmante(JAXBElement<String> value) {
        this.idFirmante = value;
    }

    /**
     * Gets the value of the idServicioVM property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdServicioVM() {
        return idServicioVM;
    }

    /**
     * Sets the value of the idServicioVM property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdServicioVM(JAXBElement<String> value) {
        this.idServicioVM = value;
    }

    /**
     * Gets the value of the indSinonimo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndSinonimo() {
        return indSinonimo;
    }

    /**
     * Sets the value of the indSinonimo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndSinonimo(JAXBElement<String> value) {
        this.indSinonimo = value;
    }

    /**
     * Gets the value of the marcaAnph property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaAnph() {
        return marcaAnph;
    }

    /**
     * Sets the value of the marcaAnph property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaAnph(JAXBElement<String> value) {
        this.marcaAnph = value;
    }

    /**
     * Gets the value of the marcaCv property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaCv() {
        return marcaCv;
    }

    /**
     * Sets the value of the marcaCv property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaCv(JAXBElement<String> value) {
        this.marcaCv = value;
    }

    /**
     * Gets the value of the marcaIp property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaIp() {
        return marcaIp;
    }

    /**
     * Sets the value of the marcaIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaIp(JAXBElement<String> value) {
        this.marcaIp = value;
    }

    /**
     * Gets the value of the mdlwOk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMdlwOk() {
        return mdlwOk;
    }

    /**
     * Sets the value of the mdlwOk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMdlwOk(Integer value) {
        this.mdlwOk = value;
    }

    /**
     * Gets the value of the nocturno property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNocturno() {
        return nocturno;
    }

    /**
     * Sets the value of the nocturno property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNocturno(JAXBElement<String> value) {
        this.nocturno = value;
    }

    /**
     * Gets the value of the nodoArbolIvr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNodoArbolIvr() {
        return nodoArbolIvr;
    }

    /**
     * Sets the value of the nodoArbolIvr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNodoArbolIvr(JAXBElement<String> value) {
        this.nodoArbolIvr = value;
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
     * Gets the value of the pinCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPinCliente() {
        return pinCliente;
    }

    /**
     * Sets the value of the pinCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPinCliente(JAXBElement<String> value) {
        this.pinCliente = value;
    }

    /**
     * Gets the value of the pinFirmante property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPinFirmante() {
        return pinFirmante;
    }

    /**
     * Sets the value of the pinFirmante property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPinFirmante(JAXBElement<String> value) {
        this.pinFirmante = value;
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
     * Gets the value of the tipopublicidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipopublicidad() {
        return tipopublicidad;
    }

    /**
     * Sets the value of the tipopublicidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipopublicidad(JAXBElement<String> value) {
        this.tipopublicidad = value;
    }

}

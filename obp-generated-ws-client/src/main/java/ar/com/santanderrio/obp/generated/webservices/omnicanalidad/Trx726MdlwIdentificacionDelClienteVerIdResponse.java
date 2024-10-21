
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx726MdlwIdentificacionDelClienteVerIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx726MdlwIdentificacionDelClienteVerIdResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CantidadProductos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HorarioMora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdContactoVm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdConversacionVm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdServicioVm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndBd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Productos" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfIdentificacionDelClienteProductResponse" minOccurs="0"/>
 *         &lt;element name="SemaforoFacturacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemaforoRentabilidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemaforoRiesgo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPublicidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_nacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id_cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id_cliente_racf_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numero_unico_persona_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primer_apellido_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pwd_cliente_racf_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="segundo_apellido_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_persona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx726MdlwIdentificacionDelClienteVerIdResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", propOrder = {
    "cantidadProductos",
    "horarioMora",
    "idContactoVm",
    "idConversacionVm",
    "idServicioVm",
    "indBd",
    "mora",
    "productos",
    "semaforoFacturacion",
    "semaforoRentabilidad",
    "semaforoRiesgo",
    "tipoPublicidad",
    "fechaNacimiento",
    "idCliente",
    "idClienteRacfAltair",
    "nombreAltair",
    "numeroUnicoPersonaAltair",
    "primerApellidoAltair",
    "pwdClienteRacfAltair",
    "segundoApellidoAltair",
    "tipoId",
    "tipoPersona"
})
public class Trx726MdlwIdentificacionDelClienteVerIdResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CantidadProductos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> cantidadProductos;
    @XmlElementRef(name = "HorarioMora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> horarioMora;
    @XmlElementRef(name = "IdContactoVm", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> idContactoVm;
    @XmlElementRef(name = "IdConversacionVm", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> idConversacionVm;
    @XmlElementRef(name = "IdServicioVm", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> idServicioVm;
    @XmlElementRef(name = "IndBd", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> indBd;
    @XmlElementRef(name = "Mora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> mora;
    @XmlElementRef(name = "Productos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<ArrayOfIdentificacionDelClienteProductResponse> productos;
    @XmlElementRef(name = "SemaforoFacturacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> semaforoFacturacion;
    @XmlElementRef(name = "SemaforoRentabilidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> semaforoRentabilidad;
    @XmlElementRef(name = "SemaforoRiesgo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> semaforoRiesgo;
    @XmlElementRef(name = "TipoPublicidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> tipoPublicidad;
    @XmlElementRef(name = "fecha_nacimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> fechaNacimiento;
    @XmlElementRef(name = "id_cliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> idCliente;
    @XmlElementRef(name = "id_cliente_racf_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> idClienteRacfAltair;
    @XmlElementRef(name = "nombre_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> nombreAltair;
    @XmlElementRef(name = "numero_unico_persona_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> numeroUnicoPersonaAltair;
    @XmlElementRef(name = "primer_apellido_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> primerApellidoAltair;
    @XmlElementRef(name = "pwd_cliente_racf_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> pwdClienteRacfAltair;
    @XmlElementRef(name = "segundo_apellido_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> segundoApellidoAltair;
    @XmlElementRef(name = "tipo_id", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> tipoId;
    @XmlElementRef(name = "tipo_persona", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx726", type = JAXBElement.class)
    protected JAXBElement<String> tipoPersona;

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
     * Gets the value of the horarioMora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHorarioMora() {
        return horarioMora;
    }

    /**
     * Sets the value of the horarioMora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHorarioMora(JAXBElement<String> value) {
        this.horarioMora = value;
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
     * Gets the value of the mora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMora() {
        return mora;
    }

    /**
     * Sets the value of the mora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMora(JAXBElement<String> value) {
        this.mora = value;
    }

    /**
     * Gets the value of the productos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfIdentificacionDelClienteProductResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfIdentificacionDelClienteProductResponse> getProductos() {
        return productos;
    }

    /**
     * Sets the value of the productos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfIdentificacionDelClienteProductResponse }{@code >}
     *     
     */
    public void setProductos(JAXBElement<ArrayOfIdentificacionDelClienteProductResponse> value) {
        this.productos = value;
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

}

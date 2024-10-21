
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx7006GrabarDatosSesionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx7006GrabarDatosSesionRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="CantidadProductos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Clave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DiasVto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DomicilioPart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstadoClienteMono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HorarioMora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdClienteRacfAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdContacto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdConversacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdServicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndSinonimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaANPH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaCv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MdlwOk" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nocturno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroUnicoPersona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroUnicoPersonaAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Operadora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ParmVto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrimerApellidoAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PwdClienteRacfAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RazonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SegundoApellidoAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemFacturacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemRentabilidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemRiesgo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sesion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="TipoClaveMono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx7006GrabarDatosSesionRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", propOrder = {
    "cantidadProductos",
    "clave",
    "diasVto",
    "documento",
    "domicilioPart",
    "estadoClienteMono",
    "horarioMora",
    "idClienteRacfAltair",
    "idContacto",
    "idConversacion",
    "idServicio",
    "indSinonimo",
    "marcaANPH",
    "marcaCv",
    "marcaIp",
    "mdlwOk",
    "mora",
    "nacimiento",
    "nocturno",
    "nombreAltair",
    "nroUnicoPersona",
    "numeroUnicoPersonaAltair",
    "operadora",
    "parmVto",
    "primerApellidoAltair",
    "pwdClienteRacfAltair",
    "razonSocial",
    "segundoApellidoAltair",
    "semFacturacion",
    "semRentabilidad",
    "semRiesgo",
    "sesion",
    "tipoClaveMono",
    "tipoDoc",
    "tipoPersona"
})
public class Trx7006GrabarDatosSesionRequest
    extends RequestBase
{

    @XmlElementRef(name = "CantidadProductos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> cantidadProductos;
    @XmlElementRef(name = "Clave", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> clave;
    @XmlElementRef(name = "DiasVto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> diasVto;
    @XmlElementRef(name = "Documento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> documento;
    @XmlElementRef(name = "DomicilioPart", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> domicilioPart;
    @XmlElementRef(name = "EstadoClienteMono", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> estadoClienteMono;
    @XmlElementRef(name = "HorarioMora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> horarioMora;
    @XmlElementRef(name = "IdClienteRacfAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> idClienteRacfAltair;
    @XmlElementRef(name = "IdContacto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> idContacto;
    @XmlElementRef(name = "IdConversacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> idConversacion;
    @XmlElementRef(name = "IdServicio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> idServicio;
    @XmlElementRef(name = "IndSinonimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> indSinonimo;
    @XmlElementRef(name = "MarcaANPH", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> marcaANPH;
    @XmlElementRef(name = "MarcaCv", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> marcaCv;
    @XmlElementRef(name = "MarcaIp", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> marcaIp;
    @XmlElementRef(name = "MdlwOk", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> mdlwOk;
    @XmlElementRef(name = "Mora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> mora;
    @XmlElementRef(name = "Nacimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> nacimiento;
    @XmlElementRef(name = "Nocturno", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> nocturno;
    @XmlElementRef(name = "NombreAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> nombreAltair;
    @XmlElementRef(name = "NroUnicoPersona", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> nroUnicoPersona;
    @XmlElementRef(name = "NumeroUnicoPersonaAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> numeroUnicoPersonaAltair;
    @XmlElementRef(name = "Operadora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> operadora;
    @XmlElementRef(name = "ParmVto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> parmVto;
    @XmlElementRef(name = "PrimerApellidoAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> primerApellidoAltair;
    @XmlElementRef(name = "PwdClienteRacfAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> pwdClienteRacfAltair;
    @XmlElementRef(name = "RazonSocial", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> razonSocial;
    @XmlElementRef(name = "SegundoApellidoAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> segundoApellidoAltair;
    @XmlElementRef(name = "SemFacturacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> semFacturacion;
    @XmlElementRef(name = "SemRentabilidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> semRentabilidad;
    @XmlElementRef(name = "SemRiesgo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> semRiesgo;
    @XmlElement(name = "Sesion")
    protected Long sesion;
    @XmlElementRef(name = "TipoClaveMono", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> tipoClaveMono;
    @XmlElementRef(name = "TipoDoc", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
    protected JAXBElement<String> tipoDoc;
    @XmlElementRef(name = "TipoPersona", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7006", type = JAXBElement.class)
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
     * Gets the value of the clave property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClave() {
        return clave;
    }

    /**
     * Sets the value of the clave property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClave(JAXBElement<String> value) {
        this.clave = value;
    }

    /**
     * Gets the value of the diasVto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDiasVto() {
        return diasVto;
    }

    /**
     * Sets the value of the diasVto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDiasVto(JAXBElement<String> value) {
        this.diasVto = value;
    }

    /**
     * Gets the value of the documento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDocumento() {
        return documento;
    }

    /**
     * Sets the value of the documento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDocumento(JAXBElement<String> value) {
        this.documento = value;
    }

    /**
     * Gets the value of the domicilioPart property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomicilioPart() {
        return domicilioPart;
    }

    /**
     * Sets the value of the domicilioPart property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomicilioPart(JAXBElement<String> value) {
        this.domicilioPart = value;
    }

    /**
     * Gets the value of the estadoClienteMono property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoClienteMono() {
        return estadoClienteMono;
    }

    /**
     * Sets the value of the estadoClienteMono property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoClienteMono(JAXBElement<String> value) {
        this.estadoClienteMono = value;
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
     * Gets the value of the idContacto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdContacto() {
        return idContacto;
    }

    /**
     * Sets the value of the idContacto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdContacto(JAXBElement<String> value) {
        this.idContacto = value;
    }

    /**
     * Gets the value of the idConversacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdConversacion() {
        return idConversacion;
    }

    /**
     * Sets the value of the idConversacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdConversacion(JAXBElement<String> value) {
        this.idConversacion = value;
    }

    /**
     * Gets the value of the idServicio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdServicio() {
        return idServicio;
    }

    /**
     * Sets the value of the idServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdServicio(JAXBElement<String> value) {
        this.idServicio = value;
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
     * Gets the value of the marcaANPH property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaANPH() {
        return marcaANPH;
    }

    /**
     * Sets the value of the marcaANPH property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaANPH(JAXBElement<String> value) {
        this.marcaANPH = value;
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMdlwOk() {
        return mdlwOk;
    }

    /**
     * Sets the value of the mdlwOk property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMdlwOk(JAXBElement<String> value) {
        this.mdlwOk = value;
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
     * Gets the value of the nacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNacimiento() {
        return nacimiento;
    }

    /**
     * Sets the value of the nacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNacimiento(JAXBElement<String> value) {
        this.nacimiento = value;
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
     * Gets the value of the nroUnicoPersona property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroUnicoPersona() {
        return nroUnicoPersona;
    }

    /**
     * Sets the value of the nroUnicoPersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroUnicoPersona(JAXBElement<String> value) {
        this.nroUnicoPersona = value;
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
     * Gets the value of the operadora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOperadora() {
        return operadora;
    }

    /**
     * Sets the value of the operadora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOperadora(JAXBElement<String> value) {
        this.operadora = value;
    }

    /**
     * Gets the value of the parmVto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getParmVto() {
        return parmVto;
    }

    /**
     * Sets the value of the parmVto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setParmVto(JAXBElement<String> value) {
        this.parmVto = value;
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
     * Gets the value of the razonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRazonSocial() {
        return razonSocial;
    }

    /**
     * Sets the value of the razonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRazonSocial(JAXBElement<String> value) {
        this.razonSocial = value;
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
     * Gets the value of the semFacturacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSemFacturacion() {
        return semFacturacion;
    }

    /**
     * Sets the value of the semFacturacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSemFacturacion(JAXBElement<String> value) {
        this.semFacturacion = value;
    }

    /**
     * Gets the value of the semRentabilidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSemRentabilidad() {
        return semRentabilidad;
    }

    /**
     * Sets the value of the semRentabilidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSemRentabilidad(JAXBElement<String> value) {
        this.semRentabilidad = value;
    }

    /**
     * Gets the value of the semRiesgo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSemRiesgo() {
        return semRiesgo;
    }

    /**
     * Sets the value of the semRiesgo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSemRiesgo(JAXBElement<String> value) {
        this.semRiesgo = value;
    }

    /**
     * Gets the value of the sesion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSesion() {
        return sesion;
    }

    /**
     * Sets the value of the sesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSesion(Long value) {
        this.sesion = value;
    }

    /**
     * Gets the value of the tipoClaveMono property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoClaveMono() {
        return tipoClaveMono;
    }

    /**
     * Sets the value of the tipoClaveMono property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoClaveMono(JAXBElement<String> value) {
        this.tipoClaveMono = value;
    }

    /**
     * Gets the value of the tipoDoc property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoDoc() {
        return tipoDoc;
    }

    /**
     * Sets the value of the tipoDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoDoc(JAXBElement<String> value) {
        this.tipoDoc = value;
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

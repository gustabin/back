
package ar.com.santanderrio.obp.generated.webservices.discador;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx7010ObtenerHistorialSolicitudesRepetitionItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx7010ObtenerHistorialSolicitudesRepetitionItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Cambioestado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Circuito" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Circuito_Descri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Colorfondo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Colortexto" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Estado_Descri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Prioperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nombre_Usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nomusu_Cambio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Observacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Producto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Producto_Apaq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Rn" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Solicitud" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Solicitud_Asol" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Sucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sucursal_Descri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipoinfinitydesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Totprod" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Usuario_Alta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Usuario_Camb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx7010ObtenerHistorialSolicitudesRepetitionItem", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", propOrder = {
    "cambioestado",
    "circuito",
    "circuitoDescri",
    "colorfondo",
    "colortexto",
    "estado",
    "estadoDescri",
    "fechaPrioperacion",
    "nombreUsuario",
    "nomusuCambio",
    "observacion",
    "producto",
    "productoApaq",
    "rn",
    "solicitud",
    "solicitudAsol",
    "sucursal",
    "sucursalDescri",
    "tipoinfinitydesc",
    "totprod",
    "usuarioAlta",
    "usuarioCamb"
})
public class Trx7010ObtenerHistorialSolicitudesRepetitionItem {

    @XmlElementRef(name = "Cambioestado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> cambioestado;
    @XmlElementRef(name = "Circuito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<Short> circuito;
    @XmlElementRef(name = "Circuito_Descri", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> circuitoDescri;
    @XmlElementRef(name = "Colorfondo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<Long> colorfondo;
    @XmlElementRef(name = "Colortexto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<Long> colortexto;
    @XmlElementRef(name = "Estado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> estado;
    @XmlElementRef(name = "Estado_Descri", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> estadoDescri;
    @XmlElementRef(name = "Fecha_Prioperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> fechaPrioperacion;
    @XmlElementRef(name = "Nombre_Usuario", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> nombreUsuario;
    @XmlElementRef(name = "Nomusu_Cambio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> nomusuCambio;
    @XmlElementRef(name = "Observacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> observacion;
    @XmlElementRef(name = "Producto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> producto;
    @XmlElementRef(name = "Producto_Apaq", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> productoApaq;
    @XmlElement(name = "Rn")
    protected BigDecimal rn;
    @XmlElementRef(name = "Solicitud", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<Long> solicitud;
    @XmlElementRef(name = "Solicitud_Asol", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<Long> solicitudAsol;
    @XmlElementRef(name = "Sucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> sucursal;
    @XmlElementRef(name = "Sucursal_Descri", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> sucursalDescri;
    @XmlElementRef(name = "Tipoinfinitydesc", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> tipoinfinitydesc;
    @XmlElementRef(name = "Totprod", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> totprod;
    @XmlElementRef(name = "Usuario_Alta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> usuarioAlta;
    @XmlElementRef(name = "Usuario_Camb", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<String> usuarioCamb;

    /**
     * Gets the value of the cambioestado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCambioestado() {
        return cambioestado;
    }

    /**
     * Sets the value of the cambioestado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCambioestado(JAXBElement<String> value) {
        this.cambioestado = value;
    }

    /**
     * Gets the value of the circuito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getCircuito() {
        return circuito;
    }

    /**
     * Sets the value of the circuito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setCircuito(JAXBElement<Short> value) {
        this.circuito = value;
    }

    /**
     * Gets the value of the circuitoDescri property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCircuitoDescri() {
        return circuitoDescri;
    }

    /**
     * Sets the value of the circuitoDescri property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCircuitoDescri(JAXBElement<String> value) {
        this.circuitoDescri = value;
    }

    /**
     * Gets the value of the colorfondo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getColorfondo() {
        return colorfondo;
    }

    /**
     * Sets the value of the colorfondo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setColorfondo(JAXBElement<Long> value) {
        this.colorfondo = value;
    }

    /**
     * Gets the value of the colortexto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getColortexto() {
        return colortexto;
    }

    /**
     * Sets the value of the colortexto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setColortexto(JAXBElement<Long> value) {
        this.colortexto = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstado(JAXBElement<String> value) {
        this.estado = value;
    }

    /**
     * Gets the value of the estadoDescri property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoDescri() {
        return estadoDescri;
    }

    /**
     * Sets the value of the estadoDescri property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoDescri(JAXBElement<String> value) {
        this.estadoDescri = value;
    }

    /**
     * Gets the value of the fechaPrioperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaPrioperacion() {
        return fechaPrioperacion;
    }

    /**
     * Sets the value of the fechaPrioperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaPrioperacion(JAXBElement<String> value) {
        this.fechaPrioperacion = value;
    }

    /**
     * Gets the value of the nombreUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Sets the value of the nombreUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreUsuario(JAXBElement<String> value) {
        this.nombreUsuario = value;
    }

    /**
     * Gets the value of the nomusuCambio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNomusuCambio() {
        return nomusuCambio;
    }

    /**
     * Sets the value of the nomusuCambio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNomusuCambio(JAXBElement<String> value) {
        this.nomusuCambio = value;
    }

    /**
     * Gets the value of the observacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getObservacion() {
        return observacion;
    }

    /**
     * Sets the value of the observacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setObservacion(JAXBElement<String> value) {
        this.observacion = value;
    }

    /**
     * Gets the value of the producto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProducto() {
        return producto;
    }

    /**
     * Sets the value of the producto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProducto(JAXBElement<String> value) {
        this.producto = value;
    }

    /**
     * Gets the value of the productoApaq property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProductoApaq() {
        return productoApaq;
    }

    /**
     * Sets the value of the productoApaq property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProductoApaq(JAXBElement<String> value) {
        this.productoApaq = value;
    }

    /**
     * Gets the value of the rn property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRn() {
        return rn;
    }

    /**
     * Sets the value of the rn property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRn(BigDecimal value) {
        this.rn = value;
    }

    /**
     * Gets the value of the solicitud property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getSolicitud() {
        return solicitud;
    }

    /**
     * Sets the value of the solicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setSolicitud(JAXBElement<Long> value) {
        this.solicitud = value;
    }

    /**
     * Gets the value of the solicitudAsol property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getSolicitudAsol() {
        return solicitudAsol;
    }

    /**
     * Sets the value of the solicitudAsol property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setSolicitudAsol(JAXBElement<Long> value) {
        this.solicitudAsol = value;
    }

    /**
     * Gets the value of the sucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursal() {
        return sucursal;
    }

    /**
     * Sets the value of the sucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursal(JAXBElement<String> value) {
        this.sucursal = value;
    }

    /**
     * Gets the value of the sucursalDescri property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalDescri() {
        return sucursalDescri;
    }

    /**
     * Sets the value of the sucursalDescri property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalDescri(JAXBElement<String> value) {
        this.sucursalDescri = value;
    }

    /**
     * Gets the value of the tipoinfinitydesc property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoinfinitydesc() {
        return tipoinfinitydesc;
    }

    /**
     * Sets the value of the tipoinfinitydesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoinfinitydesc(JAXBElement<String> value) {
        this.tipoinfinitydesc = value;
    }

    /**
     * Gets the value of the totprod property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getTotprod() {
        return totprod;
    }

    /**
     * Sets the value of the totprod property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setTotprod(JAXBElement<BigDecimal> value) {
        this.totprod = value;
    }

    /**
     * Gets the value of the usuarioAlta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUsuarioAlta() {
        return usuarioAlta;
    }

    /**
     * Sets the value of the usuarioAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUsuarioAlta(JAXBElement<String> value) {
        this.usuarioAlta = value;
    }

    /**
     * Gets the value of the usuarioCamb property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUsuarioCamb() {
        return usuarioCamb;
    }

    /**
     * Sets the value of the usuarioCamb property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUsuarioCamb(JAXBElement<String> value) {
        this.usuarioCamb = value;
    }

}

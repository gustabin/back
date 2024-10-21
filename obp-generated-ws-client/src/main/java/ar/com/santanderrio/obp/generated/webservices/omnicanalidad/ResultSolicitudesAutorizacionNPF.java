
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultSolicitudesAutorizacionNPF complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultSolicitudesAutorizacionNPF">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CanalAcceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoRechazoAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescripcionRechazoAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstadoSolicitudAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaBaja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaHoraPasajeHistorico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaHoraUltimaModificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdServicioTransaccional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdSistema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdSolicitudAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ParametrosServicioTransaccional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubcanalAcceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultSolicitudesAutorizacionNPF", propOrder = {
    "canalAcceso",
    "codigoRechazoAutorizacion",
    "descripcionRechazoAutorizacion",
    "estadoSolicitudAutorizacion",
    "fechaAlta",
    "fechaBaja",
    "fechaHoraPasajeHistorico",
    "fechaHoraUltimaModificacion",
    "idServicioTransaccional",
    "idSistema",
    "idSolicitudAutorizacion",
    "nroOperacion",
    "parametrosServicioTransaccional",
    "subcanalAcceso"
})
public class ResultSolicitudesAutorizacionNPF
    extends ResponseBase
{

    @XmlElementRef(name = "CanalAcceso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> canalAcceso;
    @XmlElementRef(name = "CodigoRechazoAutorizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> codigoRechazoAutorizacion;
    @XmlElementRef(name = "DescripcionRechazoAutorizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> descripcionRechazoAutorizacion;
    @XmlElementRef(name = "EstadoSolicitudAutorizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> estadoSolicitudAutorizacion;
    @XmlElementRef(name = "FechaAlta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> fechaAlta;
    @XmlElementRef(name = "FechaBaja", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> fechaBaja;
    @XmlElementRef(name = "FechaHoraPasajeHistorico", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> fechaHoraPasajeHistorico;
    @XmlElementRef(name = "FechaHoraUltimaModificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> fechaHoraUltimaModificacion;
    @XmlElementRef(name = "IdServicioTransaccional", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> idServicioTransaccional;
    @XmlElementRef(name = "IdSistema", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> idSistema;
    @XmlElementRef(name = "IdSolicitudAutorizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> idSolicitudAutorizacion;
    @XmlElementRef(name = "NroOperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nroOperacion;
    @XmlElementRef(name = "ParametrosServicioTransaccional", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> parametrosServicioTransaccional;
    @XmlElementRef(name = "SubcanalAcceso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> subcanalAcceso;

    /**
     * Gets the value of the canalAcceso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCanalAcceso() {
        return canalAcceso;
    }

    /**
     * Sets the value of the canalAcceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCanalAcceso(JAXBElement<String> value) {
        this.canalAcceso = value;
    }

    /**
     * Gets the value of the codigoRechazoAutorizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoRechazoAutorizacion() {
        return codigoRechazoAutorizacion;
    }

    /**
     * Sets the value of the codigoRechazoAutorizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoRechazoAutorizacion(JAXBElement<String> value) {
        this.codigoRechazoAutorizacion = value;
    }

    /**
     * Gets the value of the descripcionRechazoAutorizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionRechazoAutorizacion() {
        return descripcionRechazoAutorizacion;
    }

    /**
     * Sets the value of the descripcionRechazoAutorizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionRechazoAutorizacion(JAXBElement<String> value) {
        this.descripcionRechazoAutorizacion = value;
    }

    /**
     * Gets the value of the estadoSolicitudAutorizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoSolicitudAutorizacion() {
        return estadoSolicitudAutorizacion;
    }

    /**
     * Sets the value of the estadoSolicitudAutorizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoSolicitudAutorizacion(JAXBElement<String> value) {
        this.estadoSolicitudAutorizacion = value;
    }

    /**
     * Gets the value of the fechaAlta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the value of the fechaAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaAlta(JAXBElement<String> value) {
        this.fechaAlta = value;
    }

    /**
     * Gets the value of the fechaBaja property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Sets the value of the fechaBaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaBaja(JAXBElement<String> value) {
        this.fechaBaja = value;
    }

    /**
     * Gets the value of the fechaHoraPasajeHistorico property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaHoraPasajeHistorico() {
        return fechaHoraPasajeHistorico;
    }

    /**
     * Sets the value of the fechaHoraPasajeHistorico property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaHoraPasajeHistorico(JAXBElement<String> value) {
        this.fechaHoraPasajeHistorico = value;
    }

    /**
     * Gets the value of the fechaHoraUltimaModificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaHoraUltimaModificacion() {
        return fechaHoraUltimaModificacion;
    }

    /**
     * Sets the value of the fechaHoraUltimaModificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaHoraUltimaModificacion(JAXBElement<String> value) {
        this.fechaHoraUltimaModificacion = value;
    }

    /**
     * Gets the value of the idServicioTransaccional property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdServicioTransaccional() {
        return idServicioTransaccional;
    }

    /**
     * Sets the value of the idServicioTransaccional property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdServicioTransaccional(JAXBElement<String> value) {
        this.idServicioTransaccional = value;
    }

    /**
     * Gets the value of the idSistema property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdSistema() {
        return idSistema;
    }

    /**
     * Sets the value of the idSistema property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdSistema(JAXBElement<String> value) {
        this.idSistema = value;
    }

    /**
     * Gets the value of the idSolicitudAutorizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdSolicitudAutorizacion() {
        return idSolicitudAutorizacion;
    }

    /**
     * Sets the value of the idSolicitudAutorizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdSolicitudAutorizacion(JAXBElement<String> value) {
        this.idSolicitudAutorizacion = value;
    }

    /**
     * Gets the value of the nroOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroOperacion() {
        return nroOperacion;
    }

    /**
     * Sets the value of the nroOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroOperacion(JAXBElement<String> value) {
        this.nroOperacion = value;
    }

    /**
     * Gets the value of the parametrosServicioTransaccional property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getParametrosServicioTransaccional() {
        return parametrosServicioTransaccional;
    }

    /**
     * Sets the value of the parametrosServicioTransaccional property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setParametrosServicioTransaccional(JAXBElement<String> value) {
        this.parametrosServicioTransaccional = value;
    }

    /**
     * Gets the value of the subcanalAcceso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubcanalAcceso() {
        return subcanalAcceso;
    }

    /**
     * Sets the value of the subcanalAcceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubcanalAcceso(JAXBElement<String> value) {
        this.subcanalAcceso = value;
    }

}

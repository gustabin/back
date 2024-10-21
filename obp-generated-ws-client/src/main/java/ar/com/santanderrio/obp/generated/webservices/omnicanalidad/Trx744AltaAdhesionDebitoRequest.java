
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx744AltaAdhesionDebitoRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx744AltaAdhesionDebitoRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="Autorizacion" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}AutorizacionRequest" minOccurs="0"/>
 *         &lt;element name="AutorizacionRequerida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cliente" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}TDatosClienteRequest" minOccurs="0"/>
 *         &lt;element name="CuitEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescMediopagoRiop" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdEmpservRiopi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdMediopagoRiopi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LimiteAdhesionDdi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NomFantaEmpservRiopi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NomIdentifCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreServicioEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCuentaProductoDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroOrdenFirmante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroPartidaServicioEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx744AltaAdhesionDebitoRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", propOrder = {
    "autorizacion",
    "autorizacionRequerida",
    "cliente",
    "cuitEmpresa",
    "descMediopagoRiop",
    "idEmpservRiopi",
    "idMediopagoRiopi",
    "limiteAdhesionDdi",
    "nomFantaEmpservRiopi",
    "nomIdentifCliente",
    "nombreServicioEmpresa",
    "nroCuentaProductoDebito",
    "nroOrdenFirmante",
    "nroPartidaServicioEmpresa",
    "sucursalCuentaDebito",
    "tipoCuentaDebito"
})
public class Trx744AltaAdhesionDebitoRequest
    extends RequestBase
{

    @XmlElementRef(name = "Autorizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<AutorizacionRequest> autorizacion;
    @XmlElementRef(name = "AutorizacionRequerida", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> autorizacionRequerida;
    @XmlElementRef(name = "Cliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<TDatosClienteRequest> cliente;
    @XmlElementRef(name = "CuitEmpresa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> cuitEmpresa;
    @XmlElementRef(name = "DescMediopagoRiop", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> descMediopagoRiop;
    @XmlElementRef(name = "IdEmpservRiopi", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> idEmpservRiopi;
    @XmlElementRef(name = "IdMediopagoRiopi", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> idMediopagoRiopi;
    @XmlElementRef(name = "LimiteAdhesionDdi", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> limiteAdhesionDdi;
    @XmlElementRef(name = "NomFantaEmpservRiopi", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> nomFantaEmpservRiopi;
    @XmlElementRef(name = "NomIdentifCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> nomIdentifCliente;
    @XmlElementRef(name = "NombreServicioEmpresa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> nombreServicioEmpresa;
    @XmlElementRef(name = "NroCuentaProductoDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> nroCuentaProductoDebito;
    @XmlElementRef(name = "NroOrdenFirmante", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> nroOrdenFirmante;
    @XmlElementRef(name = "NroPartidaServicioEmpresa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> nroPartidaServicioEmpresa;
    @XmlElementRef(name = "SucursalCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCuentaDebito;
    @XmlElementRef(name = "TipoCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx744", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuentaDebito;

    /**
     * Gets the value of the autorizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AutorizacionRequest }{@code >}
     *     
     */
    public JAXBElement<AutorizacionRequest> getAutorizacion() {
        return autorizacion;
    }

    /**
     * Sets the value of the autorizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AutorizacionRequest }{@code >}
     *     
     */
    public void setAutorizacion(JAXBElement<AutorizacionRequest> value) {
        this.autorizacion = value;
    }

    /**
     * Gets the value of the autorizacionRequerida property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAutorizacionRequerida() {
        return autorizacionRequerida;
    }

    /**
     * Sets the value of the autorizacionRequerida property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAutorizacionRequerida(JAXBElement<String> value) {
        this.autorizacionRequerida = value;
    }

    /**
     * Gets the value of the cliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TDatosClienteRequest }{@code >}
     *     
     */
    public JAXBElement<TDatosClienteRequest> getCliente() {
        return cliente;
    }

    /**
     * Sets the value of the cliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TDatosClienteRequest }{@code >}
     *     
     */
    public void setCliente(JAXBElement<TDatosClienteRequest> value) {
        this.cliente = value;
    }

    /**
     * Gets the value of the cuitEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuitEmpresa() {
        return cuitEmpresa;
    }

    /**
     * Sets the value of the cuitEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuitEmpresa(JAXBElement<String> value) {
        this.cuitEmpresa = value;
    }

    /**
     * Gets the value of the descMediopagoRiop property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescMediopagoRiop() {
        return descMediopagoRiop;
    }

    /**
     * Sets the value of the descMediopagoRiop property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescMediopagoRiop(JAXBElement<String> value) {
        this.descMediopagoRiop = value;
    }

    /**
     * Gets the value of the idEmpservRiopi property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdEmpservRiopi() {
        return idEmpservRiopi;
    }

    /**
     * Sets the value of the idEmpservRiopi property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdEmpservRiopi(JAXBElement<String> value) {
        this.idEmpservRiopi = value;
    }

    /**
     * Gets the value of the idMediopagoRiopi property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdMediopagoRiopi() {
        return idMediopagoRiopi;
    }

    /**
     * Sets the value of the idMediopagoRiopi property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdMediopagoRiopi(JAXBElement<String> value) {
        this.idMediopagoRiopi = value;
    }

    /**
     * Gets the value of the limiteAdhesionDdi property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLimiteAdhesionDdi() {
        return limiteAdhesionDdi;
    }

    /**
     * Sets the value of the limiteAdhesionDdi property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLimiteAdhesionDdi(JAXBElement<String> value) {
        this.limiteAdhesionDdi = value;
    }

    /**
     * Gets the value of the nomFantaEmpservRiopi property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNomFantaEmpservRiopi() {
        return nomFantaEmpservRiopi;
    }

    /**
     * Sets the value of the nomFantaEmpservRiopi property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNomFantaEmpservRiopi(JAXBElement<String> value) {
        this.nomFantaEmpservRiopi = value;
    }

    /**
     * Gets the value of the nomIdentifCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNomIdentifCliente() {
        return nomIdentifCliente;
    }

    /**
     * Sets the value of the nomIdentifCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNomIdentifCliente(JAXBElement<String> value) {
        this.nomIdentifCliente = value;
    }

    /**
     * Gets the value of the nombreServicioEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreServicioEmpresa() {
        return nombreServicioEmpresa;
    }

    /**
     * Sets the value of the nombreServicioEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreServicioEmpresa(JAXBElement<String> value) {
        this.nombreServicioEmpresa = value;
    }

    /**
     * Gets the value of the nroCuentaProductoDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCuentaProductoDebito() {
        return nroCuentaProductoDebito;
    }

    /**
     * Sets the value of the nroCuentaProductoDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCuentaProductoDebito(JAXBElement<String> value) {
        this.nroCuentaProductoDebito = value;
    }

    /**
     * Gets the value of the nroOrdenFirmante property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroOrdenFirmante() {
        return nroOrdenFirmante;
    }

    /**
     * Sets the value of the nroOrdenFirmante property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroOrdenFirmante(JAXBElement<String> value) {
        this.nroOrdenFirmante = value;
    }

    /**
     * Gets the value of the nroPartidaServicioEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroPartidaServicioEmpresa() {
        return nroPartidaServicioEmpresa;
    }

    /**
     * Sets the value of the nroPartidaServicioEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroPartidaServicioEmpresa(JAXBElement<String> value) {
        this.nroPartidaServicioEmpresa = value;
    }

    /**
     * Gets the value of the sucursalCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalCuentaDebito() {
        return sucursalCuentaDebito;
    }

    /**
     * Sets the value of the sucursalCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalCuentaDebito(JAXBElement<String> value) {
        this.sucursalCuentaDebito = value;
    }

    /**
     * Gets the value of the tipoCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCuentaDebito() {
        return tipoCuentaDebito;
    }

    /**
     * Sets the value of the tipoCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCuentaDebito(JAXBElement<String> value) {
        this.tipoCuentaDebito = value;
    }

}

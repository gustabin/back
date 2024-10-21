
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.AutorizacionRequest;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.TDatosClienteHeadRequest;


/**
 * <p>Java class for Trx168TransferenciaAch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx168TransferenciaAch">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}TDatosClienteHeadRequest">
 *       &lt;sequence>
 *         &lt;element name="Autorizacion" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}AutorizacionRequest" minOccurs="0"/>
 *         &lt;element name="AutorizacionRequerida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CaracteristicasCtaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DigitoCtaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntidadCtaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirmanteCtaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdentificBeneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteTransferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InformacDiscrecional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaGravado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaLimite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MonedaTransferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCtaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReferenciaUnivoca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCtaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoEjecucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoTransferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx168TransferenciaAch", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", propOrder = {
    "autorizacion",
    "autorizacionRequerida",
    "caracteristicasCtaCredito",
    "digitoCtaCredito",
    "entidadCtaCredito",
    "firmanteCtaDebito",
    "identificBeneficiario",
    "importeTransferencia",
    "informacDiscrecional",
    "marcaGravado",
    "marcaLimite",
    "monedaTransferencia",
    "nroCtaCredito",
    "nroCuentaDebito",
    "referenciaUnivoca",
    "sucursalCtaCredito",
    "sucursalCuentaDebito",
    "tipoCuentaDebito",
    "tipoEjecucion",
    "tipoTransferencia"
})
public class Trx168TransferenciaAch
    extends TDatosClienteHeadRequest
{

    @XmlElementRef(name = "Autorizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<AutorizacionRequest> autorizacion;
    @XmlElementRef(name = "AutorizacionRequerida", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> autorizacionRequerida;
    @XmlElementRef(name = "CaracteristicasCtaCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> caracteristicasCtaCredito;
    @XmlElementRef(name = "DigitoCtaCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> digitoCtaCredito;
    @XmlElementRef(name = "EntidadCtaCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> entidadCtaCredito;
    @XmlElementRef(name = "FirmanteCtaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> firmanteCtaDebito;
    @XmlElementRef(name = "IdentificBeneficiario", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> identificBeneficiario;
    @XmlElementRef(name = "ImporteTransferencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> importeTransferencia;
    @XmlElementRef(name = "InformacDiscrecional", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> informacDiscrecional;
    @XmlElementRef(name = "MarcaGravado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> marcaGravado;
    @XmlElementRef(name = "MarcaLimite", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> marcaLimite;
    @XmlElementRef(name = "MonedaTransferencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> monedaTransferencia;
    @XmlElementRef(name = "NroCtaCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> nroCtaCredito;
    @XmlElementRef(name = "NroCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> nroCuentaDebito;
    @XmlElementRef(name = "ReferenciaUnivoca", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> referenciaUnivoca;
    @XmlElementRef(name = "SucursalCtaCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCtaCredito;
    @XmlElementRef(name = "SucursalCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCuentaDebito;
    @XmlElementRef(name = "TipoCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuentaDebito;
    @XmlElementRef(name = "TipoEjecucion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> tipoEjecucion;
    @XmlElementRef(name = "TipoTransferencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> tipoTransferencia;

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
     * Gets the value of the caracteristicasCtaCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaracteristicasCtaCredito() {
        return caracteristicasCtaCredito;
    }

    /**
     * Sets the value of the caracteristicasCtaCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaracteristicasCtaCredito(JAXBElement<String> value) {
        this.caracteristicasCtaCredito = value;
    }

    /**
     * Gets the value of the digitoCtaCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDigitoCtaCredito() {
        return digitoCtaCredito;
    }

    /**
     * Sets the value of the digitoCtaCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDigitoCtaCredito(JAXBElement<String> value) {
        this.digitoCtaCredito = value;
    }

    /**
     * Gets the value of the entidadCtaCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEntidadCtaCredito() {
        return entidadCtaCredito;
    }

    /**
     * Sets the value of the entidadCtaCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEntidadCtaCredito(JAXBElement<String> value) {
        this.entidadCtaCredito = value;
    }

    /**
     * Gets the value of the firmanteCtaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirmanteCtaDebito() {
        return firmanteCtaDebito;
    }

    /**
     * Sets the value of the firmanteCtaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirmanteCtaDebito(JAXBElement<String> value) {
        this.firmanteCtaDebito = value;
    }

    /**
     * Gets the value of the identificBeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdentificBeneficiario() {
        return identificBeneficiario;
    }

    /**
     * Sets the value of the identificBeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdentificBeneficiario(JAXBElement<String> value) {
        this.identificBeneficiario = value;
    }

    /**
     * Gets the value of the importeTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteTransferencia() {
        return importeTransferencia;
    }

    /**
     * Sets the value of the importeTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteTransferencia(JAXBElement<String> value) {
        this.importeTransferencia = value;
    }

    /**
     * Gets the value of the informacDiscrecional property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInformacDiscrecional() {
        return informacDiscrecional;
    }

    /**
     * Sets the value of the informacDiscrecional property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInformacDiscrecional(JAXBElement<String> value) {
        this.informacDiscrecional = value;
    }

    /**
     * Gets the value of the marcaGravado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaGravado() {
        return marcaGravado;
    }

    /**
     * Sets the value of the marcaGravado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaGravado(JAXBElement<String> value) {
        this.marcaGravado = value;
    }

    /**
     * Gets the value of the marcaLimite property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaLimite() {
        return marcaLimite;
    }

    /**
     * Sets the value of the marcaLimite property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaLimite(JAXBElement<String> value) {
        this.marcaLimite = value;
    }

    /**
     * Gets the value of the monedaTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMonedaTransferencia() {
        return monedaTransferencia;
    }

    /**
     * Sets the value of the monedaTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMonedaTransferencia(JAXBElement<String> value) {
        this.monedaTransferencia = value;
    }

    /**
     * Gets the value of the nroCtaCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCtaCredito() {
        return nroCtaCredito;
    }

    /**
     * Sets the value of the nroCtaCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCtaCredito(JAXBElement<String> value) {
        this.nroCtaCredito = value;
    }

    /**
     * Gets the value of the nroCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCuentaDebito() {
        return nroCuentaDebito;
    }

    /**
     * Sets the value of the nroCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCuentaDebito(JAXBElement<String> value) {
        this.nroCuentaDebito = value;
    }

    /**
     * Gets the value of the referenciaUnivoca property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReferenciaUnivoca() {
        return referenciaUnivoca;
    }

    /**
     * Sets the value of the referenciaUnivoca property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReferenciaUnivoca(JAXBElement<String> value) {
        this.referenciaUnivoca = value;
    }

    /**
     * Gets the value of the sucursalCtaCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalCtaCredito() {
        return sucursalCtaCredito;
    }

    /**
     * Sets the value of the sucursalCtaCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalCtaCredito(JAXBElement<String> value) {
        this.sucursalCtaCredito = value;
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

    /**
     * Gets the value of the tipoEjecucion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoEjecucion() {
        return tipoEjecucion;
    }

    /**
     * Sets the value of the tipoEjecucion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoEjecucion(JAXBElement<String> value) {
        this.tipoEjecucion = value;
    }

    /**
     * Gets the value of the tipoTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoTransferencia() {
        return tipoTransferencia;
    }

    /**
     * Sets the value of the tipoTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoTransferencia(JAXBElement<String> value) {
        this.tipoTransferencia = value;
    }

}

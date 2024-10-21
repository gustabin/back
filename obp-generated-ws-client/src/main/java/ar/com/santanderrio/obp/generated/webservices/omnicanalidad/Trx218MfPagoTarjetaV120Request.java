
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx218MfPagoTarjetaV120Request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx218MfPagoTarjetaV120Request">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestContactBase">
 *       &lt;sequence>
 *         &lt;element name="CantidadCheques" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoMoneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoTitularidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImportePago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiceSinonimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ModoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MomentoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuentaTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroReferenciaOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroSobre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroSucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OperacionReversa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoEjecucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx218MfPagoTarjetaV120Request", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", propOrder = {
    "cantidadCheques",
    "codigoMoneda",
    "codigoTitularidad",
    "idOperacion",
    "importePago",
    "indiceSinonimo",
    "modoPago",
    "momentoPago",
    "numeroCuentaDebito",
    "numeroCuentaTarjeta",
    "numeroReferenciaOrigen",
    "numeroSobre",
    "numeroSucursal",
    "numeroTarjeta",
    "operacionReversa",
    "sucursalCuentaDebito",
    "tipoCuentaDebito",
    "tipoEjecucion",
    "tipoPago",
    "tipoTarjeta"
})
public class Trx218MfPagoTarjetaV120Request
    extends RequestContactBase
{

    @XmlElementRef(name = "CantidadCheques", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> cantidadCheques;
    @XmlElementRef(name = "CodigoMoneda", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> codigoMoneda;
    @XmlElementRef(name = "CodigoTitularidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> codigoTitularidad;
    @XmlElementRef(name = "IdOperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> idOperacion;
    @XmlElementRef(name = "ImportePago", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> importePago;
    @XmlElementRef(name = "IndiceSinonimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> indiceSinonimo;
    @XmlElementRef(name = "ModoPago", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> modoPago;
    @XmlElementRef(name = "MomentoPago", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> momentoPago;
    @XmlElementRef(name = "NumeroCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> numeroCuentaDebito;
    @XmlElementRef(name = "NumeroCuentaTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> numeroCuentaTarjeta;
    @XmlElementRef(name = "NumeroReferenciaOrigen", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> numeroReferenciaOrigen;
    @XmlElementRef(name = "NumeroSobre", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> numeroSobre;
    @XmlElementRef(name = "NumeroSucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> numeroSucursal;
    @XmlElementRef(name = "NumeroTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> numeroTarjeta;
    @XmlElementRef(name = "OperacionReversa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> operacionReversa;
    @XmlElementRef(name = "SucursalCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCuentaDebito;
    @XmlElementRef(name = "TipoCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuentaDebito;
    @XmlElementRef(name = "TipoEjecucion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> tipoEjecucion;
    @XmlElementRef(name = "TipoPago", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> tipoPago;
    @XmlElementRef(name = "TipoTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> tipoTarjeta;

    /**
     * Gets the value of the cantidadCheques property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantidadCheques() {
        return cantidadCheques;
    }

    /**
     * Sets the value of the cantidadCheques property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantidadCheques(JAXBElement<String> value) {
        this.cantidadCheques = value;
    }

    /**
     * Gets the value of the codigoMoneda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoMoneda() {
        return codigoMoneda;
    }

    /**
     * Sets the value of the codigoMoneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoMoneda(JAXBElement<String> value) {
        this.codigoMoneda = value;
    }

    /**
     * Gets the value of the codigoTitularidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoTitularidad() {
        return codigoTitularidad;
    }

    /**
     * Sets the value of the codigoTitularidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoTitularidad(JAXBElement<String> value) {
        this.codigoTitularidad = value;
    }

    /**
     * Gets the value of the idOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdOperacion() {
        return idOperacion;
    }

    /**
     * Sets the value of the idOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdOperacion(JAXBElement<String> value) {
        this.idOperacion = value;
    }

    /**
     * Gets the value of the importePago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImportePago() {
        return importePago;
    }

    /**
     * Sets the value of the importePago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImportePago(JAXBElement<String> value) {
        this.importePago = value;
    }

    /**
     * Gets the value of the indiceSinonimo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndiceSinonimo() {
        return indiceSinonimo;
    }

    /**
     * Sets the value of the indiceSinonimo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndiceSinonimo(JAXBElement<String> value) {
        this.indiceSinonimo = value;
    }

    /**
     * Gets the value of the modoPago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getModoPago() {
        return modoPago;
    }

    /**
     * Sets the value of the modoPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setModoPago(JAXBElement<String> value) {
        this.modoPago = value;
    }

    /**
     * Gets the value of the momentoPago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMomentoPago() {
        return momentoPago;
    }

    /**
     * Sets the value of the momentoPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMomentoPago(JAXBElement<String> value) {
        this.momentoPago = value;
    }

    /**
     * Gets the value of the numeroCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCuentaDebito() {
        return numeroCuentaDebito;
    }

    /**
     * Sets the value of the numeroCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCuentaDebito(JAXBElement<String> value) {
        this.numeroCuentaDebito = value;
    }

    /**
     * Gets the value of the numeroCuentaTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCuentaTarjeta() {
        return numeroCuentaTarjeta;
    }

    /**
     * Sets the value of the numeroCuentaTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCuentaTarjeta(JAXBElement<String> value) {
        this.numeroCuentaTarjeta = value;
    }

    /**
     * Gets the value of the numeroReferenciaOrigen property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroReferenciaOrigen() {
        return numeroReferenciaOrigen;
    }

    /**
     * Sets the value of the numeroReferenciaOrigen property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroReferenciaOrigen(JAXBElement<String> value) {
        this.numeroReferenciaOrigen = value;
    }

    /**
     * Gets the value of the numeroSobre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroSobre() {
        return numeroSobre;
    }

    /**
     * Sets the value of the numeroSobre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroSobre(JAXBElement<String> value) {
        this.numeroSobre = value;
    }

    /**
     * Gets the value of the numeroSucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroSucursal() {
        return numeroSucursal;
    }

    /**
     * Sets the value of the numeroSucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroSucursal(JAXBElement<String> value) {
        this.numeroSucursal = value;
    }

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
        this.numeroTarjeta = value;
    }

    /**
     * Gets the value of the operacionReversa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOperacionReversa() {
        return operacionReversa;
    }

    /**
     * Sets the value of the operacionReversa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOperacionReversa(JAXBElement<String> value) {
        this.operacionReversa = value;
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
     * Gets the value of the tipoPago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoPago() {
        return tipoPago;
    }

    /**
     * Sets the value of the tipoPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoPago(JAXBElement<String> value) {
        this.tipoPago = value;
    }

    /**
     * Gets the value of the tipoTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * Sets the value of the tipoTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoTarjeta(JAXBElement<String> value) {
        this.tipoTarjeta = value;
    }

}

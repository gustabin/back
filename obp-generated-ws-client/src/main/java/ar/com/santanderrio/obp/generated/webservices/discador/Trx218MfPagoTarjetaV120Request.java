
package ar.com.santanderrio.obp.generated.webservices.discador;

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
 *         &lt;element name="CodigoEmpresaVisa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoMoneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoTitularidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CotizacionCompra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CotizacionVenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DenominacionCuentaVisa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DenominacionEmpresaVisa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaDeVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteDolares" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImportePago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiceSinonimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ModoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MomentoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCuentaBancoDolares" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuentaTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroReferenciaOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroSobre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroSucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NupUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OperacionReversa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Producto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoDolares" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoPesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalBancoDolares" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuentaBancoDolares" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "codigoEmpresaVisa",
    "codigoMoneda",
    "codigoTitularidad",
    "cotizacionCompra",
    "cotizacionVenta",
    "denominacionCuentaVisa",
    "denominacionEmpresaVisa",
    "fechaDeVencimiento",
    "idOperacion",
    "importeDolares",
    "importePago",
    "indiceSinonimo",
    "modoPago",
    "momentoPago",
    "nombreUsuario",
    "nroCuentaBancoDolares",
    "numeroCuentaDebito",
    "numeroCuentaTarjeta",
    "numeroReferenciaOrigen",
    "numeroSobre",
    "numeroSucursal",
    "numeroTarjeta",
    "nupUsuario",
    "operacionReversa",
    "producto",
    "saldoDolares",
    "saldoPesos",
    "sucursalBancoDolares",
    "sucursalCuentaDebito",
    "tipoCuentaBancoDolares",
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
    @XmlElementRef(name = "CodigoEmpresaVisa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> codigoEmpresaVisa;
    @XmlElementRef(name = "CodigoMoneda", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> codigoMoneda;
    @XmlElementRef(name = "CodigoTitularidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> codigoTitularidad;
    @XmlElementRef(name = "CotizacionCompra", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> cotizacionCompra;
    @XmlElementRef(name = "CotizacionVenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> cotizacionVenta;
    @XmlElementRef(name = "DenominacionCuentaVisa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> denominacionCuentaVisa;
    @XmlElementRef(name = "DenominacionEmpresaVisa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> denominacionEmpresaVisa;
    @XmlElementRef(name = "FechaDeVencimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> fechaDeVencimiento;
    @XmlElementRef(name = "IdOperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> idOperacion;
    @XmlElementRef(name = "ImporteDolares", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> importeDolares;
    @XmlElementRef(name = "ImportePago", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> importePago;
    @XmlElementRef(name = "IndiceSinonimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> indiceSinonimo;
    @XmlElementRef(name = "ModoPago", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> modoPago;
    @XmlElementRef(name = "MomentoPago", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> momentoPago;
    @XmlElementRef(name = "NombreUsuario", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> nombreUsuario;
    @XmlElementRef(name = "NroCuentaBancoDolares", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> nroCuentaBancoDolares;
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
    @XmlElementRef(name = "NupUsuario", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> nupUsuario;
    @XmlElementRef(name = "OperacionReversa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> operacionReversa;
    @XmlElementRef(name = "Producto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> producto;
    @XmlElementRef(name = "SaldoDolares", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> saldoDolares;
    @XmlElementRef(name = "SaldoPesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> saldoPesos;
    @XmlElementRef(name = "SucursalBancoDolares", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> sucursalBancoDolares;
    @XmlElementRef(name = "SucursalCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCuentaDebito;
    @XmlElementRef(name = "TipoCuentaBancoDolares", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuentaBancoDolares;
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
     * Gets the value of the codigoEmpresaVisa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoEmpresaVisa() {
        return codigoEmpresaVisa;
    }

    /**
     * Sets the value of the codigoEmpresaVisa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoEmpresaVisa(JAXBElement<String> value) {
        this.codigoEmpresaVisa = value;
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
     * Gets the value of the cotizacionCompra property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCotizacionCompra() {
        return cotizacionCompra;
    }

    /**
     * Sets the value of the cotizacionCompra property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCotizacionCompra(JAXBElement<String> value) {
        this.cotizacionCompra = value;
    }

    /**
     * Gets the value of the cotizacionVenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCotizacionVenta() {
        return cotizacionVenta;
    }

    /**
     * Sets the value of the cotizacionVenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCotizacionVenta(JAXBElement<String> value) {
        this.cotizacionVenta = value;
    }

    /**
     * Gets the value of the denominacionCuentaVisa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDenominacionCuentaVisa() {
        return denominacionCuentaVisa;
    }

    /**
     * Sets the value of the denominacionCuentaVisa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDenominacionCuentaVisa(JAXBElement<String> value) {
        this.denominacionCuentaVisa = value;
    }

    /**
     * Gets the value of the denominacionEmpresaVisa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDenominacionEmpresaVisa() {
        return denominacionEmpresaVisa;
    }

    /**
     * Sets the value of the denominacionEmpresaVisa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDenominacionEmpresaVisa(JAXBElement<String> value) {
        this.denominacionEmpresaVisa = value;
    }

    /**
     * Gets the value of the fechaDeVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    /**
     * Sets the value of the fechaDeVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaDeVencimiento(JAXBElement<String> value) {
        this.fechaDeVencimiento = value;
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
     * Gets the value of the importeDolares property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteDolares() {
        return importeDolares;
    }

    /**
     * Sets the value of the importeDolares property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteDolares(JAXBElement<String> value) {
        this.importeDolares = value;
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
     * Gets the value of the nroCuentaBancoDolares property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCuentaBancoDolares() {
        return nroCuentaBancoDolares;
    }

    /**
     * Sets the value of the nroCuentaBancoDolares property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCuentaBancoDolares(JAXBElement<String> value) {
        this.nroCuentaBancoDolares = value;
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
     * Gets the value of the nupUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNupUsuario() {
        return nupUsuario;
    }

    /**
     * Sets the value of the nupUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNupUsuario(JAXBElement<String> value) {
        this.nupUsuario = value;
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
     * Gets the value of the saldoDolares property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoDolares() {
        return saldoDolares;
    }

    /**
     * Sets the value of the saldoDolares property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoDolares(JAXBElement<String> value) {
        this.saldoDolares = value;
    }

    /**
     * Gets the value of the saldoPesos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoPesos() {
        return saldoPesos;
    }

    /**
     * Sets the value of the saldoPesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoPesos(JAXBElement<String> value) {
        this.saldoPesos = value;
    }

    /**
     * Gets the value of the sucursalBancoDolares property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalBancoDolares() {
        return sucursalBancoDolares;
    }

    /**
     * Sets the value of the sucursalBancoDolares property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalBancoDolares(JAXBElement<String> value) {
        this.sucursalBancoDolares = value;
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
     * Gets the value of the tipoCuentaBancoDolares property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCuentaBancoDolares() {
        return tipoCuentaBancoDolares;
    }

    /**
     * Sets the value of the tipoCuentaBancoDolares property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCuentaBancoDolares(JAXBElement<String> value) {
        this.tipoCuentaBancoDolares = value;
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

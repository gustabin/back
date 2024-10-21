
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdentificacionDelClienteProductResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentificacionDelClienteProductResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="CbuAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClaseCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClasePaquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoAplicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoPaquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoTitularidadAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContratoAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DepositosEfectivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstadoTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GrupoAfinidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorJerarquiaCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MonedaAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroSucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroSucursalAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroTarjetaAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroPaquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OficinaAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrdenFirmante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductoAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoPorPonformar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SignoSaldoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SignoSaldoPorConformar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubproductoAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalPaquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoModoOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentificacionDelClienteProductResponse", propOrder = {
    "cbuAltair",
    "claseCuenta",
    "clasePaquete",
    "codigoAplicacion",
    "codigoPaquete",
    "codigoTitularidadAltair",
    "contratoAltair",
    "depositosEfectivo",
    "estadoTarjeta",
    "grupoAfinidad",
    "indicadorJerarquiaCuenta",
    "monedaAltair",
    "nroCuenta",
    "nroSucursal",
    "nroSucursalAltair",
    "nroTarjetaAltair",
    "numeroPaquete",
    "oficinaAltair",
    "ordenFirmante",
    "productoAltair",
    "saldoCuenta",
    "saldoPorPonformar",
    "signoSaldoCuenta",
    "signoSaldoPorConformar",
    "subproductoAltair",
    "sucursalPaquete",
    "tipoCuenta",
    "tipoModoOperacion"
})
public class IdentificacionDelClienteProductResponse
    extends MappingModelBase
{

    @XmlElementRef(name = "CbuAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> cbuAltair;
    @XmlElementRef(name = "ClaseCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> claseCuenta;
    @XmlElementRef(name = "ClasePaquete", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> clasePaquete;
    @XmlElementRef(name = "CodigoAplicacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> codigoAplicacion;
    @XmlElementRef(name = "CodigoPaquete", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> codigoPaquete;
    @XmlElementRef(name = "CodigoTitularidadAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> codigoTitularidadAltair;
    @XmlElementRef(name = "ContratoAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> contratoAltair;
    @XmlElementRef(name = "DepositosEfectivo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> depositosEfectivo;
    @XmlElementRef(name = "EstadoTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> estadoTarjeta;
    @XmlElementRef(name = "GrupoAfinidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> grupoAfinidad;
    @XmlElementRef(name = "IndicadorJerarquiaCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorJerarquiaCuenta;
    @XmlElementRef(name = "MonedaAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> monedaAltair;
    @XmlElementRef(name = "NroCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nroCuenta;
    @XmlElementRef(name = "NroSucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nroSucursal;
    @XmlElementRef(name = "NroSucursalAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nroSucursalAltair;
    @XmlElementRef(name = "NroTarjetaAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nroTarjetaAltair;
    @XmlElementRef(name = "NumeroPaquete", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> numeroPaquete;
    @XmlElementRef(name = "OficinaAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> oficinaAltair;
    @XmlElementRef(name = "OrdenFirmante", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> ordenFirmante;
    @XmlElementRef(name = "ProductoAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> productoAltair;
    @XmlElementRef(name = "SaldoCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> saldoCuenta;
    @XmlElementRef(name = "SaldoPorPonformar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> saldoPorPonformar;
    @XmlElementRef(name = "SignoSaldoCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> signoSaldoCuenta;
    @XmlElementRef(name = "SignoSaldoPorConformar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> signoSaldoPorConformar;
    @XmlElementRef(name = "SubproductoAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> subproductoAltair;
    @XmlElementRef(name = "SucursalPaquete", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> sucursalPaquete;
    @XmlElementRef(name = "TipoCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuenta;
    @XmlElementRef(name = "TipoModoOperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> tipoModoOperacion;

    /**
     * Gets the value of the cbuAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCbuAltair() {
        return cbuAltair;
    }

    /**
     * Sets the value of the cbuAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCbuAltair(JAXBElement<String> value) {
        this.cbuAltair = value;
    }

    /**
     * Gets the value of the claseCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClaseCuenta() {
        return claseCuenta;
    }

    /**
     * Sets the value of the claseCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClaseCuenta(JAXBElement<String> value) {
        this.claseCuenta = value;
    }

    /**
     * Gets the value of the clasePaquete property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClasePaquete() {
        return clasePaquete;
    }

    /**
     * Sets the value of the clasePaquete property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClasePaquete(JAXBElement<String> value) {
        this.clasePaquete = value;
    }

    /**
     * Gets the value of the codigoAplicacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoAplicacion() {
        return codigoAplicacion;
    }

    /**
     * Sets the value of the codigoAplicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoAplicacion(JAXBElement<String> value) {
        this.codigoAplicacion = value;
    }

    /**
     * Gets the value of the codigoPaquete property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoPaquete() {
        return codigoPaquete;
    }

    /**
     * Sets the value of the codigoPaquete property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoPaquete(JAXBElement<String> value) {
        this.codigoPaquete = value;
    }

    /**
     * Gets the value of the codigoTitularidadAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoTitularidadAltair() {
        return codigoTitularidadAltair;
    }

    /**
     * Sets the value of the codigoTitularidadAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoTitularidadAltair(JAXBElement<String> value) {
        this.codigoTitularidadAltair = value;
    }

    /**
     * Gets the value of the contratoAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getContratoAltair() {
        return contratoAltair;
    }

    /**
     * Sets the value of the contratoAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setContratoAltair(JAXBElement<String> value) {
        this.contratoAltair = value;
    }

    /**
     * Gets the value of the depositosEfectivo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositosEfectivo() {
        return depositosEfectivo;
    }

    /**
     * Sets the value of the depositosEfectivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositosEfectivo(JAXBElement<String> value) {
        this.depositosEfectivo = value;
    }

    /**
     * Gets the value of the estadoTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoTarjeta() {
        return estadoTarjeta;
    }

    /**
     * Sets the value of the estadoTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoTarjeta(JAXBElement<String> value) {
        this.estadoTarjeta = value;
    }

    /**
     * Gets the value of the grupoAfinidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGrupoAfinidad() {
        return grupoAfinidad;
    }

    /**
     * Sets the value of the grupoAfinidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGrupoAfinidad(JAXBElement<String> value) {
        this.grupoAfinidad = value;
    }

    /**
     * Gets the value of the indicadorJerarquiaCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorJerarquiaCuenta() {
        return indicadorJerarquiaCuenta;
    }

    /**
     * Sets the value of the indicadorJerarquiaCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorJerarquiaCuenta(JAXBElement<String> value) {
        this.indicadorJerarquiaCuenta = value;
    }

    /**
     * Gets the value of the monedaAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMonedaAltair() {
        return monedaAltair;
    }

    /**
     * Sets the value of the monedaAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMonedaAltair(JAXBElement<String> value) {
        this.monedaAltair = value;
    }

    /**
     * Gets the value of the nroCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCuenta() {
        return nroCuenta;
    }

    /**
     * Sets the value of the nroCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCuenta(JAXBElement<String> value) {
        this.nroCuenta = value;
    }

    /**
     * Gets the value of the nroSucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroSucursal() {
        return nroSucursal;
    }

    /**
     * Sets the value of the nroSucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroSucursal(JAXBElement<String> value) {
        this.nroSucursal = value;
    }

    /**
     * Gets the value of the nroSucursalAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroSucursalAltair() {
        return nroSucursalAltair;
    }

    /**
     * Sets the value of the nroSucursalAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroSucursalAltair(JAXBElement<String> value) {
        this.nroSucursalAltair = value;
    }

    /**
     * Gets the value of the nroTarjetaAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroTarjetaAltair() {
        return nroTarjetaAltair;
    }

    /**
     * Sets the value of the nroTarjetaAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroTarjetaAltair(JAXBElement<String> value) {
        this.nroTarjetaAltair = value;
    }

    /**
     * Gets the value of the numeroPaquete property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroPaquete() {
        return numeroPaquete;
    }

    /**
     * Sets the value of the numeroPaquete property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroPaquete(JAXBElement<String> value) {
        this.numeroPaquete = value;
    }

    /**
     * Gets the value of the oficinaAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOficinaAltair() {
        return oficinaAltair;
    }

    /**
     * Sets the value of the oficinaAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOficinaAltair(JAXBElement<String> value) {
        this.oficinaAltair = value;
    }

    /**
     * Gets the value of the ordenFirmante property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrdenFirmante() {
        return ordenFirmante;
    }

    /**
     * Sets the value of the ordenFirmante property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrdenFirmante(JAXBElement<String> value) {
        this.ordenFirmante = value;
    }

    /**
     * Gets the value of the productoAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProductoAltair() {
        return productoAltair;
    }

    /**
     * Sets the value of the productoAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProductoAltair(JAXBElement<String> value) {
        this.productoAltair = value;
    }

    /**
     * Gets the value of the saldoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoCuenta() {
        return saldoCuenta;
    }

    /**
     * Sets the value of the saldoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoCuenta(JAXBElement<String> value) {
        this.saldoCuenta = value;
    }

    /**
     * Gets the value of the saldoPorPonformar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoPorPonformar() {
        return saldoPorPonformar;
    }

    /**
     * Sets the value of the saldoPorPonformar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoPorPonformar(JAXBElement<String> value) {
        this.saldoPorPonformar = value;
    }

    /**
     * Gets the value of the signoSaldoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSignoSaldoCuenta() {
        return signoSaldoCuenta;
    }

    /**
     * Sets the value of the signoSaldoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSignoSaldoCuenta(JAXBElement<String> value) {
        this.signoSaldoCuenta = value;
    }

    /**
     * Gets the value of the signoSaldoPorConformar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSignoSaldoPorConformar() {
        return signoSaldoPorConformar;
    }

    /**
     * Sets the value of the signoSaldoPorConformar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSignoSaldoPorConformar(JAXBElement<String> value) {
        this.signoSaldoPorConformar = value;
    }

    /**
     * Gets the value of the subproductoAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubproductoAltair() {
        return subproductoAltair;
    }

    /**
     * Sets the value of the subproductoAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubproductoAltair(JAXBElement<String> value) {
        this.subproductoAltair = value;
    }

    /**
     * Gets the value of the sucursalPaquete property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalPaquete() {
        return sucursalPaquete;
    }

    /**
     * Sets the value of the sucursalPaquete property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalPaquete(JAXBElement<String> value) {
        this.sucursalPaquete = value;
    }

    /**
     * Gets the value of the tipoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the value of the tipoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCuenta(JAXBElement<String> value) {
        this.tipoCuenta = value;
    }

    /**
     * Gets the value of the tipoModoOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoModoOperacion() {
        return tipoModoOperacion;
    }

    /**
     * Sets the value of the tipoModoOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoModoOperacion(JAXBElement<String> value) {
        this.tipoModoOperacion = value;
    }

}

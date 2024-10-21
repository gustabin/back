
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrxIdentificacionClienteProductResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrxIdentificacionClienteProductResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="cbu_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clase_cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clase_paquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_aplicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_paquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_titularidad_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contrato_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depositos_efectivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado_tarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indicador_jerarquia_cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="moneda_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nro_cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nro_sucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nro_sucursal_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nro_tarjeta_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numero_paquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oficina_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orden_firmante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="producto_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saldo_cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saldo_por_conformar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signo_saldo_cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signo_saldo_por_conformar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subproducto_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucursal_paquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_modo_operacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrxIdentificacionClienteProductResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", propOrder = {
    "cbuAltair",
    "claseCuenta",
    "clasePaquete",
    "codigoAplicacion",
    "codigoPaquete",
    "codigoTitularidadAltair",
    "contratoAltair",
    "depositosEfectivo",
    "estadoTarjeta",
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
    "saldoPorConformar",
    "signoSaldoCuenta",
    "signoSaldoPorConformar",
    "subproductoAltair",
    "sucursalPaquete",
    "tipoCuenta",
    "tipoModoOperacion"
})
@XmlSeeAlso({
    Trx002IdentificacionDelClienteProductResponse.class,
    Trx602IdentificacionDelClienteProductResponse.class,
    Trx802IdentificacionDelClienteProductResponse.class
})
public class TrxIdentificacionClienteProductResponse
    extends MappingModelBase
{

    @XmlElementRef(name = "cbu_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> cbuAltair;
    @XmlElementRef(name = "clase_cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> claseCuenta;
    @XmlElementRef(name = "clase_paquete", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> clasePaquete;
    @XmlElementRef(name = "codigo_aplicacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> codigoAplicacion;
    @XmlElementRef(name = "codigo_paquete", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> codigoPaquete;
    @XmlElementRef(name = "codigo_titularidad_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> codigoTitularidadAltair;
    @XmlElementRef(name = "contrato_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> contratoAltair;
    @XmlElementRef(name = "depositos_efectivo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> depositosEfectivo;
    @XmlElementRef(name = "estado_tarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> estadoTarjeta;
    @XmlElementRef(name = "indicador_jerarquia_cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> indicadorJerarquiaCuenta;
    @XmlElementRef(name = "moneda_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> monedaAltair;
    @XmlElementRef(name = "nro_cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> nroCuenta;
    @XmlElementRef(name = "nro_sucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> nroSucursal;
    @XmlElementRef(name = "nro_sucursal_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> nroSucursalAltair;
    @XmlElementRef(name = "nro_tarjeta_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> nroTarjetaAltair;
    @XmlElementRef(name = "numero_paquete", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> numeroPaquete;
    @XmlElementRef(name = "oficina_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> oficinaAltair;
    @XmlElementRef(name = "orden_firmante", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> ordenFirmante;
    @XmlElementRef(name = "producto_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> productoAltair;
    @XmlElementRef(name = "saldo_cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> saldoCuenta;
    @XmlElementRef(name = "saldo_por_conformar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> saldoPorConformar;
    @XmlElementRef(name = "signo_saldo_cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> signoSaldoCuenta;
    @XmlElementRef(name = "signo_saldo_por_conformar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> signoSaldoPorConformar;
    @XmlElementRef(name = "subproducto_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> subproductoAltair;
    @XmlElementRef(name = "sucursal_paquete", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> sucursalPaquete;
    @XmlElementRef(name = "tipo_cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuenta;
    @XmlElementRef(name = "tipo_modo_operacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
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
     * Gets the value of the saldoPorConformar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoPorConformar() {
        return saldoPorConformar;
    }

    /**
     * Sets the value of the saldoPorConformar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoPorConformar(JAXBElement<String> value) {
        this.saldoPorConformar = value;
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

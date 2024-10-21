
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.MappingModelBase;
import ar.com.santanderrio.obp.generated.webservices.discador.Trx201IdentificacionDeLlamadaProductoResponse;


/**
 * <p>Java class for IdentificacionDeLlamadaProductoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentificacionDeLlamadaProductoResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="CbuAltairIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClaseCuentaIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClasePaqueteIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoAplicacionIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoPaqueteIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoTitularidadAltairIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContratoAltairIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DepositosEfectivoIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstadoTarjetaIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorJerarquiaCuentaIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MonedaAltairIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCuentaIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroSucursalAltairIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroSucursalIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroTarjetaAltairIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroPaqueteIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OficinaAltairIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrdenFirmanteIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductoAltairIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoCuentaIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoPorConformarIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SignoSaldoCuentaIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SignoSaldoPorConformarIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubproductoAltairIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalPaqueteIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuentaIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoModoOperacionIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentificacionDeLlamadaProductoResponse", propOrder = {
    "cbuAltairIdentificacion",
    "claseCuentaIdentificacion",
    "clasePaqueteIdentificacion",
    "codigoAplicacionIdentificacion",
    "codigoPaqueteIdentificacion",
    "codigoTitularidadAltairIdentificacion",
    "contratoAltairIdentificacion",
    "depositosEfectivoIdentificacion",
    "estadoTarjetaIdentificacion",
    "indicadorJerarquiaCuentaIdentificacion",
    "monedaAltairIdentificacion",
    "nroCuentaIdentificacion",
    "nroSucursalAltairIdentificacion",
    "nroSucursalIdentificacion",
    "nroTarjetaAltairIdentificacion",
    "numeroPaqueteIdentificacion",
    "oficinaAltairIdentificacion",
    "ordenFirmanteIdentificacion",
    "productoAltairIdentificacion",
    "saldoCuentaIdentificacion",
    "saldoPorConformarIdentificacion",
    "signoSaldoCuentaIdentificacion",
    "signoSaldoPorConformarIdentificacion",
    "subproductoAltairIdentificacion",
    "sucursalPaqueteIdentificacion",
    "tipoCuentaIdentificacion",
    "tipoModoOperacionIdentificacion"
})
@XmlSeeAlso({
    Trx201IdentificacionDeLlamadaProductoResponse.class
})
public class IdentificacionDeLlamadaProductoResponse
    extends MappingModelBase
{

    @XmlElementRef(name = "CbuAltairIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> cbuAltairIdentificacion;
    @XmlElementRef(name = "ClaseCuentaIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> claseCuentaIdentificacion;
    @XmlElementRef(name = "ClasePaqueteIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> clasePaqueteIdentificacion;
    @XmlElementRef(name = "CodigoAplicacionIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> codigoAplicacionIdentificacion;
    @XmlElementRef(name = "CodigoPaqueteIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> codigoPaqueteIdentificacion;
    @XmlElementRef(name = "CodigoTitularidadAltairIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> codigoTitularidadAltairIdentificacion;
    @XmlElementRef(name = "ContratoAltairIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> contratoAltairIdentificacion;
    @XmlElementRef(name = "DepositosEfectivoIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> depositosEfectivoIdentificacion;
    @XmlElementRef(name = "EstadoTarjetaIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> estadoTarjetaIdentificacion;
    @XmlElementRef(name = "IndicadorJerarquiaCuentaIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorJerarquiaCuentaIdentificacion;
    @XmlElementRef(name = "MonedaAltairIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> monedaAltairIdentificacion;
    @XmlElementRef(name = "NroCuentaIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nroCuentaIdentificacion;
    @XmlElementRef(name = "NroSucursalAltairIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nroSucursalAltairIdentificacion;
    @XmlElementRef(name = "NroSucursalIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nroSucursalIdentificacion;
    @XmlElementRef(name = "NroTarjetaAltairIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nroTarjetaAltairIdentificacion;
    @XmlElementRef(name = "NumeroPaqueteIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> numeroPaqueteIdentificacion;
    @XmlElementRef(name = "OficinaAltairIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> oficinaAltairIdentificacion;
    @XmlElementRef(name = "OrdenFirmanteIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> ordenFirmanteIdentificacion;
    @XmlElementRef(name = "ProductoAltairIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> productoAltairIdentificacion;
    @XmlElementRef(name = "SaldoCuentaIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> saldoCuentaIdentificacion;
    @XmlElementRef(name = "SaldoPorConformarIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> saldoPorConformarIdentificacion;
    @XmlElementRef(name = "SignoSaldoCuentaIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> signoSaldoCuentaIdentificacion;
    @XmlElementRef(name = "SignoSaldoPorConformarIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> signoSaldoPorConformarIdentificacion;
    @XmlElementRef(name = "SubproductoAltairIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> subproductoAltairIdentificacion;
    @XmlElementRef(name = "SucursalPaqueteIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> sucursalPaqueteIdentificacion;
    @XmlElementRef(name = "TipoCuentaIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuentaIdentificacion;
    @XmlElementRef(name = "TipoModoOperacionIdentificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> tipoModoOperacionIdentificacion;

    /**
     * Gets the value of the cbuAltairIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCbuAltairIdentificacion() {
        return cbuAltairIdentificacion;
    }

    /**
     * Sets the value of the cbuAltairIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCbuAltairIdentificacion(JAXBElement<String> value) {
        this.cbuAltairIdentificacion = value;
    }

    /**
     * Gets the value of the claseCuentaIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClaseCuentaIdentificacion() {
        return claseCuentaIdentificacion;
    }

    /**
     * Sets the value of the claseCuentaIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClaseCuentaIdentificacion(JAXBElement<String> value) {
        this.claseCuentaIdentificacion = value;
    }

    /**
     * Gets the value of the clasePaqueteIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClasePaqueteIdentificacion() {
        return clasePaqueteIdentificacion;
    }

    /**
     * Sets the value of the clasePaqueteIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClasePaqueteIdentificacion(JAXBElement<String> value) {
        this.clasePaqueteIdentificacion = value;
    }

    /**
     * Gets the value of the codigoAplicacionIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoAplicacionIdentificacion() {
        return codigoAplicacionIdentificacion;
    }

    /**
     * Sets the value of the codigoAplicacionIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoAplicacionIdentificacion(JAXBElement<String> value) {
        this.codigoAplicacionIdentificacion = value;
    }

    /**
     * Gets the value of the codigoPaqueteIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoPaqueteIdentificacion() {
        return codigoPaqueteIdentificacion;
    }

    /**
     * Sets the value of the codigoPaqueteIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoPaqueteIdentificacion(JAXBElement<String> value) {
        this.codigoPaqueteIdentificacion = value;
    }

    /**
     * Gets the value of the codigoTitularidadAltairIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoTitularidadAltairIdentificacion() {
        return codigoTitularidadAltairIdentificacion;
    }

    /**
     * Sets the value of the codigoTitularidadAltairIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoTitularidadAltairIdentificacion(JAXBElement<String> value) {
        this.codigoTitularidadAltairIdentificacion = value;
    }

    /**
     * Gets the value of the contratoAltairIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getContratoAltairIdentificacion() {
        return contratoAltairIdentificacion;
    }

    /**
     * Sets the value of the contratoAltairIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setContratoAltairIdentificacion(JAXBElement<String> value) {
        this.contratoAltairIdentificacion = value;
    }

    /**
     * Gets the value of the depositosEfectivoIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositosEfectivoIdentificacion() {
        return depositosEfectivoIdentificacion;
    }

    /**
     * Sets the value of the depositosEfectivoIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositosEfectivoIdentificacion(JAXBElement<String> value) {
        this.depositosEfectivoIdentificacion = value;
    }

    /**
     * Gets the value of the estadoTarjetaIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoTarjetaIdentificacion() {
        return estadoTarjetaIdentificacion;
    }

    /**
     * Sets the value of the estadoTarjetaIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoTarjetaIdentificacion(JAXBElement<String> value) {
        this.estadoTarjetaIdentificacion = value;
    }

    /**
     * Gets the value of the indicadorJerarquiaCuentaIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorJerarquiaCuentaIdentificacion() {
        return indicadorJerarquiaCuentaIdentificacion;
    }

    /**
     * Sets the value of the indicadorJerarquiaCuentaIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorJerarquiaCuentaIdentificacion(JAXBElement<String> value) {
        this.indicadorJerarquiaCuentaIdentificacion = value;
    }

    /**
     * Gets the value of the monedaAltairIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMonedaAltairIdentificacion() {
        return monedaAltairIdentificacion;
    }

    /**
     * Sets the value of the monedaAltairIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMonedaAltairIdentificacion(JAXBElement<String> value) {
        this.monedaAltairIdentificacion = value;
    }

    /**
     * Gets the value of the nroCuentaIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCuentaIdentificacion() {
        return nroCuentaIdentificacion;
    }

    /**
     * Sets the value of the nroCuentaIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCuentaIdentificacion(JAXBElement<String> value) {
        this.nroCuentaIdentificacion = value;
    }

    /**
     * Gets the value of the nroSucursalAltairIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroSucursalAltairIdentificacion() {
        return nroSucursalAltairIdentificacion;
    }

    /**
     * Sets the value of the nroSucursalAltairIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroSucursalAltairIdentificacion(JAXBElement<String> value) {
        this.nroSucursalAltairIdentificacion = value;
    }

    /**
     * Gets the value of the nroSucursalIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroSucursalIdentificacion() {
        return nroSucursalIdentificacion;
    }

    /**
     * Sets the value of the nroSucursalIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroSucursalIdentificacion(JAXBElement<String> value) {
        this.nroSucursalIdentificacion = value;
    }

    /**
     * Gets the value of the nroTarjetaAltairIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroTarjetaAltairIdentificacion() {
        return nroTarjetaAltairIdentificacion;
    }

    /**
     * Sets the value of the nroTarjetaAltairIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroTarjetaAltairIdentificacion(JAXBElement<String> value) {
        this.nroTarjetaAltairIdentificacion = value;
    }

    /**
     * Gets the value of the numeroPaqueteIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroPaqueteIdentificacion() {
        return numeroPaqueteIdentificacion;
    }

    /**
     * Sets the value of the numeroPaqueteIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroPaqueteIdentificacion(JAXBElement<String> value) {
        this.numeroPaqueteIdentificacion = value;
    }

    /**
     * Gets the value of the oficinaAltairIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOficinaAltairIdentificacion() {
        return oficinaAltairIdentificacion;
    }

    /**
     * Sets the value of the oficinaAltairIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOficinaAltairIdentificacion(JAXBElement<String> value) {
        this.oficinaAltairIdentificacion = value;
    }

    /**
     * Gets the value of the ordenFirmanteIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrdenFirmanteIdentificacion() {
        return ordenFirmanteIdentificacion;
    }

    /**
     * Sets the value of the ordenFirmanteIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrdenFirmanteIdentificacion(JAXBElement<String> value) {
        this.ordenFirmanteIdentificacion = value;
    }

    /**
     * Gets the value of the productoAltairIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProductoAltairIdentificacion() {
        return productoAltairIdentificacion;
    }

    /**
     * Sets the value of the productoAltairIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProductoAltairIdentificacion(JAXBElement<String> value) {
        this.productoAltairIdentificacion = value;
    }

    /**
     * Gets the value of the saldoCuentaIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoCuentaIdentificacion() {
        return saldoCuentaIdentificacion;
    }

    /**
     * Sets the value of the saldoCuentaIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoCuentaIdentificacion(JAXBElement<String> value) {
        this.saldoCuentaIdentificacion = value;
    }

    /**
     * Gets the value of the saldoPorConformarIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoPorConformarIdentificacion() {
        return saldoPorConformarIdentificacion;
    }

    /**
     * Sets the value of the saldoPorConformarIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoPorConformarIdentificacion(JAXBElement<String> value) {
        this.saldoPorConformarIdentificacion = value;
    }

    /**
     * Gets the value of the signoSaldoCuentaIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSignoSaldoCuentaIdentificacion() {
        return signoSaldoCuentaIdentificacion;
    }

    /**
     * Sets the value of the signoSaldoCuentaIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSignoSaldoCuentaIdentificacion(JAXBElement<String> value) {
        this.signoSaldoCuentaIdentificacion = value;
    }

    /**
     * Gets the value of the signoSaldoPorConformarIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSignoSaldoPorConformarIdentificacion() {
        return signoSaldoPorConformarIdentificacion;
    }

    /**
     * Sets the value of the signoSaldoPorConformarIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSignoSaldoPorConformarIdentificacion(JAXBElement<String> value) {
        this.signoSaldoPorConformarIdentificacion = value;
    }

    /**
     * Gets the value of the subproductoAltairIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubproductoAltairIdentificacion() {
        return subproductoAltairIdentificacion;
    }

    /**
     * Sets the value of the subproductoAltairIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubproductoAltairIdentificacion(JAXBElement<String> value) {
        this.subproductoAltairIdentificacion = value;
    }

    /**
     * Gets the value of the sucursalPaqueteIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalPaqueteIdentificacion() {
        return sucursalPaqueteIdentificacion;
    }

    /**
     * Sets the value of the sucursalPaqueteIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalPaqueteIdentificacion(JAXBElement<String> value) {
        this.sucursalPaqueteIdentificacion = value;
    }

    /**
     * Gets the value of the tipoCuentaIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCuentaIdentificacion() {
        return tipoCuentaIdentificacion;
    }

    /**
     * Sets the value of the tipoCuentaIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCuentaIdentificacion(JAXBElement<String> value) {
        this.tipoCuentaIdentificacion = value;
    }

    /**
     * Gets the value of the tipoModoOperacionIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoModoOperacionIdentificacion() {
        return tipoModoOperacionIdentificacion;
    }

    /**
     * Sets the value of the tipoModoOperacionIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoModoOperacionIdentificacion(JAXBElement<String> value) {
        this.tipoModoOperacionIdentificacion = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx619MfPlazoFijoV140Request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx619MfPlazoFijoV140Request">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxChequeraPlazoFijoDebitRequestBase">
 *       &lt;sequence>
 *         &lt;element name="Ceros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoSubproducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoTarifa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoTarifaRenovacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CotizacionCodigoUr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Divisa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntidadCuentaPlazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaAltaPf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormaCancelReversa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormaPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteDebitoReversa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImportePf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorCapitalizaIntereses" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorCertificadoTransferible" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorRenovacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiceSinonimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuentaPlazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroReferenciaOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PeriodoLiquidacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PlazoDias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResponsableImpuesto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoInicUr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecuenciaReversa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SpreadRenovacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCuentaPlazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalRadicacionCertificado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TasaPf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx619MfPlazoFijoV140Request", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", propOrder = {
    "ceros",
    "codigoProducto",
    "codigoSubproducto",
    "codigoTarifa",
    "codigoTarifaRenovacion",
    "cotizacionCodigoUr",
    "divisa",
    "entidadCuentaPlazo",
    "fechaAltaPf",
    "formaCancelReversa",
    "formaPago",
    "idOperacion",
    "importeDebitoReversa",
    "importePf",
    "indicadorCapitalizaIntereses",
    "indicadorCertificadoTransferible",
    "indicadorRenovacion",
    "indiceSinonimo",
    "nio",
    "numeroCuenta",
    "numeroCuentaPlazo",
    "numeroReferenciaOrigen",
    "periodoLiquidacion",
    "plazoDias",
    "responsableImpuesto",
    "saldoInicUr",
    "secuenciaReversa",
    "spreadRenovacion",
    "sucursalCuentaPlazo",
    "sucursalRadicacionCertificado",
    "tasaPf",
    "tipoPf"
})
public class Trx619MfPlazoFijoV140Request
    extends TrxChequeraPlazoFijoDebitRequestBase
{

    @XmlElementRef(name = "Ceros", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> ceros;
    @XmlElementRef(name = "CodigoProducto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> codigoProducto;
    @XmlElementRef(name = "CodigoSubproducto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> codigoSubproducto;
    @XmlElementRef(name = "CodigoTarifa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> codigoTarifa;
    @XmlElementRef(name = "CodigoTarifaRenovacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> codigoTarifaRenovacion;
    @XmlElementRef(name = "CotizacionCodigoUr", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> cotizacionCodigoUr;
    @XmlElementRef(name = "Divisa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> divisa;
    @XmlElementRef(name = "EntidadCuentaPlazo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> entidadCuentaPlazo;
    @XmlElementRef(name = "FechaAltaPf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> fechaAltaPf;
    @XmlElementRef(name = "FormaCancelReversa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> formaCancelReversa;
    @XmlElementRef(name = "FormaPago", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> formaPago;
    @XmlElementRef(name = "IdOperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> idOperacion;
    @XmlElementRef(name = "ImporteDebitoReversa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> importeDebitoReversa;
    @XmlElementRef(name = "ImportePf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> importePf;
    @XmlElementRef(name = "IndicadorCapitalizaIntereses", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> indicadorCapitalizaIntereses;
    @XmlElementRef(name = "IndicadorCertificadoTransferible", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> indicadorCertificadoTransferible;
    @XmlElementRef(name = "IndicadorRenovacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> indicadorRenovacion;
    @XmlElementRef(name = "IndiceSinonimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> indiceSinonimo;
    @XmlElementRef(name = "Nio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> nio;
    @XmlElementRef(name = "NumeroCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> numeroCuenta;
    @XmlElementRef(name = "NumeroCuentaPlazo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> numeroCuentaPlazo;
    @XmlElementRef(name = "NumeroReferenciaOrigen", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> numeroReferenciaOrigen;
    @XmlElementRef(name = "PeriodoLiquidacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> periodoLiquidacion;
    @XmlElementRef(name = "PlazoDias", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> plazoDias;
    @XmlElementRef(name = "ResponsableImpuesto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> responsableImpuesto;
    @XmlElementRef(name = "SaldoInicUr", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> saldoInicUr;
    @XmlElementRef(name = "SecuenciaReversa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> secuenciaReversa;
    @XmlElementRef(name = "SpreadRenovacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> spreadRenovacion;
    @XmlElementRef(name = "SucursalCuentaPlazo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCuentaPlazo;
    @XmlElementRef(name = "SucursalRadicacionCertificado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> sucursalRadicacionCertificado;
    @XmlElementRef(name = "TasaPf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> tasaPf;
    @XmlElementRef(name = "TipoPf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> tipoPf;

    /**
     * Gets the value of the ceros property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCeros() {
        return ceros;
    }

    /**
     * Sets the value of the ceros property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCeros(JAXBElement<String> value) {
        this.ceros = value;
    }

    /**
     * Gets the value of the codigoProducto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoProducto() {
        return codigoProducto;
    }

    /**
     * Sets the value of the codigoProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoProducto(JAXBElement<String> value) {
        this.codigoProducto = value;
    }

    /**
     * Gets the value of the codigoSubproducto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoSubproducto() {
        return codigoSubproducto;
    }

    /**
     * Sets the value of the codigoSubproducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoSubproducto(JAXBElement<String> value) {
        this.codigoSubproducto = value;
    }

    /**
     * Gets the value of the codigoTarifa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoTarifa() {
        return codigoTarifa;
    }

    /**
     * Sets the value of the codigoTarifa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoTarifa(JAXBElement<String> value) {
        this.codigoTarifa = value;
    }

    /**
     * Gets the value of the codigoTarifaRenovacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoTarifaRenovacion() {
        return codigoTarifaRenovacion;
    }

    /**
     * Sets the value of the codigoTarifaRenovacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoTarifaRenovacion(JAXBElement<String> value) {
        this.codigoTarifaRenovacion = value;
    }

    /**
     * Gets the value of the cotizacionCodigoUr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCotizacionCodigoUr() {
        return cotizacionCodigoUr;
    }

    /**
     * Sets the value of the cotizacionCodigoUr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCotizacionCodigoUr(JAXBElement<String> value) {
        this.cotizacionCodigoUr = value;
    }

    /**
     * Gets the value of the divisa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDivisa() {
        return divisa;
    }

    /**
     * Sets the value of the divisa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDivisa(JAXBElement<String> value) {
        this.divisa = value;
    }

    /**
     * Gets the value of the entidadCuentaPlazo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEntidadCuentaPlazo() {
        return entidadCuentaPlazo;
    }

    /**
     * Sets the value of the entidadCuentaPlazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEntidadCuentaPlazo(JAXBElement<String> value) {
        this.entidadCuentaPlazo = value;
    }

    /**
     * Gets the value of the fechaAltaPf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaAltaPf() {
        return fechaAltaPf;
    }

    /**
     * Sets the value of the fechaAltaPf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaAltaPf(JAXBElement<String> value) {
        this.fechaAltaPf = value;
    }

    /**
     * Gets the value of the formaCancelReversa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFormaCancelReversa() {
        return formaCancelReversa;
    }

    /**
     * Sets the value of the formaCancelReversa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFormaCancelReversa(JAXBElement<String> value) {
        this.formaCancelReversa = value;
    }

    /**
     * Gets the value of the formaPago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFormaPago() {
        return formaPago;
    }

    /**
     * Sets the value of the formaPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFormaPago(JAXBElement<String> value) {
        this.formaPago = value;
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
     * Gets the value of the importeDebitoReversa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteDebitoReversa() {
        return importeDebitoReversa;
    }

    /**
     * Sets the value of the importeDebitoReversa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteDebitoReversa(JAXBElement<String> value) {
        this.importeDebitoReversa = value;
    }

    /**
     * Gets the value of the importePf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImportePf() {
        return importePf;
    }

    /**
     * Sets the value of the importePf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImportePf(JAXBElement<String> value) {
        this.importePf = value;
    }

    /**
     * Gets the value of the indicadorCapitalizaIntereses property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorCapitalizaIntereses() {
        return indicadorCapitalizaIntereses;
    }

    /**
     * Sets the value of the indicadorCapitalizaIntereses property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorCapitalizaIntereses(JAXBElement<String> value) {
        this.indicadorCapitalizaIntereses = value;
    }

    /**
     * Gets the value of the indicadorCertificadoTransferible property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorCertificadoTransferible() {
        return indicadorCertificadoTransferible;
    }

    /**
     * Sets the value of the indicadorCertificadoTransferible property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorCertificadoTransferible(JAXBElement<String> value) {
        this.indicadorCertificadoTransferible = value;
    }

    /**
     * Gets the value of the indicadorRenovacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorRenovacion() {
        return indicadorRenovacion;
    }

    /**
     * Sets the value of the indicadorRenovacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorRenovacion(JAXBElement<String> value) {
        this.indicadorRenovacion = value;
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
     * Gets the value of the nio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNio() {
        return nio;
    }

    /**
     * Sets the value of the nio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNio(JAXBElement<String> value) {
        this.nio = value;
    }

    /**
     * Gets the value of the numeroCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Sets the value of the numeroCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCuenta(JAXBElement<String> value) {
        this.numeroCuenta = value;
    }

    /**
     * Gets the value of the numeroCuentaPlazo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCuentaPlazo() {
        return numeroCuentaPlazo;
    }

    /**
     * Sets the value of the numeroCuentaPlazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCuentaPlazo(JAXBElement<String> value) {
        this.numeroCuentaPlazo = value;
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
     * Gets the value of the periodoLiquidacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPeriodoLiquidacion() {
        return periodoLiquidacion;
    }

    /**
     * Sets the value of the periodoLiquidacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPeriodoLiquidacion(JAXBElement<String> value) {
        this.periodoLiquidacion = value;
    }

    /**
     * Gets the value of the plazoDias property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPlazoDias() {
        return plazoDias;
    }

    /**
     * Sets the value of the plazoDias property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPlazoDias(JAXBElement<String> value) {
        this.plazoDias = value;
    }

    /**
     * Gets the value of the responsableImpuesto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getResponsableImpuesto() {
        return responsableImpuesto;
    }

    /**
     * Sets the value of the responsableImpuesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setResponsableImpuesto(JAXBElement<String> value) {
        this.responsableImpuesto = value;
    }

    /**
     * Gets the value of the saldoInicUr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoInicUr() {
        return saldoInicUr;
    }

    /**
     * Sets the value of the saldoInicUr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoInicUr(JAXBElement<String> value) {
        this.saldoInicUr = value;
    }

    /**
     * Gets the value of the secuenciaReversa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSecuenciaReversa() {
        return secuenciaReversa;
    }

    /**
     * Sets the value of the secuenciaReversa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecuenciaReversa(JAXBElement<String> value) {
        this.secuenciaReversa = value;
    }

    /**
     * Gets the value of the spreadRenovacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSpreadRenovacion() {
        return spreadRenovacion;
    }

    /**
     * Sets the value of the spreadRenovacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSpreadRenovacion(JAXBElement<String> value) {
        this.spreadRenovacion = value;
    }

    /**
     * Gets the value of the sucursalCuentaPlazo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalCuentaPlazo() {
        return sucursalCuentaPlazo;
    }

    /**
     * Sets the value of the sucursalCuentaPlazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalCuentaPlazo(JAXBElement<String> value) {
        this.sucursalCuentaPlazo = value;
    }

    /**
     * Gets the value of the sucursalRadicacionCertificado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalRadicacionCertificado() {
        return sucursalRadicacionCertificado;
    }

    /**
     * Sets the value of the sucursalRadicacionCertificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalRadicacionCertificado(JAXBElement<String> value) {
        this.sucursalRadicacionCertificado = value;
    }

    /**
     * Gets the value of the tasaPf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTasaPf() {
        return tasaPf;
    }

    /**
     * Sets the value of the tasaPf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTasaPf(JAXBElement<String> value) {
        this.tasaPf = value;
    }

    /**
     * Gets the value of the tipoPf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoPf() {
        return tipoPf;
    }

    /**
     * Sets the value of the tipoPf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoPf(JAXBElement<String> value) {
        this.tipoPf = value;
    }

}

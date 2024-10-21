
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.MappingModelBase;


/**
 * <p>Java class for ResultCertificadoUvi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultCertificadoUvi">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="CanalApertura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CapitalAjustado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CentroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CentroCuentaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CentroCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoUr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaCreditoAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaDebitoAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaInversion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescDivisa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescPerLiquidacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescTipoPlazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Divisa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DivisaOriginal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntidadAso1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntidadCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntidadCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaProximaLiquidacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImpuPdteRet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImpuReten" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImpuTotSal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImpuestosPf" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfResultImpuestoPfUvi" minOccurs="0"/>
 *         &lt;element name="IndAjuAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndAjuUltLiq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorBloqueo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorCapitalizaIntereses" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorCapitalizaReajustes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorCustodia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorEstado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorRenovacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorTransferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InteresLiquidados" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IntpendaboSal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MontoACobrar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCertificado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroSecuencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PeriodicidadLiquidacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Plazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Producto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoInicial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoInicialUr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecuenciaRenovacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalRadicacionCertificado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TasaReal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TasaTeorica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultCertificadoUvi", propOrder = {
    "canalApertura",
    "capitalAjustado",
    "centroCuenta",
    "centroCuentaCredito",
    "centroCuentaDebito",
    "codigoUr",
    "cuentaCreditoAltair",
    "cuentaDebitoAltair",
    "cuentaInversion",
    "descDivisa",
    "descPerLiquidacion",
    "descTipoPlazo",
    "divisa",
    "divisaOriginal",
    "entidadAso1",
    "entidadCuenta",
    "entidadCuentaDebito",
    "fechaAlta",
    "fechaProximaLiquidacion",
    "fechaVencimiento",
    "impuPdteRet",
    "impuReten",
    "impuTotSal",
    "impuestosPf",
    "indAjuAlta",
    "indAjuUltLiq",
    "indicadorBloqueo",
    "indicadorCapitalizaIntereses",
    "indicadorCapitalizaReajustes",
    "indicadorCustodia",
    "indicadorEstado",
    "indicadorRenovacion",
    "indicadorTransferencia",
    "interesLiquidados",
    "intpendaboSal",
    "montoACobrar",
    "nroCertificado",
    "numeroSecuencia",
    "periodicidadLiquidacion",
    "plazo",
    "producto",
    "saldoInicial",
    "saldoInicialUr",
    "secuenciaRenovacion",
    "subProducto",
    "sucursalRadicacionCertificado",
    "tasaReal",
    "tasaTeorica"
})
public class ResultCertificadoUvi
    extends MappingModelBase
{

    @XmlElementRef(name = "CanalApertura", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> canalApertura;
    @XmlElementRef(name = "CapitalAjustado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> capitalAjustado;
    @XmlElementRef(name = "CentroCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> centroCuenta;
    @XmlElementRef(name = "CentroCuentaCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> centroCuentaCredito;
    @XmlElementRef(name = "CentroCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> centroCuentaDebito;
    @XmlElementRef(name = "CodigoUr", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> codigoUr;
    @XmlElementRef(name = "CuentaCreditoAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> cuentaCreditoAltair;
    @XmlElementRef(name = "CuentaDebitoAltair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> cuentaDebitoAltair;
    @XmlElementRef(name = "CuentaInversion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> cuentaInversion;
    @XmlElementRef(name = "DescDivisa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> descDivisa;
    @XmlElementRef(name = "DescPerLiquidacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> descPerLiquidacion;
    @XmlElementRef(name = "DescTipoPlazo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> descTipoPlazo;
    @XmlElementRef(name = "Divisa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> divisa;
    @XmlElementRef(name = "DivisaOriginal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> divisaOriginal;
    @XmlElementRef(name = "EntidadAso1", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> entidadAso1;
    @XmlElementRef(name = "EntidadCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> entidadCuenta;
    @XmlElementRef(name = "EntidadCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> entidadCuentaDebito;
    @XmlElementRef(name = "FechaAlta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> fechaAlta;
    @XmlElementRef(name = "FechaProximaLiquidacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> fechaProximaLiquidacion;
    @XmlElementRef(name = "FechaVencimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> fechaVencimiento;
    @XmlElementRef(name = "ImpuPdteRet", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> impuPdteRet;
    @XmlElementRef(name = "ImpuReten", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> impuReten;
    @XmlElementRef(name = "ImpuTotSal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> impuTotSal;
    @XmlElementRef(name = "ImpuestosPf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<ArrayOfResultImpuestoPfUvi> impuestosPf;
    @XmlElementRef(name = "IndAjuAlta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indAjuAlta;
    @XmlElementRef(name = "IndAjuUltLiq", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indAjuUltLiq;
    @XmlElementRef(name = "IndicadorBloqueo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorBloqueo;
    @XmlElementRef(name = "IndicadorCapitalizaIntereses", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorCapitalizaIntereses;
    @XmlElementRef(name = "IndicadorCapitalizaReajustes", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorCapitalizaReajustes;
    @XmlElementRef(name = "IndicadorCustodia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorCustodia;
    @XmlElementRef(name = "IndicadorEstado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorEstado;
    @XmlElementRef(name = "IndicadorRenovacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorRenovacion;
    @XmlElementRef(name = "IndicadorTransferencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorTransferencia;
    @XmlElementRef(name = "InteresLiquidados", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> interesLiquidados;
    @XmlElementRef(name = "IntpendaboSal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> intpendaboSal;
    @XmlElementRef(name = "MontoACobrar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> montoACobrar;
    @XmlElementRef(name = "NroCertificado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nroCertificado;
    @XmlElementRef(name = "NumeroSecuencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> numeroSecuencia;
    @XmlElementRef(name = "PeriodicidadLiquidacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> periodicidadLiquidacion;
    @XmlElementRef(name = "Plazo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> plazo;
    @XmlElementRef(name = "Producto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> producto;
    @XmlElementRef(name = "SaldoInicial", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> saldoInicial;
    @XmlElementRef(name = "SaldoInicialUr", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> saldoInicialUr;
    @XmlElementRef(name = "SecuenciaRenovacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> secuenciaRenovacion;
    @XmlElementRef(name = "SubProducto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> subProducto;
    @XmlElementRef(name = "SucursalRadicacionCertificado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> sucursalRadicacionCertificado;
    @XmlElementRef(name = "TasaReal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> tasaReal;
    @XmlElementRef(name = "TasaTeorica", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> tasaTeorica;

    /**
     * Gets the value of the canalApertura property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCanalApertura() {
        return canalApertura;
    }

    /**
     * Sets the value of the canalApertura property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCanalApertura(JAXBElement<String> value) {
        this.canalApertura = value;
    }

    /**
     * Gets the value of the capitalAjustado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCapitalAjustado() {
        return capitalAjustado;
    }

    /**
     * Sets the value of the capitalAjustado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCapitalAjustado(JAXBElement<String> value) {
        this.capitalAjustado = value;
    }

    /**
     * Gets the value of the centroCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCentroCuenta() {
        return centroCuenta;
    }

    /**
     * Sets the value of the centroCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCentroCuenta(JAXBElement<String> value) {
        this.centroCuenta = value;
    }

    /**
     * Gets the value of the centroCuentaCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCentroCuentaCredito() {
        return centroCuentaCredito;
    }

    /**
     * Sets the value of the centroCuentaCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCentroCuentaCredito(JAXBElement<String> value) {
        this.centroCuentaCredito = value;
    }

    /**
     * Gets the value of the centroCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCentroCuentaDebito() {
        return centroCuentaDebito;
    }

    /**
     * Sets the value of the centroCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCentroCuentaDebito(JAXBElement<String> value) {
        this.centroCuentaDebito = value;
    }

    /**
     * Gets the value of the codigoUr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoUr() {
        return codigoUr;
    }

    /**
     * Sets the value of the codigoUr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoUr(JAXBElement<String> value) {
        this.codigoUr = value;
    }

    /**
     * Gets the value of the cuentaCreditoAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaCreditoAltair() {
        return cuentaCreditoAltair;
    }

    /**
     * Sets the value of the cuentaCreditoAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaCreditoAltair(JAXBElement<String> value) {
        this.cuentaCreditoAltair = value;
    }

    /**
     * Gets the value of the cuentaDebitoAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaDebitoAltair() {
        return cuentaDebitoAltair;
    }

    /**
     * Sets the value of the cuentaDebitoAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaDebitoAltair(JAXBElement<String> value) {
        this.cuentaDebitoAltair = value;
    }

    /**
     * Gets the value of the cuentaInversion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaInversion() {
        return cuentaInversion;
    }

    /**
     * Sets the value of the cuentaInversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaInversion(JAXBElement<String> value) {
        this.cuentaInversion = value;
    }

    /**
     * Gets the value of the descDivisa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescDivisa() {
        return descDivisa;
    }

    /**
     * Sets the value of the descDivisa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescDivisa(JAXBElement<String> value) {
        this.descDivisa = value;
    }

    /**
     * Gets the value of the descPerLiquidacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescPerLiquidacion() {
        return descPerLiquidacion;
    }

    /**
     * Sets the value of the descPerLiquidacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescPerLiquidacion(JAXBElement<String> value) {
        this.descPerLiquidacion = value;
    }

    /**
     * Gets the value of the descTipoPlazo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescTipoPlazo() {
        return descTipoPlazo;
    }

    /**
     * Sets the value of the descTipoPlazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescTipoPlazo(JAXBElement<String> value) {
        this.descTipoPlazo = value;
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
     * Gets the value of the divisaOriginal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDivisaOriginal() {
        return divisaOriginal;
    }

    /**
     * Sets the value of the divisaOriginal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDivisaOriginal(JAXBElement<String> value) {
        this.divisaOriginal = value;
    }

    /**
     * Gets the value of the entidadAso1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEntidadAso1() {
        return entidadAso1;
    }

    /**
     * Sets the value of the entidadAso1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEntidadAso1(JAXBElement<String> value) {
        this.entidadAso1 = value;
    }

    /**
     * Gets the value of the entidadCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEntidadCuenta() {
        return entidadCuenta;
    }

    /**
     * Sets the value of the entidadCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEntidadCuenta(JAXBElement<String> value) {
        this.entidadCuenta = value;
    }

    /**
     * Gets the value of the entidadCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEntidadCuentaDebito() {
        return entidadCuentaDebito;
    }

    /**
     * Sets the value of the entidadCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEntidadCuentaDebito(JAXBElement<String> value) {
        this.entidadCuentaDebito = value;
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
     * Gets the value of the fechaProximaLiquidacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaProximaLiquidacion() {
        return fechaProximaLiquidacion;
    }

    /**
     * Sets the value of the fechaProximaLiquidacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaProximaLiquidacion(JAXBElement<String> value) {
        this.fechaProximaLiquidacion = value;
    }

    /**
     * Gets the value of the fechaVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the value of the fechaVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaVencimiento(JAXBElement<String> value) {
        this.fechaVencimiento = value;
    }

    /**
     * Gets the value of the impuPdteRet property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImpuPdteRet() {
        return impuPdteRet;
    }

    /**
     * Sets the value of the impuPdteRet property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImpuPdteRet(JAXBElement<String> value) {
        this.impuPdteRet = value;
    }

    /**
     * Gets the value of the impuReten property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImpuReten() {
        return impuReten;
    }

    /**
     * Sets the value of the impuReten property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImpuReten(JAXBElement<String> value) {
        this.impuReten = value;
    }

    /**
     * Gets the value of the impuTotSal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImpuTotSal() {
        return impuTotSal;
    }

    /**
     * Sets the value of the impuTotSal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImpuTotSal(JAXBElement<String> value) {
        this.impuTotSal = value;
    }

    /**
     * Gets the value of the impuestosPf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfResultImpuestoPfUvi }{@code >}
     *     
     */
    public JAXBElement<ArrayOfResultImpuestoPfUvi> getImpuestosPf() {
        return impuestosPf;
    }

    /**
     * Sets the value of the impuestosPf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfResultImpuestoPfUvi }{@code >}
     *     
     */
    public void setImpuestosPf(JAXBElement<ArrayOfResultImpuestoPfUvi> value) {
        this.impuestosPf = value;
    }

    /**
     * Gets the value of the indAjuAlta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndAjuAlta() {
        return indAjuAlta;
    }

    /**
     * Sets the value of the indAjuAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndAjuAlta(JAXBElement<String> value) {
        this.indAjuAlta = value;
    }

    /**
     * Gets the value of the indAjuUltLiq property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndAjuUltLiq() {
        return indAjuUltLiq;
    }

    /**
     * Sets the value of the indAjuUltLiq property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndAjuUltLiq(JAXBElement<String> value) {
        this.indAjuUltLiq = value;
    }

    /**
     * Gets the value of the indicadorBloqueo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorBloqueo() {
        return indicadorBloqueo;
    }

    /**
     * Sets the value of the indicadorBloqueo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorBloqueo(JAXBElement<String> value) {
        this.indicadorBloqueo = value;
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
     * Gets the value of the indicadorCapitalizaReajustes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorCapitalizaReajustes() {
        return indicadorCapitalizaReajustes;
    }

    /**
     * Sets the value of the indicadorCapitalizaReajustes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorCapitalizaReajustes(JAXBElement<String> value) {
        this.indicadorCapitalizaReajustes = value;
    }

    /**
     * Gets the value of the indicadorCustodia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorCustodia() {
        return indicadorCustodia;
    }

    /**
     * Sets the value of the indicadorCustodia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorCustodia(JAXBElement<String> value) {
        this.indicadorCustodia = value;
    }

    /**
     * Gets the value of the indicadorEstado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorEstado() {
        return indicadorEstado;
    }

    /**
     * Sets the value of the indicadorEstado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorEstado(JAXBElement<String> value) {
        this.indicadorEstado = value;
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
     * Gets the value of the indicadorTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorTransferencia() {
        return indicadorTransferencia;
    }

    /**
     * Sets the value of the indicadorTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorTransferencia(JAXBElement<String> value) {
        this.indicadorTransferencia = value;
    }

    /**
     * Gets the value of the interesLiquidados property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInteresLiquidados() {
        return interesLiquidados;
    }

    /**
     * Sets the value of the interesLiquidados property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInteresLiquidados(JAXBElement<String> value) {
        this.interesLiquidados = value;
    }

    /**
     * Gets the value of the intpendaboSal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIntpendaboSal() {
        return intpendaboSal;
    }

    /**
     * Sets the value of the intpendaboSal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIntpendaboSal(JAXBElement<String> value) {
        this.intpendaboSal = value;
    }

    /**
     * Gets the value of the montoACobrar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMontoACobrar() {
        return montoACobrar;
    }

    /**
     * Sets the value of the montoACobrar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMontoACobrar(JAXBElement<String> value) {
        this.montoACobrar = value;
    }

    /**
     * Gets the value of the nroCertificado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCertificado() {
        return nroCertificado;
    }

    /**
     * Sets the value of the nroCertificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCertificado(JAXBElement<String> value) {
        this.nroCertificado = value;
    }

    /**
     * Gets the value of the numeroSecuencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroSecuencia() {
        return numeroSecuencia;
    }

    /**
     * Sets the value of the numeroSecuencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroSecuencia(JAXBElement<String> value) {
        this.numeroSecuencia = value;
    }

    /**
     * Gets the value of the periodicidadLiquidacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPeriodicidadLiquidacion() {
        return periodicidadLiquidacion;
    }

    /**
     * Sets the value of the periodicidadLiquidacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPeriodicidadLiquidacion(JAXBElement<String> value) {
        this.periodicidadLiquidacion = value;
    }

    /**
     * Gets the value of the plazo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPlazo() {
        return plazo;
    }

    /**
     * Sets the value of the plazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPlazo(JAXBElement<String> value) {
        this.plazo = value;
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
     * Gets the value of the saldoInicial property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoInicial() {
        return saldoInicial;
    }

    /**
     * Sets the value of the saldoInicial property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoInicial(JAXBElement<String> value) {
        this.saldoInicial = value;
    }

    /**
     * Gets the value of the saldoInicialUr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoInicialUr() {
        return saldoInicialUr;
    }

    /**
     * Sets the value of the saldoInicialUr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoInicialUr(JAXBElement<String> value) {
        this.saldoInicialUr = value;
    }

    /**
     * Gets the value of the secuenciaRenovacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSecuenciaRenovacion() {
        return secuenciaRenovacion;
    }

    /**
     * Sets the value of the secuenciaRenovacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecuenciaRenovacion(JAXBElement<String> value) {
        this.secuenciaRenovacion = value;
    }

    /**
     * Gets the value of the subProducto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubProducto() {
        return subProducto;
    }

    /**
     * Sets the value of the subProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubProducto(JAXBElement<String> value) {
        this.subProducto = value;
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
     * Gets the value of the tasaReal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTasaReal() {
        return tasaReal;
    }

    /**
     * Sets the value of the tasaReal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTasaReal(JAXBElement<String> value) {
        this.tasaReal = value;
    }

    /**
     * Gets the value of the tasaTeorica property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTasaTeorica() {
        return tasaTeorica;
    }

    /**
     * Sets the value of the tasaTeorica property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTasaTeorica(JAXBElement<String> value) {
        this.tasaTeorica = value;
    }

}

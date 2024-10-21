
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx211DetTcV130Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx211DetTcV130Response">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="AdelantosDolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdelantosPesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AjusteCreditoDolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AjusteCreditoPesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AjusteDebitoDolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AjusteDebitoPesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CicloTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConsumosDolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConsumosPesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CotizacionComprador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CotizacionVendedor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstadoTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaProximoCierre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaProximoVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaUltimoCierre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaUltimoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaVencimientoLiquidacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormaPagoTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LimiteCompraPesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LimiteFinanciacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PagoMinimoImpago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PagoMinimoPesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PagosDolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PagosPesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoActualDolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoActualPesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoDolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoPesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoUltimoCierreDolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoUltimoCierrePesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx211DetTcV130Response", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", propOrder = {
    "adelantosDolar",
    "adelantosPesos",
    "ajusteCreditoDolar",
    "ajusteCreditoPesos",
    "ajusteDebitoDolar",
    "ajusteDebitoPesos",
    "cicloTarjeta",
    "consumosDolar",
    "consumosPesos",
    "cotizacionComprador",
    "cotizacionVendedor",
    "estadoTarjeta",
    "fechaProximoCierre",
    "fechaProximoVencimiento",
    "fechaUltimoCierre",
    "fechaUltimoPago",
    "fechaVencimientoLiquidacion",
    "formaPagoTarjeta",
    "limiteCompraPesos",
    "limiteFinanciacion",
    "pagoMinimoImpago",
    "pagoMinimoPesos",
    "pagosDolar",
    "pagosPesos",
    "saldoActualDolar",
    "saldoActualPesos",
    "saldoDolar",
    "saldoPesos",
    "saldoUltimoCierreDolar",
    "saldoUltimoCierrePesos"
})
public class Trx211DetTcV130Response
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "AdelantosDolar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> adelantosDolar;
    @XmlElementRef(name = "AdelantosPesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> adelantosPesos;
    @XmlElementRef(name = "AjusteCreditoDolar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> ajusteCreditoDolar;
    @XmlElementRef(name = "AjusteCreditoPesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> ajusteCreditoPesos;
    @XmlElementRef(name = "AjusteDebitoDolar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> ajusteDebitoDolar;
    @XmlElementRef(name = "AjusteDebitoPesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> ajusteDebitoPesos;
    @XmlElementRef(name = "CicloTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> cicloTarjeta;
    @XmlElementRef(name = "ConsumosDolar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> consumosDolar;
    @XmlElementRef(name = "ConsumosPesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> consumosPesos;
    @XmlElementRef(name = "CotizacionComprador", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> cotizacionComprador;
    @XmlElementRef(name = "CotizacionVendedor", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> cotizacionVendedor;
    @XmlElementRef(name = "EstadoTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> estadoTarjeta;
    @XmlElementRef(name = "FechaProximoCierre", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> fechaProximoCierre;
    @XmlElementRef(name = "FechaProximoVencimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> fechaProximoVencimiento;
    @XmlElementRef(name = "FechaUltimoCierre", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> fechaUltimoCierre;
    @XmlElementRef(name = "FechaUltimoPago", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> fechaUltimoPago;
    @XmlElementRef(name = "FechaVencimientoLiquidacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> fechaVencimientoLiquidacion;
    @XmlElementRef(name = "FormaPagoTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> formaPagoTarjeta;
    @XmlElementRef(name = "LimiteCompraPesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> limiteCompraPesos;
    @XmlElementRef(name = "LimiteFinanciacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> limiteFinanciacion;
    @XmlElementRef(name = "PagoMinimoImpago", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> pagoMinimoImpago;
    @XmlElementRef(name = "PagoMinimoPesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> pagoMinimoPesos;
    @XmlElementRef(name = "PagosDolar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> pagosDolar;
    @XmlElementRef(name = "PagosPesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> pagosPesos;
    @XmlElementRef(name = "SaldoActualDolar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> saldoActualDolar;
    @XmlElementRef(name = "SaldoActualPesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> saldoActualPesos;
    @XmlElementRef(name = "SaldoDolar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> saldoDolar;
    @XmlElementRef(name = "SaldoPesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> saldoPesos;
    @XmlElementRef(name = "SaldoUltimoCierreDolar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> saldoUltimoCierreDolar;
    @XmlElementRef(name = "SaldoUltimoCierrePesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> saldoUltimoCierrePesos;

    /**
     * Gets the value of the adelantosDolar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAdelantosDolar() {
        return adelantosDolar;
    }

    /**
     * Sets the value of the adelantosDolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAdelantosDolar(JAXBElement<String> value) {
        this.adelantosDolar = value;
    }

    /**
     * Gets the value of the adelantosPesos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAdelantosPesos() {
        return adelantosPesos;
    }

    /**
     * Sets the value of the adelantosPesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAdelantosPesos(JAXBElement<String> value) {
        this.adelantosPesos = value;
    }

    /**
     * Gets the value of the ajusteCreditoDolar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAjusteCreditoDolar() {
        return ajusteCreditoDolar;
    }

    /**
     * Sets the value of the ajusteCreditoDolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAjusteCreditoDolar(JAXBElement<String> value) {
        this.ajusteCreditoDolar = value;
    }

    /**
     * Gets the value of the ajusteCreditoPesos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAjusteCreditoPesos() {
        return ajusteCreditoPesos;
    }

    /**
     * Sets the value of the ajusteCreditoPesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAjusteCreditoPesos(JAXBElement<String> value) {
        this.ajusteCreditoPesos = value;
    }

    /**
     * Gets the value of the ajusteDebitoDolar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAjusteDebitoDolar() {
        return ajusteDebitoDolar;
    }

    /**
     * Sets the value of the ajusteDebitoDolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAjusteDebitoDolar(JAXBElement<String> value) {
        this.ajusteDebitoDolar = value;
    }

    /**
     * Gets the value of the ajusteDebitoPesos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAjusteDebitoPesos() {
        return ajusteDebitoPesos;
    }

    /**
     * Sets the value of the ajusteDebitoPesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAjusteDebitoPesos(JAXBElement<String> value) {
        this.ajusteDebitoPesos = value;
    }

    /**
     * Gets the value of the cicloTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCicloTarjeta() {
        return cicloTarjeta;
    }

    /**
     * Sets the value of the cicloTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCicloTarjeta(JAXBElement<String> value) {
        this.cicloTarjeta = value;
    }

    /**
     * Gets the value of the consumosDolar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConsumosDolar() {
        return consumosDolar;
    }

    /**
     * Sets the value of the consumosDolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConsumosDolar(JAXBElement<String> value) {
        this.consumosDolar = value;
    }

    /**
     * Gets the value of the consumosPesos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConsumosPesos() {
        return consumosPesos;
    }

    /**
     * Sets the value of the consumosPesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConsumosPesos(JAXBElement<String> value) {
        this.consumosPesos = value;
    }

    /**
     * Gets the value of the cotizacionComprador property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCotizacionComprador() {
        return cotizacionComprador;
    }

    /**
     * Sets the value of the cotizacionComprador property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCotizacionComprador(JAXBElement<String> value) {
        this.cotizacionComprador = value;
    }

    /**
     * Gets the value of the cotizacionVendedor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCotizacionVendedor() {
        return cotizacionVendedor;
    }

    /**
     * Sets the value of the cotizacionVendedor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCotizacionVendedor(JAXBElement<String> value) {
        this.cotizacionVendedor = value;
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
     * Gets the value of the fechaProximoCierre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaProximoCierre() {
        return fechaProximoCierre;
    }

    /**
     * Sets the value of the fechaProximoCierre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaProximoCierre(JAXBElement<String> value) {
        this.fechaProximoCierre = value;
    }

    /**
     * Gets the value of the fechaProximoVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaProximoVencimiento() {
        return fechaProximoVencimiento;
    }

    /**
     * Sets the value of the fechaProximoVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaProximoVencimiento(JAXBElement<String> value) {
        this.fechaProximoVencimiento = value;
    }

    /**
     * Gets the value of the fechaUltimoCierre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaUltimoCierre() {
        return fechaUltimoCierre;
    }

    /**
     * Sets the value of the fechaUltimoCierre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaUltimoCierre(JAXBElement<String> value) {
        this.fechaUltimoCierre = value;
    }

    /**
     * Gets the value of the fechaUltimoPago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    /**
     * Sets the value of the fechaUltimoPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaUltimoPago(JAXBElement<String> value) {
        this.fechaUltimoPago = value;
    }

    /**
     * Gets the value of the fechaVencimientoLiquidacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaVencimientoLiquidacion() {
        return fechaVencimientoLiquidacion;
    }

    /**
     * Sets the value of the fechaVencimientoLiquidacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaVencimientoLiquidacion(JAXBElement<String> value) {
        this.fechaVencimientoLiquidacion = value;
    }

    /**
     * Gets the value of the formaPagoTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFormaPagoTarjeta() {
        return formaPagoTarjeta;
    }

    /**
     * Sets the value of the formaPagoTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFormaPagoTarjeta(JAXBElement<String> value) {
        this.formaPagoTarjeta = value;
    }

    /**
     * Gets the value of the limiteCompraPesos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLimiteCompraPesos() {
        return limiteCompraPesos;
    }

    /**
     * Sets the value of the limiteCompraPesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLimiteCompraPesos(JAXBElement<String> value) {
        this.limiteCompraPesos = value;
    }

    /**
     * Gets the value of the limiteFinanciacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLimiteFinanciacion() {
        return limiteFinanciacion;
    }

    /**
     * Sets the value of the limiteFinanciacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLimiteFinanciacion(JAXBElement<String> value) {
        this.limiteFinanciacion = value;
    }

    /**
     * Gets the value of the pagoMinimoImpago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPagoMinimoImpago() {
        return pagoMinimoImpago;
    }

    /**
     * Sets the value of the pagoMinimoImpago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPagoMinimoImpago(JAXBElement<String> value) {
        this.pagoMinimoImpago = value;
    }

    /**
     * Gets the value of the pagoMinimoPesos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPagoMinimoPesos() {
        return pagoMinimoPesos;
    }

    /**
     * Sets the value of the pagoMinimoPesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPagoMinimoPesos(JAXBElement<String> value) {
        this.pagoMinimoPesos = value;
    }

    /**
     * Gets the value of the pagosDolar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPagosDolar() {
        return pagosDolar;
    }

    /**
     * Sets the value of the pagosDolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPagosDolar(JAXBElement<String> value) {
        this.pagosDolar = value;
    }

    /**
     * Gets the value of the pagosPesos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPagosPesos() {
        return pagosPesos;
    }

    /**
     * Sets the value of the pagosPesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPagosPesos(JAXBElement<String> value) {
        this.pagosPesos = value;
    }

    /**
     * Gets the value of the saldoActualDolar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoActualDolar() {
        return saldoActualDolar;
    }

    /**
     * Sets the value of the saldoActualDolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoActualDolar(JAXBElement<String> value) {
        this.saldoActualDolar = value;
    }

    /**
     * Gets the value of the saldoActualPesos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoActualPesos() {
        return saldoActualPesos;
    }

    /**
     * Sets the value of the saldoActualPesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoActualPesos(JAXBElement<String> value) {
        this.saldoActualPesos = value;
    }

    /**
     * Gets the value of the saldoDolar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoDolar() {
        return saldoDolar;
    }

    /**
     * Sets the value of the saldoDolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoDolar(JAXBElement<String> value) {
        this.saldoDolar = value;
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
     * Gets the value of the saldoUltimoCierreDolar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoUltimoCierreDolar() {
        return saldoUltimoCierreDolar;
    }

    /**
     * Sets the value of the saldoUltimoCierreDolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoUltimoCierreDolar(JAXBElement<String> value) {
        this.saldoUltimoCierreDolar = value;
    }

    /**
     * Gets the value of the saldoUltimoCierrePesos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoUltimoCierrePesos() {
        return saldoUltimoCierrePesos;
    }

    /**
     * Sets the value of the saldoUltimoCierrePesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoUltimoCierrePesos(JAXBElement<String> value) {
        this.saldoUltimoCierrePesos = value;
    }

}

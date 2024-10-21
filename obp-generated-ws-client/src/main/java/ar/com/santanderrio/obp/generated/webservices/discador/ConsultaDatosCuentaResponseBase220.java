
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultaDatosCuentaResponseBase220 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaDatosCuentaResponseBase220">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="ChequesRechazadosArs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChequesRechazadosUsd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaAperturaARS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaAperturaUsd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoCuentaArs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoCuentaUsd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoPorConformarUsd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoporConformarArs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCoberturaCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPosicionamientoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaDatosCuentaResponseBase220", propOrder = {
    "chequesRechazadosArs",
    "chequesRechazadosUsd",
    "fechaAperturaARS",
    "fechaAperturaUsd",
    "saldoCuentaArs",
    "saldoCuentaUsd",
    "saldoPorConformarUsd",
    "saldoporConformarArs",
    "tipoCoberturaCuenta",
    "tipoPosicionamientoCuenta"
})
@XmlSeeAlso({
    Trx108ConsultaDatosCuentaCruResponse.class
})
public class ConsultaDatosCuentaResponseBase220
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "ChequesRechazadosArs", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> chequesRechazadosArs;
    @XmlElementRef(name = "ChequesRechazadosUsd", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> chequesRechazadosUsd;
    @XmlElementRef(name = "FechaAperturaARS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> fechaAperturaARS;
    @XmlElementRef(name = "FechaAperturaUsd", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> fechaAperturaUsd;
    @XmlElementRef(name = "SaldoCuentaArs", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> saldoCuentaArs;
    @XmlElementRef(name = "SaldoCuentaUsd", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> saldoCuentaUsd;
    @XmlElementRef(name = "SaldoPorConformarUsd", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> saldoPorConformarUsd;
    @XmlElementRef(name = "SaldoporConformarArs", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> saldoporConformarArs;
    @XmlElementRef(name = "TipoCoberturaCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> tipoCoberturaCuenta;
    @XmlElementRef(name = "TipoPosicionamientoCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> tipoPosicionamientoCuenta;

    /**
     * Gets the value of the chequesRechazadosArs property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getChequesRechazadosArs() {
        return chequesRechazadosArs;
    }

    /**
     * Sets the value of the chequesRechazadosArs property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setChequesRechazadosArs(JAXBElement<String> value) {
        this.chequesRechazadosArs = value;
    }

    /**
     * Gets the value of the chequesRechazadosUsd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getChequesRechazadosUsd() {
        return chequesRechazadosUsd;
    }

    /**
     * Sets the value of the chequesRechazadosUsd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setChequesRechazadosUsd(JAXBElement<String> value) {
        this.chequesRechazadosUsd = value;
    }

    /**
     * Gets the value of the fechaAperturaARS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaAperturaARS() {
        return fechaAperturaARS;
    }

    /**
     * Sets the value of the fechaAperturaARS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaAperturaARS(JAXBElement<String> value) {
        this.fechaAperturaARS = value;
    }

    /**
     * Gets the value of the fechaAperturaUsd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaAperturaUsd() {
        return fechaAperturaUsd;
    }

    /**
     * Sets the value of the fechaAperturaUsd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaAperturaUsd(JAXBElement<String> value) {
        this.fechaAperturaUsd = value;
    }

    /**
     * Gets the value of the saldoCuentaArs property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoCuentaArs() {
        return saldoCuentaArs;
    }

    /**
     * Sets the value of the saldoCuentaArs property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoCuentaArs(JAXBElement<String> value) {
        this.saldoCuentaArs = value;
    }

    /**
     * Gets the value of the saldoCuentaUsd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoCuentaUsd() {
        return saldoCuentaUsd;
    }

    /**
     * Sets the value of the saldoCuentaUsd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoCuentaUsd(JAXBElement<String> value) {
        this.saldoCuentaUsd = value;
    }

    /**
     * Gets the value of the saldoPorConformarUsd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoPorConformarUsd() {
        return saldoPorConformarUsd;
    }

    /**
     * Sets the value of the saldoPorConformarUsd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoPorConformarUsd(JAXBElement<String> value) {
        this.saldoPorConformarUsd = value;
    }

    /**
     * Gets the value of the saldoporConformarArs property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoporConformarArs() {
        return saldoporConformarArs;
    }

    /**
     * Sets the value of the saldoporConformarArs property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoporConformarArs(JAXBElement<String> value) {
        this.saldoporConformarArs = value;
    }

    /**
     * Gets the value of the tipoCoberturaCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCoberturaCuenta() {
        return tipoCoberturaCuenta;
    }

    /**
     * Sets the value of the tipoCoberturaCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCoberturaCuenta(JAXBElement<String> value) {
        this.tipoCoberturaCuenta = value;
    }

    /**
     * Gets the value of the tipoPosicionamientoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoPosicionamientoCuenta() {
        return tipoPosicionamientoCuenta;
    }

    /**
     * Sets the value of the tipoPosicionamientoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoPosicionamientoCuenta(JAXBElement<String> value) {
        this.tipoPosicionamientoCuenta = value;
    }

}

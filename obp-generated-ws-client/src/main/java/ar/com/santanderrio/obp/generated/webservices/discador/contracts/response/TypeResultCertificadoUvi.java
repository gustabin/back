
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.ResponseBase;


/**
 * <p>Java class for TypeResultCertificadoUvi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TypeResultCertificadoUvi">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CanalAperturaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CentroCuentaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaInversionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescDivisaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescPerLiquidacionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DivisaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntidadCuentaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaAltaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorCapitalizaInteresesType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorCustodiaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorEstadoType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorRenovacionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorTransferenciaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCertificadoType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroSecuenciaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PlazoType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoInicialType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecuenciaRenovacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TasaRealType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TasaTeoricaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeResultCertificadoUvi", propOrder = {
    "canalAperturaType",
    "centroCuentaType",
    "cuentaInversionType",
    "descDivisaType",
    "descPerLiquidacionType",
    "divisaType",
    "entidadCuentaType",
    "fechaAltaType",
    "indicadorCapitalizaInteresesType",
    "indicadorCustodiaType",
    "indicadorEstadoType",
    "indicadorRenovacionType",
    "indicadorTransferenciaType",
    "numeroCertificadoType",
    "numeroSecuenciaType",
    "plazoType",
    "saldoInicialType",
    "secuenciaRenovacion",
    "tasaRealType",
    "tasaTeoricaType"
})
public class TypeResultCertificadoUvi
    extends ResponseBase
{

    @XmlElementRef(name = "CanalAperturaType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> canalAperturaType;
    @XmlElementRef(name = "CentroCuentaType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> centroCuentaType;
    @XmlElementRef(name = "CuentaInversionType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> cuentaInversionType;
    @XmlElementRef(name = "DescDivisaType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> descDivisaType;
    @XmlElementRef(name = "DescPerLiquidacionType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> descPerLiquidacionType;
    @XmlElementRef(name = "DivisaType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> divisaType;
    @XmlElementRef(name = "EntidadCuentaType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> entidadCuentaType;
    @XmlElementRef(name = "FechaAltaType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> fechaAltaType;
    @XmlElementRef(name = "IndicadorCapitalizaInteresesType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorCapitalizaInteresesType;
    @XmlElementRef(name = "IndicadorCustodiaType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorCustodiaType;
    @XmlElementRef(name = "IndicadorEstadoType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorEstadoType;
    @XmlElementRef(name = "IndicadorRenovacionType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorRenovacionType;
    @XmlElementRef(name = "IndicadorTransferenciaType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorTransferenciaType;
    @XmlElementRef(name = "NumeroCertificadoType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> numeroCertificadoType;
    @XmlElementRef(name = "NumeroSecuenciaType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> numeroSecuenciaType;
    @XmlElementRef(name = "PlazoType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> plazoType;
    @XmlElementRef(name = "SaldoInicialType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> saldoInicialType;
    @XmlElementRef(name = "SecuenciaRenovacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> secuenciaRenovacion;
    @XmlElementRef(name = "TasaRealType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> tasaRealType;
    @XmlElementRef(name = "TasaTeoricaType", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> tasaTeoricaType;

    /**
     * Gets the value of the canalAperturaType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCanalAperturaType() {
        return canalAperturaType;
    }

    /**
     * Sets the value of the canalAperturaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCanalAperturaType(JAXBElement<String> value) {
        this.canalAperturaType = value;
    }

    /**
     * Gets the value of the centroCuentaType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCentroCuentaType() {
        return centroCuentaType;
    }

    /**
     * Sets the value of the centroCuentaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCentroCuentaType(JAXBElement<String> value) {
        this.centroCuentaType = value;
    }

    /**
     * Gets the value of the cuentaInversionType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaInversionType() {
        return cuentaInversionType;
    }

    /**
     * Sets the value of the cuentaInversionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaInversionType(JAXBElement<String> value) {
        this.cuentaInversionType = value;
    }

    /**
     * Gets the value of the descDivisaType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescDivisaType() {
        return descDivisaType;
    }

    /**
     * Sets the value of the descDivisaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescDivisaType(JAXBElement<String> value) {
        this.descDivisaType = value;
    }

    /**
     * Gets the value of the descPerLiquidacionType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescPerLiquidacionType() {
        return descPerLiquidacionType;
    }

    /**
     * Sets the value of the descPerLiquidacionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescPerLiquidacionType(JAXBElement<String> value) {
        this.descPerLiquidacionType = value;
    }

    /**
     * Gets the value of the divisaType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDivisaType() {
        return divisaType;
    }

    /**
     * Sets the value of the divisaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDivisaType(JAXBElement<String> value) {
        this.divisaType = value;
    }

    /**
     * Gets the value of the entidadCuentaType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEntidadCuentaType() {
        return entidadCuentaType;
    }

    /**
     * Sets the value of the entidadCuentaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEntidadCuentaType(JAXBElement<String> value) {
        this.entidadCuentaType = value;
    }

    /**
     * Gets the value of the fechaAltaType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaAltaType() {
        return fechaAltaType;
    }

    /**
     * Sets the value of the fechaAltaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaAltaType(JAXBElement<String> value) {
        this.fechaAltaType = value;
    }

    /**
     * Gets the value of the indicadorCapitalizaInteresesType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorCapitalizaInteresesType() {
        return indicadorCapitalizaInteresesType;
    }

    /**
     * Sets the value of the indicadorCapitalizaInteresesType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorCapitalizaInteresesType(JAXBElement<String> value) {
        this.indicadorCapitalizaInteresesType = value;
    }

    /**
     * Gets the value of the indicadorCustodiaType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorCustodiaType() {
        return indicadorCustodiaType;
    }

    /**
     * Sets the value of the indicadorCustodiaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorCustodiaType(JAXBElement<String> value) {
        this.indicadorCustodiaType = value;
    }

    /**
     * Gets the value of the indicadorEstadoType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorEstadoType() {
        return indicadorEstadoType;
    }

    /**
     * Sets the value of the indicadorEstadoType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorEstadoType(JAXBElement<String> value) {
        this.indicadorEstadoType = value;
    }

    /**
     * Gets the value of the indicadorRenovacionType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorRenovacionType() {
        return indicadorRenovacionType;
    }

    /**
     * Sets the value of the indicadorRenovacionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorRenovacionType(JAXBElement<String> value) {
        this.indicadorRenovacionType = value;
    }

    /**
     * Gets the value of the indicadorTransferenciaType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorTransferenciaType() {
        return indicadorTransferenciaType;
    }

    /**
     * Sets the value of the indicadorTransferenciaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorTransferenciaType(JAXBElement<String> value) {
        this.indicadorTransferenciaType = value;
    }

    /**
     * Gets the value of the numeroCertificadoType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCertificadoType() {
        return numeroCertificadoType;
    }

    /**
     * Sets the value of the numeroCertificadoType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCertificadoType(JAXBElement<String> value) {
        this.numeroCertificadoType = value;
    }

    /**
     * Gets the value of the numeroSecuenciaType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroSecuenciaType() {
        return numeroSecuenciaType;
    }

    /**
     * Sets the value of the numeroSecuenciaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroSecuenciaType(JAXBElement<String> value) {
        this.numeroSecuenciaType = value;
    }

    /**
     * Gets the value of the plazoType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPlazoType() {
        return plazoType;
    }

    /**
     * Sets the value of the plazoType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPlazoType(JAXBElement<String> value) {
        this.plazoType = value;
    }

    /**
     * Gets the value of the saldoInicialType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoInicialType() {
        return saldoInicialType;
    }

    /**
     * Sets the value of the saldoInicialType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoInicialType(JAXBElement<String> value) {
        this.saldoInicialType = value;
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
     * Gets the value of the tasaRealType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTasaRealType() {
        return tasaRealType;
    }

    /**
     * Sets the value of the tasaRealType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTasaRealType(JAXBElement<String> value) {
        this.tasaRealType = value;
    }

    /**
     * Gets the value of the tasaTeoricaType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTasaTeoricaType() {
        return tasaTeoricaType;
    }

    /**
     * Sets the value of the tasaTeoricaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTasaTeoricaType(JAXBElement<String> value) {
        this.tasaTeoricaType = value;
    }

}

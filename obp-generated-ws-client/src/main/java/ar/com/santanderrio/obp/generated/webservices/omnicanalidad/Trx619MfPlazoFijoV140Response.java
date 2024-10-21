
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx619MfPlazoFijoV140Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx619MfPlazoFijoV140Response">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="AjusteAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CotizacionCodigoUr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntidadCuentaPlazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaAltaReal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaMinPrecancelar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaVencimientoPf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteCertificado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteDebitado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Impuestos" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfImpuestoPlazoFijoResponse" minOccurs="0"/>
 *         &lt;element name="IndBD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorPrecancelable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Interesantes" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfInteresantesResponse" minOccurs="0"/>
 *         &lt;element name="InteresesPf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MinimoDiasPrecancelar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCertificado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuentaPlazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OcurrenciasImpuestos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OcurrenciasInteresante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PeriodoLiquidacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PlazoDias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PorcentajePenalizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoInicUr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SignoAjuste" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCuentaPlazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TasaVarMinGarantizada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TasasPrimerTramo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalImpuestosCobrados" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalImpuestosVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx619MfPlazoFijoV140Response", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", propOrder = {
    "ajusteAlta",
    "cotizacionCodigoUr",
    "entidadCuentaPlazo",
    "fechaAltaReal",
    "fechaMinPrecancelar",
    "fechaVencimientoPf",
    "importeCertificado",
    "importeDebitado",
    "impuestos",
    "indBD",
    "indicadorPrecancelable",
    "interesantes",
    "interesesPf",
    "minimoDiasPrecancelar",
    "nio",
    "numeroCertificado",
    "numeroCuentaPlazo",
    "ocurrenciasImpuestos",
    "ocurrenciasInteresante",
    "periodoLiquidacion",
    "plazoDias",
    "porcentajePenalizacion",
    "saldoInicUr",
    "signoAjuste",
    "sucursalCuentaPlazo",
    "tasaVarMinGarantizada",
    "tasasPrimerTramo",
    "totalImpuestosCobrados",
    "totalImpuestosVencimiento"
})
public class Trx619MfPlazoFijoV140Response
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "AjusteAlta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> ajusteAlta;
    @XmlElementRef(name = "CotizacionCodigoUr", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> cotizacionCodigoUr;
    @XmlElementRef(name = "EntidadCuentaPlazo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> entidadCuentaPlazo;
    @XmlElementRef(name = "FechaAltaReal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> fechaAltaReal;
    @XmlElementRef(name = "FechaMinPrecancelar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> fechaMinPrecancelar;
    @XmlElementRef(name = "FechaVencimientoPf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> fechaVencimientoPf;
    @XmlElementRef(name = "ImporteCertificado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> importeCertificado;
    @XmlElementRef(name = "ImporteDebitado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> importeDebitado;
    @XmlElementRef(name = "Impuestos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<ArrayOfImpuestoPlazoFijoResponse> impuestos;
    @XmlElementRef(name = "IndBD", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> indBD;
    @XmlElementRef(name = "IndicadorPrecancelable", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> indicadorPrecancelable;
    @XmlElementRef(name = "Interesantes", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<ArrayOfInteresantesResponse> interesantes;
    @XmlElementRef(name = "InteresesPf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> interesesPf;
    @XmlElementRef(name = "MinimoDiasPrecancelar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> minimoDiasPrecancelar;
    @XmlElementRef(name = "Nio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> nio;
    @XmlElementRef(name = "NumeroCertificado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> numeroCertificado;
    @XmlElementRef(name = "NumeroCuentaPlazo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> numeroCuentaPlazo;
    @XmlElementRef(name = "OcurrenciasImpuestos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> ocurrenciasImpuestos;
    @XmlElementRef(name = "OcurrenciasInteresante", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> ocurrenciasInteresante;
    @XmlElementRef(name = "PeriodoLiquidacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> periodoLiquidacion;
    @XmlElementRef(name = "PlazoDias", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> plazoDias;
    @XmlElementRef(name = "PorcentajePenalizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> porcentajePenalizacion;
    @XmlElementRef(name = "SaldoInicUr", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> saldoInicUr;
    @XmlElementRef(name = "SignoAjuste", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> signoAjuste;
    @XmlElementRef(name = "SucursalCuentaPlazo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCuentaPlazo;
    @XmlElementRef(name = "TasaVarMinGarantizada", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> tasaVarMinGarantizada;
    @XmlElementRef(name = "TasasPrimerTramo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> tasasPrimerTramo;
    @XmlElementRef(name = "TotalImpuestosCobrados", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> totalImpuestosCobrados;
    @XmlElementRef(name = "TotalImpuestosVencimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx619", type = JAXBElement.class)
    protected JAXBElement<String> totalImpuestosVencimiento;

    /**
     * Gets the value of the ajusteAlta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAjusteAlta() {
        return ajusteAlta;
    }

    /**
     * Sets the value of the ajusteAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAjusteAlta(JAXBElement<String> value) {
        this.ajusteAlta = value;
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
     * Gets the value of the fechaAltaReal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaAltaReal() {
        return fechaAltaReal;
    }

    /**
     * Sets the value of the fechaAltaReal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaAltaReal(JAXBElement<String> value) {
        this.fechaAltaReal = value;
    }

    /**
     * Gets the value of the fechaMinPrecancelar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaMinPrecancelar() {
        return fechaMinPrecancelar;
    }

    /**
     * Sets the value of the fechaMinPrecancelar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaMinPrecancelar(JAXBElement<String> value) {
        this.fechaMinPrecancelar = value;
    }

    /**
     * Gets the value of the fechaVencimientoPf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaVencimientoPf() {
        return fechaVencimientoPf;
    }

    /**
     * Sets the value of the fechaVencimientoPf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaVencimientoPf(JAXBElement<String> value) {
        this.fechaVencimientoPf = value;
    }

    /**
     * Gets the value of the importeCertificado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteCertificado() {
        return importeCertificado;
    }

    /**
     * Sets the value of the importeCertificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteCertificado(JAXBElement<String> value) {
        this.importeCertificado = value;
    }

    /**
     * Gets the value of the importeDebitado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteDebitado() {
        return importeDebitado;
    }

    /**
     * Sets the value of the importeDebitado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteDebitado(JAXBElement<String> value) {
        this.importeDebitado = value;
    }

    /**
     * Gets the value of the impuestos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfImpuestoPlazoFijoResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfImpuestoPlazoFijoResponse> getImpuestos() {
        return impuestos;
    }

    /**
     * Sets the value of the impuestos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfImpuestoPlazoFijoResponse }{@code >}
     *     
     */
    public void setImpuestos(JAXBElement<ArrayOfImpuestoPlazoFijoResponse> value) {
        this.impuestos = value;
    }

    /**
     * Gets the value of the indBD property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndBD() {
        return indBD;
    }

    /**
     * Sets the value of the indBD property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndBD(JAXBElement<String> value) {
        this.indBD = value;
    }

    /**
     * Gets the value of the indicadorPrecancelable property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorPrecancelable() {
        return indicadorPrecancelable;
    }

    /**
     * Sets the value of the indicadorPrecancelable property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorPrecancelable(JAXBElement<String> value) {
        this.indicadorPrecancelable = value;
    }

    /**
     * Gets the value of the interesantes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfInteresantesResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfInteresantesResponse> getInteresantes() {
        return interesantes;
    }

    /**
     * Sets the value of the interesantes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfInteresantesResponse }{@code >}
     *     
     */
    public void setInteresantes(JAXBElement<ArrayOfInteresantesResponse> value) {
        this.interesantes = value;
    }

    /**
     * Gets the value of the interesesPf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInteresesPf() {
        return interesesPf;
    }

    /**
     * Sets the value of the interesesPf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInteresesPf(JAXBElement<String> value) {
        this.interesesPf = value;
    }

    /**
     * Gets the value of the minimoDiasPrecancelar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMinimoDiasPrecancelar() {
        return minimoDiasPrecancelar;
    }

    /**
     * Sets the value of the minimoDiasPrecancelar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMinimoDiasPrecancelar(JAXBElement<String> value) {
        this.minimoDiasPrecancelar = value;
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
     * Gets the value of the numeroCertificado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCertificado() {
        return numeroCertificado;
    }

    /**
     * Sets the value of the numeroCertificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCertificado(JAXBElement<String> value) {
        this.numeroCertificado = value;
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
     * Gets the value of the ocurrenciasImpuestos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOcurrenciasImpuestos() {
        return ocurrenciasImpuestos;
    }

    /**
     * Sets the value of the ocurrenciasImpuestos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOcurrenciasImpuestos(JAXBElement<String> value) {
        this.ocurrenciasImpuestos = value;
    }

    /**
     * Gets the value of the ocurrenciasInteresante property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOcurrenciasInteresante() {
        return ocurrenciasInteresante;
    }

    /**
     * Sets the value of the ocurrenciasInteresante property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOcurrenciasInteresante(JAXBElement<String> value) {
        this.ocurrenciasInteresante = value;
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
     * Gets the value of the porcentajePenalizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPorcentajePenalizacion() {
        return porcentajePenalizacion;
    }

    /**
     * Sets the value of the porcentajePenalizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPorcentajePenalizacion(JAXBElement<String> value) {
        this.porcentajePenalizacion = value;
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
     * Gets the value of the signoAjuste property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSignoAjuste() {
        return signoAjuste;
    }

    /**
     * Sets the value of the signoAjuste property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSignoAjuste(JAXBElement<String> value) {
        this.signoAjuste = value;
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
     * Gets the value of the tasaVarMinGarantizada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTasaVarMinGarantizada() {
        return tasaVarMinGarantizada;
    }

    /**
     * Sets the value of the tasaVarMinGarantizada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTasaVarMinGarantizada(JAXBElement<String> value) {
        this.tasaVarMinGarantizada = value;
    }

    /**
     * Gets the value of the tasasPrimerTramo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTasasPrimerTramo() {
        return tasasPrimerTramo;
    }

    /**
     * Sets the value of the tasasPrimerTramo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTasasPrimerTramo(JAXBElement<String> value) {
        this.tasasPrimerTramo = value;
    }

    /**
     * Gets the value of the totalImpuestosCobrados property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTotalImpuestosCobrados() {
        return totalImpuestosCobrados;
    }

    /**
     * Sets the value of the totalImpuestosCobrados property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTotalImpuestosCobrados(JAXBElement<String> value) {
        this.totalImpuestosCobrados = value;
    }

    /**
     * Gets the value of the totalImpuestosVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTotalImpuestosVencimiento() {
        return totalImpuestosVencimiento;
    }

    /**
     * Sets the value of the totalImpuestosVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTotalImpuestosVencimiento(JAXBElement<String> value) {
        this.totalImpuestosVencimiento = value;
    }

}

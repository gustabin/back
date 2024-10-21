
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.ArrayOfImpuestoPlazoFijoResponse;


/**
 * <p>Java class for Trx414CondPfPfiResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx414CondPfPfiResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CodigoProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoSubproducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoTarifa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CotizacionCodigoUr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescripcionProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescripcionSubproducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaAltaReal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaVencimientoPlazoFijo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteADebitar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteCertificado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImpuestoPlazosFijos" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfImpuestoPlazoFijoResponse" minOccurs="0"/>
 *         &lt;element name="InteresesPf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OcurrenciasImpuestos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PeriodicidadLiquidacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PlazoDias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoInicUr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TasaPf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TasaRenovacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalImpuestoInicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalImpuestoVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx414CondPfPfiResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", propOrder = {
    "codigoProducto",
    "codigoSubproducto",
    "codigoTarifa",
    "cotizacionCodigoUr",
    "descripcionProducto",
    "descripcionSubproducto",
    "fechaAltaReal",
    "fechaVencimientoPlazoFijo",
    "importeADebitar",
    "importeCertificado",
    "impuestoPlazosFijos",
    "interesesPf",
    "ocurrenciasImpuestos",
    "periodicidadLiquidacion",
    "plazoDias",
    "saldoInicUr",
    "tasaPf",
    "tasaRenovacion",
    "tipoPf",
    "totalImpuestoInicio",
    "totalImpuestoVencimiento"
})
public class Trx414CondPfPfiResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CodigoProducto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> codigoProducto;
    @XmlElementRef(name = "CodigoSubproducto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> codigoSubproducto;
    @XmlElementRef(name = "CodigoTarifa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> codigoTarifa;
    @XmlElementRef(name = "CotizacionCodigoUr", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> cotizacionCodigoUr;
    @XmlElementRef(name = "DescripcionProducto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> descripcionProducto;
    @XmlElementRef(name = "DescripcionSubproducto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> descripcionSubproducto;
    @XmlElementRef(name = "FechaAltaReal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> fechaAltaReal;
    @XmlElementRef(name = "FechaVencimientoPlazoFijo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> fechaVencimientoPlazoFijo;
    @XmlElementRef(name = "ImporteADebitar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> importeADebitar;
    @XmlElementRef(name = "ImporteCertificado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> importeCertificado;
    @XmlElementRef(name = "ImpuestoPlazosFijos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<ArrayOfImpuestoPlazoFijoResponse> impuestoPlazosFijos;
    @XmlElementRef(name = "InteresesPf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> interesesPf;
    @XmlElementRef(name = "OcurrenciasImpuestos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> ocurrenciasImpuestos;
    @XmlElementRef(name = "PeriodicidadLiquidacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> periodicidadLiquidacion;
    @XmlElementRef(name = "PlazoDias", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> plazoDias;
    @XmlElementRef(name = "SaldoInicUr", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> saldoInicUr;
    @XmlElementRef(name = "TasaPf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> tasaPf;
    @XmlElementRef(name = "TasaRenovacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> tasaRenovacion;
    @XmlElementRef(name = "TipoPf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> tipoPf;
    @XmlElementRef(name = "TotalImpuestoInicio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> totalImpuestoInicio;
    @XmlElementRef(name = "TotalImpuestoVencimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx414", type = JAXBElement.class)
    protected JAXBElement<String> totalImpuestoVencimiento;

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
     * Gets the value of the descripcionProducto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionProducto() {
        return descripcionProducto;
    }

    /**
     * Sets the value of the descripcionProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionProducto(JAXBElement<String> value) {
        this.descripcionProducto = value;
    }

    /**
     * Gets the value of the descripcionSubproducto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionSubproducto() {
        return descripcionSubproducto;
    }

    /**
     * Sets the value of the descripcionSubproducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionSubproducto(JAXBElement<String> value) {
        this.descripcionSubproducto = value;
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
     * Gets the value of the fechaVencimientoPlazoFijo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaVencimientoPlazoFijo() {
        return fechaVencimientoPlazoFijo;
    }

    /**
     * Sets the value of the fechaVencimientoPlazoFijo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaVencimientoPlazoFijo(JAXBElement<String> value) {
        this.fechaVencimientoPlazoFijo = value;
    }

    /**
     * Gets the value of the importeADebitar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteADebitar() {
        return importeADebitar;
    }

    /**
     * Sets the value of the importeADebitar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteADebitar(JAXBElement<String> value) {
        this.importeADebitar = value;
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
     * Gets the value of the impuestoPlazosFijos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfImpuestoPlazoFijoResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfImpuestoPlazoFijoResponse> getImpuestoPlazosFijos() {
        return impuestoPlazosFijos;
    }

    /**
     * Sets the value of the impuestoPlazosFijos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfImpuestoPlazoFijoResponse }{@code >}
     *     
     */
    public void setImpuestoPlazosFijos(JAXBElement<ArrayOfImpuestoPlazoFijoResponse> value) {
        this.impuestoPlazosFijos = value;
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
     * Gets the value of the tasaRenovacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTasaRenovacion() {
        return tasaRenovacion;
    }

    /**
     * Sets the value of the tasaRenovacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTasaRenovacion(JAXBElement<String> value) {
        this.tasaRenovacion = value;
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

    /**
     * Gets the value of the totalImpuestoInicio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTotalImpuestoInicio() {
        return totalImpuestoInicio;
    }

    /**
     * Sets the value of the totalImpuestoInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTotalImpuestoInicio(JAXBElement<String> value) {
        this.totalImpuestoInicio = value;
    }

    /**
     * Gets the value of the totalImpuestoVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTotalImpuestoVencimiento() {
        return totalImpuestoVencimiento;
    }

    /**
     * Sets the value of the totalImpuestoVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTotalImpuestoVencimiento(JAXBElement<String> value) {
        this.totalImpuestoVencimiento = value;
    }

}

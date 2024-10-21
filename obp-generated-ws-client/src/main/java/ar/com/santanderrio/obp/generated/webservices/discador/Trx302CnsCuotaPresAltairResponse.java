
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx302CnsCuotaPresAltairResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx302CnsCuotaPresAltairResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CodigoMoneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comisiones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GastosAnexos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteAmortizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteComplementario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteEndeudamiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteIngresoBruto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteIntereses" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteIva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteIvaAdicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImportePunitorios" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteSeguroBien" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteServicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteTotalCuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteTotalSeguros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MontoSeguroVida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroRecibo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TasaPrestamo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VencimientoCuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx302CnsCuotaPresAltairResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", propOrder = {
    "codigoMoneda",
    "comisiones",
    "gastosAnexos",
    "importeAmortizacion",
    "importeComplementario",
    "importeEndeudamiento",
    "importeIngresoBruto",
    "importeIntereses",
    "importeIva",
    "importeIvaAdicional",
    "importePunitorios",
    "importeSeguroBien",
    "importeServicio",
    "importeTotalCuota",
    "importeTotalSeguros",
    "montoSeguroVida",
    "numeroRecibo",
    "tasaPrestamo",
    "vencimientoCuota"
})
public class Trx302CnsCuotaPresAltairResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CodigoMoneda", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> codigoMoneda;
    @XmlElementRef(name = "Comisiones", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> comisiones;
    @XmlElementRef(name = "GastosAnexos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> gastosAnexos;
    @XmlElementRef(name = "ImporteAmortizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> importeAmortizacion;
    @XmlElementRef(name = "ImporteComplementario", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> importeComplementario;
    @XmlElementRef(name = "ImporteEndeudamiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> importeEndeudamiento;
    @XmlElementRef(name = "ImporteIngresoBruto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> importeIngresoBruto;
    @XmlElementRef(name = "ImporteIntereses", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> importeIntereses;
    @XmlElementRef(name = "ImporteIva", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> importeIva;
    @XmlElementRef(name = "ImporteIvaAdicional", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> importeIvaAdicional;
    @XmlElementRef(name = "ImportePunitorios", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> importePunitorios;
    @XmlElementRef(name = "ImporteSeguroBien", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> importeSeguroBien;
    @XmlElementRef(name = "ImporteServicio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> importeServicio;
    @XmlElementRef(name = "ImporteTotalCuota", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> importeTotalCuota;
    @XmlElementRef(name = "ImporteTotalSeguros", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> importeTotalSeguros;
    @XmlElementRef(name = "MontoSeguroVida", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> montoSeguroVida;
    @XmlElementRef(name = "NumeroRecibo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> numeroRecibo;
    @XmlElementRef(name = "TasaPrestamo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> tasaPrestamo;
    @XmlElementRef(name = "VencimientoCuota", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> vencimientoCuota;

    /**
     * Gets the value of the codigoMoneda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoMoneda() {
        return codigoMoneda;
    }

    /**
     * Sets the value of the codigoMoneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoMoneda(JAXBElement<String> value) {
        this.codigoMoneda = value;
    }

    /**
     * Gets the value of the comisiones property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getComisiones() {
        return comisiones;
    }

    /**
     * Sets the value of the comisiones property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setComisiones(JAXBElement<String> value) {
        this.comisiones = value;
    }

    /**
     * Gets the value of the gastosAnexos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGastosAnexos() {
        return gastosAnexos;
    }

    /**
     * Sets the value of the gastosAnexos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGastosAnexos(JAXBElement<String> value) {
        this.gastosAnexos = value;
    }

    /**
     * Gets the value of the importeAmortizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteAmortizacion() {
        return importeAmortizacion;
    }

    /**
     * Sets the value of the importeAmortizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteAmortizacion(JAXBElement<String> value) {
        this.importeAmortizacion = value;
    }

    /**
     * Gets the value of the importeComplementario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteComplementario() {
        return importeComplementario;
    }

    /**
     * Sets the value of the importeComplementario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteComplementario(JAXBElement<String> value) {
        this.importeComplementario = value;
    }

    /**
     * Gets the value of the importeEndeudamiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteEndeudamiento() {
        return importeEndeudamiento;
    }

    /**
     * Sets the value of the importeEndeudamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteEndeudamiento(JAXBElement<String> value) {
        this.importeEndeudamiento = value;
    }

    /**
     * Gets the value of the importeIngresoBruto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteIngresoBruto() {
        return importeIngresoBruto;
    }

    /**
     * Sets the value of the importeIngresoBruto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteIngresoBruto(JAXBElement<String> value) {
        this.importeIngresoBruto = value;
    }

    /**
     * Gets the value of the importeIntereses property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteIntereses() {
        return importeIntereses;
    }

    /**
     * Sets the value of the importeIntereses property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteIntereses(JAXBElement<String> value) {
        this.importeIntereses = value;
    }

    /**
     * Gets the value of the importeIva property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteIva() {
        return importeIva;
    }

    /**
     * Sets the value of the importeIva property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteIva(JAXBElement<String> value) {
        this.importeIva = value;
    }

    /**
     * Gets the value of the importeIvaAdicional property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteIvaAdicional() {
        return importeIvaAdicional;
    }

    /**
     * Sets the value of the importeIvaAdicional property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteIvaAdicional(JAXBElement<String> value) {
        this.importeIvaAdicional = value;
    }

    /**
     * Gets the value of the importePunitorios property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImportePunitorios() {
        return importePunitorios;
    }

    /**
     * Sets the value of the importePunitorios property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImportePunitorios(JAXBElement<String> value) {
        this.importePunitorios = value;
    }

    /**
     * Gets the value of the importeSeguroBien property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteSeguroBien() {
        return importeSeguroBien;
    }

    /**
     * Sets the value of the importeSeguroBien property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteSeguroBien(JAXBElement<String> value) {
        this.importeSeguroBien = value;
    }

    /**
     * Gets the value of the importeServicio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteServicio() {
        return importeServicio;
    }

    /**
     * Sets the value of the importeServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteServicio(JAXBElement<String> value) {
        this.importeServicio = value;
    }

    /**
     * Gets the value of the importeTotalCuota property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteTotalCuota() {
        return importeTotalCuota;
    }

    /**
     * Sets the value of the importeTotalCuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteTotalCuota(JAXBElement<String> value) {
        this.importeTotalCuota = value;
    }

    /**
     * Gets the value of the importeTotalSeguros property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteTotalSeguros() {
        return importeTotalSeguros;
    }

    /**
     * Sets the value of the importeTotalSeguros property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteTotalSeguros(JAXBElement<String> value) {
        this.importeTotalSeguros = value;
    }

    /**
     * Gets the value of the montoSeguroVida property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMontoSeguroVida() {
        return montoSeguroVida;
    }

    /**
     * Sets the value of the montoSeguroVida property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMontoSeguroVida(JAXBElement<String> value) {
        this.montoSeguroVida = value;
    }

    /**
     * Gets the value of the numeroRecibo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroRecibo() {
        return numeroRecibo;
    }

    /**
     * Sets the value of the numeroRecibo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroRecibo(JAXBElement<String> value) {
        this.numeroRecibo = value;
    }

    /**
     * Gets the value of the tasaPrestamo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTasaPrestamo() {
        return tasaPrestamo;
    }

    /**
     * Sets the value of the tasaPrestamo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTasaPrestamo(JAXBElement<String> value) {
        this.tasaPrestamo = value;
    }

    /**
     * Gets the value of the vencimientoCuota property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVencimientoCuota() {
        return vencimientoCuota;
    }

    /**
     * Sets the value of the vencimientoCuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVencimientoCuota(JAXBElement<String> value) {
        this.vencimientoCuota = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx296MdlwStopRenovacionPlazoFijoAltairRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx296MdlwStopRenovacionPlazoFijoAltairRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestContactBase">
 *       &lt;sequence>
 *         &lt;element name="BloqueoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Circular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Divisa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormaPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdOper" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndSinonimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorCertificadoTransferible" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorRenovacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCertificadoImpreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCuentaPlazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroRefOrig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nssf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OperacionReversa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecuenciaCln" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecuenciaSel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCuentaPlazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalRadicacionCertificado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tarifa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TasaPf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx296MdlwStopRenovacionPlazoFijoAltairRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", propOrder = {
    "bloqueoCuenta",
    "circular",
    "divisa",
    "formaPago",
    "idOper",
    "indSinonimo",
    "indicadorCertificadoTransferible",
    "indicadorRenovacion",
    "nroCertificadoImpreso",
    "nroCuentaPlazo",
    "nroRefOrig",
    "nssf",
    "operacionReversa",
    "secuenciaCln",
    "secuenciaSel",
    "sucursalCuentaPlazo",
    "sucursalRadicacionCertificado",
    "tarifa",
    "tasaPf"
})
public class Trx296MdlwStopRenovacionPlazoFijoAltairRequest
    extends RequestContactBase
{

    @XmlElementRef(name = "BloqueoCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> bloqueoCuenta;
    @XmlElementRef(name = "Circular", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> circular;
    @XmlElementRef(name = "Divisa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> divisa;
    @XmlElementRef(name = "FormaPago", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> formaPago;
    @XmlElementRef(name = "IdOper", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> idOper;
    @XmlElementRef(name = "IndSinonimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> indSinonimo;
    @XmlElementRef(name = "IndicadorCertificadoTransferible", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> indicadorCertificadoTransferible;
    @XmlElementRef(name = "IndicadorRenovacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> indicadorRenovacion;
    @XmlElementRef(name = "NroCertificadoImpreso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> nroCertificadoImpreso;
    @XmlElementRef(name = "NroCuentaPlazo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> nroCuentaPlazo;
    @XmlElementRef(name = "NroRefOrig", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> nroRefOrig;
    @XmlElementRef(name = "Nssf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> nssf;
    @XmlElementRef(name = "OperacionReversa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> operacionReversa;
    @XmlElementRef(name = "SecuenciaCln", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> secuenciaCln;
    @XmlElementRef(name = "SecuenciaSel", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> secuenciaSel;
    @XmlElementRef(name = "SucursalCuentaPlazo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCuentaPlazo;
    @XmlElementRef(name = "SucursalRadicacionCertificado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> sucursalRadicacionCertificado;
    @XmlElementRef(name = "Tarifa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> tarifa;
    @XmlElementRef(name = "TasaPf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx296", type = JAXBElement.class)
    protected JAXBElement<String> tasaPf;

    /**
     * Gets the value of the bloqueoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBloqueoCuenta() {
        return bloqueoCuenta;
    }

    /**
     * Sets the value of the bloqueoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBloqueoCuenta(JAXBElement<String> value) {
        this.bloqueoCuenta = value;
    }

    /**
     * Gets the value of the circular property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCircular() {
        return circular;
    }

    /**
     * Sets the value of the circular property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCircular(JAXBElement<String> value) {
        this.circular = value;
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
     * Gets the value of the idOper property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdOper() {
        return idOper;
    }

    /**
     * Sets the value of the idOper property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdOper(JAXBElement<String> value) {
        this.idOper = value;
    }

    /**
     * Gets the value of the indSinonimo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndSinonimo() {
        return indSinonimo;
    }

    /**
     * Sets the value of the indSinonimo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndSinonimo(JAXBElement<String> value) {
        this.indSinonimo = value;
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
     * Gets the value of the nroCertificadoImpreso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCertificadoImpreso() {
        return nroCertificadoImpreso;
    }

    /**
     * Sets the value of the nroCertificadoImpreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCertificadoImpreso(JAXBElement<String> value) {
        this.nroCertificadoImpreso = value;
    }

    /**
     * Gets the value of the nroCuentaPlazo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCuentaPlazo() {
        return nroCuentaPlazo;
    }

    /**
     * Sets the value of the nroCuentaPlazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCuentaPlazo(JAXBElement<String> value) {
        this.nroCuentaPlazo = value;
    }

    /**
     * Gets the value of the nroRefOrig property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroRefOrig() {
        return nroRefOrig;
    }

    /**
     * Sets the value of the nroRefOrig property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroRefOrig(JAXBElement<String> value) {
        this.nroRefOrig = value;
    }

    /**
     * Gets the value of the nssf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNssf() {
        return nssf;
    }

    /**
     * Sets the value of the nssf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNssf(JAXBElement<String> value) {
        this.nssf = value;
    }

    /**
     * Gets the value of the operacionReversa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOperacionReversa() {
        return operacionReversa;
    }

    /**
     * Sets the value of the operacionReversa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOperacionReversa(JAXBElement<String> value) {
        this.operacionReversa = value;
    }

    /**
     * Gets the value of the secuenciaCln property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSecuenciaCln() {
        return secuenciaCln;
    }

    /**
     * Sets the value of the secuenciaCln property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecuenciaCln(JAXBElement<String> value) {
        this.secuenciaCln = value;
    }

    /**
     * Gets the value of the secuenciaSel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSecuenciaSel() {
        return secuenciaSel;
    }

    /**
     * Sets the value of the secuenciaSel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecuenciaSel(JAXBElement<String> value) {
        this.secuenciaSel = value;
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
     * Gets the value of the tarifa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTarifa() {
        return tarifa;
    }

    /**
     * Sets the value of the tarifa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTarifa(JAXBElement<String> value) {
        this.tarifa = value;
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

}

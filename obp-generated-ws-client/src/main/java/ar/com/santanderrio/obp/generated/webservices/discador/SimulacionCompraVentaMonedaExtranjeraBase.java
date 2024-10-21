
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.TDatosClienteRequest;


/**
 * <p>Java class for SimulacionCompraVentaMonedaExtranjeraBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SimulacionCompraVentaMonedaExtranjeraBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="Aplicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AplicacionDolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AutorizAfip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cliente" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}TDatosClienteRequest" minOccurs="0"/>
 *         &lt;element name="CodigoConcepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConcepCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConcepDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaValor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdTuatesora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdicCompraVta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteCoti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteCred" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteDeb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroDocBcra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumBoleCvta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuentaDolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaisEmisDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaisNac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalDolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCambio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoDocBcra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimulacionCompraVentaMonedaExtranjeraBase", propOrder = {
    "aplicacion",
    "aplicacionDolar",
    "autorizAfip",
    "cliente",
    "codigoConcepto",
    "codigoCredito",
    "codigoDebito",
    "concepCredito",
    "concepDebito",
    "fechaValor",
    "idTuatesora",
    "idicCompraVta",
    "importeCoti",
    "importeCred",
    "importeDeb",
    "nroDocBcra",
    "numBoleCvta",
    "numeroCuenta",
    "numeroCuentaDolar",
    "paisEmisDoc",
    "paisNac",
    "sucursal",
    "sucursalDolar",
    "tipoCambio",
    "tipoDocBcra"
})
@XmlSeeAlso({
    Trx740MdlwSimulacionBancoCompraMonedaExtranjeraRequest.class,
    Trx738MdlwSimulacionBancoVendeMonedaExtranjeraRequest.class
})
public class SimulacionCompraVentaMonedaExtranjeraBase
    extends RequestBase
{

    @XmlElementRef(name = "Aplicacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> aplicacion;
    @XmlElementRef(name = "AplicacionDolar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> aplicacionDolar;
    @XmlElementRef(name = "AutorizAfip", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> autorizAfip;
    @XmlElementRef(name = "Cliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<TDatosClienteRequest> cliente;
    @XmlElementRef(name = "CodigoConcepto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> codigoConcepto;
    @XmlElementRef(name = "CodigoCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> codigoCredito;
    @XmlElementRef(name = "CodigoDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> codigoDebito;
    @XmlElementRef(name = "ConcepCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> concepCredito;
    @XmlElementRef(name = "ConcepDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> concepDebito;
    @XmlElementRef(name = "FechaValor", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> fechaValor;
    @XmlElementRef(name = "IdTuatesora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> idTuatesora;
    @XmlElementRef(name = "IdicCompraVta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> idicCompraVta;
    @XmlElementRef(name = "ImporteCoti", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> importeCoti;
    @XmlElementRef(name = "ImporteCred", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> importeCred;
    @XmlElementRef(name = "ImporteDeb", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> importeDeb;
    @XmlElementRef(name = "NroDocBcra", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> nroDocBcra;
    @XmlElementRef(name = "NumBoleCvta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> numBoleCvta;
    @XmlElementRef(name = "NumeroCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> numeroCuenta;
    @XmlElementRef(name = "NumeroCuentaDolar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> numeroCuentaDolar;
    @XmlElementRef(name = "PaisEmisDoc", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> paisEmisDoc;
    @XmlElementRef(name = "PaisNac", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> paisNac;
    @XmlElementRef(name = "Sucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> sucursal;
    @XmlElementRef(name = "SucursalDolar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> sucursalDolar;
    @XmlElementRef(name = "TipoCambio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> tipoCambio;
    @XmlElementRef(name = "TipoDocBcra", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> tipoDocBcra;

    /**
     * Gets the value of the aplicacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAplicacion() {
        return aplicacion;
    }

    /**
     * Sets the value of the aplicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAplicacion(JAXBElement<String> value) {
        this.aplicacion = value;
    }

    /**
     * Gets the value of the aplicacionDolar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAplicacionDolar() {
        return aplicacionDolar;
    }

    /**
     * Sets the value of the aplicacionDolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAplicacionDolar(JAXBElement<String> value) {
        this.aplicacionDolar = value;
    }

    /**
     * Gets the value of the autorizAfip property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAutorizAfip() {
        return autorizAfip;
    }

    /**
     * Sets the value of the autorizAfip property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAutorizAfip(JAXBElement<String> value) {
        this.autorizAfip = value;
    }

    /**
     * Gets the value of the cliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TDatosClienteRequest }{@code >}
     *     
     */
    public JAXBElement<TDatosClienteRequest> getCliente() {
        return cliente;
    }

    /**
     * Sets the value of the cliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TDatosClienteRequest }{@code >}
     *     
     */
    public void setCliente(JAXBElement<TDatosClienteRequest> value) {
        this.cliente = value;
    }

    /**
     * Gets the value of the codigoConcepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoConcepto() {
        return codigoConcepto;
    }

    /**
     * Sets the value of the codigoConcepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoConcepto(JAXBElement<String> value) {
        this.codigoConcepto = value;
    }

    /**
     * Gets the value of the codigoCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoCredito() {
        return codigoCredito;
    }

    /**
     * Sets the value of the codigoCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoCredito(JAXBElement<String> value) {
        this.codigoCredito = value;
    }

    /**
     * Gets the value of the codigoDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoDebito() {
        return codigoDebito;
    }

    /**
     * Sets the value of the codigoDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoDebito(JAXBElement<String> value) {
        this.codigoDebito = value;
    }

    /**
     * Gets the value of the concepCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConcepCredito() {
        return concepCredito;
    }

    /**
     * Sets the value of the concepCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConcepCredito(JAXBElement<String> value) {
        this.concepCredito = value;
    }

    /**
     * Gets the value of the concepDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConcepDebito() {
        return concepDebito;
    }

    /**
     * Sets the value of the concepDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConcepDebito(JAXBElement<String> value) {
        this.concepDebito = value;
    }

    /**
     * Gets the value of the fechaValor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaValor() {
        return fechaValor;
    }

    /**
     * Sets the value of the fechaValor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaValor(JAXBElement<String> value) {
        this.fechaValor = value;
    }

    /**
     * Gets the value of the idTuatesora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdTuatesora() {
        return idTuatesora;
    }

    /**
     * Sets the value of the idTuatesora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdTuatesora(JAXBElement<String> value) {
        this.idTuatesora = value;
    }

    /**
     * Gets the value of the idicCompraVta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdicCompraVta() {
        return idicCompraVta;
    }

    /**
     * Sets the value of the idicCompraVta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdicCompraVta(JAXBElement<String> value) {
        this.idicCompraVta = value;
    }

    /**
     * Gets the value of the importeCoti property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteCoti() {
        return importeCoti;
    }

    /**
     * Sets the value of the importeCoti property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteCoti(JAXBElement<String> value) {
        this.importeCoti = value;
    }

    /**
     * Gets the value of the importeCred property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteCred() {
        return importeCred;
    }

    /**
     * Sets the value of the importeCred property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteCred(JAXBElement<String> value) {
        this.importeCred = value;
    }

    /**
     * Gets the value of the importeDeb property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteDeb() {
        return importeDeb;
    }

    /**
     * Sets the value of the importeDeb property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteDeb(JAXBElement<String> value) {
        this.importeDeb = value;
    }

    /**
     * Gets the value of the nroDocBcra property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroDocBcra() {
        return nroDocBcra;
    }

    /**
     * Sets the value of the nroDocBcra property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroDocBcra(JAXBElement<String> value) {
        this.nroDocBcra = value;
    }

    /**
     * Gets the value of the numBoleCvta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumBoleCvta() {
        return numBoleCvta;
    }

    /**
     * Sets the value of the numBoleCvta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumBoleCvta(JAXBElement<String> value) {
        this.numBoleCvta = value;
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
     * Gets the value of the numeroCuentaDolar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCuentaDolar() {
        return numeroCuentaDolar;
    }

    /**
     * Sets the value of the numeroCuentaDolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCuentaDolar(JAXBElement<String> value) {
        this.numeroCuentaDolar = value;
    }

    /**
     * Gets the value of the paisEmisDoc property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisEmisDoc() {
        return paisEmisDoc;
    }

    /**
     * Sets the value of the paisEmisDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisEmisDoc(JAXBElement<String> value) {
        this.paisEmisDoc = value;
    }

    /**
     * Gets the value of the paisNac property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisNac() {
        return paisNac;
    }

    /**
     * Sets the value of the paisNac property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisNac(JAXBElement<String> value) {
        this.paisNac = value;
    }

    /**
     * Gets the value of the sucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursal() {
        return sucursal;
    }

    /**
     * Sets the value of the sucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursal(JAXBElement<String> value) {
        this.sucursal = value;
    }

    /**
     * Gets the value of the sucursalDolar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalDolar() {
        return sucursalDolar;
    }

    /**
     * Sets the value of the sucursalDolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalDolar(JAXBElement<String> value) {
        this.sucursalDolar = value;
    }

    /**
     * Gets the value of the tipoCambio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCambio() {
        return tipoCambio;
    }

    /**
     * Sets the value of the tipoCambio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCambio(JAXBElement<String> value) {
        this.tipoCambio = value;
    }

    /**
     * Gets the value of the tipoDocBcra property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoDocBcra() {
        return tipoDocBcra;
    }

    /**
     * Sets the value of the tipoDocBcra property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoDocBcra(JAXBElement<String> value) {
        this.tipoDocBcra = value;
    }

}

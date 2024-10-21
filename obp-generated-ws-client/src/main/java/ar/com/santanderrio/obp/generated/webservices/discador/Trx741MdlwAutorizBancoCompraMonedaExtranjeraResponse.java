
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx741MdlwAutorizBancoCompraMonedaExtranjeraResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx741MdlwAutorizBancoCompraMonedaExtranjeraResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CuentaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DivisaCuentaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DivisaCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteAcreditar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteCotizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteDebitar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NioCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NioDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroBoleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SpredAplicado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx741MdlwAutorizBancoCompraMonedaExtranjeraResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", propOrder = {
    "cuentaCredito",
    "cuentaDebito",
    "divisaCuentaCredito",
    "divisaCuentaDebito",
    "importeAcreditar",
    "importeCotizacion",
    "importeDebitar",
    "nioCredito",
    "nioDebito",
    "nroBoleto",
    "spredAplicado"
})
public class Trx741MdlwAutorizBancoCompraMonedaExtranjeraResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CuentaCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> cuentaCredito;
    @XmlElementRef(name = "CuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> cuentaDebito;
    @XmlElementRef(name = "DivisaCuentaCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> divisaCuentaCredito;
    @XmlElementRef(name = "DivisaCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> divisaCuentaDebito;
    @XmlElementRef(name = "ImporteAcreditar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> importeAcreditar;
    @XmlElementRef(name = "ImporteCotizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> importeCotizacion;
    @XmlElementRef(name = "ImporteDebitar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> importeDebitar;
    @XmlElementRef(name = "NioCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> nioCredito;
    @XmlElementRef(name = "NioDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> nioDebito;
    @XmlElementRef(name = "NroBoleto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> nroBoleto;
    @XmlElementRef(name = "SpredAplicado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> spredAplicado;

    /**
     * Gets the value of the cuentaCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaCredito() {
        return cuentaCredito;
    }

    /**
     * Sets the value of the cuentaCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaCredito(JAXBElement<String> value) {
        this.cuentaCredito = value;
    }

    /**
     * Gets the value of the cuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaDebito() {
        return cuentaDebito;
    }

    /**
     * Sets the value of the cuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaDebito(JAXBElement<String> value) {
        this.cuentaDebito = value;
    }

    /**
     * Gets the value of the divisaCuentaCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDivisaCuentaCredito() {
        return divisaCuentaCredito;
    }

    /**
     * Sets the value of the divisaCuentaCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDivisaCuentaCredito(JAXBElement<String> value) {
        this.divisaCuentaCredito = value;
    }

    /**
     * Gets the value of the divisaCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDivisaCuentaDebito() {
        return divisaCuentaDebito;
    }

    /**
     * Sets the value of the divisaCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDivisaCuentaDebito(JAXBElement<String> value) {
        this.divisaCuentaDebito = value;
    }

    /**
     * Gets the value of the importeAcreditar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteAcreditar() {
        return importeAcreditar;
    }

    /**
     * Sets the value of the importeAcreditar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteAcreditar(JAXBElement<String> value) {
        this.importeAcreditar = value;
    }

    /**
     * Gets the value of the importeCotizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteCotizacion() {
        return importeCotizacion;
    }

    /**
     * Sets the value of the importeCotizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteCotizacion(JAXBElement<String> value) {
        this.importeCotizacion = value;
    }

    /**
     * Gets the value of the importeDebitar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteDebitar() {
        return importeDebitar;
    }

    /**
     * Sets the value of the importeDebitar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteDebitar(JAXBElement<String> value) {
        this.importeDebitar = value;
    }

    /**
     * Gets the value of the nioCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNioCredito() {
        return nioCredito;
    }

    /**
     * Sets the value of the nioCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNioCredito(JAXBElement<String> value) {
        this.nioCredito = value;
    }

    /**
     * Gets the value of the nioDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNioDebito() {
        return nioDebito;
    }

    /**
     * Sets the value of the nioDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNioDebito(JAXBElement<String> value) {
        this.nioDebito = value;
    }

    /**
     * Gets the value of the nroBoleto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroBoleto() {
        return nroBoleto;
    }

    /**
     * Sets the value of the nroBoleto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroBoleto(JAXBElement<String> value) {
        this.nroBoleto = value;
    }

    /**
     * Gets the value of the spredAplicado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSpredAplicado() {
        return spredAplicado;
    }

    /**
     * Sets the value of the spredAplicado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSpredAplicado(JAXBElement<String> value) {
        this.spredAplicado = value;
    }

}

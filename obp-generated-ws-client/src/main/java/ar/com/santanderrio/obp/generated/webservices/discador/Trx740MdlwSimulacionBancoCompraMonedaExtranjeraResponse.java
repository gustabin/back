
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx740MdlwSimulacionBancoCompraMonedaExtranjeraResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx740MdlwSimulacionBancoCompraMonedaExtranjeraResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="ImporteCotizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SPRED" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx740MdlwSimulacionBancoCompraMonedaExtranjeraResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx740", propOrder = {
    "importeCotizacion",
    "importeCredito",
    "importeDebito",
    "spred"
})
public class Trx740MdlwSimulacionBancoCompraMonedaExtranjeraResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "ImporteCotizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx740", type = JAXBElement.class)
    protected JAXBElement<String> importeCotizacion;
    @XmlElementRef(name = "ImporteCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx740", type = JAXBElement.class)
    protected JAXBElement<String> importeCredito;
    @XmlElementRef(name = "ImporteDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx740", type = JAXBElement.class)
    protected JAXBElement<String> importeDebito;
    @XmlElementRef(name = "SPRED", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx740", type = JAXBElement.class)
    protected JAXBElement<String> spred;

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
     * Gets the value of the importeCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteCredito() {
        return importeCredito;
    }

    /**
     * Sets the value of the importeCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteCredito(JAXBElement<String> value) {
        this.importeCredito = value;
    }

    /**
     * Gets the value of the importeDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteDebito() {
        return importeDebito;
    }

    /**
     * Sets the value of the importeDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteDebito(JAXBElement<String> value) {
        this.importeDebito = value;
    }

    /**
     * Gets the value of the spred property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSPRED() {
        return spred;
    }

    /**
     * Sets the value of the spred property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSPRED(JAXBElement<String> value) {
        this.spred = value;
    }

}

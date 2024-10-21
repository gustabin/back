
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx050MdlwConsultaCotizacionTransferenciaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx050MdlwConsultaCotizacionTransferenciaResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CotizacionTransferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteAcreditar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteDebitar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx050MdlwConsultaCotizacionTransferenciaResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx050", propOrder = {
    "cotizacionTransferencia",
    "importeAcreditar",
    "importeDebitar"
})
public class Trx050MdlwConsultaCotizacionTransferenciaResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CotizacionTransferencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx050", type = JAXBElement.class)
    protected JAXBElement<String> cotizacionTransferencia;
    @XmlElementRef(name = "ImporteAcreditar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx050", type = JAXBElement.class)
    protected JAXBElement<String> importeAcreditar;
    @XmlElementRef(name = "ImporteDebitar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx050", type = JAXBElement.class)
    protected JAXBElement<String> importeDebitar;

    /**
     * Gets the value of the cotizacionTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCotizacionTransferencia() {
        return cotizacionTransferencia;
    }

    /**
     * Sets the value of the cotizacionTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCotizacionTransferencia(JAXBElement<String> value) {
        this.cotizacionTransferencia = value;
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

}

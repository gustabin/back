
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx737MdlwConsultaCotizacionMonedaExtranjeraResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx737MdlwConsultaCotizacionMonedaExtranjeraResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CotizSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "Trx737MdlwConsultaCotizacionMonedaExtranjeraResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", propOrder = {
    "cotizSalida",
    "importeSalida",
    "spredAplicado"
})
public class Trx737MdlwConsultaCotizacionMonedaExtranjeraResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CotizSalida", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", type = JAXBElement.class)
    protected JAXBElement<String> cotizSalida;
    @XmlElementRef(name = "ImporteSalida", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", type = JAXBElement.class)
    protected JAXBElement<String> importeSalida;
    @XmlElementRef(name = "SpredAplicado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", type = JAXBElement.class)
    protected JAXBElement<String> spredAplicado;

    /**
     * Gets the value of the cotizSalida property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCotizSalida() {
        return cotizSalida;
    }

    /**
     * Sets the value of the cotizSalida property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCotizSalida(JAXBElement<String> value) {
        this.cotizSalida = value;
    }

    /**
     * Gets the value of the importeSalida property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteSalida() {
        return importeSalida;
    }

    /**
     * Sets the value of the importeSalida property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteSalida(JAXBElement<String> value) {
        this.importeSalida = value;
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

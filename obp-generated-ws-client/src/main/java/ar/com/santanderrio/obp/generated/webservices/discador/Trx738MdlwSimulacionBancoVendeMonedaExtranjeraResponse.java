
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx738MdlwSimulacionBancoVendeMonedaExtranjeraResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx738MdlwSimulacionBancoVendeMonedaExtranjeraResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxMonedaResponseBase">
 *       &lt;sequence>
 *         &lt;element name="NUMBOLE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NUMIDEN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PORIMPU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TIPOID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TOTCARG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx738MdlwSimulacionBancoVendeMonedaExtranjeraResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx738", propOrder = {
    "numbole",
    "numiden",
    "porimpu",
    "tipoid",
    "totcarg"
})
public class Trx738MdlwSimulacionBancoVendeMonedaExtranjeraResponse
    extends TrxMonedaResponseBase
{

    @XmlElementRef(name = "NUMBOLE", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx738", type = JAXBElement.class)
    protected JAXBElement<String> numbole;
    @XmlElementRef(name = "NUMIDEN", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx738", type = JAXBElement.class)
    protected JAXBElement<String> numiden;
    @XmlElementRef(name = "PORIMPU", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx738", type = JAXBElement.class)
    protected JAXBElement<String> porimpu;
    @XmlElementRef(name = "TIPOID", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx738", type = JAXBElement.class)
    protected JAXBElement<String> tipoid;
    @XmlElementRef(name = "TOTCARG", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx738", type = JAXBElement.class)
    protected JAXBElement<String> totcarg;

    /**
     * Gets the value of the numbole property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNUMBOLE() {
        return numbole;
    }

    /**
     * Sets the value of the numbole property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNUMBOLE(JAXBElement<String> value) {
        this.numbole = value;
    }

    /**
     * Gets the value of the numiden property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNUMIDEN() {
        return numiden;
    }

    /**
     * Sets the value of the numiden property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNUMIDEN(JAXBElement<String> value) {
        this.numiden = value;
    }

    /**
     * Gets the value of the porimpu property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPORIMPU() {
        return porimpu;
    }

    /**
     * Sets the value of the porimpu property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPORIMPU(JAXBElement<String> value) {
        this.porimpu = value;
    }

    /**
     * Gets the value of the tipoid property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTIPOID() {
        return tipoid;
    }

    /**
     * Sets the value of the tipoid property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTIPOID(JAXBElement<String> value) {
        this.tipoid = value;
    }

    /**
     * Gets the value of the totcarg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTOTCARG() {
        return totcarg;
    }

    /**
     * Sets the value of the totcarg property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTOTCARG(JAXBElement<String> value) {
        this.totcarg = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrxMonoProductoBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrxMonoProductoBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Delimitador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HeaderTrama" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LongitudMensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StatusResult" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}StatusResult" minOccurs="0"/>
 *         &lt;element name="Tipoclave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrxMonoProductoBase", propOrder = {
    "delimitador",
    "headerTrama",
    "longitudMensaje",
    "nup",
    "statusResult",
    "tipoclave"
})
@XmlSeeAlso({
    Trx903CnsEsMonoProducto120Response.class,
    Trx703CnsEsMonoProductoResponse.class
})
public class TrxMonoProductoBase
    extends ResponseBase
{

    @XmlElementRef(name = "Delimitador", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> delimitador;
    @XmlElementRef(name = "HeaderTrama", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> headerTrama;
    @XmlElementRef(name = "LongitudMensaje", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> longitudMensaje;
    @XmlElementRef(name = "Nup", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> nup;
    @XmlElementRef(name = "StatusResult", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<StatusResult> statusResult;
    @XmlElementRef(name = "Tipoclave", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> tipoclave;

    /**
     * Gets the value of the delimitador property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelimitador() {
        return delimitador;
    }

    /**
     * Sets the value of the delimitador property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelimitador(JAXBElement<String> value) {
        this.delimitador = value;
    }

    /**
     * Gets the value of the headerTrama property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHeaderTrama() {
        return headerTrama;
    }

    /**
     * Sets the value of the headerTrama property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHeaderTrama(JAXBElement<String> value) {
        this.headerTrama = value;
    }

    /**
     * Gets the value of the longitudMensaje property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLongitudMensaje() {
        return longitudMensaje;
    }

    /**
     * Sets the value of the longitudMensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLongitudMensaje(JAXBElement<String> value) {
        this.longitudMensaje = value;
    }

    /**
     * Gets the value of the nup property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNup() {
        return nup;
    }

    /**
     * Sets the value of the nup property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNup(JAXBElement<String> value) {
        this.nup = value;
    }

    /**
     * Gets the value of the statusResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StatusResult }{@code >}
     *     
     */
    public JAXBElement<StatusResult> getStatusResult() {
        return statusResult;
    }

    /**
     * Sets the value of the statusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StatusResult }{@code >}
     *     
     */
    public void setStatusResult(JAXBElement<StatusResult> value) {
        this.statusResult = value;
    }

    /**
     * Gets the value of the tipoclave property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoclave() {
        return tipoclave;
    }

    /**
     * Sets the value of the tipoclave property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoclave(JAXBElement<String> value) {
        this.tipoclave = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx908GetVDNyVQaTransferirRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx908GetVDNyVQaTransferirRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="CodigoApp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OpcionIVR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VdnIngreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx908GetVDNyVQaTransferirRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx908", propOrder = {
    "codigoApp",
    "opcionIVR",
    "vdnIngreso"
})
public class Trx908GetVDNyVQaTransferirRequest
    extends RequestBase
{

    @XmlElementRef(name = "CodigoApp", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx908", type = JAXBElement.class)
    protected JAXBElement<String> codigoApp;
    @XmlElementRef(name = "OpcionIVR", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx908", type = JAXBElement.class)
    protected JAXBElement<String> opcionIVR;
    @XmlElementRef(name = "VdnIngreso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx908", type = JAXBElement.class)
    protected JAXBElement<String> vdnIngreso;

    /**
     * Gets the value of the codigoApp property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoApp() {
        return codigoApp;
    }

    /**
     * Sets the value of the codigoApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoApp(JAXBElement<String> value) {
        this.codigoApp = value;
    }

    /**
     * Gets the value of the opcionIVR property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOpcionIVR() {
        return opcionIVR;
    }

    /**
     * Sets the value of the opcionIVR property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOpcionIVR(JAXBElement<String> value) {
        this.opcionIVR = value;
    }

    /**
     * Gets the value of the vdnIngreso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVdnIngreso() {
        return vdnIngreso;
    }

    /**
     * Sets the value of the vdnIngreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVdnIngreso(JAXBElement<String> value) {
        this.vdnIngreso = value;
    }

}

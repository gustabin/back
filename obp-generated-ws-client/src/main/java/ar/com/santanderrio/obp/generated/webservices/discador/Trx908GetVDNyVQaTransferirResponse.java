
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx908GetVDNyVQaTransferirResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx908GetVDNyVQaTransferirResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="VdnSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VqSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx908GetVDNyVQaTransferirResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx908", propOrder = {
    "vdnSalida",
    "vqSalida"
})
public class Trx908GetVDNyVQaTransferirResponse
    extends ResponseBase
{

    @XmlElementRef(name = "VdnSalida", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx908", type = JAXBElement.class)
    protected JAXBElement<String> vdnSalida;
    @XmlElementRef(name = "VqSalida", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx908", type = JAXBElement.class)
    protected JAXBElement<String> vqSalida;

    /**
     * Gets the value of the vdnSalida property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVdnSalida() {
        return vdnSalida;
    }

    /**
     * Sets the value of the vdnSalida property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVdnSalida(JAXBElement<String> value) {
        this.vdnSalida = value;
    }

    /**
     * Gets the value of the vqSalida property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVqSalida() {
        return vqSalida;
    }

    /**
     * Sets the value of the vqSalida property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVqSalida(JAXBElement<String> value) {
        this.vqSalida = value;
    }

}

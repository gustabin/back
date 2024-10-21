
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx728TBanelcoVIDRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx728TBanelcoVIDRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="TarjetaBanelco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx728TBanelcoVIDRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx728", propOrder = {
    "tarjetaBanelco"
})
public class Trx728TBanelcoVIDRequest
    extends RequestBase
{

    @XmlElementRef(name = "TarjetaBanelco", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx728", type = JAXBElement.class)
    protected JAXBElement<String> tarjetaBanelco;

    /**
     * Gets the value of the tarjetaBanelco property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTarjetaBanelco() {
        return tarjetaBanelco;
    }

    /**
     * Sets the value of the tarjetaBanelco property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTarjetaBanelco(JAXBElement<String> value) {
        this.tarjetaBanelco = value;
    }

}

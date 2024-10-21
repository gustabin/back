
package ar.com.santanderrio.obp.generated.webservices.segmento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SetBrandSelectOnlineResult" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}BrandSelectOnlineResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "setBrandSelectOnlineResult"
})
@XmlRootElement(name = "SetBrandSelectOnlineResponse")
public class SetBrandSelectOnlineResponse {

    @XmlElementRef(name = "SetBrandSelectOnlineResult", namespace = "BSR.CRMBE.API.Services", type = JAXBElement.class)
    protected JAXBElement<BrandSelectOnlineResponse> setBrandSelectOnlineResult;

    /**
     * Gets the value of the setBrandSelectOnlineResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BrandSelectOnlineResponse }{@code >}
     *     
     */
    public JAXBElement<BrandSelectOnlineResponse> getSetBrandSelectOnlineResult() {
        return setBrandSelectOnlineResult;
    }

    /**
     * Sets the value of the setBrandSelectOnlineResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BrandSelectOnlineResponse }{@code >}
     *     
     */
    public void setSetBrandSelectOnlineResult(JAXBElement<BrandSelectOnlineResponse> value) {
        this.setBrandSelectOnlineResult = value;
    }

}

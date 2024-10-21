
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
 *         &lt;element name="GetClientDetailedPositionByNupResult" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}GetClientDetailedPositionByNupResponse" minOccurs="0"/>
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
    "getClientDetailedPositionByNupResult"
})
@XmlRootElement(name = "GetClientDetailedPositionByNupResponse")
public class GetClientDetailedPositionByNupResponse {

    @XmlElementRef(name = "GetClientDetailedPositionByNupResult", namespace = "BSR.CRMBE.API.Services", type = JAXBElement.class)
    protected JAXBElement<GetClientDetailedPositionByNupResponse2> getClientDetailedPositionByNupResult;

    /**
     * Gets the value of the getClientDetailedPositionByNupResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetClientDetailedPositionByNupResponse2 }{@code >}
     *     
     */
    public JAXBElement<GetClientDetailedPositionByNupResponse2> getGetClientDetailedPositionByNupResult() {
        return getClientDetailedPositionByNupResult;
    }

    /**
     * Sets the value of the getClientDetailedPositionByNupResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetClientDetailedPositionByNupResponse2 }{@code >}
     *     
     */
    public void setGetClientDetailedPositionByNupResult(JAXBElement<GetClientDetailedPositionByNupResponse2> value) {
        this.getClientDetailedPositionByNupResult = value;
    }

}

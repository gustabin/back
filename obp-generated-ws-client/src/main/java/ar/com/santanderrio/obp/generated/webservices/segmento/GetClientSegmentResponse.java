
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
 *         &lt;element name="GetClientSegmentResult" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}GetClientSegmentDataResponse" minOccurs="0"/>
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
    "getClientSegmentResult"
})
@XmlRootElement(name = "GetClientSegmentResponse")
public class GetClientSegmentResponse {

    @XmlElementRef(name = "GetClientSegmentResult", namespace = "BSR.CRMBE.API.Services", type = JAXBElement.class)
    protected JAXBElement<GetClientSegmentDataResponse> getClientSegmentResult;

    /**
     * Gets the value of the getClientSegmentResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetClientSegmentDataResponse }{@code >}
     *     
     */
    public JAXBElement<GetClientSegmentDataResponse> getGetClientSegmentResult() {
        return getClientSegmentResult;
    }

    /**
     * Sets the value of the getClientSegmentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetClientSegmentDataResponse }{@code >}
     *     
     */
    public void setGetClientSegmentResult(JAXBElement<GetClientSegmentDataResponse> value) {
        this.getClientSegmentResult = value;
    }

}

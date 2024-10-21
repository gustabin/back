
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
 *         &lt;element name="GetClientSelectSegmentResult" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}GetClientSegmentSelectDataResponse" minOccurs="0"/>
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
    "getClientSelectSegmentResult"
})
@XmlRootElement(name = "GetClientSelectSegmentResponse")
public class GetClientSelectSegmentResponse {

    @XmlElementRef(name = "GetClientSelectSegmentResult", namespace = "BSR.CRMBE.API.Services", type = JAXBElement.class)
    protected JAXBElement<GetClientSegmentSelectDataResponse> getClientSelectSegmentResult;

    /**
     * Gets the value of the getClientSelectSegmentResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetClientSegmentSelectDataResponse }{@code >}
     *     
     */
    public JAXBElement<GetClientSegmentSelectDataResponse> getGetClientSelectSegmentResult() {
        return getClientSelectSegmentResult;
    }

    /**
     * Sets the value of the getClientSelectSegmentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetClientSegmentSelectDataResponse }{@code >}
     *     
     */
    public void setGetClientSelectSegmentResult(JAXBElement<GetClientSegmentSelectDataResponse> value) {
        this.getClientSelectSegmentResult = value;
    }

}

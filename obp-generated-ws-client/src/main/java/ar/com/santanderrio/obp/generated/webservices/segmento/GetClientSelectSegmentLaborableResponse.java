
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
 *         &lt;element name="GetClientSelectSegmentLaborableResult" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}GetClientSegmentSelectLaborableDataResponse" minOccurs="0"/>
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
    "getClientSelectSegmentLaborableResult"
})
@XmlRootElement(name = "GetClientSelectSegmentLaborableResponse")
public class GetClientSelectSegmentLaborableResponse {

    @XmlElementRef(name = "GetClientSelectSegmentLaborableResult", namespace = "BSR.CRMBE.API.Services", type = JAXBElement.class)
    protected JAXBElement<GetClientSegmentSelectLaborableDataResponse> getClientSelectSegmentLaborableResult;

    /**
     * Gets the value of the getClientSelectSegmentLaborableResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetClientSegmentSelectLaborableDataResponse }{@code >}
     *     
     */
    public JAXBElement<GetClientSegmentSelectLaborableDataResponse> getGetClientSelectSegmentLaborableResult() {
        return getClientSelectSegmentLaborableResult;
    }

    /**
     * Sets the value of the getClientSelectSegmentLaborableResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetClientSegmentSelectLaborableDataResponse }{@code >}
     *     
     */
    public void setGetClientSelectSegmentLaborableResult(JAXBElement<GetClientSegmentSelectLaborableDataResponse> value) {
        this.getClientSelectSegmentLaborableResult = value;
    }

}

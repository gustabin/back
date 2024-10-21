
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
 *         &lt;element name="GetPersonTypeAndSegmentResult" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}GetPersonTypeAndSegmentResponse" minOccurs="0"/>
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
    "getPersonTypeAndSegmentResult"
})
@XmlRootElement(name = "GetPersonTypeAndSegmentResponse")
public class GetPersonTypeAndSegmentResponse {

    @XmlElementRef(name = "GetPersonTypeAndSegmentResult", namespace = "BSR.CRMBE.API.Services", type = JAXBElement.class)
    protected JAXBElement<GetPersonTypeAndSegmentResponse2> getPersonTypeAndSegmentResult;

    /**
     * Gets the value of the getPersonTypeAndSegmentResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetPersonTypeAndSegmentResponse2 }{@code >}
     *     
     */
    public JAXBElement<GetPersonTypeAndSegmentResponse2> getGetPersonTypeAndSegmentResult() {
        return getPersonTypeAndSegmentResult;
    }

    /**
     * Sets the value of the getPersonTypeAndSegmentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetPersonTypeAndSegmentResponse2 }{@code >}
     *     
     */
    public void setGetPersonTypeAndSegmentResult(JAXBElement<GetPersonTypeAndSegmentResponse2> value) {
        this.getPersonTypeAndSegmentResult = value;
    }

}

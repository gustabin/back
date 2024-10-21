
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
 *         &lt;element name="GetLastEntryOBPResult" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}GetLastEntryOBPResponse" minOccurs="0"/>
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
    "getLastEntryOBPResult"
})
@XmlRootElement(name = "GetLastEntryOBPResponse")
public class GetLastEntryOBPResponse {

    @XmlElementRef(name = "GetLastEntryOBPResult", namespace = "BSR.CRMBE.API.Services", type = JAXBElement.class)
    protected JAXBElement<GetLastEntryOBPResponse2> getLastEntryOBPResult;

    /**
     * Gets the value of the getLastEntryOBPResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetLastEntryOBPResponse2 }{@code >}
     *     
     */
    public JAXBElement<GetLastEntryOBPResponse2> getGetLastEntryOBPResult() {
        return getLastEntryOBPResult;
    }

    /**
     * Sets the value of the getLastEntryOBPResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetLastEntryOBPResponse2 }{@code >}
     *     
     */
    public void setGetLastEntryOBPResult(JAXBElement<GetLastEntryOBPResponse2> value) {
        this.getLastEntryOBPResult = value;
    }

}

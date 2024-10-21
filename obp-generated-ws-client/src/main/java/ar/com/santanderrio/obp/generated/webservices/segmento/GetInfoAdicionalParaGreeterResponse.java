
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
 *         &lt;element name="GetInfoAdicionalParaGreeterResult" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}GetInfoAdicionalParaGreeterResponse" minOccurs="0"/>
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
    "getInfoAdicionalParaGreeterResult"
})
@XmlRootElement(name = "GetInfoAdicionalParaGreeterResponse")
public class GetInfoAdicionalParaGreeterResponse {

    @XmlElementRef(name = "GetInfoAdicionalParaGreeterResult", namespace = "BSR.CRMBE.API.Services", type = JAXBElement.class)
    protected JAXBElement<GetInfoAdicionalParaGreeterResponse2> getInfoAdicionalParaGreeterResult;

    /**
     * Gets the value of the getInfoAdicionalParaGreeterResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetInfoAdicionalParaGreeterResponse2 }{@code >}
     *     
     */
    public JAXBElement<GetInfoAdicionalParaGreeterResponse2> getGetInfoAdicionalParaGreeterResult() {
        return getInfoAdicionalParaGreeterResult;
    }

    /**
     * Sets the value of the getInfoAdicionalParaGreeterResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetInfoAdicionalParaGreeterResponse2 }{@code >}
     *     
     */
    public void setGetInfoAdicionalParaGreeterResult(JAXBElement<GetInfoAdicionalParaGreeterResponse2> value) {
        this.getInfoAdicionalParaGreeterResult = value;
    }

}

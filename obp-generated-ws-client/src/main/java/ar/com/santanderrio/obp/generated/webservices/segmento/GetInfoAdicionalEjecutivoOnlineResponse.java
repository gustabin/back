
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
 *         &lt;element name="GetInfoAdicionalEjecutivoOnlineResult" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}GetInfoAdicionalEjecutivoOnlineResponse" minOccurs="0"/>
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
    "getInfoAdicionalEjecutivoOnlineResult"
})
@XmlRootElement(name = "GetInfoAdicionalEjecutivoOnlineResponse")
public class GetInfoAdicionalEjecutivoOnlineResponse {

    @XmlElementRef(name = "GetInfoAdicionalEjecutivoOnlineResult", namespace = "BSR.CRMBE.API.Services", type = JAXBElement.class)
    protected JAXBElement<GetInfoAdicionalEjecutivoOnlineResponse2> getInfoAdicionalEjecutivoOnlineResult;

    /**
     * Gets the value of the getInfoAdicionalEjecutivoOnlineResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetInfoAdicionalEjecutivoOnlineResponse2 }{@code >}
     *     
     */
    public JAXBElement<GetInfoAdicionalEjecutivoOnlineResponse2> getGetInfoAdicionalEjecutivoOnlineResult() {
        return getInfoAdicionalEjecutivoOnlineResult;
    }

    /**
     * Sets the value of the getInfoAdicionalEjecutivoOnlineResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetInfoAdicionalEjecutivoOnlineResponse2 }{@code >}
     *     
     */
    public void setGetInfoAdicionalEjecutivoOnlineResult(JAXBElement<GetInfoAdicionalEjecutivoOnlineResponse2> value) {
        this.getInfoAdicionalEjecutivoOnlineResult = value;
    }

}


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
 *         &lt;element name="GetInfoAdicionalEjecutivoRemotoResult" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}GetInfoAdicionalEjecutivoRemotoResponse" minOccurs="0"/>
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
    "getInfoAdicionalEjecutivoRemotoResult"
})
@XmlRootElement(name = "GetInfoAdicionalEjecutivoRemotoResponse")
public class GetInfoAdicionalEjecutivoRemotoResponse {

    @XmlElementRef(name = "GetInfoAdicionalEjecutivoRemotoResult", namespace = "BSR.CRMBE.API.Services", type = JAXBElement.class)
    protected JAXBElement<GetInfoAdicionalEjecutivoRemotoResponse2> getInfoAdicionalEjecutivoRemotoResult;

    /**
     * Gets the value of the getInfoAdicionalEjecutivoRemotoResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetInfoAdicionalEjecutivoRemotoResponse2 }{@code >}
     *     
     */
    public JAXBElement<GetInfoAdicionalEjecutivoRemotoResponse2> getGetInfoAdicionalEjecutivoRemotoResult() {
        return getInfoAdicionalEjecutivoRemotoResult;
    }

    /**
     * Sets the value of the getInfoAdicionalEjecutivoRemotoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetInfoAdicionalEjecutivoRemotoResponse2 }{@code >}
     *     
     */
    public void setGetInfoAdicionalEjecutivoRemotoResult(JAXBElement<GetInfoAdicionalEjecutivoRemotoResponse2> value) {
        this.getInfoAdicionalEjecutivoRemotoResult = value;
    }

}

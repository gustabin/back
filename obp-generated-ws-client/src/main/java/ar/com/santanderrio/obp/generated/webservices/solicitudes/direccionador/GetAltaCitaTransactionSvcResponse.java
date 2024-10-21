
package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaSvcResponse;


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
 *         &lt;element name="GetAltaCitaTransactionSvcResult" type="{http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts}GetAltaCitaSvcResponse" minOccurs="0"/>
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
    "getAltaCitaTransactionSvcResult"
})
@XmlRootElement(name = "GetAltaCitaTransactionSvcResponse")
public class GetAltaCitaTransactionSvcResponse {

    @XmlElementRef(name = "GetAltaCitaTransactionSvcResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<GetAltaCitaSvcResponse> getAltaCitaTransactionSvcResult;

    /**
     * Gets the value of the getAltaCitaTransactionSvcResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetAltaCitaSvcResponse }{@code >}
     *     
     */
    public JAXBElement<GetAltaCitaSvcResponse> getGetAltaCitaTransactionSvcResult() {
        return getAltaCitaTransactionSvcResult;
    }

    /**
     * Sets the value of the getAltaCitaTransactionSvcResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetAltaCitaSvcResponse }{@code >}
     *     
     */
    public void setGetAltaCitaTransactionSvcResult(JAXBElement<GetAltaCitaSvcResponse> value) {
        this.getAltaCitaTransactionSvcResult = value;
    }

}

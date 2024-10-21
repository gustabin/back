
package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaConMotivoSvcResponse;


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
 *         &lt;element name="GetAltaCitaTransactionConMotivoSvcResult" type="{http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts}GetAltaCitaConMotivoSvcResponse" minOccurs="0"/>
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
    "getAltaCitaTransactionConMotivoSvcResult"
})
@XmlRootElement(name = "GetAltaCitaTransactionConMotivoSvcResponse")
public class GetAltaCitaTransactionConMotivoSvcResponse {

    @XmlElementRef(name = "GetAltaCitaTransactionConMotivoSvcResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<GetAltaCitaConMotivoSvcResponse> getAltaCitaTransactionConMotivoSvcResult;

    /**
     * Gets the value of the getAltaCitaTransactionConMotivoSvcResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetAltaCitaConMotivoSvcResponse }{@code >}
     *     
     */
    public JAXBElement<GetAltaCitaConMotivoSvcResponse> getGetAltaCitaTransactionConMotivoSvcResult() {
        return getAltaCitaTransactionConMotivoSvcResult;
    }

    /**
     * Sets the value of the getAltaCitaTransactionConMotivoSvcResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetAltaCitaConMotivoSvcResponse }{@code >}
     *     
     */
    public void setGetAltaCitaTransactionConMotivoSvcResult(JAXBElement<GetAltaCitaConMotivoSvcResponse> value) {
        this.getAltaCitaTransactionConMotivoSvcResult = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador;

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
 *         &lt;element name="GetAltaCitaTransactionConEmailCuitSvcResult" type="{http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts}GetAltaCitaTransactionConEmailCuitSvcResponse" minOccurs="0"/>
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
    "getAltaCitaTransactionConEmailCuitSvcResult"
})
@XmlRootElement(name = "GetAltaCitaTransactionConEmailCuitSvcResponse")
public class GetAltaCitaTransactionConEmailCuitSvcResponse {

    @XmlElementRef(name = "GetAltaCitaTransactionConEmailCuitSvcResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaTransactionConEmailCuitSvcResponse> getAltaCitaTransactionConEmailCuitSvcResult;

    /**
     * Gets the value of the getAltaCitaTransactionConEmailCuitSvcResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaTransactionConEmailCuitSvcResponse }{@code >}
     *     
     */
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaTransactionConEmailCuitSvcResponse> getGetAltaCitaTransactionConEmailCuitSvcResult() {
        return getAltaCitaTransactionConEmailCuitSvcResult;
    }

    /**
     * Sets the value of the getAltaCitaTransactionConEmailCuitSvcResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaTransactionConEmailCuitSvcResponse }{@code >}
     *     
     */
    public void setGetAltaCitaTransactionConEmailCuitSvcResult(JAXBElement<ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaTransactionConEmailCuitSvcResponse> value) {
        this.getAltaCitaTransactionConEmailCuitSvcResult = value;
    }

}

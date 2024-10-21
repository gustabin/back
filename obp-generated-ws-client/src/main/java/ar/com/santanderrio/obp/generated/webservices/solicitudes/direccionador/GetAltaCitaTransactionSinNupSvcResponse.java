
package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaSinNupSvcResponse;


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
 *         &lt;element name="GetAltaCitaTransactionSinNupSvcResult" type="{http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts}GetAltaCitaSinNupSvcResponse" minOccurs="0"/>
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
    "getAltaCitaTransactionSinNupSvcResult"
})
@XmlRootElement(name = "GetAltaCitaTransactionSinNupSvcResponse")
public class GetAltaCitaTransactionSinNupSvcResponse {

    @XmlElementRef(name = "GetAltaCitaTransactionSinNupSvcResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<GetAltaCitaSinNupSvcResponse> getAltaCitaTransactionSinNupSvcResult;

    /**
     * Gets the value of the getAltaCitaTransactionSinNupSvcResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetAltaCitaSinNupSvcResponse }{@code >}
     *     
     */
    public JAXBElement<GetAltaCitaSinNupSvcResponse> getGetAltaCitaTransactionSinNupSvcResult() {
        return getAltaCitaTransactionSinNupSvcResult;
    }

    /**
     * Sets the value of the getAltaCitaTransactionSinNupSvcResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetAltaCitaSinNupSvcResponse }{@code >}
     *     
     */
    public void setGetAltaCitaTransactionSinNupSvcResult(JAXBElement<GetAltaCitaSinNupSvcResponse> value) {
        this.getAltaCitaTransactionSinNupSvcResult = value;
    }

}

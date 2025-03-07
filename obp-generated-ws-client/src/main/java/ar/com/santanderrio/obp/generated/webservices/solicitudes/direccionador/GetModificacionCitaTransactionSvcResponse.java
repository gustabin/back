
package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetModificacionCitaSvcResponse;


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
 *         &lt;element name="GetModificacionCitaTransactionSvcResult" type="{http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts}GetModificacionCitaSvcResponse" minOccurs="0"/>
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
    "getModificacionCitaTransactionSvcResult"
})
@XmlRootElement(name = "GetModificacionCitaTransactionSvcResponse")
public class GetModificacionCitaTransactionSvcResponse {

    @XmlElementRef(name = "GetModificacionCitaTransactionSvcResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<GetModificacionCitaSvcResponse> getModificacionCitaTransactionSvcResult;

    /**
     * Gets the value of the getModificacionCitaTransactionSvcResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetModificacionCitaSvcResponse }{@code >}
     *     
     */
    public JAXBElement<GetModificacionCitaSvcResponse> getGetModificacionCitaTransactionSvcResult() {
        return getModificacionCitaTransactionSvcResult;
    }

    /**
     * Sets the value of the getModificacionCitaTransactionSvcResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetModificacionCitaSvcResponse }{@code >}
     *     
     */
    public void setGetModificacionCitaTransactionSvcResult(JAXBElement<GetModificacionCitaSvcResponse> value) {
        this.getModificacionCitaTransactionSvcResult = value;
    }

}


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
 *         &lt;element name="GetConsultaCitaConMotivoSvcResult" type="{http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts}GetConsultaCitaConMotivoSvcResponse" minOccurs="0"/>
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
    "getConsultaCitaConMotivoSvcResult"
})
@XmlRootElement(name = "GetConsultaCitaConMotivoSvcResponse")
public class GetConsultaCitaConMotivoSvcResponse {

    @XmlElementRef(name = "GetConsultaCitaConMotivoSvcResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConMotivoSvcResponse> getConsultaCitaConMotivoSvcResult;

    /**
     * Gets the value of the getConsultaCitaConMotivoSvcResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConMotivoSvcResponse }{@code >}
     *     
     */
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConMotivoSvcResponse> getGetConsultaCitaConMotivoSvcResult() {
        return getConsultaCitaConMotivoSvcResult;
    }

    /**
     * Sets the value of the getConsultaCitaConMotivoSvcResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConMotivoSvcResponse }{@code >}
     *     
     */
    public void setGetConsultaCitaConMotivoSvcResult(JAXBElement<ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConMotivoSvcResponse> value) {
        this.getConsultaCitaConMotivoSvcResult = value;
    }

}

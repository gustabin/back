
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
 *         &lt;element name="GetConsultaHorariosDisponiblesSvcResult" type="{http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts}GetConsultaHorariosDisponiblesSvcResponse" minOccurs="0"/>
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
    "getConsultaHorariosDisponiblesSvcResult"
})
@XmlRootElement(name = "GetConsultaHorariosDisponiblesSvcResponse")
public class GetConsultaHorariosDisponiblesSvcResponse {

    @XmlElementRef(name = "GetConsultaHorariosDisponiblesSvcResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaHorariosDisponiblesSvcResponse> getConsultaHorariosDisponiblesSvcResult;

    /**
     * Gets the value of the getConsultaHorariosDisponiblesSvcResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaHorariosDisponiblesSvcResponse }{@code >}
     *     
     */
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaHorariosDisponiblesSvcResponse> getGetConsultaHorariosDisponiblesSvcResult() {
        return getConsultaHorariosDisponiblesSvcResult;
    }

    /**
     * Sets the value of the getConsultaHorariosDisponiblesSvcResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaHorariosDisponiblesSvcResponse }{@code >}
     *     
     */
    public void setGetConsultaHorariosDisponiblesSvcResult(JAXBElement<ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaHorariosDisponiblesSvcResponse> value) {
        this.getConsultaHorariosDisponiblesSvcResult = value;
    }

}

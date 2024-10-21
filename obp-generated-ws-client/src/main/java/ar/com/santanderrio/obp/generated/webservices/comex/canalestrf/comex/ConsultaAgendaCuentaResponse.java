
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex;

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
 *         &lt;element name="ConsultaAgendaCuentaResult" type="{Response/Canales}ConsultaAgendaCuentaResponse" minOccurs="0"/>
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
    "consultaAgendaCuentaResult"
})
@XmlRootElement(name = "ConsultaAgendaCuentaResponse")
public class ConsultaAgendaCuentaResponse {

    @XmlElementRef(name = "ConsultaAgendaCuentaResult", namespace = "Comex", type = JAXBElement.class)
    protected JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaAgendaCuentaResponse> consultaAgendaCuentaResult;

    /**
     * Gets the value of the consultaAgendaCuentaResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaAgendaCuentaResponse }{@code >}
     *     
     */
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaAgendaCuentaResponse> getConsultaAgendaCuentaResult() {
        return consultaAgendaCuentaResult;
    }

    /**
     * Sets the value of the consultaAgendaCuentaResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaAgendaCuentaResponse }{@code >}
     *     
     */
    public void setConsultaAgendaCuentaResult(JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaAgendaCuentaResponse> value) {
        this.consultaAgendaCuentaResult = value;
    }

}

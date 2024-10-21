
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
 *         &lt;element name="CargaAgendaCuentaResult" type="{Response/Canales}CargaAgendaCuentaResponse" minOccurs="0"/>
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
    "cargaAgendaCuentaResult"
})
@XmlRootElement(name = "CargaAgendaCuentaResponse")
public class CargaAgendaCuentaResponse {

    @XmlElementRef(name = "CargaAgendaCuentaResult", namespace = "Comex", type = JAXBElement.class)
    protected JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaCuentaResponse> cargaAgendaCuentaResult;

    /**
     * Gets the value of the cargaAgendaCuentaResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaCuentaResponse }{@code >}
     *     
     */
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaCuentaResponse> getCargaAgendaCuentaResult() {
        return cargaAgendaCuentaResult;
    }

    /**
     * Sets the value of the cargaAgendaCuentaResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaCuentaResponse }{@code >}
     *     
     */
    public void setCargaAgendaCuentaResult(JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaCuentaResponse> value) {
        this.cargaAgendaCuentaResult = value;
    }

}

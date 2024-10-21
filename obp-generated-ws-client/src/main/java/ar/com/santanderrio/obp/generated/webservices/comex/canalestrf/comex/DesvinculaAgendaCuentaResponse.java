
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
 *         &lt;element name="DesvinculaAgendaCuentaResult" type="{Response/Canales}DesvinculaAgendaCuentaResponse" minOccurs="0"/>
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
    "desvinculaAgendaCuentaResult"
})
@XmlRootElement(name = "DesvinculaAgendaCuentaResponse")
public class DesvinculaAgendaCuentaResponse {

    @XmlElementRef(name = "DesvinculaAgendaCuentaResult", namespace = "Comex", type = JAXBElement.class)
    protected JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaCuentaResponse> desvinculaAgendaCuentaResult;

    /**
     * Gets the value of the desvinculaAgendaCuentaResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaCuentaResponse }{@code >}
     *     
     */
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaCuentaResponse> getDesvinculaAgendaCuentaResult() {
        return desvinculaAgendaCuentaResult;
    }

    /**
     * Sets the value of the desvinculaAgendaCuentaResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaCuentaResponse }{@code >}
     *     
     */
    public void setDesvinculaAgendaCuentaResult(JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaCuentaResponse> value) {
        this.desvinculaAgendaCuentaResult = value;
    }

}

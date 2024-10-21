
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
 *         &lt;element name="ConsultaMotExcepcionResult" type="{Response/Canales}ConsultaMotExcepcionResponse" minOccurs="0"/>
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
    "consultaMotExcepcionResult"
})
@XmlRootElement(name = "ConsultaMotExcepcionResponse")
public class ConsultaMotExcepcionResponse {

    @XmlElementRef(name = "ConsultaMotExcepcionResult", namespace = "Comex", type = JAXBElement.class)
    protected JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaMotExcepcionResponse> consultaMotExcepcionResult;

    /**
     * Gets the value of the consultaMotExcepcionResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaMotExcepcionResponse }{@code >}
     *     
     */
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaMotExcepcionResponse> getConsultaMotExcepcionResult() {
        return consultaMotExcepcionResult;
    }

    /**
     * Sets the value of the consultaMotExcepcionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaMotExcepcionResponse }{@code >}
     *     
     */
    public void setConsultaMotExcepcionResult(JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaMotExcepcionResponse> value) {
        this.consultaMotExcepcionResult = value;
    }

}

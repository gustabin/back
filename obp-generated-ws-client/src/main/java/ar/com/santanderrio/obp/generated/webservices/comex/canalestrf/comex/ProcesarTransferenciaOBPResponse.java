
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
 *         &lt;element name="ProcesarTransferenciaOBPResult" type="{Response/Canales}ProcesarTransferenciaOBPResponse" minOccurs="0"/>
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
    "procesarTransferenciaOBPResult"
})
@XmlRootElement(name = "ProcesarTransferenciaOBPResponse")
public class ProcesarTransferenciaOBPResponse {

    @XmlElementRef(name = "ProcesarTransferenciaOBPResult", namespace = "Comex", type = JAXBElement.class)
    protected JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse> procesarTransferenciaOBPResult;

    /**
     * Gets the value of the procesarTransferenciaOBPResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse }{@code >}
     *     
     */
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse> getProcesarTransferenciaOBPResult() {
        return procesarTransferenciaOBPResult;
    }

    /**
     * Sets the value of the procesarTransferenciaOBPResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse }{@code >}
     *     
     */
    public void setProcesarTransferenciaOBPResult(JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse> value) {
        this.procesarTransferenciaOBPResult = value;
    }

}

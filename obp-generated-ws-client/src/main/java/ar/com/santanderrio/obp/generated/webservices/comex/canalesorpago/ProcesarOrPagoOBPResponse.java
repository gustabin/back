
package ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago;

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
 *         &lt;element name="ProcesarOrPagoOBPResult" type="{Response/Canales}ProcesarOrPagoOBPResponse" minOccurs="0"/>
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
    "procesarOrPagoOBPResult"
})
@XmlRootElement(name = "ProcesarOrPagoOBPResponse")
public class ProcesarOrPagoOBPResponse {

    @XmlElementRef(name = "ProcesarOrPagoOBPResult", namespace = "Comex", type = JAXBElement.class)
    protected JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse> procesarOrPagoOBPResult;

    /**
     * Gets the value of the procesarOrPagoOBPResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse }{@code >}
     *     
     */
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse> getProcesarOrPagoOBPResult() {
        return procesarOrPagoOBPResult;
    }

    /**
     * Sets the value of the procesarOrPagoOBPResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse }{@code >}
     *     
     */
    public void setProcesarOrPagoOBPResult(JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse> value) {
        this.procesarOrPagoOBPResult = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.CargaAgendaCuentaRequest;


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
 *         &lt;element name="request" type="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf}CargaAgendaCuentaRequest" minOccurs="0"/>
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
    "request"
})
@XmlRootElement(name = "CargaAgendaCuenta")
public class CargaAgendaCuenta {

    @XmlElementRef(name = "request", namespace = "Comex", type = JAXBElement.class)
    protected JAXBElement<CargaAgendaCuentaRequest> request;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CargaAgendaCuentaRequest }{@code >}
     *     
     */
    public JAXBElement<CargaAgendaCuentaRequest> getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CargaAgendaCuentaRequest }{@code >}
     *     
     */
    public void setRequest(JAXBElement<CargaAgendaCuentaRequest> value) {
        this.request = value;
    }

}

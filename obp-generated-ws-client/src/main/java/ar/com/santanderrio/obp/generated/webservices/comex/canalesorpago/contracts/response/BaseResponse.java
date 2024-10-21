
package ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse;


/**
 * <p>Java class for BaseResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Error_Interface" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Error_Sistema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseResponse", propOrder = {
    "errorInterface",
    "errorSistema"
})
@XmlSeeAlso({
    ProcesarOrPagoOBPResponse.class
})
public class BaseResponse {

    @XmlElementRef(name = "Error_Interface", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", type = JAXBElement.class)
    protected JAXBElement<String> errorInterface;
    @XmlElementRef(name = "Error_Sistema", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", type = JAXBElement.class)
    protected JAXBElement<String> errorSistema;

    /**
     * Gets the value of the errorInterface property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getErrorInterface() {
        return errorInterface;
    }

    /**
     * Sets the value of the errorInterface property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setErrorInterface(JAXBElement<String> value) {
        this.errorInterface = value;
    }

    /**
     * Gets the value of the errorSistema property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getErrorSistema() {
        return errorSistema;
    }

    /**
     * Sets the value of the errorSistema property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setErrorSistema(JAXBElement<String> value) {
        this.errorSistema = value;
    }

}

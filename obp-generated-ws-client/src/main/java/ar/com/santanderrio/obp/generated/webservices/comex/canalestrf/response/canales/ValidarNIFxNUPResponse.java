
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.BaseResponse;


/**
 * <p>Java class for ValidarNIFxNUPResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidarNIFxNUPResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="existe_nif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidarNIFxNUPResponse", propOrder = {
    "existeNif"
})
public class ValidarNIFxNUPResponse
    extends BaseResponse
{

    @XmlElementRef(name = "existe_nif", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> existeNif;

    /**
     * Gets the value of the existeNif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExisteNif() {
        return existeNif;
    }

    /**
     * Sets the value of the existeNif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExisteNif(JAXBElement<String> value) {
        this.existeNif = value;
    }

}

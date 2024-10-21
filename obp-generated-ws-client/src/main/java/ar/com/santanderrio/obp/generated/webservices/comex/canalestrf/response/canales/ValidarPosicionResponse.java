
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.BaseResponse;


/**
 * <p>Java class for ValidarPosicionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidarPosicionResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="es_valida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidarPosicionResponse", propOrder = {
    "esValida"
})
public class ValidarPosicionResponse
    extends BaseResponse
{

    @XmlElementRef(name = "es_valida", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> esValida;

    /**
     * Gets the value of the esValida property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEsValida() {
        return esValida;
    }

    /**
     * Sets the value of the esValida property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEsValida(JAXBElement<String> value) {
        this.esValida = value;
    }

}

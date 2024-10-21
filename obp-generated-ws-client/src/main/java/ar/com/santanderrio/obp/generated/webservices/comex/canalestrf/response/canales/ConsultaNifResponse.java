
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.BaseResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfConsNif;


/**
 * <p>Java class for ConsultaNifResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaNifResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="Registros" type="{Domain}ArrayOfConsNif" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaNifResponse", propOrder = {
    "registros"
})
public class ConsultaNifResponse
    extends BaseResponse
{

    @XmlElementRef(name = "Registros", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<ArrayOfConsNif> registros;

    /**
     * Gets the value of the registros property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfConsNif }{@code >}
     *     
     */
    public JAXBElement<ArrayOfConsNif> getRegistros() {
        return registros;
    }

    /**
     * Sets the value of the registros property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfConsNif }{@code >}
     *     
     */
    public void setRegistros(JAXBElement<ArrayOfConsNif> value) {
        this.registros = value;
    }

}

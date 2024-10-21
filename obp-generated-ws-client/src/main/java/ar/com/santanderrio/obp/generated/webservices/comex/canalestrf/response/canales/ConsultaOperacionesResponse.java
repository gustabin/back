
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.CursorResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfOperacion;


/**
 * <p>Java class for ConsultaOperacionesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaOperacionesResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}CursorResponse">
 *       &lt;sequence>
 *         &lt;element name="Registros" type="{Domain}ArrayOfOperacion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaOperacionesResponse", propOrder = {
    "registros"
})
public class ConsultaOperacionesResponse
    extends CursorResponse
{

    @XmlElementRef(name = "Registros", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<ArrayOfOperacion> registros;

    /**
     * Gets the value of the registros property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOperacion }{@code >}
     *     
     */
    public JAXBElement<ArrayOfOperacion> getRegistros() {
        return registros;
    }

    /**
     * Sets the value of the registros property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOperacion }{@code >}
     *     
     */
    public void setRegistros(JAXBElement<ArrayOfOperacion> value) {
        this.registros = value;
    }

}

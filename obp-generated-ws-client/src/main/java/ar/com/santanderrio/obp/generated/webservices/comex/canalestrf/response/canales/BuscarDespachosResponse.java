
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.CursorResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfDespacho;


/**
 * <p>Java class for BuscarDespachosResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BuscarDespachosResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}CursorResponse">
 *       &lt;sequence>
 *         &lt;element name="Registros" type="{Domain}ArrayOfDespacho" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BuscarDespachosResponse", propOrder = {
    "registros"
})
public class BuscarDespachosResponse
    extends CursorResponse
{

    @XmlElementRef(name = "Registros", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<ArrayOfDespacho> registros;

    /**
     * Gets the value of the registros property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDespacho }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDespacho> getRegistros() {
        return registros;
    }

    /**
     * Sets the value of the registros property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDespacho }{@code >}
     *     
     */
    public void setRegistros(JAXBElement<ArrayOfDespacho> value) {
        this.registros = value;
    }

}

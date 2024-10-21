
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.BaseResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfSwift;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfSwiftBody;


/**
 * <p>Java class for ObtenerMsgSwiftResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObtenerMsgSwiftResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="Cabecera" type="{Domain}ArrayOfSwift" minOccurs="0"/>
 *         &lt;element name="Detalle" type="{Domain}ArrayOfSwiftBody" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObtenerMsgSwiftResponse", propOrder = {
    "cabecera",
    "detalle"
})
public class ObtenerMsgSwiftResponse
    extends BaseResponse
{

    @XmlElementRef(name = "Cabecera", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<ArrayOfSwift> cabecera;
    @XmlElementRef(name = "Detalle", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<ArrayOfSwiftBody> detalle;

    /**
     * Gets the value of the cabecera property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSwift }{@code >}
     *     
     */
    public JAXBElement<ArrayOfSwift> getCabecera() {
        return cabecera;
    }

    /**
     * Sets the value of the cabecera property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSwift }{@code >}
     *     
     */
    public void setCabecera(JAXBElement<ArrayOfSwift> value) {
        this.cabecera = value;
    }

    /**
     * Gets the value of the detalle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSwiftBody }{@code >}
     *     
     */
    public JAXBElement<ArrayOfSwiftBody> getDetalle() {
        return detalle;
    }

    /**
     * Sets the value of the detalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSwiftBody }{@code >}
     *     
     */
    public void setDetalle(JAXBElement<ArrayOfSwiftBody> value) {
        this.detalle = value;
    }

}

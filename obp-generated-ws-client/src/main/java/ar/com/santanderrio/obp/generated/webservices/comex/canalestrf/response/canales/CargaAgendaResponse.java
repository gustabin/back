
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.BaseResponse;


/**
 * <p>Java class for CargaAgendaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CargaAgendaResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="Id_Agenda_Nuevo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CargaAgendaResponse", propOrder = {
    "idAgendaNuevo"
})
public class CargaAgendaResponse
    extends BaseResponse
{

    @XmlElementRef(name = "Id_Agenda_Nuevo", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Long> idAgendaNuevo;

    /**
     * Gets the value of the idAgendaNuevo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getIdAgendaNuevo() {
        return idAgendaNuevo;
    }

    /**
     * Sets the value of the idAgendaNuevo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setIdAgendaNuevo(JAXBElement<Long> value) {
        this.idAgendaNuevo = value;
    }

}

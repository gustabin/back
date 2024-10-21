
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx716DetallePagoCapResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx716DetallePagoCapResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="DetallePagosCap" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfStDetallePagoCap" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx716DetallePagoCapResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx716", propOrder = {
    "detallePagosCap"
})
public class Trx716DetallePagoCapResponse
    extends ResponseBase
{

    @XmlElementRef(name = "DetallePagosCap", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx716", type = JAXBElement.class)
    protected JAXBElement<ArrayOfStDetallePagoCap> detallePagosCap;

    /**
     * Gets the value of the detallePagosCap property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfStDetallePagoCap }{@code >}
     *     
     */
    public JAXBElement<ArrayOfStDetallePagoCap> getDetallePagosCap() {
        return detallePagosCap;
    }

    /**
     * Sets the value of the detallePagosCap property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfStDetallePagoCap }{@code >}
     *     
     */
    public void setDetallePagosCap(JAXBElement<ArrayOfStDetallePagoCap> value) {
        this.detallePagosCap = value;
    }

}

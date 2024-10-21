
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx15ConsultaCotizacionDivisaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx15ConsultaCotizacionDivisaResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Cotizaciones" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx015}ArrayOfTrx15ConsultaCotizacionDivisaIterationResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx15ConsultaCotizacionDivisaResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx015", propOrder = {
    "cotizaciones"
})
public class Trx15ConsultaCotizacionDivisaResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "Cotizaciones", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx015", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrx15ConsultaCotizacionDivisaIterationResponse> cotizaciones;

    /**
     * Gets the value of the cotizaciones property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx15ConsultaCotizacionDivisaIterationResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrx15ConsultaCotizacionDivisaIterationResponse> getCotizaciones() {
        return cotizaciones;
    }

    /**
     * Sets the value of the cotizaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx15ConsultaCotizacionDivisaIterationResponse }{@code >}
     *     
     */
    public void setCotizaciones(JAXBElement<ArrayOfTrx15ConsultaCotizacionDivisaIterationResponse> value) {
        this.cotizaciones = value;
    }

}

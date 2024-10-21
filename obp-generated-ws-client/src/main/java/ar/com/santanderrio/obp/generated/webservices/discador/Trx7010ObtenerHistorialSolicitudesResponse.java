
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx7010ObtenerHistorialSolicitudesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx7010ObtenerHistorialSolicitudesResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="HistorialSolicitudes" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010}ArrayOfTrx7010ObtenerHistorialSolicitudesRepetitionItem" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx7010ObtenerHistorialSolicitudesResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", propOrder = {
    "historialSolicitudes"
})
public class Trx7010ObtenerHistorialSolicitudesResponse
    extends ResponseBase
{

    @XmlElementRef(name = "HistorialSolicitudes", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrx7010ObtenerHistorialSolicitudesRepetitionItem> historialSolicitudes;

    /**
     * Gets the value of the historialSolicitudes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx7010ObtenerHistorialSolicitudesRepetitionItem }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrx7010ObtenerHistorialSolicitudesRepetitionItem> getHistorialSolicitudes() {
        return historialSolicitudes;
    }

    /**
     * Sets the value of the historialSolicitudes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx7010ObtenerHistorialSolicitudesRepetitionItem }{@code >}
     *     
     */
    public void setHistorialSolicitudes(JAXBElement<ArrayOfTrx7010ObtenerHistorialSolicitudesRepetitionItem> value) {
        this.historialSolicitudes = value;
    }

}

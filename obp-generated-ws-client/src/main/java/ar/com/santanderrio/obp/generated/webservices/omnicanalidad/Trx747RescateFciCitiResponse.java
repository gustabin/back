
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx747RescateFciCitiResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx747RescateFciCitiResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="ImporteRescateBruto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Solicitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalCuotasPartes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx747RescateFciCitiResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx747", propOrder = {
    "importeRescateBruto",
    "solicitud",
    "totalCuotasPartes"
})
public class Trx747RescateFciCitiResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "ImporteRescateBruto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx747", type = JAXBElement.class)
    protected JAXBElement<String> importeRescateBruto;
    @XmlElementRef(name = "Solicitud", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx747", type = JAXBElement.class)
    protected JAXBElement<String> solicitud;
    @XmlElementRef(name = "TotalCuotasPartes", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx747", type = JAXBElement.class)
    protected JAXBElement<String> totalCuotasPartes;

    /**
     * Gets the value of the importeRescateBruto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteRescateBruto() {
        return importeRescateBruto;
    }

    /**
     * Sets the value of the importeRescateBruto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteRescateBruto(JAXBElement<String> value) {
        this.importeRescateBruto = value;
    }

    /**
     * Gets the value of the solicitud property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSolicitud() {
        return solicitud;
    }

    /**
     * Sets the value of the solicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSolicitud(JAXBElement<String> value) {
        this.solicitud = value;
    }

    /**
     * Gets the value of the totalCuotasPartes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTotalCuotasPartes() {
        return totalCuotasPartes;
    }

    /**
     * Sets the value of the totalCuotasPartes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTotalCuotasPartes(JAXBElement<String> value) {
        this.totalCuotasPartes = value;
    }

}

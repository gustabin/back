
package ar.com.santanderrio.obp.generated.webservices.extractos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WM_SALDOS_OBCMResult" type="{http://schemas.datacontract.org/2004/07/SantanderRio.Reportes.Servicio}ReportePdf" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "wmsaldosobcmResult"
})
@XmlRootElement(name = "WM_SALDOS_OBCMResponse")
public class WMSALDOSOBCMResponse {

    @XmlElementRef(name = "WM_SALDOS_OBCMResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ReportePdf> wmsaldosobcmResult;

    /**
     * Gets the value of the wmsaldosobcmResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ReportePdf }{@code >}
     *     
     */
    public JAXBElement<ReportePdf> getWMSALDOSOBCMResult() {
        return wmsaldosobcmResult;
    }

    /**
     * Sets the value of the wmsaldosobcmResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ReportePdf }{@code >}
     *     
     */
    public void setWMSALDOSOBCMResult(JAXBElement<ReportePdf> value) {
        this.wmsaldosobcmResult = ((JAXBElement<ReportePdf> ) value);
    }

}

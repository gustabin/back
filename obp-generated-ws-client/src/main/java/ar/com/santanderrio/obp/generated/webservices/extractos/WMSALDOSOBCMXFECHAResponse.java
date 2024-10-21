
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
 *         &lt;element name="WM_SALDOS_OBCM_X_FECHAResult" type="{http://schemas.datacontract.org/2004/07/SantanderRio.Reportes.Servicio}ReportePdf" minOccurs="0"/>
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
    "wmsaldosobcmxfechaResult"
})
@XmlRootElement(name = "WM_SALDOS_OBCM_X_FECHAResponse")
public class WMSALDOSOBCMXFECHAResponse {

    @XmlElementRef(name = "WM_SALDOS_OBCM_X_FECHAResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ReportePdf> wmsaldosobcmxfechaResult;

    /**
     * Gets the value of the wmsaldosobcmxfechaResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ReportePdf }{@code >}
     *     
     */
    public JAXBElement<ReportePdf> getWMSALDOSOBCMXFECHAResult() {
        return wmsaldosobcmxfechaResult;
    }

    /**
     * Sets the value of the wmsaldosobcmxfechaResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ReportePdf }{@code >}
     *     
     */
    public void setWMSALDOSOBCMXFECHAResult(JAXBElement<ReportePdf> value) {
        this.wmsaldosobcmxfechaResult = ((JAXBElement<ReportePdf> ) value);
    }

}

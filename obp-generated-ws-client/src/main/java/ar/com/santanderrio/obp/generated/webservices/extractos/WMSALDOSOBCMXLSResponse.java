
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
 *         &lt;element name="WM_SALDOS_OBCM_XLSResult" type="{http://schemas.datacontract.org/2004/07/SantanderRio.Reportes.Servicio}ReporteExcel" minOccurs="0"/>
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
    "wmsaldosobcmxlsResult"
})
@XmlRootElement(name = "WM_SALDOS_OBCM_XLSResponse")
public class WMSALDOSOBCMXLSResponse {

    @XmlElementRef(name = "WM_SALDOS_OBCM_XLSResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ReporteExcel> wmsaldosobcmxlsResult;

    /**
     * Gets the value of the wmsaldosobcmxlsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ReporteExcel }{@code >}
     *     
     */
    public JAXBElement<ReporteExcel> getWMSALDOSOBCMXLSResult() {
        return wmsaldosobcmxlsResult;
    }

    /**
     * Sets the value of the wmsaldosobcmxlsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ReporteExcel }{@code >}
     *     
     */
    public void setWMSALDOSOBCMXLSResult(JAXBElement<ReporteExcel> value) {
        this.wmsaldosobcmxlsResult = ((JAXBElement<ReporteExcel> ) value);
    }

}


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
 *         &lt;element name="WM_MOVIMIENTOS_OBCM_XLSResult" type="{http://schemas.datacontract.org/2004/07/SantanderRio.Reportes.Servicio}ReporteExcel" minOccurs="0"/>
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
    "wmmovimientosobcmxlsResult"
})
@XmlRootElement(name = "WM_MOVIMIENTOS_OBCM_XLSResponse")
public class WMMOVIMIENTOSOBCMXLSResponse {

    @XmlElementRef(name = "WM_MOVIMIENTOS_OBCM_XLSResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ReporteExcel> wmmovimientosobcmxlsResult;

    /**
     * Gets the value of the wmmovimientosobcmxlsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ReporteExcel }{@code >}
     *     
     */
    public JAXBElement<ReporteExcel> getWMMOVIMIENTOSOBCMXLSResult() {
        return wmmovimientosobcmxlsResult;
    }

    /**
     * Sets the value of the wmmovimientosobcmxlsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ReporteExcel }{@code >}
     *     
     */
    public void setWMMOVIMIENTOSOBCMXLSResult(JAXBElement<ReporteExcel> value) {
        this.wmmovimientosobcmxlsResult = ((JAXBElement<ReporteExcel> ) value);
    }

}


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
 *         &lt;element name="WM_FIRMAS_CUENTAS_X_NUPResult" type="{http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos}CuentasFirmadasResponse" minOccurs="0"/>
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
    "wmfirmascuentasxnupResult"
})
@XmlRootElement(name = "WM_FIRMAS_CUENTAS_X_NUPResponse")
public class WMFIRMASCUENTASXNUPResponse {

    @XmlElementRef(name = "WM_FIRMAS_CUENTAS_X_NUPResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<CuentasFirmadasResponse> wmfirmascuentasxnupResult;

    /**
     * Gets the value of the wmfirmascuentasxnupResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CuentasFirmadasResponse }{@code >}
     *     
     */
    public JAXBElement<CuentasFirmadasResponse> getWMFIRMASCUENTASXNUPResult() {
        return wmfirmascuentasxnupResult;
    }

    /**
     * Sets the value of the wmfirmascuentasxnupResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CuentasFirmadasResponse }{@code >}
     *     
     */
    public void setWMFIRMASCUENTASXNUPResult(JAXBElement<CuentasFirmadasResponse> value) {
        this.wmfirmascuentasxnupResult = ((JAXBElement<CuentasFirmadasResponse> ) value);
    }

}

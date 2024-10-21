
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
 *         &lt;element name="WM_CNS_MOV_FONDOS_OLYResult" type="{http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos}MovFondosResponse" minOccurs="0"/>
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
    "wmcnsmovfondosolyResult"
})
@XmlRootElement(name = "WM_CNS_MOV_FONDOS_OLYResponse")
public class WMCNSMOVFONDOSOLYResponse {

    @XmlElementRef(name = "WM_CNS_MOV_FONDOS_OLYResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<MovFondosResponse> wmcnsmovfondosolyResult;

    /**
     * Gets the value of the wmcnsmovfondosolyResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link MovFondosResponse }{@code >}
     *     
     */
    public JAXBElement<MovFondosResponse> getWMCNSMOVFONDOSOLYResult() {
        return wmcnsmovfondosolyResult;
    }

    /**
     * Sets the value of the wmcnsmovfondosolyResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link MovFondosResponse }{@code >}
     *     
     */
    public void setWMCNSMOVFONDOSOLYResult(JAXBElement<MovFondosResponse> value) {
        this.wmcnsmovfondosolyResult = ((JAXBElement<MovFondosResponse> ) value);
    }

}

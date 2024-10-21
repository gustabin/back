
package ar.com.santanderrio.obp.generated.webservices.bpriv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.bpriv.domain.ArrayOfMovimiento3Ros;


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
 *         &lt;element name="BP_CUENTAS_CNS_MOVIMIENTOSResult" type="{http://schemas.datacontract.org/2004/07/BancaPrivada.Domain}ArrayOfMovimiento3ros" minOccurs="0"/>
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
    "bpcuentascnsmovimientosResult"
})
@XmlRootElement(name = "BP_CUENTAS_CNS_MOVIMIENTOSResponse")
public class BPCUENTASCNSMOVIMIENTOSResponse {

    @XmlElementRef(name = "BP_CUENTAS_CNS_MOVIMIENTOSResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfMovimiento3Ros> bpcuentascnsmovimientosResult;

    /**
     * Gets the value of the bpcuentascnsmovimientosResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMovimiento3Ros }{@code >}
     *     
     */
    public JAXBElement<ArrayOfMovimiento3Ros> getBPCUENTASCNSMOVIMIENTOSResult() {
        return bpcuentascnsmovimientosResult;
    }

    /**
     * Sets the value of the bpcuentascnsmovimientosResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMovimiento3Ros }{@code >}
     *     
     */
    public void setBPCUENTASCNSMOVIMIENTOSResult(JAXBElement<ArrayOfMovimiento3Ros> value) {
        this.bpcuentascnsmovimientosResult = value;
    }

}

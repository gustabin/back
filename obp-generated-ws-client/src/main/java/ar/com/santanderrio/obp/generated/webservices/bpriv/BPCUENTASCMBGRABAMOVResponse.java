
package ar.com.santanderrio.obp.generated.webservices.bpriv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.bpriv.domain.GrabamovAltair;


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
 *         &lt;element name="BP_CUENTAS_CMB_GRABAMOVResult" type="{http://schemas.datacontract.org/2004/07/BancaPrivada.Domain}GrabamovAltair" minOccurs="0"/>
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
    "bpcuentascmbgrabamovResult"
})
@XmlRootElement(name = "BP_CUENTAS_CMB_GRABAMOVResponse")
public class BPCUENTASCMBGRABAMOVResponse {

    @XmlElementRef(name = "BP_CUENTAS_CMB_GRABAMOVResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<GrabamovAltair> bpcuentascmbgrabamovResult;

    /**
     * Gets the value of the bpcuentascmbgrabamovResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GrabamovAltair }{@code >}
     *     
     */
    public JAXBElement<GrabamovAltair> getBPCUENTASCMBGRABAMOVResult() {
        return bpcuentascmbgrabamovResult;
    }

    /**
     * Sets the value of the bpcuentascmbgrabamovResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GrabamovAltair }{@code >}
     *     
     */
    public void setBPCUENTASCMBGRABAMOVResult(JAXBElement<GrabamovAltair> value) {
        this.bpcuentascmbgrabamovResult = value;
    }

}

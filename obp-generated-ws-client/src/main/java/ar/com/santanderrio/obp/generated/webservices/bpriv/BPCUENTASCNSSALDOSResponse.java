
package ar.com.santanderrio.obp.generated.webservices.bpriv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.bpriv.domain.ArrayOfSaldo;


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
 *         &lt;element name="BP_CUENTAS_CNS_SALDOSResult" type="{http://schemas.datacontract.org/2004/07/BancaPrivada.Domain}ArrayOfSaldo" minOccurs="0"/>
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
    "bpcuentascnssaldosResult"
})
@XmlRootElement(name = "BP_CUENTAS_CNS_SALDOSResponse")
public class BPCUENTASCNSSALDOSResponse {

    @XmlElementRef(name = "BP_CUENTAS_CNS_SALDOSResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfSaldo> bpcuentascnssaldosResult;

    /**
     * Gets the value of the bpcuentascnssaldosResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSaldo }{@code >}
     *     
     */
    public JAXBElement<ArrayOfSaldo> getBPCUENTASCNSSALDOSResult() {
        return bpcuentascnssaldosResult;
    }

    /**
     * Sets the value of the bpcuentascnssaldosResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSaldo }{@code >}
     *     
     */
    public void setBPCUENTASCNSSALDOSResult(JAXBElement<ArrayOfSaldo> value) {
        this.bpcuentascnssaldosResult = value;
    }

}

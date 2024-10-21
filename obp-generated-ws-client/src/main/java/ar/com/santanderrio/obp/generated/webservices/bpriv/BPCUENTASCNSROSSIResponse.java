
package ar.com.santanderrio.obp.generated.webservices.bpriv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.bpriv.domain.ArrayOfLoadRossi;


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
 *         &lt;element name="BP_CUENTAS_CNS_ROSSIResult" type="{http://schemas.datacontract.org/2004/07/BancaPrivada.Domain}ArrayOfLoadRossi" minOccurs="0"/>
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
    "bpcuentascnsrossiResult"
})
@XmlRootElement(name = "BP_CUENTAS_CNS_ROSSIResponse")
public class BPCUENTASCNSROSSIResponse {

    @XmlElementRef(name = "BP_CUENTAS_CNS_ROSSIResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfLoadRossi> bpcuentascnsrossiResult;

    /**
     * Gets the value of the bpcuentascnsrossiResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfLoadRossi }{@code >}
     *     
     */
    public JAXBElement<ArrayOfLoadRossi> getBPCUENTASCNSROSSIResult() {
        return bpcuentascnsrossiResult;
    }

    /**
     * Sets the value of the bpcuentascnsrossiResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfLoadRossi }{@code >}
     *     
     */
    public void setBPCUENTASCNSROSSIResult(JAXBElement<ArrayOfLoadRossi> value) {
        this.bpcuentascnsrossiResult = value;
    }

}

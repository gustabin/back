
package ar.com.santanderrio.obp.generated.webservices.bpriv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPCUENTASCMBGRABAMOVPARAMETER;


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
 *         &lt;element name="Parameter" type="{http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts}BP_CUENTAS_CMB_GRABAMOV_PARAMETER" minOccurs="0"/>
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
    "parameter"
})
@XmlRootElement(name = "BP_CUENTAS_CMB_GRABAMOV")
public class BPCUENTASCMBGRABAMOV {

    @XmlElementRef(name = "Parameter", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<BPCUENTASCMBGRABAMOVPARAMETER> parameter;

    /**
     * Gets the value of the parameter property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BPCUENTASCMBGRABAMOVPARAMETER }{@code >}
     *     
     */
    public JAXBElement<BPCUENTASCMBGRABAMOVPARAMETER> getParameter() {
        return parameter;
    }

    /**
     * Sets the value of the parameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BPCUENTASCMBGRABAMOVPARAMETER }{@code >}
     *     
     */
    public void setParameter(JAXBElement<BPCUENTASCMBGRABAMOVPARAMETER> value) {
        this.parameter = value;
    }

}

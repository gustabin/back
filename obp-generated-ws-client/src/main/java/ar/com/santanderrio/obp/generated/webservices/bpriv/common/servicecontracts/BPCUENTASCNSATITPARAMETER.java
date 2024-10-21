
package ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.parameters.BaseParameter;


/**
 * <p>Java class for BP_CUENTAS_CNS_ATIT_PARAMETER complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BP_CUENTAS_CNS_ATIT_PARAMETER">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters}BaseParameter">
 *       &lt;sequence>
 *         &lt;element name="CuentaBP" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="NUP" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BP_CUENTAS_CNS_ATIT_PARAMETER", propOrder = {
    "cuentaBP",
    "nup"
})
public class BPCUENTASCNSATITPARAMETER
    extends BaseParameter
{

    @XmlElementRef(name = "CuentaBP", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> cuentaBP;
    @XmlElement(name = "NUP")
    protected BigDecimal nup;

    /**
     * Gets the value of the cuentaBP property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getCuentaBP() {
        return cuentaBP;
    }

    /**
     * Sets the value of the cuentaBP property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setCuentaBP(JAXBElement<BigDecimal> value) {
        this.cuentaBP = value;
    }

    /**
     * Gets the value of the nup property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNUP() {
        return nup;
    }

    /**
     * Sets the value of the nup property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNUP(BigDecimal value) {
        this.nup = value;
    }

}

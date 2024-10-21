
package ar.com.santanderrio.obp.generated.webservices.extractos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CuentaDetalle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CuentaDetalle">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos}CNOEntidadBase">
 *       &lt;sequence>
 *         &lt;element name="ATit" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="AccountNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CuentaDetalle", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", propOrder = {
    "aTit",
    "accountNO"
})
@XmlSeeAlso({
    CuentaTitulo.class
})
public class CuentaDetalle
    extends CNOEntidadBase
{

    @XmlElement(name = "ATit")
    protected Integer aTit;
    @XmlElementRef(name = "AccountNO", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", type = JAXBElement.class)
    protected JAXBElement<String> accountNO;

    /**
     * Gets the value of the aTit property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getATit() {
        return aTit;
    }

    /**
     * Sets the value of the aTit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setATit(Integer value) {
        this.aTit = value;
    }

    /**
     * Gets the value of the accountNO property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAccountNO() {
        return accountNO;
    }

    /**
     * Sets the value of the accountNO property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAccountNO(JAXBElement<String> value) {
        this.accountNO = ((JAXBElement<String> ) value);
    }

}

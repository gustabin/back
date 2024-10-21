
package ar.com.santanderrio.obp.generated.webservices.bpriv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarOperacionCambioOBParameter;


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
 *         &lt;element name="parameter" type="{http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts}InsertarOperacionCambioOBParameter" minOccurs="0"/>
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
@XmlRootElement(name = "InsertarOperacionCambioOB")
public class InsertarOperacionCambioOB {

    @XmlElementRef(name = "parameter", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<InsertarOperacionCambioOBParameter> parameter;

    /**
     * Gets the value of the parameter property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link InsertarOperacionCambioOBParameter }{@code >}
     *     
     */
    public JAXBElement<InsertarOperacionCambioOBParameter> getParameter() {
        return parameter;
    }

    /**
     * Sets the value of the parameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link InsertarOperacionCambioOBParameter }{@code >}
     *     
     */
    public void setParameter(JAXBElement<InsertarOperacionCambioOBParameter> value) {
        this.parameter = value;
    }

}

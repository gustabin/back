
package ar.com.santanderrio.obp.generated.webservices.inv.licitacion;

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
 *         &lt;element name="ObtenerCanalTramoResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "obtenerCanalTramoResult"
})
@XmlRootElement(name = "ObtenerCanalTramoResponse")
public class ObtenerCanalTramoResponse {

    @XmlElementRef(name = "ObtenerCanalTramoResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> obtenerCanalTramoResult;

    /**
     * Gets the value of the obtenerCanalTramoResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getObtenerCanalTramoResult() {
        return obtenerCanalTramoResult;
    }

    /**
     * Sets the value of the obtenerCanalTramoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setObtenerCanalTramoResult(JAXBElement<String> value) {
        this.obtenerCanalTramoResult = ((JAXBElement<String> ) value);
    }

}

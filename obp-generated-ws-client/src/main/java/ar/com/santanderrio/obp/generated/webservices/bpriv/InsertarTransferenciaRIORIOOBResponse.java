
package ar.com.santanderrio.obp.generated.webservices.bpriv;

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
 *         &lt;element name="InsertarTransferenciaRIORIOOBResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "insertarTransferenciaRIORIOOBResult"
})
@XmlRootElement(name = "InsertarTransferenciaRIORIOOBResponse")
public class InsertarTransferenciaRIORIOOBResponse {

    @XmlElementRef(name = "InsertarTransferenciaRIORIOOBResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> insertarTransferenciaRIORIOOBResult;

    /**
     * Gets the value of the insertarTransferenciaRIORIOOBResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInsertarTransferenciaRIORIOOBResult() {
        return insertarTransferenciaRIORIOOBResult;
    }

    /**
     * Sets the value of the insertarTransferenciaRIORIOOBResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInsertarTransferenciaRIORIOOBResult(JAXBElement<String> value) {
        this.insertarTransferenciaRIORIOOBResult = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.discador.client;

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
 *         &lt;element name="GrabarDatosClickToCall_SinFirmaResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "grabarDatosClickToCallSinFirmaResult"
})
@XmlRootElement(name = "GrabarDatosClickToCall_SinFirmaResponse")
public class GrabarDatosClickToCallSinFirmaResponse {

    @XmlElementRef(name = "GrabarDatosClickToCall_SinFirmaResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> grabarDatosClickToCallSinFirmaResult;

    /**
     * Gets the value of the grabarDatosClickToCallSinFirmaResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGrabarDatosClickToCallSinFirmaResult() {
        return grabarDatosClickToCallSinFirmaResult;
    }

    /**
     * Sets the value of the grabarDatosClickToCallSinFirmaResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGrabarDatosClickToCallSinFirmaResult(JAXBElement<String> value) {
        this.grabarDatosClickToCallSinFirmaResult = value;
    }

}

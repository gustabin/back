
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx7009SetNodoArbolIVRRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx7009SetNodoArbolIVRRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="NodoArbolIVR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sesion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx7009SetNodoArbolIVRRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7009", propOrder = {
    "nodoArbolIVR",
    "sesion"
})
public class Trx7009SetNodoArbolIVRRequest
    extends RequestBase
{

    @XmlElementRef(name = "NodoArbolIVR", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7009", type = JAXBElement.class)
    protected JAXBElement<String> nodoArbolIVR;
    @XmlElement(name = "Sesion")
    protected Long sesion;

    /**
     * Gets the value of the nodoArbolIVR property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNodoArbolIVR() {
        return nodoArbolIVR;
    }

    /**
     * Sets the value of the nodoArbolIVR property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNodoArbolIVR(JAXBElement<String> value) {
        this.nodoArbolIVR = value;
    }

    /**
     * Gets the value of the sesion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSesion() {
        return sesion;
    }

    /**
     * Sets the value of the sesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSesion(Long value) {
        this.sesion = value;
    }

}

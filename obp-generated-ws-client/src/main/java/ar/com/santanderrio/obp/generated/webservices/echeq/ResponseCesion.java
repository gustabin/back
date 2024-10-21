
package ar.com.santanderrio.obp.generated.webservices.echeq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseCesion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseCesion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stat" type="{http://echeq.amco.com.ar/}CCEStat"/>
 *         &lt;element name="cesion" type="{http://echeq.amco.com.ar/}Cesion"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseCesion", propOrder = {
    "code",
    "message",
    "stat",
    "cesion"
})
public class ResponseCesion {

    protected String code;
    protected String message;
    @XmlElement(required = true)
    protected CCEStat stat;
    @XmlElement(required = true)
    protected Cesion cesion;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the stat property.
     * 
     * @return
     *     possible object is
     *     {@link CCEStat }
     *     
     */
    public CCEStat getStat() {
        return stat;
    }

    /**
     * Sets the value of the stat property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCEStat }
     *     
     */
    public void setStat(CCEStat value) {
        this.stat = value;
    }

    /**
     * Gets the value of the cesion property.
     * 
     * @return
     *     possible object is
     *     {@link Cesion }
     *     
     */
    public Cesion getCesion() {
        return cesion;
    }

    /**
     * Sets the value of the cesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cesion }
     *     
     */
    public void setCesion(Cesion value) {
        this.cesion = value;
    }

}

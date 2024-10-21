
package ar.com.santanderrio.obp.generated.webservices.productos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="arg_0_1" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
    "arg01"
})
@XmlRootElement(name = "init")
public class Init {

    @XmlElement(name = "arg_0_1", required = true, nillable = true)
    protected Object arg01;

    /**
     * Gets the value of the arg01 property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getArg01() {
        return arg01;
    }

    /**
     * Sets the value of the arg01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setArg01(Object value) {
        this.arg01 = value;
    }

}

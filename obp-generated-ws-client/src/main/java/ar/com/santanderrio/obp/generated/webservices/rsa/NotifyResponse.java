
package ar.com.santanderrio.obp.generated.webservices.rsa;

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
 *         &lt;element name="notifyReturn" type="{http://ws.csd.rsa.com}NotifyResponse"/>
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
    "notifyReturn"
})
@XmlRootElement(name = "notifyResponse")
public class NotifyResponse {

    @XmlElement(required = true)
    protected NotifyResponse2 notifyReturn;

    /**
     * Gets the value of the notifyReturn property.
     * 
     * @return
     *     possible object is
     *     {@link NotifyResponse2 }
     *     
     */
    public NotifyResponse2 getNotifyReturn() {
        return notifyReturn;
    }

    /**
     * Sets the value of the notifyReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotifyResponse2 }
     *     
     */
    public void setNotifyReturn(NotifyResponse2 value) {
        this.notifyReturn = value;
    }

}


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
 *         &lt;element name="authenticateReturn" type="{http://ws.csd.rsa.com}AuthenticateResponse"/>
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
    "authenticateReturn"
})
@XmlRootElement(name = "authenticateResponse")
public class AuthenticateResponse {

    @XmlElement(required = true)
    protected AuthenticateResponse2 authenticateReturn;

    /**
     * Gets the value of the authenticateReturn property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticateResponse2 }
     *     
     */
    public AuthenticateResponse2 getAuthenticateReturn() {
        return authenticateReturn;
    }

    /**
     * Sets the value of the authenticateReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticateResponse2 }
     *     
     */
    public void setAuthenticateReturn(AuthenticateResponse2 value) {
        this.authenticateReturn = value;
    }

}

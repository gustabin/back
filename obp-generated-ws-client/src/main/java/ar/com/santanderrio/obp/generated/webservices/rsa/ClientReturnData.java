
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This defines the Client Return Data - sent during an anaylze request for re-direction after authentication
 * 
 * <p>Java class for ClientReturnData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClientReturnData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="returnUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validationMethod" type="{http://ws.csd.rsa.com}AuthorizationMethod" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClientReturnData", propOrder = {
    "returnUrl",
    "validationMethod"
})
public class ClientReturnData {

    protected String returnUrl;
    protected AuthorizationMethod validationMethod;

    /**
     * Gets the value of the returnUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnUrl() {
        return returnUrl;
    }

    /**
     * Sets the value of the returnUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnUrl(String value) {
        this.returnUrl = value;
    }

    /**
     * Gets the value of the validationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorizationMethod }
     *     
     */
    public AuthorizationMethod getValidationMethod() {
        return validationMethod;
    }

    /**
     * Sets the value of the validationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorizationMethod }
     *     
     */
    public void setValidationMethod(AuthorizationMethod value) {
        this.validationMethod = value;
    }

}

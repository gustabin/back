
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClientGenCookie complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClientGenCookie">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}DeviceIdentifier">
 *       &lt;sequence>
 *         &lt;element name="clientGenCookie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClientGenCookie", propOrder = {
    "clientGenCookie"
})
public class ClientGenCookie
    extends DeviceIdentifier
{

    protected String clientGenCookie;

    /**
     * Gets the value of the clientGenCookie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientGenCookie() {
        return clientGenCookie;
    }

    /**
     * Sets the value of the clientGenCookie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientGenCookie(String value) {
        this.clientGenCookie = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OOBGenChallengeRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OOBGenChallengeRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}AcspChallengeRequest">
 *       &lt;sequence>
 *         &lt;element name="contactList" type="{http://ws.oobgen.csd.rsa.com}OOBPhoneInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OOBGenChallengeRequest", namespace = "http://ws.oobgen.csd.rsa.com", propOrder = {
    "contactList"
})
@XmlSeeAlso({
    OOBSMSChallengeRequest.class
})
public abstract class OOBGenChallengeRequest
    extends AcspChallengeRequest
{

    protected OOBPhoneInfo contactList;

    /**
     * Gets the value of the contactList property.
     * 
     * @return
     *     possible object is
     *     {@link OOBPhoneInfo }
     *     
     */
    public OOBPhoneInfo getContactList() {
        return contactList;
    }

    /**
     * Sets the value of the contactList property.
     * 
     * @param value
     *     allowed object is
     *     {@link OOBPhoneInfo }
     *     
     */
    public void setContactList(OOBPhoneInfo value) {
        this.contactList = value;
    }

}

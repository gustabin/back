
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This type defines the Phone Challenge Request Payload
 * 
 * <p>Java class for OOBPhoneChallengeRequestPayload complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OOBPhoneChallengeRequestPayload">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="noOp" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="phoneInfo" type="{http://ws.csd.rsa.com}PhoneInfo" minOccurs="0"/>
 *         &lt;element name="tokenCollectionFlow" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OOBPhoneChallengeRequestPayload", propOrder = {
    "noOp",
    "phoneInfo",
    "tokenCollectionFlow"
})
public class OOBPhoneChallengeRequestPayload {

    protected Boolean noOp;
    protected PhoneInfo phoneInfo;
    protected Boolean tokenCollectionFlow;

    /**
     * Gets the value of the noOp property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoOp() {
        return noOp;
    }

    /**
     * Sets the value of the noOp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoOp(Boolean value) {
        this.noOp = value;
    }

    /**
     * Gets the value of the phoneInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PhoneInfo }
     *     
     */
    public PhoneInfo getPhoneInfo() {
        return phoneInfo;
    }

    /**
     * Sets the value of the phoneInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhoneInfo }
     *     
     */
    public void setPhoneInfo(PhoneInfo value) {
        this.phoneInfo = value;
    }

    /**
     * Gets the value of the tokenCollectionFlow property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTokenCollectionFlow() {
        return tokenCollectionFlow;
    }

    /**
     * Sets the value of the tokenCollectionFlow property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTokenCollectionFlow(Boolean value) {
        this.tokenCollectionFlow = value;
    }

}

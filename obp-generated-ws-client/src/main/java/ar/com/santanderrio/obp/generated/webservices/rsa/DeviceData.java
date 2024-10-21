
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This defines device information
 * 
 * <p>Java class for DeviceData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeviceData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bindingType" type="{http://ws.csd.rsa.com}BindingType" minOccurs="0"/>
 *         &lt;element name="deviceTokenCookie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceTokenFSO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lookupLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceData", propOrder = {
    "bindingType",
    "deviceTokenCookie",
    "deviceTokenFSO",
    "lookupLabel",
    "newLabel"
})
public class DeviceData {

    protected BindingType bindingType;
    protected String deviceTokenCookie;
    protected String deviceTokenFSO;
    protected String lookupLabel;
    protected String newLabel;

    /**
     * Gets the value of the bindingType property.
     * 
     * @return
     *     possible object is
     *     {@link BindingType }
     *     
     */
    public BindingType getBindingType() {
        return bindingType;
    }

    /**
     * Sets the value of the bindingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BindingType }
     *     
     */
    public void setBindingType(BindingType value) {
        this.bindingType = value;
    }

    /**
     * Gets the value of the deviceTokenCookie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceTokenCookie() {
        return deviceTokenCookie;
    }

    /**
     * Sets the value of the deviceTokenCookie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceTokenCookie(String value) {
        this.deviceTokenCookie = value;
    }

    /**
     * Gets the value of the deviceTokenFSO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceTokenFSO() {
        return deviceTokenFSO;
    }

    /**
     * Sets the value of the deviceTokenFSO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceTokenFSO(String value) {
        this.deviceTokenFSO = value;
    }

    /**
     * Gets the value of the lookupLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLookupLabel() {
        return lookupLabel;
    }

    /**
     * Sets the value of the lookupLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLookupLabel(String value) {
        this.lookupLabel = value;
    }

    /**
     * Gets the value of the newLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewLabel() {
        return newLabel;
    }

    /**
     * Sets the value of the newLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewLabel(String value) {
        this.newLabel = value;
    }

}

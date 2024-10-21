
package ar.com.santanderrio.obp.generated.webservices.rsa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Requests information about the device
 * 
 * <p>Java class for DeviceRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeviceRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="beaconId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="devicePrint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceTokenCookie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceTokenFSO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="httpAccept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="httpAcceptChars" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="httpAcceptEncoding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="httpAcceptLanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="httpReferrer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAgent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="geoLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="domElements" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jsEvents" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pageId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceIdentifier" type="{http://ws.csd.rsa.com}DeviceIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceRequest", propOrder = {
    "beaconId",
    "devicePrint",
    "deviceTokenCookie",
    "deviceTokenFSO",
    "httpAccept",
    "httpAcceptChars",
    "httpAcceptEncoding",
    "httpAcceptLanguage",
    "httpReferrer",
    "ipAddress",
    "userAgent",
    "geoLocation",
    "domElements",
    "jsEvents",
    "pageId",
    "deviceIdentifier"
})
public class DeviceRequest {

    protected String beaconId;
    protected String devicePrint;
    protected String deviceTokenCookie;
    protected String deviceTokenFSO;
    protected String httpAccept;
    protected String httpAcceptChars;
    protected String httpAcceptEncoding;
    protected String httpAcceptLanguage;
    protected String httpReferrer;
    protected String ipAddress;
    protected String userAgent;
    protected String geoLocation;
    protected String domElements;
    protected String jsEvents;
    protected String pageId;
    protected List<DeviceIdentifier> deviceIdentifier;

    /**
     * Gets the value of the beaconId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeaconId() {
        return beaconId;
    }

    /**
     * Sets the value of the beaconId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeaconId(String value) {
        this.beaconId = value;
    }

    /**
     * Gets the value of the devicePrint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDevicePrint() {
        return devicePrint;
    }

    /**
     * Sets the value of the devicePrint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDevicePrint(String value) {
        this.devicePrint = value;
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
     * Gets the value of the httpAccept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHttpAccept() {
        return httpAccept;
    }

    /**
     * Sets the value of the httpAccept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHttpAccept(String value) {
        this.httpAccept = value;
    }

    /**
     * Gets the value of the httpAcceptChars property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHttpAcceptChars() {
        return httpAcceptChars;
    }

    /**
     * Sets the value of the httpAcceptChars property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHttpAcceptChars(String value) {
        this.httpAcceptChars = value;
    }

    /**
     * Gets the value of the httpAcceptEncoding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHttpAcceptEncoding() {
        return httpAcceptEncoding;
    }

    /**
     * Sets the value of the httpAcceptEncoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHttpAcceptEncoding(String value) {
        this.httpAcceptEncoding = value;
    }

    /**
     * Gets the value of the httpAcceptLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHttpAcceptLanguage() {
        return httpAcceptLanguage;
    }

    /**
     * Sets the value of the httpAcceptLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHttpAcceptLanguage(String value) {
        this.httpAcceptLanguage = value;
    }

    /**
     * Gets the value of the httpReferrer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHttpReferrer() {
        return httpReferrer;
    }

    /**
     * Sets the value of the httpReferrer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHttpReferrer(String value) {
        this.httpReferrer = value;
    }

    /**
     * Gets the value of the ipAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the value of the ipAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpAddress(String value) {
        this.ipAddress = value;
    }

    /**
     * Gets the value of the userAgent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets the value of the userAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAgent(String value) {
        this.userAgent = value;
    }

    /**
     * Gets the value of the geoLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeoLocation() {
        return geoLocation;
    }

    /**
     * Sets the value of the geoLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeoLocation(String value) {
        this.geoLocation = value;
    }

    /**
     * Gets the value of the domElements property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomElements() {
        return domElements;
    }

    /**
     * Sets the value of the domElements property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomElements(String value) {
        this.domElements = value;
    }

    /**
     * Gets the value of the jsEvents property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJsEvents() {
        return jsEvents;
    }

    /**
     * Sets the value of the jsEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJsEvents(String value) {
        this.jsEvents = value;
    }

    /**
     * Gets the value of the pageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * Sets the value of the pageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageId(String value) {
        this.pageId = value;
    }

    /**
     * Gets the value of the deviceIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deviceIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeviceIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeviceIdentifier }
     * 
     * 
     */
    public List<DeviceIdentifier> getDeviceIdentifier() {
        if (deviceIdentifier == null) {
            deviceIdentifier = new ArrayList<DeviceIdentifier>();
        }
        return this.deviceIdentifier;
    }

}

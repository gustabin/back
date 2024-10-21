
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MobileDevice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MobileDevice">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}DeviceIdentifier">
 *       &lt;sequence>
 *         &lt;element name="simId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otherId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hardwareId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="geoLocation" type="{http://ws.csd.rsa.com}GeoLocation" minOccurs="0"/>
 *         &lt;element name="deviceModel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceMultiTaskingSupported" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceSystemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceSystemVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="languages" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wiFiMacAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wiFiNetworksData" type="{http://ws.csd.rsa.com}WiFiNetworkData" minOccurs="0"/>
 *         &lt;element name="cellTowerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="screenSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfAddressBookEntries" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="rsaApplicationkey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wapClientID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vendorClientID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mcc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mnc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="osId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobileSdkData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MobileDevice", propOrder = {
    "simId",
    "otherId",
    "hardwareId",
    "geoLocation",
    "deviceModel",
    "deviceMultiTaskingSupported",
    "deviceName",
    "deviceSystemName",
    "deviceSystemVersion",
    "languages",
    "wiFiMacAddress",
    "wiFiNetworksData",
    "cellTowerID",
    "locationAreaCode",
    "screenSize",
    "numberOfAddressBookEntries",
    "rsaApplicationkey",
    "wapClientID",
    "vendorClientID",
    "mcc",
    "mnc",
    "osId",
    "mobileSdkData"
})
public class MobileDevice
    extends DeviceIdentifier
{

    protected String simId;
    protected String otherId;
    protected String hardwareId;
    protected GeoLocation geoLocation;
    protected String deviceModel;
    protected String deviceMultiTaskingSupported;
    protected String deviceName;
    protected String deviceSystemName;
    protected String deviceSystemVersion;
    protected String languages;
    protected String wiFiMacAddress;
    protected WiFiNetworkData wiFiNetworksData;
    protected String cellTowerID;
    protected String locationAreaCode;
    protected String screenSize;
    protected Integer numberOfAddressBookEntries;
    protected String rsaApplicationkey;
    protected String wapClientID;
    protected String vendorClientID;
    protected String mcc;
    protected String mnc;
    protected String osId;
    protected String mobileSdkData;

    /**
     * Gets the value of the simId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimId() {
        return simId;
    }

    /**
     * Sets the value of the simId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimId(String value) {
        this.simId = value;
    }

    /**
     * Gets the value of the otherId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherId() {
        return otherId;
    }

    /**
     * Sets the value of the otherId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherId(String value) {
        this.otherId = value;
    }

    /**
     * Gets the value of the hardwareId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHardwareId() {
        return hardwareId;
    }

    /**
     * Sets the value of the hardwareId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHardwareId(String value) {
        this.hardwareId = value;
    }

    /**
     * Gets the value of the geoLocation property.
     * 
     * @return
     *     possible object is
     *     {@link GeoLocation }
     *     
     */
    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    /**
     * Sets the value of the geoLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeoLocation }
     *     
     */
    public void setGeoLocation(GeoLocation value) {
        this.geoLocation = value;
    }

    /**
     * Gets the value of the deviceModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * Sets the value of the deviceModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceModel(String value) {
        this.deviceModel = value;
    }

    /**
     * Gets the value of the deviceMultiTaskingSupported property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceMultiTaskingSupported() {
        return deviceMultiTaskingSupported;
    }

    /**
     * Sets the value of the deviceMultiTaskingSupported property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceMultiTaskingSupported(String value) {
        this.deviceMultiTaskingSupported = value;
    }

    /**
     * Gets the value of the deviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Sets the value of the deviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceName(String value) {
        this.deviceName = value;
    }

    /**
     * Gets the value of the deviceSystemName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceSystemName() {
        return deviceSystemName;
    }

    /**
     * Sets the value of the deviceSystemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceSystemName(String value) {
        this.deviceSystemName = value;
    }

    /**
     * Gets the value of the deviceSystemVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceSystemVersion() {
        return deviceSystemVersion;
    }

    /**
     * Sets the value of the deviceSystemVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceSystemVersion(String value) {
        this.deviceSystemVersion = value;
    }

    /**
     * Gets the value of the languages property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguages() {
        return languages;
    }

    /**
     * Sets the value of the languages property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguages(String value) {
        this.languages = value;
    }

    /**
     * Gets the value of the wiFiMacAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWiFiMacAddress() {
        return wiFiMacAddress;
    }

    /**
     * Sets the value of the wiFiMacAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWiFiMacAddress(String value) {
        this.wiFiMacAddress = value;
    }

    /**
     * Gets the value of the wiFiNetworksData property.
     * 
     * @return
     *     possible object is
     *     {@link WiFiNetworkData }
     *     
     */
    public WiFiNetworkData getWiFiNetworksData() {
        return wiFiNetworksData;
    }

    /**
     * Sets the value of the wiFiNetworksData property.
     * 
     * @param value
     *     allowed object is
     *     {@link WiFiNetworkData }
     *     
     */
    public void setWiFiNetworksData(WiFiNetworkData value) {
        this.wiFiNetworksData = value;
    }

    /**
     * Gets the value of the cellTowerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellTowerID() {
        return cellTowerID;
    }

    /**
     * Sets the value of the cellTowerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellTowerID(String value) {
        this.cellTowerID = value;
    }

    /**
     * Gets the value of the locationAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationAreaCode() {
        return locationAreaCode;
    }

    /**
     * Sets the value of the locationAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationAreaCode(String value) {
        this.locationAreaCode = value;
    }

    /**
     * Gets the value of the screenSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScreenSize() {
        return screenSize;
    }

    /**
     * Sets the value of the screenSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScreenSize(String value) {
        this.screenSize = value;
    }

    /**
     * Gets the value of the numberOfAddressBookEntries property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfAddressBookEntries() {
        return numberOfAddressBookEntries;
    }

    /**
     * Sets the value of the numberOfAddressBookEntries property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfAddressBookEntries(Integer value) {
        this.numberOfAddressBookEntries = value;
    }

    /**
     * Gets the value of the rsaApplicationkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRsaApplicationkey() {
        return rsaApplicationkey;
    }

    /**
     * Sets the value of the rsaApplicationkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRsaApplicationkey(String value) {
        this.rsaApplicationkey = value;
    }

    /**
     * Gets the value of the wapClientID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWapClientID() {
        return wapClientID;
    }

    /**
     * Sets the value of the wapClientID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWapClientID(String value) {
        this.wapClientID = value;
    }

    /**
     * Gets the value of the vendorClientID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorClientID() {
        return vendorClientID;
    }

    /**
     * Sets the value of the vendorClientID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorClientID(String value) {
        this.vendorClientID = value;
    }

    /**
     * Gets the value of the mcc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMcc() {
        return mcc;
    }

    /**
     * Sets the value of the mcc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMcc(String value) {
        this.mcc = value;
    }

    /**
     * Gets the value of the mnc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMnc() {
        return mnc;
    }

    /**
     * Sets the value of the mnc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMnc(String value) {
        this.mnc = value;
    }

    /**
     * Gets the value of the osId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOsId() {
        return osId;
    }

    /**
     * Sets the value of the osId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOsId(String value) {
        this.osId = value;
    }

    /**
     * Gets the value of the mobileSdkData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileSdkData() {
        return mobileSdkData;
    }

    /**
     * Sets the value of the mobileSdkData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileSdkData(String value) {
        this.mobileSdkData = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This defines the device management request payload
 * 
 * <p>Java class for DeviceManagementRequestPayload complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeviceManagementRequestPayload">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actionTypeList" type="{http://ws.csd.rsa.com}DeviceActionTypeList" minOccurs="0"/>
 *         &lt;element name="deviceData" type="{http://ws.csd.rsa.com}DeviceData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceManagementRequestPayload", propOrder = {
    "actionTypeList",
    "deviceData"
})
public class DeviceManagementRequestPayload {

    protected DeviceActionTypeList actionTypeList;
    protected DeviceData deviceData;

    /**
     * Gets the value of the actionTypeList property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceActionTypeList }
     *     
     */
    public DeviceActionTypeList getActionTypeList() {
        return actionTypeList;
    }

    /**
     * Sets the value of the actionTypeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceActionTypeList }
     *     
     */
    public void setActionTypeList(DeviceActionTypeList value) {
        this.actionTypeList = value;
    }

    /**
     * Gets the value of the deviceData property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceData }
     *     
     */
    public DeviceData getDeviceData() {
        return deviceData;
    }

    /**
     * Sets the value of the deviceData property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceData }
     *     
     */
    public void setDeviceData(DeviceData value) {
        this.deviceData = value;
    }

}

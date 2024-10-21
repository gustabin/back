
package ar.com.santanderrio.obp.generated.webservices.rsa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This defines the device management response payload
 * 
 * <p>Java class for DeviceManagementResponsePayload complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeviceManagementResponsePayload">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acspAccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="callStatus" type="{http://ws.csd.rsa.com}CallStatus" minOccurs="0"/>
 *         &lt;element name="deviceData" type="{http://ws.csd.rsa.com}DeviceData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceManagementResponsePayload", propOrder = {
    "acspAccountId",
    "callStatus",
    "deviceData"
})
public class DeviceManagementResponsePayload {

    protected String acspAccountId;
    protected CallStatus callStatus;
    protected List<DeviceData> deviceData;

    /**
     * Gets the value of the acspAccountId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcspAccountId() {
        return acspAccountId;
    }

    /**
     * Sets the value of the acspAccountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcspAccountId(String value) {
        this.acspAccountId = value;
    }

    /**
     * Gets the value of the callStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CallStatus }
     *     
     */
    public CallStatus getCallStatus() {
        return callStatus;
    }

    /**
     * Sets the value of the callStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallStatus }
     *     
     */
    public void setCallStatus(CallStatus value) {
        this.callStatus = value;
    }

    /**
     * Gets the value of the deviceData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deviceData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeviceData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeviceData }
     * 
     * 
     */
    public List<DeviceData> getDeviceData() {
        if (deviceData == null) {
            deviceData = new ArrayList<DeviceData>();
        }
        return this.deviceData;
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeviceActionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DeviceActionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BROWSE_DEVICES"/>
 *     &lt;enumeration value="UPDATE_DEVICE"/>
 *     &lt;enumeration value="UNBIND_ALL_DEVICES"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DeviceActionType")
@XmlEnum
public enum DeviceActionType {

    BROWSE_DEVICES,
    UPDATE_DEVICE,
    UNBIND_ALL_DEVICES;

    public String value() {
        return name();
    }

    public static DeviceActionType fromValue(String v) {
        return valueOf(v);
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSUserType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WSUserType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PERSISTENT"/>
 *     &lt;enumeration value="NONPERSISTENT"/>
 *     &lt;enumeration value="BAIT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WSUserType")
@XmlEnum
public enum WSUserType {

    PERSISTENT,
    NONPERSISTENT,
    BAIT;

    public String value() {
        return name();
    }

    public static WSUserType fromValue(String v) {
        return valueOf(v);
    }

}

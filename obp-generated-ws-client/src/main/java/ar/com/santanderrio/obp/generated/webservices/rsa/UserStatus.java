
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UserStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NOTENROLLED"/>
 *     &lt;enumeration value="UNVERIFIED"/>
 *     &lt;enumeration value="VERIFIED"/>
 *     &lt;enumeration value="DELETE"/>
 *     &lt;enumeration value="LOCKOUT"/>
 *     &lt;enumeration value="UNLOCKED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UserStatus")
@XmlEnum
public enum UserStatus {

    NOTENROLLED,
    UNVERIFIED,
    VERIFIED,
    DELETE,
    LOCKOUT,
    UNLOCKED;

    public String value() {
        return name();
    }

    public static UserStatus fromValue(String v) {
        return valueOf(v);
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OtherAccountOwnershipType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OtherAccountOwnershipType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ME_TO_ME"/>
 *     &lt;enumeration value="ME_TO_YOU"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OtherAccountOwnershipType")
@XmlEnum
public enum OtherAccountOwnershipType {

    ME_TO_ME,
    ME_TO_YOU;

    public String value() {
        return name();
    }

    public static OtherAccountOwnershipType fromValue(String v) {
        return valueOf(v);
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CredentialStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CredentialStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="DISABLED"/>
 *     &lt;enumeration value="LOCKED"/>
 *     &lt;enumeration value="UNLOCKED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CredentialStatus")
@XmlEnum
public enum CredentialStatus {

    ACTIVE,
    DISABLED,
    LOCKED,
    UNLOCKED;

    public String value() {
        return name();
    }

    public static CredentialStatus fromValue(String v) {
        return valueOf(v);
    }

}

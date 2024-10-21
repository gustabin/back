
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CredentialType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CredentialType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="QUESTION"/>
 *     &lt;enumeration value="OOBPHONE"/>
 *     &lt;enumeration value="OOBEMAIL"/>
 *     &lt;enumeration value="USER_DEFINED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CredentialType")
@XmlEnum
public enum CredentialType {

    QUESTION,
    OOBPHONE,
    OOBEMAIL,
    USER_DEFINED;

    public String value() {
        return name();
    }

    public static CredentialType fromValue(String v) {
        return valueOf(v);
    }

}

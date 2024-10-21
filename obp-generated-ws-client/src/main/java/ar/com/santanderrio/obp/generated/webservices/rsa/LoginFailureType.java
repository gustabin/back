
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoginFailureType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LoginFailureType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CARD_ON_BLACKLIST"/>
 *     &lt;enumeration value="CARD_EXPIRED"/>
 *     &lt;enumeration value="CARD_LOST"/>
 *     &lt;enumeration value="CARD_NONEXISTENT"/>
 *     &lt;enumeration value="INCORRECT_PIN"/>
 *     &lt;enumeration value="INCORRECT_CVV"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LoginFailureType")
@XmlEnum
public enum LoginFailureType {

    CARD_ON_BLACKLIST,
    CARD_EXPIRED,
    CARD_LOST,
    CARD_NONEXISTENT,
    INCORRECT_PIN,
    INCORRECT_CVV;

    public String value() {
        return name();
    }

    public static LoginFailureType fromValue(String v) {
        return valueOf(v);
    }

}

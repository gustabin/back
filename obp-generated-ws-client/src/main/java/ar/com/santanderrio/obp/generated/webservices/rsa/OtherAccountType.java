
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OtherAccountType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OtherAccountType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BILLER"/>
 *     &lt;enumeration value="PERSONAL_ACCOUNT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OtherAccountType")
@XmlEnum
public enum OtherAccountType {

    BILLER,
    PERSONAL_ACCOUNT;

    public String value() {
        return name();
    }

    public static OtherAccountType fromValue(String v) {
        return valueOf(v);
    }

}

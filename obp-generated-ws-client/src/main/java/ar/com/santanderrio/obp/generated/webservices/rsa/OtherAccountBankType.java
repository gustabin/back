
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OtherAccountBankType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OtherAccountBankType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SAME_BANK"/>
 *     &lt;enumeration value="OTHER_BANK"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OtherAccountBankType")
@XmlEnum
public enum OtherAccountBankType {

    SAME_BANK,
    OTHER_BANK;

    public String value() {
        return name();
    }

    public static OtherAccountBankType fromValue(String v) {
        return valueOf(v);
    }

}

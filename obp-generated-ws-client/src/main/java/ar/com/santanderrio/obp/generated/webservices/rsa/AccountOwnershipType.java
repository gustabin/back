
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountOwnershipType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccountOwnershipType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INDIVIDUAL"/>
 *     &lt;enumeration value="JOINT"/>
 *     &lt;enumeration value="TRUST"/>
 *     &lt;enumeration value="CUSTODIAL"/>
 *     &lt;enumeration value="BUSINESS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccountOwnershipType")
@XmlEnum
public enum AccountOwnershipType {

    INDIVIDUAL,
    JOINT,
    TRUST,
    CUSTODIAL,
    BUSINESS;

    public String value() {
        return name();
    }

    public static AccountOwnershipType fromValue(String v) {
        return valueOf(v);
    }

}

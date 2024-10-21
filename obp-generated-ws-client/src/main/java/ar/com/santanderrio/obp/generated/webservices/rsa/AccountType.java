
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccountType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CREDIT_CARD"/>
 *     &lt;enumeration value="DEBIT_CARD"/>
 *     &lt;enumeration value="CHECKING"/>
 *     &lt;enumeration value="CHECKING_WITH_OVERDRAFT"/>
 *     &lt;enumeration value="BROKERAGE"/>
 *     &lt;enumeration value="SAVINGS"/>
 *     &lt;enumeration value="CD"/>
 *     &lt;enumeration value="LINE_OF_CREDIT"/>
 *     &lt;enumeration value="RETIREMENT"/>
 *     &lt;enumeration value="MORTGAGE"/>
 *     &lt;enumeration value="USER_DEFINED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccountType")
@XmlEnum
public enum AccountType {

    CREDIT_CARD,
    DEBIT_CARD,
    CHECKING,
    CHECKING_WITH_OVERDRAFT,
    BROKERAGE,
    SAVINGS,
    CD,
    LINE_OF_CREDIT,
    RETIREMENT,
    MORTGAGE,
    USER_DEFINED;

    public String value() {
        return name();
    }

    public static AccountType fromValue(String v) {
        return valueOf(v);
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TermType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TermType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GOOD_FOR_DAY"/>
 *     &lt;enumeration value="GOOD_UNTIL_CANCELLED"/>
 *     &lt;enumeration value="IMMEDIATE_OR_CANCEL"/>
 *     &lt;enumeration value="FILL_OR_KILL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TermType")
@XmlEnum
public enum TermType {

    GOOD_FOR_DAY,
    GOOD_UNTIL_CANCELLED,
    IMMEDIATE_OR_CANCEL,
    FILL_OR_KILL;

    public String value() {
        return name();
    }

    public static TermType fromValue(String v) {
        return valueOf(v);
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PriceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PriceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MARKET"/>
 *     &lt;enumeration value="BRACKETED_FIXED"/>
 *     &lt;enumeration value="BRACKETED_POINTS"/>
 *     &lt;enumeration value="BRACKETED_PERCENTAGES"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PriceType")
@XmlEnum
public enum PriceType {

    MARKET,
    BRACKETED_FIXED,
    BRACKETED_POINTS,
    BRACKETED_PERCENTAGES;

    public String value() {
        return name();
    }

    public static PriceType fromValue(String v) {
        return valueOf(v);
    }

}

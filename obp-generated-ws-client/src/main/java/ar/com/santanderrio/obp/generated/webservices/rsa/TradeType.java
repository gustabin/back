
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TradeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TradeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BUY"/>
 *     &lt;enumeration value="SELL"/>
 *     &lt;enumeration value="SELL_SHORT"/>
 *     &lt;enumeration value="BUY_TO_COVER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TradeType")
@XmlEnum
public enum TradeType {

    BUY,
    SELL,
    SELL_SHORT,
    BUY_TO_COVER;

    public String value() {
        return name();
    }

    public static TradeType fromValue(String v) {
        return valueOf(v);
    }

}

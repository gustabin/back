
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChannelIndicatorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChannelIndicatorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="WEB"/>
 *     &lt;enumeration value="IVR"/>
 *     &lt;enumeration value="CALL_CENTER"/>
 *     &lt;enumeration value="BRANCH"/>
 *     &lt;enumeration value="ATM"/>
 *     &lt;enumeration value="MOBILE"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChannelIndicatorType")
@XmlEnum
public enum ChannelIndicatorType {

    WEB,
    IVR,
    CALL_CENTER,
    BRANCH,
    ATM,
    MOBILE,
    OTHER;

    public String value() {
        return name();
    }

    public static ChannelIndicatorType fromValue(String v) {
        return valueOf(v);
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActionCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ActionCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="ALLOW"/>
 *     &lt;enumeration value="REVIEW"/>
 *     &lt;enumeration value="DELAY_AND_REVIEW"/>
 *     &lt;enumeration value="STOP_AND_REVIEW"/>
 *     &lt;enumeration value="ELEVATE_SECURITY"/>
 *     &lt;enumeration value="REDIRECT_CHALLENGE"/>
 *     &lt;enumeration value="REDIRECT_COLLECT"/>
 *     &lt;enumeration value="CHALLENGE"/>
 *     &lt;enumeration value="COLLECT"/>
 *     &lt;enumeration value="DENY"/>
 *     &lt;enumeration value="BLOCK"/>
 *     &lt;enumeration value="LOCKED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ActionCode")
@XmlEnum
public enum ActionCode {

    NONE,
    ALLOW,
    REVIEW,
    DELAY_AND_REVIEW,
    STOP_AND_REVIEW,
    ELEVATE_SECURITY,
    REDIRECT_CHALLENGE,
    REDIRECT_COLLECT,
    CHALLENGE,
    COLLECT,
    DENY,
    BLOCK,
    LOCKED;

    public String value() {
        return name();
    }

    public static ActionCode fromValue(String v) {
        return valueOf(v);
    }

}

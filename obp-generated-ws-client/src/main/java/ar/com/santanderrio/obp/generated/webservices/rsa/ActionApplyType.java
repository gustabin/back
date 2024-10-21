
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActionApplyType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ActionApplyType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="STRICT"/>
 *     &lt;enumeration value="LIGHT"/>
 *     &lt;enumeration value="OVERRIDE"/>
 *     &lt;enumeration value="LOG"/>
 *     &lt;enumeration value="NONE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ActionApplyType")
@XmlEnum
public enum ActionApplyType {

    STRICT,
    LIGHT,
    OVERRIDE,
    LOG,
    NONE;

    public String value() {
        return name();
    }

    public static ActionApplyType fromValue(String v) {
        return valueOf(v);
    }

}

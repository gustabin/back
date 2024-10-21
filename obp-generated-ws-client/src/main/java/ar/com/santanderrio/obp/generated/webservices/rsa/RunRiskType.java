
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RunRiskType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RunRiskType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RISK_ONLY"/>
 *     &lt;enumeration value="ALL"/>
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="DEVICE_ONLY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RunRiskType")
@XmlEnum
public enum RunRiskType {

    RISK_ONLY,
    ALL,
    NONE,
    DEVICE_ONLY;

    public String value() {
        return name();
    }

    public static RunRiskType fromValue(String v) {
        return valueOf(v);
    }

}

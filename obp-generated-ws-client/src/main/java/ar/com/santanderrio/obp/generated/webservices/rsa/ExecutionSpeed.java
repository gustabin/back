
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExecutionSpeed.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ExecutionSpeed">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SEVERAL_DAYS"/>
 *     &lt;enumeration value="OVER_NIGHT"/>
 *     &lt;enumeration value="FEW_HOURS"/>
 *     &lt;enumeration value="REAL_TIME"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ExecutionSpeed")
@XmlEnum
public enum ExecutionSpeed {

    SEVERAL_DAYS,
    OVER_NIGHT,
    FEW_HOURS,
    REAL_TIME;

    public String value() {
        return name();
    }

    public static ExecutionSpeed fromValue(String v) {
        return valueOf(v);
    }

}

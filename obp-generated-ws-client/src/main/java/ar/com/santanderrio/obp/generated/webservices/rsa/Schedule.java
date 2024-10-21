
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Schedule.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Schedule">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="IMMEDIATE"/>
 *     &lt;enumeration value="SCHEDULED"/>
 *     &lt;enumeration value="RECURRING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Schedule")
@XmlEnum
public enum Schedule {

    IMMEDIATE,
    SCHEDULED,
    RECURRING;

    public String value() {
        return name();
    }

    public static Schedule fromValue(String v) {
        return valueOf(v);
    }

}

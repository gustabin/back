
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SSNType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SSNType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SSN4"/>
 *     &lt;enumeration value="SSN9"/>
 *     &lt;enumeration value="SSN5"/>
 *     &lt;enumeration value="OTHER"/>
 *     &lt;enumeration value="NOSSN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SSNType", namespace = "http://ws.kba.csd.rsa.com")
@XmlEnum
public enum SSNType {

    @XmlEnumValue("SSN4")
    SSN_4("SSN4"),
    @XmlEnumValue("SSN9")
    SSN_9("SSN9"),
    @XmlEnumValue("SSN5")
    SSN_5("SSN5"),
    OTHER("OTHER"),
    NOSSN("NOSSN");
    private final String value;

    SSNType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SSNType fromValue(String v) {
        for (SSNType c: SSNType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

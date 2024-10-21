
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MockCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MockCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="Error"/>
 *     &lt;enumeration value="ErrorException"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MockCode")
@XmlEnum
public enum MockCode {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Error")
    ERROR("Error"),
    @XmlEnumValue("ErrorException")
    ERROR_EXCEPTION("ErrorException");
    private final String value;

    MockCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MockCode fromValue(String v) {
        for (MockCode c: MockCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

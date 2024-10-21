
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MilterOption.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MilterOption">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DISABLED"/>
 *     &lt;enumeration value="PHRASE_ENABLED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MilterOption")
@XmlEnum
public enum MilterOption {

    DISABLED,
    PHRASE_ENABLED;

    public String value() {
        return name();
    }

    public static MilterOption fromValue(String v) {
        return valueOf(v);
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ATMLocationTypes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ATMLocationTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BANK_BRANCH"/>
 *     &lt;enumeration value="PETROL_STATION"/>
 *     &lt;enumeration value="PUBLIC_TRANSPORT"/>
 *     &lt;enumeration value="STREET"/>
 *     &lt;enumeration value="CONVENIENCE_STORE"/>
 *     &lt;enumeration value="SUPERMARKET"/>
 *     &lt;enumeration value="LEISURE_FACILITY"/>
 *     &lt;enumeration value="DRIVE_THRU"/>
 *     &lt;enumeration value="ENTERTAINMENT_VENUE"/>
 *     &lt;enumeration value="TRANSPORT_TERMINAL"/>
 *     &lt;enumeration value="POST_OFFICE"/>
 *     &lt;enumeration value="RETAIL_OUTLET"/>
 *     &lt;enumeration value="CASINO"/>
 *     &lt;enumeration value="GOVERNMENT_OFFICE"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ATMLocationTypes")
@XmlEnum
public enum ATMLocationTypes {

    BANK_BRANCH,
    PETROL_STATION,
    PUBLIC_TRANSPORT,
    STREET,
    CONVENIENCE_STORE,
    SUPERMARKET,
    LEISURE_FACILITY,
    DRIVE_THRU,
    ENTERTAINMENT_VENUE,
    TRANSPORT_TERMINAL,
    POST_OFFICE,
    RETAIL_OUTLET,
    CASINO,
    GOVERNMENT_OFFICE,
    OTHER;

    public String value() {
        return name();
    }

    public static ATMLocationTypes fromValue(String v) {
        return valueOf(v);
    }

}

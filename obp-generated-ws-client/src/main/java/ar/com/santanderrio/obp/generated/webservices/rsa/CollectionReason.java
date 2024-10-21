
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CollectionReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CollectionReason">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CSR_REQUESTED"/>
 *     &lt;enumeration value="USER_SETTINGS"/>
 *     &lt;enumeration value="FIRST_COLLECTION"/>
 *     &lt;enumeration value="REFRESH_AFTER_FAILURE"/>
 *     &lt;enumeration value="ADDITIONAL_COLLECTION"/>
 *     &lt;enumeration value="REFRESH_COLLECTION"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CollectionReason")
@XmlEnum
public enum CollectionReason {

    CSR_REQUESTED,
    USER_SETTINGS,
    FIRST_COLLECTION,
    REFRESH_AFTER_FAILURE,
    ADDITIONAL_COLLECTION,
    REFRESH_COLLECTION;

    public String value() {
        return name();
    }

    public static CollectionReason fromValue(String v) {
        return valueOf(v);
    }

}

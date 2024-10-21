
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountRelationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccountRelationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PRIMARY_OWNER"/>
 *     &lt;enumeration value="CO_OWNER"/>
 *     &lt;enumeration value="AUTHORIZED_USER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccountRelationType")
@XmlEnum
public enum AccountRelationType {

    PRIMARY_OWNER,
    CO_OWNER,
    AUTHORIZED_USER;

    public String value() {
        return name();
    }

    public static AccountRelationType fromValue(String v) {
        return valueOf(v);
    }

}

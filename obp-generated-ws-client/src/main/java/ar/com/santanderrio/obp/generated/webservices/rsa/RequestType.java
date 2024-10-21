
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RequestType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ANALYZE"/>
 *     &lt;enumeration value="AUTHENTICATE"/>
 *     &lt;enumeration value="CHALLENGE"/>
 *     &lt;enumeration value="CREATEUSER"/>
 *     &lt;enumeration value="NOTIFY"/>
 *     &lt;enumeration value="QUERY"/>
 *     &lt;enumeration value="QUERYAUTHSTATUS"/>
 *     &lt;enumeration value="UPDATEUSER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RequestType")
@XmlEnum
public enum RequestType {

    ANALYZE,
    AUTHENTICATE,
    CHALLENGE,
    CREATEUSER,
    NOTIFY,
    QUERY,
    QUERYAUTHSTATUS,
    UPDATEUSER;

    public String value() {
        return name();
    }

    public static RequestType fromValue(String v) {
        return valueOf(v);
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for APIType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="APIType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="WEB_REDIRECT"/>
 *     &lt;enumeration value="DIRECT_SOAP_API"/>
 *     &lt;enumeration value="ANALYZE_ONLY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "APIType")
@XmlEnum
public enum APIType {

    WEB_REDIRECT,
    DIRECT_SOAP_API,
    ANALYZE_ONLY;

    public String value() {
        return name();
    }

    public static APIType fromValue(String v) {
        return valueOf(v);
    }

}


package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HttpMethodEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="HttpMethodEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Get"/>
 *     &lt;enumeration value="Post"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "HttpMethodEnum", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.Enums")
@XmlEnum
public enum HttpMethodEnum {

    @XmlEnumValue("Get")
    GET("Get"),
    @XmlEnumValue("Post")
    POST("Post");
    private final String value;

    HttpMethodEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HttpMethodEnum fromValue(String v) {
        for (HttpMethodEnum c: HttpMethodEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

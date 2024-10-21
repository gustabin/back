
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoOperacion.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoOperacion">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Login"/>
 *     &lt;enumeration value="CambioDePin"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoOperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.Dtos.ProxyLogin")
@XmlEnum
public enum TipoOperacion {

    @XmlEnumValue("Login")
    LOGIN("Login"),
    @XmlEnumValue("CambioDePin")
    CAMBIO_DE_PIN("CambioDePin");
    private final String value;

    TipoOperacion(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoOperacion fromValue(String v) {
        for (TipoOperacion c: TipoOperacion.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

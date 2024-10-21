
package ar.com.santanderrio.obp.generated.webservices.mercado.canal;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoHash.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoHash">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="B64"/>
 *     &lt;enumeration value="PEM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoHash", namespace = "http://schemas.datacontract.org/2004/07/Isban.Adsec.Service")
@XmlEnum
public enum TipoHash {

    @XmlEnumValue("B64")
    B_64("B64"),
    PEM("PEM");
    private final String value;

    TipoHash(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoHash fromValue(String v) {
        for (TipoHash c: TipoHash.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

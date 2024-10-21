
package ar.com.santanderrio.obp.generated.webservices.mercado.canal;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DatosFirmado.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DatosFirmado">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Si"/>
 *     &lt;enumeration value="No"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DatosFirmado", namespace = "http://schemas.datacontract.org/2004/07/Isban.Adsec.Service")
@XmlEnum
public enum DatosFirmado {

    @XmlEnumValue("Si")
    SI("Si"),
    @XmlEnumValue("No")
    NO("No");
    private final String value;

    DatosFirmado(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DatosFirmado fromValue(String v) {
        for (DatosFirmado c: DatosFirmado.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

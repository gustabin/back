
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OOBActionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OOBActionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ADD_OOB"/>
 *     &lt;enumeration value="GET_OOB"/>
 *     &lt;enumeration value="DELETE_OOB"/>
 *     &lt;enumeration value="UPDATE_OOB"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OOBActionType")
@XmlEnum
public enum OOBActionType {

    ADD_OOB,
    GET_OOB,
    DELETE_OOB,
    UPDATE_OOB;

    public String value() {
        return name();
    }

    public static OOBActionType fromValue(String v) {
        return valueOf(v);
    }

}


package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GenericActionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GenericActionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GET_USER_STATUS"/>
 *     &lt;enumeration value="GET_USER_PREFERENCE"/>
 *     &lt;enumeration value="GET_USER_GROUP"/>
 *     &lt;enumeration value="GET_SYSTEM_CREDENTIAL"/>
 *     &lt;enumeration value="GET_USER_CREDENTIAL"/>
 *     &lt;enumeration value="GET_SYSTEM_CREDENTIAL_EXTENDED"/>
 *     &lt;enumeration value="GET_USER_CREDENTIAL_EXTENDED"/>
 *     &lt;enumeration value="BROWSE_USER_GROUP"/>
 *     &lt;enumeration value="SET_USER_GROUP"/>
 *     &lt;enumeration value="UPDATE_USER_NAME"/>
 *     &lt;enumeration value="SET_USER_STATUS"/>
 *     &lt;enumeration value="SET_USER_PREFERENCE"/>
 *     &lt;enumeration value="ADD_FAVORITE"/>
 *     &lt;enumeration value="DEL_FAVORITE"/>
 *     &lt;enumeration value="CLEAR_FAVORITES"/>
 *     &lt;enumeration value="GET_FAVORITES"/>
 *     &lt;enumeration value="OPEN_SESSION"/>
 *     &lt;enumeration value="CLOSE_SESSION"/>
 *     &lt;enumeration value="COMMIT"/>
 *     &lt;enumeration value="CANCEL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GenericActionType")
@XmlEnum
public enum GenericActionType {

    GET_USER_STATUS,
    GET_USER_PREFERENCE,
    GET_USER_GROUP,
    GET_SYSTEM_CREDENTIAL,
    GET_USER_CREDENTIAL,
    GET_SYSTEM_CREDENTIAL_EXTENDED,
    GET_USER_CREDENTIAL_EXTENDED,
    BROWSE_USER_GROUP,
    SET_USER_GROUP,
    UPDATE_USER_NAME,
    SET_USER_STATUS,
    SET_USER_PREFERENCE,
    ADD_FAVORITE,
    DEL_FAVORITE,
    CLEAR_FAVORITES,
    GET_FAVORITES,
    OPEN_SESSION,
    CLOSE_SESSION,
    COMMIT,
    CANCEL;

    public String value() {
        return name();
    }

    public static GenericActionType fromValue(String v) {
        return valueOf(v);
    }

}

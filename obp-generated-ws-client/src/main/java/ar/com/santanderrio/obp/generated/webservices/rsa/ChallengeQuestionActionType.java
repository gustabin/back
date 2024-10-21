
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChallengeQuestionActionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChallengeQuestionActionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ADD_USER_QUESTION"/>
 *     &lt;enumeration value="BROWSE_QUESTION"/>
 *     &lt;enumeration value="GET_USER_QUESTION"/>
 *     &lt;enumeration value="SET_USER_QUESTION"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChallengeQuestionActionType")
@XmlEnum
public enum ChallengeQuestionActionType {

    ADD_USER_QUESTION,
    BROWSE_QUESTION,
    GET_USER_QUESTION,
    SET_USER_QUESTION;

    public String value() {
        return name();
    }

    public static ChallengeQuestionActionType fromValue(String v) {
        return valueOf(v);
    }

}

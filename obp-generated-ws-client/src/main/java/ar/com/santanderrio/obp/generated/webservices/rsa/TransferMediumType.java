
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransferMediumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransferMediumType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INTERNAL"/>
 *     &lt;enumeration value="BILLPAY_MAIL"/>
 *     &lt;enumeration value="BILLPAY_ELEC"/>
 *     &lt;enumeration value="BALANCE_TRANSFER"/>
 *     &lt;enumeration value="ACH"/>
 *     &lt;enumeration value="WIRE"/>
 *     &lt;enumeration value="INTL_WIRE"/>
 *     &lt;enumeration value="CHECK"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransferMediumType")
@XmlEnum
public enum TransferMediumType {

    INTERNAL,
    BILLPAY_MAIL,
    BILLPAY_ELEC,
    BALANCE_TRANSFER,
    ACH,
    WIRE,
    INTL_WIRE,
    CHECK;

    public String value() {
        return name();
    }

    public static TransferMediumType fromValue(String v) {
        return valueOf(v);
    }

}

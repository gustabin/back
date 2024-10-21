/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * @author sergio.e.goldentair
 *
 */
@XmlType
@XmlEnum(String.class)
public enum MyaCodOperacionEnum {
    @XmlEnumValue("A") ALTA, @XmlEnumValue("B") BAJA, @XmlEnumValue("M") MODIFICACION, @XmlEnumValue("C") CONSULTA;

}

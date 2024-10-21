/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Tipo de Cliente tomado del servicio de identificación del cliente.
 * 
 * @author sergio.e.goldentair
 *
 */
@XmlType
@XmlEnum(String.class)
public enum MyaTipoPersonaEnum {
    @XmlEnumValue("F") FISICA, @XmlEnumValue("J") JURIDICA, @XmlEnumValue("I") INDIVIDUO, @XmlEnumValue("E") EMPRESA;
}

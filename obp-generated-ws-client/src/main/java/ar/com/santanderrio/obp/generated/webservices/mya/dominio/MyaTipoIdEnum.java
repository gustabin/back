/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Tipo Documento tomado del servicio de identificación del cliente.
 * 
 * @author sergio.e.goldentair
 *
 */
@XmlType
@XmlEnum(String.class)
public enum MyaTipoIdEnum {
    @XmlEnumValue("N") DNI, @XmlEnumValue("C") LIBRETA_CIVICA, @XmlEnumValue("E") LIBRETA_ENROLAMIENTO, @XmlEnumValue("I") CEDULA_IDENTIDAD, @XmlEnumValue("M") CEDULA_MILITAR, @XmlEnumValue("P") PASAPORTE, @XmlEnumValue("T") CUIT, @XmlEnumValue("L") CUIL, @XmlEnumValue("D") CDI, @XmlEnumValue("X") DNI_EXTRANJERO, @XmlEnumValue("F") CERTIFICADO_INT;
}

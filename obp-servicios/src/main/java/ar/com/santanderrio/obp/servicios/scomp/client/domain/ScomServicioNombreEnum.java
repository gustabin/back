/**
 * 
 */
package ar.com.santanderrio.obp.servicios.scomp.client.domain;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Lista de operaciones que acepta el tag nombre en el xml que se envia a scomp
 * y operaciones del ws.
 * 
 * @author sergio.e.goldentair
 *
 */
@XmlType
@XmlEnum(String.class)
public enum ScomServicioNombreEnum {
    @XmlEnumValue("CNSLISTACOMPROBANTES") CNSLISTACOMPROBANTES,
    @XmlEnumValue("ALTACOMPROBANTE") ALTACOMPROBANTE,
    @XmlEnumValue("CNSLISTACOMPROBANTESOREXT") CNSLISTACOMPROBANTESOREXT,
    @XmlEnumValue("CNSDETALLECOMPROBANTE") CNSDETALLECOMPROBANTE;
}

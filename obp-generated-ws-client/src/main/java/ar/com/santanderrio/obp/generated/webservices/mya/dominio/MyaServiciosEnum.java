/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Lista de operaciones que acepta el tag Servicio en el xml que se envia a Mya
 * y operaciones del ws.
 * 
 * @author sergio.e.goldentair
 *
 */
@XmlType
@XmlEnum(String.class)
public enum MyaServiciosEnum {
	@XmlEnumValue("confirmarEmail") CONFIRMAR_EMAIL, @XmlEnumValue("getStatusCliente") STATUS_CLIENTE, @XmlEnumValue("getEstadoCliente") ESTADO_CLIENTE, @XmlEnumValue("getParametros") PARAMETROS, @XmlEnumValue("getSuscripciones") SUSCRIPCIONES, @XmlEnumValue("registrar") REGISTRAR, @XmlEnumValue("registrarConDestino") REGISTRAR_CON_DESTINO, @XmlEnumValue("updateDestinos") UPDATE_DESTINOS, @XmlEnumValue("updateEstadoCliente") UPDATE_ESTADO_CLIENTE, @XmlEnumValue("updateMensajes") UPDATE_MENSAJES, @XmlEnumValue("updateMensajeRecarga") UPDATE_MENSAJE_RECARGA, @XmlEnumValue("getEstadoClienteV3") ESTADO_CLIENTE_V3;
}

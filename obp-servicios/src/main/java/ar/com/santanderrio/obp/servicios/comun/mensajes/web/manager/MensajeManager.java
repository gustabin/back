/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * The Interface MensajeManager.
 */
public interface MensajeManager {

	/**
	 * Obtener mensaje por codigo. Si no encuentra el mensaje o la BD se
	 * encuentra caida, trae un mensaje vacio.
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the mensaje
	 */
	Mensaje obtenerMensajePorCodigo(String codigoMensaje);

	/**
	 * Obtener mensaje por codigo con error generico. Si no encuentra el mensaje
	 * o la BD se encuentra caida, dispara un robot exception que se traduce a
	 * un error generico
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the mensaje
	 */
	Mensaje obtenerMensajePorCodigoConErrorGenerico(String codigoMensaje);

	/**
	 * Limpia cache de mensajes.
	 *
	 * @return true o false
	 */
	Respuesta<Boolean> limpiarMensajes();
}

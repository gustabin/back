/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.mensaje.bo;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;

/**
 * The Interface MensajeBO.
 */
public interface MensajeBO {

	/**
	 * Obtener mensaje descripcion.
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the string
	 */
	@Deprecated
	String obtenerMensajeDescripcion(String codigoMensaje);

	/**
	 * Obtiene un mensaje por su codigo.
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the mensaje
	 * 
	 */
	@Deprecated
	Mensaje obtenerMensaje(String codigoMensaje);

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
	 * Obtener mensaje por codigo.
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @param parametros
	 *            the parametros
	 * @return the mensaje
	 */
	Mensaje obtenerMensajePorCodigo(String codigoMensaje, String... parametros);

}

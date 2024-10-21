/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.mensaje.dao;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.servicios.exception.GeneralMapper;
import ar.com.santanderrio.obp.servicios.exception.RobotException;

/**
 * The Interface MensajeDAO.
 */
public interface MensajeDAO {

	/**
	 * Obtener la descripcion asociada al codigo de mensaje solicitado, si no
	 * esta presente devuelvo null.
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the string
	 * 
	 *         TODO: USAR MensajeDAO#obtenerMensajePorCodigo
	 */
	@Deprecated
	String obtenerMensajeDescripcion(String codigoMensaje);

	/**
	 * Recupera el mensaje de la base de datos, segun un codigo de mensaje. En
	 * caso de no encontrar el mensaje, genera un mensaje de error generico.
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the Mensaje
	 * 
	 *         TODO: USAR MensajeDAO#obtenerMensajePorCodigo
	 */
	@Deprecated
	Mensaje obtenerMensaje(String codigoMensaje);

	/**
	 * Recupera el mensaje de la base de datos, segun un codigo de mensaje. En
	 * caso de no encontrar el mensaje, genera un mensaje de error generico. Si
	 * el mensaje no existe, arroja una excepcion {@link RobotException}
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the Mensaje
	 * @see {@link GeneralMapper}
	 */
	Mensaje obtenerMensajePorCodigo(String codigoMensaje);
}

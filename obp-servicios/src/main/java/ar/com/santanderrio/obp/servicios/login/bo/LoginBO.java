/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.bo;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * The Interface LoginBO.
 */
public interface LoginBO {

	/**
	 * Obtener inicio login.
	 *
	 * @return the respuesta
	 */
	Respuesta<Mensaje> obtenerInicioLogin();

}

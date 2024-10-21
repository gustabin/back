/*
 * 
 */
package ar.com.santanderrio.obp.servicios.token.externos;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * The Interface TokenManager.
 */
public interface TokenManager {

	/**
	 * Obtener token encriptado.
	 *
	 * @return the respuesta
	 */
	Respuesta<TokenView> obtenerTokenEncriptado();

}

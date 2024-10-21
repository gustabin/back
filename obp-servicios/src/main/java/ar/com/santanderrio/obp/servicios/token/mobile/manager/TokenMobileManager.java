/*
 * 
 */
package ar.com.santanderrio.obp.servicios.token.mobile.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.mobile.entities.TokenMobile;

/**
 * The Interface TokenMobileManager.
 */
public interface TokenMobileManager {

	/**
	 * Consultar token mobile.
	 *
	 * @param nup
	 *            the nup
	 * @return the respuesta
	 */
	Respuesta<TokenMobile> consultarTokenMobile(String nup);

}

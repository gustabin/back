/*
 * 
 */
package ar.com.santanderrio.obp.servicios.token.mobile.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.mobile.entities.TokenMobile;

/**
 * The Interface TokenMobileBO.
 */
public interface TokenMobileBO {

	/**
	 * Consultar token mobile.
	 *
	 * @param nup
	 *            the nup
	 * @return the respuesta
	 */
	Respuesta<TokenMobile> consultarTokenMobile(String nup);

}

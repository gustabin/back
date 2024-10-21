/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cosmos.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cosmos.web.view.CosmosInView;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;


/**
 * The Interface CosmosManager.
 */
public interface CosmosManager {
	
	/**
	 * Obtener token cosmos.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */
	Respuesta<TokenView> obtenerTokenCosmos(CosmosInView inView);
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.fecp.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;


/**
 * The Interface CosmosManager.
 */
public interface FecpManager {
	
	/**
	 * Obtener token cosmos.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */
	Respuesta<TokenView> obtenerTokenFecp(OfertaComercialView oferta);
}

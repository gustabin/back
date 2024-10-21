/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tenencias.view.DesafioPresentarView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasInView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasView;

/**
 * Gestiona la logica relacionada a Tenencias.
 * 
 * @author
 *
 */
public interface TenenciasManager {

	/**
	 * realiza consultar en Tenencias.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return respuesta con el objeto view response.
	 */
	Respuesta<TenenciasView> consultarTenencias(TenenciasInView viewRequest);

	/**
	 * Challenge presentar.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<DesafioPresentarView> challengePresentar(DesafioPresentarView viewRequest);
	
}

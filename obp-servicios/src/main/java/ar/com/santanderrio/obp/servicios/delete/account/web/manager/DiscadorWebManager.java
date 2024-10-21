/*
 * 
 */
package ar.com.santanderrio.obp.servicios.delete.account.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.DiscadorView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.SolicitudDiscadorView;

/**
 * The Interface AadvantageManager.
 */
public interface DiscadorWebManager {

	/**
	 * Consulta millas aadvantage.
	 *
	 * @param inView
	 *            the in view
	 * @return the respuesta
	 */
	public Respuesta<DiscadorView> solicitarDiscador(SolicitudDiscadorView inView);
	
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.aadvantage.web.view.ConsultaMillasAadvantageView;
import ar.com.santanderrio.obp.servicios.aadvantage.web.view.SolicitudMillasAadvantageView;

/**
 * The Interface AadvantageManager.
 */
public interface AadvantageManager {

	/**
	 * Consulta millas aadvantage.
	 *
	 * @param inView
	 *            the in view
	 * @return the respuesta
	 */
	public Respuesta<ConsultaMillasAadvantageView> consultaMillasAadvantage(SolicitudMillasAadvantageView inView);
	
}

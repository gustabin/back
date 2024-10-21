/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.TopbarHomeView;

/**
 * The Interface TopbarHomeManager.
 */
public interface TopbarHomeManager {

	/**
	 * Obtener topbar.
	 *
	 * @return the respuesta
	 */
	Respuesta<TopbarHomeView> obtenerTopbar();

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.TableroHomeView;

/**
 * The Interface TableroHomeManager.
 */
public interface TableroHomeManager {

	/**
	 * Obtener tablero.
	 *
	 * @return the respuesta
	 */
	Respuesta<TableroHomeView> obtenerTablero();

}

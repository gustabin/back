/**
 * 
 */
package ar.com.santanderrio.obp.servicios.status.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.status.view.StatusView;

/**
 * Obtener informacion de estado de la app.
 * 
 * @author sergio.e.goldentair
 *
 */
public interface StatusManager {
	/**
	 * Obtener informacion util para confirmar que la instancia puede dar
	 * respeusta a todas las operaciones.
	 * 
	 * @return estado de la instancia
	 */
	Respuesta<StatusView> getStatus();
}

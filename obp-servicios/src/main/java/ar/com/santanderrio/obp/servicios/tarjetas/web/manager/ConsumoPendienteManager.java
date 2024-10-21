/**
 *
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosPendientesView;

/**
 * The Interface ConsumoPendienteManager.
 */
public interface ConsumoPendienteManager {

	/**
	 * Obtener consumo pendiente.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConsumosPendientesView> obtenerConsumoPendiente();

}
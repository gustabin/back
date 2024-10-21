package ar.com.santanderrio.obp.servicios.home.web.manager;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;

/**
 * The Interface SeccionReclamosHomeManager.
 */
public interface SeccionReclamosHomeManager {

	/**
	 * Obtener seccion.
	 *
	 * @param cliente the cliente
	 * @return the seccion view
	 */
	SeccionView obtenerSeccion(Cliente cliente);
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.home.web.view.ControllerView;

/**
 * The Interface ControllerHomeManager.
 */
public interface ControllerHomeManager {

	/**
	 * Obtener controller.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the controller view
	 */
	ControllerView obtenerController(Cliente cliente);

}

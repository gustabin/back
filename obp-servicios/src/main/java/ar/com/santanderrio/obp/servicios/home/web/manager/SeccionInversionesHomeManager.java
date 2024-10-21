/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;

/**
 * The Interface TopbarHomeManager.
 */
public interface SeccionInversionesHomeManager {

	/**
	 * Valida las opciones correspondientes a la seccion.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the seccion view
	 */
	SeccionView obtenerSeccion(Cliente cliente);

    Respuesta<Void> notificarAccesoServiciosDeInversion();

}

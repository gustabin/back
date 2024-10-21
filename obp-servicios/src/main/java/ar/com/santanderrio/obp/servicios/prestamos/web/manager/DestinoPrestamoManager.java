/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * The Interface DestinoPrestamoManager.
 */
public interface DestinoPrestamoManager {

	/**
	 * Vaciar destinos prestamo.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> vaciarDestinosPrestamo();

}

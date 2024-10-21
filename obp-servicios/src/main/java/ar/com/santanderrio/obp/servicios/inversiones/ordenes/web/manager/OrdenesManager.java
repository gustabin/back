/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.FiltrosOrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenesView;

/**
 * The Interface OrdenesManager.
 * 
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @since Mon 23, 2017
 */
public interface OrdenesManager {

	/**
	 * Iniciar ordenes operaciones. Carga la vista con los datos de inicio de la
	 * pantalla de busquedas y operaciones de inversiones.
	 *
	 * @return the respuesta
	 */
	Respuesta<OrdenesView> iniciarOrdenes();

	/**
	 * Buscar ordenes operaciones por numero de cuenta.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the respuesta
	 */
	Respuesta<OrdenesView> buscarOrdenesPorCuenta(String numeroCuenta);

	/**
	 * Buscar ordenes operaciones.
	 *
	 * @param filtrosOrdenesView
	 *            the filtros ordenes view
	 * @return the respuesta
	 */
	Respuesta<OrdenesView> buscarOrdenes(FiltrosOrdenesView filtrosOrdenesView);

	/**
	 * Obtener filtros de busqueda.
	 *
	 * @return the respuesta
	 */
	Respuesta<FiltrosOrdenesView> obtenerFiltrosDeBusqueda();

	/**
	 * Grabar estadistica Busqueda.
	 *
	 */
	void grabarEstadisticaBusqueda();

}

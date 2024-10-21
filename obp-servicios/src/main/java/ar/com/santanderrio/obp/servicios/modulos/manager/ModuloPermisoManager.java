/**
 * 
 */
package ar.com.santanderrio.obp.servicios.modulos.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Interface ModuloPermisoManager.
 *
 * @author sergio.e.goldentair
 */
public interface ModuloPermisoManager {

	/**
	 * Limpia los permisos sobre los modulos cacheados.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> limpiarModuloPermisos();

	/**
	 * Obtener el modulo para acceder al estado y al mensaje si fueran
	 * necesarios.
	 *
	 * @param accionController
	 *            the accion controller
	 * @return moduloPermiso
	 */
	ModuloPermiso obtenerModuloPermiso(AccionController accionController);
}

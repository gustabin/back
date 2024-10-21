/**
 * 
 */
package ar.com.santanderrio.obp.servicios.modulos.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Interface ModuloPermisoBO.
 *
 * @author sergio.e.goldentair
 */
/**
 * @author sergio.e.goldentair
 *
 */
public interface ModuloPermisoBO {
    /**
     * Limpiar el cache de Modulos Permitos.
     * 
     * @return true o false
     */
    Respuesta<Boolean> limpiarModulosPermisos();

    /**
     * Obtener el permiso que tiene un modulo desde el enum de acciones.
     *
     * @param accionController
     *            the accion controller
     * @return ModuloExcluido
     */
    ModuloPermiso obtenerModuloPermisoPorAccion(AccionController accionController);

    /**
	 * Indicar si el permiso sobre el que se consulta puede ser visualizado.
	 *
	 * @param accionController
	 *            the accion controller
	 * @return true, if successful
	 */
    boolean tienePermisoMostrar(AccionController accionController);
}

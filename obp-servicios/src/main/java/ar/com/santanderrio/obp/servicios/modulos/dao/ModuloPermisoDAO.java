/**
 * 
 */
package ar.com.santanderrio.obp.servicios.modulos.dao;

import java.util.Map;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * Obtener el listado de modulos y sus correspondientes permisos de la base.
 * 
 * @author sergio.e.goldentair
 *
 */
public interface ModuloPermisoDAO {

	/**
	 * Si no fuera posible levantar los modulos se lanza una excepcion ya que se
	 * determino que no permita continuar. Es posible modificar el origen de los
	 * datos por si en algun ambiente es conveniente levantarlos desde la base o
	 * desde un txt, esto mediante un flag PERMISOS_POR_BASE que en true indica
	 * que los valores sean tomados desde la base.<br>
	 * Default value para PERMISOS_POR_BASE=true.
	 *
	 * @return mapa de modulos permisos.
	 * @throws DAOException
	 *             the DAO exception
	 */
	Map<String, ModuloPermiso> obtenerModulosPermisos() throws DAOException;

	/**
	 * Limpiar los modulos cacheados.
	 */
	void limpiarModulosPermisos();
}

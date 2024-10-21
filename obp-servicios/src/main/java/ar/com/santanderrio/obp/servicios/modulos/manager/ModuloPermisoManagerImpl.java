/**
 * 
 */
package ar.com.santanderrio.obp.servicios.modulos.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class ModuloPermisoManagerImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("moduloPermisoManager")
public class ModuloPermisoManagerImpl implements ModuloPermisoManager {
	/** BO para gestionar los permisos sobre los modulos. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.modulos.manager.ModuloPermisoManager#
	 * limpiarModuloPermisos()
	 */
	@Override
	public Respuesta<Boolean> limpiarModuloPermisos() {
		return moduloPermisoBO.limpiarModulosPermisos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.modulos.manager.ModuloPermisoManager#
	 * obtenerModuloPermiso(ar.com.santanderrio.obp.servicios.home.web.view.
	 * AccionController)
	 */
	@Override
	public ModuloPermiso obtenerModuloPermiso(AccionController accionController) {
		return moduloPermisoBO.obtenerModuloPermisoPorAccion(accionController);
	}

}

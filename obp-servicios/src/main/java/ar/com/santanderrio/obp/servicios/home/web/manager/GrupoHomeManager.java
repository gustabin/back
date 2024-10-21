/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;

/**
 * The Interface GrupoHomeManager.
 */
public interface GrupoHomeManager {

	/**
	 * obtiene el grupo asociado.
	 *
	 * @return the grupo caja home view
	 */
	GrupoCajaHomeView obtenerGrupoElementos();

	/**
	 * Validaciones para mostrar el grupo o no <br/>
	 * Relacionado a la existencia de datos en la sesion u casos de error.
	 *
	 * @return respuesta con estado OK <br/>
	 *         respuesta.respuesta true = aplica <br/>
	 *         respuesta.respuesta true = no aplica <br/>
	 *         <br/>
	 *         respuesta con estado ERROR = no aplica <br/>
	 *         respuesta.itemsRespuesta - informacion sobre porque no aplica
	 */
	Respuesta<Boolean> aplicaGrupo();

	/**
	 * Indicar el permiso que tendra la accion.
	 * 
	 * @return AccionController
	 */
	AccionController obtenerAccion();

}

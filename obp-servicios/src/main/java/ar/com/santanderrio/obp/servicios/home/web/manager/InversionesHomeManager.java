/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeInversionesView;

/**
 * Grupo asociado a inversiones.
 *
 * @author marcelo.ruiz
 */
public interface InversionesHomeManager extends GrupoHomeManager {

	/**
	 * Obtener tenencias de inverioens.
	 *
	 * @return the respuesta
	 */
	Respuesta<CajaHomeInversionesView> obtenerTenencias();

}

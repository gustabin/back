/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeSegurosView;

/**
 * Grupo asociado a Seguros.
 */
public interface SeguroHomeManager extends GrupoHomeManager {

	/**
	 * Obtener seguro.
	 *
	 * @return the respuesta
	 */
	Respuesta<CajaHomeSegurosView> obtenerSeguro();

}

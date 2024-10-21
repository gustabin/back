/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeChanceView;

/**
 * The Interface ChanceHomeManager.
 */
public interface ChanceHomeManager extends GrupoHomeManager {

	/**
	 * Obtener premiaciones periodo actual.
	 *
	 * @return the respuesta
	 */
	public Respuesta<CajaHomeChanceView> premiacionesPeriodoActual();
	
}
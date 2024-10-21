/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.legal.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * Gestion del legal.
 * 
 * @author sergio.e.goldentair
 *
 */
public interface LegalManager {

	/**
	 * Limpiar el cache de legales.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> vaciarLegales();
}

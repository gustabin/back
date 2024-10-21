/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenenciaConsolidada.comun.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;

/**
 * The Interface TenenciaConsolidadaManager.
 */
public interface TenenciaConsolidadaManager {
	
	/**
	 * Obtener rendimiento.
	 *
	 * @return the respuesta
	 */
	Respuesta<RendimientoView> obtenerRendimiento();

	/**
	 * Obtener rendimiento B priv.
	 *
	 * @return the respuesta
	 */
	Respuesta<RendimientoBPrivView> obtenerRendimientoBPriv();

}

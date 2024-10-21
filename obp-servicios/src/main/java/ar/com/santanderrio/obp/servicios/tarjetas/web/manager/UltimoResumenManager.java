/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.UltimoResumenView;

/**
 * The Interface UltimoResumenManager.
 */
public interface UltimoResumenManager {

	/**
	 * Obtener ultimo resumen.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<UltimoResumenView> obtenerUltimoResumen(String cuenta);

	/**
	 * Ver detalle ultimo resumen.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> verDetalleUltimoResumen();

	/**
	 * Descarga de resumen actual on demand.
	 *
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarResumenActualOnDemand(String nroCuenta);

}

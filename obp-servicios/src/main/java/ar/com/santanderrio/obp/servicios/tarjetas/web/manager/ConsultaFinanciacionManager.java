/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsultaFinanciacionView;

/**
 * The Interface TarjetaManager.
 *
 * 
 */
public interface ConsultaFinanciacionManager {

	/**
	 * Gets the obtenerConsultaFinanciacion.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the tarjetas.
	 */
	Respuesta<ConsultaFinanciacionView> obtenerConsultaFinanciacion(String numeroCuenta);
	
	/**
	 * Gets the descargarExcelCuotasPendientes.
	 *
	 * @return the reporteView.
	 */
	Respuesta<ReporteView> descargarExcelConsultaFinanciacion();

	

}

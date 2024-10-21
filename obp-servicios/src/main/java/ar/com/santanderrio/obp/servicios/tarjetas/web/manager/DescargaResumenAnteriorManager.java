/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface DescargaResumenAnteriorManager.
 */
public interface DescargaResumenAnteriorManager {

	/**
	 * Exportar on demand puntual resumen.
	 *
	 * @param nroCuenta
	 *            the nro cuenta
	 * @param list
	 *            the id
	 * @param string
	 *            the string
	 * @return the respuesta
	 */
	Respuesta<ReporteView> exportarOnDemandPuntualResumenConEstadistica(String nroCuenta, Integer list, String string);

	/**
	 * Exportar on demand multiple resumen.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param fechas
	 *            the fechas
	 * @param cantResumenes
	 *            the cant resumenes
	 * @param string
	 *            the string
	 * @return the respuesta
	 */
	Respuesta<ReporteView> exportarOnDemandMultipleResumen(String numeroCuenta, Integer fechas, int cantResumenes,
			String string);

}

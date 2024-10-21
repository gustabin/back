package ar.com.santanderrio.obp.servicios.inversiones.resumen.web.manager;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenesInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenesMensualesInversionesView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ReporteFinancieroView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasInView;

/**
 * The Interface ResumenInversionesManager.
 */
public interface ResumenInversionesManager {

	/**
	 * Obtener lista resumen.
	 *
	 * @param nroCuenta
	 *     the nro cuenta
	 * @param isBP
	 *     the is BP
	 * @return the respuesta
	 */
	Respuesta<ResumenesMensualesInversionesView> obtenerListaResumen(String nroCuenta, boolean isBP);

	/**
	 * Obtener resumenes PDF.
	 *
	 * @param view
	 *     the view
	 * @return the respuesta
	 */
	Respuesta<ReporteView> obtenerResumenesPDF(ResumenInversionesView view);

	/**
	 * Obtener resumen descarga multiple.
	 *
	 * @param ids
	 *     the ids
	 * @param nroCuenta
	 *     the nro cuenta
	 * @param isBP
	 *     the is BP
	 * @param cantidadADescargar
	 *     the cantidad A descargar
	 * @return the respuesta
	 */
	Respuesta<ReporteView> obtenerResumenDescargaMultiple(List<String> ids, String nroCuenta, boolean isBP, int cantidadADescargar);

	/**
	 * obtenerCuentas.
	 *
	 * @return the respuesta
	 */
	Respuesta<ResumenesInversionesView> obtenerCuentas();
	
	
	/**
	 * Obtener resumen financiero tenencias PDF.
	 *
	 * @param view the view
	 * @return the respuesta
	 */
	Respuesta<ReporteFinancieroView> obtenerResumenFinancieroTenenciasPDF(TenenciasInView view);

}

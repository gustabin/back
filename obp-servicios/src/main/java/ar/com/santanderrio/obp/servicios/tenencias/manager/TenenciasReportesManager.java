/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.manager;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tenencias.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasExcelView;

/**
 * The Interface TenenciasReportesManager.
 */
public interface TenenciasReportesManager {

	/**
	 * realiza la transformacion a ReporteViev reporte excel en Tenencias.
	 *
	 * @param respuestaView
	 *            the respuesta view
	 * @return respuesta con el objeto view response.
	 */
	Respuesta<ReporteView> exportarExcelTenencias(TenenciasExcelView respuestaView);

	/**
	 * realiza reporte excel en Tenencias.
	 *
	 * @param respuestaView
	 *            the respuesta view
	 * @return respuesta con el objeto view response.
	 */
	Respuesta<Reporte> getExcelTenencias(TenenciasExcelView respuestaView);

}

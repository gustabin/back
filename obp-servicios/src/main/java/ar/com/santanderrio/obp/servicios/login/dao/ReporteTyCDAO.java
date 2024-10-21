/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.dao;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;

/**
 * The Interface ReporteTyCDAO.
 */
public interface ReporteTyCDAO {

	/**
	 * Obtener reporte ty C mya.
	 *
	 * @param texto
	 *            the texto
	 * @return the reporte
	 */
	Reporte obtenerReporteTyCMya(String texto);
}

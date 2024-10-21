/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao;

import java.util.Map;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;

/**
 * The Interface ReporteUltimoResumenDAO.
 */
public interface ReporteUltimoResumenDAO {

	/**
	 * Exportar ultimo resumen.
	 *
	 * @param parameters
	 *            the parameters
	 * @return the reporte
	 */
	Reporte exportarUltimoResumen(Map<String, Object> parameters);

}

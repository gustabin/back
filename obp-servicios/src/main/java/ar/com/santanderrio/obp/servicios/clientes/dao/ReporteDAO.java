/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.dao;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosComprobante;

/**
 * The Interface ReporteDAO.
 */
public interface ReporteDAO {

	/**
	 * Descargar comprobante.
	 *
	 * @param datos
	 *            the datos
	 * @return the reporte
	 */
	Reporte descargarComprobante(DatosComprobante datos);

}

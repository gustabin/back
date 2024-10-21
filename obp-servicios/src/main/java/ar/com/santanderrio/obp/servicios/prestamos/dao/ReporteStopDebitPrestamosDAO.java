/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobanteStopDebitPrestamoView;

// TODO: Auto-generated Javadoc
/**
 * The Interface StopDebitPrestamosDAO.
 */
public interface ReporteStopDebitPrestamosDAO {

	/**
	 * Generar comprobante stop debit prestamos.
	 *
	 * @param nroComprobante the nro comprobante
	 * @param legal the legal
	 * @return the reporte
	 */
	Reporte generarComprobanteStopDebitPrestamos(ComprobanteStopDebitPrestamoView comprobanteStopDebitPrestamoView) throws DAOException;
	
}

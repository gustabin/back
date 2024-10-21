/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobanteStopDebitPrestamoView;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReportePrestamoDAO.
 */
public interface ReportePrestamoDAO {

	/**
	 * Obtener reporte comprobante PDF.
	 *
	 * @param detalleView
	 *            the detalle view
	 * @return el comprobante en formato PDF
	 * @throws DAOException
	 *             the DAO exception
	 */
	Reporte obtenerReportePrestamoPDF(ReportComprobanteView detalleView) throws DAOException;
	
	/**
	 * Obtener reporte comprobante PDF.
	 *
	 * @param detalleView
	 *            the detalle view
	 * @param isUva
	 * 			  the is uva
	 * @return el comprobante en formato PDF
	 * @throws DAOException
	 *             the DAO exception
	 */
	Reporte obtenerReportePrestamoPDF(ReportComprobanteView detalleView, boolean isUva) throws DAOException;
	
}

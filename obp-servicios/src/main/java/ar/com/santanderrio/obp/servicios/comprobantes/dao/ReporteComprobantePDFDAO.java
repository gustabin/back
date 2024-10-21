/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

/**
 * The Interface ReporteComprobantePDFDAO.
 */
public interface ReporteComprobantePDFDAO {

	/**
	 * Obtener reporte comprobante PDF.
	 *
	 * @param detalleView
	 *            the detalle view
	 * @return el comprobante en formato PDF
	 * @throws DAOException
	 *             the DAO exception
	 */
	Reporte obtenerReporteComprobantePDF(DetalleComprobanteView detalleView) throws DAOException;

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.pagos.bo.ComprobantePrestamoReporte;

/**
 * The Interface ReporteComprobanteDAO.
 */
public interface ReporteComprobanteDAO {

	/**
	 * Builds the report comprobante.
	 *
	 * @param comprobantePrestamoReporte
	 *            the comprobante prestamo reporte
	 * @return the byte[]
	 * @throws DAOException
	 *             the DAO exception
	 */
	byte[] buildReportComprobante(ComprobantePrestamoReporte comprobantePrestamoReporte) throws DAOException;

	/**
	 * Builds the report comprobante detalle.
	 *
	 * @param comprobantePrestamoReporte
	 *            the comprobante prestamo reporte
	 * @return the byte[]
	 * @throws DAOException
	 *             the DAO exception
	 */
	byte[] buildReportComprobanteDetalle(ComprobantePrestamoReporte comprobantePrestamoReporte) throws DAOException;

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView;

/**
 * The Interface ReporteDescuentoChequesPDFDAO.
 */
public interface ReporteDescuentoChequesPDFDAO {

	/**
	 * Obtener operacion PDF.
	 *
	 * @param detalleIn
	 *            the detalle in
	 * @return the reporte
	 * @throws DAOException
	 *             the DAO exception
	 */
	Reporte obtenerOperacionPDF(DetalleOperacionesPrecargadasView detalleIn) throws DAOException;

	/**
	 * Obtener operacion PDF.
	 *
	 * @param chequesView
	 *            the cheques view
	 * @return the reporte
	 * @throws DAOException
	 *             the DAO exception
	 */
	Reporte obtenerOperacionPDF(AltaChequesViewOut chequesView) throws DAOException;

}

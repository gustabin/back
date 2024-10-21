/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.manager;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.todopago.web.view.AdhesionRespuestaView;
import ar.com.santanderrio.obp.servicios.todopago.web.view.TodoPagoView;

/**
 * The Interface TodoPagoManager.
 */
public interface TodoPagoManager {

	/**
	 * Adhesion TodoPago.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<TodoPagoView> adhesionTodoPago(TodoPagoView view) throws DAOException;

	/**
	 * Confirmar adhesion.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<AdhesionRespuestaView> confirmarAdhesion(TodoPagoView viewRequest) throws DAOException;

	/**
	 * Descarga de comprobante de adhesion a TodoPago.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargaComprobanteAdhesion();
}

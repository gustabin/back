/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chequera.entity.PedidoChequeraInEntity;
import ar.com.santanderrio.obp.servicios.chequera.entity.PedidoChequeraOutEntity;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionViewResponse;

/**
 * The Interface PedidoChequeraDAO.
 */
public interface PedidoChequeraDAO {

	/**
	 * Confirmar pedido chequera.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<PedidoChequeraOutEntity> confirmarPedidoChequera(PedidoChequeraInEntity request) throws DAOException;

	/**
	 * Generar comprobante chequera.
	 *
	 * @param chequeraConfirmacionView
	 *            the chequera confirmacion view
	 * @return the reporte
	 */
	Reporte generarComprobanteChequera(ChequeraConfirmacionViewResponse chequeraConfirmacionView);

}

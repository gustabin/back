/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosPagoTC;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;

/**
 * The Interface PagoTarjetaCreditoDAO.
 */
public interface PagoTarjetaCreditoDAO {

	/**
	 * Realiza el paago de una tarjeta de credito para el dia de la fecha.
	 *
	 * @param cliente
	 *            el cliente que desea realizar el pago
	 * @param pago
	 *            la informacion para llamar al servicio de iatx
	 * @return el comprobante del pago realizado
	 * @throws DAOException
	 *             the DAO exception
	 */
	String pagar(Cliente cliente, DatosPagoTC pago) throws DAOException;

	/**
	 * Programa el paago de una tarjeta de credito.
	 *
	 * @param cliente
	 *            el cliente que desea programar el pago
	 * @param datosPagoTC
	 *            the datos pago TC
	 * @return el comprobante del pago programado
	 * @throws DAOException
	 *             the DAO exception
	 */
	String programarPago(Cliente cliente, DatosPagoTC datosPagoTC) throws DAOException;

	/**
	 * Ejecutar baja pago programado de tarjeta credito.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<ResultadoTransaccion> ejecutarBajaPagoProgramadoDeTarjetaCredito(PagoPendiente pagoPendiente,
			Cliente cliente) throws DAOException;
}

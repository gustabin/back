/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosPagoAutomaticoEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;

/**
 * The Interface StopDebitDAO.
 */
public interface StopDebitDAO {

	/**
	 * Ejecutar stop debit pago mis cuentas.
	 *
	 * @param datosPagoAutomatico
	 *            the datos pago automatico
	 * @param cliente
	 *            the cliente
	 * @return the resultado transaccion
	 * @throws DAOException
	 *             the DAO exception
	 */
	ResultadoTransaccion ejecutarStopDebitPagoMisCuentas(DatosPagoAutomaticoEntity datosPagoAutomatico, Cliente cliente)
			throws DAOException;

	/**
	 * Ejecutar stop debito en cuenta.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @return the resultado transaccion
	 * @throws DAOException
	 *             the DAO exception
	 */
	ResultadoTransaccion ejecutarStopDebitoEnCuenta(PagoPendiente pagoPendiente, Cliente cliente) throws DAOException;

}

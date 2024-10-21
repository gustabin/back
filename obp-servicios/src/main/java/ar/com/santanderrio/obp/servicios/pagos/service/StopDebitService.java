/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.service;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.Service;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;

/**
 * The Interface StopDebitService.
 */
public interface StopDebitService extends Service {

	/**
	 * Ejecutar stop debit pago mis cuentas.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<ResultadoTransaccion> ejecutarStopDebitPagoMisCuentas(PagoPendiente pagoPendiente, Cliente cliente)
			throws ServiceException;

	/**
	 * Ejecutar stop debito en cuenta.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<ResultadoTransaccion> ejecutarStopDebitoEnCuenta(PagoPendiente pagoPendiente, Cliente cliente)
			throws ServiceException;

}

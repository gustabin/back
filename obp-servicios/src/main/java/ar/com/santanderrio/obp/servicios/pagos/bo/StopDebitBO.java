/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;

/**
 * The Interface StopDebitBO.
 *
 * @author B039636
 */
public interface StopDebitBO extends BusinessObject {

	/**
	 * Ejecutar stop debit pago mis cuentas.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<ResultadoTransaccion> ejecutarStopDebitPagoMisCuentas(PagoPendiente pagoPendiente, Cliente cliente)
			throws BusinessException;

	/**
	 * Ejecutar stop debito en cuenta.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<ResultadoTransaccion> ejecutarStopDebitoEnCuenta(PagoPendiente pagoPendiente, Cliente cliente)
			throws BusinessException;

}

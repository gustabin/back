/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.pagos.bo.StopDebitBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.service.PagosService;
import ar.com.santanderrio.obp.servicios.pagos.service.StopDebitService;

/**
 * The Class StopDebitServiceImpl.
 */
@Component
public class StopDebitServiceImpl implements StopDebitService {

	/** The Constant STOP_DEBIT_SERVICE_EJECTURAR_STOP_DEBIT. */
	private static final Object STOP_DEBIT_SERVICE_EJECTURAR_STOP_DEBIT = "StopDebitService";

	/** The stop debit BO. */
	@Autowired
	private StopDebitBO stopDebitBO;

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagosService.class);

	/** The Constant ERROR_LOG. */
	private static final String ERROR_LOG = "Ha ocurrido un error en servicio: ";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.StopDebitService#
	 * ejecutarStopDebitPagoMisCuentas(ar.com.santanderrio.obp.servicios.pagos.
	 * entities.PagoPendiente,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> ejecutarStopDebitPagoMisCuentas(PagoPendiente pagoPendiente, Cliente cliente)
			throws ServiceException {
		try {
			return stopDebitBO.ejecutarStopDebitPagoMisCuentas(pagoPendiente, cliente);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_LOG, STOP_DEBIT_SERVICE_EJECTURAR_STOP_DEBIT, e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.StopDebitService#
	 * ejecutarStopDebitoEnCuenta(ar.com.santanderrio.obp.servicios.pagos.
	 * entities.PagoPendiente,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> ejecutarStopDebitoEnCuenta(PagoPendiente pagoPendiente, Cliente cliente)
			throws ServiceException {
		try {
			return stopDebitBO.ejecutarStopDebitoEnCuenta(pagoPendiente, cliente);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_LOG, STOP_DEBIT_SERVICE_EJECTURAR_STOP_DEBIT, e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

}
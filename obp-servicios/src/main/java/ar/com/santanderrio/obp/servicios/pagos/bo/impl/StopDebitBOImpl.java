/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.pagos.bo.StopDebitBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.StopDebitDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;

/**
 * The Class StopDebitBOImpl.
 *
 * @author B039636
 */
@Component
public class StopDebitBOImpl implements StopDebitBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(StopDebitBOImpl.class);

	/** The stop debit DAO. */
	@Autowired
	private StopDebitDAO stopDebitDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.bo.StopDebitBO#
	 * ejecutarStopDebitPagoMisCuentas(ar.com.santanderrio.obp.servicios.pagos.
	 * entities.PagoPendiente,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> ejecutarStopDebitPagoMisCuentas(PagoPendiente pagoPendiente, Cliente cliente)
			throws BusinessException {
		Respuesta<ResultadoTransaccion> respuestaStopDebit = new Respuesta<ResultadoTransaccion>();
		try {
			ResultadoTransaccion resultado = stopDebitDAO
					.ejecutarStopDebitPagoMisCuentas(pagoPendiente.getDatosPagoAutomatico(), cliente);
			if (EstadoRespuesta.OK.equals(resultado.getEstadoRespuesta())) {
				respuestaStopDebit.setRespuesta(resultado);
				respuestaStopDebit.setEstadoRespuesta(resultado.getEstadoRespuesta());
				respuestaStopDebit.setEstadoRespuesta(EstadoRespuesta.OK);
				respuestaStopDebit.setRespuestaVacia(false);
			} else {
				respuestaStopDebit.setRespuesta(null);
				respuestaStopDebit.setEstadoRespuesta(EstadoRespuesta.WARNING);
				respuestaStopDebit.setRespuestaVacia(true);
			}
			return respuestaStopDebit;
		} catch (DAOException e) {
			String mensaje = e.getMessage();
			LOGGER.error(mensaje, e);
			throw new BusinessException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.bo.StopDebitBO#
	 * ejecutarStopDebitoEnCuenta(ar.com.santanderrio.obp.servicios.pagos.
	 * entities.PagoPendiente,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> ejecutarStopDebitoEnCuenta(PagoPendiente pagoPendiente, Cliente cliente)
			throws BusinessException {
		Respuesta<ResultadoTransaccion> respuestaStopDebit = new Respuesta<ResultadoTransaccion>();
		try {
			ResultadoTransaccion resultado = stopDebitDAO.ejecutarStopDebitoEnCuenta(pagoPendiente, cliente);
			if (EstadoRespuesta.OK.equals(resultado.getEstadoRespuesta())) {
				respuestaStopDebit.setRespuesta(resultado);
				respuestaStopDebit.setEstadoRespuesta(EstadoRespuesta.OK);
				respuestaStopDebit.setRespuestaVacia(false);
			} else {
				respuestaStopDebit.setRespuesta(null);
				respuestaStopDebit.setEstadoRespuesta(EstadoRespuesta.WARNING);
				respuestaStopDebit.setRespuestaVacia(true);
			}
			return respuestaStopDebit;
		} catch (DAOException e) {
			String mensaje = e.getMessage();
			LOGGER.error(mensaje, e);
			throw new BusinessException(e);
		}
	}

}

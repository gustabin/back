/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMenorAlMinimoException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteTransferenciaFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteTransferenciaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TransferenciaOutEntity;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TransferenciaBloqueadaException;

/**
 * The Class TransferenciaFondoDAOImpl.
 *
 * @author
 */
@Component
public class TransferenciaFondoDAOImpl extends IatxBaseDAO implements TransferenciaFondoDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaFondoDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant FINALIZAR_SUSCRIPCION_FUERA_HORARIO_CODIGO_RETORNO. */
	private static final int FINALIZAR_SUSCRIPCION_FUERA_HORARIO_CODIGO_RETORNO = 99002400;

	/** The Constant CODIGO_ERROR_CUENTA_SIN_OPERAR. */
	private static final int CODIGO_ERROR_CUENTA_SIN_OPERAR = 10000117;

	/** The Constant FUERA_HORARIO_CODIGO_RETORNO. */
	private static final int FUERA_HORARIO_CODIGO_RETORNO = 10000024;

	/** The Constant SUPERA_SALDO_DISPONIBLE. */
	private static final int SUPERA_SALDO_DISPONIBLE = 10000174;

	/** The Constant ID_SISTEMA. */
	private static final String ID_SISTEMA = "FCI";

	/** The Constant CODIGO_ERROR_IMPORTE_MENOR_AL_MINIMO. */
	private static final int CODIGO_ERROR_IMPORTE_MENOR_AL_MINIMO = 10000100;

	/** The Constant SUSCRIPCIONES_BLOQUEADAS. */
	private static final int SUSCRIPCIONES_BLOQUEADAS = 10000087;

	private static final int FUERA_DE_HORARIO_CCO = 18888801;

	private static final String ID_SISTEMA_CCO = "CCO";

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/**
	 * Consultar iatx para Transferencia.
	 *
	 * @param transferenciaIn the transferencia in
	 * @return entidad con la informacion de retorno de iatx de Transferencia
	 * @throws DAOException the DAO exception
	 */
	public TransferenciaOutEntity transferir(TransferenciaInEntity transferenciaIn) throws DAOException {
		String servicio = "CNSTRFFCI_";
		String version = "120";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		try {
			IatxRequestData iatxRequestData = generateRequestData(transferenciaIn);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				return processTrama(iatxResponse.getTrama(), TransferenciaOutEntity.class);
			} else {
				manejarErroresTransferencia(errorCode, iatxResponse.getErrorSystem());
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return new TransferenciaOutEntity();
	}

	/**
	 * Genera el objeto IatxRequestData para llamar al servicio iatx.
	 *
	 * @param transferenciaInEntity the transferencia in entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestData(TransferenciaInEntity transferenciaInEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(transferenciaInEntity.getCliente());
		iatxRequestData.addBodyValue(transferenciaInEntity.getTerminalSafe());
		iatxRequestData.addBodyValue(transferenciaInEntity.getCodigoFondo());
		iatxRequestData.addBodyValue(transferenciaInEntity.getCodigoCliente());
		iatxRequestData.addBodyValue(transferenciaInEntity.getCodigoAgente());
		iatxRequestData.addBodyValue(transferenciaInEntity.getCodigoCanal());
		iatxRequestData.addBodyValue(transferenciaInEntity.getImporteNeto());
		iatxRequestData.addBodyValue(transferenciaInEntity.getPorcentComision());
		iatxRequestData.addBodyValue(transferenciaInEntity.getImprSolicitud());
		iatxRequestData.addBodyValue(transferenciaInEntity.getCodigoCustodiaActual());
		iatxRequestData.addBodyValue(transferenciaInEntity.getCodigoCustodiaAnterior());
		iatxRequestData.addBodyValue(transferenciaInEntity.getCodigoFondoDest());
		iatxRequestData.addBodyValue(transferenciaInEntity.getPorcentComisionD());

		return iatxRequestData;
	}

	/**
	 * Logica errores comprobante transferencia fondo.
	 *
	 * @param iatxResponse the iatx response
	 * @param errorCode    the error code
	 * @throws DAOException the DAO exception
	 */
	public void logicaErroresComprobanteTransferenciaFondo(IatxResponse iatxResponse, int errorCode)
			throws DAOException {
		if (FINALIZAR_SUSCRIPCION_FUERA_HORARIO_CODIGO_RETORNO == errorCode) {
			throw new FueraDeHorarioException();
		}
		if (CODIGO_ERROR_CUENTA_SIN_OPERAR == errorCode) {
			throw new CuentaSinOperarException();
		}

		if (FUERA_DE_HORARIO_CCO == errorCode && ID_SISTEMA_CCO.equalsIgnoreCase(iatxResponse.getErrorSystem())) {
			throw new FueraDeHorarioException();
		}
		LOGGER.debug("Codigo de error no esperado de iatx en servicio TRFFCI_. ");
		throw new DAOException();
	}

	/**
	 * Manejar errores transferencia.
	 *
	 * @param errorCode   the error code
	 * @param errorSystem the error system
	 * @throws DAOException the DAO exception
	 */
	private void manejarErroresTransferencia(int errorCode, String errorSystem) throws DAOException {
		if (ID_SISTEMA.equalsIgnoreCase(errorSystem)) {
			if (FUERA_HORARIO_CODIGO_RETORNO == errorCode) {
				throw new FueraDeHorarioException();
			}
			if (CODIGO_ERROR_IMPORTE_MENOR_AL_MINIMO == errorCode) {
				throw new ImporteMenorAlMinimoException();
			}
			if (SUSCRIPCIONES_BLOQUEADAS == errorCode) {
				throw new TransferenciaBloqueadaException();
			}
			if (SUPERA_SALDO_DISPONIBLE == errorCode) {
				throw new SaldoInsuficienteException();
			}


		}
		if (FUERA_DE_HORARIO_CCO == errorCode && ID_SISTEMA_CCO.equalsIgnoreCase(errorSystem)) {
			throw new FueraDeHorarioException();
		}
		LOGGER.debug("Codigo de error no esperado de iatx en servicio CNSTRFFCI. ");
		throw new DAOException();
	}

	/**
	 * ObtenerComprobante iatx para Transferencia.
	 *
	 * @param comprobanteTransferenciaFondoInEntity the comprobante transferencia
	 *                                              fondo in entity
	 * @return the comprobante transferencia fondo out entity
	 * @throws DAOException the DAO exception
	 */

	@Override
	public ComprobanteTransferenciaFondoOutEntity obtenerComprobante(
			ComprobanteTransferenciaFondoInEntity comprobanteTransferenciaFondoInEntity) throws DAOException {
		String servicio = "TRFFCI____";
		String version = "130";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ComprobanteTransferenciaFondoOutEntity comprobanteTransferenciaFondoOutEntity = new ComprobanteTransferenciaFondoOutEntity();
		try {
			IatxRequestData iatxRequestData = generateComprobanteRequestData(comprobanteTransferenciaFondoInEntity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				comprobanteTransferenciaFondoOutEntity = processTrama(iatxResponse.getTrama(),
						ComprobanteTransferenciaFondoOutEntity.class);
			} else {
				logicaErroresComprobanteTransferenciaFondo(iatxResponse, errorCode);
			}
		} catch (IatxException e) {
			if (e.getMessage().equals(TIMEOUT_EXCEPTION)) {
				LOGGER.error(e.getMessage(), e);
				throw new TimeOutException(e.getMessage());
			} else {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e);
			}
		}
		return comprobanteTransferenciaFondoOutEntity;
	}

	/**
	 * Generate comprobante request data.
	 *
	 * @param comprobanteTransferenciaFondoInEntity the comprobante transferencia
	 *                                              fondo in entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateComprobanteRequestData(
			ComprobanteTransferenciaFondoInEntity comprobanteTransferenciaFondoInEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(comprobanteTransferenciaFondoInEntity.getCliente());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getTerminalSafe());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getCodigoFondo());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getCodigoCliente());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getCodigoAgente());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getCodigoCanal());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getImporteNeto());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getPorcentajeComis());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getImpreSolicitud());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getCodigoCustodiaActual());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getCodigoCustodiaAnterior());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getCodigoFondoDest());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getPorcentajeComisD());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getTipoCuenta());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getSucursalCuenta());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getNumeroCuenta());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getNumeroCertificadoReservar());
		iatxRequestData.addBodyValue(comprobanteTransferenciaFondoInEntity.getCantCuotasPartes());
		return iatxRequestData;
	}

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.perfil.dao.impl.CambioDomicilioDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoOpenCreditDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditOutEntity;

/**
 * OLYMPUS PrestamoOpenCreditDAOImpl.
 *
 * @author Silvina_Luque
 */
@Component
public class PrestamoOpenCreditDAOImpl extends IatxBaseDAO implements PrestamoOpenCreditDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CambioDomicilioDAOImpl.class);

	/** The SERVICIO_CNSPERACTI. */
	private static final String SERVICIO_CNSPERACTI = "CNSPERACTI";

	/** The VERSION_CNSPERACTI. */
	private static final String VERSION_CNSPERACTI = "120";

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant INDEX_CAPITAL_SOLICITADO. */
	private static final int INDEX_CAPITAL_SOLICITADO = 5;

	/** The Constant INDEX_CAPITAL_DISPUESTO. */
	private static final int INDEX_CAPITAL_DISPUESTO = 221;

	/** The Constant ENTERO_DOCE. */
	private static final int ENTERO_DOCE = 12;

	/** The Constant INDEX_FECHAS_INICIO. */
	private static final int INDEX_FECHAS_INICIO = 14;

	/** The Constant SERVICIO_CNSPMOHIST. */
	private static final String SERVICIO_CNSPMOHIST = "CNSPMOHIST";

	/** The Constant VERSION_120. */
	private static final String VERSION_120 = "120";

	/** The Constant CONSULTA_OK. */
	private static final String CONSULTA_OK = "La consulta al DAO se realizo exitosamente: {}.";

	/** The Constant MENSAJE_ERROR_SERVICIO. */
	private static final String MENSAJE_ERROR_SERVICIO = "Error del servicio iatx con codigo extendido de error.";

	/**
	 * Consultar prestamo open credit.
	 *
	 * @param consultaPrestamoOpenCreditInEntity
	 *            the consulta prestamo open credit in entity
	 * @return the consulta prestamo open credit out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public ConsultaPrestamoOpenCreditOutEntity consultarPrestamo(
			ConsultaPrestamoOpenCreditInEntity consultaPrestamoOpenCreditInEntity) throws DAOException {
		LOGGER.info("PrestamoOpenCreditDAOImpl _ Iniciando metodo consultarPrestamo open credit");
		IatxRequest iatxRequest = new IatxRequest(SERVICIO_CNSPERACTI, VERSION_CNSPERACTI);
		ConsultaPrestamoOpenCreditOutEntity consultaPrestamoOpenCreditOutEntity = new ConsultaPrestamoOpenCreditOutEntity();

		try {
			LOGGER.debug("PrestamoOpenCreditDAOImpl _ Iniciando llamada iatx CNSPERACTI");
			IatxRequestData iatxRequestData = this.generateRequestDataCns(consultaPrestamoOpenCreditInEntity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			LOGGER.debug("PrestamoOpenCreditDAOImpl _ Finalizando llamada iatx CNSPERACTI");
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				consultaPrestamoOpenCreditOutEntity.setCapitalSolicitado(ISBANStringUtils
						.stringToBigDecimal(iatxResponse.getData(INDEX_CAPITAL_SOLICITADO), 13, 4, false));
				consultaPrestamoOpenCreditOutEntity.setCapitalDispuesto(ISBANStringUtils
						.stringToBigDecimal(iatxResponse.getData(INDEX_CAPITAL_DISPUESTO), 13, 4, false));
				consultaPrestamoOpenCreditOutEntity.setFechasInicio(iatxResponse.getData(INDEX_FECHAS_INICIO));

			} else {
				LOGGER.debug("Error iatx CNSPERACTI, codigo retorno distinto de ceros");
				throw new DAOException(MENSAJE_ERROR_SERVICIO);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		LOGGER.debug("PrestamoOpenCreditDAOImpl _ Finalizando llamada consultarPrestamo");
		return consultaPrestamoOpenCreditOutEntity;
	}

	/**
	 * Obtiene las cuotas pagas de un prestamo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inEntity
	 *            the in entity
	 * @return the consulta cuota paga out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public ConsultaCuotaPagaOutEntity obtenerCuotasPagasPrestamo(Cliente cliente, ConsultaCuotaPagaInEntity inEntity)
			throws DAOException {
		LOGGER.info("PrestamoOpenCreditDAOImpl obtenerCuotasPagasPrestamo ");
		try {
			ConsultaCuotaPagaOutEntity outEntity = ejecutarConsultaServicioCNSPMOHIST(cliente, inEntity);
			LOGGER.info(CONSULTA_OK, outEntity);
			return outEntity;
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Ejecuta la consulta al servicio CNSPMOHIST.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inEntity
	 *            the in entity
	 * @return the consulta cuota paga out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConsultaCuotaPagaOutEntity ejecutarConsultaServicioCNSPMOHIST(Cliente cliente,
			ConsultaCuotaPagaInEntity inEntity) throws DAOException {
		LOGGER.info("PrestamoOpenCreditDAOImpl ejecutarConsultaServicioCNSPMOHIST");
		IatxRequest iatxRequest = new IatxRequest(SERVICIO_CNSPMOHIST, VERSION_120);
		ConsultaCuotaPagaOutEntity cuotaPagaOut = new ConsultaCuotaPagaOutEntity();
		try {
			iatxRequest.setData(generarRequestDataCNSPMOHIST(cliente, inEntity));
			IatxResponse iatxResp = iatxComm.exec(iatxRequest);
			int errorCode = iatxResp.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				cuotaPagaOut = processTrama(iatxResp.getTrama(), ConsultaCuotaPagaOutEntity.class);
			} else {
				throw new DAOException(MENSAJE_ERROR_SERVICIO, String.valueOf(errorCode));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return cuotaPagaOut;

	}

	/**
	 * Genera el request data para el servicio CNSPMOHIST.
	 *
	 * @param cliente
	 *            the cliente
	 * @param consultaEntity
	 *            the consulta entity
	 * @return the iatx request data
	 */
	private IatxRequestData generarRequestDataCNSPMOHIST(Cliente cliente, ConsultaCuotaPagaInEntity consultaEntity) {
		IatxRequestData iatxData = new IatxRequestData(cliente);
		iatxData.addBodyValue(consultaEntity.getNumRegistros());
		iatxData.addBodyValue(consultaEntity.getOficina());
		iatxData.addBodyValue(consultaEntity.getCuenta());
		iatxData.addBodyValue(consultaEntity.getCodEvento());
		iatxData.addBodyValue(consultaEntity.getFecInicio());
		iatxData.addBodyValue(consultaEntity.getFecFin());
		iatxData.addBodyValue(consultaEntity.getTipomov());
		iatxData.addBodyValue(consultaEntity.getTimestamp());
		iatxData.addBodyValue(consultaEntity.getCodconli());
		iatxData.addBodyValue(consultaEntity.getNumSecuencia());
		return iatxData;
	}

	/**
	 * generateRequestDataCns.
	 *
	 * @param consultaPrestamoOpenCreditInEntity
	 *            the consulta prestamo open credit in entity
	 * @return IatxRequestData
	 */
	private IatxRequestData generateRequestDataCns(
			ConsultaPrestamoOpenCreditInEntity consultaPrestamoOpenCreditInEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(consultaPrestamoOpenCreditInEntity.getCliente());
		iatxRequestData.addBodyValue(consultaPrestamoOpenCreditInEntity.getSucursal());
		iatxRequestData
				.addBodyValue(StringUtils.right(consultaPrestamoOpenCreditInEntity.getNumeroPrestamo(), ENTERO_DOCE));
		iatxRequestData.addBodyValue(consultaPrestamoOpenCreditInEntity.getFechaDelDia());
		return iatxRequestData;
	}

}

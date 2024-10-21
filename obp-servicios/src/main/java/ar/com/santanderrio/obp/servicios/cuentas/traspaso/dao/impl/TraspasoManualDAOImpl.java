/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaTraspasoManualInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaTraspasoManualOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.TraspasoManualDAO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.exceptions.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class TraspasoManualDAOImpl.
 */
@Component
public class TraspasoManualDAOImpl extends IatxBaseDAO implements TraspasoManualDAO {

	/** The Constant VERSION_TRFCTACRU. */
	private static final String VERSION_TRFCTACRU = "100";

	/** The Constant SERVICIO_TRFCTACRU. */
	private static final String SERVICIO_TRFCTACRU = "TRFCTACRU_";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TraspasoManualDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant ERROR_SALDO_INSUFICIENTE. */
	private static final int ERROR_SALDO_INSUFICIENTE = 10000515;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/**
	 * Generate request data TRFCTACRU.
	 *
	 * @param consultaTraspasoManualInEntity
	 *            the consulta traspaso manual in entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataTRFCTACRU(
			ConsultaTraspasoManualInEntity consultaTraspasoManualInEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(consultaTraspasoManualInEntity.getCliente());

		iatxRequestData.addBodyValue(consultaTraspasoManualInEntity.getSucursalCuenta());
		iatxRequestData.addBodyValue(consultaTraspasoManualInEntity.getNroCuenta());
		iatxRequestData.addBodyValue(consultaTraspasoManualInEntity.getDivisa());
		iatxRequestData.addBodyValue(consultaTraspasoManualInEntity.getIndDebitoCreditoCA());
		iatxRequestData.addBodyValue(consultaTraspasoManualInEntity.getIndDebitoCreditoCC());
		iatxRequestData.addBodyValue(consultaTraspasoManualInEntity.getImporteDebitoCredito());
		iatxRequestData.addBodyValue(consultaTraspasoManualInEntity.getNio());
		iatxRequestData.addBodyValue(consultaTraspasoManualInEntity.getCentroOrigen());

		return iatxRequestData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.TraspasoManualDAO#
	 * ejecutarTraspasoManual(ar.com.santanderrio.obp.servicios.cuentas.entities
	 * .ConsultaTraspasoManualInEntity)
	 */
	@Override
	public ConsultaTraspasoManualOutEntity ejecutarTraspasoManual(
			ConsultaTraspasoManualInEntity consultaTraspasoManualInEntity) throws DAOException {

		IatxRequest iatxRequest = new IatxRequest(SERVICIO_TRFCTACRU, VERSION_TRFCTACRU);

		ConsultaTraspasoManualOutEntity consultaTraspasoManualOutEntity = new ConsultaTraspasoManualOutEntity();

		try {
			IatxRequestData iatxRequestData = generateRequestDataTRFCTACRU(consultaTraspasoManualInEntity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (ERROR_SALDO_INSUFICIENTE == errorCode) {
				LOGGER.error("Error en el servicio, " + iatxResponse.getErrorMessage());
				throw new SaldoInsuficienteException(iatxResponse.getErrorMessage());

			}
			if (OK_CODIGO_RETORNO == errorCode) {
				consultaTraspasoManualOutEntity = processTrama(iatxResponse.getTrama(),
						ConsultaTraspasoManualOutEntity.class);
			} else {

				LOGGER.error("Error en el servicio, " + iatxResponse.getErrorMessage());
			}
		} catch (IatxException e) {
			if (TIMEOUT_EXCEPTION.equals(e.getMessage())) {
				LOGGER.error(e.getMessage(), e);
			} else {
				LOGGER.error(e.getMessage(), e);
			}
			throw new DAOException(e.getMessage());
		}
		return consultaTraspasoManualOutEntity;
	}
}

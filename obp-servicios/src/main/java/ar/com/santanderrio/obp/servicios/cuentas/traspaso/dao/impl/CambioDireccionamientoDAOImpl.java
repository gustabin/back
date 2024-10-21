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
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidationEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.CambioDireccionamientoDAO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaCambioDireccionamientoInEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class CambioDireccionamientoDAOImpl.
 */
@Component
public class CambioDireccionamientoDAOImpl extends IatxBaseDAO implements CambioDireccionamientoDAO {

	/** The Constant VERSION_CMBCTAINDI. */
	private static final String VERSION_CMBCTAINDI = "100";

	/** The Constant SERVICIO_CMBCTAINDI. */
	private static final String SERVICIO_CMBCTAINDI = "CMBCTAINDI";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CambioDireccionamientoDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.
	 * CambioDireccionamientoDAO#cambiarDireccionamiento(ar.com.santanderrio.obp
	 * .servicios.cuentas.traspaso.entity.
	 * ConsultaCambioDireccionamientoInEntity)
	 */
	@Override
	public void cambiarDireccionamiento(ConsultaCambioDireccionamientoInEntity consultaCambioDireccionamientoInEntity)
			throws DAOException {

		if (!ValidationEntity.validate(consultaCambioDireccionamientoInEntity)) {
			throw new DAOException("Error al validar parámetros de entrada");
		}
		try {
			ejecucionConsulta(consultaCambioDireccionamientoInEntity);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Ejecucion consulta.
	 *
	 * @param consultaCambioDireccionamientoInEntity
	 *            the consulta cambio direccionamiento in entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void ejecucionConsulta(ConsultaCambioDireccionamientoInEntity consultaCambioDireccionamientoInEntity)
			throws DAOException {
		String servicio = SERVICIO_CMBCTAINDI;
		String version = VERSION_CMBCTAINDI;
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		try {
			IatxRequestData iatxRequestData = generateRequestDataCMBCTAINDI(consultaCambioDireccionamientoInEntity);

			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO != errorCode) {
				throw new IatxException("IATX falló con código de error: " + errorCode);
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Generate request data CMBCTAINDI.
	 *
	 * @param consultaCambioDireccionamientoInEntity
	 *            the consulta cambio direccionamiento in entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCMBCTAINDI(
			ConsultaCambioDireccionamientoInEntity consultaCambioDireccionamientoInEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(consultaCambioDireccionamientoInEntity.getCliente());

		iatxRequestData.addBodyValue(consultaCambioDireccionamientoInEntity.getSucursalPaquete());
		iatxRequestData.addBodyValue(consultaCambioDireccionamientoInEntity.getNumeroPaquete());
		iatxRequestData.addBodyValue(consultaCambioDireccionamientoInEntity.getIndicadorDireccionaFondos());

		return iatxRequestData;
	}
}

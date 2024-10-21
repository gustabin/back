/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.comun.dao.ConsultaInhabilitadosDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosOutEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class ConsultaInhabilitadosDAOImpl.
 *
 * @author alejandro_leal
 */
@Component("consultaInhabilitadosDAO")
public class ConsultaInhabilitadosDAOImpl extends IatxBaseDAO implements ConsultaInhabilitadosDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaInhabilitadosDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;
	
	/** The Constant COD_ERROR_101. */
	private static final int COD_ERROR_101 = 10000101;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.monedero.dao.ConsultaInhabilitadosDAO#
	 * consultaInhabilitados(ar.com.santanderrio.obp.servicios.monedero.entities
	 * .ConsultaInhabilitadosInEntity)
	 */
	@Override
	public ConsultaInhabilitadosOutEntity consultaInhabilitados(ConsultaInhabilitadosInEntity entity)
			throws DAOException {
		String servicio = "CNSINHMTIN";
		String version = "120";

		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ConsultaInhabilitadosOutEntity consultaInhabilitadosOutEntity = new ConsultaInhabilitadosOutEntity();

		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSINHMTIN(entity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode || COD_ERROR_101 == errorCode) {
				consultaInhabilitadosOutEntity = processTrama(iatxResponse.getTrama(),
						ConsultaInhabilitadosOutEntity.class);
			} else {
				// manejar respuesta ERROR
				return null;
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}

		return consultaInhabilitadosOutEntity;
	}

	/**
	 * Generate request data CNSINHMTIN.
	 *
	 * @param entity
	 *            the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSINHMTIN(ConsultaInhabilitadosInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		iatxRequestData.addBodyValue(entity.getApellido());
		iatxRequestData.addBodyValue(entity.getNombre());

		// 1. Sexo A1 H = Hombre / M =Mujer
		String sexo = "F".equals(entity.getSexo()) ? "M" : "H";
		iatxRequestData.addBodyValue(sexo);

		iatxRequestData.addBodyValue(entity.getFechaNac());
		iatxRequestData.addBodyValue(entity.getTipoDoc().trim());
		iatxRequestData.addBodyValue(entity.getNroDoc());

		return iatxRequestData;
	}

}

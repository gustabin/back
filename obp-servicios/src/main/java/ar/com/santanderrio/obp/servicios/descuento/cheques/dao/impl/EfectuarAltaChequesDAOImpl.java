/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.EfectuarAltaChequesDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.EfectivizacionAltaChequesEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class EfectuarAltaChequesDAOImpl.
 */
@Component
public class EfectuarAltaChequesDAOImpl extends IatxBaseDAO implements EfectuarAltaChequesDAO{

	/** The Constant VERSION_ACTCONFPRE. */
	private static final String VERSION_ACTCONFPRE = "100";

	/** The Constant SERVICIO_ACTCONFPRE. */
	private static final String SERVICIO_ACTCONFPRE = "ACTCONFPRE";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EfectuarAltaChequesDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.dao.EfectuarAltaChequesDAO#efectuarAltaChequesDAO(java.lang.String, ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public EfectivizacionAltaChequesEntity efectuarAltaChequesDAO(String nroTramite, Cliente cliente)
			throws DAOException {
		EfectivizacionAltaChequesEntity entity = new EfectivizacionAltaChequesEntity();
		try {
			entity = ejecucionConsulta(nroTramite, cliente);
			if (entity == null) {
				throw new DAOException("Error de servicio");
			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return entity;
	}

	/**
	 * Ejecucion consulta.
	 *
	 * @param nroTramite
	 *            the nro tramite
	 * @param cliente
	 *            the cliente
	 * @return the efectivizacion alta cheques entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private EfectivizacionAltaChequesEntity ejecucionConsulta(String nroTramite, Cliente cliente) throws DAOException{
		IatxRequest iatxRequest = new IatxRequest(SERVICIO_ACTCONFPRE, VERSION_ACTCONFPRE);
		EfectivizacionAltaChequesEntity operaciones = new EfectivizacionAltaChequesEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataACTCONFPRE(nroTramite, cliente);

			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				operaciones = processTrama(iatxResponse.getTrama(), EfectivizacionAltaChequesEntity.class);
			} else {
				operaciones.setCodigoRetornoExtendido(new BigDecimal(errorCode).toString());
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return operaciones;
	}

	/**
	 * Generate request data ACTCONFPRE.
	 *
	 * @param nroTramite
	 *            the nro tramite
	 * @param cliente
	 *            the cliente
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataACTCONFPRE(String nroTramite, Cliente cliente) {
		IatxRequestData request = new IatxRequestData(cliente);
		request.addBodyValue(nroTramite);
		return request;
	}

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.SimulacionAltaChequeDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequeASimularDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesASimularDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.SimulacionAltaChequesEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.SimulacionAltaChequesWarningEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class SimulacionAltaChequeDAOImpl.
 */
@Component
public class SimulacionAltaChequeDAOImpl extends IatxBaseDAO implements SimulacionAltaChequeDAO{

	/** The Constant VERSION_ACTPRECAR. */
	private static final String VERSION_ACTPRECAR_ = "100";

	/** The Constant SERVICIO_ACTPRECAR. */
	private static final String SERVICIO_ACTPRECAR_ = "ACTPRECAR_";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SimulacionAltaChequeDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant WARNING_EXCEDE_LIMITE_DIARIO. */
	private static final int WARNING_EXCEDE_LIMITE_DIARIO = 20000005;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.dao.SimulacionAltaChequeDAO#simularAltaChequesDAO(ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesASimularDTO, ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public SimulacionAltaChequesEntity simularAltaChequesDAO(ChequesASimularDTO cheques, Cliente cliente) throws DAOException {
		SimulacionAltaChequesEntity entity = new SimulacionAltaChequesEntity();
		try {
			entity = ejecucionConsulta(cheques, cliente);
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
	 * @param cheques
	 *            the cheques
	 * @param cliente
	 *            the cliente
	 * @return the simulacion alta cheques entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private SimulacionAltaChequesEntity ejecucionConsulta(ChequesASimularDTO cheques, Cliente cliente)throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(SERVICIO_ACTPRECAR_, VERSION_ACTPRECAR_);
		SimulacionAltaChequesEntity operaciones = new SimulacionAltaChequesEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataACTPRECAR(cheques, cliente);

			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				operaciones = processTrama(iatxResponse.getTrama(), SimulacionAltaChequesEntity.class);
			} else if (WARNING_EXCEDE_LIMITE_DIARIO == errorCode) {
				SimulacionAltaChequesWarningEntity entity = processTrama(iatxResponse.getTrama(), SimulacionAltaChequesWarningEntity.class);
				BeanUtils.copyProperties(entity, operaciones);
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
	 * Generate request data ACTPRECAR.
	 *
	 * @param cheques
	 *            the cheques
	 * @param cliente
	 *            the cliente
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataACTPRECAR(ChequesASimularDTO cheques, Cliente cliente) {
		IatxRequestData request = new IatxRequestData(cliente);
		request.addBodyValue("90");
		request.addBodyValue(cheques.getSubprodByP());
		request.addBodyValue(cheques.getTipoCuenta());
		request.addBodyValue(cheques.getSucCuenta());
		request.addBodyValue(cheques.getNroCuenta());
		request.addBodyValue("ARS");
		request.addBodyValue("F");
		request.addBodyValue(ISBANStringUtils.repeat("0", 10));
		request.addBodyValue(cheques.getCalificado());
		request.addBodyValue(cheques.getDisponible());
		if(cheques.getNroTramite() == null) {
			request.addBodyValue("PRE");	
		}else {
			request.addBodyValue("REC");
		}
		request.addBodyValue(cheques.getLinea());
		request.addBodyValue(cheques.getTipoLinea());
		if(cheques.getNroTramite() == null) {
			request.addBodyValue(ISBANStringUtils.repeat(" ", 10));	
		}else {
			request.addBodyValue(cheques.getNroTramite());
		}
		for(ChequeASimularDTO cheque : cheques.getListaCheques()) {
			request.addBodyValue(cheque.getBcoGirado());
			request.addBodyValue(cheque.getSucGirada());
			request.addBodyValue(cheque.getCodPostal());
			request.addBodyValue(cheque.getDigVerificador1());
			request.addBodyValue(cheque.getNroCheque());
			request.addBodyValue(cheque.getDigVerificador2());
			request.addBodyValue(cheque.getCuentaGirada());
			request.addBodyValue(cheque.getDigVerificador3());
			request.addBodyValue(cheque.getImpCheque());
			request.addBodyValue(cheque.getFechaVencimiento());
			request.addBodyValue(cheque.getTipoDocLibrador1());
			request.addBodyValue(cheque.getNroDocLibrador1());
			request.addBodyValue(ISBANStringUtils.repeat(" ", 1));
			request.addBodyValue(ISBANStringUtils.repeat(" ", 11));
		}
		return request;
	}


}

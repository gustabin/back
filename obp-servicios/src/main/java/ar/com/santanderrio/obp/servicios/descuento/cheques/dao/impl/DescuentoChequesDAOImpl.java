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
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.DescuentoChequesDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DescuentoChequesEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class DescuentoChequesDAOImpl.
 */
@Component
public class DescuentoChequesDAOImpl extends IatxBaseDAO implements DescuentoChequesDAO {

	/** The Constant VERSION_CNSLISTRA_. */
	private static final String VERSION_CNSLISTRA_ = "100";

	/** The Constant SERVICIO_CNSLISTRA_. */
	private static final String SERVICIO_CNSLISTRA_ = "CNSLISTRA_";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DescuentoChequesDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.dao.DescuentoChequesDAO#obtenerOperaciones(java.lang.String, ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, boolean, java.lang.String)
	 */
	@Override
	public DescuentoChequesEntity obtenerOperaciones(String nroTramite, Cliente cliente, boolean esPrecargada, String filtro) throws DAOException {
		DescuentoChequesEntity entity = new DescuentoChequesEntity();
		try {
			entity = ejecucionConsulta(nroTramite, cliente,esPrecargada,filtro);
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
	 * @param esPrecargada
	 *            the es precargada
	 * @param filtro
	 *            the filtro
	 * @return the descuento cheques entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private DescuentoChequesEntity ejecucionConsulta(String nroTramite, Cliente cliente, boolean esPrecargada, String filtro) throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(SERVICIO_CNSLISTRA_, VERSION_CNSLISTRA_);
		DescuentoChequesEntity operaciones = new DescuentoChequesEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSLISTRA(nroTramite, cliente, esPrecargada,filtro);

			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				operaciones = processTrama(iatxResponse.getTrama(), DescuentoChequesEntity.class);
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
	 * Generate request data CNSLISTRA.
	 *
	 * @param nroTramite
	 *            the nro tramite
	 * @param cliente
	 *            the cliente
	 * @param esPrecargada
	 *            the es precargada
	 * @param filtro
	 *            the filtro
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSLISTRA(String nroTramite, Cliente cliente, boolean esPrecargada, String filtro) {
		IatxRequestData request = new IatxRequestData(cliente);
		request.addBodyValue("I");
		request.addBodyValue(nroTramite);
		request.addBodyValue(cliente.getTipoDocumento());
		request.addBodyValue(cliente.getDni());
		if (esPrecargada) {
			request.addBodyValue("03");
		} else {
			request.addBodyValue(filtro);
		}
		request.addBodyValue("050");
		request.addBodyValue("N");
		request.addBodyValue("4");
		if (!ISBANStringUtils.isNullOrBlank(nroTramite)) {
			request.addBodyValue("S");
		} else {
			request.addBodyValue("N");
		}
		return request;
	}

}

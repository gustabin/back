/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.ConsultaTasasIndicativasDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasasIndicativasEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class ConsultaTasasIndicativasDAOImpl.
 */
@Component
public class ConsultaTasasIndicativasDAOImpl extends IatxBaseDAO  implements ConsultaTasasIndicativasDAO{

    /** The Constant VERSION_CNSTASIND_. */
    private static final String VERSION_CNSTASIND_ = "100";

    /** The Constant SERVICIO_CNSTASIND_. */
    private static final String SERVICIO_CNSTASIND_ = "CNSTASIND_";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaTasasIndicativasDAOImpl.class);

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.dao.ConsultaTasasIndicativasDAO#obtenerTasasIndicativas(java.lang.String, ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public TasasIndicativasEntity obtenerTasasIndicativas(String subProdPaquete, Cliente cliente) throws DAOException {
		TasasIndicativasEntity entity = new TasasIndicativasEntity();
		try {
			entity = ejecucionConsulta(subProdPaquete,cliente);
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
	 * @param subProdPaquete
	 *            the sub prod paquete
	 * @param cliente
	 *            the cliente
	 * @return the tasas indicativas entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private TasasIndicativasEntity ejecucionConsulta(String subProdPaquete, Cliente cliente) throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(SERVICIO_CNSTASIND_, VERSION_CNSTASIND_);
		TasasIndicativasEntity tasasIndicativasEntity = null;
        try {
            IatxRequestData iatxRequestData = generateRequestDataCNSTASIND(subProdPaquete,cliente);

            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {
            	tasasIndicativasEntity = processTrama(iatxResponse.getTrama(), TasasIndicativasEntity.class);
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return tasasIndicativasEntity;
	}

	/**
	 * Generate request data CNSTASIND.
	 *
	 * @param subProdPaquete
	 *            the sub prod paquete
	 * @param cliente
	 *            the cliente
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSTASIND(String subProdPaquete, Cliente cliente) {
		IatxRequestData request = new IatxRequestData(cliente);
		request.addBodyValue("90");
		request.addBodyValue(subProdPaquete);
		request.addBodyValue("N");
		request.addBodyValue("   ");
		return request;
	}

}

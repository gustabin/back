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
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.DetalleOperacionesPrecargadasDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleOperacionesPrecargadasEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class DetalleOperacionesPrecargadasDAOImpl.
 */
@Component
public class DetalleOperacionesPrecargadasDAOImpl extends IatxBaseDAO implements DetalleOperacionesPrecargadasDAO{

    /** The Constant VERSION_CNSDETTRA_. */
    private static final String VERSION_CNSDETTRA_ = "100";

    /** The Constant SERVICIO_CNSDETTRA_. */
    private static final String SERVICIO_CNSDETTRA_ = "CNSDETTRA_";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaTasasIndicativasDAOImpl.class);

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.dao.DetalleOperacionesPrecargadasDAO#obtenerDetalleOperacionesPrecargadas(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.lang.String)
	 */
	@Override
	public DetalleOperacionesPrecargadasEntity obtenerDetalleOperacionesPrecargadas(Cliente cliente,
			String nroTramite) throws DAOException {
		DetalleOperacionesPrecargadasEntity entity = new DetalleOperacionesPrecargadasEntity();
		try {
			entity = ejecucionConsulta(nroTramite,cliente);
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
	 * @return the detalle operaciones precargadas entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private DetalleOperacionesPrecargadasEntity ejecucionConsulta(String nroTramite, Cliente cliente) throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(SERVICIO_CNSDETTRA_, VERSION_CNSDETTRA_);
		DetalleOperacionesPrecargadasEntity tasasIndicativasEntity = null;
        try {
            IatxRequestData iatxRequestData = generateRequestDataCNSDETTRA(nroTramite,cliente);

            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {
            	tasasIndicativasEntity = processTrama(iatxResponse.getTrama(), DetalleOperacionesPrecargadasEntity.class);
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return tasasIndicativasEntity;
	}

	/**
	 * Generate request data CNSDETTRA.
	 *
	 * @param nroTramite
	 *            the nro tramite
	 * @param cliente
	 *            the cliente
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSDETTRA(String nroTramite, Cliente cliente) {
		IatxRequestData request = new IatxRequestData(cliente);
		request.addBodyValue(nroTramite);
		request.addBodyValue("N");
		request.addBodyValue(ISBANStringUtils.repeat(" ", 3));
		request.addBodyValue(ISBANStringUtils.repeat(" ", 3));
		request.addBodyValue(ISBANStringUtils.repeat(" ", 4));
		request.addBodyValue(ISBANStringUtils.repeat(" ", 1));
		request.addBodyValue(ISBANStringUtils.repeat(" ", 8));
		request.addBodyValue(ISBANStringUtils.repeat(" ", 1));
		request.addBodyValue(ISBANStringUtils.repeat(" ", 11));
		request.addBodyValue(ISBANStringUtils.repeat(" ", 1));
		return request;
	}
	
}

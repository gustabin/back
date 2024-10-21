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
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.ClienteHabilitadoChequesDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class ClienteHabilitadoChequesDAOImpl.
 */
@Component
public class ClienteHabilitadoChequesDAOImpl extends IatxBaseDAO  implements ClienteHabilitadoChequesDAO{

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;
    
    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteHabilitadoChequesDAOImpl.class);
    
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.descuento.cheques.dao.ClienteHabilitadoChequesDAO#obtenerHabilitadoCesion(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public DatosCesionEntity obtenerHabilitadoCesion(Cliente cliente) throws DAOException {
        DatosCesionEntity outEntity = new DatosCesionEntity();
        String servicio = "CNSCLICALO";
        String version = "100";
        IatxRequest iatxRequest = new IatxRequest(servicio, version);
        try {
            IatxRequestData iatxRequestData = new IatxRequestData(cliente);

            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {
                outEntity = processTrama(iatxResponse.getTrama(), DatosCesionEntity.class);
            } else {
                LOGGER.error("Codigo de error no esperado de iatx en servicio CNSPESPAGC. ");
                outEntity.setCodigoRetornoExtendido(String.valueOf(iatxResponse.getErrorCode()));
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return outEntity;
    }

    
}

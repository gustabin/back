/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.monedero.dao.ConsultaUnidadControlDAO;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlInEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlOutEntity;

/**
 * The Class ConsultaUnidadControlDAOImpl.
 *
 * @author alejandro_leal
 */
@Component("unidadControlDAO")
public class ConsultaUnidadControlDAOImpl extends IatxBaseDAO implements ConsultaUnidadControlDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaUnidadControlDAOImpl.class);

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.monedero.dao.ConsultaUnidadControlDAO#
     * consultaUC(ar.com.santanderrio.obp.servicios.monedero.entities.
     * ConsultaUnidadControlInEntity)
     */
    @Override
    public ConsultaUnidadControlOutEntity consultaUC(ConsultaUnidadControlInEntity entity) throws DAOException {

        String servicio = "CNSUNICTRL";
        String version = "100";

        IatxRequest iatxRequest = new IatxRequest(servicio, version);
        ConsultaUnidadControlOutEntity consultaUnidadControlOutEntity = new ConsultaUnidadControlOutEntity();

        try {
            IatxRequestData iatxRequestData = generateRequestDataCNSUNICTRL(entity);

            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {
                consultaUnidadControlOutEntity = processTrama(iatxResponse.getTrama(),
                        ConsultaUnidadControlOutEntity.class);
            } else {
                // manejar respuesta ERROR
                return null;
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return consultaUnidadControlOutEntity;

    }

    /**
     * Generate request data CNSUNICTRL.
     *
     * @param entity
     *            the entity
     * @return the iatx request data
     */
    public IatxRequestData generateRequestDataCNSUNICTRL(ConsultaUnidadControlInEntity entity) {
        IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
        iatxRequestData.addBodyValue(entity.getNup());
        iatxRequestData.addBodyValue(StringUtils.isNotBlank(entity.getTipoDocumento()) ? entity.getTipoDocumento()
                : StringUtils.repeat(" ", 2));
        iatxRequestData.addBodyValue(StringUtils.isNotBlank(entity.getNroDocumento()) ? entity.getNroDocumento()
                : StringUtils.repeat(" ", 11));
        return iatxRequestData;
    }

}

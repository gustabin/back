/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.merlin.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.dao.impl.ConsultaInhabilitadosDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.merlin.dao.MerlinDAO;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.ResultadoMerlinEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.ResultadoMerlinWarningEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinError1Exception;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class MerlinDAOImpl.
 */
@Component("merlinDAO")
public class MerlinDAOImpl extends IatxBaseDAO implements MerlinDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaInhabilitadosDAOImpl.class);

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /** The servicio pag tjc. */
    @Value("${SERVICIO.PREFIJO.CNSDMERLIN}")
    private String servicioCnsMerlin;

    /** The version pag tjc. */
    @Value("${SERVICIO.VERSION.CNSDMERLIN}")
    private String versionCnsMerlin;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.merlin.dao.MerlinDAO#
     * getResultadoMerlin(ar.com.santanderrio.obp.servicios.comun.merlin.
     * entities. DatosMerlinInEntity)
     */
    @Override
    public ResultadoMerlinEntity getResultadoMerlin(DatosMerlinInEntity datosDeDomicilio)
            throws DAOException, MerlinError1Exception {
        return resultadoMerlin(generateRequestDataCNSDMERLIN(datosDeDomicilio));
    }

    /**
	 * Resultado merlin.
	 *
	 * @param iatxRequestData
	 *            the iatx request data
	 * @return the resultado merlin entity
	 * @throws DAOException
	 *             the DAO exception
	 * @throws MerlinError1Exception
	 *             the merlin error 1 exception
	 */
    private ResultadoMerlinEntity resultadoMerlin(IatxRequestData iatxRequestData)
            throws DAOException, MerlinError1Exception {
        IatxRequest iatxRequest = new IatxRequest(servicioCnsMerlin, versionCnsMerlin);
        ResultadoMerlinEntity resultadoMerlinEntity = new ResultadoMerlinEntity();
        iatxRequest.setData(iatxRequestData);
        try {
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {
                resultadoMerlinEntity = processTrama(iatxResponse.getTrama(), ResultadoMerlinEntity.class);
            } else if (String.valueOf(errorCode).startsWith("1")) {
                LOGGER.debug("Codigo de error de IATX: " + errorCode);
                throw new MerlinError1Exception();
            } else if (String.valueOf(errorCode).startsWith("2")) {
                LOGGER.debug("Codigo de warning de IATX: " + errorCode);
                ResultadoMerlinWarningEntity resultadoMerlinWarningEntity = processTrama(iatxResponse.getTrama(),
                        ResultadoMerlinWarningEntity.class);
                resultadoMerlinEntity = resultadoMerlinWarningEntity.toResultadoMerlinEntity();

            } else {
                LOGGER.debug("Codigo de error de IATX: " + errorCode);
                throw new DAOException();
            }
        } catch (MerlinError1Exception me) {
            LOGGER.error(me.getMessage(), me);
            throw new MerlinError1Exception();
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return resultadoMerlinEntity;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.comun.merlin.dao.MerlinDAO#busquedaMerlin(ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity)
     */
    @Override
    public ResultadoMerlinEntity busquedaMerlin(DatosMerlinInEntity datosDeDomicilio)
            throws DAOException, MerlinError1Exception {
        return resultadoMerlin(generateRequestDataBusquedaCNSDMERLIN(datosDeDomicilio));
    }

    /**
     * Generate request data CNSDMERLIN.
     *
     * @param datosDeDomicilio
     *            the datos de domicilio
     * @return the iatx request data
     */
    private IatxRequestData generateRequestDataCNSDMERLIN(DatosMerlinInEntity datosDeDomicilio) {
        IatxRequestData iatxRequestData = new IatxRequestData(datosDeDomicilio.getCliente());
        iatxRequestData.addBodyValue(datosDeDomicilio.getNup());
        iatxRequestData.addBodyValue(datosDeDomicilio.getTipoDeDomicilio());
        iatxRequestData.addBodyValue(datosDeDomicilio.getSecuenciaDeDomicilio());
        iatxRequestData.addBodyValue(ISBANStringUtils
                .removerCaraceteresEspeciales(ISBANStringUtils.fillStr(datosDeDomicilio.getNombreCalle(), 50)));
        iatxRequestData.addBodyValue(StringUtils.leftPad(datosDeDomicilio.getNumeroBloque(), 10, "0"));
        iatxRequestData.addBodyValue(StringUtils.leftPad(datosDeDomicilio.getCodigoPostal(), 8, "0"));
        iatxRequestData.addBodyValue(datosDeDomicilio.getProvincia().trim());
        iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(datosDeDomicilio.getLocalidad(), 30));
        iatxRequestData.addBodyValue(StringUtils.rightPad("", 30));
        iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(datosDeDomicilio.getPiso(), 4));
        iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(datosDeDomicilio.getDepartamento(), 5));
        iatxRequestData.addBodyValue(datosDeDomicilio.getTimestamp());

        return iatxRequestData;
    }

    /**
	 * Generate request data busqueda CNSDMERLIN.
	 *
	 * @param datosDeDomicilio
	 *            the datos de domicilio
	 * @return the iatx request data
	 */
    private IatxRequestData generateRequestDataBusquedaCNSDMERLIN(DatosMerlinInEntity datosDeDomicilio) {
        IatxRequestData iatxRequestData = new IatxRequestData(datosDeDomicilio.getCliente());
        iatxRequestData.addBodyValue(datosDeDomicilio.getNup());
        iatxRequestData.addBodyValue(StringUtils.rightPad("", 3));
        iatxRequestData.addBodyValue(datosDeDomicilio.obtenerSecuenciaDeDomicilioBusqueda());
        iatxRequestData.addBodyValue(ISBANStringUtils.removerCaraceteresEspeciales(
                StringUtils.rightPad(StringUtils.trimToEmpty(datosDeDomicilio.getNombreCalle()), 50)));
        iatxRequestData.addBodyValue(
                StringUtils.leftPad(StringUtils.trimToEmpty(datosDeDomicilio.getNumeroBloque()), 10, "0"));
        iatxRequestData
                .addBodyValue(StringUtils.leftPad(StringUtils.trimToEmpty(datosDeDomicilio.getCodigoPostal()), 8, "0"));
        iatxRequestData.addBodyValue(datosDeDomicilio.getProvincia());
        iatxRequestData
                .addBodyValue(StringUtils.rightPad(StringUtils.trimToEmpty(datosDeDomicilio.getLocalidad()), 30));
        iatxRequestData.addBodyValue(StringUtils.rightPad("", 30));
        iatxRequestData.addBodyValue(StringUtils.rightPad(StringUtils.trimToEmpty(datosDeDomicilio.getPiso()), 4));
        iatxRequestData
                .addBodyValue(StringUtils.rightPad(StringUtils.trimToEmpty(datosDeDomicilio.getDepartamento()), 5));
        iatxRequestData.addBodyValue(StringUtils.rightPad("", 26));

        return iatxRequestData;
    }
}

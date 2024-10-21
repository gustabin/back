/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.tarjetas.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.dao.ConsultaTarjetasDAO;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.ConsultaDatosTarjetasIn;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.ConsultaDatosTarjetasOut;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.TarjetaDatos;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * TarjetasDAOImpl.
 *
 * @author Silvina_Luque
 */
@Component
public class ConsultaTarjetasDAOImpl implements ConsultaTarjetasDAO {
    
    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;
    
    /** The Constant LONGITUD_FIELDSET. */
    private static final int LONGITUD_FIELDSET = 43;
    
    /** The Constant LONGITUD_TIPO_CUENTA. */
    private static final int LONGITUD_MARCA = 1;

    /** The Constant LONGITUD_TIPO_CUENTA. */
    private static final int LONGITUD_TIPO_CUENTA = 1;

    /** The Constant LONGITUD_CUENTA. */
    private static final int LONGITUD_CUENTA = 5;
    
    /** The Constant OPCION. */
    private static final String OPCION = "A";

    /** The Constant CATEGORIA. */
    private static final String CATEGORIA = "T";

    /** The Constant logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaTarjetasDAOImpl.class);
    
    /** Codigo retorno ok. */
    private static final int OK_CODIGO_RETORNO = 0;

    /**
	 * consultaDatosTarjetas.
	 *
	 * @param in
	 *            the in
	 * @return the consulta datos tarjetas out
	 * @throws DAOException
	 *             the DAO exception
	 */
    @Override
    public ConsultaDatosTarjetasOut consultaDatosTarjetas(ConsultaDatosTarjetasIn in) throws DAOException {
        LOGGER.info("TarjetasDAOImpl _ Iniciando metodo consultaDatosTarjetas");
        ConsultaDatosTarjetasOut out = new ConsultaDatosTarjetasOut();
        IatxRequest request = buildIatxRequest(in);
        try {
            IatxResponse iatxResponse = iatxComm.exec(request);
            LOGGER.debug("TarjetasDAOImpl _ Finalizando llamada iatx CNSTJCDATC");
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {
                out = buildDatosTarjeta(iatxResponse);
            } else {
                LOGGER.debug("Error servicio CNSTJCDATC, codigo retorno distinto de 0");
                throw new DAOException("");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        LOGGER.debug("TarjetasDAOImpl _ Finalizando llamada consultaDatosTarjetas");
        return out;
       

    }

    /**
     * Builds the datos tarjeta.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the consulta datos tarjetas out
     */
    private ConsultaDatosTarjetasOut buildDatosTarjeta(IatxResponse iatxResponse) {
        ConsultaDatosTarjetasOut out = new ConsultaDatosTarjetasOut();
        List<TarjetaDatos> tarjetas = new ArrayList<TarjetaDatos>();
        out.setMarcaReLlamada(iatxResponse.getData(1));
        out.setHayReEnganche(iatxResponse.getData(2));
        int cant = Integer.parseInt(iatxResponse.getData(5));
        int next = 6;
        for (int n = 0; n < cant; ++n) {
            TarjetaDatos tarjeta = new TarjetaDatos();
            tarjeta.setNroTarjeta(iatxResponse.getData(next));
            tarjeta.setApliCtaRelacionada(iatxResponse.getData(next + 1));
            tarjeta.setSucursalCtaRelacionada(iatxResponse.getData(next + 2));
            tarjeta.setTipoCuenta(iatxResponse.getData(3));
            tarjeta.setNroCuenta(iatxResponse.getData(4));
            tarjeta.setEstadoTarjeta(iatxResponse.getData(next + 6));
            tarjeta.setApellidoNombreEmbozado(iatxResponse.getData(next + 27));
            tarjetas.add(tarjeta);
            next += LONGITUD_FIELDSET;
        }
        out.setTarjetas(tarjetas);
        return out;
    }

    /**
     * Builds the iatx request.
     *
     * @param in
     *            the in
     * @return the iatx request
     */
    private IatxRequest buildIatxRequest(ConsultaDatosTarjetasIn in) {
        IatxRequest request = new IatxRequest("CNSTJCDATC", "100");
        IatxRequestData requestData = new IatxRequestData(in.getCliente());
        requestData.addBodyValue(StringUtils.leftPad(in.getMarca(), LONGITUD_MARCA, '0'));
        requestData.addBodyValue(StringUtils.leftPad(in.getTipoCuenta(), LONGITUD_TIPO_CUENTA, '0'));
        requestData.addBodyValue(StringUtils.leftPad(in.getNroCuenta().substring(5), LONGITUD_CUENTA, '0'));
        requestData.addBodyValue("    ");
        requestData.addBodyValue("000");
        requestData.addBodyValue("0");
        requestData.addBodyValue("0000000000000");
        requestData.addBodyValue("00");
        requestData.addBodyValue("0000000000000000");
        requestData.addBodyValue(OPCION);
        requestData.addBodyValue(CATEGORIA);
        requestData.addBodyValue(in.getClaveReEnganche());
        request.setData(requestData);
        return request;
    }
    
}

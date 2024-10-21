/*
 * 
 */
package ar.com.santanderrio.obp.servicios.stopdebittarjetas.dao.impl;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.dao.StopDebitTarjetasDAO;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.DatosStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitOut;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * Clase StopDebitTarjetasDAOImpl.
 *
 * @author mariano.g.pulera
 */

@Component
public class StopDebitTarjetasDAOImpl implements StopDebitTarjetasDAO {

    /** Constante LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StopDebitTarjetasDAOImpl.class);

    /** Variable iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /** The mensaje dao. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The legal dao. */
    @Autowired
    private LegalDAO legalDAO;

    /** Constante CODIGO_RETORNO_OK. */
    private static final int CODIGO_RETORNO_OK = 0;

    /** The Constant CODIGO_RETORNO_ERROR_NO_HAY_STOP_DEBIT. */
    private static final int CODIGO_RETORNO_ERROR_NO_HAY_STOP_DEBIT = 10001054;

    /**
     * Ejecuta el servicio STPDEB para solicitar el stop debit configurado.
     *
     * @param cliente
     *            the cliente
     * @param datos
     *            the datos
     * @return OK o ERROR segun corresponda.
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public StopDebitOut realizarStopDebitTarjeta(Cliente cliente, DatosStopDebit datos) throws DAOException {
        StopDebitOut out = new StopDebitOut();

        IatxRequest request = new IatxRequest("STPDEB____", "100");
        try {
            IatxRequestData requestData = new IatxRequestData(cliente);

            if (("02").equals(datos.getTipoCuenta())) {
                requestData.addBodyValue("00");
            } else {
                requestData.addBodyValue(datos.getTipoCuenta());
            }

            requestData.addBodyValue(datos.getSucursalCuenta());
            requestData.addBodyValue(
                    datos.getNroCuenta().substring(datos.getNroCuenta().length() - 7, datos.getNroCuenta().length()));

            requestData.addBodyValue(datos.getCodigoServicio());
            requestData.addBodyValue(datos.getNroPartida());

            request.setData(requestData);

            IatxResponse iatxResponse = iatxComm.exec(request);

            out.setNroDeComprobante(iatxResponse.getNroComprobante());
            if (CODIGO_RETORNO_OK == iatxResponse.getErrorCode()) {
                out.setResultado(ESTADO_OK);
            } else {
                out.setResultado(ESTADO_ERROR);
                out.setErrorCode(iatxResponse.getErrorCode());
            }

            return out;

        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.stopdebittarjetas.dao.
     * StopDebitTarjetasDAO#cancelarStopDebitTarjeta(ar.com.santanderrio.obp.
     * servicios.clientes.entities.Cliente,
     * ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.
     * DatosStopDebit, java.lang.String)
     */
    @Override
    public ComprobanteFeedbackView cancelarStopDebitTarjeta(Cliente cliente, DatosStopDebit datos, String nroTarjeta)
            throws DAOException {

        IatxRequest request = new IatxRequest("STPDEB____", "100");
        try {
            IatxRequestData requestData = new IatxRequestData(cliente);

            requestData.addBodyValue(datos.getTipoCuenta());
            requestData.addBodyValue(datos.getSucursalCuenta());
            requestData.addBodyValue(
                    datos.getNroCuenta().substring(datos.getNroCuenta().length() - 7, datos.getNroCuenta().length()));

            requestData.addBodyValue(datos.getCodigoServicio());
            requestData.addBodyValue(datos.getNroPartida());

            requestData.setIndOperReversa("1");
            request.setData(requestData);

            IatxResponse iatxResponse = iatxComm.exec(request);

            if (CODIGO_RETORNO_OK == iatxResponse.getErrorCode()) {
                return armarComprobanteFeedback(iatxResponse, nroTarjeta);
            }
            LOGGER.info(iatxResponse.getMensaje());
            if (CODIGO_RETORNO_ERROR_NO_HAY_STOP_DEBIT == iatxResponse.getErrorCode()) {
                throw new DAOException(CodigoMensajeConstantes.CANCELAR_STOP_DEBIT_TARJETA_NO_HAY_STOP_DEBIT);
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
        }
        throw new DAOException(CodigoMensajeConstantes.CANCELAR_STOP_DEBIT_TARJETA_ERROR_GENERICO);
    }

    /**
     * Armar comprobante feedback.
     *
     * @param iatxResponse
     *            the iatx response
     * @param nroTarjeta
     *            the nro tarjeta
     * @return the comprobante feedback
     * @throws DAOException
     *             the DAO exception
     */
    private ComprobanteFeedbackView armarComprobanteFeedback(IatxResponse iatxResponse, String nroTarjeta)
            throws DAOException {

        ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
        comprobante.setNroDeComprobante(iatxResponse.getNroComprobante());
        String mensajeFeedback = mensajeBO.obtenerMensajePorCodigo("1261").getMensaje();
        mensajeFeedback = MessageFormat.format(mensajeFeedback, "<b>" + nroTarjeta + "</b>");
        comprobante.setMensajeFeedback(mensajeFeedback);
        comprobante.setLegalesSEUO(legalDAO.obtenerLegal("1005"));

        String strFecha = iatxResponse.getFecha();
        String strHora = iatxResponse.getHora();
        String fecha = strFecha.substring(6, strFecha.length()) + "/" + strFecha.substring(4, 6) + "/"
                + strFecha.substring(0, 4);
        String fechaMobile = strFecha.substring(6, strFecha.length()) + "/" + strFecha.substring(4, 6) + "/"
                + strFecha.substring(2, 4);
        String hora = strHora.substring(0, 5);
        comprobante.setFechaHora(fecha + " - " + hora);
        comprobante.setFechaHoraMobile(fechaMobile + " - " + hora);
        comprobante.setAccionRealizada(true);

        return comprobante;

    }

}

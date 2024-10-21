/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagos.dao.StopDebitDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosPagoAutomaticoEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;

/**
 * The Class StopDebitDAOImpl.
 */
@Component
/**
 * Conector con el servicio STPDEBPAU
 * 
 * @author b039920
 *
 */
public class StopDebitDAOImpl implements StopDebitDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StopDebitDAOImpl.class);

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /** The servicio stpdebpau. */
    private String servicioStpdebpau = "STPDEBPAU_";

    /** The version stpdebpau. */
    private String versionStpdebpau = "100";

    /** The servicio acvddistpd. */
    private String servicioAcvddistpd = "ACVDDISTPD";

    /** The version acvddistpd. */
    private String versionAcvddistpd = "100";

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;

    /** The Constant POSICION_SISTEMA_ASOCIADO_AL_ERROR. */
    private static final int POSICION_SISTEMA_ASOCIADO_AL_ERROR = 1;

    /** The Constant POSICION_CANTIDAD_DESCRIPCIONES. */
    private static final int POSICION_CANTIDAD_DESCRIPCIONES = 2;

    /** The Constant POSICION_INICIO_DESCRIPCIONES. */
    private static final int POSICION_INICIO_DESCRIPCIONES = 3;

    /** The Constant FORMATO_FECHA_STPDEBPAU. */
    private static final String FORMATO_FECHA_STPDEBPAU = "yyMMdd";

    /** The Constant FORMATO_FECHA_ACVDDISTPD. */
    private static final String FORMATO_FECHA_ACVDDISTPD = "yyyyMMdd";

    /** The Constant INDICADOR_FECHA_STOP. */
    private static final String INDICADOR_FECHA_STOP = "";

    /** The Constant DATO_EN_BLANCO. */
    private static final String DATO_EN_BLANCO = "";

    /** The Constant LONGITUD_TIPO_CUENTA. */
    private static final int LONGITUD_TIPO_CUENTA = 2;

    /** The Constant LONGITUD_NOMBRE_EMPRESA. */
    private static final int LONGITUD_NOMBRE_EMPRESA = 10;

    /** The Constant LONGITUD_CODIGO_CLIENTE_EMPRESA. */
    private static final int LONGITUD_CODIGO_CLIENTE_EMPRESA = 22;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.StopDebitDAO#
     * ejecutarStopDebitPagoMisCuentas(ar.com.santanderrio.obp.servicios.pagos.
     * entities.DatosPagoAutomatico,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public ResultadoTransaccion ejecutarStopDebitPagoMisCuentas(DatosPagoAutomaticoEntity datosPagoAutomatico,
            Cliente cliente) throws DAOException {
        IatxRequest iatxRequest = new IatxRequest(servicioStpdebpau, versionStpdebpau);
        try {
            IatxRequestData iatxRequestData = generarIatxRequestDataStopDebit(datosPagoAutomatico, cliente);
            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            int errorCode = iatxResponse.getErrorCode();
            ResultadoTransaccion resultado = new ResultadoTransaccion();
            if (OK_CODIGO_RETORNO == errorCode) {
                resultado = iatxResponse.getResultadoTransaccion();
                resultado.setEstadoRespuesta(EstadoRespuesta.OK);
                datosPagoAutomatico.setNumeroComprobante(iatxResponse.getNroComprobante());
                return resultado;
            } else {
                parsearResponseError(datosPagoAutomatico, iatxResponse);
                resultado = iatxResponse.getResultadoTransaccion();
                resultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
                return resultado;
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    /**
     * Generar iatx request data stop debit.
     *
     * @param datosPagoAutomatico
     *            the datos pago automatico
     * @param cliente
     *            the cliente
     * @return the iatx request data
     */
    private IatxRequestData generarIatxRequestDataStopDebit(DatosPagoAutomaticoEntity datosPagoAutomatico,
            Cliente cliente) {

        IatxRequestData requestData = new IatxRequestData(cliente);
        SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_FECHA_STPDEBPAU);

        requestData.addBodyValue(datosPagoAutomatico.getEmpresa());
        requestData.addBodyValue(datosPagoAutomatico.getIdClienteEmpresa());
        requestData.addBodyValue(datosPagoAutomatico.getFactura());
        requestData.addBodyValue(formatter.format(datosPagoAutomatico.getVencimiento()));

        return requestData;
    }

    /**
     * Parsear response error.
     *
     * @param datosPagoAutomatico
     *            the datos pago automatico
     * @param iatxResponse
     *            the iatx response
     */
    private void parsearResponseError(DatosPagoAutomaticoEntity datosPagoAutomatico, IatxResponse iatxResponse) {
        datosPagoAutomatico.setDescripcionesDeError(new ArrayList<String>());
        datosPagoAutomatico.setSistemaAsociadoAlError(iatxResponse.getData(POSICION_SISTEMA_ASOCIADO_AL_ERROR));
        int cantidadMensajes = Integer.valueOf(iatxResponse.getData(POSICION_CANTIDAD_DESCRIPCIONES));

        for (int i = POSICION_INICIO_DESCRIPCIONES; i < cantidadMensajes + POSICION_INICIO_DESCRIPCIONES; i++) {
            datosPagoAutomatico.getDescripcionesDeError().add(iatxResponse.getData(i));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.StopDebitDAO#
     * ejecutarStopDebitoEnCuenta(ar.com.santanderrio.obp.servicios.pagos.
     * entities.PagoPendiente,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public ResultadoTransaccion ejecutarStopDebitoEnCuenta(PagoPendiente pagoPendiente, Cliente cliente)
            throws DAOException {
        IatxRequest iatxRequest = new IatxRequest(servicioAcvddistpd, versionAcvddistpd);
        try {
            IatxRequestData iatxRequestData = generarIatxRequestDataAdhesionStopDebit(pagoPendiente, cliente);
            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            int errorCode = iatxResponse.getErrorCode();
            ResultadoTransaccion resultado = new ResultadoTransaccion();
            if (OK_CODIGO_RETORNO == errorCode) {
                pagoPendiente.getDatosPagoAutomatico().setNumeroComprobante(iatxResponse.getNroComprobante());
                resultado = iatxResponse.getResultadoTransaccion();
                resultado.setEstadoRespuesta(EstadoRespuesta.OK);
                return resultado;
            } else {
                parsearResponseError(pagoPendiente, iatxResponse);
                resultado = iatxResponse.getResultadoTransaccion();
                resultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
                return resultado;
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    /**
     * Generar iatx request data adhesion stop debit.
     *
     * @param pagoPendiente
     *            the pago pendiente
     * @param cliente
     *            the cliente
     * @return the iatx request data
     */
    private IatxRequestData generarIatxRequestDataAdhesionStopDebit(PagoPendiente pagoPendiente, Cliente cliente) {
        IatxRequestData requestData = new IatxRequestData(cliente);
        SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_FECHA_ACVDDISTPD);

        requestData.addBodyValue(pagoPendiente.getCuitEmpresa());
        requestData.addBodyValue(StringUtils.left(pagoPendiente.getNombreEmpresaIatx(), LONGITUD_NOMBRE_EMPRESA));
        requestData.addBodyValue(
                StringUtils.rightPad(pagoPendiente.getCodigoClienteEmpresa(), LONGITUD_CODIGO_CLIENTE_EMPRESA, "0"));
        requestData.addBodyValue(StringUtils.rightPad(
                pagoPendiente.getDatosPagoAutomatico().getTipoCuenta().getCodigo().toString(), LONGITUD_TIPO_CUENTA));
        requestData.addBodyValue(pagoPendiente.getDatosPagoAutomatico().getIdentificacionCuenta().getNroSucursal());
        requestData
                .addBodyValue(pagoPendiente.getDatosPagoAutomatico().getIdentificacionCuenta().getNroCuentaProducto());
        requestData.addBodyValue(pagoPendiente.getOrdenFirmante());
        String idDebitoDDI = pagoPendiente.getDatosPagoAutomatico().getIdDebitoDDI();
        Date vencimientoDDI = pagoPendiente.getVencimiento();
        String vencimientoFormateadoDDI;
        if (idDebitoDDI == null || !esHoy(vencimientoDDI)) {
            idDebitoDDI = DATO_EN_BLANCO;
            vencimientoFormateadoDDI = DATO_EN_BLANCO;
        } else {
            vencimientoFormateadoDDI = formatter.format(vencimientoDDI);
        }
        requestData.addBodyValue(idDebitoDDI);
        requestData.addBodyValue(vencimientoFormateadoDDI);
        requestData.addBodyValue(INDICADOR_FECHA_STOP);

        return requestData;
    }

    /**
     * Parsear response error.
     *
     * @param pagoPendiente
     *            the pago pendiente
     * @param iatxResponse
     *            the iatx response
     */
    private void parsearResponseError(PagoPendiente pagoPendiente, IatxResponse iatxResponse) {
        pagoPendiente.getDatosPagoAutomatico().setDescripcionesDeError(new ArrayList<String>());
        pagoPendiente.getDatosPagoAutomatico()
                .setSistemaAsociadoAlError(iatxResponse.getData(POSICION_SISTEMA_ASOCIADO_AL_ERROR));
        int cantidadMensajes = Integer.valueOf(iatxResponse.getData(POSICION_CANTIDAD_DESCRIPCIONES));

        for (int i = POSICION_INICIO_DESCRIPCIONES; i < cantidadMensajes + POSICION_INICIO_DESCRIPCIONES; i++) {
            pagoPendiente.getDatosPagoAutomatico().getDescripcionesDeError().add(iatxResponse.getData(i));
        }

    }

    /**
     * Es hoy.
     *
     * @param vencimiento
     *            the vencimiento
     * @return true, if successful
     */
    private boolean esHoy(Date vencimiento) {

        Calendar hoy = Calendar.getInstance();
        Calendar vencimientoPagoPendiente = Calendar.getInstance();
        vencimientoPagoPendiente.setTime(vencimiento);

        if (hoy.get(Calendar.DAY_OF_MONTH) == vencimientoPagoPendiente.get(Calendar.DAY_OF_MONTH)
                && hoy.get(Calendar.MONTH) == vencimientoPagoPendiente.get(Calendar.MONTH)
                && hoy.get(Calendar.YEAR) == vencimientoPagoPendiente.get(Calendar.YEAR)) {
            return true;
        }
        return false;
    }

}

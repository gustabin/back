/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.ConfirmacionSolicitudPlanV;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.InformacionPlanV;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.FinanciacionTarjetaBO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.impl.ConsultaFinanciacionDAOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosConfirmacionFinanciacionTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.SimulacionPlanVRequestDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.exceptions.SimulacionDAOException;

/**
 * The Class FinanciacionTarjetaBOImpl.
 * 
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 6, 2016
 */
@Component
public class FinanciacionTarjetaBOImpl implements FinanciacionTarjetaBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FinanciacionTarjetaBOImpl.class);

    /** The Constant ERROR_WS_SIM. */
    private static final String ERROR_WS_SIM = "Ha ocurrido un error al consumir alguno de los WS de simulacion financiacion";

    /** The Constant ERROR_WS_INF. */
    private static final String ERROR_WS_INF = "Ha ocurrido un error al consumir alguno de los WS de obtener informacion";

    /** The Constant WARNING_LIMITE_IMPORTES. */
    private static final String WARNING_LIMITE_IMPORTES = "Ha ocurrido un error, importe ingresado es mayor o igual al maximo.";

    /** The Constant ERROR_MONTO_MAXIMO_CERO. */
    private static final String ERROR_MONTO_MAXIMO_CERO = "******* NO SE PUEDE FINANCIAR, LA TARJETA TIENE UN MONTO MAXIMO CERO *******";

    /** The consulta financiacion DAO. */
    @Autowired
    private ConsultaFinanciacionDAOImpl consultaFinanciacionDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The mensaje DAO. */
    @Autowired
    private MensajeDAO mensajeDAO;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.FinanciacionTarjetaBO#
     * solicitarFinanciacionTarjeta()
     */
    @Override
    public Respuesta<FinanciacionTarjetaDTO> solicitarFinanciacionTarjeta(Cuenta cuenta) {
        Respuesta<FinanciacionTarjetaDTO> respuesta;
        try {

            FinanciacionTarjetaDTO financiacionTarjetaDTO = new FinanciacionTarjetaDTO();
            InformacionPlanV informacionPlanV;

            LOGGER.info("LLamando al WS obtenerInformacionPlanV");
            try {
                informacionPlanV = consultaFinanciacionDAO.obtenerInformacionPlanV(cuenta);
            } catch (DAOException e) {
                String errorCode = e.getErrorCode();
                LOGGER.error(ERROR_WS_INF + e.getMessage() + " " + errorCode, e);

                if (errorCode != null && "1".equals(errorCode)) {
                    return getErrorPersonalizadoSinSaldo();
                } else {
                    Mensaje mensajeError = mensajeDAO
                            .obtenerMensajePorCodigo(CodigoMensajeConstantes.SIMULACION_FINANCIACION_TARJETA_ERROR);
                    return respuestaFactory.crearRespuestaError(FinanciacionTarjetaDTO.class, mensajeError.getMensaje(),
                            null);
                }

            }

            financiacionTarjetaDTO.setInformacionPlanV(informacionPlanV);

            SimulacionPlanVRequestDTO request = new SimulacionPlanVRequestDTO(cuenta.getTipoCuentaEnum().name(),
                    cuenta.getNroCuentaProducto(),
                    new BigDecimal(informacionPlanV.getMontoMaximoAFinanciar(), MathContext.DECIMAL64),
                    DivisaEnum.PESO.getCodigo(), String.valueOf(informacionPlanV.getCuotasMaximo()), cuenta.getCbu());

            if (informacionPlanV.getMontoMaximoAFinanciar() == 0D) {
                LOGGER.debug(ERROR_MONTO_MAXIMO_CERO);
                return getErrorPersonalizadoSinSaldo();
            }

            LOGGER.info("LLamando al WS simulacionFinanciacionPlanV");
            financiacionTarjetaDTO.setSimulacionPlanVDTO(
                    consultaFinanciacionDAO.simulacionFinanciacionPlanV(request, sesionCliente.getCliente()));

            respuesta = respuestaFactory.crearRespuestaOk(FinanciacionTarjetaDTO.class);
            respuesta.setRespuesta(financiacionTarjetaDTO);

        } catch (SimulacionDAOException simExc) {
            LOGGER.warn(ERROR_WS_SIM, simExc);
            Mensaje mensajeWarningSimulacion = mensajeDAO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.SIMULACION_FINANCIACION_TARJETA_ERROR);
            respuesta = respuestaFactory.crearRespuestaError(FinanciacionTarjetaDTO.class,
                    mensajeWarningSimulacion.getMensaje(), null);
        } catch (DAOException e) {
            LOGGER.error(ERROR_WS_SIM, e);
            Mensaje mensajeErrorSimulacion = mensajeDAO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.SIMULACION_FINANCIACION_TARJETA_ERROR);
            respuesta = respuestaFactory.crearRespuestaError(FinanciacionTarjetaDTO.class,
                    mensajeErrorSimulacion.getMensaje(), null);
        }
        return respuesta;
    }

    /**
     * Gets the error personalizado sin saldo. Mensaje: El resúmen de esta
     * tarjeta no posee ningún saldo para financiar.
     * 
     * @return the error personalizado sin saldo
     */
    private Respuesta<FinanciacionTarjetaDTO> getErrorPersonalizadoSinSaldo() {
        LOGGER.debug(ERROR_MONTO_MAXIMO_CERO);
        Mensaje mensajeWarning = mensajeDAO
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.SIMULACION_FINMAX_FINMIN_TARJETA_ERROR);
        return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(FinanciacionTarjetaDTO.class,
                mensajeWarning.getMensaje(), TipoError.ERROR_FINANCIACION_TARJETA_SALDO_INSUFICIENTE.getDescripcion());
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.FinanciacionTarjetaBO#
     * ejecutarFinanciacionTarjeta()
     */
    @Override
    public Respuesta<FinanciacionTarjetaDTO> ejecutarFinanciacionTarjeta(
            DatosConfirmacionFinanciacionTarjetaDTO datos) {
        Respuesta<FinanciacionTarjetaDTO> respuesta;
        FinanciacionTarjetaDTO financiacionTarjetaDTO = new FinanciacionTarjetaDTO();
        try {

            ConfirmacionSolicitudPlanV confirmacionSolicitudPlanV = consultaFinanciacionDAO
                    .ejecutarFinanciacionTarjeta(datos);
            financiacionTarjetaDTO.setConfirmacionSolicitudPlanV(confirmacionSolicitudPlanV);

            respuesta = respuestaFactory.crearRespuestaOk(FinanciacionTarjetaDTO.class);
            respuesta.setRespuesta(financiacionTarjetaDTO);

        } catch (DAOException e) {
            LOGGER.error(ERROR_WS_SIM, e);
            String mensajeError = mensajeDAO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.CONFIRMACION_FINANCIACION_TARJETA_ERROR)
                    .getMensaje();
            mensajeError = MessageFormat.format(mensajeError, datos.getTarjeta().getTipoCuentaEnum().name(),
                    ISBANStringUtils.mascaraTarjetaCredito(datos.getTarjeta().getNroTarjetaCredito(),
                            datos.getTarjeta().getTipoCuenta()));
            respuesta = respuestaFactory.crearRespuestaWarning(FinanciacionTarjetaDTO.class, mensajeError, null);
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.FinanciacionTarjetaBO#
     * simularFinanciacionTarjeta(ar.com.santanderrio.obp.servicios.tarjetas.
     * entities.FinanciacionTarjetaView)
     */
    @Override
    public Respuesta<FinanciacionTarjetaDTO> simularFinanciacionTarjeta(FinanciacionTarjetaView financiacionTarjetaView,
            Cuenta cuenta) {
        Respuesta<FinanciacionTarjetaDTO> respuesta;
        Mensaje mensajeWarning;
        FinanciacionTarjetaDTO financiacionTarjetaDTO = new FinanciacionTarjetaDTO();

        if (esImporteMenorQueImporteMinimo(financiacionTarjetaView)) {
            mensajeWarning = mensajeDAO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.SIMULACION_FIN_FINMIN_TARJETA_ERROR);
            return respuestaFactory.crearRespuestaWarning(FinanciacionTarjetaDTO.class, mensajeWarning.getMensaje(),
                    null);
        }
        if (esImporteMayorQueImporteMaximo(financiacionTarjetaView)) {
            mensajeWarning = mensajeDAO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.SIMULACION_FIN_FINMAX_TARJETA_ERROR);
            return respuestaFactory.crearRespuestaWarning(FinanciacionTarjetaDTO.class, mensajeWarning.getMensaje(),
                    null);
        }
        SimulacionPlanVRequestDTO simulacionPlanVRequestDTO = new SimulacionPlanVRequestDTO(
                financiacionTarjetaView.getMarcaTarjetaSeleccionada(),
                financiacionTarjetaView.getNroCuentaProductoSeleccionado(),
                new BigDecimal(financiacionTarjetaView.getImporteFinanciar()), DivisaEnum.PESO.getCodigo(),
                financiacionTarjetaView.getCantidadCuotas(), cuenta.getCbu());

        try {
            financiacionTarjetaDTO.setSimulacionPlanVDTO(consultaFinanciacionDAO
                    .simulacionFinanciacionPlanV(simulacionPlanVRequestDTO, sesionCliente.getCliente()));
            financiacionTarjetaDTO.setInformacionPlanV(null);
            respuesta = respuestaFactory.crearRespuestaOk(FinanciacionTarjetaDTO.class);
            respuesta.setRespuesta(financiacionTarjetaDTO);

        } catch (SimulacionDAOException simExc) {
            LOGGER.warn(ERROR_WS_SIM, simExc);
            Mensaje mensajeWarningSimulacion = mensajeDAO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.SIMULACION_FINANCIACION_TARJETA_ERROR);
            respuesta = respuestaFactory.crearRespuestaError(FinanciacionTarjetaDTO.class,
                    mensajeWarningSimulacion.getMensaje(), null);
        } catch (DAOException e) {
            LOGGER.error(ERROR_WS_SIM, e);
            Mensaje mensajeWarningSimulacion = mensajeDAO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.SIMULACION_FINANCIACION_TARJETA_ERROR);
            respuesta = respuestaFactory.crearRespuestaError(FinanciacionTarjetaDTO.class,
                    mensajeWarningSimulacion.getMensaje(), null);
        }

        return respuesta;
    }

    /**
     * Es importe minimo menor que importe minimo.
     *
     * @param financiacionTarjetaView
     *            the financiacion tarjeta view
     * @return true, if successful
     */
    private boolean esImporteMenorQueImporteMinimo(FinanciacionTarjetaView financiacionTarjetaView) {
        String importe = financiacionTarjetaView.getImporteFinanciar();
        String importeMinimo = financiacionTarjetaView.getImporteMinimoFinanciar();
        if (importe != null && importeMinimo != null
                && (new BigDecimal(importe).compareTo(formatearImporte(importeMinimo)) == -1)) {
            LOGGER.debug(WARNING_LIMITE_IMPORTES);
            return true;
        }
        return false;
    }

    /**
     * Es importe mayor que importe maximo.
     *
     * @param financiacionTarjetaView
     *            the financiacion tarjeta view
     * @return true, if successful
     */
    private boolean esImporteMayorQueImporteMaximo(FinanciacionTarjetaView financiacionTarjetaView) {
        String importe = financiacionTarjetaView.getImporteFinanciar();
        String importeMaximo = financiacionTarjetaView.getImporteMaximoFinanciar();
        if (importe != null && importeMaximo != null
                && (new BigDecimal(importe).compareTo(formatearImporte(importeMaximo)) == 1)) {
            LOGGER.debug(WARNING_LIMITE_IMPORTES);
            return true;
        }
        return false;
    }

    /**
     * Formatear importe. Elimina el punto de la unidad de mil y cambia la coma
     * de los decimales por el punto.
     *
     * @param importe
     *            the importe
     * @return the double
     */
    public static BigDecimal formatearImporte(String importe) {
        if ("".equalsIgnoreCase(importe.replaceAll(".", ""))) {
            String imp = importe.replace(".", "");
            return new BigDecimal(imp.replace(",", "."));
        } else {
            return new BigDecimal(importe.replaceAll(".", "").replace(",", "."));
        }
    }

}

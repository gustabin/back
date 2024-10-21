/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.dao.StopDebitTarjetasDAO;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.DatosStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.RecargaTarjetaBO;
import ar.com.santanderrio.obp.servicios.tarjetas.constants.ConstantsTR;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.RecargaTarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobanteRecargaTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobanteRecargaTarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosRecargaTREntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FormaDePagoTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitOut;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.TarjetaRecargableEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ComprobanteRecargaTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ComprobanteRecargaTarjetaView.TipoComprobanteRecarga;

/**
 * The Class RecargaTarjetaBOImpl.
 */
@Component("recargaTarjetaBO")
public class RecargaTarjetaBOImpl implements RecargaTarjetaBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RecargaTarjetaBOImpl.class);

    /** The mensaje dao. */
    @Autowired
    private MensajeDAO mensajeDao;

    /** The Constant TIME_OUT. */
    private static final String TIME_OUT = "TIME_OUT";

    /** The Constant CODIGO_RETORNO_SALDO_INSUF_CTA_CTE. */
    private static final String CODIGO_RETORNO_SALDO_INSUF_CTA_CTE = "10000515";

    /** The Constant CODIGO_RETORNO_SALDO_INSUF_CAJA_AHORRO. */
    private static final String CODIGO_RETORNO_SALDO_INSUF_CAJA_AHORRO = "10002122";

    /** The Constant CODIGO_RETORNO_ERROR_AGENDAR_FECHA. */
    private static final String CODIGO_RETORNO_ERROR_AGENDAR_FECHA = "10000102";

    /** The Constant CODIGO_RETORNO_ERROR_AGENDAR_FECHA_1. */
    private static final String CODIGO_RETORNO_ERROR_AGENDAR_FECHA_1 = "10000098";

    /** The Constant TIPOCTA_VISA. */
    private static final String TIPOCTA_VISA = "07";

    /** The session parametros. */
    @Autowired
    private SesionParametros sessionParametros;

    /** The recarga tarjeta DAO. */
    @Autowired
    private RecargaTarjetaDAO recargaTarjetaDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The cuenta manager. */
    @Autowired
    private CuentaManager cuentaManager;

    /** The stop debit tarjetas DAO. */
    @Autowired
    private StopDebitTarjetasDAO stopDebitTarjetasDAO;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.RecargaTarjetaBO#recargar(
     * ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosRecargaTREntity,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<ComprobanteRecargaTarjetaDTO> recargar(DatosRecargaTREntity datosRecargaTR, Cliente cliente) {
        try {
            String visaCode = ISBANStringUtils.mascaraTarjetaCredito(datosRecargaTR.getNroTarjeta(), TIPOCTA_VISA);
            LOGGER.info("Inicia recarga de tarjeta recargable {} !", visaCode);
            ComprobanteRecargaTarjetaEntity devolucionServicio = recargaTarjetaDAO.recargar(cliente, datosRecargaTR);
            Respuesta<ComprobanteRecargaTarjetaDTO> respuesta = respuestaRecargaOk(devolucionServicio, datosRecargaTR);
            LOGGER.debug("Respuesta recargaTarjetas : {}.", respuesta);
            return respuesta;
        } catch (DAOException e) {
            return respuestaRecargaTarjetaError(e, datosRecargaTR);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.RecargaTarjetaBO#
     * bajaProgramacion(ar.com.santanderrio.obp.servicios.tarjetas.entities.
     * DatosRecargaTREntity,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<ComprobanteRecargaTarjetaDTO> bajaProgramacion(DatosRecargaTREntity datosRecargaTR,
            Cliente cliente) throws ServiceException {
        try {
            String visaCode = ISBANStringUtils.mascaraTarjetaCredito(datosRecargaTR.getNroTarjeta(), TIPOCTA_VISA);
            LOGGER.info("Inicia BAJA de tarjeta recargable {} !", visaCode);
            datosRecargaTR.setFrecuencia(FormaDePagoTarjetaEnum.POR_CAJA.getCodigo());

            TarjetaRecargableEntity trEntity = obtenerTarjetaRecargableCommonEntity(datosRecargaTR);
            trEntity.setTipoCuentaDebito(ConstantsTR.TXT_00);
            trEntity.setSucursalCuentaDebito(ConstantsTR.TXT_00);
            trEntity.setNroCuentaDebito(ConstantsTR.TXT_00);
            ComprobanteRecargaTarjetaEntity devolucionServicio = recargaTarjetaDAO.programarRecarga(cliente, trEntity);
            Respuesta<ComprobanteRecargaTarjetaDTO> respuesta = respuestaBajaModificarAgendarRecargaOK(
                    devolucionServicio, datosRecargaTR);
            LOGGER.debug("Respuesta recargaTarjetas : {}.", respuesta);
            return respuesta;
        } catch (DAOException e) {
            return respuestaBajaModificarAgendarRecargaNoOK();
        }
    }

    /**
     * Respuesta recarga tarjeta error.
     *
     * @param error
     *            the error
     * @param datosRecargaTR
     *            the datos recarga TR
     * @return the respuesta
     */
    private Respuesta<ComprobanteRecargaTarjetaDTO> respuestaRecargaTarjetaError(DAOException error,
            DatosRecargaTREntity datosRecargaTR) {
        String tarjeta = ISBANStringUtils.mascaraTarjetaCredito(datosRecargaTR.getNroTarjeta(), TIPOCTA_VISA);
        if (CODIGO_RETORNO_SALDO_INSUF_CAJA_AHORRO.equals(error.getMessage())
                || CODIGO_RETORNO_SALDO_INSUF_CTA_CTE.equals(error.getMessage())) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SALDO_INSUFICIENTE_RECARGA,
                    CodigoMensajeConstantes.MENSAJE_RECARGA_SALDO_INSUFICIENTE);
        } else if (TIME_OUT.equals(error.getErrorCode())) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_TIME_OUT_RECARGA_TARJETA,
                    CodigoMensajeConstantes.MENSAJE_RECARGA_TIME_OUT);
        } else if (!sessionParametros.getContador().permiteReintentar()) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTOS_AGOTADOS,
                    CodigoMensajeConstantes.MENSAJE_FEEDBACK_TRES_REINTENTOS_RECARGA, tarjeta);
        } else {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_RECARGA,
                    CodigoMensajeConstantes.MENSAJE_RECARGA_TARJETA, tarjeta);
        }
    }

    /**
     * Respuesta agendar tarjeta error.
     *
     * @param error
     *            the error
     * @param datosRecargaTR
     *            the datos recarga TR
     * @return the respuesta
     */
    private Respuesta<ComprobanteRecargaTarjetaDTO> respuestaAgendarTarjetaError(DAOException error,
            DatosRecargaTREntity datosRecargaTR) {
        String tarjeta = ISBANStringUtils.mascaraTarjetaCredito(datosRecargaTR.getNroTarjeta(), TIPOCTA_VISA);
        if (CODIGO_RETORNO_ERROR_AGENDAR_FECHA.equals(error.getErrorCode())
                || CODIGO_RETORNO_ERROR_AGENDAR_FECHA_1.equals(error.getErrorCode())) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_GENERICO_RECARGA,
                    CodigoMensajeConstantes.MENSAJE_AGENDAR_RECARGA_ERRO_FECHA);
        } else if (TIME_OUT.equals(error.getErrorCode())) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_TIME_OUT_RECARGA_TARJETA,
                    CodigoMensajeConstantes.MENSAJE_AGENDAR_RECARGA_NO_OK);
        } else {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_RECARGA,
                    CodigoMensajeConstantes.MENSAJE_AGENDAR_RECARGA_NO_OK, tarjeta);
        }
    }

    /**
     * Respuesta stop debit agendar tarjeta error.
     *
     * @param datosRecargaTR
     *            the datos recarga TR
     * @return the respuesta
     */
    private Respuesta<ComprobanteRecargaTarjetaDTO> respuestaStopDebitAgendarTarjetaError(
            DatosRecargaTREntity datosRecargaTR) {
        String tarjeta = ISBANStringUtils.mascaraTarjetaCredito(datosRecargaTR.getNroTarjeta(), TIPOCTA_VISA);
        return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_RECARGA,
                CodigoMensajeConstantes.MENSAJE_STOP_DEBIT_RECARGA_NO_OK, tarjeta);

    }

    /**
     * Respuesta modificar agendar tarjeta error.
     *
     * @param error
     *            the error
     * @param datosRecargaTR
     *            the datos recarga TR
     * @return the respuesta
     */
    private Respuesta<ComprobanteRecargaTarjetaDTO> respuestaModificarAgendarTarjetaError(DAOException error,
            DatosRecargaTREntity datosRecargaTR) {
        String tarjeta = ISBANStringUtils.mascaraTarjetaCredito(datosRecargaTR.getNroTarjeta(), TIPOCTA_VISA);
        if (CODIGO_RETORNO_ERROR_AGENDAR_FECHA.equals(error.getMessage())
                || CODIGO_RETORNO_ERROR_AGENDAR_FECHA_1.equals(error.getMessage())) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_GENERICO_RECARGA,
                    CodigoMensajeConstantes.MENSAJE_AGENDAR_RECARGA_ERRO_FECHA);
        } else if (TIME_OUT.equals(error.getMessage())) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_TIME_OUT_RECARGA_TARJETA,
                    CodigoMensajeConstantes.MENSAJE_MODIFICAR_AGENDAR_RECARGA_NO_OK);
        } else {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_RECARGA,
                    CodigoMensajeConstantes.MENSAJE_MODIFICAR_AGENDAR_RECARGA_NO_OK, tarjeta);
        }
    }

    /**
     * Respuesta comprobante agendar OK.
     *
     * @param devolucionServicio
     *            the devolucion servicio
     * @param datosRecargaTR
     *            the datos recarga TR
     * @return the comprobante recarga tarjeta DTO
     */
    private ComprobanteRecargaTarjetaDTO respuestaComprobanteAgendarOK(
            ComprobanteRecargaTarjetaEntity devolucionServicio, DatosRecargaTREntity datosRecargaTR) {
        ComprobanteRecargaTarjetaDTO comprobante = new ComprobanteRecargaTarjetaDTO();

        comprobante
                .setNumeroTarjeta(ISBANStringUtils.mascaraTarjetaCredito(datosRecargaTR.getNroTarjeta(), TIPOCTA_VISA));
        comprobante.setMonto(ISBANStringUtils.formatearSaldo(new BigDecimal(datosRecargaTR.getImporte().replace("\u00A0", ""))));
        comprobante.setNroCuentaOrigen(datosRecargaTR.getNroCuentaOrigen());
        comprobante.setTipoCuentaOrigen(datosRecargaTR.getTipoCuentaOrigen());
        comprobante.setFechaHora(datosRecargaTR.getFechaDeRecarga());
        comprobante.setFechaInicio(datosRecargaTR.getFechaInicio());
        comprobante.setFechaDeProxRecarga(devolucionServicio.getFechaDeProxRecarga());
        comprobante.setFrecuencia(datosRecargaTR.getFrecuencia());

        comprobante.setImporteAcreditado(ISBANStringUtils.formatearSaldo(new BigDecimal(datosRecargaTR.getImporte().replace("\u00A0", ""))));

        comprobante.setNroComprobante(devolucionServicio.getNroComprobante());
        comprobante.setLegalesSEO(
                mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_RECARGA_LEGALES_SEO).getMensaje());
        Date fechaHoraActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        String fechaHoraActualFormateada = sdf.format(fechaHoraActual);
        comprobante.setFechaHora(fechaHoraActualFormateada);
        return comprobante;
    }

    /**
     * Respuesta agendar recarga OK.
     *
     * @param devolucionServicio
     *            the devolucion servicio
     * @param datosRecargaTR
     *            the datos recarga TR
     * @return the respuesta
     */
    private Respuesta<ComprobanteRecargaTarjetaDTO> respuestaAgendarRecargaOK(
            ComprobanteRecargaTarjetaEntity devolucionServicio, DatosRecargaTREntity datosRecargaTR) {
        ComprobanteRecargaTarjetaDTO comprobante = respuestaComprobanteAgendarOK(devolucionServicio, datosRecargaTR);
        comprobante.setMensaje(MessageFormat.format(
                mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_AGENDAR_RECARGA_OK).getMensaje(),
                comprobante.getNumeroTarjeta(), comprobante.getMonto(), comprobante.getFechaInicio()));
        return respuestaFactory.crearRespuestaOk(ComprobanteRecargaTarjetaDTO.class, comprobante);
    }

    /**
     * Respuesta modificar agendar recarga OK.
     *
     * @param devolucionServicio
     *            the devolucion servicio
     * @param datosRecargaTR
     *            the datos recarga TR
     * @return the respuesta
     */
    private Respuesta<ComprobanteRecargaTarjetaDTO> respuestaModificarAgendarRecargaOK(
            ComprobanteRecargaTarjetaEntity devolucionServicio, DatosRecargaTREntity datosRecargaTR) {
        ComprobanteRecargaTarjetaDTO comprobante = respuestaComprobanteAgendarOK(devolucionServicio, datosRecargaTR);
        comprobante.setMensaje(MessageFormat.format(mensajeDao
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_MODIFICAR_AGENDAR_RECARGA_OK).getMensaje(),
                comprobante.getNumeroTarjeta(), comprobante.getMonto()));

        return respuestaFactory.crearRespuestaOk(ComprobanteRecargaTarjetaDTO.class, comprobante);
    }

    /**
     * Respuesta stop debit agendar recarga OK.
     *
     * @param devolucionServicio
     *            the devolucion servicio
     * @param datosRecargaTR
     *            the datos recarga TR
     * @return the respuesta
     */
    private Respuesta<ComprobanteRecargaTarjetaDTO> respuestaStopDebitAgendarRecargaOK(
            ComprobanteRecargaTarjetaEntity devolucionServicio, DatosRecargaTREntity datosRecargaTR) {
        ComprobanteRecargaTarjetaDTO comprobante = respuestaComprobanteAgendarOK(devolucionServicio, datosRecargaTR);
        comprobante.setMensaje(MessageFormat.format(
                mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_STOP_DEBIT_RECARGA_OK).getMensaje(),
                comprobante.getNumeroTarjeta()));

        return respuestaFactory.crearRespuestaOk(ComprobanteRecargaTarjetaDTO.class, comprobante);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.RecargaTarjetaBO#
     * programarRecarga(ar.com.santanderrio.obp.servicios.tarjetas.entities.
     * DatosRecargaTREntity,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<ComprobanteRecargaTarjetaDTO> programarRecarga(DatosRecargaTREntity datosRecargaTR,
            Cliente cliente) throws ServiceException {
        try {
            String visaCode = ISBANStringUtils.mascaraTarjetaCredito(datosRecargaTR.getNroTarjeta(), TIPOCTA_VISA);
            LOGGER.info("Inicia BAJA de tarjeta recargable {} !", visaCode);
            TarjetaRecargableEntity trEntity = obtenerTarjetaRecargableCommonEntity(datosRecargaTR);
            setearDatosComunesAgendarModificar(trEntity, datosRecargaTR);
            ComprobanteRecargaTarjetaEntity nroComprobante = recargaTarjetaDAO.programarRecarga(cliente, trEntity);
            nroComprobante.setSucursalCuentaOrigen(datosRecargaTR.getSucursalCuentaOrigen());
            return respuestaAgendarRecargaOK(nroComprobante, datosRecargaTR);
        } catch (DAOException e) {
            return respuestaAgendarTarjetaError(e, datosRecargaTR);
        }
    }

    /**
     * Setear datos comunes agendar modificar.
     *
     * @param trEntity
     *            the tr entity
     * @param datosRecargaTR
     *            the datos recarga TR
     */
    private void setearDatosComunesAgendarModificar(TarjetaRecargableEntity trEntity,
            DatosRecargaTREntity datosRecargaTR) {
        String tipoCuentaOrigen = ConstantsTR.getTipoCuentaOrigen(datosRecargaTR.getTipoCuentaOrigenAbreviatura());
        trEntity.setSucursalCuentaDebito(datosRecargaTR.getSucursalCuentaOrigen());
        trEntity.setNroCuentaDebito(datosRecargaTR.getNroCuentaOrigen());
        trEntity.setTipoCuentaDebito(tipoCuentaOrigen);
    }

    /**
     * Obtener tarjeta recargable common entity.
     *
     * @param datosRecargaTR
     *            the datos recarga TR
     * @return the tarjeta recargable entity
     * @throws ServiceException
     *             the service exception
     */
    private TarjetaRecargableEntity obtenerTarjetaRecargableCommonEntity(DatosRecargaTREntity datosRecargaTR)
            throws ServiceException {
        TarjetaRecargableEntity trEntity = new TarjetaRecargableEntity();
        trEntity.setTipoTarjeta(TIPOCTA_VISA);
        trEntity.setNroCuentaTarjeta(datosRecargaTR.getNroCuentaDestino());

        Cuenta cuenta = (Cuenta) cuentaManager.obtenerCuentaById(datosRecargaTR.getNroCuentaOrigenSinFormato());
        trEntity.setNroFirmante(cuenta.getNroOrdenFirmante());
        int codigoFrecuencia = TarjetaUtils.codigoFrecunciaRecargable(datosRecargaTR.getFrecuencia(),
                ISBANStringUtils.formatearFecha(datosRecargaTR.getFechaInicio()));
        trEntity.setSucursalCuentaDebito(cuenta.getNroSucursal());
        trEntity.setFormaPago(String.valueOf(codigoFrecuencia));
        trEntity.setImporteAgendamiento(datosRecargaTR.getImporte());
        try {
            trEntity.setFechaProximoAgendamiento(ISBANStringUtils.parseFecha(datosRecargaTR.getFechaInicio()));
        } catch (ParseException e) {
            LOGGER.debug("ERROR PARSEAR FECHA ", e);
            trEntity.setFechaProximoAgendamiento(null);
        }

        return trEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.RecargaTarjetaBO#
     * modificarRecarga(ar.com.santanderrio.obp.servicios.tarjetas.entities.
     * DatosRecargaTREntity,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<ComprobanteRecargaTarjetaDTO> modificarRecarga(DatosRecargaTREntity datosRecargaTR,
            Cliente cliente) throws ServiceException {
        try {
            TarjetaRecargableEntity trEntity = obtenerTarjetaRecargableCommonEntity(datosRecargaTR);
            setearDatosComunesAgendarModificar(trEntity, datosRecargaTR);
            ComprobanteRecargaTarjetaEntity nroComprobante = recargaTarjetaDAO.programarRecarga(cliente, trEntity);
            return respuestaModificarAgendarRecargaOK(nroComprobante, datosRecargaTR);
        } catch (DAOException e) {
            return respuestaModificarAgendarTarjetaError(e, datosRecargaTR);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.bo.RecargaTarjetaBO#stopDebit(
     * ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosRecargaTREntity,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<ComprobanteRecargaTarjetaDTO> stopDebit(DatosRecargaTREntity datosRecargaTR, Cliente cliente)
            throws ServiceException {
        try {
            TarjetaRecargableEntity trEntity = obtenerTarjetaRecargableCommonEntity(datosRecargaTR);
            setearDatosComunesAgendarModificar(trEntity, datosRecargaTR);

            DatosStopDebit datos = new DatosStopDebit();
            datos.setCodigoServicio("009");
            datos.setNroCuenta(datosRecargaTR.getNroCuentaOrigen());
            datos.setNroPartida(StringUtils.leftPad(datosRecargaTR.getNroCuentaDestino(), 18, "0"));
            datos.setSucursalCuenta(datosRecargaTR.getSucursalCuentaOrigen());
            datos.setTipoCuenta("0" + tipoCta2tipoCtaHost(datosRecargaTR.getTipoCuentaOrigenAbreviatura()));
            StopDebitOut nroComprobante = stopDebitTarjetasDAO.realizarStopDebitTarjeta(cliente, datos);
            if (nroComprobante.getErrorCode() != 0) {
                return respuestaStopDebitAgendarTarjetaError(datosRecargaTR);
            }
            ComprobanteRecargaTarjetaEntity comprobante = new ComprobanteRecargaTarjetaEntity();
            comprobante.setNroComprobante(nroComprobante.getNroDeComprobante());
            return respuestaStopDebitAgendarRecargaOK(comprobante, datosRecargaTR);
        } catch (DAOException e) {
            return respuestaStopDebitAgendarTarjetaError(datosRecargaTR);
        }
    }

    /**
     * Tipo cta 2 tipo cta host.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the string
     */
    private String tipoCta2tipoCtaHost(String tipoCuenta) {
        if ("CU".equals(tipoCuenta) || "CUP".equals(tipoCuenta)) {
            return "0";
        }
        if ("CCP".equals(tipoCuenta)) {
            return "0";
        }
        if ("CCD".equals(tipoCuenta)) {
            return "3";
        }
        if ("CAP".equals(tipoCuenta)) {
            return "1";
        }
        if ("CAD".equals(tipoCuenta)) {
            return "4";
        }

        return "?";

    }

    /**
     * View to entity.
     *
     * @param comprobanteRecargaTarjetaView
     *            the comprobante recarga tarjeta view
     * @return the comprobante recarga tarjeta entity
     */
    private ComprobanteRecargaTarjetaEntity viewToEntity(ComprobanteRecargaTarjetaView comprobanteRecargaTarjetaView) {
        ComprobanteRecargaTarjetaEntity entity = new ComprobanteRecargaTarjetaEntity();
        entity.setNroCuentaOrigen(comprobanteRecargaTarjetaView.getNroCuentaOrigen());
        entity.setSucursalCuentaOrigen(comprobanteRecargaTarjetaView.getSucursalCuentaOrigen());
        entity.setTipoCuentaOrigen(comprobanteRecargaTarjetaView.getTipoCuentaOrigen());
        entity.setFechaInicio(comprobanteRecargaTarjetaView.getFechaInicio());
        entity.setFechaDeProxRecarga(comprobanteRecargaTarjetaView.getFechaProxRecarga());
        entity.setFrecuencia(comprobanteRecargaTarjetaView.getFrecuencia());
        entity.setNroComprobante(comprobanteRecargaTarjetaView.getNroComprobante());
        entity.setImporteAcreditado(comprobanteRecargaTarjetaView.getImporteAcreditado());
        entity.setNroComprobante(comprobanteRecargaTarjetaView.getNroComprobante());
        entity.setNumeroTarjeta(comprobanteRecargaTarjetaView.getNumeroTarjeta());
        return entity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.RecargaTarjetaBO#
     * generarComprobanteRecarga(ar.com.santanderrio.obp.servicios.tarjetas.web.
     * view.ComprobanteRecargaTarjetaView)
     */
    @Override
    public Respuesta<Reporte> generarComprobanteRecarga(ComprobanteRecargaTarjetaView comprobanteRecargaTarjetaView) {
        Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
        Reporte reporte = null;

        if (TipoComprobanteRecarga.AGENDAR == comprobanteRecargaTarjetaView.getTipoComprobanteRecarga()) {
            reporte = recargaTarjetaDAO.generarComprobanteRecargaAgendar(viewToEntity(comprobanteRecargaTarjetaView));
        }
        if (TipoComprobanteRecarga.MODIFICAR_AGENDAR == comprobanteRecargaTarjetaView.getTipoComprobanteRecarga()) {
            reporte = recargaTarjetaDAO
                    .generarComprobanteRecargaModificarAgendar(viewToEntity(comprobanteRecargaTarjetaView));
        }
        if (TipoComprobanteRecarga.STOP_DEBIT == comprobanteRecargaTarjetaView.getTipoComprobanteRecarga()) {
            reporte = recargaTarjetaDAO
                    .generarComprobanteRecargaStopDebitAgendar(viewToEntity(comprobanteRecargaTarjetaView));
        }
        if (TipoComprobanteRecarga.RECARGAR == comprobanteRecargaTarjetaView.getTipoComprobanteRecarga()) {
            reporte = recargaTarjetaDAO.generarComprobanteRecarga(comprobanteRecargaTarjetaView);
        }
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(reporte);
        return respuesta;
    }

    /**
     * Respuesta baja modificar agendar recarga OK.
     *
     * @param devolucionServicio
     *            the devolucion servicio
     * @param datosRecargaTR
     *            the datos recarga TR
     * @return the respuesta
     */
    private Respuesta<ComprobanteRecargaTarjetaDTO> respuestaBajaModificarAgendarRecargaOK(
            ComprobanteRecargaTarjetaEntity devolucionServicio, DatosRecargaTREntity datosRecargaTR) {
        ComprobanteRecargaTarjetaDTO comprobante = respuestaComprobanteAgendarOK(devolucionServicio, datosRecargaTR);
        comprobante.setMensaje(MessageFormat.format(mensajeDao
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BAJA_AGENDAR_RECARGA_OK).getMensaje(),
                comprobante.getNumeroTarjeta()));

        return respuestaFactory.crearRespuestaOk(ComprobanteRecargaTarjetaDTO.class, comprobante);
    }

    /**
     * Respuesta baja modificar agendar recarga no OK.
     *
     * @return the respuesta
     */
    private Respuesta<ComprobanteRecargaTarjetaDTO> respuestaBajaModificarAgendarRecargaNoOK() {
        return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_RECARGA,
                CodigoMensajeConstantes.MENSAJE_BAJA_AGENDAR_RECARGA_NO_OK);
    }

    /**
     * Respuesta recarga ok.
     *
     * @param devolucionServicio
     *            the devolucion servicio
     * @param datosRecargaTR
     *            the datos recarga TR
     * @return the respuesta
     */
    private Respuesta<ComprobanteRecargaTarjetaDTO> respuestaRecargaOk(
            ComprobanteRecargaTarjetaEntity devolucionServicio, DatosRecargaTREntity datosRecargaTR) {
        ComprobanteRecargaTarjetaDTO comprobante = new ComprobanteRecargaTarjetaDTO();
        comprobante
                .setNumeroTarjeta(ISBANStringUtils.mascaraTarjetaCredito(datosRecargaTR.getNroTarjeta(), TIPOCTA_VISA));
        comprobante.setMonto(ISBANStringUtils.formatearSaldo(new BigDecimal(datosRecargaTR.getImporte())));
        comprobante.setNroCuentaOrigen(datosRecargaTR.getNroCuentaOrigen());
        comprobante.setTipoCuentaOrigen(datosRecargaTR.getTipoCuentaOrigen());
        comprobante.setFechaHora(datosRecargaTR.getFechaDeRecarga());

        String comisionTotal = valorMonetarioFormateado(devolucionServicio.getComisionTotal(), 2);
        comprobante.setComisionTotal(comisionTotal);

        String comision = valorMonetarioFormateado(devolucionServicio.getComision(), 2);
        comprobante.setComision(comision);

        String iva = valorMonetarioFormateado(devolucionServicio.getIva(), 2);
        comprobante.setIva(iva);

        String importeAcreditado = valorMonetarioFormateado(devolucionServicio.getImporteAcreditado(), 2);
        comprobante.setImporteAcreditado(importeAcreditado);

        comprobante.setNroComprobante(devolucionServicio.getNroComprobante());
        comprobante.setMensaje(MessageFormat.format(
                mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_RECARGA_OK).getMensaje(),
                comprobante.getNumeroTarjeta(), comprobante.getMonto()));
        comprobante.setLegalesSEO(
                mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_RECARGA_LEGALES_SEO).getMensaje());
        Date fechaHoraActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        String fechaHoraActualFormateada = sdf.format(fechaHoraActual);
        comprobante.setFechaHora(fechaHoraActualFormateada);
        return respuestaFactory.crearRespuestaOk(ComprobanteRecargaTarjetaDTO.class, comprobante);
    }

    /**
     * Valor monetario formateado.
     *
     * @param val
     *            the val
     * @param cantDecimales
     *            the cant decimales
     * @return the string
     */
    private String valorMonetarioFormateado(String val, int cantDecimales) {
        String valor = "";
        String valorString = "";
        try {
            valor = ISBANStringUtils.convertirStrToBigDecimal(val, cantDecimales).toString();
            valorString = valor.replaceAll("\\.", "\\,");
            valorString = ISBANStringUtils.agregadorDecimales(valorString);
            valorString = ISBANStringUtils.agregadorPuntoDivisor(valorString);
        } catch (ImporteConvertException e) {
            LOGGER.debug("ERROR", e);
        }
        return valorString;
    }

    /**
     * Obtiene el tipo de cuenta de un destinatario agendado.
     *
     * @param tipoCuentaDestinatario
     *            the tipo cuenta destinatario
     * @return the string
     */
    public String obtenerTipoCuenta(Integer tipoCuentaDestinatario) {
        switch (tipoCuentaDestinatario) {
        case 0:
            return TipoCuenta.CUENTA_CORRIENTE_PESOS.getDescripcionConMoneda();
        case 1:
            return TipoCuenta.CAJA_AHORRO_PESOS.getDescripcionConMoneda();
        case 2:
            return TipoCuenta.CUENTA_UNICA.getDescripcionConMoneda();
        case 3:
            return TipoCuenta.CUENTA_CORRIENTE_DOLARES.getDescripcionConMoneda();
        default:
            return TipoCuenta.CAJA_AHORRO_DOLARES.getDescripcionConMoneda();
        }
    }

    /**
     * Gets the stop debit tarjetas DAO.
     *
     * @return the stop debit tarjetas DAO
     */
    public StopDebitTarjetasDAO getStopDebitTarjetasDAO() {
        return stopDebitTarjetasDAO;
    }

    /**
     * Sets the stop debit tarjetas DAO.
     *
     * @param stopDebitTarjetasDAO
     *            the new stop debit tarjetas DAO
     */
    public void setStopDebitTarjetasDAO(StopDebitTarjetasDAO stopDebitTarjetasDAO) {
        this.stopDebitTarjetasDAO = stopDebitTarjetasDAO;
    }
}

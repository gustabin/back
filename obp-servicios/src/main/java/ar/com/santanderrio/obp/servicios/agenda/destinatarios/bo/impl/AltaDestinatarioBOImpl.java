/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.CasuisticaAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosCliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * BO para alta de destinatario.
 */
@Component
public class AltaDestinatarioBOImpl extends DestinatarioCommon implements AltaDestinatarioBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AltaDestinatarioBOImpl.class);

    /** The Constant COD_RETORNO_EXTENDIDO_CEROS. */
    private static final String COD_RETORNO_EXTENDIDO_CEROS = "00000000";

    /** The Constant COD_RETORNO_EXTENDIDO_CERO. */
    private static final String COD_RETORNO_EXTENDIDO_CERO = "0";

    /** The Constant DOCE. */
    private static final int DOCE = 12;

    /** The Constant STRING_CERO. */
    private static final String STRING_CERO = "0";

    /** The Constant CUATRO. */
    private static final int CUATRO = 4;

    /** The Constant CINCUENTA. */
    private static final int CINCUENTA = 50;

    /** The Constant DOS. */
    private static final int DOS = 2;

    /** The Constant TREINTA. */
    private static final int TREINTA = 30;

    /** The Constant SESENTAYCUATRO. */
    private static final int SESENTAYCUATRO = 64;

    /** The Constant CIEN. */
    private static final int CIEN = 100;

    /** The Constant VEINTE. */
    private static final int VEINTE = 20;

    /** The Constant COD_ERROR_TIPO_CUENTA_INVALIDA. */
    private static final String COD_ERROR_TIPO_CUENTA_INVALIDA = "10014001";

    /** The Constant COD_ERROR_CUENTA_AGENDADA. */
    private static final String COD_ERROR_CUENTA_AGENDADA = "10011634";

    /** The Constant COD_ERROR_CUENTA_AGENDADA. */
    private static final String COD_ERROR_CBU_AGENDADO = "10011636";

    /** The Constant COD_ERROR_CUENTA_INVALIDA. */
    private static final String COD_ERROR_CUENTA_INVALIDA = "10010091";

    /** The Constant COD_ERROR_CUENTA_INEXISTENTE. */
    private static final String COD_ERROR_CUENTA_INEXISTENTE = "10000054";

    /** The Constant COD_ERROR_CUENTA_INEXISTENTE. */
    private static final String COD_ERROR_CUENTA_NO_CORRESPONDE_SANTANDER = "10000038";

    /** The Constant ERROR_DAO. */
    private static final String ERROR_DAO = "Error en la consulta al DAO.";

    /** The Constant ERROR_BO. */
    private static final String ERROR_BO = "Error inesperado en el BO.";

    /** The Constant MENSAJE_FEEDBACK_OK. */
    private static final String MENSAJE_FEEDBACK_OK = "1454";

    /** The Constant COD_CBU_EXISTENTE. */
    private static final String CODIGO_CBU_EXISTENTE = "11636";

    /** The Constant CODIGO_CBU_ENTIDAD_INEXISTENTE. */
    private static final String CODIGO_CBU_ENTIDAD_INEXISTENTE = "11639";

    /** The Constant CODIGO_CBU_ENTIDAD_NO_VALIDA. */
    private static final String CODIGO_CBU_ENTIDAD_NO_VALIDA = "11640";

    /** The Constant CODIGO_CBU_CUENTA_NO_VALIDA. */
    private static final String CODIGO_CBU_CUENTA_NO_VALIDA = "11641";

    /** The Constant CODIGO_CBU_ERROR_VALIDACION_1. */
    private static final String CODIGO_CBU_ERROR_VALIDACION_1 = "11642";

    /** The Constant CODIGO_CBU_ERROR_VALIDACION_2. */
    private static final String CODIGO_CBU_ERROR_VALIDACION_2 = "11643";

    /** The Constant CODIGO_ERROR_INICIO_1. */
    private static final String CODIGO_ERROR_INICIO_1 = "1";

    /** The Constant CODIGO_ERROR_INICIO_2. */
    private static final String CODIGO_ERROR_INICIO_2 = "2";

    /** The Constant CODIGO_ERROR_INICIO_3. */
    private static final String CODIGO_ERROR_INICIO_3 = "3";

    /** The Constant CODIGO_SANTANDER. */
    private static final String CODIGO_SANTANDER = "072";

    /** The agenda destinatario DAO. */
    @Autowired
    private AgendaDestinatarioDAO agendaDestinatarioDAO;

    /** The casuistica. */
    @Autowired
    private CasuisticaAgendaDestinatarios casuistica;

    /** The casuistica alta. */
    @Autowired
    private CasuisticaAltaDestinatarioImpl casuisticaAlta;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion. */
    @Autowired
    private SesionAgendaDestinatarios sesionAgenda;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /**
     * Continua la configuracion del alta de un destinatario.
     *
     * @param cliente
     *            the cliente
     * @param numeroCuenta
     *            the numero cuenta
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the respuesta
     */
    @Override
    public Respuesta<ConfiguracionAltaDestinatarioDTO> continuarConfiguracionAltaDestinatario(Cliente cliente,
            String numeroCuenta, String tipoCuenta) {
        if (cuentaIngresadaEsPropia(cliente, numeroCuenta, tipoCuenta)) {
            return casuistica.respuestaErrorAltaConfiguracionEsCuentaPropia();
        }
        try {
            ValidacionCuentaInEntity entity = armarDatosEntrada(cliente, numeroCuenta, tipoCuenta);
            DatosCliente datosCliente = agendaDestinatarioDAO.validarCuenta(entity);
            if (datosCliente.getCodigoError() == 0) {
                return respuestaFactory.crearRespuestaOk(ConfiguracionAltaDestinatarioDTO.class,
                        new ConfiguracionAltaDestinatarioDTO(datosCliente));
            }
            return casuistica.crearRespuestaConfiguracion(datosCliente);
        } catch (DAOException e) {
            LOGGER.error("Error en servicio: {}", e);
            return casuistica.respuestaErrorAltaConfiguracionErrorServicio();
        }
    }

    /**
     * Confirma el alta de un destinatario Rio para la agenda.
     *
     * @param ip
     *            the ip
     * @param cliente
     *            the cliente
     * @param datosConfirmados
     *            the datosConfirmados
     * @param referenciaTitular
     *            the referencia titular
     * @param cuitCuil
     *            the cuit cuil
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    @Override
    public Respuesta<ComprobanteAltaDestinatarioDTO> confirmarAltaDestinatarioRio(String ip, Cliente cliente,
            ConfirmacionAltaDestinatarioView datosConfirmados, String referenciaTitular, String cuitCuil)
            throws BusinessException {
        try {
            AgendaDestinatarioInEntity inEntity = crearAgendaDestinatarioInEntityRio(cliente, datosConfirmados,
                    cuitCuil);

            if(CuentasBancaPrivadaUtil.isCuentaBP(inEntity.getSucursalCuentaDestinatario())) {
            	inEntity.setIsTransferenciaBP(Boolean.TRUE);
            }

            AgendaDestinatarioOutEntity outEntity = obtenerEntidadAgendaDestinatarioOutEntity(inEntity, ip);
            return casuistica.crearRespuesta(obtenerComprobanteAltaDTO(outEntity, referenciaTitular),
                    referenciaTitular);
        } catch (Exception e) {
            LOGGER.error(ERROR_BO, e);
            throw new BusinessException();
        }

    }

    /**
     * Confirma el alta de un destinatario a travÃ©s de CBU para la agenda.
     *
     * @param ip
     *            the ip
     * @param cliente
     *            the cliente
     * @param datosConfirmados
     *            the datosConfirmados
     * @param referenciaTitular
     *            the referencia titular
     * @param cuitCuil
     *            the cuit cuil
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    @Override
    public Respuesta<ComprobanteAltaDestinatarioDTO> confirmarAltaDestinatarioOtrosBancos(String ip, Cliente cliente,
            ConfirmacionAltaDestinatarioView datosConfirmados, String referenciaTitular, String cuitCuil)
            throws BusinessException {
        try {
            // Validacion cuit
            Respuesta<ComprobanteAltaDestinatarioDTO> validacionCuit = casuistica
                    .validarCuit(datosConfirmados.getNumeroDocumento());
            if (validacionCuit != null) {
                return validacionCuit;
            }
            AgendaDestinatarioInEntity inEntity = crearAgendaDestinatarioInEntityOtrosBancos(cliente, datosConfirmados);
            AgendaDestinatarioOutEntity outEntity = obtenerEntidadAgendaDestinatarioOutEntity(inEntity, ip);
            return casuistica.crearRespuestaOtrosBancos(
                    obtenerComprobanteAltaOtrosBancosDTO(outEntity, referenciaTitular), referenciaTitular);
        } catch (Exception e) {
            LOGGER.error(ERROR_BO, e);
            throw new BusinessException();
        }

    }

    /**
     * Confirma el alta de un destinatario para el envÃ­o de efectivo en la
     * agenda.
     *
     * @param ip
     *            the ip
     * @param cliente
     *            the cliente
     * @param datosConfirmados
     *            the datosConfirmados
     * @param cuitCuil
     *            the cuit cuil
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    @Override
    public Respuesta<ComprobanteAltaDestinatarioDTO> confirmarAltaDestinatarioEnvioEfectivo(String ip, Cliente cliente,
            ConfirmacionAltaDestinatarioView datosConfirmados, String cuitCuil) throws BusinessException {
        try {
            AgendaDestinatarioInEntity inEntity = crearAgendaDestinatarioInEntityEnvioEfectivo(cliente,
                    datosConfirmados);
            Respuesta<AgendaDestinatarioInEntity> resValidacion = respuestaFactory.validate(inEntity);
            if (resValidacion.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
                return casuistica.respuestaErrorServicios();
            }
            AgendaDestinatarioOutEntity outEntity = obtenerEntidadAgendaDestinatarioEnvioEfectivoOutEntity(inEntity,
                    ip);
            return casuisticaAlta.crearRespuestaEnvioEfectivo(outEntity);

        } catch (NullPointerException e) {
            LOGGER.error(ERROR_BO, e);
            throw new BusinessException();
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
     * AltaDestinatarioBO#confirmarAltaDestinatarioAlias(java.lang.String,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
     * ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.
     * ConfirmacionAltaDestinatarioView, java.lang.String, java.lang.String)
     */
    @Override
    public Respuesta<ComprobanteAltaDestinatarioDTO> confirmarAltaDestinatarioAlias(String ip, Cliente cliente,
            ConfirmacionAltaDestinatarioView datosConfirmados, String referenciaTitular, String cuitCuil)
            throws BusinessException {
        try {
            AgendaDestinatarioInEntity inEntity = crearAgendaDestinatarioInEntityAlias(cliente, datosConfirmados);
            if(CuentasBancaPrivadaUtil.isCuentaBP(inEntity.getSucursalCuentaDestinatario())) {
            	inEntity.setIsTransferenciaBP(Boolean.TRUE);
            }
            AgendaDestinatarioOutEntity outEntity = obtenerEntidadAgendaDestinatarioOutEntity(inEntity, ip);
            return casuistica.crearRespuesta(obtenerComprobanteAltaDTO(outEntity, referenciaTitular),
                    referenciaTitular);
        } catch (Exception e) {
            LOGGER.error(ERROR_BO, e);
            throw new BusinessException();
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
     * AltaDestinatarioBO#realizarAltaDestinatario(java.lang.String,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
     * ar.com.santanderrio.obp.servicios.transferencias.web.view.
     * TransferenciaView, java.lang.String, java.lang.String)
     */
    @Override
    public String realizarAltaDestinatario(String ip, Cliente cliente, TransferenciaView transferenciaView,
            String referenciaTitular, String cuitCuil) {
        AgendaDestinatarioInEntity inEntity = crearAgendaDestinatarioInEntityAliasFromTransferencia(cliente,
                transferenciaView);
        String mensaje = "";
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity = realizarAltaDestinatario(inEntity, ip, TipoOperacionACTAGEDESTEnum.ALTA);
        // TODO : maximilianos meterlo en un metodo y refactorizarlo si es
        // necesario
        if (outEntity.getCodigoRetornoExtendido().equals(COD_RETORNO_EXTENDIDO_CEROS)) {
            mensaje = MessageFormat.format(mensajeBO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.ALTA_MODIFICACION_DE_ALIAS_REALIDAZA_CON_EXITO)
                    .getMensaje(), transferenciaView.getAliasDestino());
            return mensaje;
        } else if (outEntity.getCodigoRetornoExtendido().equals(COD_ERROR_CBU_AGENDADO)
                || outEntity.getCodigoRetornoExtendido().equals(COD_ERROR_CUENTA_AGENDADA)) {
            outEntity = realizarAltaDestinatario(inEntity, ip, TipoOperacionACTAGEDESTEnum.MODIFICACION);
            if (outEntity.getCodigoRetornoExtendido().equals(COD_RETORNO_EXTENDIDO_CEROS)
                    || outEntity.getCodigoRetornoExtendido().equals(COD_RETORNO_EXTENDIDO_CERO)) {
                mensaje = MessageFormat.format(mensajeBO
                        .obtenerMensajePorCodigo(CodigoMensajeConstantes.ALTA_MODIFICACION_DE_ALIAS_REALIDAZA_CON_EXITO)
                        .getMensaje(), transferenciaView.getAliasDestino());
                return mensaje;
            }
        }

        return mensaje;
    }

    /**
     * Crear agenda destinatario in entity envio efectivo.
     *
     * @param cliente
     *            the cliente
     * @param datosConfirmados
     *            the datos confirmados
     * @return the agenda destinatario in entity
     */
    private AgendaDestinatarioInEntity crearAgendaDestinatarioInEntityEnvioEfectivo(Cliente cliente,
            ConfirmacionAltaDestinatarioView datosConfirmados) {
        AgendaDestinatarioInEntity agendaDestinatarioInEntity = new AgendaDestinatarioInEntity();
        agendaDestinatarioInEntity.setCliente(cliente);
        agendaDestinatarioInEntity.setTipoAgendaOcurrencia(TipoAgendaEnum.AGENDA_EXTRACCIONES.getCampo());
        agendaDestinatarioInEntity.setTipoCuentaDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 2));
        agendaDestinatarioInEntity
                .setSucursalCuentaDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 4));
        agendaDestinatarioInEntity.setNumeroCuentaDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 12));
        agendaDestinatarioInEntity.setCbuDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 22));
        agendaDestinatarioInEntity.setBancoDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 50));
        agendaDestinatarioInEntity
                .setTipoDocumentoDestinatario(obtenerTipoDocumento(datosConfirmados.getTipoDocumento()));

        agendaDestinatarioInEntity
                .setDocumentoDestinatario(StringUtils.leftPad(datosConfirmados.getNumeroDocumento(), 11, "0"));
        if (datosConfirmados.getReferencia() == null) {
            agendaDestinatarioInEntity.setDescripcionCuentaDestinatario(
                    StringUtils.leftPad(datosConfirmados.getNumeroDocumento(), 30, " "));
            agendaDestinatarioInEntity.setTitular(StringUtils.leftPad(datosConfirmados.getNumeroDocumento(), 64, " "));
        } else {
            agendaDestinatarioInEntity
                    .setDescripcionCuentaDestinatario(obtenerDescripcionCuenta(datosConfirmados.getReferencia()));
            agendaDestinatarioInEntity.setTitular(obtenerTitular(datosConfirmados.getReferencia()));
        }
        agendaDestinatarioInEntity
                .setCaracteristicasCuentaDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 10));
        agendaDestinatarioInEntity.setDireccionCorreo(obtenerDireccionCorreo(datosConfirmados.getDireccionCorreo()));
        agendaDestinatarioInEntity.setTelefonoDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 16));
        return agendaDestinatarioInEntity;
    }

    /**
     * Obtener tipo documento.
     *
     * @param tipoDocumento
     *            the tipo documento
     * @return the string
     */
    private String obtenerTipoDocumento(String tipoDocumento) {
        String res = null;
        if ("DNI".equals(tipoDocumento)) {
            res = "N ";
        } else if ("LC".equals(tipoDocumento)) {
            res = "C ";
        } else if ("LE".equals(tipoDocumento)) {
            res = "E ";
        } else if ("Pasaporte".equals(tipoDocumento)) {
            res = "P ";
        } else if ("CI".equals(tipoDocumento)) {
            res = "I ";
        }
        return res;
    }

    /**
     * Obtener entidad agenda destinatario envio efectivo out entity.
     *
     * @param inEntity
     *            the in entity
     * @param ip
     *            the ip
     * @return the agenda destinatario out entity
     */
    private AgendaDestinatarioOutEntity obtenerEntidadAgendaDestinatarioEnvioEfectivoOutEntity(
            AgendaDestinatarioInEntity inEntity, String ip) {
        try {
            return agendaDestinatarioDAO.eliminarUAgregar(inEntity, ip, TipoOperacionACTAGEDESTEnum.ALTA);
        } catch (DAOException e) {
            LOGGER.error(ERROR_DAO, e);
        }
        return null;
    }

    /**
     * Obtiene la entidad agenda destinatario out entity.
     *
     * @param inEntity
     *            the in entity
     * @param ip
     *            the ip
     * @return the agenda destinatario out entity
     */
    private AgendaDestinatarioOutEntity obtenerEntidadAgendaDestinatarioOutEntity(AgendaDestinatarioInEntity inEntity,
            String ip) {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        try {
            outEntity = agendaDestinatarioDAO.eliminarUAgregar(inEntity, ip,
                    existeDestinatarioVacio(inEntity) ? TipoOperacionACTAGEDESTEnum.MODIFICACION
                            : TipoOperacionACTAGEDESTEnum.ALTA);
        } catch (DAOException e) {
            LOGGER.error(ERROR_DAO, e);
        }
        return outEntity;
    }

    /**
     * Realizar alta destinatario.
     *
     * @param inEntity
     *            the in entity
     * @param ip
     *            the ip
     * @param tipoOperacion
     *            the tipo operacion
     * @return the agenda destinatario out entity
     */
    private AgendaDestinatarioOutEntity realizarAltaDestinatario(AgendaDestinatarioInEntity inEntity, String ip,
            TipoOperacionACTAGEDESTEnum tipoOperacion) {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        try {
            outEntity = agendaDestinatarioDAO.eliminarUAgregar(inEntity, ip, tipoOperacion);
            grabarEstadisticasDeAlias(outEntity.getCodigoRetornoExtendido(), tipoOperacion);
        } catch (DAOException e) {
            LOGGER.error(ERROR_DAO, e);
            if (tipoOperacion.equals(TipoOperacionACTAGEDESTEnum.MODIFICACION)) {
                estadisticaManager.add(EstadisticasConstants.EDICION_DESTINATARIO_ALIAS,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            } else if (tipoOperacion.equals(TipoOperacionACTAGEDESTEnum.ALTA)) {
                estadisticaManager.add(EstadisticasConstants.ALTA_DESTINATARIO_ALIAS_REALIZADA,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }

        }
        return outEntity;
    }

    /**
     * Grabar estadisticas de alias.
     *
     * @param codigoError
     *            the codigo error
     * @param tipoOperacion
     *            the tipo operacion
     */
    private void grabarEstadisticasDeAlias(String codigoError, TipoOperacionACTAGEDESTEnum tipoOperacion) {
        if (tipoOperacion.equals(TipoOperacionACTAGEDESTEnum.MODIFICACION)) {
            if (COD_RETORNO_EXTENDIDO_CERO.equals(codigoError) || COD_RETORNO_EXTENDIDO_CEROS.equals(codigoError)) {
                estadisticaManager.add(EstadisticasConstants.EDICION_DESTINATARIO_ALIAS,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            } else {
                estadisticaManager.add(EstadisticasConstants.EDICION_DESTINATARIO_ALIAS,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        } else if (tipoOperacion.equals(TipoOperacionACTAGEDESTEnum.ALTA)) {
            if (COD_RETORNO_EXTENDIDO_CERO.equals(codigoError) || COD_RETORNO_EXTENDIDO_CEROS.equals(codigoError)) {
                estadisticaManager.add(EstadisticasConstants.ALTA_DESTINATARIO_ALIAS_REALIZADA,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            } else if (!codigoError.equals(COD_ERROR_CBU_AGENDADO) || !codigoError.equals(COD_ERROR_CUENTA_AGENDADA)) {
                estadisticaManager.add(EstadisticasConstants.ALTA_DESTINATARIO_ALIAS_REALIZADA,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        }
    }

    /**
     * Obtiene el error del comprobante de alta de destinatario.
     *
     * @param outEntity
     *            the out entity
     * @return the error
     */
    private Error obtenerErrorComprobanteAlta(AgendaDestinatarioOutEntity outEntity) {
        Error error = new Error();
        if (esErrorCuentaInvalida(outEntity)) {
            error.setIsErrorCuentaInvalida(Boolean.TRUE);
        } else if (esErrorDestinatarioAgendado(outEntity)) {
            error.setIsErrorDestinatarioAgendado(Boolean.TRUE);
        } else if (COD_ERROR_CUENTA_NO_CORRESPONDE_SANTANDER.equals(outEntity.getCodigoRetornoExtendido())) {
            error.setIsErrorCuentaInexistenteSantander(Boolean.TRUE);
        } else {
            error.setIsErrorIndeseado(Boolean.TRUE);
        }
        return error;
    }

    /**
     * Es error destinatario agendado.
     *
     * @param outEntity
     *            the out entity
     * @return the boolean
     */
    private Boolean esErrorDestinatarioAgendado(AgendaDestinatarioOutEntity outEntity) {
        return StringUtils.equals(COD_ERROR_CUENTA_AGENDADA, outEntity.getCodigoRetornoExtendido())
                || StringUtils.equals(COD_ERROR_CBU_AGENDADO, outEntity.getCodigoRetornoExtendido());
    }

    /**
     * Es error cuenta invalida.
     *
     * @param outEntity
     *            the out entity
     * @return the boolean
     */
    private Boolean esErrorCuentaInvalida(AgendaDestinatarioOutEntity outEntity) {
        return StringUtils.equals(COD_ERROR_CUENTA_INEXISTENTE, outEntity.getCodigoRetornoExtendido())
                || StringUtils.equals(COD_ERROR_CUENTA_INVALIDA, outEntity.getCodigoRetornoExtendido())
                || StringUtils.equals(COD_ERROR_TIPO_CUENTA_INVALIDA, outEntity.getCodigoRetornoExtendido());
    }

    /**
     * Obtiene el error del comprobante de alta de destinatario para otros
     * bancos.
     *
     * @param outEntity
     *            the out entity
     * @return the error
     */
    private Error obtenerErrorComprobanteAltaOtrosBancos(AgendaDestinatarioOutEntity outEntity) {
        Error error = new Error();
        if (esErrorCuentaInvalidaOtrosBancos(outEntity)) {
            error.setIsErrorCuentaInvalida(Boolean.TRUE);
        } else {
            if (esErrorDestinatarioAgendadoOtrosBancos(outEntity)) {
                error.setIsErrorDestinatarioAgendado(Boolean.TRUE);
            } else {
                error.setIsErrorIndeseado(Boolean.TRUE);
            }
        }
        return error;
    }

    /**
     * Es error destinatario agendado otros bancos.
     *
     * @param outEntity
     *            the out entity
     * @return the boolean
     */
    private Boolean esErrorDestinatarioAgendadoOtrosBancos(AgendaDestinatarioOutEntity outEntity) {
        Boolean res = false;
        ArrayList<String> codigoInicial = new ArrayList<String>(
                Arrays.asList(CODIGO_ERROR_INICIO_1, CODIGO_ERROR_INICIO_2, CODIGO_ERROR_INICIO_3));
        if (codigoInicial.contains(StringUtils.left(outEntity.getCodigoRetornoExtendido(), 1)) && StringUtils
                .equals(CODIGO_CBU_EXISTENTE, StringUtils.right(outEntity.getCodigoRetornoExtendido(), 5))) {
            res = true;
        }
        return res;
    }

    /**
     * Es error cuenta invalida otros bancos.
     *
     * @param outEntity
     *            the out entity
     * @return the boolean
     */
    private Boolean esErrorCuentaInvalidaOtrosBancos(AgendaDestinatarioOutEntity outEntity) {
        Boolean res = false;
        ArrayList<String> codigoInicial = new ArrayList<String>(
                Arrays.asList(CODIGO_ERROR_INICIO_1, CODIGO_ERROR_INICIO_2, CODIGO_ERROR_INICIO_3));
        ArrayList<String> codigoErrores = new ArrayList<String>(
                Arrays.asList(CODIGO_CBU_ENTIDAD_INEXISTENTE, CODIGO_CBU_ENTIDAD_NO_VALIDA, CODIGO_CBU_CUENTA_NO_VALIDA,
                        CODIGO_CBU_ERROR_VALIDACION_1, CODIGO_CBU_ERROR_VALIDACION_2));
        if (codigoInicial.contains(StringUtils.left(outEntity.getCodigoRetornoExtendido(), 1))
                && codigoErrores.contains(StringUtils.right(outEntity.getCodigoRetornoExtendido(), 5))) {
            res = true;
        }
        return res;
    }

    /**
     * Obtiene el comprobante alta DTO.
     *
     * @param outEntity
     *            the out entity
     * @param referenciaTitular
     *            the referencia titular
     * @return the comprobante alta destinatario DTO
     */
    private ComprobanteAltaDestinatarioDTO obtenerComprobanteAltaDTO(AgendaDestinatarioOutEntity outEntity,
            String referenciaTitular) {
        ComprobanteAltaDestinatarioDTO dto = new ComprobanteAltaDestinatarioDTO(outEntity);
        dto.setMensajeEfectivizacion(completarMensajeFeedbackOK(dto, referenciaTitular, MENSAJE_FEEDBACK_OK));
        if (!esRespuestaOK(outEntity)) {
            Error error = obtenerErrorComprobanteAlta(outEntity);
            dto.setTieneError(error.getIsErrorIndeseado());
            dto.setTieneErrorCuentaInvalida(error.getIsErrorCuentaInvalida());
            dto.setTieneErrorDestinatarioAgendado(error.getIsErrorDestinatarioAgendado());
            dto.setTieneErrorCuentaInexistenteSantander(error.getIsErrorCuentaInexistenteSantander());
        }
        return dto;
    }

    /**
     * Obtiene el comprobante alta DTO.
     *
     * @param outEntity
     *            the out entity
     * @param referenciaTitular
     *            the referencia titular
     * @return the comprobante alta destinatario DTO
     */
    private ComprobanteAltaDestinatarioDTO obtenerComprobanteAltaOtrosBancosDTO(AgendaDestinatarioOutEntity outEntity,
            String referenciaTitular) {
        ComprobanteAltaDestinatarioDTO dto = new ComprobanteAltaDestinatarioDTO(outEntity);
        dto.setMensajeEfectivizacion(completarMensajeFeedbackOK(dto, referenciaTitular, MENSAJE_FEEDBACK_OK));
        if (!esRespuestaOK(outEntity)) {
            Error error = obtenerErrorComprobanteAltaOtrosBancos(outEntity);
            dto.setTieneError(error.getIsErrorIndeseado());
            dto.setTieneErrorCuentaInvalida(error.getIsErrorCuentaInvalida());
            dto.setTieneErrorDestinatarioAgendado(error.getIsErrorDestinatarioAgendado());
        }
        return dto;
    }

    /**
     * Es respuesta OK.
     *
     * @param outEntity
     *            the out entity
     * @return the boolean
     */
    private Boolean esRespuestaOK(AgendaDestinatarioOutEntity outEntity) {
        return StringUtils.equals(COD_RETORNO_EXTENDIDO_CEROS, outEntity.getCodigoRetornoExtendido());
    }

    /**
     * Arma los datos de entrada.
     *
     * @param cliente
     *            the cliente
     * @param numeroCuenta
     *            the numero cuenta
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the validacion cuenta in entity
     */
    private ValidacionCuentaInEntity armarDatosEntrada(Cliente cliente, String numeroCuenta, String tipoCuenta) {
        IdentificacionCuenta idCuenta = new IdentificacionCuenta(numeroCuenta);
        ValidacionCuentaInEntity entity = new ValidacionCuentaInEntity();
        entity.setCliente(cliente);
        entity.setTipoCuenta(tipoCuenta);
        entity.setSucursalCuenta(idCuenta.getNroSucursal());
        entity.setNumeroCuenta(idCuenta.getNroCuentaProducto());
        entity.setInformarCuil("1");
        return entity;
    }

    /**
     * Cuenta ingresada es propia.
     *
     * @param cliente
     *            the cliente
     * @param numeroCuentaAlta
     *            the numero cuenta alta
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the boolean
     */
    @Override
    public Boolean cuentaIngresadaEsPropia(Cliente cliente, String numeroCuentaAlta, String tipoCuenta) {
        for (Cuenta cuenta : cliente.getCuentas()) {
            String numeroCuentaCliente = new IdentificacionCuenta(cuenta.getNroSucursal(),
                    cuenta.getNroCuentaProducto()).toString();
            if (StringUtils.equals(numeroCuentaAlta, numeroCuentaCliente) && esMismoTipoCuenta(cuenta, tipoCuenta)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Es mismo tipo cuenta.
     *
     * @param cuenta
     *            the cuenta
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the boolean
     */
    private Boolean esMismoTipoCuenta(Cuenta cuenta, String tipoCuenta) {
        return Integer.valueOf(tipoCuenta).equals(obtenerTipoCuenta(cuenta.getTipoCuentaEnum()));
    }

    /**
     * Obtiene el tipo de cuenta.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the integer
     */
    private Integer obtenerTipoCuenta(TipoCuenta tipoCuenta) {
        if (tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_PESOS) || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_DOLARES)) {
            return DOS;
        }
        return tipoCuenta.getCodigo();
    }

    /**
     * Obtiene la direccion de correo.
     *
     * @param direccionCorreo
     *            the direccion correo
     * @return the string
     */
    private String obtenerDireccionCorreo(String direccionCorreo) {
        if (direccionCorreo.length() < CIEN) {
            return StringUtils.rightPad(direccionCorreo, CIEN, ISBANStringUtils.ESPACIO_STRING);
        } else {
            return direccionCorreo;
        }
    }

    /**
     * Obtiene el titular.
     *
     * @param titular
     *            the titular
     * @return the string
     */
    private String obtenerTitular(String titular) {
        if (titular.length() < SESENTAYCUATRO) {
            return StringUtils.rightPad(titular, SESENTAYCUATRO, ISBANStringUtils.ESPACIO_STRING);
        } else {
            return titular;
        }
    }

    /**
     * Obtiene la descripcion de la cuenta.
     *
     * @param descripcionCuenta
     *            the descripcion cuenta
     * @return the string
     */
    private String obtenerDescripcionCuenta(String descripcionCuenta) {
        if (descripcionCuenta == null) {
            return StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, TREINTA);
        }
        if (descripcionCuenta.length() < TREINTA) {
            return StringUtils.rightPad(descripcionCuenta, TREINTA, ISBANStringUtils.ESPACIO_STRING);
        } else {
            return descripcionCuenta;
        }
    }

    /**
     * Obtiene el tipo de documento.
     *
     * @param isCuit
     *            the is cuit
     * @param cuitCuil
     *            the cuit cuil
     * @return the string
     */
    private String obtenerTipoDocumento(Boolean isCuit, String cuitCuil) {
        if (cuitCuil != null) {
            return cuitCuil;
        }
        if (isCuit) {
            return StringUtils.rightPad("T", DOS, ISBANStringUtils.ESPACIO_STRING);
        }
        return StringUtils.rightPad("L", DOS, ISBANStringUtils.ESPACIO_STRING);
    }

    /**
     * Obtener tipo documento otros bancos.
     *
     * @param isCuit
     *            the is cuit
     * @return the string
     */
    private String obtenerTipoDocumentoOtrosBancos(Boolean isCuit) {
        if (isCuit != null) {
            if (isCuit) {
                return StringUtils.rightPad("T ", DOS, ISBANStringUtils.ESPACIO_STRING);
            }
            return StringUtils.rightPad("L ", DOS, ISBANStringUtils.ESPACIO_STRING);
        }
        return StringUtils.rightPad("T ", DOS, ISBANStringUtils.ESPACIO_STRING);
    }

    /**
     * Si existe destinatario vacio o proveniente de obe se debe modificar sino
     * dar de alta.
     *
     * @param inEntity
     *            the in entity
     * @return true, if successful
     */
    @Override
    public boolean existeDestinatarioVacio(AgendaDestinatarioInEntity inEntity) {
        Boolean modifica = Boolean.FALSE;
        for (DestinatarioEntity entity : sesionAgenda.getDestinatariosEntity()) {
            if ((TipoAgendaEnum.AGENDA_RIO.getCampo().equals(inEntity.getTipoAgendaOcurrencia())
                    && StringUtils.equals(inEntity.getNumeroCuentaDestinatario(), entity.getNumeroCuentaDestinatario()))
                    || (TipoAgendaEnum.AGENDA_OTROS_BANCOS.getCampo().equals(inEntity.getTipoAgendaOcurrencia())
                            && StringUtils.equals(inEntity.getCbuDestinatario(), entity.getCbuDestinatario()))) {
                modifica = entity.noTieneReferenciaNiTitular() || entity.esFiltradoOBE();
            }
        }
        return modifica;
    }

    /**
     * The Class Error.
     */
    class Error {

        /** The is error destinatario agendado. */
        Boolean isErrorDestinatarioAgendado = Boolean.FALSE;

        /** The is error cuenta invalida. */
        Boolean isErrorCuentaInvalida = Boolean.FALSE;

        /** The is error cuenta inexistente santander. */
        Boolean isErrorCuentaInexistenteSantander = Boolean.FALSE;

        /** The is error indeseado. */
        Boolean isErrorIndeseado = Boolean.FALSE;

        /**
         * Gets the checks if is error destinatario agendado.
         *
         * @return the isErrorDestinatarioAgendado
         */
        public Boolean getIsErrorDestinatarioAgendado() {
            return isErrorDestinatarioAgendado;
        }

        /**
         * Sets the checks if is error destinatario agendado.
         *
         * @param isErrorDestinatarioAgendado
         *            the isErrorDestinatarioAgendado to set
         */
        public void setIsErrorDestinatarioAgendado(Boolean isErrorDestinatarioAgendado) {
            this.isErrorDestinatarioAgendado = isErrorDestinatarioAgendado;
        }

        /**
         * Gets the checks if is error cuenta invalida.
         *
         * @return the isErrorCuentaInvalida
         */
        public Boolean getIsErrorCuentaInvalida() {
            return isErrorCuentaInvalida;
        }

        /**
         * Sets the checks if is error cuenta invalida.
         *
         * @param isErrorCuentaInvalida
         *            the isErrorCuentaInvalida to set
         */
        public void setIsErrorCuentaInvalida(Boolean isErrorCuentaInvalida) {
            this.isErrorCuentaInvalida = isErrorCuentaInvalida;
        }

        /**
         * Gets the checks if is error indeseado.
         *
         * @return the isErrorIndeseado
         */
        public Boolean getIsErrorIndeseado() {
            return isErrorIndeseado;
        }

        /**
         * Sets the checks if is error indeseado.
         *
         * @param isErrorIndeseado
         *            the isErrorIndeseado to set
         */
        public void setIsErrorIndeseado(Boolean isErrorIndeseado) {
            this.isErrorIndeseado = isErrorIndeseado;
        }

        /**
         * Gets the checks if is error cuenta inexistente santander.
         *
         * @return the checks if is error cuenta inexistente santander
         */
        public Boolean getIsErrorCuentaInexistenteSantander() {
            return isErrorCuentaInexistenteSantander;
        }

        /**
         * Sets the checks if is error cuenta inexistente santander.
         *
         * @param isErrorCuentaInexistenteSantander
         *            the new checks if is error cuenta inexistente santander
         */
        public void setIsErrorCuentaInexistenteSantander(Boolean isErrorCuentaInexistenteSantander) {
            this.isErrorCuentaInexistenteSantander = isErrorCuentaInexistenteSantander;
        }

    }

    /**
     * Cargar agenda destinatario in entity.
     *
     * @param cliente
     *            the cliente
     * @param datosConfirmados
     *            the datos confirmados
     * @return the agenda destinatario in entity
     */
    private AgendaDestinatarioInEntity cargarAgendaDestinatarioInEntity(Cliente cliente,
            ConfirmacionAltaDestinatarioView datosConfirmados) {
        AgendaDestinatarioInEntity agendaDestinatarioInEntity = new AgendaDestinatarioInEntity();
        agendaDestinatarioInEntity.setCliente(cliente);
        agendaDestinatarioInEntity.setDocumentoDestinatario(datosConfirmados.getNumeroDocumento());
        agendaDestinatarioInEntity
                .setDescripcionCuentaDestinatario(obtenerDescripcionCuenta(datosConfirmados.getReferencia()));
        agendaDestinatarioInEntity
                .setCaracteristicasCuentaDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 10));
        agendaDestinatarioInEntity.setTitular(obtenerTitular(datosConfirmados.getTitular()));
        agendaDestinatarioInEntity.setDireccionCorreo(obtenerDireccionCorreo(datosConfirmados.getDireccionCorreo()));
        agendaDestinatarioInEntity.setTelefonoDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 16));
        return agendaDestinatarioInEntity;
    }

    /**
     * Cargar agenda destinatario in entity desde transferencia.
     *
     * @param cliente
     *            the cliente
     * @param datosConfirmados
     *            the datos confirmados
     * @return the agenda destinatario in entity
     */
    private AgendaDestinatarioInEntity cargarAgendaDestinatarioInEntityDesdeTransferencia(Cliente cliente,
            TransferenciaView datosConfirmados) {
        AgendaDestinatarioInEntity agendaDestinatarioInEntity = new AgendaDestinatarioInEntity();
        DestinatarioEntity destinatarioEntity = sesionAgenda.obtenerDestinatarioEntitySeleccionado();
        agendaDestinatarioInEntity.setCliente(cliente);
        agendaDestinatarioInEntity.setDocumentoDestinatario(datosConfirmados.getCuit());
        agendaDestinatarioInEntity.setDescripcionCuentaDestinatario(
                obtenerDescripcionCuenta(destinatarioEntity.getDescripcionCuentaDestinatario()));
        agendaDestinatarioInEntity
                .setCaracteristicasCuentaDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 10));
        agendaDestinatarioInEntity.setTitular(obtenerTitular(datosConfirmados.getTitular()));
        agendaDestinatarioInEntity.setDireccionCorreo(obtenerDireccionCorreo(destinatarioEntity.getDireccionCorreo()));
        agendaDestinatarioInEntity.setTelefonoDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 16));
        return agendaDestinatarioInEntity;
    }

    /**
     * Crear agenda destinatario in entity rio.
     *
     * @param cliente
     *            the cliente
     * @param datosConfirmados
     *            the datos confirmados
     * @param cuitCuil
     *            the cuit cuil
     * @return the agenda destinatario in entity
     */
    private AgendaDestinatarioInEntity crearAgendaDestinatarioInEntityRio(Cliente cliente,
            ConfirmacionAltaDestinatarioView datosConfirmados, String cuitCuil) {
        AgendaDestinatarioInEntity agendaDestinatarioInEntity = cargarAgendaDestinatarioInEntity(cliente,
                datosConfirmados);
        agendaDestinatarioInEntity.setTipoAgendaOcurrencia(TipoAgendaEnum.AGENDA_RIO.getCampo());
        agendaDestinatarioInEntity.setTipoCuentaDestinatario(datosConfirmados.getIdTipoCuenta());
        agendaDestinatarioInEntity.setSucursalCuentaDestinatario(
                obtenerSucursal(new IdentificacionCuenta(datosConfirmados.getNroCuenta()).getNroSucursal()));
        agendaDestinatarioInEntity.setNumeroCuentaDestinatario(
                obtenerNroCuenta(new IdentificacionCuenta(datosConfirmados.getNroCuenta()).getNroCuentaProducto()));
        agendaDestinatarioInEntity.setCbuDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 22));
        agendaDestinatarioInEntity.setBancoDestinatario(StringUtils.rightPad(BancoEnum.SANTANDER_RIO.getDescripcion(),
                CINCUENTA, ISBANStringUtils.ESPACIO_STRING));
        agendaDestinatarioInEntity
                .setTipoDocumentoDestinatario(obtenerTipoDocumento(datosConfirmados.getIsCuit(), cuitCuil));
        return agendaDestinatarioInEntity;

    }

    /**
     * Crear agenda destinatario in entity otros bancos.
     *
     * @param cliente
     *            the cliente
     * @param datosConfirmados
     *            the datos confirmados
     * @return the agenda destinatario in entity
     */
    private AgendaDestinatarioInEntity crearAgendaDestinatarioInEntityOtrosBancos(Cliente cliente,
            ConfirmacionAltaDestinatarioView datosConfirmados) {
        AgendaDestinatarioInEntity agendaDestinatarioInEntity = cargarAgendaDestinatarioInEntity(cliente,
                datosConfirmados);
        agendaDestinatarioInEntity.setTipoAgendaOcurrencia(TipoAgendaEnum.AGENDA_OTROS_BANCOS.getCampo());
        agendaDestinatarioInEntity.setTipoCuentaDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 2));
        agendaDestinatarioInEntity
                .setSucursalCuentaDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 4));
        agendaDestinatarioInEntity.setNumeroCuentaDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 12));
        agendaDestinatarioInEntity.setCbuDestinatario(datosConfirmados.getCbu());
        agendaDestinatarioInEntity.setBancoDestinatario(
                StringUtils.rightPad(datosConfirmados.getBancoDestino(), CINCUENTA, ISBANStringUtils.ESPACIO_STRING));
        agendaDestinatarioInEntity
                .setTipoDocumentoDestinatario(obtenerTipoDocumentoOtrosBancos(datosConfirmados.getIsCuit()));
        return agendaDestinatarioInEntity;
    }

    /**
     * Crear agenda destinatario in entity alias.
     *
     * @param cliente
     *            the cliente
     * @param datosConfirmados
     *            the datos confirmados
     * @return the agenda destinatario in entity
     */
    private AgendaDestinatarioInEntity crearAgendaDestinatarioInEntityAlias(Cliente cliente,
            ConfirmacionAltaDestinatarioView datosConfirmados) {
        AgendaDestinatarioInEntity agendaDestinatarioInEntity = cargarAgendaDestinatarioInEntity(cliente,
                datosConfirmados);
        if (CODIGO_SANTANDER.equals(datosConfirmados.getCbu().substring(0, 3))) {
            agendaDestinatarioInEntity.setTipoAgendaOcurrencia(TipoAgendaEnum.AGENDA_RIO.getCampo());
            agendaDestinatarioInEntity.setTipoCuentaDestinatario("0" + datosConfirmados.getCodTipoCuenta());
            agendaDestinatarioInEntity.setSucursalCuentaDestinatario(datosConfirmados.getCbu().substring(3, 7));
            agendaDestinatarioInEntity.setNumeroCuentaDestinatario(
                    StringUtils.leftPad(datosConfirmados.getCbu().substring(14, 21), DOCE, STRING_CERO));
            agendaDestinatarioInEntity.setCbuDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 22));
            agendaDestinatarioInEntity.setBancoDestinatario(StringUtils
                    .rightPad(BancoEnum.SANTANDER_RIO.getDescripcion(), CINCUENTA, ISBANStringUtils.ESPACIO_STRING));
        } else {
            agendaDestinatarioInEntity.setTipoAgendaOcurrencia(TipoAgendaEnum.AGENDA_OTROS_BANCOS.getCampo());
            agendaDestinatarioInEntity.setTipoCuentaDestinatario("  ");
            agendaDestinatarioInEntity.setSucursalCuentaDestinatario("    ");
            agendaDestinatarioInEntity.setNumeroCuentaDestinatario("            ");
            agendaDestinatarioInEntity.setCbuDestinatario(datosConfirmados.getCbu());
            agendaDestinatarioInEntity.setBancoDestinatario(StringUtils.rightPad(datosConfirmados.getBancoDestino(),
                    CINCUENTA, ISBANStringUtils.ESPACIO_STRING));
        }
        agendaDestinatarioInEntity.setTipoDocumentoDestinatario("T ");
        agendaDestinatarioInEntity
                .setAlias(StringUtils.rightPad(datosConfirmados.getAlias(), VEINTE, ISBANStringUtils.ESPACIO_STRING));
        return agendaDestinatarioInEntity;

    }

    /**
     * Crear agenda destinatario in entity alias from transferencia.
     *
     * @param cliente
     *            the cliente
     * @param transferenciaView
     *            the transferencia view
     * @return the agenda destinatario in entity
     */
    private AgendaDestinatarioInEntity crearAgendaDestinatarioInEntityAliasFromTransferencia(Cliente cliente,
            TransferenciaView transferenciaView) {
        AgendaDestinatarioInEntity agendaDestinatarioInEntity = cargarAgendaDestinatarioInEntityDesdeTransferencia(
                cliente, transferenciaView);
        if (transferenciaView.getIsRioRio()) {
            agendaDestinatarioInEntity.setTipoAgendaOcurrencia(TipoAgendaEnum.AGENDA_RIO.getCampo());
            agendaDestinatarioInEntity.setTipoCuentaDestinatario("0" + TipoCuenta
                    .fromDescripcionConMoneda(transferenciaView.getTipoCuentaDestino()).getCodigo().toString());
            agendaDestinatarioInEntity
                    .setSucursalCuentaDestinatario("0" + transferenciaView.getNroCuentaDestino().substring(0, 3));
            String nroCuentaDestino = transferenciaView.getNroCuentaDestino().replace("-", "").replace("/", "")
                    .substring(3, 10);
            agendaDestinatarioInEntity
                    .setNumeroCuentaDestinatario(StringUtils.leftPad(nroCuentaDestino, DOCE, STRING_CERO));
            agendaDestinatarioInEntity.setCbuDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 22));
            agendaDestinatarioInEntity.setBancoDestinatario(
                    StringUtils.rightPad(BancoEnum.SANTANDER_RIO.getDescripcion(), CINCUENTA, ISBANStringUtils.ESPACIO_STRING));
        } else {
            agendaDestinatarioInEntity.setTipoAgendaOcurrencia(TipoAgendaEnum.AGENDA_OTROS_BANCOS.getCampo());
            agendaDestinatarioInEntity.setTipoCuentaDestinatario("  ");
            agendaDestinatarioInEntity.setSucursalCuentaDestinatario("    ");
            agendaDestinatarioInEntity.setNumeroCuentaDestinatario("            ");
            agendaDestinatarioInEntity.setCbuDestinatario(transferenciaView.getCbu());
            agendaDestinatarioInEntity.setBancoDestinatario(
                    StringUtils.rightPad(transferenciaView.getBanco(), CINCUENTA, ISBANStringUtils.ESPACIO_STRING));
        }
        agendaDestinatarioInEntity.setTipoDocumentoDestinatario("T ");
        agendaDestinatarioInEntity.setAlias(
                StringUtils.rightPad(transferenciaView.getAliasDestino(), VEINTE, ISBANStringUtils.ESPACIO_STRING));
        return agendaDestinatarioInEntity;
    }

    /**
     * Obtiene la sucursal del cliente.
     *
     * @param nroSucursal
     *            the nro sucursal
     * @return the string
     */
    private String obtenerSucursal(String nroSucursal) {
        if (nroSucursal.length() < CUATRO) {
            return StringUtils.leftPad(nroSucursal, CUATRO, ISBANStringUtils.ZERO_STR);
        } else {
            return nroSucursal;
        }
    }

    /**
     * Obtiene el numero de cuenta del cliente.
     *
     * @param nroCuentaProducto
     *            the nro cuenta producto
     * @return the string
     */
    private String obtenerNroCuenta(String nroCuentaProducto) {
        if (nroCuentaProducto.length() < DOCE) {
            return StringUtils.leftPad(nroCuentaProducto, DOCE, ISBANStringUtils.ZERO_STR);
        } else {
            return nroCuentaProducto;
        }
    }
}
/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.bo.impl.AutentificacionBOImpl;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.TokenOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.challenge.exception.BloqueoException;
import ar.com.santanderrio.obp.servicios.comun.challenge.exception.SyncException;
import ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.bo.SoftTokenBO;
import ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.dao.SoftTokenDAO;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.exception.RobotException;

/**
 * The Class SoftTokenBOImpl.
 */
@Component
public class SoftTokenBOImpl extends AutentificacionBOImpl implements SoftTokenBO {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SoftTokenBOImpl.class);

    /** The soft token DAO. */
    @Autowired
    private SoftTokenDAO softTokenDAO;

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The soft token habilitado offset. */
    private static int softTokenHabilitadoOffset = 4;

    /** The soft token habilitado char. */
    private static char softTokenHabilitadoChar = '3';

    /** The Constant MSJ_VERIFICANDO_TOKEN_HABILITADO. */
    private static final String MSJ_VERIFICANDO_TOKEN_HABILITADO = "Verificando si tiene el token habilitado...";

    /** The Constant MSJ_TOKEN_HABILITADO. */
    private static final String MSJ_TOKEN_HABILITADO = "Tiene token habilitado.";

    /** The Constant MSJ_TOKEN_DESHABILITADO. */
    private static final String MSJ_TOKEN_DESHABILITADO = "Tiene token deshabilitado.";

    /** The Constant ERROR_INESPERADO_SOLICITAR_TOKEN. */
    private static final String MSJ_ERROR_INESPERADO_SOLICITAR_TOKEN = "Ha ocurrido un error al solicitar el token.";

    /** The Constant MSJ_EJECUTANDO_TOKEN. */
    private static final String MSJ_EJECUTANDO_TOKEN = "Validando token...";

    /** The Constant MSJ_EJECUCION_TOKEN_SATISFACTORIA. */
    private static final String MSJ_EJECUCION_TOKEN_SATISFACTORIA = "Ha ejecutado el token satisfactoriamente";

    /** The Constant MSJ_EJECUCION_TOKEN_ERRONEA. */
    private static final String MSJ_EJECUCION_TOKEN_CON_ERRORES = "Ha ejecutado el token con errores";

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.bo.SoftTokenBO#
     * tieneSoftTokenHabilitado()
     */
    @Override
    public Respuesta<AutentificacionDTO> tieneSoftTokenHabilitado() {
        try {
            LOGGER.info(MSJ_VERIFICANDO_TOKEN_HABILITADO);
            if (tieneSoftToken()) {
                LOGGER.info(MSJ_TOKEN_HABILITADO);
                return respuestaOKSolicitud();
            } else {
                LOGGER.info(MSJ_TOKEN_DESHABILITADO);
                return respuestaWarningSinMetodoDesafioToken();
            }
        } catch (NullPointerException e) {
            LOGGER.error(MSJ_ERROR_INESPERADO_SOLICITAR_TOKEN, e);
            return crearRespuestaErrorGenericoSinEstadistica();
        }
    }

    /**
     * Validar soft token.
     *
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @param codigoEstadisticaValidacion
     *            the codigo estadistica validacion
     * @return the respuesta
     * @see ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.bo.SoftTokenBO#validarSoftToken(ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.DesafioDTO)
     */
    @Override
    public Respuesta<AutentificacionDTO> validarSoftToken(AutentificacionDTO autentificacionDTO,
            String codigoEstadisticaValidacion) {
        try {
            return ejecutarValidacionToken(autentificacionDTO, codigoEstadisticaValidacion);
        } catch (NullPointerException e) {
            LOGGER.error(MSJ_ERROR_INESPERADO_SOLICITAR_TOKEN, e);
            return crearRespuestaErrorGenerico(codigoEstadisticaValidacion);
        }
    }

    /**
     * Ejecutar validacion token.
     *
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @param codigoEstadisticaValidacion
     *            the codigo estadistica validacion
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> ejecutarValidacionToken(AutentificacionDTO autentificacionDTO,
            String codigoEstadisticaValidacion) {
        try {
            LOGGER.info(MSJ_EJECUTANDO_TOKEN);
            TokenOperacionDTO tokenDTO = autentificacionDTO.getTokenOperacion();
            checkForNullValuesAndLog(tokenDTO);
            boolean ejecutarValidacionToken = softTokenDAO.ejecutarValidacionToken(tokenDTO.getIngresoToken(),
                    getSesionParamatros().getRegistroSession().getIp().replace(".", ""),
                    getSesionCliente().getCliente());
            if (ejecutarValidacionToken) {
                LOGGER.info(MSJ_EJECUCION_TOKEN_SATISFACTORIA);
                return respuestaOKValidacion(codigoEstadisticaValidacion, autentificacionDTO);
            }
            LOGGER.debug(MSJ_EJECUCION_TOKEN_CON_ERRORES);
            return crearRespuestaErrorGenerico(codigoEstadisticaValidacion);
        } catch (DAOException e) {
            LOGGER.error(MSJ_ERROR_INESPERADO_SOLICITAR_TOKEN, e);
            return crearRespuestaErrorGenericoToken(codigoEstadisticaValidacion);
        }
    }

    private void checkForNullValuesAndLog(TokenOperacionDTO tokenDto) {

        StringBuilder message = new StringBuilder();

        if (tokenDto != null && tokenDto.getIngresoToken() != null) {
            message.append("TOKEN = " + tokenDto.getIngresoToken());
        } else {
            message.append("TOKEN = null");
        }

        if (getSesionParamatros() != null && getSesionParamatros().getRegistroSession() != null && getSesionParamatros().getRegistroSession().getIp() != null) {
            message.append(", IP = " + getSesionParamatros().getRegistroSession().getIp());
        } else {
            message.append(", IP = null");
        }

        if (getSesionCliente() != null && getSesionCliente().getCliente() != null && getSesionCliente().getCliente().getNup() != null) {
            message.append(", NUP = " + getSesionCliente().getCliente().getNup());
        } else {
            message.append(", NUP = null");
        }

        LOGGER.info("Valores antes de iniciar validacion de token: {}", message);

    }

    /**
     * Busca si en al menos una cuenta banelco esta habilitado a usar softtoken.
     * 
     * * Se setea el {@link TipoError} SIN_DESAFIO para que el
     * {@link AutentificacionManager} lo capture y evalue si ya agoto la evaluacion
     * de todo la lista de desafios disponibles.
     *
     * @return true, if successful
     * @last_updated: b039542 - ignacio_valek@itrsa.com.ar - 21/09/2016
     */
    @Override
    public boolean tieneSoftToken() {
        for (Cuenta cuenta : getSesionCliente().getCliente().getCuentas()) {
            if (TipoCuenta.BANELCO.equals(cuenta.getTipoCuentaEnum())
                    && cuenta.getGrupoAfinidad().length() > softTokenHabilitadoOffset
                    && softTokenHabilitadoChar == cuenta.getGrupoAfinidad().charAt(softTokenHabilitadoOffset)) {
                return true;
            }
        }
        for (Cuenta cuenta : getSesionCliente().getCliente().getCuentasPrivadas()) {
            if (TipoCuenta.BANELCO.equals(cuenta.getTipoCuentaEnum())
                    && cuenta.getGrupoAfinidad().length() > softTokenHabilitadoOffset
                    && softTokenHabilitadoChar == cuenta.getGrupoAfinidad().charAt(softTokenHabilitadoOffset)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Respuesta OK solicitud. Realiza el grabado de estadistica.
     *
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> respuestaOKSolicitud() {
        try {
            Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.SOFT_TOKEN_MENSAJE_INPUT);
            return getRespuestaFactory().crearRespuestaWarning(AutentificacionDTO.class,
                    new AutentificacionDTO(TipoDesafioEnum.TOKEN, new TokenOperacionDTO(mensaje.getMensaje())),
                    TipoError.DESAFIO, CodigoMensajeConstantes.SOFT_TOKEN_MENSAJE_INPUT, "");
        } catch (RobotException e) {
            LOGGER.error(MSJ_ERROR_INESPERADO_SOLICITAR_TOKEN, e);
            return crearRespuestaErrorGenericoSinEstadistica();
        }
    }

    /**
     * Respuesta OK validacion.
     *
     * @param codigoEstadistica
     *            the codigo estadistica
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> respuestaOKValidacion(String codigoEstadistica,
            AutentificacionDTO autentificacionDTO) {
        grabarEstadistica(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        autentificacionDTO.setValorNotificarRSA(true);
        return getRespuestaFactory().crearRespuestaOk(AutentificacionDTO.class, autentificacionDTO);
    }

    /**
     * Respuesta error generico solicitud.
     *
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> respuestaWarningSinMetodoDesafioToken() {
        return getRespuestaFactory().crearRespuestaWarning(AutentificacionDTO.class, null, TipoError.SIN_METODO_DESAFIO,
                CodigoMensajeConstantes.ADHERIR_METODO_DESAFIO_MENSAJE, TipoDesafioEnum.TOKEN.getId());
    }

    /**
     * Respuesta warning bloqueo de token.
     *
     * @param codigoEstadistica
     *            the codigo estadistica
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> respuestaWarningBloqueoDeToken(String codigoEstadistica,
            AutentificacionDTO autentificacionDTO) {
        grabarEstadistica(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        autentificacionDTO.setValorNotificarRSA(false);
        autentificacionDTO.setReintentosAgotados(true);
        return getRespuestaFactory().crearRespuestaWarning(AutentificacionDTO.class, autentificacionDTO,
                TipoError.ERROR_BLOQUEO_TOKEN, CodigoMensajeConstantes.SOFT_TOKEN_BLOQ_ERROR, "");
    }

    /**
     * Respuesta warning error token.
     *
     * @param codigoEstadistica
     *            the codigo estadistica
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> respuestaWarningErrorToken(String codigoEstadistica,
            AutentificacionDTO autentificacionDTO) {
        grabarEstadistica(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        autentificacionDTO.setValorNotificarRSA(false);
        autentificacionDTO.setReintentosAgotados(false);
        return getRespuestaFactory().crearRespuestaWarning(AutentificacionDTO.class, autentificacionDTO,
                TipoError.ERROR_SYNC_TOKEN, CodigoMensajeConstantes.SOFT_TOKEN_SYNC_ERROR, "");
    }

}

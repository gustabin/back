/*
 * 
 */
package ar.com.santanderrio.obp.servicios.banelco.bo.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.banelco.bo.BanelcoBO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.bo.impl.AutentificacionBOImpl;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.BanelcoOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.pagos.dao.BanelcoDAO;

/**
 * The Class BanelcoBOImpl.
 */
@Component
public class BanelcoBOImpl extends AutentificacionBOImpl implements BanelcoBO {

    /** The banelco DAO. */
    @Autowired
    private BanelcoDAO banelcoDAO;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BanelcoBOImpl.class);

    /** The Constant CODIGO_RETORNO_OK. */
    private static final int CODIGO_RETORNO_OK = 0;

    /** The Constant CANTIDAD_MAXIMA_DIGITOS. */
    private static final int CANTIDAD_MAXIMA_DIGITOS = 8;

    /** The Constant CODIGO_ERROR_DEBITO_BLOQUEADA. */
    private static final int CODIGO_ERROR_DEBITO_BLOQUEADA = 10005020;

    /** The Constant CODIGO_ERROR_DEBITO_NUMEROS_EQUIVOCADOS. */
    private static final int CODIGO_WARNING_DEBITO_NUMEROS_EQUIVOCADOS = 10005030;

    /** The Constant CODIGO_MENSAJE_OCHO_DIGITOS_ERROR. */
    private static final String CODIGO_MENSAJE_OCHO_DIGITOS_ERROR = "1217";

    /** The Constant MENSAJE_BANELCO_BLOQUEADA. */
    private static final String MENSAJE_BANELCO_BLOQUEADA = "1291";

    /** The Constant CODIGO_MENSAJE. */
    private static final String CODIGO_MENSAJE = "1199";

    /** The Constant ERROR_INESPERADO_SOLICITAR_TOKEN. */
    private static final String MSJ_ERROR_INESPERADO_SOLICITAR_BANELCO = "Ha ocurrido un error al solicitar banelco.";

    /** The Constant ERROR_BANELCO. */
    private static final String ERROR_BANELCO = "RSA Banelco Error: ";

    /** The mensaje DAO. */
    @Autowired
    private MensajeDAO mensajeDAO;

    /** {@inheritDoc} */
    @Deprecated // usar #validarOchoDigitos que esta integrado con el
    // Autentificador generico.
    @Override
    public Respuesta<Boolean> validarTarjetaDebito(String numTarjeta) throws BusinessException {
        final int ultimosOchoDigitosTarjeta = 8;
        try {

            if (StringUtils.length(numTarjeta) == ultimosOchoDigitosTarjeta) {
                LOGGER.info("Valida los ultimos 8 digitos de la tarjeta");
                return banelcoDAO.validarTarjetaDebito(getSesionCliente().getCliente(),
                        numTarjeta.substring(numTarjeta.length() - ultimosOchoDigitosTarjeta, numTarjeta.length()));
            }
            return getRespuestaFactory().crearRespuestaError(Boolean.class, "", "");

        } catch (DAOException e) {
            throw new BusinessException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.banelco.bo.BanelcoBO#
     * obtenerUltimosDigitosValidacion()
     */
    @Override
    public Respuesta<AutentificacionDTO> obtenerUltimosDigitosValidacion() {
        try {
            String cuatroDigitos = getSesionCliente().getCliente().getUltimosDigitosBanelco();
            if (StringUtils.EMPTY.equals(cuatroDigitos)) {
                return respuestaWarningSinMetodoDesafioBanelco();
            }
            AutentificacionDTO autentificacionDTO = crearAutentificacionDTO(cuatroDigitos);
            return crearRespuestaBanelco(autentificacionDTO);
        } catch (NullPointerException e) {
            LOGGER.error(MSJ_ERROR_INESPERADO_SOLICITAR_BANELCO, e);
            return crearRespuestaErrorGenericoSinEstadistica();
        }
    }

    /** {@inheritDoc} */
    @Override
    public Respuesta<AutentificacionDTO> validarOchoDigitos(AutentificacionDTO auntentificacionDTO,
            String codigoEstadisticaValidacion) {
        try {
            String numeroTarjeta = auntentificacionDTO.getBanelcoOperacion().getIngresoDigitos();
            if (StringUtils.length(numeroTarjeta) == CANTIDAD_MAXIMA_DIGITOS && numeroTarjeta != null) {
                String numTarjeta = numeroTarjeta.substring(numeroTarjeta.length() - CANTIDAD_MAXIMA_DIGITOS,
                        numeroTarjeta.length());
                Integer errorCode = banelcoDAO.validarOchoDigitos(getSesionCliente().getCliente(), numTarjeta);
                return crearRespuestaBanelco(errorCode, codigoEstadisticaValidacion, auntentificacionDTO);
            }
            return crearRespuestaErrorGenerico(codigoEstadisticaValidacion);
        } catch (DAOException e) {
            LOGGER.error(ERROR_BANELCO, e);
            return crearRespuestaErrorGenerico(codigoEstadisticaValidacion);
        } catch (NullPointerException e) {
            LOGGER.error(ERROR_BANELCO, e);
            return crearRespuestaErrorGenerico(codigoEstadisticaValidacion);
        }
    }

    /**
     * Obtener respuesta banelco.
     *
     * @param errorCode
     *            the error code
     * @param codigoEstadisticaValidacion
     *            the codigo estadistica validacion
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> crearRespuestaBanelco(Integer errorCode, String codigoEstadisticaValidacion,
            AutentificacionDTO autentificacionDTO) {
        if (errorCode == CODIGO_RETORNO_OK) {
            grabarEstadistica(codigoEstadisticaValidacion, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            autentificacionDTO.setValorNotificarRSA(true);
            return getRespuestaFactory().crearRespuestaOk(AutentificacionDTO.class, autentificacionDTO);
        } else if (errorCode == CODIGO_ERROR_DEBITO_BLOQUEADA) {
            grabarEstadistica(codigoEstadisticaValidacion, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            autentificacionDTO.setValorNotificarRSA(false);
            return getRespuestaFactory().crearRespuestaWarning(null, TipoError.DEBITO_BLOQUEADA,
                    MENSAJE_BANELCO_BLOQUEADA);
        } else if (errorCode == CODIGO_WARNING_DEBITO_NUMEROS_EQUIVOCADOS) {
            grabarEstadistica(codigoEstadisticaValidacion, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            autentificacionDTO.setValorNotificarRSA(false);
            return getRespuestaFactory().crearRespuestaWarning(AutentificacionDTO.class, autentificacionDTO,
                    TipoError.DEBITO_NUMEROS_EQUIVOCADOS, CODIGO_MENSAJE_OCHO_DIGITOS_ERROR, "");
        }
        return crearRespuestaErrorGenerico(codigoEstadisticaValidacion);
    }

    /**
     * Crear respuesta banelco.
     *
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> crearRespuestaBanelco(AutentificacionDTO autentificacionDTO) {
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setTipoError(TipoError.DESAFIO.getDescripcion());
        List<ItemMensajeRespuesta> itemsMensaje = new ArrayList<ItemMensajeRespuesta>();
        itemsMensaje.add(itemMensajeRespuesta);
        return getRespuestaFactory().crearRespuestaWarning(AutentificacionDTO.class, autentificacionDTO, itemsMensaje);
    }

    /**
     * Obtener autentificacion DTO.
     *
     * @param cuatroDigitos
     *            the cuatro digitos
     * @return the autentificacion DTO
     */
    private AutentificacionDTO crearAutentificacionDTO(String cuatroDigitos) {
        Mensaje mensaje = mensajeDAO.obtenerMensajePorCodigo(CODIGO_MENSAJE);
        String mensajeBanelco = MessageFormat.format(mensaje.getMensaje(),
                getSesionCliente().getCliente().getUltimosDigitosBanelco());

        BanelcoOperacionDTO banelcoOperacionDTO = new BanelcoOperacionDTO();
        banelcoOperacionDTO.setDigitos(cuatroDigitos);
        banelcoOperacionDTO.setMensaje(mensajeBanelco);
        banelcoOperacionDTO.setTipoDesafio(TipoDesafioEnum.BANELCO);

        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setTipoDesafio(TipoDesafioEnum.BANELCO);
        autentificacionDTO.setBanelcoOperacion(banelcoOperacionDTO);
        autentificacionDTO.setReintentosAgotados(false);
        autentificacionDTO.setValorNotificarRSA(false);
        return autentificacionDTO;
    }

    /**
     * Respuesta error generico solicitud.
     * 
     * Se setea el {@link TipoError} SIN_DESAFIO para que el
     * {@link AutentificacionManager} lo capture y evalue si ya agoto la evaluacion
     * de todo la lista de desafios disponibles.
     *
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> respuestaWarningSinMetodoDesafioBanelco() {
        LOGGER.error("El usuario no tiene desafio de 8 digitos.");
        return getRespuestaFactory().crearRespuestaWarning(AutentificacionDTO.class, null, TipoError.SIN_METODO_DESAFIO,
                CodigoMensajeConstantes.ERROR_GENERICO_RSA, TipoDesafioEnum.BANELCO.getId());
    }

}

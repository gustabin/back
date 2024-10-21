/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionFactory;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.BanelcoDesafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasDesafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Cvv2Desafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Desafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.TokenDesafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.challenge.cvv2.bo.Cvv2BO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.bo.SoftTokenBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;
import ar.com.santanderrio.obp.servicios.tarjetascoordenadas.dao.TarjetaCoordenadaDAO;
import ar.com.santanderrio.obp.servicios.token.mobile.entities.TokenMobile;
import ar.com.santanderrio.obp.servicios.token.mobile.manager.TokenMobileManager;

/**
 * Clase que implementa {@link AutentificacionManager} y gestiona la
 * autentificacion de los diferentes metodos en base a sus prioridades.
 *
 * @author marcelo.
 * @author ignacio.valek
 * @author emilio.watemberg
 * @since Sep 23, 2016.
 */
@Component
public class AutentificacionManagerImpl implements AutentificacionManager {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AutentificacionManagerImpl.class);

    /**
     * The Constant
     * LA_OPERACIÓN_SELECCIONADA_ES_INCORRECTA_INEXISTENTE_OPERACION.
     */
    private static final String LA_OPERACION_SELECCIONADA_ES_INCORRECTA_INEXISTENTE_OPERACION = "La operacion seleccionada es incorrecta/inexistente. OPERACION: ";

    /**
     * The Constant AUTENTIFICACION_DE_COORDENADAS_TOKEN_Y_BANELCO_CARGADAS.
     */
    private static final String AUTENTIFICACION_DE_COORDENADAS_TOKEN_Y_BANELCO_CARGADAS = "Autentificacion de coordenadas, token y banelco cargadas";

    /** The Constant AUTENTIFICACIÓN_DE_COORDENADAS_Y_TOKEN_CARGADAS. */
    private static final String AUTENTIFICACION_DE_COORDENADAS_Y_TOKEN_CARGADAS = "Autentificacion de coordenadas y token cargadas";

    /** The Constant AUTENTIFICACIÓN_DE_COORDENADAS_BANELCO_CARGADAS. */
    private static final String AUTENTIFICACION_DE_COORDENADAS_BANELCO_CARGADAS = "Autentificacion de coordenadas, banelco cargadas";

    /** The Constant AUTENTIFICACIÓN_DE_COORDENADAS_CARGADAS. */
    private static final String AUTENTIFICACION_DE_COORDENADAS_CARGADAS = "Autentificacion de coordenadas cargadas";

    /**
     * The Constant
     * OCURRIO_UN_ERROR_AL_CONSULTAR_QUE_DESAFIOS_TIENE_HABILITADO_EL_CLIENTE.
     */
    private static final String OCURRIO_UN_ERROR_AL_CONSULTAR_QUE_DESAFIOS_TIENE_HABILITADO_EL_CLIENTE = "Ocurrio un error al consultar que desafios tiene habilitado el cliente";

    /**
     * The Constant
     * OCURRIO_UN_ERROR_AL_CONSULTAR_RSA_DESDE_EL_AUTENTIFICADOR_MANAGER.
     */
    private static final String OCURRIO_UN_ERROR_AL_CONSULTAR_RSA_DESDE_EL_AUTENTIFICADOR_MANAGER = "Ocurrio un error al consultar RSA desde el Autentificador Manager";

    /** The Constant EL_USUARIO_NO_TIENE_NINGUN_DESAFIO_HABILITADO. */
    private static final String EL_USUARIO_NO_TIENE_NINGUN_DESAFIO_HABILITADO = "El usuario no tiene ningun desafio habilitado.";

    /**
     * The Constant ERROR_AL_VALIDAR_SI_EL_USUARIO_TIENE_UN_DESAFIO_HABILITADO.
     */
    private static final String ERROR_AL_VALIDAR_SI_EL_USUARIO_TIENE_UN_DESAFIO_HABILITADO = "Error al validar si el usuario tiene un desafio habilitado.";

    /** The Constant EL_USUARIO_TIENE_UN_DESAFIO_HABILITADO. */
    private static final String EL_USUARIO_TIENE_UN_DESAFIO_HABILITADO = "El usuario tiene un desafio habilitado";

    /** The Constant SIN_DESAFIO_HABILITADO. */
    private static final String SIN_DESAFIO_HABILITADO = "SIN_DESAFIO_HABILITADO";

    /** The url sin desafio habilitado. */
    @Value("${TRJCOORD.AYUDA.URL}")
    private String urlSinDesafioHabilitado;

    /** The autentificacion factory. */
    @Autowired
    private AutentificacionFactory autentificacionFactory;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The rsa manager. */
    @Autowired
    private RsaManager rsaManager;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The token mobile manager. */
    @Autowired
    private TokenMobileManager tokenMobileManager;

    /** The Constant COORDENADAS_BANELCO. */
    public static final int COORDENADAS = 0;

    /** The Constant COORDENADAS_BANELCO. */
    public static final int COORDENADAS_BANELCO = 1;

    /** The Constant TOKEN_COORDENADAS_BANELCO. */
    public static final int TOKEN_COORDENADAS = 2;

    /** The Constant OCHODIGITOS_TOKEN_COORDENADAS. */
    public static final int TOKEN_COORDENADAS_BANELCO = 3;

    /** The Constant TOKEN_COORDENADAS_BANELCO_CVV2. */
    public static final int TOKEN_COORDENADAS_BANELCO_CVV2 = 4;

    /** The codigo estadistica solicitud coordenadas. */
    private String codigoEstadisticaSolicitudCoordenadas = null;

    /** The codigo estadistica validacion coordenadas. */
    private String codigoEstadisticaValidacionCoordenadas = null;

    /** The codigo estadistica solicitud token. */
    private String codigoEstadisticaSolicitudToken = null;

    /** The codigo estadistica validacion token. */
    private String codigoEstadisticaValidacionToken = null;

    /** The codigo estadistica solicitud Banelco. */
    private String codigoEstadisticaSolicitudBanelco = null;

    /** The codigo estadistica validacion Banelco. */
    private String codigoEstadisticaValidacionBanelco = null;

    /** The codigo estadistica solicitud cvv2. */
    private String codigoEstadisticaSolicitudCvv2 = null;

    /** The codigo estadistica validacion cvv2. */
    private String codigoEstadisticaValidacionCvv2 = null;

    /** The codigo estadistica sin desafios. */
    private String codigoEstadisticaSinDesafios = null;

    /** The mensaje bo. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The soft token BO. */
    @Autowired
    private SoftTokenBO softTokenBO;

    /** The cvv 2 BO. */
    @Autowired
    private Cvv2BO cvv2BO;

	/** Variable tarjeta coordenada dao. */
	@Autowired
	private TarjetaCoordenadaDAO tarjetaCoordenadaDAO;
	
    /**
     * Este atributo permite distinguir entre la llamada al metodo que valida y
     * obtiene el desafio, de la llamada que simplemente valida si tiene o no un
     * desafio habilitado.
     */
    boolean soloEstaVerificandoSiHayDesafios = false;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.
     * AutentificacionManager#ejecutarMetodoValidacion(ar.com.santanderrio.obp.
     * servicios.comun.autentificacion.entities.Desafio)
     */
    public Respuesta<AutentificacionDTO> ejecutarMetodoAutentificacion(AutentificacionDTO autentificacionDTO) {
        Desafio<AutentificacionDTO> desafio = sesionParametros.getDesafioEnCurso();

        return desafio.ejecutar(autentificacionDTO);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.
     * AutentificacionManager#obtenerAutentificacionValidaParaCliente(ar.com.
     * santanderrio.obp.servicios.comun.autentificacion.entities.
     * AutentificacionDTO)
     */
    @Override
    public Respuesta<AutentificacionDTO> obtenerAutentificacionValidaParaCliente(AutentificacionDTO dto) {
        return obtenerAutentificacionValidaParaCliente(dto, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.
     * AutentificacionManager#obtenerMetodoDeAutentificacion(int)
     */
    public Respuesta<AutentificacionDTO> obtenerAutentificacionValidaParaCliente(AutentificacionDTO autentificacionDTO,
            String codigoEstadisticaSolicitud, String codigoEstadisticaValidacion) {
        List<Desafio<AutentificacionDTO>> autentificacionList = new ArrayList<Desafio<AutentificacionDTO>>();
        CoordenadasDesafio coordenadas;
        TokenDesafio token;
        BanelcoDesafio banelco;
        Cvv2Desafio cvv2;
        sesionParametros.setDesafioEnCurso(null);
        LOGGER.info("obteniendo Autentificacion Valida Para Cliente");
        switch (autentificacionDTO.getOperacion()) {
        case COORDENADAS:
            coordenadas = (CoordenadasDesafio) autentificacionFactory.crearAutentificacion(CoordenadasDesafio.class, 2,
                    codigoEstadisticaSolicitudCoordenadas, codigoEstadisticaValidacionCoordenadas, autentificacionDTO,
                    soloEstaVerificandoSiHayDesafios);
            autentificacionList.add(coordenadas);
            LOGGER.info(AUTENTIFICACION_DE_COORDENADAS_CARGADAS);
            break;
        case COORDENADAS_BANELCO:
            coordenadas = (CoordenadasDesafio) autentificacionFactory.crearAutentificacion(CoordenadasDesafio.class, 1,
                    codigoEstadisticaSolicitudCoordenadas, codigoEstadisticaValidacionCoordenadas, autentificacionDTO,
                    soloEstaVerificandoSiHayDesafios);
            banelco = (BanelcoDesafio) autentificacionFactory.crearAutentificacion(BanelcoDesafio.class, 2,
                    codigoEstadisticaSolicitudBanelco, codigoEstadisticaValidacionBanelco, autentificacionDTO);
            autentificacionList.add(banelco);
            autentificacionList.add(coordenadas);
            LOGGER.info(AUTENTIFICACION_DE_COORDENADAS_BANELCO_CARGADAS);
            break;
        case TOKEN_COORDENADAS:
            token = (TokenDesafio) autentificacionFactory.crearAutentificacion(TokenDesafio.class, 1,
                    codigoEstadisticaSolicitudToken, codigoEstadisticaValidacionToken, autentificacionDTO);
            autentificacionList.add(token);
            LOGGER.info(AUTENTIFICACION_DE_COORDENADAS_Y_TOKEN_CARGADAS);
            break;
        case TOKEN_COORDENADAS_BANELCO:
            token = (TokenDesafio) autentificacionFactory.crearAutentificacion(TokenDesafio.class, 1,
                    codigoEstadisticaSolicitudToken, codigoEstadisticaValidacionToken, autentificacionDTO);
            coordenadas = (CoordenadasDesafio) autentificacionFactory.crearAutentificacion(CoordenadasDesafio.class, 2,
                    codigoEstadisticaSolicitudCoordenadas, codigoEstadisticaValidacionCoordenadas, autentificacionDTO,
                    soloEstaVerificandoSiHayDesafios);
            banelco = (BanelcoDesafio) autentificacionFactory.crearAutentificacion(BanelcoDesafio.class, 3,
                    codigoEstadisticaSolicitudBanelco, codigoEstadisticaValidacionBanelco, autentificacionDTO);
            autentificacionList.add(banelco);
            autentificacionList.add(token);
            autentificacionList.add(coordenadas);
            LOGGER.info(AUTENTIFICACION_DE_COORDENADAS_TOKEN_Y_BANELCO_CARGADAS);
            break;
        case TOKEN_COORDENADAS_BANELCO_CVV2:
            token = (TokenDesafio) autentificacionFactory.crearAutentificacion(TokenDesafio.class, 1,
                    codigoEstadisticaSolicitudToken, codigoEstadisticaValidacionToken, autentificacionDTO);
            coordenadas = (CoordenadasDesafio) autentificacionFactory.crearAutentificacion(CoordenadasDesafio.class, 2,
                    codigoEstadisticaSolicitudCoordenadas, codigoEstadisticaValidacionCoordenadas, autentificacionDTO);
            banelco = (BanelcoDesafio) autentificacionFactory.crearAutentificacion(BanelcoDesafio.class, 3,
                    codigoEstadisticaSolicitudBanelco, codigoEstadisticaValidacionBanelco, autentificacionDTO);
            cvv2 = (Cvv2Desafio) autentificacionFactory.crearAutentificacion(Cvv2Desafio.class, 4,
                    codigoEstadisticaSolicitudCvv2, codigoEstadisticaValidacionCvv2, autentificacionDTO);
            autentificacionList.add(banelco);
            autentificacionList.add(token);
            autentificacionList.add(coordenadas);
            autentificacionList.add(cvv2);
            LOGGER.info("Autentificación de coordenadas, token, banelco y cvv2 cargadas");
            break;
        default:
            LOGGER.warn(
                    LA_OPERACION_SELECCIONADA_ES_INCORRECTA_INEXISTENTE_OPERACION + autentificacionDTO.getOperacion());
            break;
        }
        return this.obtenerAutentificacionValida(autentificacionList);
    }

    /**
     * Este metodo retorna el modo de autentificacion válido.
     * 
     * En todas las combinatorias de desafios (lista), el desafio de coordenadas
     * siempre es el ultimo en consultarse. Motivo por el cual, si este desafio
     * no esta habilitado, se setea por defecto el
     * {@link TipoError.SIN_METODO_DESAFIO}.
     *
     * @param autentificacionList
     *            the autentificacion list
     * @return the metodo autentificacion
     */
    private Respuesta<AutentificacionDTO> obtenerAutentificacionValida(
            List<Desafio<AutentificacionDTO>> autentificacionList) {
        // Se ordena por prioridad ascendente (mas chico = mas prioritario)
        Collections.sort(autentificacionList, Desafio.Comparators.PRIORIDAD);
        for (int i = 0; i < autentificacionList.size(); i++) {
            Desafio<AutentificacionDTO> autentificacionMetodo = autentificacionList.get(i);
            Respuesta<AutentificacionDTO> respuesta = autentificacionMetodo.solicitar();
            if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
                String tipoError = respuesta.getItemsMensajeRespuesta().get(0).getTipoError();
                if (!TipoError.SIN_METODO_DESAFIO.getDescripcion().equals(tipoError)) {
                    LOGGER.info(EL_USUARIO_TIENE_UN_DESAFIO_HABILITADO);
                    sesionParametros.setDesafioEnCurso(autentificacionMetodo);
                    return respuesta;
                } else if (i == autentificacionList.size() - 1
                        && TipoError.SIN_METODO_DESAFIO.getDescripcion().equals(tipoError)) {
                    // no tiene desafios habilitados.
                    LOGGER.info(EL_USUARIO_NO_TIENE_NINGUN_DESAFIO_HABILITADO);
                    return respuestaFactory.crearRespuestaErrorPersonalizado(AutentificacionDTO.class,
                            getMensajeErrorSinDesafio(autentificacionMetodo.getAutentificacionDTO().getOperacion()),
                            tipoError);

                }
            } else if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
                // falla con error generico al solicitar si tiene habilitado un
                // desafio.
                LOGGER.info(ERROR_AL_VALIDAR_SI_EL_USUARIO_TIENE_UN_DESAFIO_HABILITADO);
                sesionParametros.setDesafioEnCurso(null);
                return respuesta;
            }
        }
        // si llego a esta instancia es que el usuario no tiene desafio.
        LOGGER.info(EL_USUARIO_NO_TIENE_NINGUN_DESAFIO_HABILITADO);
        return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_GENERICO_RSA);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.
     * AutentificacionManager#setCodigoEstadisticaSolicitudCoordenadas(java.lang
     * .String)
     */
    public void setCodigoEstadisticaSolicitudCoordenadas(String codigoEstadisticaSolicitudCoordenadas) {
        this.codigoEstadisticaSolicitudCoordenadas = codigoEstadisticaSolicitudCoordenadas;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.
     * AutentificacionManager#setCodigoEstadisticaValidacionCoordenadas(java.
     * lang.String)
     */
    public void setCodigoEstadisticaValidacionCoordenadas(String codigoEstadisticaValidacionCoordenadas) {
        this.codigoEstadisticaValidacionCoordenadas = codigoEstadisticaValidacionCoordenadas;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.
     * AutentificacionManager#setCodigoEstadisticaSolicitudToken(java.lang.
     * String)
     */
    public void setCodigoEstadisticaSolicitudToken(String codigoEstadisticaSolicitudToken) {
        this.codigoEstadisticaSolicitudToken = codigoEstadisticaSolicitudToken;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.
     * AutentificacionManager#setCodigoEstadisticaValidacionToken(java.lang.
     * String)
     */
    public void setCodigoEstadisticaValidacionToken(String codigoEstadisticaValidacionToken) {
        this.codigoEstadisticaValidacionToken = codigoEstadisticaValidacionToken;
    }

    /**
     * Gets the codigo estadistica solicitud banelco.
     *
     * @return the codigo estadistica solicitud banelco
     */
    public String getCodigoEstadisticaSolicitudBanelco() {
        return codigoEstadisticaSolicitudBanelco;
    }

    /**
     * Sets the codigo estadistica solicitud banelco.
     *
     * @param codigoEstadisticaSolicitudBanelco
     *            the new codigo estadistica solicitud banelco
     */
    public void setCodigoEstadisticaSolicitudBanelco(String codigoEstadisticaSolicitudBanelco) {
        this.codigoEstadisticaSolicitudBanelco = codigoEstadisticaSolicitudBanelco;
    }

    /**
     * Gets the codigo estadistica validacion banelco.
     *
     * @return the codigo estadistica validacion banelco
     */
    public String getCodigoEstadisticaValidacionBanelco() {
        return codigoEstadisticaValidacionBanelco;
    }

    /**
     * Sets the codigo estadistica validacion banelco.
     *
     * @param codigoEstadisticaValidacionBanelco
     *            the new codigo estadistica validacion banelco
     */
    public void setCodigoEstadisticaValidacionBanelco(String codigoEstadisticaValidacionBanelco) {
        this.codigoEstadisticaValidacionBanelco = codigoEstadisticaValidacionBanelco;
    }

    /**
     * Gets the codigo estadistica solicitud cvv2.
     *
     * @return the codigo estadistica solicitud cvv2
     */
    public String getCodigoEstadisticaSolicitudCvv2() {
        return codigoEstadisticaSolicitudCvv2;
    }

    /**
     * Sets the codigo estadistica solicitud cvv2.
     *
     * @param codigoEstadisticaSolicitudCvv2
     *            the new codigo estadistica solicitud cvv2
     */
    public void setCodigoEstadisticaSolicitudCvv2(String codigoEstadisticaSolicitudCvv2) {
        this.codigoEstadisticaSolicitudCvv2 = codigoEstadisticaSolicitudCvv2;
    }

    /**
     * Gets the codigo estadistica validacion cvv2.
     *
     * @return the codigo estadistica validacion cvv2
     */
    public String getCodigoEstadisticaValidacionCvv2() {
        return codigoEstadisticaValidacionCvv2;
    }

    /**
     * Sets the codigo estadistica validacion cvv2.
     *
     * @param codigoEstadisticaValidacionCvv2
     *            the new codigo estadistica validacion cvv2
     */
    public void setCodigoEstadisticaValidacionCvv2(String codigoEstadisticaValidacionCvv2) {
        this.codigoEstadisticaValidacionCvv2 = codigoEstadisticaValidacionCvv2;
    }

    /**
     * Gets the codigo estadistica sin desafios.
     *
     * @return the codigo estadistica sin desafios
     */
    public String getCodigoEstadisticaSinDesafios() {
        return codigoEstadisticaSinDesafios;
    }

    /**
     * Sets the codigo estadistica sin desafios.
     *
     * @param codigoEstadisticaSinDesafios
     *            the new codigo estadistica sin desafios
     */
    public void setCodigoEstadisticaSinDesafios(String codigoEstadisticaSinDesafios) {
        this.codigoEstadisticaSinDesafios = codigoEstadisticaSinDesafios;
    }

    /**
     * Checks if is verificando si hay desafios.
     *
     * @return true, if is verificando si hay desafios
     */
    public boolean isVerificandoSiHayDesafios() {
        return soloEstaVerificandoSiHayDesafios;
    }

    /**
     * Sets the verificando si hay desafios.
     *
     * @param verificandoSiHayDesafios
     *            the new verificando si hay desafios
     */
    public void setVerificandoSiHayDesafios(boolean verificandoSiHayDesafios) {
        this.soloEstaVerificandoSiHayDesafios = verificandoSiHayDesafios;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.
     * AutentificacionManager#analizarOperacionDeRiesgo(ar.com.santanderrio.obp.
     * servicios.comun.autentificacion.entities.OperacionDeRiesgo, int)
     */
    @Override
    public Respuesta<AutentificacionDTO> analizarRSAyObtenerAutenticacionValida(AutentificacionDTO autentificacionDTO) {
        sesionParametros.setRSACantidadIntentos(0);
        LOGGER.info("analizarRSAyObtenerAutenticacionValida");
        ActionCode actionCode = null;
        if(autentificacionDTO.getCnsActionCodePrevioRsaAnalizar() != null) {
            actionCode = autentificacionDTO.getCnsActionCodePrevioRsaAnalizar();
            autentificacionDTO.setCnsActionCodePrevioRsaAnalizar(null);
            LOGGER.info("NO requiere volver a consultar a rsa ya que se realizo previamente y se mantuvo el actionCode resultante de la invocacion.");
        } else {
            LOGGER.info("obtenerDesafioHabilitadoOperacion");
        	TipoDesafioEnum desafioAplicable = obtenerDesafioHabilitadoOperacion(autentificacionDTO);

        	if (autentificacionDTO.isEvitarRsa()) {
                LOGGER.info("autentificacionDTO.isEvitarRsa(): {}, ActionCode.CHALLENGE", autentificacionDTO.isEvitarRsa());
        		actionCode = ActionCode.CHALLENGE;
        	} else {
        		Respuesta<ActionCode> respuestaRiesgo = rsaManager.analizar(autentificacionDTO.getRsaDTO(), desafioAplicable);

                sesionParametros.setReglaRsaTis(autentificacionDTO.getRsaDTO().getReglaRsaTis());
                autentificacionDTO.getRsaDTO().setReglaRsaTis(null);

        		actionCode = respuestaRiesgo.getRespuesta();
                LOGGER.info("autentificacionDTO.isEvitarRsa(): {}, llamando a rsaManager.analizar(), Action code: {} ", autentificacionDTO.isEvitarRsa(), actionCode);
        	}

        }
        Respuesta<AutentificacionDTO> respuestaAutentificacion = null;
        
        switch (actionCode) {
        case ALLOW:
            respuestaAutentificacion = respuestaFactory.crearRespuestaOk(AutentificacionDTO.class);
            respuestaAutentificacion.setRespuesta(autentificacionDTO);
            LOGGER.info("Action code: Allow " );
            break;
        case DENY:
            respuestaAutentificacion = respuestaFactory.crearRespuestaError(AutentificacionDTO.class, StringUtils.EMPTY,
                    TipoError.DENY_RSA, CodigoMensajeConstantes.DENY_RSA_NUEVO_MENSAJE);
            respuestaAutentificacion.setRespuesta(autentificacionDTO);
            LOGGER.info("Action code: DENY - {}", TipoError.DENY_RSA);
            break;
        case LOCKED:
        	respuestaAutentificacion = new Respuesta<AutentificacionDTO>();
        	autentificacionDTO.setBloquearUsuario(Boolean.TRUE);
        	respuestaAutentificacion.setRespuesta(autentificacionDTO);
        	respuestaAutentificacion.setEstadoRespuesta(EstadoRespuesta.ERROR);
            LOGGER.info("Action code: LOCKED {}", EstadoRespuesta.ERROR);
            break;    
        case CHALLENGE:
            LOGGER.info("Action code: CHALLENGE ");
            LOGGER.info("analizarRSAyObtenerAutenticacionValida + ");
            asignarEstadisticasDeAutenticacion(autentificacionDTO);
            respuestaAutentificacion = obtenerAutentificacionValidaParaCliente(autentificacionDTO);
            if (!autentificacionDTO.getRsaDTO().getIgnorarRSA()) {
                // En el caso que el metodo se encuentre bloquado se notifica a
                // RSA
                if (esBloqueo(respuestaAutentificacion)) {
                    LOGGER.info("Action code: CHALLENGE --- notificando a RSA");
                	rsaManager.notificar(respuestaAutentificacion.getRespuesta(),
                            sesionParametros.getRSACantidadIntentos());
                    respuestaAutentificacion.getRespuesta().setRsaDTO(null);
                }
            }
            break;
        default:
            LOGGER.error("Action code: default -> {} ",  OCURRIO_UN_ERROR_AL_CONSULTAR_RSA_DESDE_EL_AUTENTIFICADOR_MANAGER);
            throw new RobotException(OCURRIO_UN_ERROR_AL_CONSULTAR_RSA_DESDE_EL_AUTENTIFICADOR_MANAGER);
        }
        return respuestaAutentificacion;
    }
    

	/**
	 * Es bloqueo.
	 *
	 * @param respuestaAutentificacion the respuesta autentificacion
	 * @return true, if successful
	 */
	private boolean esBloqueo(Respuesta<AutentificacionDTO> respuestaAutentificacion) {
		if (EstadoRespuesta.WARNING.equals(respuestaAutentificacion.getEstadoRespuesta())
				|| EstadoRespuesta.ERROR.equals(respuestaAutentificacion.getEstadoRespuesta())) {
			return !TipoError.SIN_METODO_DESAFIO.getDescripcion()
					.equals(respuestaAutentificacion.getItemsMensajeRespuesta().get(0).getTipoError())
					&& respuestaAutentificacion.getRespuesta().isReintentosAgotados();
		}
		return false;
	}

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.
     * AutentificacionManager#evaluarDesafioOperacionDeRiesgo(ar.com.
     * santanderrio.obp.servicios.comun.autentificacion.entities.
     * OperacionDeRiesgo,
     * ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.
     * AutentificacionDTO)
     */
    @Override
    public Respuesta<AutentificacionDTO> ejecutarMetodoAutenticacionNotificandoRSA(AutentificacionDTO desafioDto) {
        sesionParametros.setRSACantidadIntentos(sesionParametros.getRSACantidadIntentos() + 1);
        Respuesta<AutentificacionDTO> respEjecucionMetodoAutentificacion = this
                .ejecutarMetodoAutentificacion(desafioDto);

        // si es ERROR no notificar, lo mismo si es un warning con otro tipo de
        // error diferente a REINTENTOS_AGOTADOS
        if ((respEjecucionMetodoAutentificacion.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)
                && respEjecucionMetodoAutentificacion.getRespuesta().isReintentosAgotados()
                || respEjecucionMetodoAutentificacion.getEstadoRespuesta().equals(EstadoRespuesta.OK))) {
            rsaManager.notificar(respEjecucionMetodoAutentificacion.getRespuesta(),
                    sesionParametros.getRSACantidadIntentos());
        }
        respEjecucionMetodoAutentificacion.getRespuesta().setRsaDTO(null);
        // se devuelve la respuesta del desafio por comportamiento que debe
        // tomar front
        return respEjecucionMetodoAutentificacion;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.
     * AutentificacionManager#obtenerDesafioHabilitadoPorOperacion(int)
     */
    @Override
    public Respuesta<Boolean> tieneAlgunDesafioHabilitadoParaLaOperacion(AutentificacionDTO autentificacionDTO) {
        Respuesta<Boolean> respuesta = respuestaFactory.crearRespuestaOk(Boolean.class);
        respuesta.setRespuesta(Boolean.TRUE);
        // definimos un flag para que la operacion de consulta de coordenadas no
        // invoque al servicio para obtener al mismo
        this.soloEstaVerificandoSiHayDesafios = true;
        cleanInstanceValue();
        Respuesta<AutentificacionDTO> respuestaDesafios = obtenerAutentificacionValidaParaCliente(autentificacionDTO);
        sesionParametros.setDesafioEnCurso(null);
        this.soloEstaVerificandoSiHayDesafios = false;
        switch (respuestaDesafios.getEstadoRespuesta()) {
        case OK:
        case WARNING:
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            return respuesta;
        case ERROR:
            respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuesta.setRespuesta(Boolean.FALSE);
            respuesta.addAll(respuestaDesafios.getItemsMensajeRespuesta());
            return respuesta;
        default:
            throw new RobotException(OCURRIO_UN_ERROR_AL_CONSULTAR_QUE_DESAFIOS_TIENE_HABILITADO_EL_CLIENTE);
        }
    }

    /**
     * Obtener desafio habilitado operacion.
     *
     * @param autentificacionDTO the autentificacion DTO
     * @return the tipo desafio enum
     */
    public TipoDesafioEnum obtenerDesafioHabilitadoOperacion(AutentificacionDTO autentificacionDTO) {
    	if (autentificacionDTO.getNoSetearDesafio()) {
            LOGGER.info("autentificacionDTO.getNoSetearDesafio SIN_DESAFIO");
    		return TipoDesafioEnum.SIN_DESAFIO;
    	}
		Respuesta<TipoDesafioEnum> respuestaTipoDesafio = obtenerDesafioHabilitado(autentificacionDTO);
		if (EstadoRespuesta.OK.equals(respuestaTipoDesafio.getEstadoRespuesta())) {
            LOGGER.info("TipoDesafioEnum:  {}", respuestaTipoDesafio.getRespuesta());
			return respuestaTipoDesafio.getRespuesta();
		}
		return null;
    }

    /**
     * Obtener desafio habilitado
     *
     * @param autentificacionDTO the autentificacion DTO
     * @return the respuesta
     */
    private Respuesta<TipoDesafioEnum> obtenerDesafioHabilitado(AutentificacionDTO autentificacionDTO) {
    	if (autentificacionDTO != null) {
    		Respuesta<TipoDesafioEnum> respuesta = respuestaFactory.crearRespuestaOk(TipoDesafioEnum.class);
            this.soloEstaVerificandoSiHayDesafios = true;
            cleanInstanceValue();
            Respuesta<AutentificacionDTO> respuestaDesafios = obtenerAutentificacionValidaParaCliente(autentificacionDTO);
            sesionParametros.setDesafioEnCurso(null);
            this.soloEstaVerificandoSiHayDesafios = false;
    		switch (respuestaDesafios.getEstadoRespuesta()) {
    			case WARNING:
    				respuesta.setRespuesta(respuestaDesafios.getRespuesta().getTipoDesafio());
    				return respuesta;
    			case ERROR:
    				respuesta.addAll(respuestaDesafios.getItemsMensajeRespuesta());
    				break;
    			default:
    				break;
    		}
    		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
    		respuesta.setRespuesta(null);
    		return respuesta;
    	}
    	return null;
    }

    /**
     * Evitar que se arrastren los valores fijados cuando se consulta rsa en
     * otros flujos.
     * 
     */
    private void cleanInstanceValue() {
        codigoEstadisticaSolicitudCoordenadas = null;
        codigoEstadisticaValidacionCoordenadas = null;
        codigoEstadisticaSolicitudToken = null;
        codigoEstadisticaValidacionToken = null;
        codigoEstadisticaSolicitudBanelco = null;
        codigoEstadisticaValidacionBanelco = null;
        codigoEstadisticaSolicitudCvv2 = null;
        codigoEstadisticaValidacionCvv2 = null;
        codigoEstadisticaSinDesafios = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.
     * AutentificacionManager#tieneAlgunDesafioHabilitadoParaLaOperacion(int)
     */
    @Override
    public boolean tieneAlgunDesafioHabilitadoParaLaOperacion(int operacion) {
        boolean tieneToken = softTokenBO.tieneSoftToken();
        boolean tieneCordenadas = tieneTarjCoordHabilitada();
        boolean tieneBanelco = StringUtils.isNotBlank(sesionCliente.getCliente().getUltimosDigitosBanelco());
        boolean tieneCVV2 = cvv2BO.getTieneTrjCred();
        boolean tieneAlgunDesafio = false;
        switch (operacion) {
        case COORDENADAS:
            tieneAlgunDesafio = tieneCordenadas;
            break;
        case COORDENADAS_BANELCO:
            tieneAlgunDesafio = tieneCordenadas || tieneBanelco;
            break;
        case TOKEN_COORDENADAS:
            tieneAlgunDesafio = tieneToken;
            break;
        case TOKEN_COORDENADAS_BANELCO:
            tieneAlgunDesafio = tieneToken || tieneCordenadas || tieneBanelco;
            break;
        case TOKEN_COORDENADAS_BANELCO_CVV2:
            tieneAlgunDesafio = tieneToken || tieneCordenadas || tieneBanelco || tieneCVV2;
            break;
        default:
            LOGGER.warn(LA_OPERACION_SELECCIONADA_ES_INCORRECTA_INEXISTENTE_OPERACION + operacion);
        }
        return tieneAlgunDesafio;
    }

    /**
     * Valida si el cliente tiene tarjeta de coordenadas hablitada
     * @return
     */
    private boolean tieneTarjCoordHabilitada() {
    	boolean tarjCoordHabilitada = Boolean.FALSE;
    	
    	List<String> listaNups = new ArrayList<String>();
    	try {
    		//Se obtiene lista de nups con clientes a los que se les dio de baja la tarjeta de coordenadas
    		listaNups = tarjetaCoordenadaDAO.obtener();
    	} catch (DAOException e) {
    		LOGGER.info("Error al obtener listado de nups de baja de coordenadas");
    	}
    	boolean nupClienteEnListado = Boolean.FALSE;
    	for (String nup : listaNups) {
    		if (nup.equals(sesionCliente.getCliente().getNup())) {
    			nupClienteEnListado = Boolean.TRUE;
    			break;
    		}   
    	}

    	if(!nupClienteEnListado && sesionCliente.getCliente().tieneTarjetaCoordenadas()){
    		tarjCoordHabilitada = Boolean.TRUE;
    	}
    	return tarjCoordHabilitada;
    }
    /**
     * Este metodo retorna el mensaje que debe mostrarse en pantalla.
     * 
     * Valida que si el conjunto de desafios incluye o no BANELCO. Si el
     * conjunto incluye BANELCO, se especifica un mensaje de error diferente que
     * si el conjunto no incluye BANELCO.
     * 
     * @author emilio.watemberg
     * @param operacion
     *            the operacion
     * @return the mensaje error sin desafio
     * @since Jul 21, 2017.
     */
    private String getMensajeErrorSinDesafio(int operacion) {
        Mensaje mensaje = null;
        if (operacion == TOKEN_COORDENADAS_BANELCO || operacion == COORDENADAS_BANELCO) {
            // ya se sabe que no tiene coordenadas
            mensaje = mensajeBO.obtenerMensajePorCodigo(
                    CodigoMensajeConstantes.CODIGO_ERROR_SIN_DESAFIO_HABILITADO_SIN_TARJETA_BANELCO);
        } else {
            // podria tener tarjetasa de coordenadas pero no ser necesario para
            // validar la operacion
            if (tieneTarjetaBanelco()) {
                mensaje = mensajeBO.obtenerMensajePorCodigo(
                        CodigoMensajeConstantes.CODIGO_ERROR_SIN_DESAFIO_HABILITADO_CON_TARJETA_BANELCO);
            } else {
                mensaje = mensajeBO.obtenerMensajePorCodigo(
                        CodigoMensajeConstantes.CODIGO_ERROR_SIN_DESAFIO_HABILITADO_SIN_TARJETA_BANELCO);
            }
        }
        return mensaje.getMensaje();
    }

    /**
     * Este metodo valida si el cliente tiene o no habilitada una tarjeta
     * banelco. Se usa para diferenciar el mensaje de feedback cuando se valida
     * que no tiene ningun desafio habilitado del conjunto de desafios
     * consultados.
     *
     * @return true, if successful
     */
    private boolean tieneTarjetaBanelco() {
        return StringUtils.isNotBlank(sesionCliente.getCliente().getUltimosDigitosBanelco());
    }

    /**
     * Ejecuta la validacion de RSA. Si tiene un desafio en curso, obtiene la
     * respuesta del mismo. Caso contrario, analizar RSA para obtener un
     * desafio.
     *
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @return the respuesta
     */
    @Override
    public Respuesta<AutentificacionDTO> ejecutarValidacionRSA(AutentificacionDTO autentificacionDTO) {
        if (sesionParametros.isExisteDesafioEnCurso()) {
            LOGGER.info("Obteniendo el desafio e curso.");
            return obtenerRespuestaDesafioEnCurso(autentificacionDTO);
        }
        LOGGER.info("Consultando RSA y evaluando metodos de autentificacion.");
        asignarEstadisticasDeAutenticacion(autentificacionDTO);
        return analizarRsaObteniendoUnDesafio(autentificacionDTO);
    }

    /**
     * Dice si es necesario generar un AutentificacionDTO para generar un nuevo
     * desafio y actualiza el estado del desafio en sesion.
     *
     * @param autentificacionDTO
     *            autentificacion que viene del request de frontend
     * @return true, si lo necesita
     */
    @Override
    public boolean necesitaNuevoDesafio(AutentificacionDTO autentificacionDTO) {
        if (sesionParametros.isExisteDesafioEnCurso() && autentificacionDTO != null) {
            LOGGER.info("Existe desafio en curso");
            return false;
        }
        LOGGER.info("No existe desafio en curso");
        sesionParametros.setExisteDesafioEnCurso(false);
        return true;
    }

    /**
     * OK: Continua a verificar el desafio actual. WARNING: Es necesario generar
     * un AutentificacionDTO para generar un nuevo desafio y actualiza el estado
     * del desafio en sesion. ERROR: No tiene desafios habilitados para la
     * operacion.
     *
     * @param <T>
     *            the generic type
     * @param autentificacionDTO
     *            autentificacion que viene del request de frontend
     * @param operacion
     *            the operacion
     * @return true, si lo necesita
     */
    @Override
    public <T> Respuesta<T> verificarEstadoDesafio(AutentificacionDTO autentificacionDTO, int operacion) {
        Respuesta<T> respuesta = new Respuesta<T>();
        if (sesionParametros.isExisteDesafioEnCurso() && autentificacionDTO != null) {
            LOGGER.info("Existe desafio en curso");
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        } else if (tieneAlgunDesafioHabilitadoParaLaOperacion(operacion)) {
            LOGGER.info("No existe desafio en curso");
            sesionParametros.setExisteDesafioEnCurso(false);
            respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        } else {
            LOGGER.info("No tiene desafios habilitados para la operacion");
            boolean tieneBanelco = StringUtils.isNotBlank(sesionCliente.getCliente().getUltimosDigitosBanelco());
            if (tieneBanelco) {
                respuesta = respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(SIN_DESAFIO_HABILITADO,
                        CodigoMensajeConstantes.CODIGO_ERROR_SIN_DESAFIO_HABILITADO_CON_TARJETA_BANELCO);
            } else {
                respuesta = respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(SIN_DESAFIO_HABILITADO,
                        CodigoMensajeConstantes.CODIGO_ERROR_SIN_DESAFIO_HABILITADO_SIN_TARJETA_BANELCO);
            }
            respuesta.getItemsMensajeRespuesta().get(0).setExtra(urlSinDesafioHabilitado);
        }
        return respuesta;
    }

    /**
     * Obtiene la respuesta del desafio en curso.
     *
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> obtenerRespuestaDesafioEnCurso(AutentificacionDTO autentificacionDTO) {
        LOGGER.info("obtener Respuesta Desafio En Curso ..");
        // ****** TODO: esto se debe sacar, cuando se unifique todo en
        // operacionCoordenadaDTO.
        if (autentificacionDTO.getCoordenadasOperacion() != null) {
            autentificacionDTO.getCoordenadasOperacion().setPedidoCoordenada(new PedidoCoordenada(
                    TarjetaCoordenadaOperacionEnum.VALIDACION, sesionParametros.getRegistroSession().getIp()));
        } // *************
        Respuesta<AutentificacionDTO> respuestaActualizarDesafio = actualizarDesafio(autentificacionDTO);
        if (EstadoRespuesta.ERROR.equals(respuestaActualizarDesafio.getEstadoRespuesta())) {
            estadisticaManager.add(sesionParametros.getDesafioEnCurso().getCodigoEstadisticaValidacion(),
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            sesionParametros.resetearDesafioEnCurso();
            LOGGER.info("Error: {}, Código Error {}: ",TipoError.ERROR_GENERICO.getDescripcion(), CodigoMensajeConstantes.ERROR_GENERICO_RSA );
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_GENERICO_RSA);
        }

        Respuesta<AutentificacionDTO> rtaAutentificacion = this.ejecutarMetodoAutentificacion(autentificacionDTO);
        if (rtaAutentificacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
            sesionParametros.resetearDesafioEnCurso();
            sesionParametros.setRSACantidadIntentos(sesionParametros.getRSACantidadIntentos() + 1);
            if (!autentificacionDTO.isEvitarRsa()) {
                LOGGER.info("obtenerRespuestaDesafioEnCurso : ***Notificando a RSA...***");
            	rsaManager.notificar(rtaAutentificacion.getRespuesta(), sesionParametros.getRSACantidadIntentos());
            }
            
            return respuestaFactory.crearRespuestaOk(AutentificacionDTO.class, autentificacionDTO);
        } else if (rtaAutentificacion.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
        	String tipoErrorRsa = rtaAutentificacion.getItemsMensajeRespuesta().get(0).getTipoError();
    		if (TipoError.ERROR_SYNC_TOKEN.getDescripcion().equals(tipoErrorRsa) ||
    				TipoError.DEBITO_NUMEROS_EQUIVOCADOS.getDescripcion().equals(tipoErrorRsa)) {
    			// Validacion incorrecta de Token o Debito
    			sesionParametros.setRSACantidadIntentos(sesionParametros.getRSACantidadIntentos() + 1);
    		} else if (TipoError.ERROR_BLOQUEO_TOKEN.getDescripcion().equals(tipoErrorRsa)
    				|| TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion().equals(tipoErrorRsa)
    				|| TipoError.DEBITO_BLOQUEADA.getDescripcion().equals(tipoErrorRsa)) {
    			// Bloqueo de Token, bloqueo de Coordenadas o bloqueo de Debito al validar
    			sesionParametros.setRSACantidadIntentos(sesionParametros.getRSACantidadIntentos() + 1);
				rsaManager.notificar(rtaAutentificacion.getRespuesta(), sesionParametros.getRSACantidadIntentos());
    		}
            return respuestaFactory.crearRespuestaWarning(AutentificacionDTO.class, autentificacionDTO,
                    rtaAutentificacion.getItemsMensajeRespuesta());
        } else {
        	String tipoErrorRsa = rtaAutentificacion.getItemsMensajeRespuesta().get(0).getTipoError();
        	if (TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion().equals(tipoErrorRsa)) {
        		// Bloqueo de Coordenadas al solicitarlas automaticamente luego de ingresar valores incorrectos
				rsaManager.notificar(rtaAutentificacion.getRespuesta(), sesionParametros.getRSACantidadIntentos());
        	}
            sesionParametros.resetearDesafioEnCurso();
            return respuestaFactory.crearRespuestaError(AutentificacionDTO.class,
                    rtaAutentificacion.getItemsMensajeRespuesta());
        }

    }

    /**
     * Actualizar desafio.
     *
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> actualizarDesafio(AutentificacionDTO autentificacionDTO) {
        LOGGER.info("Actualizando desafío");
        if (sesionParametros.getRegistroSession().isMobile()) {
            Desafio<AutentificacionDTO> desafio = sesionParametros.getDesafioEnCurso();
            if (desafio instanceof TokenDesafio && autentificacionDTO.getTokenOperacion().getIngresoToken() == null) {
                Respuesta<TokenMobile> respuesta = tokenMobileManager
                        .consultarTokenMobile(sesionCliente.getCliente().getNup());
                LOGGER.info("Nup: {}", sesionCliente.getCliente().getNup() );
                if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
                    autentificacionDTO.getTokenOperacion().setIngresoToken(respuesta.getRespuesta().getToken());
                    LOGGER.info("Token: {}", respuesta.getRespuesta().getToken() );
                } else {
                    return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                            CodigoMensajeConstantes.ERROR_GENERICO_RSA);
                }
            }
        }
        return respuestaFactory.crearRespuestaOk(AutentificacionDTO.class, autentificacionDTO);
    }

    /**
     * Analiza RSA obteniendo un desafio.
     *
     * @param autentificacionDTO
     *            the datos confirmacion recarga
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> analizarRsaObteniendoUnDesafio(AutentificacionDTO autentificacionDTO) {
        Respuesta<AutentificacionDTO> respuestaAutentificacion = this
                .analizarRSAyObtenerAutenticacionValida(autentificacionDTO);
        if (respuestaAutentificacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
            LOGGER.info("La operacion no requiere un desafio adicional");
            if (this.codigoEstadisticaSinDesafios != null) {
                estadisticaManager.add(codigoEstadisticaSinDesafios, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            }
            sesionParametros.resetearDesafioEnCurso();
            return respuestaAutentificacion;
        } else {
            if (respuestaAutentificacion.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
                LOGGER.info("Desafiando al usuario con "
                        + respuestaAutentificacion.getRespuesta().getTipoDesafio().getDescripcion());
                sesionParametros.setExisteDesafioEnCurso(true);
                return respuestaAutentificacion;
            }
        }
        LOGGER.info("Operacion Desafio con Deny.");
        sesionParametros.resetearDesafioEnCurso();
        
        if(EstadoRespuesta.ERROR.equals(respuestaAutentificacion.getEstadoRespuesta()) && 
        	TipoError.SIN_METODO_DESAFIO.toString().equals(respuestaAutentificacion.getItemsMensajeRespuesta().get(0).getTipoError())) {
        	return respuestaAutentificacion;
        }
        
        if(respuestaAutentificacion.getRespuesta().getBloquearUsuario()){
        	return respuestaAutentificacion;
        }
        return respuestaFactory.crearRespuestaError(AutentificacionDTO.class,
                respuestaAutentificacion.getItemsMensajeRespuesta());
    }

    /**
     * Asigna la estadisticas de autenticacion.
     *
     * @param autentificacionDTO
     *            the autentificacion DTO
     */
    private void asignarEstadisticasDeAutenticacion(AutentificacionDTO autentificacionDTO) {
        this.setCodigoEstadisticaSolicitudToken(autentificacionDTO.getCodigoEstadisticaSolicitudToken());
        this.setCodigoEstadisticaValidacionToken(autentificacionDTO.getCodigoEstadisticaValidacionToken());
        this.setCodigoEstadisticaSolicitudCoordenadas(autentificacionDTO.getCodigoEstadisticaSolicitudCoordenadas());
        this.setCodigoEstadisticaValidacionCoordenadas(autentificacionDTO.getCodigoEstadisticaValidacionCoordenadas());
        this.setCodigoEstadisticaSolicitudBanelco(autentificacionDTO.getCodigoEstadisticaSolicitudBanelco());
        this.setCodigoEstadisticaValidacionBanelco(autentificacionDTO.getCodigoEstadisticaValidacionBanelco());
        this.setCodigoEstadisticaSinDesafios(autentificacionDTO.getCodigoEstadisticaSinDesafios());
    }

}

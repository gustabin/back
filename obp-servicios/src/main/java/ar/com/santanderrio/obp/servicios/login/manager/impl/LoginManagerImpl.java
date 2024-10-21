/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.biocatch.BiocatchManager;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityName;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.jwt.JwtToken;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.filter.FilterConstants;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.UserStatus;
import ar.com.santanderrio.obp.generated.webservices.rsa.WSUserType;
import ar.com.santanderrio.obp.servicios.chat.web.manager.ChatManager;
import ar.com.santanderrio.obp.servicios.clave.online.entities.CredencialesClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.SesionExpiradaException;
import ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager;
import ar.com.santanderrio.obp.servicios.clave.online.util.ClaveOnlineUtils;
import ar.com.santanderrio.obp.servicios.clave.online.view.AltaClaveOnlineView;
import ar.com.santanderrio.obp.servicios.clave.online.view.MetodoAutenticacionView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.LogIn;
import ar.com.santanderrio.obp.servicios.comun.campaniapublica.bo.CampaniaPublicaBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.TipoTeclado;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.rsa.web.controller.util.RSAControllerUtil;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.exception.SessionManagerException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialMicrofrontDto;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.WebContentView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.web.manager.OfertaComercialManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.AdministradorPermisos;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.login.bo.ApiAuthBO;
import ar.com.santanderrio.obp.servicios.login.bo.LoginBO;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.encrypines.EncryPines;
import ar.com.santanderrio.obp.servicios.login.entity.ApiAuthToken;
import ar.com.santanderrio.obp.servicios.login.entity.OperacionesLoginOBEEnum;
import ar.com.santanderrio.obp.servicios.login.manager.LoginManager;
import ar.com.santanderrio.obp.servicios.login.manager.MyaManager;
import ar.com.santanderrio.obp.servicios.login.manager.TokenManager;
import ar.com.santanderrio.obp.servicios.login.manager.microfront.impl.MicrofrontManagerImpl;
import ar.com.santanderrio.obp.servicios.login.view.CredencialesLoginView;
import ar.com.santanderrio.obp.servicios.login.view.CsidView;
import ar.com.santanderrio.obp.servicios.login.view.DatosMyaView;
import ar.com.santanderrio.obp.servicios.login.view.InicioLoginView;
import ar.com.santanderrio.obp.servicios.login.view.LogOutResponseView;
import ar.com.santanderrio.obp.servicios.login.view.LoginResponseView;
import ar.com.santanderrio.obp.servicios.login.view.LoginView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.perfil.bo.ActualizarNombreBO;
import ar.com.santanderrio.obp.servicios.queue.bo.QueueSTBO;
import ar.com.santanderrio.obp.servicios.queue.entities.QueueSTDTO;
import ar.com.santanderrio.obp.servicios.queue.entities.QueueSTOperations;
import ar.com.santanderrio.obp.servicios.queue.entities.TurnStatesEnum;
import ar.com.santanderrio.obp.servicios.queue.utils.QueueSTUtils;
import ar.com.santanderrio.obp.servicios.referidos.manager.ReferidosManager;
import ar.com.santanderrio.obp.servicios.referidos.view.ReferidosInicioResponseView;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaCreateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericResponseData;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.session.manager.SessionControlManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 *
 * Created by pablo.martin.gore on 9/2/2016.
 */
@Component
public class LoginManagerImpl implements LoginManager {

    private static final boolean FROM_APP = true;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginManagerImpl.class);

    /** Formato de Fecha *. */
    private static final String FORMATO_FECHA = "ddMMyyyy";

    /** The Constant ERROR_NO_IDENTIFICADO_DE_RSA. */
    private static final String ERROR_NO_IDENTIFICADO_DE_RSA = "Error No identificado de RSA";

	/** The Constant RSA_FAULT. */
	private static final String RSA_FAULT = "-1";
	
	private static final String RESPUESTA_OK = "0";
	
	/** The Constant TEXTO_LOGGER_LINK . */
	private static final String TEXTO_LOGGER_LINK = "Se retorna el link {}.";
	
    /**
     * Listado de host a los cuales se les debe permitir entrar a obp sin consultar
     */
    @Value("#{'${TBANCO.HOST.LIST.NO.JUMP}'.split(',')}")
    private List<String> hostList;

    /** Token Manager *. */
    @Autowired
    private TokenManager tokenManager;

    /** The cliente manager. */
    @Autowired
    private ClienteManager clienteManager;

    /** The clave online manager. */
    @Autowired
    private ClaveOnlineManager claveOnlineManager;

    /** The session control manager. */
    @Autowired
    private SessionControlManager sessionControlManager;

    /** The login BO. */
    @Autowired
    private LoginBO loginBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The Session Paramatros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The Session Paramatros. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The mya manager. */
    @Autowired
    private MyaManager myaManager;

    /** The mensaje manager. */
    @Autowired
    private MensajeManager mensajeManager;

    /** The rsa manager. */
    @Autowired
    private RsaManager rsaManager;

    /** The ModuloPermiso BO. */
    @Autowired
    private ModuloPermisoBO moduloPermisoBO;

    /** The encry pines. */
    @Autowired
    private EncryPines encryPines;
    
    /** The oferta comercial manager. */
    @Autowired
    private OfertaComercialManager ofertaComercialManager;
    
    /** The chat manager. */
    @Autowired
    private ChatManager chatManager;
    
    @Autowired
    private CampaniaPublicaBO campaniaPublicaBO;

    @Autowired
	private AdministradorPermisos administradorPermisos;
    
	@Autowired
	private ReferidosManager referidosManager;

    /** The queue ST BO. */
    @Autowired
    private QueueSTBO queueSTBO;

    @Autowired
    private MicrofrontManagerImpl microfrontManager;
    
    @Autowired
    private ActualizarNombreBO actualizarNombreBO;
 
    @Autowired
    private ApiAuthBO authenticatorBO;
    
	@Value("${JWT_DEV_MODE:false}")
	private boolean jwtDevMode;

	@Value("${API-AUTH.REFRESH-TIME:120000}")
	private Integer apiAuthRefreshTime;

    @Autowired
    private BiocatchManager biocatchManager;
    
    @Value("${BIOCATCH-API.LEGACY-GETSCORELOGIN:true}")
    private boolean biocatchLegacyGetScoreLogin;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.login.manager.LoginManager#loginOBE(ar.com.
     * santanderrio.obp.servicios.login.view.LoginView,
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Respuesta<LoginResponseView> loginOBE(LoginView loginView, HttpServletRequest request, HttpServletResponse response) {
        if (!moduloPermisoBO.tienePermisoMostrar(AccionController.SALTO_OBE_TBANCO)) {
            LOGGER.info("No esta permitido ingresar desde OBE, token {}.", loginView.getToken());
            request.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
            return respuestaFactory.crearRespuestaError(LoginResponseView.class, null, TipoError.LOGIN_ERROR_TOTAL,
                    CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
        }
        sesionParametros.setCsid(loginView.getCsid());
        Respuesta<LoginResponseView> respuestaObe = this.loginFromOBE(loginView, request, response);
        if (EstadoRespuesta.OK.equals(respuestaObe.getEstadoRespuesta())) {
            estadisticaManager.add(EstadisticasConstants.SALTO_OBE_TBANCO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            estadisticaManager.add(EstadisticasConstants.SALTO_OBE_TBANCO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return respuestaObe;
    }

    public Respuesta<LoginResponseView> loginAPP(LoginView loginView, HttpServletRequest request, HttpServletResponse response) {
        if (!moduloPermisoBO.tienePermisoMostrar(AccionController.SALTO_APP_TBANCO)) {
            LOGGER.info("No esta permitido ingresar desde APP, token {}.", loginView.getToken());
            request.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
            return respuestaFactory.crearRespuestaError(LoginResponseView.class, null, TipoError.LOGIN_ERROR_TOTAL,
                    CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
        }
        sesionParametros.setCsid(loginView.getCsid());
        Respuesta<LoginResponseView> respuestaApp = this.loginAppToken(loginView, request, response);
        if (EstadoRespuesta.OK.equals(respuestaApp.getEstadoRespuesta())) {
            estadisticaManager.add(EstadisticasConstants.SALTO_APP_TBANCO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            estadisticaManager.add(EstadisticasConstants.SALTO_APP_TBANCO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return respuestaApp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.login.manager.LoginManager#login(java.lang.
     * String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Respuesta<LoginResponseView> login(String datoEntrada, HttpServletRequest request, HttpServletResponse response) {
        String cryptoInput = encryPines.obtenerSoloCrypto(datoEntrada);
        LoginView loginView = encryPines.obtenerViewFromJson(cryptoInput, LoginView.class);
        boolean queueSTHabilitado = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.QUEUE_ST_LOGIN).tienePermisoDeVisibilidad()
				&& QueueSTUtils.verificarHorarioQueue(QueueSTOperations.LOGIN);
        sesionParametros.setCsid(loginView.getCsid());
        if (queueSTHabilitado && !validarTurno(loginView.getwId())) {
        	return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
        }
        Respuesta<LoginResponseView> respuestaLogin = this.login(loginView, request, response);
        if (queueSTHabilitado && respuestaLogin != null && EstadoRespuesta.OK.equals(respuestaLogin.getEstadoRespuesta())) {
        	queueSTBO.actualizarTurno(loginView.getwId(), TurnStatesEnum.TURN_STATUS_FINISHED);
        }
        
        return respuestaLogin;
    }

    /**
     * Validar turno.
     *
     * @param turnId the turn id
     * @return true, if successful
     */
    private boolean validarTurno(String turnId) {
		Respuesta<QueueSTDTO> respuestaTurno = queueSTBO.verificarTurno(turnId);
		if (moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.QUEUE_ST_LOGIN_NO_WAIT)
				.tienePermisoDeVisibilidad()) {
			return true;
		}
		if (EstadoRespuesta.OK.equals(respuestaTurno.getEstadoRespuesta())) {
			QueueSTDTO queueSTDTO = respuestaTurno.getRespuesta();
			return QueueSTUtils.esTurnoHabilitado(queueSTDTO.getTurn());
		} else if (EstadoRespuesta.ERROR.equals(respuestaTurno.getEstadoRespuesta())
				&& TipoError.QUEUE_ERROR_TURN_NOT_FOUND.getDescripcion()
						.equals(respuestaTurno.getItemsMensajeRespuesta().get(0).getTipoError())) {
			return false;			
		}
		// Ante falla se permite continuar
		return true;
	}

    private Respuesta<LoginResponseView> loginFromOBE(LoginView loginView, HttpServletRequest request,
                                               HttpServletResponse response) {
        return this.login(loginView, request, response, !FROM_APP, true);
    }


    /**
     * Por default ----> login convencional
     * @param loginView
     * @param request
     * @param response
     * @return
     */
    private Respuesta<LoginResponseView> login(LoginView loginView, HttpServletRequest request,
			HttpServletResponse response) {
		return this.login(loginView, request, response, !FROM_APP);
	}
    
	/**
	 * Para cambiar la forma de desencriptar el token
	 * @param loginView
	 * @param request
	 * @param response
	 * @return
	 */
	private Respuesta<LoginResponseView> loginAppToken(LoginView loginView, HttpServletRequest request,
			HttpServletResponse response) {
		return this.login(loginView, request, response, FROM_APP);
	}


    private Respuesta<LoginResponseView> login(LoginView loginView, HttpServletRequest request, HttpServletResponse response, boolean fromApp) {
        return this.login(loginView, request,  response, fromApp, false);
    }

        /**
         * Login.
         *
         * @param loginView
         *            the login view
         * @param request
         *            the request
         * @param response
         *            the response
         * @return the respuesta
         */
    private Respuesta<LoginResponseView> login(LoginView loginView, HttpServletRequest request, HttpServletResponse response, boolean fromApp, boolean fromObe) {
        if (isLoginDeshabilitado()) {
            Respuesta<Void> respuesta = respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(
                    "Por el momento, no estamos disponibles. " + "Estamos realizando tareas de mantenimiento",
                    TipoError.LOGIN_DESHABILITADO.getDescripcion());
            LOGGER.info("No se permite continuar, Login deshabilitado.");
            request.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
            return Respuesta.copy(LoginResponseView.class, respuesta);
        }
        CredencialCliente credenciales = obtenerCredenciales(loginView, request);

        UsernamePasswordAuthenticationToken autenticacion = obtenerAuthentication(loginView, request, credenciales);

        Respuesta<LoginResponseView> respuestaLoginResponseView;

        if(fromApp) {
        	respuestaLoginResponseView = clienteManager.loginTokenApp(autenticacion, loginView, request, response);
        } else {
        	respuestaLoginResponseView = clienteManager.login(autenticacion, loginView, request, response);
        }

        // Analiza RSA
        if (!EstadoRespuesta.ERROR.equals(respuestaLoginResponseView.getEstadoRespuesta())) {
            sesionCliente.setIpCliente(NetworkUtil.getRemoteIp(request));

            BiocatchResponseDataDTO biocatchData = getBiocatchResponseData(fromApp, fromObe);

            Respuesta<Void> respuesta = analyzeRsa(StringUtils.EMPTY, biocatchData, fromApp, fromObe);
            if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
                LOGGER.info("No se permite continuar, se cierra la session {}.", respuesta);
                request.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
                respuesta.setSkipLog(Boolean.TRUE);
                if(sesionCliente.getAuthCliente().isApiAuthClient()) {
                	authenticatorBO.logout(request);
                }
                grabarEstadisticaError();
                return Respuesta.copy(LoginResponseView.class, respuesta);
            }
        }

        if (EstadoRespuesta.OK.equals(respuestaLoginResponseView.getEstadoRespuesta())) {
            LOGGER.info("Consultar a mensajes y avisos.");
            respuestaLoginResponseView = myaManager.obtenerMya(respuestaLoginResponseView);
            if (null != respuestaLoginResponseView.getRespuesta().getCredencialesMya().getEmail()) {
            	sesionCliente.getCliente().setEmailLoginMya(respuestaLoginResponseView.getRespuesta().getCredencialesMya().getEmail());
            }            
         
            LOGGER.info("Consultar al gestor comercial.");
            ofertaComercialManager.obtenerOfertasComerciales();
            
            if (StringUtils.isNotBlank(loginView.getCampaign())) {            	
            	try {
            		WebContentView webContent = evaluarCampaignInLogin(loginView.getCampaign());         
                	respuestaLoginResponseView.getRespuesta().setContenido(webContent);                	
                } catch (NullPointerException e) {
                    LOGGER.error("error generando el salto");
                }
             }                
        }

        if (invalidate(respuestaLoginResponseView)) {
            LOGGER.info("No se permite continuar, se cierra la session {}.", respuestaLoginResponseView);
            request.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
            respuestaLoginResponseView.setSkipLog(Boolean.TRUE);
            if(sesionCliente.getAuthCliente().isApiAuthClient()) {
            	authenticatorBO.logout(request);
            }
        }

        if (EstadoRespuesta.OK.equals(respuestaLoginResponseView.getEstadoRespuesta())) {
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LOGIN,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            
            String respuestaActualizar = actualizarNombreBO.actualizarNombreCliente(sesionCliente.getCliente());
            if (RESPUESTA_OK.equals(respuestaActualizar)) {
            	estadisticaManager.add(EstadisticasConstants.ACTUALIZACION_NOMBRE_BASE_DATOS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            } else {
            	estadisticaManager.add(EstadisticasConstants.ACTUALIZACION_NOMBRE_BASE_DATOS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
            
        }
        if (EstadoRespuesta.WARNING.equals(respuestaLoginResponseView.getEstadoRespuesta())
                && !contieneTipoError(respuestaLoginResponseView.getItemsMensajeRespuesta(),
                        TipoError.SUSCRIPCION_MYA)) {
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LOGIN,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
        return respuestaLoginResponseView;
    }

    private BiocatchResponseDataDTO getBiocatchResponseData(boolean fromApp, boolean fromObe) {
        BiocatchResponseDataDTO biocatchData = null;
        if(fromApp) {
            biocatchManager.init(sesionCliente.getCliente().getNup(), sesionCliente.getIpCliente(), ActivityName.LOGIN_APP);
        }
        else if(fromObe){
                biocatchManager.init(sesionCliente.getCliente().getNup(), sesionCliente.getIpCliente(), ActivityName.LOGIN_OBE);
        }
        else {
            biocatchData = biocatchManager.getScoreLogin(sesionCliente.getCliente().getNup(), sesionCliente.getIpCliente());
        }
        return biocatchData;
    }

    /**
     * Contiene tipo error.
     *
     * @param itemsMensajeRespuesta
     *            the items mensaje respuesta
     * @param tipoError
     *            the tipo error
     * @return the boolean
     */
    private Boolean contieneTipoError(List<ItemMensajeRespuesta> itemsMensajeRespuesta, TipoError tipoError) {

        if (!CollectionUtils.isEmpty(itemsMensajeRespuesta)) {
            for (ItemMensajeRespuesta item : itemsMensajeRespuesta) {
                if (tipoError.getDescripcion().equals(item.getTipoError())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Invalidate.
     *
     * @param loginResponse
     *            the login response
     * @return the boolean
     */
    private Boolean invalidate(Respuesta<LoginResponseView> loginResponse) {

        if (!EstadoRespuesta.OK.equals(loginResponse.getEstadoRespuesta())) {
            if (CollectionUtils.isNotEmpty(loginResponse.getItemsMensajeRespuesta())) {

                for (ItemMensajeRespuesta item : loginResponse.getItemsMensajeRespuesta()) {

                    if (TipoError.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS.getDescripcion().equals(item.getTipoError())
                            || TipoError.LOGIN_DOCUMENTO_HOMONIMO.getDescripcion().equals(item.getTipoError())
                            || TipoError.LOGIN_ERROR_USUARIO_NO_DEFINIDO.getDescripcion().equals(item.getTipoError())
                            || TipoError.LOGIN_ERROR_USUARIO_CAMBIO_PENDIENTE.getDescripcion()
                                    .equals(item.getTipoError())
                            || TipoError.SUSCRIPCION_MYA.getDescripcion().equals(item.getTipoError())) {
                        return false;
                    }
                }
            }

            return true;
        }
        return false;
    }

    /**
     * Obtener authentication.
     *
     * @param loginView
     *            the login view
     * @param request
     *            the request
     * @param credenciales
     *            the credenciales
     * @return the username password authentication token
     */
    private UsernamePasswordAuthenticationToken obtenerAuthentication(LoginView loginView, HttpServletRequest request,
            CredencialCliente credenciales) {
        RsaGenericRequestData rsaGenericRequestData = RSAControllerUtil.getRsaGenericRequestData(request,
                loginView.getDevicePrint());
        sesionParametros.setRsaGenericRequestData(rsaGenericRequestData);
        UsernamePasswordAuthenticationToken autenticacion = new UsernamePasswordAuthenticationToken(
                rsaGenericRequestData, credenciales);
        return autenticacion;
    }

    /**
     * Obtener credenciales.
     *
     * @param loginView
     *            the login view
     * @param request
     *            the request
     * @return the credencial cliente
     */
    private CredencialCliente obtenerCredenciales(LoginView loginView, HttpServletRequest request) {
        CredencialCliente credenciales = new CredencialCliente();
        credenciales.setClave(loginView.getClave());
        credenciales.setDni(loginView.getDni());
        credenciales.setDniOri(loginView.getDniOri());
        credenciales.setUsuario(loginView.getUsuario());
        credenciales.setUsuarioNuevo(loginView.getUsuarioVacio());
        credenciales.setClaveNueva(loginView.getClaveVacia());
        credenciales.setPrefijo(loginView.getPrefijo());
        credenciales.setSufijo(loginView.getSufijo());
        credenciales
                .setFechaNacimiento(FechaUtils.parsearFechaNacimiento(loginView.getFechaDeNacimiento(), FORMATO_FECHA));
        credenciales.setDispositivo(loginView.getDispositivo());
        credenciales.setNavegador(loginView.getNavegador());
        credenciales.setIdSesion(request.getSession().getId());
        credenciales.setInstancia(buildInstancia(request));
        credenciales.setHostName(NetworkUtil.getHostName());
        credenciales.setIp(NetworkUtil.getRemoteIp(request));
        credenciales.setToken(loginView.getToken());
        return credenciales;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.login.manager.LoginManager#logout(javax
     * .servlet.http.HttpServletRequest)
     */
    @Override
    public Respuesta<LogOutResponseView> logout(HttpServletRequest request) {
        Respuesta<LogOutResponseView> respuesta = this.respuestaFactory.crearRespuestaOk(new LogOutResponseView());
        respuesta.setSkipLog(Boolean.TRUE);
        try {
            if (request.getSession() != null) {            	
                limpiarTokenEnBase();
                if (sesionParametros.getDetalleSesionChat() != null){
                	chatManager.desconectar();
                }
                if (StringUtils.isNotBlank(sesionCliente.getCliente().getQueueTurnId())) {
                	queueSTBO.actualizarTurno(sesionCliente.getCliente().getQueueTurnId(), TurnStatesEnum.TURN_STATUS_FINISHED);
                }
                if(sesionCliente.getAuthCliente().isApiAuthClient()) {
                	authenticatorBO.logout(request);
                }
            }            
        } catch (SessionManagerException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(LogOutResponseView.class, new LogOutResponseView(), null,
                    TipoError.LOGIN_ERROR_CONTROL_SESSION, CodigoMensajeConstantes.LOGIN_ERROR_CONTROL_SESSION);
        } catch (Exception e) {
            LOGGER.error("Error no manejado", e);
            respuesta = respuestaFactory.crearRespuestaError(LogOutResponseView.class, new LogOutResponseView(), null,
                    TipoError.LOGIN_ERROR_CONTROL_SESSION, CodigoMensajeConstantes.LOGIN_ERROR_CONTROL_SESSION);
        }
        estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LOGOUT,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        request.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
        return respuesta;
    }

    /**
     * 
     */
    private void limpiarTokenEnBase() {
        if(clienteManager.obtenerClienteSesion() != null) { 
            sessionControlManager.saveOrUpdate(new Long(clienteManager.obtenerClienteSesion().getNup()), null);                
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.manager.LoginManager#refresh(
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Respuesta<LoginResponseView> refresh(HttpServletRequest request, HttpServletResponse response) {
    	LOGGER.info("refresh nup: {} login api-auth:{} ",sesionCliente.getResumenCliente().getNup() ,sesionCliente.getAuthCliente().isApiAuthClient());
    	if(sesionCliente.getAuthCliente().isApiAuthClient()) {
        	JwtToken token = getJWT(request);
        	try {
    			Jws<Claims> claims = token.verifyToken(sesionCliente.getResumenCliente().getNup());
    			Date expirationTime = claims.getBody().getExpiration();
    			if(System.currentTimeMillis() + apiAuthRefreshTime > expirationTime.getTime()) {
    				if (token.getToken().equals(sesionCliente.getAuthCliente().getLastToken())) {
    					return getRespuestaLoginResponseView(sesionCliente.getAuthCliente().getAuthJWT());
    				}
    				ResumenCliente resumenCliente = sesionCliente.getResumenCliente();
    				if (sesionCliente.getAuthCliente().getRefreshToken() == null) {
    					esperarLlamadaencurso();
    					return getRespuestaLoginResponseView(sesionCliente.getAuthCliente().getAuthJWT());
    				}
    				String refreshToken = sesionCliente.getAuthCliente().getRefreshToken();
    				sesionCliente.getAuthCliente().setRefreshToken(null);
    				Respuesta<ApiAuthToken> respuesta = authenticatorBO.refreshToken(sesionCliente.getAuthCliente().getAuthJWT(), refreshToken);
    				if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
    					LOGGER.error("Error al guardar resumen cliente en obp-api-users: ");
    					request.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
    					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
    				}			sesionCliente.getAuthCliente().setLastToken(token.getToken());
    				sesionCliente.getAuthCliente().setAuthJWT(respuesta.getRespuesta().getAccessToken());
    				sesionCliente.getAuthCliente().setRefreshToken(respuesta.getRespuesta().getRefreshToken());
    				if (!jwtDevMode) {
    					tokenManager.grabarOmodificarToken(resumenCliente, respuesta.getRespuesta().getAccessToken());
    					sesionParametros.setJwt(respuesta.getRespuesta().getAccessToken());
    					tokenManager.grabarOmodificarCookieSC(respuesta.getRespuesta().getAccessToken(), request, response);
    				}
    				return getRespuestaLoginResponseView(respuesta.getRespuesta().getAccessToken());
    				}
    				return getRespuestaLoginResponseView(token.getToken());
	    		} catch (Exception e ) {
	    			request.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
	    			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
	    		}    		
    		} else {
    			return tokenManager.refresh(request, response);
    	}
    		
    }

	private void esperarLlamadaencurso() {
		for (int i=0; sesionCliente.getAuthCliente().getRefreshToken() == null && i<10; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.error(e.getMessage());
			}
		}
	}
    
    private Respuesta<LoginResponseView> getRespuestaLoginResponseView(String token) {
    	LoginResponseView view = new LoginResponseView();
    	view.setToken(token);
		return respuestaFactory.crearRespuestaOk(view);
	}

	private JwtToken getJWT(HttpServletRequest request) {
    	return JwtToken.parseToken(request.getHeader(JwtToken.AUTHORIZATION_HEADER));
    }

    /**
     * Builds the instancia.
     *
     * @param request
     *            the request
     * @return the string
     */
    private String buildInstancia(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return (request.isSecure() ? "https" : "http") + "://" + request.getLocalAddr() + ":" + request.getLocalPort()
                + uri.substring(0, uri.indexOf('/', 2));

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.manager.LoginManager#
     * obtenerIncioLogin()
     */
    @Override
    public Respuesta<InicioLoginView> obtenerIncioLogin() {

        Respuesta<InicioLoginView> respuestaInicioLoginView = new Respuesta<InicioLoginView>();
        InicioLoginView inicioLoginView = new InicioLoginView();
        respuestaInicioLoginView.setRespuesta(inicioLoginView);

        Respuesta<Mensaje> respuestaMensaje = loginBO.obtenerInicioLogin();

        inicioLoginView.setMensajeLogin(respuestaMensaje.getRespuesta().getMensaje());
        respuestaInicioLoginView.setEstadoRespuesta(respuestaMensaje.getEstadoRespuesta());

        return respuestaInicioLoginView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.manager.LoginManager#
     * grabarEstadisticaUsuarioClaveExpiradas()
     */
    @Override
    public Respuesta<Void> grabarEstadisticaUsuarioClaveExpiradas() {

        estadisticaManager.add(EstadisticasConstants.INICIO_CLAVE_USUARIO_EXPIRADOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        return respuestaFactory.crearRespuestaOk(Void.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.manager.LoginManager#
     * loginConfirmarUsuario(java.lang.String,
     * org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<FeedbackMensajeView> loginConfirmarUsuario(String datoEntrada, MessageContext mc) {
        String cryptoInput = encryPines.obtenerSoloCrypto(datoEntrada);
        CredencialesLoginView credencialesLoginView = encryPines.obtenerViewFromJson(cryptoInput,
                CredencialesLoginView.class);

        RegistroSesion registroSesion = sesionParametros.getRegistroSession();
        if (registroSesion != null) {
            registroSesion
                    .setTipoTeclado(TipoTeclado.getTipoTecladoFromDescripcion(credencialesLoginView.getTipoTeclado()));
        }
        Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();

        CredencialCliente credencialCliente = guardarCredenciales(mc.getHttpServletRequest(), credencialesLoginView,
                registroSesion);

        credencialCliente = buildCredencialCliente(mc.getHttpServletRequest(), credencialesLoginView, registroSesion,
                credencialCliente);

        if (credencialesLoginView.isFromClaveOnline()) {
        	
        	ResumenCliente resumenCliente = new ResumenCliente();
        	resumenCliente.setNup(sesionParametros.getCredencialesClaveOnline().getNup());
        	sesionCliente.setResumenCliente(resumenCliente);
        	Respuesta<Void> respuestaRSA = analyzeRsa("CHANGE_PASSWORD_GENERACION_CLAVE", null, false, false);
        	
            if (EstadoRespuesta.ERROR.equals(respuestaRSA.getEstadoRespuesta())) {
            	return respuestaFactory.crearRespuestaError("", TipoError.LOGIN_ERROR_TOTAL, CodigoMensajeConstantes.DENY_RSA_NUEVO_MENSAJE);
            }
        	
            Respuesta<AltaClaveOnlineView> respuestaAltaSGIClave = claveOnlineManager.altaSGIClave(credencialCliente,
                    mc);
            String codEstadistica = ClaveOnlineUtils.getEstadistica(mc.getHttpServletRequest(), EstadisticasConstants.CODIGO_TRANSACION_LOGIN_GENERACION_CLAVE, 
					EstadisticasConstants.CODIGO_TRANSACION_LOGIN_GENERACION_CLAVE_WEBVIEW);
            if (respuestaAltaSGIClave.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
                credencialCliente = actualizarCredenciales(credencialCliente, registroSesion, credencialesLoginView);
                respuesta = clienteManager.loginConfirmarUsuario(credencialCliente, mc.getHttpServletRequest());
                estadisticaManager.add(codEstadistica,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            } else {
                respuesta = Respuesta.copy(FeedbackMensajeView.class, respuestaAltaSGIClave);
                estadisticaManager.add(codEstadistica,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        } else {
            respuesta = clienteManager.loginConfirmarUsuario(credencialCliente, mc.getHttpServletRequest());
        }

        return respuesta;
    }

    /**
     * Builds the credencial cliente.
     *
     * @param request
     *            the request
     * @param credencialesLoginView
     *            the credenciales login view
     * @param registroSesion
     *            the registro sesion
     * @param credencialCliente
     *            the credencial cliente
     * @return the credencial cliente
     */
    private CredencialCliente buildCredencialCliente(HttpServletRequest request,
            CredencialesLoginView credencialesLoginView, RegistroSesion registroSesion,
            CredencialCliente credencialCliente) {
        credencialCliente.setClave(registroSesion.getClave());
        credencialCliente.setClaveNueva(registroSesion.getClaveNueva());
        credencialCliente.setUsuario(credencialesLoginView.getUsuarioActual());
        credencialCliente.setUsuarioNuevo(credencialesLoginView.getUsuarioNuevo());
        credencialCliente.setIsAlta(registroSesion.getIsAlta());
        credencialCliente.setDni(registroSesion.getDniEncriptado());

        if (credencialesLoginView.isFromClaveOnline()) {
            credencialCliente.setIp(NetworkUtil.getRemoteIp(request));
            credencialCliente.setDniOri(registroSesion.getDni());
            credencialCliente.setDni(registroSesion.getDniEncriptado());
            credencialCliente.setIdSesion(registroSesion.getDatosAutenticacion().getSesion());
            credencialCliente.setUsuarioNuevo(credencialesLoginView.getUsuarioActual());
            credencialCliente.setUsuario(credencialesLoginView.getUsuarioNuevo());
            credencialCliente.setIsAlta(Boolean.FALSE);
        }
        return credencialCliente;
    }

    /**
     * Actualizar credenciales.
     *
     * @param credencialCliente
     *            the credencial cliente
     * @param registroSesion
     *            the registro sesion
     * @param credencialesLoginView
     *            the credenciales login view
     * @return the credencial cliente
     */
    private CredencialCliente actualizarCredenciales(CredencialCliente credencialCliente, RegistroSesion registroSesion,
            CredencialesLoginView credencialesLoginView) {
        credencialCliente.setClave(registroSesion.getClaveNuevaFormateada());
        credencialCliente.setUsuario(credencialesLoginView.getUsuarioNuevoFormateado());
        return credencialCliente;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.manager.LoginManager#
     * loginConfirmarClave(java.lang.String,
     * org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<Void> loginConfirmarClave(String datoEntrada, MessageContext mc) {
        String cryptoInput = encryPines.obtenerSoloCrypto(datoEntrada);
        CredencialesLoginView credencialesLoginView = encryPines.obtenerViewFromJson(cryptoInput,
                CredencialesLoginView.class);

        RegistroSesion registroSesion = sesionParametros.getRegistroSession();
        if (registroSesion != null) {
            registroSesion.setClave(credencialesLoginView.getClaveActual());
            registroSesion.setClaveNueva(credencialesLoginView.getClaveNueva());
            registroSesion
                    .setTipoTeclado(TipoTeclado.getTipoTecladoFromDescripcion(credencialesLoginView.getTipoTeclado()));
            if (credencialesLoginView.isFromClaveOnline()) {
                try {
                    if (mc != null) {
                        claveOnlineManager.controlarTimeOutSesion(mc.getHttpServletRequest());
                    }
                } catch (SesionExpiradaException e) {
                    LOGGER.error(e.getMessage(), e);
                    Respuesta<MetodoAutenticacionView> respuesta = claveOnlineManager.crearRespuestaSesionExpirada();
                    respuesta.setSkipLog(Boolean.TRUE);
                    return Respuesta.copy(Void.class, respuesta);
                }
                registroSesion.setDniEncriptado(credencialesLoginView.getDni());
                registroSesion.setClaveNueva(credencialesLoginView.getClaveActual());
                registroSesion.setClave(credencialesLoginView.getClaveNueva());
                registroSesion.setClaveNuevaFormateada(credencialesLoginView.getClaveNuevaFormateada());
            }
            sesionParametros.setRegistroSession(registroSesion);
        }
        return respuestaFactory.crearRespuestaOk(Void.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.manager.LoginManager#
     * loginCUEIngresoHome()
     */
    @Override
    public Respuesta<LoginResponseView> loginIngresoHome(HttpServletRequest request, HttpServletResponse response) {
        ResumenCliente resumenCliente = sesionCliente.getResumenCliente();

        Respuesta<LoginResponseView> respuestaLoginResponseView = clienteManager
                .realizarControlSesionObtenerClienteConSaldo(resumenCliente, null, null, request, response);
        if (EstadoRespuesta.ERROR.equals(respuestaLoginResponseView.getEstadoRespuesta())&& sesionCliente.getAuthCliente().isApiAuthClient()) {
        	authenticatorBO.logout(request);
        }
        // Analiza RSA
        if (!EstadoRespuesta.ERROR.equals(respuestaLoginResponseView.getEstadoRespuesta())) {
            sesionCliente.setIpCliente(NetworkUtil.getRemoteIp(request));
            Respuesta<Void> respuesta = analyzeRsa(StringUtils.EMPTY, null, false, false);

            if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
                LOGGER.info("Se cierra la session, no se permite continuar con este session de usuario {}.", respuesta);
                request.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
                respuesta.setSkipLog(Boolean.TRUE);
                grabarEstadisticaError();
                if(sesionCliente.getAuthCliente().isApiAuthClient()) {
                	authenticatorBO.logout(request);
                }
                return Respuesta.copy(LoginResponseView.class, respuesta);
            }
        }

        if (EstadoRespuesta.OK.equals(respuestaLoginResponseView.getEstadoRespuesta())) {
            respuestaLoginResponseView = myaManager.obtenerMya(respuestaLoginResponseView);
            if (null != respuestaLoginResponseView.getRespuesta().getCredencialesMya().getEmail() && !"".equalsIgnoreCase(respuestaLoginResponseView.getRespuesta().getCredencialesMya().getEmail())) {
            	sesionCliente.getCliente().setEmailLoginMya(respuestaLoginResponseView.getRespuesta().getCredencialesMya().getEmail());
            }            
            LOGGER.info("Consultar al gestor comercial.");
            ofertaComercialManager.obtenerOfertasComerciales();
        }

        // Spring security
        if (!EstadoRespuesta.ERROR.equals(respuestaLoginResponseView.getEstadoRespuesta())) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    resumenCliente.getDni(), resumenCliente, new ArrayList<GrantedAuthority>()));
        }
        if (EstadoRespuesta.OK.equals(respuestaLoginResponseView.getEstadoRespuesta())) {
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LOGIN,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
        if (EstadoRespuesta.WARNING.equals(respuestaLoginResponseView.getEstadoRespuesta())
                && !contieneTipoError(respuestaLoginResponseView.getItemsMensajeRespuesta(),
                        TipoError.SUSCRIPCION_MYA)) {
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LOGIN,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
        actualizarRegistroSessionConNup(resumenCliente);
        return respuestaLoginResponseView;
    }

    /**
     * La validacion del token utilizan el registroSession por lo que requiere que
     * el nup este seteado.
     *
     * @param resumenCliente
     *            the resumen cliente
     */
    private void actualizarRegistroSessionConNup(ResumenCliente resumenCliente) {
        RegistroSesion registroSesion = sesionParametros.getRegistroSession();
        if (StringUtils.isEmpty(registroSesion.getNup())) {
            registroSesion.setNup(resumenCliente.getNup());
        }

    }

    /**
     * Definir nuevo usuario.
     *
     * @param request
     *            the request
     * @param datoEntrada
     *            the dato entrada
     * @return the respuesta
     */
    @Override
    public Respuesta<FeedbackMensajeView> loginDefinirUsuario(HttpServletRequest request, String datoEntrada) {
        String cryptoInput = encryPines.obtenerSoloCrypto(datoEntrada);
        CredencialesLoginView credencialesLoginView = encryPines.obtenerViewFromJson(cryptoInput,
                CredencialesLoginView.class);

        RegistroSesion registroSesion = sesionParametros.getRegistroSession();

        if (registroSesion != null) {
            registroSesion
                    .setTipoTeclado(TipoTeclado.getTipoTecladoFromDescripcion(credencialesLoginView.getTipoTeclado()));
        }

        CredencialCliente credencialCliente = guardarCredenciales(request, credencialesLoginView, registroSesion);

        // clave vacia encriptada
        credencialCliente.setUsuario(registroSesion.getUsuarioNuevo());
        // usuario ingresado por el cliente
        credencialCliente.setUsuarioNuevo(credencialesLoginView.getUsuarioNuevo());

        credencialCliente.setClave(registroSesion.getClave());

        // clave vacia encriptada
        credencialCliente.setClaveNueva(registroSesion.getClaveNueva());

        credencialCliente.setIsAlta(true);

        return clienteManager.loginDefinirUsuario(credencialCliente);
    }

    /**
     * Guarda datos de login en credencialesCliente.
     *
     * @param request
     *            the request
     * @param credencialesLoginView
     *            the credenciales login view
     * @param registroSesion
     *            the registro sesion
     * @return the credencial cliente
     */
    private CredencialCliente guardarCredenciales(HttpServletRequest request,
            CredencialesLoginView credencialesLoginView, RegistroSesion registroSesion) {

        CredencialCliente credencialCliente = new CredencialCliente();

        credencialCliente.setIsAlta(registroSesion.getIsAlta());
        credencialCliente.setDniOri(registroSesion.getDni());
        credencialCliente.setDni(registroSesion.getDniEncriptado());
        if (credencialesLoginView.isFromClaveOnline()) {
            CredencialesClaveOnline credencialesClaveOnline = sesionParametros.getCredencialesClaveOnline();
            credencialCliente.setFechaNacimiento(
                    FechaUtils.parsearFechaNacimiento(credencialesClaveOnline.getFechaDeNacimiento(), FORMATO_FECHA));
        } else {
            Date fecha = FechaUtils.parsearFechaNacimiento(registroSesion.getFechaNacimiento(), FORMATO_FECHA);
            if (fecha == null)
                fecha = FechaUtils.parsearFechaNacimiento(registroSesion.getSinonimo(), "yyyyMMdd");

            credencialCliente.setFechaNacimiento(fecha);
        }
        credencialCliente.setPrefijo(registroSesion.getPrefijo());
        credencialCliente.setSufijo(registroSesion.getSufijo());
        credencialCliente.setIp(NetworkUtil.getRemoteIp(request));
        return credencialCliente;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.manager.LoginManager#
     * cambioUsuarioPendiente(javax.servlet.http.HttpServletRequest,
     * ar.com.santanderrio.obp.servicios.login.view.CredencialesLoginView)
     */
    @Override
    public Respuesta<FeedbackMensajeView> cambioUsuarioPendiente(HttpServletRequest request,
            CredencialesLoginView credencialesLoginView) {

        RegistroSesion registroSesion = sesionParametros.getRegistroSession();

        CredencialCliente credencialCliente = guardarCredenciales(request, credencialesLoginView, registroSesion);

        // clave encriptada
        credencialCliente.setUsuario(credencialesLoginView.getUsuarioActual());
        // usuario ingresado por el cliente
        credencialCliente.setUsuarioNuevo(credencialesLoginView.getUsuarioNuevo());

        credencialCliente.setClave(registroSesion.getClave());

        // clave encriptada
        credencialCliente.setClaveNueva(registroSesion.getClaveNueva());

        credencialCliente.setIsAlta(false);

        return clienteManager.cambioUsuarioPendiente(credencialCliente);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.manager.LoginManager#
     * confirmarDatosMya(ar.com.santanderrio.obp.servicios.login.view. DatosMyaView,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    public Respuesta<LoginResponseView> confirmarDatosMya(DatosMyaView datosMyaView, HttpServletRequest request) {
        LoginResponseView loginResponseView = new LoginResponseView();
        Respuesta<LoginResponseView> respuestaLoginResponseView = respuestaFactory
                .crearRespuestaOk(LoginResponseView.class);
        Respuesta<CredencialesMya> respuesta = myaManager.confirmarDatosMya(datosMyaView);

        if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
            LOGGER.info("Se cierra la session, no se permite continuar con este session de usuario {}.", respuesta);
            respuesta.setSkipLog(Boolean.TRUE);
            respuestaLoginResponseView = Respuesta.copy(LoginResponseView.class, respuesta);
            request.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LOGIN,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }

        if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
            Mensaje mensaje = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.MYA_FEEDBACK_OK);
            respuestaLoginResponseView = Respuesta.copy(LoginResponseView.class, respuesta);
            if (!isWarningCelular(respuesta)) {
                loginResponseView.setMensaje(mensaje.getMensaje());
                estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LOGIN,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            }
            respuestaLoginResponseView.setRespuesta(loginResponseView);
        }

        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            Mensaje mensaje = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.MYA_FEEDBACK_OK);
            loginResponseView.setMensaje(mensaje.getMensaje());
            respuestaLoginResponseView.setRespuesta(loginResponseView);
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LOGIN,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }

        return respuestaLoginResponseView;
    }

    /**
     * Checks if is warning celular.
     *
     * @param respuesta
     *            the respuesta
     * @return true, if is warning celular
     */
    private boolean isWarningCelular(Respuesta<CredencialesMya> respuesta) {
        return EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta()) && TipoError.MYA_CELULAR_YA_REGISTRADO
                .getDescripcion().equals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.manager.LoginManager#
     * obtenerTerminosCondicionesPDF()
     */
    @Override
    public Respuesta<ReporteView> obtenerTerminosCondicionesPDF() {
        return myaManager.obtenerTerminosCondicionesPDF();
    }

    /**
     * Analyze rsa.
     *
     * @author juan.pablo.gatto
     * @return the respuesta
     */
    @Override
    public Respuesta<Void> analyzeRsa(String lugarIngreso, BiocatchResponseDataDTO biocatchData, boolean fromApp, boolean fromObe) {

        Respuesta<Void> respuestaLoginResponseView = respuestaFactory.crearRespuestaOk(Void.class);
        sesionParametros.setRsaEstado(new HashMap<OperacionesRSAEnum, Boolean>(rsaManager.getRsaEstado()));
        RegistroSesion registroSesion = sesionParametros.getRegistroSession();
        LogIn login = new LogIn();
        login.setIpClient(registroSesion.getIp());
        login.setLugarIngreso(lugarIngreso);
        login.setBiocatchResponseData(biocatchData);
        login.setFromApp(fromApp);
        login.setFromObe(fromObe);

        // verifica en el properties si el servicio debe operar con RSA, de lo
        // contrario siempre solicitara desafio.
        if (!rsaManager.isServicioActivo(login.getTipoOperacion())) {
            respuestaLoginResponseView = Respuesta.copy(Void.class, respuestaWARNING());
            return respuestaLoginResponseView;
        }
        RsaAnalyzeRequestData rsaAnalyzeRequestData = rsaManager.crearRsaAnalyzeRequestData(login, null);

        Respuesta<RsaAnalyzeResponseData> respuesta = analizarLogin(rsaAnalyzeRequestData);

        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            sesionCliente.setTieneTokenRSA(Boolean.TRUE);
            if (UserStatus.LOCKOUT.value().equals(respuesta.getRespuesta().getIdUserStatus())){
            	estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LOGIN,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            	 return respuestaFactory.crearRespuestaError(Void.class, null, TipoError.LOGIN_RSA_USUARIO_BLOQUEADO,
                         CodigoMensajeConstantes.RSA_LOGIN_USUARIO_BLOQUEADO);
            }
            if (ActionCode.DENY.equals(respuesta.getRespuesta().getActionCode())) {
                estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LOGIN,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                return respuestaFactory.crearRespuestaError(Void.class, null, TipoError.LOGIN_RSA_DENY,
                        CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
            }
            RsaAnalyzeResponseData resp = respuesta.getRespuesta();
            if (ActionCode.ALLOW.equals(resp.getActionCode())) {
                if (UserStatus.NOTENROLLED.value().equals(resp.getIdUserStatus())
                        || UserStatus.DELETE.value().equals(resp.getIdUserStatus())) {

                    String deviceTokenCookie = resp.getRsaGenericResponseData().getDeviceTokenCookie();
                    Cliente cliente = sesionCliente.getCliente();
                    RsaCreateUserRequestData createUserRequestData = generateRsaCreateUserRequestData(deviceTokenCookie,
                            cliente);

                    Respuesta<RsaGenericResponseData> respuestaEnrollar = rsaManager.enrolarUser(createUserRequestData);
                    if (EstadoRespuesta.ERROR.equals(respuestaEnrollar.getEstadoRespuesta())) {
                        ItemMensajeRespuesta itemMensajeRespuesta = respuestaEnrollar.getItemsMensajeRespuesta().get(0);
                        if (ERROR_NO_IDENTIFICADO_DE_RSA.equals(itemMensajeRespuesta.getTipoError())) {
                            respuestaLoginResponseView = Respuesta.copy(Void.class, respuestaWARNING());
                            respuestaLoginResponseView
                                    .setItemMensajeRespuesta(respuestaEnrollar.getItemsMensajeRespuesta());
                        }
                    }
                }
            }
            respuestaLoginResponseView = Respuesta.copy(Void.class, respuesta);
        } else {
            sesionCliente.setTieneTokenRSA(Boolean.FALSE);
            respuestaLoginResponseView = Respuesta.copy(Void.class, respuesta);
        }
        return respuestaLoginResponseView;
    }

    /**
     * Verifica el enrolamiento de un cliente si existió un error, se devuelve un
     * warning.
     *
     * @param rsaAnalyzeRequestData
     *            the rsa analyze request data
     * @return the respuesta
     */
    public Respuesta<RsaAnalyzeResponseData> analizarLogin(RsaAnalyzeRequestData rsaAnalyzeRequestData) {
        Respuesta<RsaAnalyzeResponseData> respuesta = new Respuesta<RsaAnalyzeResponseData>();

        Respuesta<RsaAnalyzeResponseData> respuestaRsaAnalyze = rsaManager.analizarRsa(rsaAnalyzeRequestData,
                Boolean.TRUE);
        if (EstadoRespuesta.ERROR.equals(respuestaRsaAnalyze.getEstadoRespuesta())) {
    		ItemMensajeRespuesta item = respuestaRsaAnalyze.getItemsMensajeRespuesta().get(0);
    		if (item != null && RSA_FAULT.equals(item.getTag())) {
    			estadisticaManager.add(EstadisticasConstants.RSA_FAULT,
    					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, 
    					CodigoMensajeConstantes.ERROR_GENERICO);
    		}
            respuesta = Respuesta.copy(RsaAnalyzeResponseData.class, respuestaWARNING());
            return respuesta;
        } else {
            actualizarSesionRsa(rsaAnalyzeRequestData.getRsaGenericRequestData(),
                    respuestaRsaAnalyze.getRespuesta().getRsaGenericResponseData());
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            respuesta.setRespuesta(respuestaRsaAnalyze.getRespuesta());
            return respuesta;
        }
    }

    /**
     * Respuesta WARNING.
     *
     * @return the respuesta
     */
    private Respuesta<ActionCode> respuestaWARNING() {
        Respuesta<ActionCode> respuesta;
        respuesta = new Respuesta<ActionCode>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuesta.setRespuesta(ActionCode.CHALLENGE);
        return respuesta;
    }

    /**
     * Actualiza el ultimo request y response de RSA en la sesion.
     *
     * @param rsaGenericRequestData
     *            the rsa generic request data
     * @param rsaGenericResponseData
     *            the rsa generic response data
     */
    private void actualizarSesionRsa(RsaGenericRequestData rsaGenericRequestData,
            RsaGenericResponseData rsaGenericResponseData) {
        sesionParametros.setRsaGenericRequestData(rsaGenericRequestData);
        sesionParametros.setRsaGenericResponseData(rsaGenericResponseData);

    }

    /**
     * Generate rsa create user request data.
     *
     * @param deviceTokenCookie
     *            the device token cookie
     * @param cliente
     *            the cliente
     * @return the rsa create user request data
     */
    private RsaCreateUserRequestData generateRsaCreateUserRequestData(String deviceTokenCookie,
            ResumenCliente cliente) {
        RsaCreateUserRequestData createUserRequestData = new RsaCreateUserRequestData();
        createUserRequestData.getRsaGenericRequestData().setDeviceTokenCookie(deviceTokenCookie);
        createUserRequestData.setEventType(EventType.SESSION_SIGNIN.value());
        createUserRequestData.setUserType(WSUserType.PERSISTENT.value());
        createUserRequestData.setUserStatus(UserStatus.VERIFIED.value());
        createUserRequestData.getRsaGenericRequestData().setResumenCliente(cliente);
        return createUserRequestData;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.manager.LoginManager#release(
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    public Respuesta<LogOutResponseView> release(HttpServletRequest request) {
        if (sesionParametros.getJwtTokenActivo()) {
            LOGGER.info("Se esta haciendo una invocacion para liberar la session fuera del flujo pre login permitido.");
            return respuestaFactory.crearRespuestaError(LogOutResponseView.class, null, TipoError.PRE_LOGIN_ERROR,
                    null);
        } else {
            Respuesta<LogOutResponseView> respuesta = respuestaFactory.crearRespuestaOk(new LogOutResponseView());
            respuesta.setSkipLog(Boolean.TRUE);
            request.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
            return respuesta;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.manager.LoginManager#
     * cargarFlagFreeTbanco(org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public void cargarFlagFreeTbanco(MessageContext mc) {
        HttpServletRequest request = mc.getHttpServletRequest();
        final String refered = request.getHeader("Wil");
        LOGGER.debug("Dominio recibido para evaluar si mantiene en el sitio o efectua salto {}.", refered);
        boolean hostValido = CollectionUtils.exists(hostList, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                String host = (String) object;
                return StringUtils.isNotEmpty(refered) && StringUtils.contains(refered, host);
            }
        });
        LOGGER.debug(
                "Validar ({}) si el dominio '{}' entrante esta en la lista {} que mantienen al cliente en TBANCO y no lo redirecciona a obp.",
                hostValido, refered, hostList);
        String ip = NetworkUtil.getRemoteIp(request);
        if (hostValido && StringUtils.startsWith(ip, "180.")) {
            LOGGER.info(
                    "Se sube marca en session para en caso de estar habilitado la consulta a la tabla la ignore para quedarse en TBANCO.");
            sesionParametros.setIgnoraTablaTbanco(Boolean.TRUE);
        }
    }

    /**
     * Tiene permiso ofertas comerciales.
     *
     * @return true, if successful
     */
    public boolean isLoginDeshabilitado() {
        return moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGIN_HBILITADO).isModuloOculto();
    }
    
    public WebContentView evaluarCampaignInLogin(String campaign) {      
    	LOGGER.info("Se recibe la campania {} en el login, evaluando los goto.", campaign);        
        WebContentView webContent = new WebContentView();        
        if (OperacionesLoginOBEEnum.CONSTITUCION_PLAZOFIJO.getOperacion().equals(campaign)) { 
        	String gotoCampania = campaign + "()";
        	LOGGER.info("Se encontro la operacion de OBE con la que se logueo {}.", gotoCampania);
        	webContent.setLink(gotoCampania);
            webContent.setTitulo("");
        	LOGGER.info(TEXTO_LOGGER_LINK, OperacionesLoginOBEEnum.CONSTITUCION_PLAZOFIJO);
    	} else if (OperacionesLoginOBEEnum.MFCDA.getOperacion().equals(campaign.split("\\|")[0])) {
    		LOGGER.info(TEXTO_LOGGER_LINK, campaign);
    		try {                
                goToCentroDeAyudaMap(campaign, webContent);   			
    		} catch (BadRequestException ex) {
    			LOGGER.info("Fallo al cargar campania del centro de ayuda publico: {}", ex.getMessage());
    		}
    	} else if (OperacionesLoginOBEEnum.LICITACIONESRTL.getOperacion().equals(campaign)
        		|| OperacionesLoginOBEEnum.LICITACIONESBP.getOperacion().equals(campaign)
        		|| OperacionesLoginOBEEnum.TITULOS.getOperacion().equals(campaign)
        		|| OperacionesLoginOBEEnum.MFETFRTL.getOperacion().equals(campaign)
        		|| OperacionesLoginOBEEnum.MFETFBP.getOperacion().equals(campaign)
        		|| OperacionesLoginOBEEnum.MFETF.getOperacion().equals(campaign)
        		) {
        	String gotoCampania = campaign;
        	LOGGER.info("Se encontro la operacion de OBE con la que se logueo {}.", gotoCampania);
        	webContent.setLink(gotoCampania);
            administradorPermisos.addNewGrant(AccionController.IR_INICIO_TITULOS);
    		administradorPermisos.addNewGrant(AccionController.IR_INICIO_LICITACIONES);
    		administradorPermisos.addNewGrant(AccionController.IR_INICIO_FONDOS_COMUNES_INVERSION);
    		administradorPermisos.addNewGrant(AccionController.IR_INICIO_FONDOS_COMUNES_INVERSION);
    		webContent.setLink(campaign);
    		webContent.setEsDeeplink(true);
        	LOGGER.info(TEXTO_LOGGER_LINK, campaign);
        } else {
        	EventosComercialesDTO eventos = sesionParametros.getOfertasComerciales();
        	List<OfertaComercialDTO> ofertas = eventos.getOfertas();
        	ofertas.addAll(campaniaPublicaBO.armarCampaniasPublicas());
            campaign = revisarCampania(campaign, webContent);
            
            OfertaComercialDTO ofertaMicrofront = this.microfrontManager.getCampania(clienteManager.obtenerClienteSesion().getNup(), campaign);
        	if(ofertaMicrofront != null){
        	    ofertas.add(ofertaMicrofront);
            }
        	
        	for (OfertaComercialDTO oferta : ofertas) {
        		if (oferta.getIdRtd().contentEquals(campaign)) {	
        			LOGGER.info("Se encontro la campania con la que se logueo {}.", campaign);	              	                  
                    if (oferta.getGotoLink().getParametros() != null) {
	        			String[] valores = oferta.getGotoLink().getParametros().split(";");
	                    webContent.setTitulo(
	                            StringUtils.isNotBlank(valores[0]) ? valores[0].trim().replace("'", "") : null);
                    }
                                       
	                    webContent.setLink(oferta.getGotoLink().getLink());
                    	webContent.setBoton(oferta.getGotoLink().obtenerTexto());
	                    webContent.setEsDeeplink(Boolean.TRUE);
                        OfertaComercialMicrofrontDto microfront = oferta.getOfertaComercialMicrofrontDto();
                        if(microfront != null){
                            webContent.setMicrofront(microfront);
                        }
	                    revisarSiCorrespondeAgregarPermiso(webContent);
	                    webContent = revisarCampaniaReferidosDeepLink(webContent);
	                    LOGGER.info(TEXTO_LOGGER_LINK, oferta.getGotoLink());
	                    break;
	            }
        	}
        }
		return webContent;
    }
    
    private void revisarSiCorrespondeAgregarPermiso(WebContentView webContent) {
    
    	if ("gotoSolicitudTarjetaRecargable()".equals(webContent.getLink()) ||
    		"gotoAdhesionWoman()".equals(webContent.getLink())) {
    		administradorPermisos.addNewGrant(AccionController.IR_INICIO_TARJETAS_SOLICITUDES);
    	}else if ("gotoReferidos()".equals(webContent.getLink())) {
    		administradorPermisos.addNewGrant(AccionController.IR_REFERIDOS);
    	}else if ("gotoFCIInformacionFondos()".equals(webContent.getLink()) ||
			"gotoInversionesFCISuscribir()".equals(webContent.getLink())) {
    		administradorPermisos.addNewGrant(AccionController.IR_INICIO_FONDOS_COMUNES_INVERSION);
    	}    	    
    }

    /**
     * Verifica si la campania:
     * es women
     * contiene datos dinamicos a firmar
     * @param campania
     * @param webContent
     * @return
     */
    private String revisarCampania (String campania, WebContentView webContent) {
		    	
    	if (campania.contains("MOBP_9638569_253")) {
    		sesionParametros.setAccesoWomenDeeplink(Boolean.TRUE);
    		return "CAMPAÑA$MOBP_9638569_253";
    	} else if(campania.contains("|")){ // trae datos a firmar
    		String[] inputs = campania.split("\\|");
    		String fields = "";
    		for (int i = 1; i < inputs.length; i++) {
    			fields += inputs[i];
    			if(i != inputs.length-1)
    				fields += ",";
    			else
    				fields += ";";
			}
    		webContent.setData(fields);
    		return inputs[0];
    	} else {    	
    		return campania;
    	}
    			    	
	}
    
    private WebContentView revisarCampaniaReferidosDeepLink (WebContentView webContent) {    	    	
    	if ("gotoReferidos()".equals(webContent.getLink())) {
        	String urlCompletaReferidos = "";
        	Respuesta<ReferidosInicioResponseView> view = referidosManager.obtenerInicioReferidos();
        	urlCompletaReferidos = view.getRespuesta().getUrlInvitacion() + view.getRespuesta().getFirma();
        	webContent.setDataUrl(urlCompletaReferidos);
    	}
    	
    	return webContent;    			    
	}
    
    public static void main(String[] args) {
		String campania = "CAMPAÃA$MOBP_9638569_253";
    	if (campania.contains("MOBP_9638569_253")) {
    		System.out.println("CAMPAÑA$MOBP_9638569_253");
    	} else {    	
    		System.out.println(campania);
    	}
    	
	}
    
    private void grabarEstadisticaError() {
        estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LOGIN,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }
    
	/**
	* Mapea el contenido de campa�as para identificar los parametros
	*   sourceIntentionId String (SI)
	*   intentionId String (I)
	*   intentionTreeId String (IT)
	*   articleId String (ART)
	*   tab String (T) // seccion especifica a la que acceder
	*   action String (ACT) // identifica si se ejecuta accion al ingresar y cual.
	* request CampId ejemplos:
	*   goToCentroDeAyuda()|n|n|n|n|n|n|
	* @param campania
	* @param webContent     
	 * @throws Exception 
	**/
	private void goToCentroDeAyudaMap (String campania, WebContentView webContent) throws BadRequestException {

		String[] inputs = campania.split("\\|");
		StringBuilder fields = new StringBuilder("?");

		validateGoToCentroDeAyudaMap(inputs);

		for (int i = 1; i < inputs.length; i++) {

			fields.append(inputs[i]);
			if(i != inputs.length-1) {
				fields.append(",");
            }
			else {
				fields.append(";");
            }
		}
		webContent.setData(fields.toString());        
        webContent.setLink(inputs[0]);        
        webContent.setEsDeeplink(true);
	}

	/** 
	*   Validaciones de longitudes: 
	*       6 parametros
	*           +1 de campa�a
	*           +1 de cierre
	*       25 cada parametro 
	 * @throws Exception 
	**/
	private void validateGoToCentroDeAyudaMap(String[] inputs) throws BadRequestException {

		if (inputs.length < 2) {
			// No genero error
			throw new BadRequestException("No hay parametros");
		}
			
		if (inputs.length > 8) {
			throw new BadRequestException("Supera la cantidad de parametros");
		}

		for (int i = 1; i < inputs.length; i++) {
			if (inputs[i].length() > 150){
				throw new BadRequestException("El parametro supera la longitud");
			}
		}
	}

	@Override
	public Respuesta<CsidView> obtenerNuevoCsid() {

		String csId = createCsId();
        CsidView csidView = new CsidView();
        csidView.setCsid(csId);
		
        return respuestaFactory.crearRespuestaOk(csidView);
	}

    private String createCsId(){
        return UUID.randomUUID().toString();
    }

}
/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.bo.impl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxInicial;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.clientes.bo.CredencialesBO;
import ar.com.santanderrio.obp.servicios.clientes.dao.CredencialesDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesArchivoFeparmException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesClaveIndividuoVencidaException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesClavePinVencidaNotieneUsuarioException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesClavesExpiradasException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesDocumentoNoExisteException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesLoginException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesServiceException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioBloqueadoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioCambioPendienteException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioConSinonimoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioNoDefinidoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioTrivialException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.SesionConcurrenteException;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.obe.bo.ObeToken;

/**
 * The Class ClienteBOImpl.
 */
@Component
public class CredencialesBOImpl implements CredencialesBO {

	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CredencialesBOImpl.class);

    /** Formato de Fecha *. */
    private static final String FORMATO_FECHA = "yyyyMMdd";

    /** The Credenciales dao. */
    @Autowired
	private CredencialesDAO credencialesDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The iatxInicial. */
    @Autowired
    private IatxInicial iatxInicial;
    /** The sign. */
    @Autowired
    private Sign sign;
    
	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	/** public key rsa256. */
	@Value("${DB.ENCPINES_RSA_PRIV.ID}")
	private String mobilePubKey;
	
    /** The tbanco obp activo. */
    @Value("${TBANCO_OBP_JUMP:false}")
    private boolean tbancoObpActivo;

    /** The tbanco obp activo. */
    @Value("${VIGENCIA_TOKEN_OBE_TBANCO:1000}")
    private long vigenciaTokenObeTbanco;
    
    @Value("${BIOCATCH-API.LEGACY-GETSCORELOGIN:true}")
    private boolean biocatchLegacyGetScoreLogin;

	/** The ModuloPermiso BO. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;



    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.clientes.bo.ClienteBO#validarCredenciales(ar.com.
     * santanderrio.base.clientes.entities.CredencialCliente)
     */
    @Override
    public Respuesta<ResumenCliente> validarCredenciales(CredencialCliente cc) {
    	
        Respuesta<ResumenCliente> respuesta = new Respuesta<ResumenCliente>();
        ResumenCliente resumenCliente = null;
        if (StringUtils.isNotBlank(cc.getToken())) {
            try {
                resumenCliente = obtenerCredencialesDesdeToken(cc);
            } catch (RobotException e) {
                respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
                        CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
            }
        } else {
            try {
            	if (isClienteApiAuth(cc)) {
            		resumenCliente = credencialesDAO.validarCredencialesApiAuth(cc, getCustomSessionID());
            		sesionParametros.setPermisoApiAuth(true);
            	} else {
            		if (moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN_VALCRED)
            				|| moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)) {
            			resumenCliente = credencialesDAO.validarCredencialesProxyLogin(cc);
            		} else {
            			resumenCliente = credencialesDAO.validarCredenciales(cc);
            		}
            	}
            } catch (CredencialesUsuarioCambioPendienteException e) {
                LOGGER.error(e.getMessage(), e);
                respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_USUARIO_CAMBIO_PENDIENTE,
                        CodigoMensajeConstantes.LOGIN_ERROR_USUARIO_CAMBIO_PENDIENTE);
            } catch (CredencialesUsuarioNoDefinidoException e) {
                LOGGER.error(e.getMessage(), e);
                respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_USUARIO_NO_DEFINIDO,
                        CodigoMensajeConstantes.LOGIN_ERROR_USUARIO_NO_DEFINIDO);
            } catch (CredencialesClavesExpiradasException e) {
                LOGGER.error(e.getMessage(), e);
                respuesta = respuestaFactory.crearRespuestaError("false", TipoError.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS,
                        CodigoMensajeConstantes.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS);
            } catch (CredencialesClavePinVencidaNotieneUsuarioException e) {
                LOGGER.error(e.getMessage(), e);
                respuesta = respuestaFactory.crearRespuestaError("true", TipoError.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS,
                        CodigoMensajeConstantes.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS);
            } catch (CredencialesClaveIndividuoVencidaException e) {
                LOGGER.error(e.getMessage(), e);
                respuesta = respuestaFactory.crearRespuestaError("true", TipoError.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS,
                        CodigoMensajeConstantes.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS);
            } catch (CredencialesUsuarioBloqueadoException e) {
                LOGGER.error(e.getMessage(), e);
                respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_CLAVE_BLOQUEADA,
                        CodigoMensajeConstantes.LOGIN_CLAVE_BLOQUEADA);
            } catch (CredencialesDocumentoNoExisteException e) {
                LOGGER.error(e.getMessage(), e);
                respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_DATOS_INCORRECTOS,
                        CodigoMensajeConstantes.MENSAJE_ERROR_LOGIN_DATOS_INCORRECTOS);
            } catch (CredencialesUsuarioConSinonimoException e) {
                LOGGER.error(e.getMessage(), e);
                respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_DOCUMENTO_HOMONIMO, "");
            } catch (CredencialesArchivoFeparmException  e) {
                LOGGER.error(e.getMessage(), e);
                respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
                        CodigoMensajeConstantes.ARCHIVO_FEPARM_NO_DISPONIBLE);
            } catch (SesionConcurrenteException  e) {
                LOGGER.error(e.getMessage(), e);
            	respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_SESSION_CONCURRENTE,
    					CodigoMensajeConstantes.LOGIN_ERROR_SESSION_CONCURRENTE);
            }  catch (CredencialesException e) {
                LOGGER.error(e.getMessage(), e);
                respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_DATOS_INCORRECTOS,
	                    CodigoMensajeConstantes.MENSAJE_ERROR_LOGIN_DATOS_INCORRECTOS);
            } catch (RobotException e) {
                respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
                        CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
            }
        }

        if (!EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
            respuesta = respuestaFactory.crearRespuestaOk(ResumenCliente.class);
        }
        respuesta.setRespuesta(completarCliente(resumenCliente));
        sesionParametros.setJwtTokenActivo(Boolean.TRUE);
        return respuesta;
    }
    
    private String getCustomSessionID() {
		if (biocatchLegacyGetScoreLogin)
			return null;
		return sesionParametros.getCsid();
	}

	private boolean isClienteApiAuth(CredencialCliente credencialCliente) {
    	boolean permisoActivo = moduloPermisoBO.tienePermisoMostrar(AccionController.APIAUTH_VALIDATION);
    	LOGGER.info("sm-api-auth activo: {} , dni:{}", permisoActivo, credencialCliente.getDniOri());
    	return permisoActivo;
	}

	@Override
    public Respuesta<ResumenCliente> validarCredencialesForApp(CredencialCliente cc) {
    	
    	Respuesta<ResumenCliente> respuesta = new Respuesta<ResumenCliente>();
    	ResumenCliente resumenCliente = null;
    	if (StringUtils.isNotBlank(cc.getToken())) {
    		try {
    			resumenCliente = obtenerCredencialesDesdeTokenApp(cc);
    		} catch (RobotException e) {
    			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
    					CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
    		}
    	} 
    	
    	respuesta.setRespuesta(completarCliente(resumenCliente));
    	return respuesta;
    }

    private ResumenCliente obtenerCredencialesDesdeTokenApp(CredencialCliente cc) {
		return obtenerCredencialesDesdeToken(cc, true);
	}

	private ResumenCliente obtenerCredencialesDesdeToken(CredencialCliente cc) {
        return obtenerCredencialesDesdeToken(cc, false);
    }
    
    /**
     * Obtener credenciales desde token.
     * Si viene de app, tenemos que utilizar otro metodo de desencriptacion
     * @param cc
     *            the cc
     * @return the resumen cliente
     */
    private ResumenCliente obtenerCredencialesDesdeToken(CredencialCliente cc, boolean fromApp) {
        ResumenCliente resumenCliente = new ResumenCliente();
        
        String token = null; 
        
        if(fromApp) {
        	try {
				token = sign.desencryptKeyPairForApp(cc.getToken(), obtenerKey());
			} catch (DAOException e) {
				LOGGER.error("Problemas para obtener token", cc.getToken());
			}
        } else {
        	token = sign.desencriptadorPKCS7Incluido(cc.getToken(), ObeToken.JKS, Boolean.TRUE);
        }
        
        if (StringUtils.isNotEmpty(token)) {
            LOGGER.debug("Token recibido: {}", token);
            StringTokenizer tokenizer = new StringTokenizer(token, "|");
            resumenCliente.setUsuarioRacf(tokenizer.nextToken());
            resumenCliente.setPasswordRacf(tokenizer.nextToken());
            resumenCliente.setNup(tokenizer.nextToken());
            resumenCliente.setMarcaANPH(tokenizer.nextToken());
            resumenCliente.setFechaNacimiento(tokenizer.nextToken());
            resumenCliente.setTipoClave(tokenizer.nextToken());
            resumenCliente.setTipoPersona(tokenizer.nextToken());
            resumenCliente.setDni(tokenizer.nextToken());
            String sinonimo = tokenizer.nextToken();
            String fechaNac = "0".equals(sinonimo) ? resumenCliente.getFechaNacimiento() : sinonimo;
            resumenCliente.setValorSinonimo(fechaNac);
            isTokenVigente(tokenizer.nextToken());
        } else {
            throw new RobotException("Se intenta ingresar desde OBE pero hubo un error con el token.");
        }
        return resumenCliente;
    }

    /**
     * Valida si la fecha recibida en la ultima posicion del token esta dentro del
     * tiempo indicado por la properties
     * {@link CredencialesBOImpl.vigenciaTokenObeTbanco}. <br/>
     * Se puede evitar esto si el token recibido fuera TimeStampToken.
     *
     * @param fechaToken
     *            the fecha token
     */
    private void isTokenVigente(String fechaToken) {
        LOGGER.debug("Fecha generacion del token en OBE {}", fechaToken);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        boolean isVigente = false;
        try {
            long fechaGeneracionObe = dateFormat.parse(fechaToken).getTime();
            long diferenciaActual = System.currentTimeMillis() - fechaGeneracionObe;
            isVigente = diferenciaActual < vigenciaTokenObeTbanco;
            LOGGER.debug("Diferencia timestamp servidores {} es vigente {}", diferenciaActual, isVigente);
        } catch (ParseException e) {
            LOGGER.error("No se puede validar la vigencia del token de obe entrante.", e);
        }
        if (!isVigente) {
            throw new RobotException("Se intenta ingresar desde OBE con un token que no esta vigente.");
        }
    }

    /**
     * Completar cliente.
     *
     * @param resumenCliente
     *            the resumen cliente
     * @return the resumen cliente
     */
    private ResumenCliente completarCliente(ResumenCliente resumenCliente) {
        if (resumenCliente != null) {
        	// Se pasa a utilizar usuario de servicio
            resumenCliente.setTipoRacf(ResumenCliente.RACF_TIPO_INICIAL);
            resumenCliente.setUsuarioRacf(iatxInicial.getRacfInicialId());
            resumenCliente.setPasswordRacf(iatxInicial.getRacfInicialPwd());
        }
        return resumenCliente;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clientes.bo.CredencialesBO#
     * loginCUEConfirmarUsuario(ar.com.santanderrio.obp.base.clientes.entities.
     * CredencialCliente)
     */
    @Override
    public Respuesta<ResumenCliente> loginConfirmarUsuario(CredencialCliente credencialCliente) {

        Respuesta<ResumenCliente> respuesta = new Respuesta<ResumenCliente>();
        ResumenCliente resumenCliente = null;

        try {
            if (moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)) {
        		resumenCliente = credencialesDAO.actualizarClaveUsuarioProxyLogin(credencialCliente);
        	} else {
        		resumenCliente = credencialesDAO.actualizarClaveUsuario(credencialCliente);
        	}
        } catch (CredencialesServiceException e) {
            respuesta = respuestaFactory.crearRespuestaError(null,
                    TipoError.LOGIN_ERROR_DE_SERVICIOS_USUARIO_CLAVE_EXPIRADAS,
                    CodigoMensajeConstantes.LOGIN_ERROR_SERVICIO_CUE);
        } catch (CredencialesLoginException e) {
            respuesta = respuestaFactory.crearRespuestaError(null,
                    TipoError.LOGIN_ERROR_FEEDBACK_USUARIO_CLAVE_EXPIRADAS,
                    CodigoMensajeConstantes.LOGIN_ERROR_FEEDBACK_USUARIO_CLAVE_EXPIRADAS);
        } catch (CredencialesException e) {
            respuesta = respuestaFactory.crearRespuestaError(null,
                    TipoError.LOGIN_ERROR_FEEDBACK_USUARIO_CLAVE_EXPIRADAS,
                    CodigoMensajeConstantes.MENSAJE_ERROR_LOGIN_DATOS_INCORRECTOS);
        } catch (RobotException e) {
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
                    CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
        }
        respuesta.setRespuesta(completarCliente(resumenCliente));
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clientes.bo.CredencialesBO#
     * loginDefinirUsuario(ar.com.santanderrio.obp.base.clientes.entities.
     * CredencialCliente)
     */
    @Override
    public Respuesta<ResumenCliente> loginDefinirUsuario(CredencialCliente credencialCliente) {

        Respuesta<ResumenCliente> respuesta = new Respuesta<ResumenCliente>();
        ResumenCliente resumenCliente = null;

        try {
        	if (moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)) {
        		resumenCliente = credencialesDAO.actualizarClaveUsuarioProxyLogin(credencialCliente);
        	} else {
        		resumenCliente = credencialesDAO.actualizarClaveUsuario(credencialCliente);
        	}
        } catch (CredencialesServiceException e) {
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_FEEDBACK_USUARIO_NO_DEFINIDO,
                    CodigoMensajeConstantes.LOGIN_ERROR_DEFINIR_USUARIO_FEEDBACK);
        } catch (CredencialesException e) {
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_FEEDBACK_USUARIO_NO_DEFINIDO,
                    CodigoMensajeConstantes.MENSAJE_ERROR_LOGIN_DATOS_INCORRECTOS);
        } catch (RobotException e) {
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
                    CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
        }

        respuesta.setRespuesta(completarCliente(resumenCliente));
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clientes.bo.CredencialesBO#
     * cambioUsuarioPendiente(ar.com.santanderrio.obp.base.clientes.entities.
     * CredencialCliente)
     */
    @Override
    public Respuesta<ResumenCliente> cambioUsuarioPendiente(CredencialCliente credencialCliente) {

        Respuesta<ResumenCliente> respuesta = new Respuesta<ResumenCliente>();
        ResumenCliente resumenCliente = null;

        try {
        	if (moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)) {
        		resumenCliente = credencialesDAO.actualizarClaveUsuarioProxyLogin(credencialCliente);
        	} else {
        		resumenCliente = credencialesDAO.actualizarClaveUsuario(credencialCliente);
        	}
        } catch (CredencialesUsuarioTrivialException e) {
            respuesta = respuestaFactory.crearRespuestaError(null,
                    TipoError.LOGIN_ERROR_FEEDBACK_USUARIO_CAMBIO_PENDIENTE_TRIVIAL,
                    CodigoMensajeConstantes.LOGIN_ERROR_FEEDBACK_USUARIO_CAMBIO_PENDIENTE_TRIVIAL);
        } catch (CredencialesServiceException e) {
            respuesta = respuestaFactory.crearRespuestaError(null,
                    TipoError.LOGIN_ERROR_FEEDBACK_USUARIO_CAMBIO_PENDIENTE,
                    CodigoMensajeConstantes.LOGIN_ERROR_FEEDBACK_USUARIO_CAMBIO_PENDIENTE);
        } catch (CredencialesLoginException e) {
            respuesta = respuestaFactory.crearRespuestaError(null,
                    TipoError.LOGIN_ERROR_FEEDBACK_USUARIO_CLAVE_EXPIRADAS,
                    CodigoMensajeConstantes.LOGIN_ERROR_FEEDBACK_USUARIO_CLAVE_EXPIRADAS);
        } catch (CredencialesException e) {
            respuesta = respuestaFactory.crearRespuestaError(null,
                    TipoError.LOGIN_ERROR_FEEDBACK_USUARIO_CAMBIO_PENDIENTE,
                    CodigoMensajeConstantes.MENSAJE_ERROR_LOGIN_DATOS_INCORRECTOS);
        } catch (RobotException e) {
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
                    CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
        }

        respuesta.setRespuesta(completarCliente(resumenCliente));
        return respuesta;
    }


    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clientes.bo.CredencialesBO#
     * cambioUsuario(javax.servlet.http.HttpServletRequest,
     * ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity,
     * boolean)
     */
    @Override
    public Respuesta<ResumenCliente> cambioUsuario(HttpServletRequest request, CambioUsuarioEntity entity,
            boolean esSoloUsuario) {
        Respuesta<ResumenCliente> respuesta = new Respuesta<ResumenCliente>();
        ResumenCliente resumenCliente = null;
        try {

            CredencialCliente cc = obtenerCredencialCliente(request, sesionParametros.getRegistroSession());
            if (moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)) {
        		resumenCliente = credencialesDAO.cambioUsuarioProxyLogin(cc, entity, esSoloUsuario);
        	} else {
        		resumenCliente = credencialesDAO.cambioUsuario(cc, entity);
        	}
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            respuesta.setRespuesta(resumenCliente);
            actualizarRegistroSession(resumenCliente);
        } catch (CredencialesException e) {
            if (TipoError.USUARIO_ACTUAL_INCORRECTO.getDescripcion().equals(e.getMessage())) {
                respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY,
                        TipoError.USUARIO_ACTUAL_INCORRECTO, CodigoMensajeConstantes.USUARIO_ACTUAL_INCORRECTO);
            } else {
                respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERRORCAMBIOUSUARIO,
                        CodigoMensajeConstantes.CODIGO_ERROR_PERFIL_CAMBIO_USUARIO);
            }
        } catch (RobotException e) {
            if (esSoloUsuario) {
                respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERRORCAMBIOUSUARIO,
                        CodigoMensajeConstantes.CODIGO_ERROR_PERFIL_CAMBIO_USUARIO);
            } else {
                respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_ERROR_PERFIL_CAMBIO_USUARIO);
            }
        }

        return respuesta;
    }

    /**
     * Actualizar usu y pass en session para enviar en la cabecera de las llamadas a
     * iatx.
     * 
     * @param resumenCliente
     */
    private void actualizarRegistroSession(ResumenCliente resumenCliente) {
        Cliente cliente = sesionCliente.getCliente();
        cliente.setUsuarioRacf(resumenCliente.getUsuarioRacf());
        cliente.setPasswordRacf(resumenCliente.getPasswordRacf());
        ResumenCliente resumenClienteSession = sesionCliente.getResumenCliente();
        resumenClienteSession.setUsuarioRacf(resumenCliente.getUsuarioRacf());
        resumenClienteSession.setPasswordRacf(resumenCliente.getPasswordRacf());
    }

    /**
     * Obtener credencial cliente.
     *
     * @param request
     *            the request
     * @param registroSesion
     *            the registro sesion
     * @return the credencial cliente
     */
    private CredencialCliente obtenerCredencialCliente(HttpServletRequest request, RegistroSesion registroSesion) {

        CredencialCliente credencialCliente = new CredencialCliente();

        credencialCliente.setIsAlta(registroSesion.getIsAlta());
        credencialCliente.setDniOri(registroSesion.getDni());
        credencialCliente.setDni(registroSesion.getDniEncriptado());
        credencialCliente.setFechaNacimiento(
                FechaUtils.parsearFechaNacimiento(registroSesion.getFechaNacimiento(), FORMATO_FECHA));
        credencialCliente.setPrefijo(registroSesion.getPrefijo());
        credencialCliente.setSufijo(registroSesion.getSufijo());
        credencialCliente.setIp(NetworkUtil.getRemoteIp(request));

        return credencialCliente;
    }
    
	/**
	 * Obtener la clave con la cual se encriptara RSA256.
	 * 
	 * @return
	 * @throws DAOException
	 */
	private String obtenerKey() throws DAOException {
		try {
			Credential credential = credentialSecurityFactory.getCredentialById(mobilePubKey);
			return credential.getPassword();
		} catch (SQLException e) {
			String errorMsgSQL = "No es posible obtener la entrada de la base de seguridad por error en la base.";
			LOGGER.error(errorMsgSQL, e);
			throw new DAOException(errorMsgSQL);
		} catch (NullPointerException e) {
			String errorMsgNull = "No es posible obtener la entrada de la base de seguridad por no estar el dato.";
			LOGGER.error(errorMsgNull, e);
			throw new DAOException(errorMsgNull);
		}
	}
    
}

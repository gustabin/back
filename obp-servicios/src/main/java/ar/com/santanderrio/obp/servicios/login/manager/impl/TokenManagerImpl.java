/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.jwt.JwtToken;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurity;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.impl.ClienteManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.AdmCookies;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.exception.SessionManagerException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.manager.TokenManager;
import ar.com.santanderrio.obp.servicios.login.view.LoginResponseView;
import ar.com.santanderrio.obp.servicios.session.dto.SessionControlDTO;
import ar.com.santanderrio.obp.servicios.session.manager.SessionControlManager;

/**
 * The Class TokenManagerImpl.
 */
@Component
public class TokenManagerImpl implements TokenManager {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteManagerImpl.class);

	/** The Constant COOKIE_NAME. */
	public static final String COOKIE_NAME = "TOKENSC";

	/** The session control manager. */
	@Autowired
	private SessionControlManager sessionControlManager;

	/** The cliente manager. */
	@Autowired
	private ClienteManager clienteManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The cs. */
	@Autowired
	private CredentialSecurity credentialSecurity;
	
	@Autowired
	private SesionCliente sesionCliente;

	/** The expiration time. */
	@Value("${JWT_EXPIRATION_TIME:240000}")
	private Integer expirationTime;

	/** The token enabled. */
	@Value("${JWT_DEV_MODE:false}")
	private Boolean jwtDevMode;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The tkn cookie. */
	@Autowired
	AdmCookies tknCookie;

	/** The tkn cookie activo. */
	@Value("${TOKENSC_COOKIE.ACTIVO:false}")
	private boolean tknCookieActivo;

	/**
	 * Habilita el login dentro de la aplicacion: - si el token de la base de
	 * datos es igual a null. - o el token existe y now es mayor a
	 * SessionControlDTO.update + (expirationTime de la aplicacion multiplicado
	 * por 2)
	 * 
	 * Solo se evalua la validez del token (timeout) en el filtro de
	 * autenticacion.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 * @param request 
	 *            the request
	 * @param response
	 *            the response
	 * @return the respuesta
	 * @throws SessionManagerException
	 *             the session manager exception
	 * @see ar.com.santanderrio.obp.config.filter.AuthenticationFilter
	 */
	private Respuesta<LoginResponseView> validate(ResumenCliente resumenCliente, HttpServletRequest request, HttpServletResponse response) throws SessionManagerException {

		final SessionControlDTO session = sessionControlManager.get(new Long(resumenCliente.getNup()));
		if (session != null && sesionParametros.getPrimerAcceso()) {
			Date ultimoAcceso = session.getUpdate();
			sesionParametros.setUltimoAcceso(ultimoAcceso);
			sesionParametros.setPrimerAcceso(false);
		}

		if (session != null && StringUtils.isNotBlank(session.getToken())) {

			Date update = session.getUpdate();
			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();
			calendar.setTime(update);
			Long time = TimeUnit.MILLISECONDS.toSeconds(expirationTime);
			calendar.add(Calendar.SECOND, time.intValue() * 2);
			Date delta = calendar.getTime();

			if (validateDelta(now, delta)|| (tknCookieActivo && isValidTokenCookie(session.getToken(), request, response))) {
				LOGGER.debug("Generacion del Token");
				Respuesta<LoginResponseView> loginResponse = new Respuesta<LoginResponseView>();
				LoginResponseView view = new LoginResponseView();
				view.setToken(buildNewToken(resumenCliente));
				loginResponse.setRespuesta(view);
				loginResponse.setEstadoRespuesta(EstadoRespuesta.OK);
				grabarOmodificarToken(resumenCliente, view.getToken());
				sesionParametros.setJwt(view.getToken());
				grabarOmodificarCookieSC(view.getToken(), request, response);
				return loginResponse;
			} else {
				return respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_SESSION_CONCURRENTE,
						CodigoMensajeConstantes.LOGIN_ERROR_SESSION_CONCURRENTE);
			}
		} else {
			// El token del DTO es nulo.
			LOGGER.info("Token vacio , se genera el token nuevo");
			String token = grabarOmodificarToken(resumenCliente, null);
			Respuesta<LoginResponseView> loginResponse = new Respuesta<LoginResponseView>();
			loginResponse.setEstadoRespuesta(EstadoRespuesta.OK);
			LoginResponseView view = new LoginResponseView();
			view.setToken(token);
			view.setNup(resumenCliente.getNup());
			loginResponse.setRespuesta(view);
			grabarOmodificarCookieSC(view.getToken(), request, response);
			sesionParametros.setJwt(view.getToken());
			return loginResponse;
		}
	}

	private boolean validateDelta(Date now, Date delta) {
		
		return sesionCliente.getAuthCliente().isApiAuthClient() || now.compareTo(delta) > 0;
	}

	/**
	 * Grabar omodificar token.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 * @param token
	 *            the token
	 * @return the string
	 */
	@Override
	public String grabarOmodificarToken(ResumenCliente resumenCliente, String token) {
		if (token == null) {
			String myToken = this.buildNewToken(resumenCliente);
			sessionControlManager.saveOrUpdate(Long.valueOf(resumenCliente.getNup()), myToken);
			return myToken;
		} else {
			sessionControlManager.saveOrUpdate(Long.valueOf(resumenCliente.getNup()), token);
			return token;
		}
	}

	/**
	 * Grabar o modificar cookie SC.
	 *
	 * @param token the token
	 * @param request the request
	 * @param response the response
	 */
	@Override
	public void grabarOmodificarCookieSC(String token, HttpServletRequest request, HttpServletResponse response) {
		int exp = ((Long) TimeUnit.MILLISECONDS.toSeconds(expirationTime)).intValue() * 2;
		if (tknCookie.existeCookie(COOKIE_NAME, request, response)) {
			tknCookie.updateCookie(COOKIE_NAME, token, exp, request, response);
		} else {
			tknCookie.addSecureHttpOnlyCookie(COOKIE_NAME, token, exp, request, response);
		}
	}

	/**
	 * Checks if is valid token cookie.
	 *
	 * @param token the token
	 * @param request the request
	 * @param response the response
	 * @return true, if is valid token cookie
	 */
	private boolean isValidTokenCookie(String token, HttpServletRequest request, HttpServletResponse response) {
		String tkn = "";
		if (tknCookie.existeCookie(COOKIE_NAME, request, response)) {
			tkn = tknCookie.getCookie(COOKIE_NAME, request, response);
		}
		if (tkn != null && !"".equals(tkn) && tkn.equals(token)) {
			LOGGER.info("Cookie TOKENSC validada");
			return true;
		}
		return false;
	}

	/**
	 * Builds the new token.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 * @return the jwt token
	 */

	public String buildNewToken(ResumenCliente resumenCliente) {
		if(sesionCliente.getAuthCliente().isApiAuthClient()) {
			return sesionCliente.getAuthCliente().getAuthJWT();
		}
		sesionParametros.setJwtTokenActivo(Boolean.TRUE);
        Long time = TimeUnit.MILLISECONDS.toSeconds(expirationTime);
        JwtToken tkn = new JwtToken(time.intValue(),
				credentialSecurity.getCredential(DataBase.JWTSECRET.getNombreTarget()).getPassword(),
				resumenCliente.getNup(), resumenCliente.getDni());
        return tkn.getToken();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.login.manager.TokenManager#
	 * verificarRespuestaToken(ar.com.santanderrio.obp.base.clientes.entities.
	 * ResumenCliente, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Respuesta<LoginResponseView> verificarRespuestaToken(ResumenCliente resumenCliente, HttpServletRequest request, HttpServletResponse response) {
		Respuesta<LoginResponseView> loginResponse = new Respuesta<LoginResponseView>();
		try {
			if (jwtDevMode) {
				LoginResponseView view = new LoginResponseView();
				view.setToken(buildNewToken(resumenCliente));
				view.setNup(resumenCliente.getNup());
				loginResponse.setRespuesta(view);
				loginResponse.setEstadoRespuesta(EstadoRespuesta.OK);
			} else {
				loginResponse = this.validate(resumenCliente, request, response);
			}

		} catch (SessionManagerException ex) {
			LOGGER.error(ex.getMessage(), ex);
			loginResponse = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_CONTROL_SESSION,
					CodigoMensajeConstantes.LOGIN_ERROR_CONTROL_SESSION);
		}
		return loginResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.login.manager.LoginManager#refresh(
	 * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public Respuesta<LoginResponseView> refresh(HttpServletRequest request, HttpServletResponse response) {
		Respuesta<LoginResponseView> loginResponse = new Respuesta<LoginResponseView>();
		LoginResponseView view = new LoginResponseView();
		ResumenCliente cliente = clienteManager.obtenerClienteSesion();
		view.setToken(this.buildNewToken(cliente));
		loginResponse.setRespuesta(view);
		loginResponse.setEstadoRespuesta(EstadoRespuesta.OK);
		if (!jwtDevMode) {
			this.grabarOmodificarToken(clienteManager.obtenerClienteSesion(), view.getToken());
			sesionParametros.setJwt(view.getToken());
			this.grabarOmodificarCookieSC(view.getToken(), request, response);
		}
		return loginResponse;
	}

}

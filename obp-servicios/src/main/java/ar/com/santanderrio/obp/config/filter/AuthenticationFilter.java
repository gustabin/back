/*
 * 
 */
package ar.com.santanderrio.obp.config.filter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWTExpiredException;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.jwt.JwtToken;
import ar.com.santanderrio.obp.base.jwt.JwtVerifyException;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurity;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.log.Log4j2Constant;

/**
 * Filtro de validacion del token jwt y validacion de sesion Created by
 * pablo.martin.gore on 8/19/2016.
 */

@Component("authenticationFilter")
public class AuthenticationFilter extends OncePerRequestFilter {

    /** The Constant LOGGER_SLF4J. */
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(AuthenticationFilter.class);

    /** Header response. */
    private static final String HEADER_ANAUTH = "Unauthorized";

    /** Url de logout. */
    private static final String LOGOUT_URI = "/login/doLogout";
    /** Url de release. */
    private static final String RELEASE_URI = "/login/doRelease";
    /** Url de encpines obtener key publica. */
    private static final String ENCPINES_URI = "/inicial/doInit";

    /** Url de preLogin. */
    private static final String PRELOGIN_URI = "/inicial/preLogin";

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        Boolean aplicoFiltro = accesoApi(servletRequest, filterChain, servletResponse);
        aplicoFiltro = accesoEncPines(servletRequest, filterChain, servletResponse, aplicoFiltro);
        iniciarLogger(servletRequest, aplicoFiltro);
        aplicoFiltro = accesoLogin(servletRequest, filterChain, servletResponse, aplicoFiltro);
        aplicoFiltro = accesoPreLogin(servletRequest, filterChain, servletResponse, aplicoFiltro);
        aplicoFiltro = accesoSessionNoActiva(servletRequest, servletResponse, aplicoFiltro);
        aplicoFiltro = accesoRelease(servletRequest, servletResponse, aplicoFiltro);
        aplicoFiltro = accesoRefrescoToken(servletRequest, filterChain, servletResponse, aplicoFiltro);
        aplicoFiltro = accesoClaveOnline(servletRequest, filterChain, servletResponse, aplicoFiltro);
        accesoGeneralPostLogin(servletRequest, servletResponse, filterChain, aplicoFiltro);
    }

    /**
	 * Obtener la key publica para que front pueda aplicar el js de encripcion
	 * previo a crypto.js
	 *
	 * @param servletRequest
	 *            the servlet request
	 * @param filterChain
	 *            the filter chain
	 * @param servletResponse
	 *            the servlet response
	 * @param aplicoFiltro
	 *            the aplico filtro
	 * @return the boolean
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ServletException
	 *             the servlet exception
	 */
    private Boolean accesoEncPines(HttpServletRequest servletRequest, FilterChain filterChain,
            HttpServletResponse servletResponse, Boolean aplicoFiltro) throws IOException, ServletException {
        if (isUriOK(servletRequest, ENCPINES_URI) && !aplicoFiltro) {
            ThreadContext.put(Log4j2Constant.LOG_FILTRADO, Boolean.TRUE.toString());
            filterChain.doFilter(servletRequest, servletResponse);
            return Boolean.TRUE;
        }
        return aplicoFiltro;
    }

    /**
	 * Acceso release.
	 *
	 * @param servletRequest
	 *            the servlet request
	 * @param servletResponse
	 *            the servlet response
	 * @param aplicoFiltro
	 *            the aplico filtro
	 * @return the boolean
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    private Boolean accesoRelease(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
            Boolean aplicoFiltro) throws IOException {
        if (isUriOK(servletRequest, RELEASE_URI) && !aplicoFiltro) {
            LOGGER_SLF4J.trace("La session no esta Activa intentan hacer una limpieza redundante, URL {}.",
                    servletRequest.getRequestURI());
            sendErrorRelease(servletResponse);
            if (servletRequest.getSession(false) != null) {
                servletRequest.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
            }
            return Boolean.TRUE;
        }
        return aplicoFiltro;
    }

    /**
	 * Agregar marca a log4j2 para que escriba en el file del usuario
	 * correspondiente a la session activa.
	 *
	 * @param servletRequest
	 *            the servlet request
	 * @param aplicoFiltro
	 *            the aplico filtro
	 */
    private void iniciarLogger(HttpServletRequest servletRequest, Boolean aplicoFiltro) {
        HttpSession session = servletRequest.getSession(Boolean.FALSE);
        if (session != null && !aplicoFiltro) {
            String userid = (String) session.getAttribute(Log4j2Constant.LOGFILENAME);
            if (StringUtils.isNotBlank(userid)) {
                ThreadContext.put(Log4j2Constant.USERID, userid);
            }
        }
    }

    /**
     * Por este metodo entran las url provenientes de la api que da informacion del
     * estado de la aplicacion.<br/>
     * <b>NO</b> se debe acceder desde la nube.
     *
     * @param request
     *            the request
     * @param filterChain
     *            the filter chain
     * @param response
     *            the response
     * @return the boolean
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServletException
     *             the servlet exception
     */
    private Boolean accesoApi(HttpServletRequest request, FilterChain filterChain, HttpServletResponse response)
            throws IOException, ServletException {
        if (isApiUrl(request)) {
            ThreadContext.put(Log4j2Constant.USERID, "MonitoreoRollingFile");
            filterChain.doFilter(request, response);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Por este metodo entra cuando el usuario se quiere loguear, hay que considerar
     * el caso en que el usuario este en el mismo browser.
     *
     * @param request
     *            the request
     * @param filterChain
     *            the filter chain
     * @param response
     *            the response
     * @param aplicoFiltro
     *            the aplico filtro
     * @return si aplico el filtro
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServletException
     *             the servlet exception
     */
    private Boolean accesoLogin(HttpServletRequest request, FilterChain filterChain, HttpServletResponse response,
            Boolean aplicoFiltro) throws IOException, ServletException {
        if (isFirstActionLogin(request) && !aplicoFiltro) {
            validateMismoBrowserLogin(request, response, filterChain);
            return Boolean.TRUE;
        }
        return aplicoFiltro;
    }

    /**
     * Por este metodo entra cuando el usuario se quiere registrar o actualizar sus
     * datos.
     *
     * @param request
     *            the request
     * @param filterChain
     *            the filter chain
     * @param response
     *            the response
     * @param aplicoFiltro
     *            the aplico filtro
     * @return si aplico el filtro
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServletException
     *             the servlet exception
     */
    private Boolean accesoClaveOnline(HttpServletRequest request, FilterChain filterChain, HttpServletResponse response,
            Boolean aplicoFiltro) throws IOException, ServletException {
        if (isFlujoAltLogin(request) && !aplicoFiltro) {
            LOGGER_SLF4J.info("Esta intentando operar su claveOnline, no se le aplica filtro token, Url {}.",
                    request.getRequestURI());
            filterChain.doFilter(request, response);
            return Boolean.TRUE;
        }
        return aplicoFiltro;
    }

    /**
     * En casos en los que la session expiro y se invoca al servidor.
     *
     * @param request
     *            the request
     * @param response
     *            the response
     * @param aplicoFiltro
     *            the aplico filtro
     * @return si aplico el filtro
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServletException
     *             the servlet exception
     */
    private Boolean accesoSessionNoActiva(HttpServletRequest request, HttpServletResponse response,
            Boolean aplicoFiltro) throws IOException, ServletException {
        if (isSessionNew(request) && !aplicoFiltro) {
            if (isUriOK(request, RELEASE_URI) || isUriOK(request, LOGOUT_URI)) {
                LOGGER_SLF4J.trace("La session no esta Activa intentan hacer una limpieza redundante, URL {}.",
                        request.getRequestURI());
                sendErrorRelease(response);
                return Boolean.TRUE;
            }
            LOGGER_SLF4J.trace("La session no esta Activa requiere login, URL {}.", request.getRequestURI());
            sendErrorJWT(response);
            return Boolean.TRUE;
        }
        return aplicoFiltro;
    }

    /**
	 * Notificarle al frontend que la operacion que se recibio no realiza accion
	 * y no requiere interacion del usuario para cerrar ventanas.
	 *
	 * @param response
	 *            the response
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    private void sendErrorRelease(HttpServletResponse response) throws IOException {
        sendError(response, HEADER_ANAUTH, "NONE");
    }

    /**
	 * Validar si la uri recibida corresponde con la que se quiere encontrar.
	 *
	 * @param request
	 *            the request
	 * @param uriToFind
	 *            the uri to find
	 * @return true, if is uri OK
	 */
    private boolean isUriOK(HttpServletRequest request, String uriToFind) {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        return uri.equals(contextPath + uriToFind);
    }

    /**
     * Acceso pre login.
     *
     * @param request the request
     * @param filterChain the filter chain
     * @param response the response
     * @param aplicoFiltro the aplico filtro
     * @return the boolean
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    private Boolean accesoPreLogin(HttpServletRequest request, FilterChain filterChain,
            HttpServletResponse response, Boolean aplicoFiltro) throws IOException, ServletException {
        if (isUriOK(request, PRELOGIN_URI) && !aplicoFiltro) {
            LOGGER_SLF4J.info("PreLogin, no se le aplica filtro token, url {}.",
                    request.getRequestURI());
            filterChain.doFilter(request, response);
            return Boolean.TRUE;
        }
        return aplicoFiltro;
    }

	/**
     * Se accede cuando la accion es refrescar el token jwt.
     *
     * @param request
     *            the request
     * @param filterChain
     *            the filter chain
     * @param response
     *            the response
     * @param aplicoFiltro
     *            the aplico filtro
     * @return si aplico el filtro
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServletException
     *             the servlet exception
     */
    private Boolean accesoRefrescoToken(HttpServletRequest request, FilterChain filterChain,
            HttpServletResponse response, Boolean aplicoFiltro) throws IOException, ServletException {
        if (isRefreshTokenUrl(request) && !aplicoFiltro) {
            LOGGER_SLF4J.info("Esta intentando ingresar, no se le aplica filtro token, url {}.",
                    request.getRequestURI());
            filterChain.doFilter(request, response);
            return Boolean.TRUE;
        }
        return aplicoFiltro;
    }

    /**
     * Las url de los servicios posteriores al login deben caer por aca.
     *
     * @param request
     *            the request
     * @param response
     *            the response
     * @param filterChain
     *            the filter chain
     * @param aplicoFiltro
     *            the aplico filtro
     * @return si aplico el filtro
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private void accesoGeneralPostLogin(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain, Boolean aplicoFiltro) throws IOException {
        if (!aplicoFiltro) {
            JwtToken token = getToken(request);

            if (token != null && token.getToken() != null && !token.getToken().isEmpty()) {
                try {
                    CredentialSecurity credentialSecurity = ContextHolder.getContext()
                            .getBean(CredentialSecurity.class);
                    SesionParametros sesionParametros = ContextHolder.getContext().getBean(SesionParametros.class);
                    RegistroSesion registroSesion = sesionParametros.getRegistroSession();
                    String nup = registroSesion.getNup();
                    try {
                		SesionCliente sesionCliente= ContextHolder.getContext().getBean(SesionCliente.class);
                		if (sesionCliente.getAuthCliente().isApiAuthClient()) {
                        	if (isRefreshTokenUrl(request)) {
                        		if(token.getToken().equals(sesionCliente.getAuthCliente().getAuthJWT())){
                        			token.verifyToken(sesionCliente.getAuthCliente().getRefreshToken());
                        		}
                        	} else {
                        		token.verifyToken(nup);
                        	}
                		} else {
                			token.verifyToken(credentialSecurity
                						.getCredential(DataBase.JWTSECRET.getNombreTarget()).getPassword(),nup);
                		}
                    } catch (JwtVerifyException e) {
                        LOGGER_SLF4J.info("Fallo la validacion del token", e);
                        if (Boolean.TRUE.toString().equals(ContextHolder.getContext().getEnvironment()
                                .getProperty("JWT_EVALUAR_DELTA_TIMEOUT", "false")) &&
                                e.getCause().getClass().equals(JWTExpiredException.class)) {
                                long actual = System.currentTimeMillis() / 1000L;
                                Long deltaMs = new Long(ContextHolder.getContext().getEnvironment()
                                        .getProperty("JWT_DELTA_TIMEOUT", "10000"));
                                Long expirationTime = ((JWTExpiredException) e.getCause()).getExpiration()
                                        + TimeUnit.MILLISECONDS.toSeconds(deltaMs);
                                LOGGER_SLF4J.info("Comparar actual {} contra expiracion mas 10seg {}", actual,
                                        expirationTime);
                                if (actual < expirationTime) {
                                    LOGGER_SLF4J.info(
                                            "Se mantiene valido debido a que esta dentro de un intervalo de tiempo aceptable.");
                                } else {
                                    throw e;
                                }
                        } else {
                            throw e;
                        }
                    }
                    LOGGER_SLF4J.debug("URL {} is authenticated", request.getRequestURI());
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    LOGGER_SLF4J.error(String.format("URL %s is not authenticated", request.getRequestURI()), e);
                    sendErrorJWT(response);
                }
            } else {
                LOGGER_SLF4J.error("URL {} is not authenticated", request.getRequestURI());
                sendErrorJWT(response);
            }
        }
    }

    /**
     * Si desde el mismo browser ya con una solapa abierta entra desde otra recibe
     * la cookie de la primera por lo que en ese caso no se le permite continuar y
     * se invalida la primera. <br/>
     * <b>Solo deberia darse en el mismo browser.</b>
     *
     * @param request
     *            the request
     * @param response
     *            the response
     * @param filterChain
     *            the filter chain
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServletException
     *             the servlet exception
     */
    private void validateMismoBrowserLogin(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        if (isSessionNew(request)) {
            String userid = (String) request.getSession(Boolean.TRUE).getAttribute(Log4j2Constant.LOGFILENAME);
            ThreadContext.put(Log4j2Constant.USERID, userid);
            LOGGER_SLF4J.info("Esta intentando ingresar, no se le aplica filtro token, Url {}.",
                    request.getRequestURI());
            filterChain.doFilter(request, response);
        } else {
            LOGGER_SLF4J.info(
                    "Esta intentando ingresar con una session activa en el mismo browser se toma como accion invalidar la session activa y no permitir continuar esta operacion, Url {}.",
                    request.getRequestURI());
            request.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
            sendErrorLogin(response);
        }
    }

    /**
     * Validar que llegue una session creada.
     *
     * @param request
     *            the request
     * @return true, if is session new
     */
    private boolean isSessionNew(HttpServletRequest request) {
        return request.getSession(false) == null || request.getSession(false).isNew();
    }

    /**
     * url que viene para consultar la api de estado.
     *
     * @param request
     *            the request
     * @return true, if is api url
     */
    private boolean isApiUrl(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String api = ContextHolder.getContext().getEnvironment().getProperty("CXF.API.ADDRESS", "/api");
        return uri.contains(contextPath + api) || uri.equals(contextPath + "/monitoreo/monitor")
                || uri.equals(contextPath + "/login/monitoreo") || uri.equals(contextPath + "/login/obtenerNuevoCsid");
    }

    /**
     * Token is in request header: Authorization : Bearer <token>. Accepts header
     * with Bearer prefix and without it.
     *
     * @param request
     *            the request
     * @return null if token was not found.
     */
    private JwtToken getToken(HttpServletRequest request) {
        return JwtToken.parseToken(request.getHeader(JwtToken.AUTHORIZATION_HEADER));
    }

    /**
     * Checks if is Inicio Login url.
     *
     * @param request
     *            the request
     * @return true, if is Inicio login url
     */
    private boolean isFirstActionLogin(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        return uri.equals(contextPath + "/login/doLogin") 
                || uri.equals(contextPath + "/login/doLoginOBE") 
                || uri.equals(contextPath + "/login/doLoginApp") 
                || uri.equals(contextPath + "/claveOnline/identificarClienteRegistrar")
                || uri.equals(contextPath + "/claveOnline/identificarClienteRenovar")
                || uri.equals(contextPath + "/claveOnline/irSolucionar");
    }

    /**
     * Controlar si viene por alguno de los flujos de login alternativos o
     * claveOnline.
     *
     * @param request
     *            the request
     * @return true, if is Inicio login url
     */
    private boolean isFlujoAltLogin(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();

        return uri.startsWith(contextPath + "/login/") || uri.startsWith(contextPath + "/claveOnline/");
    }

    /**
     * Checks if is refresh token url.
     *
     * @param request
     *            the request
     * @return true, if is refresh token url
     */
    private boolean isRefreshTokenUrl(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        return uri.equals(contextPath + "/login/refresh");
    }

    /**
     * Send error.
     *
     * @param response
     *            the response
     * @param message
     *            the message
     * @param auth
     *            corresponde al header www_authenticate
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private void sendError(HttpServletResponse response, String message, String auth) throws IOException {
        response.setHeader(HttpHeaders.WWW_AUTHENTICATE, auth);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
    }

    /**
     * Send error con codigo de autenticacion JWT.
     *
     * @param response
     *            the response
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private void sendErrorJWT(HttpServletResponse response) throws IOException {
        sendError(response, HEADER_ANAUTH, "JWT");
    }

    /**
     * Send error con codigo de autenticacion LOGIN cuando quiere entrar desde el
     * mismo browser con solapas diferentes.
     *
     * @param response
     *            the response
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private void sendErrorLogin(HttpServletResponse response) throws IOException {
        sendError(response, HEADER_ANAUTH, "LOGIN");
    }

}

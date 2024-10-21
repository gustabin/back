/**
 * 
 */
package ar.com.santanderrio.obp.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ar.com.santanderrio.obp.servicios.log.Log4j2Constant;

/**
 * Propaga la invocacion y a la vuelta validar si la session existe y si posee
 * la marca para realiar un invalidate de la misma.<br\> De esta forma seria la
 * ultima accion que se efectua y no provoca que se cree una session nueva
 * debido a que log4j tenga un level mas verboso (provocando que se le deje una
 * session abierta al browser y se escriba mal en el log de session).
 * 
 * @author sergio.e.goldentair
 *
 */
@Component("releaseSessionFilter")
public class ReleaseSessionFilter extends OncePerRequestFilter {
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.
     * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        filterChain.doFilter(request, response);
        HttpSession httpSession = request.getSession(Boolean.FALSE);
        if (httpSession != null && requiereInvalidar(httpSession)) {
            httpSession.invalidate();
        }
        limpiarLogger();
    }

    /**
	 * Valida si en el flujo en lugar de hacer el invalidates se marca la
	 * session para ser cerrada aca.
	 *
	 * @param httpSession
	 *            the http session
	 * @return true, if successful
	 */
    private boolean requiereInvalidar(HttpSession httpSession) {
        Object requiereInvalidarSession = httpSession.getAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION);
        return requiereInvalidarSession != null ? true : false;
    }

    /**
     * Remover la marca sobre la cual se escribe el log de session.
     */
    private void limpiarLogger() {
        if (StringUtils.isNotBlank(ThreadContext.get(Log4j2Constant.USERID))
                || StringUtils.isNotBlank(ThreadContext.get(Log4j2Constant.LOG_FILTRADO))) {
            ThreadContext.clearAll();
        }
    }
}

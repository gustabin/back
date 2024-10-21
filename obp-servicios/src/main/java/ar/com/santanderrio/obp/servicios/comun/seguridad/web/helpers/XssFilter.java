/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.seguridad.web.helpers;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filtro que aplica un wrapper al request. <br>
 * Esto se hace para filtrar cualquier parametro enviado en las urls <br>
 * y evitar que realicen invocacion de codigo explicita <br>
 */
@Component("xssFilter")
public class XssFilter extends OncePerRequestFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		filterChain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);

	}

}

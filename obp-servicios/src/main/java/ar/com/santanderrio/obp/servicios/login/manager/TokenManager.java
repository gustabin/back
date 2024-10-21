/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.login.view.LoginResponseView;

/**
 * The Interface TokenManager.
 */
public interface TokenManager {

	/**
	 * Verificar respuesta token.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 * @param request 
	 *            the request
	 * @param response
	 *            the response
	 * @return the respuesta
	 */
	public Respuesta<LoginResponseView> verificarRespuestaToken(ResumenCliente resumenCliente, HttpServletRequest request, HttpServletResponse response);

	/**
	 * Refresh.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the respuesta
	 */
	public Respuesta<LoginResponseView> refresh(HttpServletRequest request, HttpServletResponse response);
	
	String grabarOmodificarToken(ResumenCliente resumenCliente, String token);
	
	void grabarOmodificarCookieSC(String token, HttpServletRequest request, HttpServletResponse response);

}

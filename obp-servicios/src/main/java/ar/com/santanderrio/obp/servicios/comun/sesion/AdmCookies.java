/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sesion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Interface AdmCookies.
 */
public interface AdmCookies {

	/**
	 * Adds the secure http only cookie.
	 *
	 * @param nombre
	 *            the nombre
	 * @param valor
	 *            the valor
	 * @param expira
	 *            the expira
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	void addSecureHttpOnlyCookie(String nombre, String valor, int expira, HttpServletRequest request, HttpServletResponse response);

	/**
	 * Delete secure http only cookie.
	 *
	 * @param nombre
	 *            the nombre
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	void deleteSecureHttpOnlyCookie(String nombre, HttpServletRequest request, HttpServletResponse response);

	/**
	 * Existe cookie.
	 *
	 * @param nombre
	 *            the nombre
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return true, if successful
	 */
	boolean existeCookie(String nombre, HttpServletRequest request, HttpServletResponse response);

	/**
	 * Gets the cookie.
	 *
	 * @param nombre
	 *            the nombre
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the cookie
	 */
	String getCookie(String nombre, HttpServletRequest request, HttpServletResponse response);

	/**
	 * Update cookie.
	 *
	 * @param nombre 
	 * 				the nombre
	 * @param valor
	 * 				the valor
	 * @param expira
	 * 				the expira
	 * @param request
	 * 				the request
	 * @param response
	 * 				the response
	 */
	void updateCookie(String nombre, String valor, int expira, HttpServletRequest request, HttpServletResponse response);

}
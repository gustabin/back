/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sesion;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class AdmCookiesImpl.
 *
 * @author B015167
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AdmCookiesImpl implements AdmCookies {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AdmCookiesImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.comun.sesion.AdmCookies#addSecureHttpOnlyCookie(
	 * java.lang.String, java.lang.String, int, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void addSecureHttpOnlyCookie(String nombre, String valor, int expira, HttpServletRequest request, HttpServletResponse response) {
		try {
			Cookie cookie = new Cookie(URLEncoder.encode(nombre, ISBANStringUtils.UTF8),
					URLEncoder.encode(valor, "UTF-8"));
			cookie.setHttpOnly(true);
			cookie.setSecure(true);
			cookie.setPath("/");
			cookie.setMaxAge(expira);
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error seteando la cookie. Informacion de la cookie: Nombre={}, Valor={}", nombre, valor, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.comun.sesion.AdmCookies#
	 * deleteSecureHttpOnlyCookie(java.lang.String, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void deleteSecureHttpOnlyCookie(String nombre, HttpServletRequest request, HttpServletResponse response) {
		// setear una cookie con tiempo de expiración=0 provoca que sea borrada
		addSecureHttpOnlyCookie(nombre, "", 0, request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.comun.sesion.AdmCookies#existeCookie(java.lang.
	 * String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public boolean existeCookie(String nombre, HttpServletRequest request, HttpServletResponse response) {
		if (request.getCookies() != null) {
			try {
				Cookie[] cookies = request.getCookies();
				String nombreEncodeado = URLEncoder.encode(nombre, ISBANStringUtils.UTF8);
				for (int i = 0; i < cookies.length; i++) {
					if (nombreEncodeado.equals(cookies[i].getName())) {
						return true;
					}
				}
			} catch (UnsupportedEncodingException e) {
				LOGGER.error("Error consultando la cookie. Información de la cookie: Nombre={}", nombre, e);
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.comun.sesion.AdmCookies#getCookie(java.lang.
	 * String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getCookie(String nombre, HttpServletRequest request, HttpServletResponse response) {
		try {
			Cookie[] cookies = request.getCookies();
			String nombreEncodeado = URLEncoder.encode(nombre, ISBANStringUtils.UTF8);
			for (int i = 0; i < cookies.length; i++) {
				if (nombreEncodeado.equals(cookies[i].getName())) {
					return URLDecoder.decode(cookies[i].getValue(), ISBANStringUtils.UTF8);
				}
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error consultando la cookie. Información de la cookie: Nombre={}", nombre, e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.comun.sesion.AdmCookies#updateCookie(
	 * java.lang.String, java.lang.String, int, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void updateCookie(String nombre, String valor, int expira, HttpServletRequest request, HttpServletResponse response) {
		try {
			Cookie[] cookies = request.getCookies();
			String nombreEncodeado = URLEncoder.encode(nombre, ISBANStringUtils.UTF8);
			for (int i = 0; i < cookies.length; i++) {
				if (nombreEncodeado.equals(cookies[i].getName())) {
					Cookie cookie = cookies[i];
					cookie.setValue(URLEncoder.encode(valor, "UTF-8"));
					cookie.setPath("/");
					cookie.setMaxAge(expira);
					response.addCookie(cookie);
					break;
				}
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error actualizando la cookie. Información de la cookie: Nombre={}", nombre, e);
		}
	}
}

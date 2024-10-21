/*
 * 
 */
package ar.com.santanderrio.base.http.web.util;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class HttpUtil.
 */
public final class HttpUtil {

	/**
	 * Instantiates a new http util.
	 */
	private HttpUtil() {

	}

	/**
	 * Builds the instancia.
	 *
	 * @param request
	 *            the request
	 * @return the string
	 */
	public static String buildInstancia(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return (request.isSecure() ? "https" : "http") + "://" + request.getLocalAddr() + ":" + request.getLocalPort()
				+ uri.substring(0, uri.indexOf('/', 2));

	}

}

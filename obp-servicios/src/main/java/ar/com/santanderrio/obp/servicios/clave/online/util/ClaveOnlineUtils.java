package ar.com.santanderrio.obp.servicios.clave.online.util;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class ClaveOnlineUtils.
 */
public class ClaveOnlineUtils {

	/** The Constant HEADER_IS_WEBVIEW. */
	private static final String HEADER_IS_WEBVIEW = "isWebview";

	/** The Constant TRUE. */
	private static final String TRUE = "true";

	/**
	 * Instantiates a new clave online utils.
	 */
	private ClaveOnlineUtils() {
	}

	/**
	 * Gets the estadistica.
	 *
	 * @param request the request
	 * @param codEstadistica the cod estadistica
	 * @param codEstadisticaWebView the cod estadistica web view
	 * @return the estadistica
	 */
	public static String getEstadistica(HttpServletRequest request, String codEstadistica, String codEstadisticaWebView) {
		if (request != null && TRUE.equals(request.getHeader(HEADER_IS_WEBVIEW))) {
			return codEstadisticaWebView;
		}
		return codEstadistica;
	}

}

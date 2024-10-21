/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * The Class HTTPResponseUtils.
 */
public final class HTTPResponseUtils {

	/**
	 * Instantiates a new HTTP response utils.
	 */
	private HTTPResponseUtils() {
	}

	/**
	 * Llenar http response.
	 *
	 * @param response
	 *            the response
	 * @param bytes
	 *            the bytes
	 * @param contentType
	 *            the content type
	 * @param fileName
	 *            the file name
	 * @param visualizarEnNavegador
	 *            the visualizar en navegador
	 */
	@Deprecated
	public static void llenarHTTPResponse(HttpServletResponse response, byte[] bytes, String contentType,
			String fileName, boolean visualizarEnNavegador) {

		String contentDisposition = visualizarEnNavegador ? "inline" : "attachment";
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Length", String.valueOf(bytes.length));
		response.setHeader("Content-Disposition", contentDisposition + "; filename=\"" + fileName + "\"");

		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			IOUtils.write(bytes, outputStream);
		} catch (IOException e) {
			// TODO ver que hacer con esta excepcion
		}
	}
}

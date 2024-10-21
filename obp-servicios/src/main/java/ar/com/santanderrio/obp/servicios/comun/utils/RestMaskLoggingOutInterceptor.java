/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.utils;

import org.apache.cxf.interceptor.LoggingOutInterceptor;

import ar.com.santanderrio.obp.base.webservice.interceptor.RestMaskLoggingUtils;

/**
 * Enmascarar datos sensibles del json en el log de salida.
 * 
 * @author sergio.e.goldentair
 *
 */
public class RestMaskLoggingOutInterceptor extends LoggingOutInterceptor {
	/** Lista de palabras sensibles que se deben enmascarar. */
	private final String[] palabrasSensiblesSalientes;

	/**
	 * The super constructor.
	 *
	 * @param palabrasSensibles
	 *            the palabras sensibles
	 * @param limit
	 *            el limite de la info a loguear
	 */
	public RestMaskLoggingOutInterceptor(String[] palabrasSensibles, int limit) {
		super();
		super.setLimit(limit);
		this.palabrasSensiblesSalientes = palabrasSensibles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.cxf.interceptor.AbstractLoggingInterceptor#transform(java.lang
	 * .String)
	 */
	@Override
	protected String transform(String originalLogString) {
		return super.transform(RestMaskLoggingUtils.procesarLogRest(originalLogString, palabrasSensiblesSalientes));
	}

}
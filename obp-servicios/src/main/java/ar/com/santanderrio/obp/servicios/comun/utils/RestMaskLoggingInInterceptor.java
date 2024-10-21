/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.utils;

import java.io.PrintWriter;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.io.CachedOutputStream;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import ar.com.santanderrio.obp.base.webservice.interceptor.RestMaskLoggingUtils;

/**
 * Interceptor para loguear peticiones rest con datos de entrada sensibles
 * enmascarados.<br>
 * Si no se recibe lista de palabras sensibles se comporta como el padre dejando
 * la trama original.
 * 
 * @author sergio.e.goldentair
 *
 */
public class RestMaskLoggingInInterceptor extends LoggingInInterceptor {
	/** Lista de palabras sensibles que se deben enmascarar. */
	private final String[] palabrasSensiblesEntrantes;

	/**
	 * The super constructor.
	 *
	 * @param palabrasSensibles
	 *            the palabras sensibles
	 * @param limit
	 *            el limite de la info a loguear
	 */
	public RestMaskLoggingInInterceptor(String[] palabrasSensibles, int limit) {
		super();
		this.palabrasSensiblesEntrantes = palabrasSensibles;
		super.setLimit(limit);
		this.addBefore(LoggingInInterceptor.class.getCanonicalName());
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
		return super.transform(RestMaskLoggingUtils.procesarLogRest(originalLogString, palabrasSensiblesEntrantes));
	}

	/**
	 * sobreescribo el writePayload para manejar el pretty print para json, si
	 * no es json los sigue manejando el super.
	 *
	 * @param builder
	 *            the builder
	 * @param cos
	 *            the cos
	 * @param encoding
	 *            the encoding
	 * @param contentType
	 *            the content type
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writePayload(StringBuilder builder, CachedOutputStream cos, String encoding, String contentType)
			throws Exception {
		if (isPrettyLogging() && isJson(contentType) && cos.size() > 0) {
			String procesado = cos.getInputStream().toString();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
			CachedOutputStream cos2 = new CachedOutputStream();
			try {
				Object json = objectMapper.readValue(procesado, Object.class);
				String indented = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
				PrintWriter p = new PrintWriter(cos2);
				p.println(indented);
				p.flush();
				p.close();
			} catch (JsonParseException ex) {
				super.writePayload(builder, cos, encoding, contentType);
			}
			super.writePayload(builder, cos2, encoding, contentType);
		} else {
			super.writePayload(builder, cos, encoding, contentType);
		}
	}

	/**
	 * Validar si el content type es json.
	 *
	 * @param contentType
	 *            the content type
	 * @return true, if is json
	 */
	private boolean isJson(String contentType) {
		return StringUtils.isNotBlank(contentType) && contentType.contains(MediaType.APPLICATION_JSON);
	}
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.alias.dao;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.webservice.interceptor.LoggingCallback;

/**
 * Loguear el inbound|outbound original cuando se utiliza Wss4j para firmar y
 * encriptar el mensaje entrante|saliente.
 * 
 * The Class OriginalWss4jInterceptor.
 */
public class OriginalWss4jInterceptor extends AbstractPhaseInterceptor<Message> {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OriginalWss4jInterceptor.class);

	/**
	 * The constructor.
	 *
	 * @param phase
	 *            the phase
	 */
	public OriginalWss4jInterceptor(String phase) {
		super(phase);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.
	 * message.Message)
	 */
	@Override
	public void handleMessage(Message message) {
		final CachedOutputStream newOut = new CachedOutputStream();
		try {
			newOut.registerCallback(new LoggingCallback());
			message.getContent(javax.xml.soap.SOAPMessage.class).writeTo(newOut);
		} catch (Exception e) {
			throw new Fault(e);
		} finally {
			try {
				newOut.close();
			} catch (Exception e) {
				LOGGER.error("Error en obtener mensaje: {}.", e);
			}
		}
	}

}

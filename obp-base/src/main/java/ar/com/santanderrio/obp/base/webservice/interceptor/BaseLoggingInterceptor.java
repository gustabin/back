
package ar.com.santanderrio.obp.base.webservice.interceptor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.TransformerException;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * Clase con el comportamiento base para loguear si es q no se necesita alguna
 * particularidad.
 * 
 * The Class BaseLoggingInterceptor.
 */
public class BaseLoggingInterceptor extends AbstractPhaseInterceptor<Message> {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseLoggingInterceptor.class);

	/** The pretty logging. */
	private boolean prettyLogging;

	/**
	 * Instantiates a new RSA logging interceptor.
	 *
	 * @param phase
	 *            the phase
	 */
	public BaseLoggingInterceptor(String phase) {
		super(phase);
	}

	/**
	 * Setter para pretty logging.
	 *
	 * @param flag
	 *            el nuevo pretty logging
	 */
	public void setPrettyLogging(boolean flag) {
		prettyLogging = flag;
	}

	/**
	 * Checks if is pretty logging.
	 *
	 * @return true, if is pretty logging
	 */
	public boolean isPrettyLogging() {
		return prettyLogging;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.
	 * message.Message)
	 */
	public void handleMessage(Message message) {
		try {
			if (Phase.PRE_STREAM.equals(this.getPhase())) {
				this.handleRequestMessage(message);
			} else if (Phase.RECEIVE.equals(this.getPhase())) {
				this.handleResponseMessage(message);
			} else if (Phase.POST_PROTOCOL.equals(this.getPhase())) {
				this.handlePostProtocol(message);
			}
		} catch (IOException e) {
			LOGGER.error("Error de io en el manejo del mensaje.", e);
			throw new Fault(e);
		} catch (TransformerException e) {
			LOGGER.error("Error al transformar el dato en el manejo del mensaje.", e);
			throw new Fault(e);
		}
	}

	/**
	 * Handle response message.
	 *
	 * @param message
	 *            the message
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws TransformerException
	 *             the transformer exception
	 */
	private void handleResponseMessage(Message message) throws IOException, TransformerException {

		InputStream is = message.getContent(InputStream.class);
		if (is != null) {
			CachedOutputStream os = new CachedOutputStream();

			IOUtils.copy(is, os);
			os.flush();
			message.setContent(InputStream.class, os.getInputStream());
			is.close();
			String xml = WritterPrettyPrintUtils.getPrettyPrint(os);
			LOGGER.info("Se loggea el siguiente xml \r\n{}", xml);
			os.close();

		}
	}

	/**
	 * Handle request message.
	 *
	 * @param message
	 *            the message
	 */
	private void handleRequestMessage(Message message) {
		final OutputStream os = message.getContent(OutputStream.class);
		if (os == null) {
			return;
		}
		final CacheAndWriteOutputStream newOut = new CacheAndWriteOutputStream(os);
		message.setContent(OutputStream.class, newOut);
		newOut.registerCallback(new LoggingCallback());
	}

	/**
	 * Manejar los req|res en phase de post protocol.
	 *
	 * @param message
	 *            the message
	 */
	private void handlePostProtocol(Message message) {
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

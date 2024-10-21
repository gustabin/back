package ar.com.santanderrio.obp.base.log.cxf.interceptor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

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

import ar.com.santanderrio.obp.base.webservice.interceptor.LoggingCallback;
import ar.com.santanderrio.obp.base.webservice.interceptor.WritterPrettyPrintUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class RSALoggingInterceptor.
 */
public class RSALoggingInterceptor extends AbstractPhaseInterceptor<Message> {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RSALoggingInterceptor.class);

	/** The pretty logging. */
	private boolean prettyLogging;

	/**
	 * Instantiates a new RSA logging interceptor.
	 *
	 * @param phase
	 *            the phase
	 */
	public RSALoggingInterceptor(String phase) {
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
			}
		} catch (IOException e) {
			throw new Fault(e);
		} catch (TransformerException e) {
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

		Date t0 = new Date();
		InputStream is = message.getContent(InputStream.class);
		if (is != null) {
			CachedOutputStream os = new CachedOutputStream();

			IOUtils.copy(is, os);
			os.flush();
			message.setContent(InputStream.class, os.getInputStream());
			is.close();
			String xml = WritterPrettyPrintUtils.getPrettyPrint(os);
			LOGGER.info(xml);
			os.close();
			Date t1 = new Date();
			long t = t1.getTime() - t0.getTime();
			LOGGER.info("**TIME RESPUESTA LOG RSA = " + t);

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

}

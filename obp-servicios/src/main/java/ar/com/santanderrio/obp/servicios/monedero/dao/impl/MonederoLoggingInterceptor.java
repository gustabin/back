package ar.com.santanderrio.obp.servicios.monedero.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.webservice.interceptor.WritterPrettyPrintUtils;

/**
 * 
 * The Class MonederoLoggingInterceptor.
 * 
 * @author ITResources
 * 
 */
public class MonederoLoggingInterceptor extends AbstractPhaseInterceptor<Message> {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MonederoLoggingInterceptor.class);
		
	/**
	 * Instantiates a new monedero logging interceptor.
	 *
	 * @param phase the phase
	 */
	public MonederoLoggingInterceptor(String phase) {
		super(phase);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
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
	 * @param message the message
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TransformerException the transformer exception
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
	 * @param message the message
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
	 * The Class LoggingCallback.
	 */
	public class LoggingCallback implements CachedOutputStreamCallback {

		/* (non-Javadoc)
		 * @see org.apache.cxf.io.CachedOutputStreamCallback#onFlush(org.apache.cxf.io.CachedOutputStream)
		 */
		public void onFlush(CachedOutputStream cos) {

		}

		/* (non-Javadoc)
		 * @see org.apache.cxf.io.CachedOutputStreamCallback#onClose(org.apache.cxf.io.CachedOutputStream)
		 */
		public void onClose(CachedOutputStream cos) {
			try {
				String request = getPrettyPrint(cos).replaceFirst("(?s)<Password[^>]*>.*?</Password>",
                        "<Password>XXXXXXXX</Password>");
				LOGGER.info(request);
			} catch (Exception e) {
				LOGGER.error("Error en pretty print", e);
			}
		}
	}
	
	/**
	 * Gets the pretty print.
	 *
	 * @param cos the cos
	 * @return the pretty print
	 * @throws TransformerConfigurationException the transformer configuration exception
	 * @throws TransformerException the transformer exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String getPrettyPrint(CachedOutputStream cos) throws TransformerConfigurationException, TransformerException, IOException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		StringWriter swriter = new StringWriter();
		serializer.transform(new StreamSource(cos.getInputStream()), new StreamResult(swriter));
		return swriter.toString();		
	}

}

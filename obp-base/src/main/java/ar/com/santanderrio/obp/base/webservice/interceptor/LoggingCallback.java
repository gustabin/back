/**
 * 
 */
package ar.com.santanderrio.obp.base.webservice.interceptor;

import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * Poder manipular el buffer y loguear el xml del mensaje soap.
 * 
 * @author sergio.e.goldentair
 *
 */
public class LoggingCallback implements CachedOutputStreamCallback {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingCallback.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.io.CachedOutputStreamCallback#onFlush(org.apache.cxf.
	 * io.CachedOutputStream)
	 */
	public void onFlush(CachedOutputStream cos) {
		LOGGER.debug("Se llama al onFlush sin implementacion.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.io.CachedOutputStreamCallback#onClose(org.apache.cxf.
	 * io.CachedOutputStream)
	 */
	public void onClose(CachedOutputStream cos) {
		LOGGER.info("Se trabajara con el siguiente xml \r\n{}", WritterPrettyPrintUtils.getPrettyPrint(cos));
	}
}

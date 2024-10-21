/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.rsa.sei.RsaSEI;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;

/**
 * The Class RsaSEIImpl.
 */
@Component("RsaSEIImpl")
public class RsaSEIImpl implements RsaSEI {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RsaSEIImpl.class);

	/** The Constant MENSAJE_RESPUESTA. */
	private static final String MENSAJE_RESPUESTA = "Respuesta: {}.";

	/** The rsa manager. */
	@Autowired
	private RsaManager rsaManager;
	
	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.sei.BilleteraSEI#getRsaDeviceTokenCookie()
	 */
	@Override
	public Respuesta<String> getRsaDeviceTokenCookie() {
		Respuesta<String> respuesta = rsaManager.getRsaDeviceTokenCookie();
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}
}

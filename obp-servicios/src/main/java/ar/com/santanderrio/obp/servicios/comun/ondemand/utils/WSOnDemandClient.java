/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.ondemand.utils;

import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODRequest;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODResponse;

/**
 * The Interface WSOnDemandClient.
 *
 * @author sergio.e.goldentair
 */
public interface WSOnDemandClient {

	/**
	 * Send.
	 *
	 * @param req
	 *            the req
	 * @param url
	 *            the url
	 * @return the WSOD response
	 * @throws WSODException
	 *             the WSOD exception
	 */
	WSODResponse send(WSODRequest req, String url) throws WSODException;
}
